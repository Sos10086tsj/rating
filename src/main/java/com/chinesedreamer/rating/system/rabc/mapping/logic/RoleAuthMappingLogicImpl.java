package com.chinesedreamer.rating.system.rabc.mapping.logic;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.rabc.mapping.model.RoleAuthMapping;
import com.chinesedreamer.rating.system.rabc.mapping.repository.RoleAuthMappingRepsitory;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:50:49 
 * Copyright:   Copyright (c)2015
 */
@Service
public class RoleAuthMappingLogicImpl extends BaseLogicImpl<RoleAuthMapping, Long> implements RoleAuthMappingLogic{
	@Resource
	private RoleAuthMappingRepsitory repository;
	@Override
	public List<RoleAuthMapping> findByRoleIds(Collection<Long> roleIds) {
		return this.repository.findByRoleIdIn(roleIds);
	}

}
