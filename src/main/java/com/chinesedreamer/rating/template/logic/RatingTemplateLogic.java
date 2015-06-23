package com.chinesedreamer.rating.template.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.template.mode.RatingTemplate;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:55:19 AM
 * @version beta
 */
public interface RatingTemplateLogic extends BaseLogic<RatingTemplate, Long>{
	public List<RatingTemplate> findByRatingId(Long ratingId);
}
