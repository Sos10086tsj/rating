package com.chinesedreamer.rating.rating.repository;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.rating.model.RatingStatisticsFile;

/**
 * Description:
 * Auth:Paris
 * Date:Dec 5, 2016
**/
public interface RatingStatisticsFileRepository extends BaseRepository<RatingStatisticsFile, Long>{
	public RatingStatisticsFile findByTmplIds(String tmplIds);
}
