package com.chinesedreamer.rating.template.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.template.logic.RatingTemplateOptionMappingLogic;
import com.chinesedreamer.rating.template.model.RatingTemplateOptionMapping;
import com.chinesedreamer.rating.template.repository.RatingTemplateOptionMappingRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:17:33 AM
 * @version beta
 */
@Service
public class RatingTemplateOptionMappingLogicIml extends BaseLogicImpl<RatingTemplateOptionMapping, Long> implements RatingTemplateOptionMappingLogic{
	@Resource
	private RatingTemplateOptionMappingRepository repository;
	@Override
	public List<RatingTemplateOptionMapping> findByTmplId(Long tmplId) {
		return this.repository.findByTmplId(tmplId);
	}

}
