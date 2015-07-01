package com.chinesedreamer.rating.rating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.rating.model.RatingUserVoteItem;

/**
 * Description: 
 * @author Paris
 * @date Jun 25, 20151:08:53 PM
 * @version beta
 */
public interface RatingUserVoteItemRepository extends BaseRepository<RatingUserVoteItem, Long>{
	public List<RatingUserVoteItem> findByUserVoteId(Long userVoteId);
	public RatingUserVoteItem findByUserVoteIdAndOptionIdAndScorer(Long userVoteId,Long optionId, Long scorer);
	
	@Query("select distinct rvi.scorer from RatingUserVoteItem rvi where rvi.userVoteId = :voteId ")
	public List<Long> getScores(@Param("voteId") Long voteId);
}
