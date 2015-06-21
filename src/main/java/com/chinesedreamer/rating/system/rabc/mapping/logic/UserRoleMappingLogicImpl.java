package com.chinesedreamer.rating.system.rabc.mapping.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.rabc.mapping.model.UserRoleMapping;
import com.chinesedreamer.rating.system.rabc.mapping.repository.UserRoleMappingRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:46:36 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserRoleMappingLogicImpl extends BaseLogicImpl<UserRoleMapping, Long> implements UserRoleMappingLogic{
	@Resource
	private UserRoleMappingRepository repository;
	@Override
	public List<UserRoleMapping> findByUserId(Long userId) {
		return this.repository.findByUserId(userId);
	}

}
