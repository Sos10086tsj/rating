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
import com.chinesedreamer.rating.system.group.service.UserGroupServcie;
import com.chinesedreamer.rating.system.user.UserPositionType;
import com.chinesedreamer.rating.system.user.service.UserService;
import com.chinesedreamer.rating.system.user.vo.UserVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015骞�鏈�1鏃�涓嬪崍3:22:33 
 * Copyright:   Copyright (c)2015
 */
@Controller
@RequestMapping(value = "system")
public class SystemMgmtController {
	@Resource
	private UserService userService;
	@Resource
	private UserGroupServcie userGroupServcie;
	
	/**
	 * 鐢ㄦ埛绠＄悊
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user",method = RequestMethod.GET)
	public String mgmtUser(Model model){
		model.addAttribute("users", this.userService.getAllUsers()) ;
		//閮ㄩ棬鍒楄〃
		model.addAttribute("groups", this.userGroupServcie.getAllGroups()) ;
		//鑱屼綅鍒楄〃
		model.addAttribute("positions", UserPositionType.values());
		
		return "systemMgmt/userMgmt/userMgmt";
	}
	
	/**
	 * 鑾峰彇鎵�湁鐢ㄦ埛鍒楄〃
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "user/list")
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
	@RequestMapping(value = "user/create",method = RequestMethod.POST)
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
	@RequestMapping(value = "user/update",method = RequestMethod.POST)
	public ResponseVo updateUser(HttpServletRequest request){
		ResponseVo vo = new ResponseVo();
		String username = request.getParameter("username").trim();
		String name = request.getParameter("name").trim();
		Long groupId = Long.parseLong(request.getParameter("groupId").trim());
		Integer positionId = Integer.parseInt(request.getParameter("positionId").trim());
		String phone = request.getParameter("phone").trim();
		if (null == this.userService.getUser(username)) {
			vo.setErrorMessage("鐢ㄦ埛涓嶅瓨鍦紒");
		}else {
			this.userService.updateUser(username,name, groupId, positionId, phone);
		}
		return vo;
	}
}
