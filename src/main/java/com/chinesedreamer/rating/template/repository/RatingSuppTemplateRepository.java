package com.chinesedreamer.rating.template.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.template.model.RatingSuppTemplate;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:51:00 AM
 * @version beta
 */
public interface RatingSuppTemplateRepository extends BaseRepository<RatingSuppTemplate, Long>{
	public List<RatingSuppTemplate> findByCode(String code);
}
