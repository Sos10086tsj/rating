package com.chinesedreamer.rating.system.rabc.mapping.logic;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.rabc.mapping.model.ResAuOprMapping;
import com.chinesedreamer.rating.system.rabc.mapping.repository.ResAuOprMappingRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:33:18 
 * Copyright:   Copyright (c)2015
 */
@Service
public class ResAuOprMappingLogicImpl extends BaseLogicImpl<ResAuOprMapping, Long> implements ResAuOprMappingLogic{
	@Resource
	private ResAuOprMappingRepository repository;
	@Override
	public List<ResAuOprMapping> findByAutCodes(Collection<String> authCodes) {
		return this.repository.findByAuthCodeIn(authCodes);
	}

}
