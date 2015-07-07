package com.chinesedreamer.rating.template.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.template.model.RatingTemplate;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:55:19 AM
 * @version beta
 */
public interface RatingTemplateLogic extends BaseLogic<RatingTemplate, Long>{
	public RatingTemplate findByRatingIdAndCode(Long ratingId, String code);
	public List<RatingTemplate> findByRatingId(Long ratingId);
	/**
	 * 为投票事件创建相应的模板
	 * @param rating
	 * @param suppTemplateIds
	 */
	public void copySuppTemplateToRating(Rating rating, Long[] suppTemplateIds);
}
