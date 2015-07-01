package com.chinesedreamer.rating.rating.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.rating.model.RatingUserVote;

/**
 * Description: 
 * @author Paris
 * @date Jun 25, 20151:01:45 PM
 * @version beta
 */
public interface RatingUserVoteLogic extends BaseLogic<RatingUserVote, Long>{
	public RatingUserVote findByRatingIdAndTmplIdAndUserId(Long ratingId, Long tmplId, Long userId);
	public List<RatingUserVote> findByTmplId(Long tmplId);
}
