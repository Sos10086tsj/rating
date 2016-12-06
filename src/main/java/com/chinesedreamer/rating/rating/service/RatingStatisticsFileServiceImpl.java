package com.chinesedreamer.rating.rating.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.rating.logic.RatingStatisticsFileLogic;
import com.chinesedreamer.rating.rating.model.RatingStatisticsFile;

/**
 * Description:
 * Auth:Paris
 * Date:Dec 6, 2016
**/
@Service
public class RatingStatisticsFileServiceImpl implements RatingStatisticsFileService{
	
	@Resource
	private RatingStatisticsFileLogic logic;

	@Override
	public RatingStatisticsFile findByTmplIds(String tmplIds) {
		tmplIds = tmplIds.replaceAll(",", "-");
		return this.logic.findByTmplIds(tmplIds);
	}

}
