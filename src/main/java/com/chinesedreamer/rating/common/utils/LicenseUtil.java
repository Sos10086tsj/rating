package com.chinesedreamer.rating.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LicenseUtil {
	
	private static Logger logger = LoggerFactory.getLogger(LicenseUtil.class);
	
	/**
	 * 获得授权文件绝对路径
	 * @return
	 */
	public static String getLicensePath() {
		String applicationPath = LicenseUtil.class.getClassLoader().getResource("application.properties").getPath();
		logger.info("applicationPath:{}",applicationPath);
		int classIndex = applicationPath.lastIndexOf("rating");
		String path = applicationPath.substring(0, classIndex) + "rating.license";
		if (path.contains("%20")) {
			try {
				path = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("{}",e);
			}
		}
		return path;
	}
}
