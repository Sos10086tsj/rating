package com.chinesedreamer.rating.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.rating.system.user.service.UserService;
import com.chinesedreamer.rating.system.user.vo.UserVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 下午3:22:33 
 * Copyright:   Copyright (c)2015
 */
@Controller
@RequestMapping(value = "system")
public class SystemMgmtController {
	@Resource
	private UserService userService;
	/**
	 * 用户管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user",method = RequestMethod.GET)
	public String mgmtUser(Model model){
		model.addAttribute("users", this.userService.getAllUsers()) ;
		return "systemMgmt/userMgmt/userMgmt";
	}
	
	/**
	 * 获取所有用户列表
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "user/list")
	public List<UserVo> getUserList(Model model){
		//JSON.toJSONString(this.userService.getAllUsers());
		return this.userService.getAllUsers();
	}
}
