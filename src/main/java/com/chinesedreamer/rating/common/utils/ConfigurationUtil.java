package com.chinesedreamer.rating.common.utils;

import java.net.URL;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ConfigurationUtil.class);

	/**
	 * 修改properties文件的key-value对，key-value成对逐个传递
	 * @param propertiesPath
	 * @param args
	 */
	public static void updatePropertiesValue(String propertiesPath, String... args) {
		int paramLength = args.length;
		if (paramLength < 2) {
			return;
		}
		if (paramLength % 2 != 0) {
			return;
		}
		URL url = ConfigurationUtil.class.getClassLoader().getResource(propertiesPath);
		try {
			PropertiesConfiguration config = new PropertiesConfiguration(url.getPath());
			for (int i = 0; i < paramLength / 2; i++) {
				config.setProperty(args[i], args[i+1]);
			}
			config.save();
		} catch (Exception e) {
			logger.error("{}",e);
		}
	}
}
