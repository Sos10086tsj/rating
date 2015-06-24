package com.chinesedreamer.rating.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.service.RatingService;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月24日 下午8:17:11 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class RatingController {
	@Resource
	private RatingService ratingService;
	
	/**
	 * 已经存在的投票
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
	 * 创建一次
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/rating/create",method = RequestMethod.POST)
	public ResponseVo createRating(HttpServletRequest request){
		ResponseVo vo = new ResponseVo();
		String name = request.getParameter("name").trim();
		return vo;
	}
}
