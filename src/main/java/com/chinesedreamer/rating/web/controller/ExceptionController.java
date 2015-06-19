package com.chinesedreamer.rating.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:24:42 
 * Copyright:   Copyright (c)2015
 */
@Controller
@RequestMapping(value = "exception")
public class ExceptionController{
	
	/**
	 * 用户超时，返回到登录页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "sessionTimeout")
	public String sessionTimeout(HttpServletRequest request, Model model){
		return "redirect:/login";
	}
}
