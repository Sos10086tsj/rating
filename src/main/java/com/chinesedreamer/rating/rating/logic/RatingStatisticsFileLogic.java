package com.chinesedreamer.rating.rating.logic;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.rating.model.RatingStatisticsFile;

/**
 * Description:
 * Auth:Paris
 * Date:Dec 5, 2016
**/
public interface RatingStatisticsFileLogic extends BaseLogic<RatingStatisticsFile, Long>{
	public RatingStatisticsFile findByTmplIds(String tmplIds);
}
