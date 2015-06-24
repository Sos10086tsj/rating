package com.chinesedreamer.rating.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinesedreamer.rating.system.group.service.UserGroupServcie;
import com.chinesedreamer.rating.system.user.UserPositionType;

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
	private UserGroupServcie userGroupServcie;
	
	/**
	 * 鐢ㄦ埛绠＄悊
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user",method = RequestMethod.GET)
	public String mgmtUser(Model model){
		//閮ㄩ棬鍒楄〃
		model.addAttribute("groups", this.userGroupServcie.getAllGroups()) ;
		//鑱屼綅鍒楄〃
		model.addAttribute("positions", UserPositionType.values());
		
		return "systemMgmt/userMgmt/userMgmt";
	}
	
	@RequestMapping(value = "rating",method = RequestMethod.GET)
	public String mgmtRating(Model model){
		return "systemMgmt/ratingMgmt/ratingMgmt";
	}
}
