package com.chinesedreamer.rating.system.rabc.resource.logic;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.system.rabc.resource.model.SysResource;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:11:10 
 * Copyright:   Copyright (c)2015
 */
public interface SysResourceLogic extends BaseLogic<SysResource, Long>{
	public SysResource findByCode(String code);
}
