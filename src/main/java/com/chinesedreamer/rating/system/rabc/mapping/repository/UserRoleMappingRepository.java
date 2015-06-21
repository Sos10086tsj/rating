package com.chinesedreamer.rating.system.rabc.mapping.repository;

import java.util.List;

import com.chinesedreamer.rating.system.rabc.mapping.model.UserRoleMapping;


import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:45:53 
 * Copyright:   Copyright (c)2015
 */
public interface UserRoleMappingRepository extends BaseRepository<UserRoleMapping, Long>{
	public List<UserRoleMapping> findByUserId(Long userId);
}
