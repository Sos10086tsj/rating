package com.chinesedreamer.rating.system.rabc.mapping.logic;

import java.util.Collection;
import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.system.rabc.mapping.model.RoleAuthMapping;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:50:33 
 * Copyright:   Copyright (c)2015
 */
public interface RoleAuthMappingLogic extends BaseLogic<RoleAuthMapping, Long>{
	public List<RoleAuthMapping> findByRoleIds(Collection<Long> roleIds);
}
