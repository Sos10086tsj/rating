package com.chinesedreamer.rating.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.service.RatingService;
import com.chinesedreamer.rating.rating.vo.RatingCreateVo;
import com.chinesedreamer.rating.rating.vo.RatingUserVo;
import com.chinesedreamer.rating.system.session.service.UserSessionService;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.system.user.service.UserService;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015��6��24�� ����8:17:11 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class RatingController {
	@Resource
	private RatingService ratingService;
	@Resource
	private UserSessionService userSessionService;
	@Resource
	private UserService userService;
	
	/**
	 * 投票管理列表页
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/rating/list")
	public Map<String, Object> getRatingList(Model model){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		List<Rating> vos = this.ratingService.findAll();
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}
	
	/**
	 * 进入新建投票页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "system/rating/create",method = RequestMethod.GET)
	public String showCreateRating(Model model){
		model.addAttribute("m", new RatingCreateVo());
		model.addAttribute("suppTemplates", this.ratingService.getAllTemplates());
		return "systemMgmt/ratingMgmt/create";
	}
	
	/**
	 * 新建投票提交
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "system/rating/create",method = RequestMethod.POST)
	public String createRating(RatingCreateVo vo){
		this.ratingService.saveRating(vo);
		return "redirect:/system/rating";
	}
	
	/********* 用户投票部分 **************/
	/**
	 * 用户投票主页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "rating",method = RequestMethod.GET)
	public String rating(Model model){
		return "rating/rating";
	}
	
	/**
	 * 获取投票列表数据
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "rating/list",method = RequestMethod.POST)
	public Map<String, Object> userRatingList(Model model){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		User user = this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername());
		List<RatingUserVo> vos = this.ratingService.getRatingUserVos(user);
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}
}
