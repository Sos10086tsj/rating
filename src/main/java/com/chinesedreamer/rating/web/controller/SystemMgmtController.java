package com.chinesedreamer.rating.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinesedreamer.rating.common.vo.SelectVo;
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
		//用户组别
		List<SelectVo> groups = this.userGroupServcie.getAllGroups();
		model.addAttribute("groups", groups) ;
		//职位
		UserPositionType[] positions = UserPositionType.values();
		model.addAttribute("positions", positions);
		
		return "systemMgmt/userMgmt/userMgmt";
	}
	
	@RequestMapping(value = "rating",method = RequestMethod.GET)
	public String mgmtRating(Model model){
		return "systemMgmt/ratingMgmt/ratingMgmt";
	}
}
