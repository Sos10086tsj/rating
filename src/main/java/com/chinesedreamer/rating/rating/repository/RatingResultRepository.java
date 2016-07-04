package com.chinesedreamer.rating.rating.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.rating.model.RatingResult;

/**
 * Description: 
 * @author Paris
 * @date Jul 2, 20159:47:02 AM
 * @version beta
 */
public interface RatingResultRepository extends BaseRepository<RatingResult, Long>{
	public List<RatingResult> findByTmplId(Long tmplId);
	public List<RatingResult> findByTmplIdAndScorer(Long tmplId, Long scorer);
	public List<RatingResult> findByTmplIdIn(List<Long> tmplIds);
	
	public List<RatingResult> findByTmplIdAndVoterIdAndScorer(Long tmplId,Long voterId, Long scorer);
}
