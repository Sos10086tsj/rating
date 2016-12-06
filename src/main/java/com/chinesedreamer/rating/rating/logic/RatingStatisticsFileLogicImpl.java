package com.chinesedreamer.rating.rating.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.rating.model.RatingStatisticsFile;
import com.chinesedreamer.rating.rating.repository.RatingStatisticsFileRepository;

/**
 * Description:
 * Auth:Paris
 * Date:Dec 5, 2016
**/
@Service
public class RatingStatisticsFileLogicImpl extends BaseLogicImpl<RatingStatisticsFile, Long> implements RatingStatisticsFileLogic{
	@Resource
	private RatingStatisticsFileRepository repository;

	@Override
	public RatingStatisticsFile findByTmplIds(String tmplIds) {
		return this.repository.findByTmplIds(tmplIds);
	}

}
