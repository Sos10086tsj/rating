package com.chinesedreamer.rating.system.group.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.group.model.UserGroup;
import com.chinesedreamer.rating.system.group.repository.UserGroupRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:05:32 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserGroupLogicImpl extends BaseLogicImpl<UserGroup, Long> implements UserGroupLogic{
	@Resource
	private UserGroupRepository repository;
	@Override
	public List<UserGroup> findAll() {
		return this.repository.findAll();
	}

}
