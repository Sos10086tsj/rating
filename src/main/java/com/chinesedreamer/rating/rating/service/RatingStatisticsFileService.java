package com.chinesedreamer.rating.rating.service;

import com.chinesedreamer.rating.rating.model.RatingStatisticsFile;

/**
 * Description:
 * Auth:Paris
 * Date:Dec 6, 2016
**/
public interface RatingStatisticsFileService {
	public RatingStatisticsFile findByTmplIds(String tmplIds);
}
