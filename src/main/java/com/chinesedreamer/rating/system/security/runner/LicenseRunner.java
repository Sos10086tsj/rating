package com.chinesedreamer.rating.system.security.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinesedreamer.rating.common.io.PropertiesUtils;
import com.chinesedreamer.rating.common.utils.ConfigurationUtil;

public class LicenseRunner implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(LicenseRunner.class);

	@Override
	public void run() {
		logger.info(" begin license count");
		
		do {
			try {
				Thread.sleep(60 * 1000);
				PropertiesUtils propertiesUtils = new PropertiesUtils("probation.properties");
				String probationN = propertiesUtils.getProperty("probation.n");
				Integer pass = Integer.valueOf(probationN);
				pass += 60;//ms
				logger.info("license count : {}", pass);
				
				ConfigurationUtil.updatePropertiesValue("probation.properties", "probation.n", String.valueOf(pass));
				
			} catch (InterruptedException e) {
				logger.error("",e);
				Thread.currentThread().interrupt();
			}
		} while (!Thread.interrupted());

	}

}
