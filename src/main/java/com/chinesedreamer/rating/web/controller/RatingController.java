package com.chinesedreamer.rating.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinesedreamer.rating.attachment.model.Attachment;
import com.chinesedreamer.rating.attachment.service.AttachmentService;
import com.chinesedreamer.rating.common.io.DefaultDownloadComponent;
import com.chinesedreamer.rating.common.io.DownloadComponent;
import com.chinesedreamer.rating.common.io.PropertiesUtils;
import com.chinesedreamer.rating.common.vo.OptionTitle;
import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.model.RatingStatisticsFile;
import com.chinesedreamer.rating.rating.service.RatingService;
import com.chinesedreamer.rating.rating.service.RatingStatisticsFileService;
import com.chinesedreamer.rating.rating.service.StatisticsService;
import com.chinesedreamer.rating.rating.vo.RatingCreateVo;
import com.chinesedreamer.rating.rating.vo.RatingUserVo;
import com.chinesedreamer.rating.rating.vo.RatingUserVoteResult;
import com.chinesedreamer.rating.rating.vo.RatingVo;
import com.chinesedreamer.rating.rating.vo.RatingWeightVo;
import com.chinesedreamer.rating.rating.vo.rpt.RptVo;
import com.chinesedreamer.rating.system.session.service.UserSessionService;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.system.user.service.UserService;
import com.chinesedreamer.rating.template.model.RatingTemplate;
import com.chinesedreamer.rating.template.service.RatingTemplateService;
import com.chinesedreamer.rating.template.service.RatingTemplateVoterService;
import com.chinesedreamer.rating.template.util.FileUtil;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015��6��24�� ����8:17:11 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class RatingController {
	
	private final String DEFAULT_USER_SCORE = "4.0";
	
	private Logger logger = LoggerFactory.getLogger(RatingController.class);
	@Resource
	private RatingService ratingService;
	@Resource
	private UserSessionService userSessionService;
	@Resource
	private UserService userService;
	@Resource
	private StatisticsService statisticsService;
	
	@Resource
	private RatingTemplateService ratingTemplateService;
	@Resource
	private RatingTemplateVoterService ratingTemplateVoterService;
	@Resource
	private AttachmentService attachmentService;
	@Resource
	private RatingStatisticsFileService ratingStatisticsFileService;
	
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
	 * 用户投票编辑页(excel)
	 * @param model
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "rating/voteExcel/{tmplId}",method = RequestMethod.GET)
	public String showRaringvoteExcel(Model model,@PathVariable("tmplId")Long tmplId){
		model.addAttribute("votePage", this.ratingService.getRatingVotePage(tmplId));
		model.addAttribute("tmplId", tmplId);
		
		return "rating/ratingVoteExcel";
	}
	
	/**
	 * 通过下载excel的方式投票
	 * @param model
	 * @param tmplId
	 * @return
	 */
	@RequestMapping(value = "rating/voteByExcel/{tmplId}",method = RequestMethod.GET)
	public String showRaringvoteByExcel(Model model,@PathVariable("tmplId")Long tmplId){
		model.addAttribute("tmplId", tmplId);
		RatingTemplate rt = this.ratingTemplateService.findOne(tmplId);
		Rating rating = this.ratingService.findOne(rt.getRatingId());
		model.addAttribute("fileName", rating.getName() + "-" + rt.getName() + "卷");
		return "rating/ratingVoteByExcel";
	}
	
	@ResponseBody
	@RequestMapping(value = "rating/voteByExcel/{tmplId}",method = RequestMethod.POST)
	public JSONObject getUserRating(@PathVariable("tmplId")Long tmplId){
		User user = this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername());
		List<OptionTitle> options = this.ratingService.getTmplOptions(tmplId);
		List<RatingUserVoteResult> results = this.ratingService.getUserRating(options, user, tmplId);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("options", options);
		jsonObject.put("results", results);
		
		return jsonObject;
	}
	
	/**
	 * 下载投票excel
	 * @param tmplId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "rating/downloadVoteExcel/{tmplId}",method = RequestMethod.GET)
	public void downloadVoteExcel(HttpServletRequest request, HttpServletResponse response,@PathVariable("tmplId")Long tmplId){
		
		User user = this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername());
		
		List<OptionTitle> options = this.ratingService.getTmplOptions(tmplId);
		List<SelectVo> users = this.userService.getScorers(
				user,
				this.ratingTemplateService.findOne(tmplId).getCode());
		Map<String, Object> scores = this.ratingService.getUserRatingVote(tmplId, user);

		List<List<String>> rows = new ArrayList<List<String>>();
		//1. 增加所有得分项
		List<String> titleRowList = new ArrayList<String>();
		titleRowList.add("");
		for (OptionTitle option : options) {
			titleRowList.add(option.getLabel());
		}
		rows.add(titleRowList);
		//2. 增加所有得分用户以及其得分项
		for (SelectVo userVo : users) {
			List<String> dataRowList = new ArrayList<String>();
			dataRowList.add(userVo.getLabel());
			//2.1 查找该用户是否已经有得分记录
			@SuppressWarnings("unchecked")
			List<String> userScores = this.getUserScoreInf(userVo.getValue(), (List<Map<String, Object>>)scores.get("rows"), options);
			dataRowList.addAll(userScores);
			rows.add(dataRowList);
		}
		
		File file = this.ratingService.generateVoteExcel(rows, tmplId);
		
		DownloadComponent downloadComponent = new DefaultDownloadComponent();
		try {
			downloadComponent.download(request, response, file.getPath(), file.getName());
		} catch (IOException e) {
			logger.error("{}",e);
		}
		FileUtils.deleteQuietly(file);
	}
	
	/**
	 * 上传投票excel
	 * @param model
	 * @param request
	 * @param pdf
	 */
	@RequestMapping(value = "rating/uploadVoteExcel/{tmplId}",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadVoteExcel(Model model,HttpServletRequest request,@RequestParam(value = "voteExcel", required = true)MultipartFile voteExcel, @PathVariable("tmplId")Long tmplId){
		User user = this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername());
		Attachment excel = this.attachmentService.saveFile(voteExcel, user.getId());
		List<OptionTitle> options = this.ratingService.getTmplOptions(tmplId);
		Map<String, Object> results = this.ratingService.saveVoteExcel(options, user, tmplId, excel);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("options", options);
		jsonObject.put("results", results);
		jsonObject.put("success", Boolean.TRUE);
		
		return jsonObject;
	}
	
	@ResponseBody
	@RequestMapping(value = "rating/voteExcel/{tmplId}", method = RequestMethod.POST)
	public List<Object> userRaringVoteExce(Model model, @PathVariable("tmplId")Long tmplId){

		List<OptionTitle> options = this.ratingService.getTmplOptions(tmplId);
		List<SelectVo> users = this.userService.lookupUser("");
		User user = this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername());
		Map<String, Object> scores = this.ratingService.getUserRatingVote(tmplId, user);

		List<Object> rows = new ArrayList<Object>();
		//1. 增加所有得分项
		List<String> titleRowList = new ArrayList<String>();
		titleRowList.add("");
		for (OptionTitle option : options) {
			titleRowList.add(option.getLabel());
		}
		rows.add(titleRowList.toArray());
		//2. 增加所有得分用户以及其得分项
		for (SelectVo userVo : users) {
			List<String> dataRowList = new ArrayList<String>();
			dataRowList.add(userVo.getLabel());
			//2.1 查找该用户是否已经有得分记录
			@SuppressWarnings("unchecked")
			List<String> userScores = this.getUserScoreInf(userVo.getValue(), (List<Map<String, Object>>)scores.get("rows"), options);
			dataRowList.addAll(userScores);
			rows.add(dataRowList.toArray());
		}

		return rows;
	}
	
	private List<String> getUserScoreInf(String userId, List<Map<String, Object>> scores, List<OptionTitle> options) {
		boolean empty = true;
		List<String> optionScores = new ArrayList<String>();
		for (Map<String, Object> score : scores) {
			if (score.get("scorerId").equals(Long.valueOf(userId))) {
				empty = false;
				for (OptionTitle option : options) {
					String s = score.get(option.getValue()).toString();
					if (StringUtils.isEmpty(s)) {
						optionScores.add(DEFAULT_USER_SCORE);
					}else {
						optionScores.add(s);
					}
				}
				break;
			}
		}
		if (empty) {
			for (int i = 0; i < options.size(); i++) {
				optionScores.add(DEFAULT_USER_SCORE);
			}
		}
		return optionScores;
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
	public String showStatisticsDetail(Model model,@PathVariable("tmplIds")String tmplIds, Integer realTime){
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
		RatingStatisticsFile statisticsFile = this.ratingStatisticsFileService.findByTmplIds(tmplIds);
		if (null != statisticsFile) {
			model.addAttribute("statisticsTime", statisticsFile.getStatisticsDate());
		}
		model.addAttribute("realTime", realTime);
		return "statistics/detail";
	}
	@ResponseBody
	@RequestMapping(value = "rating/statistics/{tmplIds}",method = {RequestMethod.GET,RequestMethod.POST})
	public Map<String, Object> getStatisticsDetail(Model model,@PathVariable("tmplIds")String tmplIds, Integer realTime){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		RptVo rptVo = null;
		if (null == realTime) {
			RatingStatisticsFile statisticsFile = this.ratingStatisticsFileService.findByTmplIds(tmplIds);
			if (null == statisticsFile) {
				rptVo = this.statisticsService.generateReport(tmplIds);
			}else {
				PropertiesUtils pu = new PropertiesUtils("config.properties");
				String fileRootFolder = pu.getProperty("file.upload.prefix");
				rptVo = JSONObject.toJavaObject(JSON.parseObject(FileUtil.readFile2Json(fileRootFolder + statisticsFile.getAttachment().getFilePath())), RptVo.class);
			}
		}else {
			rptVo = this.statisticsService.generateReport(tmplIds);
		}
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
		
		// 计算我的得分和我的组内最高、最低低分
		List<RatingTemplate> ratingTemplates = this.ratingTemplateService.findByRatingId(ratingId);
		Set<Long> templateIds = new HashSet<Long>();
//		UserGroup userGroup = this.userService.findOne(userId).getUserGroup();
		//所有得分者内的最高分
		for (RatingTemplate ratingTemplate : ratingTemplates) {
			templateIds.add(ratingTemplate.getId());
		}
//		for (RatingTemplate ratingTemplate : ratingTemplates) {
//			if (null != this.ratingTemplateVoterService.findByTmplIdAndGroupId(ratingTemplate.getId(),userGroup.getId())) { 
//				templateIds.add(ratingTemplate.getId());
//			}
//		}
		StringBuffer tmplIds = new StringBuffer();
		for (Long id : templateIds) {
			tmplIds.append(id).append(",");
		}

		float min = 999;
		float max = 0;
		RptVo rptVo = this.statisticsService.generateReportForUser(tmplIds.toString().substring(0, tmplIds.length() - 1), 
				this.userService.getUser(this.userSessionService.getCurrentUserSession().getUsername()));
		for (Map<String, String> score : rptVo.getScores()) {
			Long tmpUserId = Long.valueOf(score.get("user_id"));
			if (userId.equals(tmpUserId)) {
				model.addAttribute("myScore", score.get("total"));
			}
			Float tmpTotal = Float.valueOf(score.get("total"));
			if (tmpTotal < min) {
				min = tmpTotal;
			}
			if (tmpTotal > max) {
				max = tmpTotal;
			}
		}
		model.addAttribute("min", min == 999 ? 0 : min);
		model.addAttribute("max", max);
		
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
		List<RatingWeightVo> vos = this.ratingService.getRatingTmplWeightVos(templateId);
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}

	@ResponseBody
	@RequestMapping(value = "rating/weight/update",method = RequestMethod.POST)
	public ResponseVo updateWeigth(HttpServletRequest request){
		ResponseVo vo = new ResponseVo();
		String tmplIdParam = request.getParameter("tmplId");
		String optionsParam = request.getParameter("options");
		this.ratingService.updateTmplWeight(Long.parseLong(tmplIdParam), optionsParam.trim());
		return vo;
	}
	
	/**
	 * 导出excel
	 * @param request
	 * @param templateId
	 */
	@ResponseBody
	@RequestMapping(value = "rating/statistics/export/{ratingId}",method = RequestMethod.GET)
	public void exportExcel(HttpServletRequest request,HttpServletResponse response, @PathVariable("ratingId")Long ratingId){
		File file = this.statisticsService.getRptExcel(ratingId);
		
		DownloadComponent downloadComponent = new DefaultDownloadComponent();
		try {
			downloadComponent.download(request, response, file.getPath(), file.getName());
		} catch (IOException e) {
			logger.error("{}",e);
		}
		FileUtils.deleteQuietly(file);
	}
}
