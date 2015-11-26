package com.chinesedreamer.rating.template.service;

import java.util.List;

import com.chinesedreamer.rating.template.model.RatingTemplate;

public interface RatingTemplateService {
	public List<RatingTemplate> findByRatingId(Long ratingId);
}
