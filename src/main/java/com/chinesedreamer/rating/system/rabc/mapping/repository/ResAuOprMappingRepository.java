package com.chinesedreamer.rating.system.rabc.mapping.repository;

import java.util.Collection;
import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.system.rabc.mapping.model.ResAuOprMapping;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:32:30 
 * Copyright:   Copyright (c)2015
 */
public interface ResAuOprMappingRepository extends BaseRepository<ResAuOprMapping, Long>{
	public List<ResAuOprMapping> findByAuthCodeIn(Collection<String> authCodes);
}
