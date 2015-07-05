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
import com.chinesedreamer.rating.system.group.service.UserGroupServcie;
import com.chinesedreamer.rating.system.group.vo.GroupVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月24日 下午8:20:13 
 * Copyright:   Copyright (c)2015
 */
@Controller
public class GroupController {
	@Resource
	private UserGroupServcie userGroupServcie;
	
	/**
	 * 组织列表
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "system/group/list")
	public Map<String, Object> getGroupList(Model model){
		Map<String, Object> rstMap = new HashMap<String, Object>();
		List<GroupVo> vos = this.userGroupServcie.getAllGroupVos();
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
	@RequestMapping(value = "system/group/create",method = RequestMethod.POST)
	public ResponseVo createGroup(HttpServletRequest request, GroupVo groupVo){
		ResponseVo vo = new ResponseVo();
		this.userGroupServcie.create(groupVo);
		return vo;
	}
}
