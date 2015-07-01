package com.chinesedreamer.rating.rating.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.rating.model.RatingUserVote;

/**
 * Description: 
 * @author Paris
 * @date Jun 25, 20151:01:23 PM
 * @version beta
 */
public interface RatingUserVoteRepository extends BaseRepository<RatingUserVote, Long>{
	public RatingUserVote findByRatingIdAndTmplIdAndUserId(Long ratingId, Long tmplId, Long userId);
	public List<RatingUserVote> findByTmplId(Long tmplId);
}
