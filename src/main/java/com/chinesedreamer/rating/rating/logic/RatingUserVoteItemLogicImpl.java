package com.chinesedreamer.rating.rating.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.rating.model.RatingUserVoteItem;
import com.chinesedreamer.rating.rating.repository.RatingUserVoteItemRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 25, 20151:09:31 PM
 * @version beta
 */
@Service
public class RatingUserVoteItemLogicImpl extends BaseLogicImpl<RatingUserVoteItem, Long> implements RatingUserVoteItemLogic{
	@Resource
	private RatingUserVoteItemRepository repository;
	@Override
	public List<RatingUserVoteItem> findByUserVoteId(Long userVoteId) {
		return this.repository.findByUserVoteId(userVoteId);
	}
	@Override
	public RatingUserVoteItem findByUserVoteIdAndOptionIdAndScorer(Long userVoteId,Long optionId, Long scorer) {
		return this.repository.findByUserVoteIdAndOptionIdAndScorer(userVoteId, optionId,scorer);
	}

}
