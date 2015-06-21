package com.chinesedreamer.rating.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinesedreamer.rating.system.user.exception.PasswordIncorrectException;
import com.chinesedreamer.rating.system.user.exception.UserFrozenException;
import com.chinesedreamer.rating.system.user.exception.UserNotExistException;
import com.chinesedreamer.rating.system.user.service.UserService;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午6:55:37 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class IndexController {
	@Resource
	private UserService userService;
	/**
	 * 用户登录
	 * @param model
	 * @return 登录页面
	 */
	@RequestMapping(value = "index",method = RequestMethod.GET)
	public String index(Model model){
		return "login";
	}
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String login(Model model,HttpServletRequest request) throws UserFrozenException,UserNotExistException,PasswordIncorrectException{
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		this.userService.login(username, password);
		return "index";
	}
}
