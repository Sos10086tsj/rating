package com.chinesedreamer.rating.system.rabc.role.repository;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.system.rabc.role.model.SysRole;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:41:23 
 * Copyright:   Copyright (c)2015
 */
public interface SysRoleRepsitory extends BaseRepository<SysRole, Long>{
	public SysRole findByName(String name);
}
