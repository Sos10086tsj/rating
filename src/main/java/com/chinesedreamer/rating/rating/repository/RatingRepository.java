package com.chinesedreamer.rating.rating.repository;

import java.util.Date;
import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.rating.model.Rating;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:38:41 AM
 * @version beta
 */
public interface RatingRepository extends BaseRepository<Rating, Long>{
	public List<Rating> findByCreateDateGreaterThan(Date createDate);
}
