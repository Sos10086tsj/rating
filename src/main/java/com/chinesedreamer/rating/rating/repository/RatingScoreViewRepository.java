package com.chinesedreamer.rating.rating.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.rating.model.RatingScoreView;

/**
 * Description: 
 * @author Paris
 * @date Jul 2, 20159:47:02 AM
 * @version beta
 */
public interface RatingScoreViewRepository extends BaseRepository<RatingScoreView, Long>{
	public List<RatingScoreView> findByTmplId(Long tmplId);
}
