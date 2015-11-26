package com.chinesedreamer.rating.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.rating.common.io.DefaultDownloadComponent;
import com.chinesedreamer.rating.common.io.DownloadComponent;
import com.chinesedreamer.rating.common.utils.IpUtil;
import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.system.security.constant.ActivationState;
import com.chinesedreamer.rating.system.security.service.SecurityService;
import com.chinesedreamer.rating.system.security.vo.LisenceVo;
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
	private Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Resource
	private UserService userService;
	@Resource
	private UserSessionService userSessionService;
	@Resource
	private SecurityService securityService;
	
	@RequestMapping(value = "login",method = RequestMethod.GET)
	public String login(Model model,HttpServletRequest request) throws UserFrozenException,UserNotExistException,PasswordIncorrectException{
		LisenceVo vo = securityService.isSystemAuthorised();
		if (vo.getState().equals(ActivationState.ACTIVE)) {
			model.addAttribute("authorised", "0");
		}else if (vo.getState().equals(ActivationState.PROBATION)){
			model.addAttribute("authorised", "1");
			model.addAttribute("remaining", vo.getRemaingDay());
			try {
				model.addAttribute("mac", IpUtil.getLocalMac(IpUtil.getLocalhost()));
			} catch (Exception e) {
				logger.error("",e);
			} 
		}else{
			model.addAttribute("authorised", "-1");
		}
		return "login";
	}
	
	@RequestMapping(value = "logout",method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(Model model,HttpServletRequest request) {
		this.userService.logout(this.userSessionService.getCurrentUserSession());
		return "login";
	}
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public String userLogin(Model model,HttpServletRequest request) throws UserFrozenException,UserNotExistException,PasswordIncorrectException{
		LisenceVo vo = securityService.isSystemAuthorised();
		if (vo.getState().equals(ActivationState.ACTIVE) || vo.getState().equals(ActivationState.PROBATION)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			this.userService.login(username, password);
			
			model.addAttribute("menus", this.userService.getUserMenus(username));
			model.addAttribute("profile", this.userService.showUserProfile(username));
			model.addAttribute("currentUser", this.userService.getUser(username).getName());
			
			return "index";
		}
		return "license/license";
	}
	
	@RequestMapping(value = "welcome",method = RequestMethod.GET)
	public String welcome(Model model,HttpServletRequest request){
		return "welcome";
	}
	
	/**
	 * 下载帮助文档
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = "downloadhelp",method = RequestMethod.GET)
	public void downoadHelp(HttpServletRequest request, HttpServletResponse response){
		String filePath = request.getSession().getServletContext().getRealPath("/") 
				+ File.separator + "WEB-INF" 
				+ File.separator + "template"
				+ File.separator + "User Manual.doc";
		File file = new File(filePath);
		DownloadComponent downloadComponent = new DefaultDownloadComponent();
		try {
			downloadComponent.download(request, response, file.getPath(), file.getName());
		} catch (IOException e) {
			logger.error("{}",e);
		}
	}
	
	/**
	 * 激活系统
	 * @param request
	 */
	@ResponseBody
	@RequestMapping(value = "authorise",method = RequestMethod.GET)
	public ResponseVo authorise(HttpServletRequest request){
		String pass = request.getParameter("macPass").trim();
		boolean result = this.securityService.authorise(pass);
		ResponseVo vo = new ResponseVo();
		Map<String, String> rst = new HashMap<String, String>();
		if (result) {
			rst.put("authorised", "0");
		}else {
			rst.put("authorised", "1");
		}
		vo.setObj(rst);
		return vo;
	}
}
