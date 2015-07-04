package com.chinesedreamer.rating.system.rabc.role.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.rabc.role.model.SysRole;
import com.chinesedreamer.rating.system.rabc.role.repository.SysRoleRepsitory;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:42:04 
 * Copyright:   Copyright (c)2015
 */
@Service
public class SysRoleLogicImpl extends BaseLogicImpl<SysRole, Long> implements SysRoleLogic{
	@Resource
	private SysRoleRepsitory repository;
	@Override
	public SysRole findByName(String name) {
		return this.repository.findByName(name);
	}

}
