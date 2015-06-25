package com.chinesedreamer.rating.template.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplate;
import com.chinesedreamer.rating.template.repository.RatingSuppTemplateRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:53:43 AM
 * @version beta
 */
@Service
public class RatingSuppTemplateLogicImpl extends BaseLogicImpl<RatingSuppTemplate, Long> implements RatingSuppTemplateLogic{
	@Resource
	private RatingSuppTemplateRepository repository;
	@Override
	public List<RatingSuppTemplate> findAll() {
		return this.repository.findAll();
	}

}
