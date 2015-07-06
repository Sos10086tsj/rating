package com.chinesedreamer.rating.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月4日 下午3:22:02 
 * Copyright:   Copyright (c)2015
 */
public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static Date format(String dateStr){
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			logger.error("{}",e);
			return null;
		}
	}
	
	public static Date format(String dateStr, String format){
		SimpleDateFormat fm = new SimpleDateFormat(format);
		try {
			return fm.parse(dateStr);
		} catch (ParseException e) {
			logger.error("{}",e);
			return null;
		}
	}
	
	public static String format(Date date){
		SimpleDateFormat fm = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return fm.format(date);
	}
	
	public static String format(Date date, String format){
		SimpleDateFormat fm = new SimpleDateFormat(format);
		return fm.format(date);
	}
}
