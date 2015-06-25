package com.chinesedreamer.rating.system.rabc.resource.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.rabc.resource.model.SysResource;
import com.chinesedreamer.rating.system.rabc.resource.repository.SysResourceRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:11:16 
 * Copyright:   Copyright (c)2015
 */
@Service
public class SysResourceLogicImpl extends BaseLogicImpl<SysResource, Long> implements SysResourceLogic{
	@Resource
	private SysResourceRepository repository;
	@Override
	public SysResource findByCode(String code) {
		return this.repository.findByCode(code);
	}

}
