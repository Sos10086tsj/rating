package com.chinesedreamer.rating.template.repository;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.template.model.RatingSuppTmplOptionWeight;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201512:22:01 PM
 * @version beta
 */
public interface RatingSuppTmplOptionWeightRepository extends BaseRepository<RatingSuppTmplOptionWeight, Long>{
	public RatingSuppTmplOptionWeight findBySuppTmplIdAndSuppOptionId(Long suppTmplId, Long suppOptionId);
}
