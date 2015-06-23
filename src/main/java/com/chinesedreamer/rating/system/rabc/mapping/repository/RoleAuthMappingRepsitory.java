package com.chinesedreamer.rating.system.rabc.mapping.repository;

import java.util.Collection;
import java.util.List;

import com.chinesedreamer.rating.system.rabc.mapping.model.RoleAuthMapping;


import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:50:14 
 * Copyright:   Copyright (c)2015
 */
public interface RoleAuthMappingRepsitory extends BaseRepository<RoleAuthMapping, Long>{
	public List<RoleAuthMapping> findByRoleIdIn(Collection<Long> roleIds);
}
