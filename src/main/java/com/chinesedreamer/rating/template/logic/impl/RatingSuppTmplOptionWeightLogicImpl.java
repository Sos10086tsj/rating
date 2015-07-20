package com.chinesedreamer.rating.template.logic.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.template.logic.RatingSuppTmplOptionWeightLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTmplOptionWeight;
import com.chinesedreamer.rating.template.repository.RatingSuppTmplOptionWeightRepository;

/**
 * Description: 
 * @author Paris
 * @date Jul 20, 20158:55:31 AM
 * @version beta
 */
@Service
public class RatingSuppTmplOptionWeightLogicImpl extends BaseLogicImpl<RatingSuppTmplOptionWeight, Long> implements RatingSuppTmplOptionWeightLogic{
	@Resource
	private RatingSuppTmplOptionWeightRepository repository;
	@Override
	public RatingSuppTmplOptionWeight findBySuppTmplIdAndSuppOptionId(
			Long suppTmplId, Long suppOptionId) {
		return this.repository.findBySuppTmplIdAndSuppOptionId(suppTmplId, suppOptionId);
	}

}
