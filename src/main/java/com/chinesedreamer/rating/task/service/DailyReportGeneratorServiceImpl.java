package com.chinesedreamer.rating.task.service;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.chinesedreamer.rating.attachment.logic.AttachmentLogic;
import com.chinesedreamer.rating.attachment.model.Attachment;
import com.chinesedreamer.rating.common.io.PropertiesUtils;
import com.chinesedreamer.rating.rating.logic.RatingStatisticsFileLogic;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.model.RatingStatisticsFile;
import com.chinesedreamer.rating.rating.service.RatingService;
import com.chinesedreamer.rating.rating.service.StatisticsService;
import com.chinesedreamer.rating.rating.vo.rpt.RptVo;
import com.chinesedreamer.rating.template.model.RatingTemplate;
import com.chinesedreamer.rating.template.service.RatingTemplateService;

/**
 * Description:
 * Auth:Paris
 * Date:Dec 5, 2016
**/
@Service
public class DailyReportGeneratorServiceImpl implements DailyReportGeneratorService{
	private Logger logger = LoggerFactory.getLogger(DailyReportGeneratorServiceImpl.class);
	
	@Resource
	private RatingService ratingService;
	@Resource
	private RatingTemplateService ratingTemplateService;
	@Resource
	private StatisticsService statisticsService;
	@Resource
	private AttachmentLogic attachmentLogic;
	@Resource
	private RatingStatisticsFileLogic ratingStatisticsFileLogic;

	@Override
	@Scheduled(cron="0 59 * * * ?")
	public void updateStatistic() {
		this.logger.info("Start daily report statistics...");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -3);
		List<Rating> currentRatings = this.ratingService.findByCreateDateGt(calendar.getTime());
		for (Rating rating : currentRatings) {
			List<RatingTemplate> templates = this.ratingTemplateService.findByRatingId(rating.getId());
			StringBuilder abBuilder = new StringBuilder();
			StringBuilder cdBuilder = new StringBuilder();
			for (RatingTemplate template : templates) {
				if (template.getCode().equalsIgnoreCase("A") || template.getCode().equalsIgnoreCase("B")) {
					abBuilder.append(template.getId()).append(",");
				}else if (template.getCode().equalsIgnoreCase("C") || template.getCode().equalsIgnoreCase("D")) {
					cdBuilder.append(template.getId()).append(",");
				}
			}
			this.logger.info(" Generate report for rating:{}, template:{}.", rating.getName(), abBuilder.toString());
			this.saveReport(abBuilder.toString());
			this.logger.info(" Generate report for rating:{}, template:{}.", rating.getName(), cdBuilder.toString());
			this.saveReport(cdBuilder.toString());
		}
	}

	private void saveReport(String tmplIds) {
		RptVo rptVo = this.statisticsService.generateReport(tmplIds);
		
		PropertiesUtils pu = new PropertiesUtils("config.properties");
		String fileRootFolder = pu.getProperty("file.upload.prefix");
		Calendar calendar = Calendar.getInstance();
		String fileFolder = calendar.get(Calendar.YEAR) + File.separator 
				+ (calendar.get(Calendar.MONTH) + 1) + File.separator
				+ calendar.get(Calendar.DAY_OF_MONTH) + File.separator;
		String fileName = UUID.randomUUID().toString() + ".json";
		File file = new File(fileRootFolder + fileFolder);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			FileUtils.writeStringToFile(new File(fileRootFolder + fileFolder + fileName), JSON.toJSONString(rptVo), "utf-8");
			Attachment attachment = new Attachment();
			attachment.setFileName(fileName);
			attachment.setFilePath(fileFolder + fileName);
			attachment.setUploadDate(new Date());
			this.attachmentLogic.save(attachment);
			
			//TODO查找
			String key = tmplIds.replaceAll(",", "-");
			RatingStatisticsFile statisticsFile = this.ratingStatisticsFileLogic.findByTmplIds(key);
			if (null == statisticsFile) {
				statisticsFile = new RatingStatisticsFile();
				statisticsFile.setTmplIds(key);
			}	
			statisticsFile.setAttachId(attachment.getId());
			statisticsFile.setStatisticsDate(new Date());
			this.ratingStatisticsFileLogic.save(statisticsFile);
		} catch (IOException e) {
			this.logger.error("{}",e);
		}
	}
}
