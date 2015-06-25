package com.chinesedreamer.rating.template.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.template.model.RatingTemplateOptionMapping;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:16:52 AM
 * @version beta
 */
public interface RatingTemplateOptionMappingRepository extends BaseRepository<RatingTemplateOptionMapping, Long>{
	public List<RatingTemplateOptionMapping> findByTmplId(Long tmplId);
}
