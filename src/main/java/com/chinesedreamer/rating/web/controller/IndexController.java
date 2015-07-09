package com.chinesedreamer.rating.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinesedreamer.rating.system.session.service.UserSessionService;
import com.chinesedreamer.rating.system.user.exception.PasswordIncorrectException;
import com.chinesedreamer.rating.system.user.exception.UserFrozenException;
import com.chinesedreamer.rating.system.user.exception.UserNotExistException;
import com.chinesedreamer.rating.system.user.service.UserService;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class IndexController {
	@Resource
	private UserService userService;
	@Resource
	private UserSessionService userSessionService;
	
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public String login(Model model,HttpServletRequest request) throws UserFrozenException,UserNotExistException,PasswordIncorrectException{
		return "login";
	}
	
	@RequestMapping(value = "logout",method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(Model model,HttpServletRequest request) {
		this.userService.logout(this.userSessionService.getCurrentUserSession());
		return "login";
	}
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String userLogin(Model model,HttpServletRequest request) throws UserFrozenException,UserNotExistException,PasswordIncorrectException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		this.userService.login(username, password);
		
		model.addAttribute("menus", this.userService.getUserMenus(username));
		model.addAttribute("profile", this.userService.showUserProfile(username));
		model.addAttribute("currentUser", this.userService.getUser(username).getName());
		
		return "index";
	}
	
	@RequestMapping(value = "welcome",method = RequestMethod.GET)
	public String welcome(Model model,HttpServletRequest request){
		return "welcome";
	}
}
