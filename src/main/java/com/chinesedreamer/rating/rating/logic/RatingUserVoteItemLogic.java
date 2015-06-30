package com.chinesedreamer.rating.rating.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.rating.model.RatingUserVoteItem;

/**
 * Description: 
 * @author Paris
 * @date Jun 25, 20151:09:08 PM
 * @version beta
 */
public interface RatingUserVoteItemLogic extends BaseLogic<RatingUserVoteItem, Long>{
	public List<RatingUserVoteItem> findByUserVoteId(Long userVoteId);
	public RatingUserVoteItem findByUserVoteIdAndOptionIdAndScorer(Long userVoteId, Long optionId, Long scorer);
}
