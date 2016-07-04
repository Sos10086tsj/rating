package com.chinesedreamer.rating.common.utils;

import com.chinesedreamer.rating.system.config.model.Config;

/**
 * Description:
 * Auth:Paris
 * Date:Jul 4, 2016
**/
public class StringUtil {
	public static String formatScore(Float score, Config config) {
		if (null == config) {
			return score.toString();
		}
		Integer format = Integer.parseInt(config.getPropertyValue());
		Float finalScore = (float)(Math.round(score*format))/format;
		return finalScore.toString();
	}
}
