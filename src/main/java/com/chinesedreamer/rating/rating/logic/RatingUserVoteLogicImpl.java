package com.chinesedreamer.rating.rating.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.rating.model.RatingUserVote;
import com.chinesedreamer.rating.rating.repository.RatingUserVoteRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 25, 20151:02:02 PM
 * @version beta
 */
@Service
public class RatingUserVoteLogicImpl extends BaseLogicImpl<RatingUserVote, Long> implements RatingUserVoteLogic{
	@Resource
	private RatingUserVoteRepository repository;
	@Override
	public RatingUserVote findByRatingIdAndTmplIdAndUserId(Long ratingId,
			Long tmplId, Long userId) {
		return this.repository.findByRatingIdAndTmplIdAndUserId(ratingId, tmplId, userId);
	}
	@Override
	public List<RatingUserVote> findByTmplId(Long tmplId) {
		return this.repository.findByTmplId(tmplId);
	}

}
