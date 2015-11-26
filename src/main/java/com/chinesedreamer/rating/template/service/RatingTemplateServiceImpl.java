package com.chinesedreamer.rating.template.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.template.logic.RatingTemplateLogic;
import com.chinesedreamer.rating.template.model.RatingTemplate;

@Service
public class RatingTemplateServiceImpl implements RatingTemplateService{
	
	@Resource
	private RatingTemplateLogic logic;

	@Override
	public List<RatingTemplate> findByRatingId(Long ratingId) {
		return this.logic.findByRatingId(ratingId);
	}

}
