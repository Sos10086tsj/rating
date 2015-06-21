package com.chinesedreamer.rating.system.rabc.mapping.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.system.rabc.mapping.model.UserRoleMapping;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:46:15 
 * Copyright:   Copyright (c)2015
 */
public interface UserRoleMappingLogic extends BaseLogic<UserRoleMapping, Long>{
	public List<UserRoleMapping> findByUserId(Long userId);
}
