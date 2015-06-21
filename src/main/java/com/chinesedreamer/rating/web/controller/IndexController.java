package com.chinesedreamer.rating.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午6:55:37 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class IndexController {
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
	public String login(Model model){
		return "index";
	}
}
