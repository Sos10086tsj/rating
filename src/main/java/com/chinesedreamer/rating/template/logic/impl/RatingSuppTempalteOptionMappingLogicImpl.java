package com.chinesedreamer.rating.template.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateOptionMappingLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplateOptionMapping;
import com.chinesedreamer.rating.template.repository.RatingSuppTemplateOptionMappingRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:19:30 AM
 * @version beta
 */
@Service
public class RatingSuppTempalteOptionMappingLogicImpl extends BaseLogicImpl<RatingSuppTemplateOptionMapping, Long> implements RatingSuppTemplateOptionMappingLogic{
	@Resource
	private RatingSuppTemplateOptionMappingRepository repository;
	@Override
	public List<RatingSuppTemplateOptionMapping> findBySuppTmplId(
			Long suppTmplId) {
		return this.repository.findBySuppTmplId(suppTmplId);
	}
	@Override
	public RatingSuppTemplateOptionMapping findBySuppTmplIdAndOptionId(
			Long suppTmplId, Long optionId) {
		return this.repository.findBySuppTmplIdAndOptionId(suppTmplId, optionId);
	}

}
