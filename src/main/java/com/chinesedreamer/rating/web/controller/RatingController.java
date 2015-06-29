package com.chinesedreamer.rating.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.chinesedreamer.rating.common.vo.OptionTitle;
import com.chinesedreamer.rating.common.vo.SelectVo;
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
	
	/**
	 * 用户投票编辑页
	 * @param model
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "rating/vote/{tmplId}",method = RequestMethod.GET)
	public String showRaringVote(Model model,@PathVariable("tmplId")Long tmplId){
		model.addAttribute("votePage", this.ratingService.getRatingVotePage(tmplId));
		List<OptionTitle> options = this.ratingService.getTmplOptions(tmplId);
		Integer totalWidth = 120;
		for (OptionTitle optionTitle : options) {
			totalWidth += optionTitle.getWidth();
		}
		model.addAttribute("options", options);
		model.addAttribute("gridWidth", totalWidth);
		List<SelectVo> users = this.userService.lookupUser("");
		model.addAttribute("usersJson", JSON.toJSONString(users).replace("\"", "'"));
		model.addAttribute("scores", JSON.toJSONString(this.ratingService.getAllScores()).replace("\"", "'"));
		model.addAttribute("optionsJson", JSON.toJSONString(options));
		return "rating/ratingVote";
	}
	
	/**
	 * 获取用户已投数据
	 * @param model
	 * @param tmplId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "rating/vote/user/{tmplId}",method = RequestMethod.POST)
	public Map<String, Object> userRaringVote(Model model,@PathVariable("tmplId")Long tmplId){
		User user = this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername());
		return this.ratingService.getUserRatingVote(tmplId, user);
	}
	
	/**
	 * 用户提交投票
	 * @param model
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "rating/userVote/{tmplId}",method = RequestMethod.POST)
	public void userVote(Model model,@PathVariable("tmplId")Long tmplId, HttpServletRequest request){
		User user = this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername());
		//1. 获取投票数据列表
		String datasource = request.getParameter("votes");
		if (StringUtils.isNotEmpty(datasource)) {
			this.ratingService.submitVote(datasource,tmplId,user);
		}
	}
	
//	/**
//	 * 获取模板相应的得分项
//	 * @param model
//	 * @param tmplId
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "rating/vote/options/{tmplId}",method = RequestMethod.GET)
//	public List<SelectVo> getRatingOptions(Model model,@PathVariable("tmplId")Long tmplId){
//		return this.ratingService.getTmplOptions(tmplId);
//	}
}
