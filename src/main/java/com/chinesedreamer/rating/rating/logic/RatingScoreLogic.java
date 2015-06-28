package com.chinesedreamer.rating.rating.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.rating.model.RatingScore;

/**
 * Description: 
 * @author Paris
 * @date Jun 25, 20151:11:33 PM
 * @version beta
 */
public interface RatingScoreLogic extends BaseLogic<RatingScore, Long>{
	public List<RatingScore> findAll();
}
