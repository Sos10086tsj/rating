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
import com.chinesedreamer.rating.template.service.SuppOptionService;
import com.chinesedreamer.rating.template.vo.OptionVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月24日 下午8:20:13 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class OptionController {
	@Resource
	private SuppOptionService optionService;
	
	/**
	 * 组织列表
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/option/list")
	public Map<String, Object> getGroupList(Model model){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		List<OptionVo> vos = this.optionService.getAll();
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}
	
	/**
	 * 创建组
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/option/update",method = RequestMethod.POST)
	public ResponseVo createGroup(HttpServletRequest request, OptionVo optionVo){
		ResponseVo vo = new ResponseVo();
		this.optionService.update(optionVo);
		return vo;
	}
}
