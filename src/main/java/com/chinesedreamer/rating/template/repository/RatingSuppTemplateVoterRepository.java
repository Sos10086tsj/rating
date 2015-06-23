package com.chinesedreamer.rating.template.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.template.model.RatingSuppTemplateVoter;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:27:27 AM
 * @version beta
 */
public interface RatingSuppTemplateVoterRepository extends BaseRepository<RatingSuppTemplateVoter, Long>{
	public List<RatingSuppTemplateVoter> findBySuppTmplId(Long suppTmplId);
}
