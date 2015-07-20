package com.chinesedreamer.rating.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.template.model.RatingSuppTemplate;
import com.chinesedreamer.rating.template.service.SuppTemplateService;
import com.chinesedreamer.rating.template.vo.OptionVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月24日 下午8:20:13 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class TemplateController {
	@Resource
	private SuppTemplateService suppTemplateService;
	
	/**
	 * 组织列表
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/template/list")
	public Map<String, Object> getGroupList(Model model){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		List<RatingSuppTemplate> vos = this.suppTemplateService.getAll();
		rstMap.put("total", vos.size());
		rstMap.put("rows", vos);
		return rstMap;
	}
	
	/**
	 * 查看模板详细信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "system/template/showedit/{id}", method = RequestMethod.GET)
	public String showEditSuppTmpl(Model model, @PathVariable("id")Long id){
		model.addAttribute("tmplId", id);
		return "systemMgmt/template/templateEdit";
	}
	
	@ResponseBody
	@RequestMapping(value = "system/template/edit/{id}", method = RequestMethod.POST)
	public Map<String, Object> editSuppTmpl(Model model, @PathVariable("id")Long id){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		List<OptionVo> vos = this.suppTemplateService.getTmplOptions(id);
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
	@RequestMapping(value = "system/template/suppupdate/{suppTmplId}",method = RequestMethod.POST)
	public ResponseVo createGroup(HttpServletRequest request, @PathVariable("suppTmplId")Long suppTmplId){
		ResponseVo vo = new ResponseVo();
		String optionsParam = request.getParameter("options");
		this.suppTemplateService.updateSuppTmpl(suppTmplId, optionsParam.trim());
		return vo;
	}
}
