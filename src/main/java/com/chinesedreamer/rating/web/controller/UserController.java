package com.chinesedreamer.rating.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.system.session.service.UserSessionService;
import com.chinesedreamer.rating.system.user.service.UserService;
import com.chinesedreamer.rating.system.user.vo.UserVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月24日 下午8:20:13 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private UserSessionService userSessionService;
	
	/**
	 * 鑾峰彇鎵�湁鐢ㄦ埛鍒楄〃
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/user/list")
	public Map<String, Object> getUserList(Model model){
		//JSON.toJSONString(this.userService.getAllUsers());
		Map<String, Object> rstMap = new HashMap<String, Object>();
		List<UserVo> vos = this.userService.getAllUsers();
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}
	
	/**
	 * 鍒涘缓鐢ㄦ埛
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "system/user/create",method = RequestMethod.POST)
	public ResponseVo createUser(HttpServletRequest request){
		ResponseVo vo = new ResponseVo();
		String username = request.getParameter("username").trim();
		String name = request.getParameter("name").trim();
		Long groupId = Long.parseLong(request.getParameter("groupId").trim());
		Integer positionId = Integer.parseInt(request.getParameter("positionId").trim());
		String phone = request.getParameter("phone").trim();
		if (null != this.userService.getUser(username)) {
			vo.setErrorMessage("用户已经存在");
		}else {
			this.userService.saveUser(username,name, groupId, positionId, phone);
		}
		return vo;
	}
	
	/**
	 * 淇敼鐢ㄦ埛
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "system/user/update",method = RequestMethod.POST)
	public ResponseVo updateUser(HttpServletRequest request){
		ResponseVo vo = new ResponseVo();
		String username = request.getParameter("username").trim();
		String name = request.getParameter("name").trim();
		Long groupId = Long.parseLong(request.getParameter("groupId").trim());
		Integer positionId = Integer.parseInt(request.getParameter("positionId").trim());
		String phone = request.getParameter("phone").trim();
		if (null == this.userService.getUser(username)) {
			vo.setErrorMessage("用户不存在");
		}else {
			this.userService.updateUser(username,name, groupId, positionId, phone);
		}
		return vo;
	}
	
//	/**
//	 * 获取个人信息
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "system/user/showProfile",method = RequestMethod.POST)
//	public String showProfile(Model model){
//		String username = this.userSessionService.getCurrentUserSession().getUsername();
//		model.addAttribute("profile", this.userService.showUserProfile(username));
//		return "/systemMgmt/userMgmt/userProfile";
//	}
	
	/**
	 * 修改个人信息
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/user/updateProfile",method = RequestMethod.POST)
	public ResponseVo updateProfile(HttpServletRequest request){
		String name = request.getParameter("name").trim();
		String phone = request.getParameter("phone").trim();
		String oldPassword = request.getParameter("oldPassword").trim();
		String newPassword = request.getParameter("newPassword").trim();
		String username = this.userSessionService.getCurrentUserSession().getUsername();
		return this.userService.updateProfile(username, oldPassword, newPassword,
				name,phone);
	}
	
	/*********** user 部分 *************/
	@ResponseBody
	@RequestMapping(value = "user/query",method = RequestMethod.GET)
	public List<SelectVo> queryUser(Model model, HttpServletRequest request){
		String name = request.getParameter("name");
		return this.userService.lookupUser(name);
	}
}
