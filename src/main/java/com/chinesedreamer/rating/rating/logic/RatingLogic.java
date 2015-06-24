package com.chinesedreamer.rating.rating.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.rating.model.Rating;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:39:04 AM
 * @version beta
 */
public interface RatingLogic extends BaseLogic<Rating, Long>{
	public List<Rating> findAll();
}
