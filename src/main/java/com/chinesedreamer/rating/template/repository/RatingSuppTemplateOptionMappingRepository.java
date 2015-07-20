package com.chinesedreamer.rating.template.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.template.model.RatingSuppTemplateOptionMapping;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:18:55 AM
 * @version beta
 */
public interface RatingSuppTemplateOptionMappingRepository extends BaseRepository<RatingSuppTemplateOptionMapping, Long>{
	public List<RatingSuppTemplateOptionMapping> findBySuppTmplId(Long suppTmplId);
	public RatingSuppTemplateOptionMapping findBySuppTmplIdAndOptionId(Long suppTmplId, Long optionId);
}
