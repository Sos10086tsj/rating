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
import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.service.RatingService;
import com.chinesedreamer.rating.rating.service.StatisticsService;
import com.chinesedreamer.rating.rating.vo.RatingCreateVo;
import com.chinesedreamer.rating.rating.vo.RatingUserVo;
import com.chinesedreamer.rating.rating.vo.RatingVo;
import com.chinesedreamer.rating.rating.vo.rpt.RptVo;
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
	@Resource
	private StatisticsService statisticsService;
	
	/**
	 * 投票管理列表页
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/rating/list")
	public Map<String, Object> getRatingList(Model model){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		List<RatingVo> vos = this.ratingService.getAllRatings();
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}
	
	/**
	 * 进入新建投票页
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = "system/rating/create",method = RequestMethod.GET)
//	public String showCreateRating(Model model){
//		model.addAttribute("m", new RatingCreateVo());
//		model.addAttribute("suppTemplates", this.ratingService.getAllTemplates());
//		return "systemMgmt/ratingMgmt/create";
//	}
	
	/**
	 * 新建投票提交
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/rating/create",method = RequestMethod.POST)
	public ResponseVo createRating(RatingCreateVo vo){
		ResponseVo rst = new ResponseVo();
		this.ratingService.saveRating(vo);
		return rst;
	}
	
	/********* 用户投票部分 **************/
	/**
	 * 用户投票主页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "rating",method = RequestMethod.GET)
	public String rating(Model model){
		model.addAttribute("currentUser", this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername()).getId());
		
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
	
	
	/**
	 * 显示统计
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "rating/statistics",method = RequestMethod.GET)
	public String showStatistics(Model model){
		return "statistics/statistics";
	}
	
	/**
	 * 所有投票列表
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "rating/statistics/list",method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> showStatisticsRatings(Model model){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		User user = this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername());
		List<RatingUserVo> vos = this.ratingService.getStatisticsRatings(user);
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}
	
	/**
	 * 跳转投票详情展示页
	 * @param model
	 * @param tmplId
	 * @return
	 */
//	@RequestMapping(value = "rating/statistics/detail/{tmplId}",method = RequestMethod.GET)
//	public String showStatisticsDetail(Model model,@PathVariable("tmplId")Long tmplId){
//		model.addAttribute("tmplId", tmplId);
//		List<OptionTitle> options = this.ratingService.getTmplOptions(tmplId);
//		Integer totalWidth = 120;
//		for (OptionTitle optionTitle : options) {
//			totalWidth += optionTitle.getWidth();
//		}
//		model.addAttribute("options", options);
//		model.addAttribute("gridWidth", totalWidth);
//		return "statistics/detail";
//	}
	
	/**
	 * 获取投票统计结果
	 * @param model
	 * @param tmplId
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "rating/statistics/{tmplId}",method = {RequestMethod.GET,RequestMethod.POST})
//	public Map<String, Object> getStatisticsDetail(Model model,@PathVariable("tmplId")Long tmplId){
//		Map<String, Object> rstMap = new HashMap<String, Object>();
//		RptVo rptVo = this.statisticsService.generateReport(tmplId);
//		model.addAttribute("rptVo", rptVo);
//		rstMap.put("total", rptVo.getScores().size());
//		rstMap.put("rows", rptVo.getScores());
//		return rstMap;
//	}
	@RequestMapping(value = "rating/statistics/detail/{tmplIds}",method = RequestMethod.GET)
	public String showStatisticsDetail(Model model,@PathVariable("tmplIds")String tmplIds){
		model.addAttribute("tmplIds", tmplIds);
		String[] ids = tmplIds.split(",");
		List<OptionTitle> options_1 = this.ratingService.getTmplOptions(Long.parseLong(ids[0]));//A卷字段比B卷少
		List<OptionTitle> options_2 = this.ratingService.getTmplOptions(Long.parseLong(ids[1]));
		List<OptionTitle> options = options_1.size() > options_2.size() ? options_1 : options_2;
		Integer totalWidth = 120;
		for (OptionTitle optionTitle : options) {
			totalWidth += optionTitle.getWidth();
		}
		model.addAttribute("options", options);
		model.addAttribute("gridWidth", totalWidth);
		model.addAttribute("ratingId", this.ratingService.getByTmplId(Long.parseLong(ids[0])).getId());
		return "statistics/detail";
	}
	@ResponseBody
	@RequestMapping(value = "rating/statistics/{tmplIds}",method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> getStatisticsDetail(Model model,@PathVariable("tmplIds")String tmplIds){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		RptVo rptVo = this.statisticsService.generateReport(tmplIds);
		model.addAttribute("rptVo", rptVo);
		rstMap.put("total", rptVo.getScores().size());
		rstMap.put("rows", rptVo.getScores());
		return rstMap;
	}
	
//	/**
//	 * 得分明细
//	 * @param model
//	 * @param tmplId
//	 * @param userId
//	 * @return
//	 */
//	@RequestMapping(value = "rating/statistics/{tmplId}/{userId}",method = RequestMethod.GET)
//	public String showUserStatisticsDetail(Model model,@PathVariable("tmplId")Long tmplId,
//			@PathVariable("userId")Long userId){
//		model.addAttribute("tmplId", tmplId);
//		model.addAttribute("userId", userId);
//		List<OptionTitle> options = this.ratingService.getTmplOptions(tmplId);
//		Integer totalWidth = 120;
//		for (OptionTitle optionTitle : options) {
//			totalWidth += optionTitle.getWidth();
//		}
//		model.addAttribute("options", options);
//		model.addAttribute("gridWidth", totalWidth);
//		return "statistics/userDetail";
//	}
//	
//	
//	@ResponseBody
//	@RequestMapping(value = "rating/statistics/detail/{tmplId}/{userId}",method = {RequestMethod.GET,RequestMethod.POST})
//	public Map<String, Object> getUserStatisticsDetail(Model model,@PathVariable("tmplId")Long tmplId,
//			@PathVariable("userId")Long userId){
//		Map<String, Object> rstMap = new HashMap<String, Object>();
//		List<Map<String, String>> vos = this.statisticsService.userDetails(tmplId, userId);
//		rstMap.put("total", vos.size());
//		rstMap.put("rows", vos);
//		return rstMap;
//	}
	
	/**
	 * 得分明细
	 * @param model
	 * @param tmplId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "rating/statistics/{ratingId}/{userId}",method = RequestMethod.GET)
	public String showUserStatisticsDetail(Model model,@PathVariable("ratingId")Long ratingId,
			@PathVariable("userId")Long userId){
		model.addAttribute("userId", userId);
		List<OptionTitle> options = this.ratingService.getUserRartingOption(userId, ratingId);
		Integer totalWidth = 120;
		for (OptionTitle optionTitle : options) {
			totalWidth += optionTitle.getWidth();
		}
		model.addAttribute("options", options);
		model.addAttribute("gridWidth", totalWidth);
		return "statistics/userDetail";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "rating/statistics/detail/{ratingId}/{userId}",method = RequestMethod.POST)
	public Map<String, Object> getUserStatisticsDetail(Model model,@PathVariable("ratingId")Long ratingId,
			@PathVariable("userId")Long userId){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		List<Map<String, String>> vos = this.statisticsService.userDetailsByRatingId(ratingId, userId);
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}
	
	
	/**
	 * 统计图表
	 * @param model
	 * @param ratingId
	 * @return
	 */
	@RequestMapping(value = "rating/statistics/chart/{ratingId}",method = RequestMethod.GET)
	public String chart(Model model,@PathVariable("ratingId")Long ratingId){
		Rating rating = this.ratingService.findOne(ratingId);
		model.addAttribute("title", rating.getName());
		//double[] data = new double[]{34.4, 21.8, 20.1, 20, 19.6, 19.5, 19.1, 18.4, 18,17.3};
		model.addAttribute("data", JSON.toJSONString(this.statisticsService.generateChart(ratingId)));
		return "statistics/chart";
	}
	
	/**
	 * 查看模板权重信息
	 * @param model
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value = "rating/template/weight/{templateId}",method = RequestMethod.GET)
	public String showTmplWeight(Model model,@PathVariable("templateId")Long templateId){
		model.addAttribute("templateId", templateId);
		return "systemMgmt/ratingMgmt/ratingTmplWeight";
	}
	/**
	 * 查看模板权重
	 * @param model
	 * @param templateId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "rating/template/weight/detail/{templateId}",method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> tmplWeight(Model model,@PathVariable("templateId")Long templateId){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		//List<Map<String, String>> vos = this.statisticsService.userDetailsByRatingId(ratingId, userId);
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}
}
