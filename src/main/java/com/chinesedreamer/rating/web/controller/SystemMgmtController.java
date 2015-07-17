package com.chinesedreamer.rating.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.rating.service.RatingService;
import com.chinesedreamer.rating.system.group.UserGroupLevel;
import com.chinesedreamer.rating.system.group.service.UserGroupServcie;
import com.chinesedreamer.rating.system.user.UserPositionType;
import com.chinesedreamer.rating.template.OptionCategory;

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
	@Resource
	private RatingService ratingService;
	
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
		model.addAttribute("suppTemplates", this.ratingService.getAllTemplates());
		return "systemMgmt/ratingMgmt/ratingMgmt";
	}
	
	/**
	 * 组管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "group",method = RequestMethod.GET)
	public String mgmtGroup(Model model){
		model.addAttribute("levels", UserGroupLevel.initGroupLevels());
		return "systemMgmt/group/group";
	}
	
	/**
	 * 得分项管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "option",method = RequestMethod.GET)
	public String mgmtOption(Model model){
		model.addAttribute("categories", OptionCategory.values());
		return "systemMgmt/option/option";
	}
	
	/**
	 * 模板管理
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "template",method = RequestMethod.GET)
	public String mgmtTemplate(Model model){
		model.addAttribute("categories", OptionCategory.values());
		return "systemMgmt/template/template";
	}
}
