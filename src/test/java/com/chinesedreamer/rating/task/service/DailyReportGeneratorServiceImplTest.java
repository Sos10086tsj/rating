package com.chinesedreamer.rating.task.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.chinesedreamer.rating.base.SpringTest;

/**
 * Description:
 * Auth:Paris
 * Date:Dec 5, 2016
**/
public class DailyReportGeneratorServiceImplTest extends SpringTest{

	@Resource
	private DailyReportGeneratorService dailyReportGeneratorService;
	
	@Test
	@Rollback(value=false)
	public void testUpdateStatistic() {
		this.dailyReportGeneratorService.updateStatistic();
	}

}
