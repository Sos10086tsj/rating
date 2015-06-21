package com.chinesedreamer.rating.system.rabc.mapping.logic;

import java.util.Collection;
import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.system.rabc.mapping.model.ResAuOprMapping;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:32:59 
 * Copyright:   Copyright (c)2015
 */
public interface ResAuOprMappingLogic extends BaseLogic<ResAuOprMapping, Long>{
	public List<ResAuOprMapping> findByAutCodes(Collection<String> authCodes);
}
