package com.chinesedreamer.rating.template.repository;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.template.model.RatingTmplOptionWeight;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201512:22:01 PM
 * @version beta
 */
public interface RatingTmplOptionWeightRepository extends BaseRepository<RatingTmplOptionWeight, Long>{
	public RatingTmplOptionWeight findByTmplIdAndOptionId(Long tmplId,Long optionId);
}
