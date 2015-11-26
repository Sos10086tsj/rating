package com.chinesedreamer.rating.common.utils;

public class LicenseUtil {
	/**
	 * 获得授权文件绝对路径
	 * @return
	 */
	public static String getLicensePath() {
		String applicationPath = LicenseUtil.class.getClassLoader().getResource("application.properties").getPath();
		int classIndex = applicationPath.lastIndexOf("rating");
		return applicationPath.substring(0, classIndex) + "rating.license";
	}
}
