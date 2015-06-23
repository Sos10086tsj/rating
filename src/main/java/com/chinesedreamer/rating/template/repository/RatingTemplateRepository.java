package com.chinesedreamer.rating.template.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.template.model.RatingTemplate;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:54:56 AM
 * @version beta
 */
public interface RatingTemplateRepository extends BaseRepository<RatingTemplate, Long>{
	public List<RatingTemplate> findByRatingId(Long ratingId);
}
