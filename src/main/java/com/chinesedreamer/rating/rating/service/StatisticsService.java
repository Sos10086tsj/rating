package com.chinesedreamer.rating.rating.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.chinesedreamer.rating.rating.vo.rpt.RptVo;


/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月1日 下午8:30:00 
 * Copyright:   Copyright (c)2015
 */
public interface StatisticsService {
	public RptVo generateReport(Long tmplId);
	
	public RptVo generateReport(String tmplIds);
	
	/**
	 * 用户得分明细
	 * @param tmplId
	 * @param user
	 * @return
	 */
	public List<Map<String, String>> userDetails(Long tmplId,Long user);
	
	public List<Map<String, String>> userDetailsByRatingId(Long ratingId,Long user);
	
	/**
	 * 生成图表
	 * @param ratingId
	 * @return
	 */
	public int[] generateChart(Long ratingId);
	
	/**
	 * 导出exel文件
	 * @param ratingId
	 * @return
	 */
	public File getRptExcel(Long ratingId);
}
