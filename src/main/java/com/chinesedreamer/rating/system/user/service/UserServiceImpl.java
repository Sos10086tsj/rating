package com.chinesedreamer.rating.system.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.exception.category.BizException;
import com.chinesedreamer.rating.common.utils.EncryptionUtil;
import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.system.rabc.mapping.logic.ResAuOprMappingLogic;
import com.chinesedreamer.rating.system.rabc.mapping.logic.RoleAuthMappingLogic;
import com.chinesedreamer.rating.system.rabc.mapping.logic.UserRoleMappingLogic;
import com.chinesedreamer.rating.system.rabc.mapping.model.ResAuOprMapping;
import com.chinesedreamer.rating.system.rabc.mapping.model.RoleAuthMapping;
import com.chinesedreamer.rating.system.rabc.mapping.model.UserRoleMapping;
import com.chinesedreamer.rating.system.rabc.resource.logic.SysResourceLogic;
import com.chinesedreamer.rating.system.rabc.resource.model.SysResource;
import com.chinesedreamer.rating.system.session.logic.UserSessionLogic;
import com.chinesedreamer.rating.system.session.model.UserSession;
import com.chinesedreamer.rating.system.user.MenuComparator;
import com.chinesedreamer.rating.system.user.SysResourceComparator;
import com.chinesedreamer.rating.system.user.UserPositionType;
import com.chinesedreamer.rating.system.user.UserStatus;
import com.chinesedreamer.rating.system.user.exception.PasswordIncorrectException;
import com.chinesedreamer.rating.system.user.exception.UserFrozenException;
import com.chinesedreamer.rating.system.user.exception.UserNotExistException;
import com.chinesedreamer.rating.system.user.logic.UserLogic;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.system.user.vo.Menu;
import com.chinesedreamer.rating.system.user.vo.UserVo;
import com.chinesedreamer.rating.web.filter.SessionFilter;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015锟斤拷1锟戒笂鍗:15:59 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserServiceImpl implements UserService{
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserLogic logic;
	@Resource
	private UserSessionLogic userSessionLogic;
	@Resource
	private UserRoleMappingLogic userRoleMappingLogic;
	@Resource
	private RoleAuthMappingLogic roleAuthMappingLogic;
	@Resource
	private ResAuOprMappingLogic resAuOprMappingLogic;
	@Resource
	private SysResourceLogic sysResourceLogic;
	
	@Override
	public ResponseVo login(String username, String password) throws UserFrozenException,UserNotExistException,PasswordIncorrectException{
		this.logger.info("user:{} passwor:{} try to login.", username, password);
		User user = this.logic.findByUsernameAndStatus(username, UserStatus.ACTIVE);
		if(null == user){
			BizException ex = null;
			if (null == this.logic.findByUsernameAndStatus(username, UserStatus.INACTIVE)) {
				ex = new UserFrozenException("用户：" + username + " 用户不存在");
			}else {
				ex = new UserNotExistException("用户：" + username + " 已被禁用");
			}
			logger.error("{}",ex);
			throw ex;
		}
		if (!EncryptionUtil.md5L32(password + user.getSalt()).equals(user.getPassword())) {
			PasswordIncorrectException ex = new PasswordIncorrectException("鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒");
			logger.error("{}",ex);
			throw ex;
		}
		//淇濆瓨缂撳瓨session 淇℃伅
		UserSession userSession = this.userSessionLogic.findByUsername(username);
		if (null == userSession) {
			userSession = new UserSession();
		}
		userSession.setSessionId(SessionFilter.SessionContext.getContext().getSession().getId());
		userSession.setCreateDate(new Date());
		userSession.setUsername(username);
		this.userSessionLogic.saveUserSessionCache(userSession);
		this.userSessionLogic.save(userSession);
		return new ResponseVo(user);
	}

	@Override
	public List<Menu> getUserMenus(String username) {
		User user = this.logic.findByUsernameAndStatus(username, UserStatus.ACTIVE);
		//1. 鎵惧埌鐢ㄦ埛鎷ユ湁鐨勮锟
		List<UserRoleMapping> userRoleMappings = this.userRoleMappingLogic.findByUserId(user.getId());
		//2. 鏍规嵁role鎵惧埌鎵�湁鐨勬潈闄�
		Set<Long> roleIds = new HashSet<Long>();
		for (UserRoleMapping userRoleMapping : userRoleMappings) {
			roleIds.add(userRoleMapping.getRoleId());
		}
		List<RoleAuthMapping> roleAuthMappings = this.roleAuthMappingLogic.findByRoleIds(roleIds);
		//3. 鏍规嵁auth鎵惧埌鎵�湁鐨勮祫婧�
		Set<String> authCodes = new HashSet<String>();
		for (RoleAuthMapping roleAuthMapping : roleAuthMappings) {
			authCodes.add(roleAuthMapping.getAuthority().getCode());
		}
		List<ResAuOprMapping> resAuOprMappings = this.resAuOprMappingLogic.findByAutCodes(authCodes);
		Set<SysResource> resources = new HashSet<SysResource>();
		for (ResAuOprMapping resAuOprMapping : resAuOprMappings) {
			if (resAuOprMapping.getResource().getShow()) {
				resources.add(resAuOprMapping.getResource());
			}
		}
		return this.generateMenuTree(resources);
	}

	private List<Menu> generateMenuTree(Set<SysResource> originResources){
		List<Menu> menus = new ArrayList<Menu>();
		
		List<SysResource> resources = new ArrayList<SysResource>(originResources);
		Collections.sort(resources, new SysResourceComparator());
		Map<String, Menu> root = new HashMap<String, Menu>();
		//浠呬粎涓ょ骇鑿滃崟锛屽浜庡綋鍓嶇郴缁熻冻锟
		for (SysResource resource : resources) {
			if (StringUtils.isEmpty(resource.getParentCode())) {//一级菜单
				Menu menu = new Menu();
				menu.setName(resource.getName());
				menu.setUrl(resource.getUrl());
				menu.setSeq(resource.getSeq());
				root.put(resource.getCode(), menu);
			}else {//二级菜单
				Menu menu = root.get(resource.getParentCode());
				if (null == menu) {//只授权了子菜单的权限，需要补充父菜单权限
					menu = new Menu();
					SysResource parentResource = this.sysResourceLogic.findByCode(resource.getParentCode());
					menu.setName(parentResource.getName());
					menu.setUrl(parentResource.getUrl());
					menu.setSeq(parentResource.getSeq());
				}
				Menu subMenu = new Menu();
				subMenu.setName(resource.getName());
				subMenu.setUrl(resource.getUrl());
				menu.getSubMenu().add(subMenu);
				root.put(resource.getParentCode(), menu);
			}
		}
		
		for (String key : root.keySet()) {
			menus.add(root.get(key));
		}
		Collections.sort(menus, new MenuComparator());
		return menus;
	}

	@Override
	public List<UserVo> getAllUsers() {
		List<UserVo> vos = new ArrayList<UserVo>();
		List<User> users = this.logic.findAll();
		for (User user : users) {
			vos.add(this.conver2Vo(user));
		}
		return vos;
	}
	private UserVo conver2Vo(User user) {
		UserVo vo = new UserVo();
		vo.setGroupName(user.getUserGroup().getName());
		vo.setId(user.getId());
		vo.setName(user.getName());
		vo.setPhone(user.getPhone());
		vo.setPositionName(UserPositionType.get(user.getPositionId()).getLabel());
		vo.setStatus(user.getStatus().toString());
		return vo;
	}

	@Override
	public void saveUser(String username,String name, Long groupId, Integer positionId,
			String phone) {
		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setGroupId(groupId);
		user.setPositionId(positionId);
		String salt = EncryptionUtil.generateSalt(6);
		user.setSalt(salt);
		user.setStatus(UserStatus.ACTIVE);
		user.setPhone(phone);
		user.setPassword(EncryptionUtil.md5L32("123456" + salt));
		this.logic.save(user);
	}

	@Override
	public User getUser(String username) {
		return this.logic.findByUsername(username);
	}

	@Override
	public void updateUser(String username, String name, Long groupId,
			Integer positionId, String phone) {
		User user = this.logic.findByUsername(username);
		user.setName(name);
		user.setGroupId(groupId);
		user.setPositionId(positionId);
		user.setPhone(phone);
		this.logic.save(user);
	}
}
