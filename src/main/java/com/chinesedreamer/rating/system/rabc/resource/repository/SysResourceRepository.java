package com.chinesedreamer.rating.system.rabc.resource.repository;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.system.rabc.resource.model.SysResource;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:10:33 
 * Copyright:   Copyright (c)2015
 */
public interface SysResourceRepository extends BaseRepository<SysResource, Long>{
	public SysResource findByCode(String code);
}
