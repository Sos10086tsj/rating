package com.chinesedreamer.rating.template.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.template.logic.RatingTemplateLogic;
import com.chinesedreamer.rating.template.mode.RatingTemplate;
import com.chinesedreamer.rating.template.repository.RatingTemplateRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:55:42 AM
 * @version beta
 */
@Service
public class RatingTemplateLogicImpl extends BaseLogicImpl<RatingTemplate, Long> implements RatingTemplateLogic{
	@Resource
	private RatingTemplateRepository repository;
	@Override
	public List<RatingTemplate> findByRatingId(Long ratingId) {
		return this.repository.findByRatingId(ratingId);
	}

}
