package com.chinesedreamer.rating.template.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Description:
 * Auth:Paris
 * Date:Mar 23, 2016
**/
public class FileUtil {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
	public static byte[] readInputStream2ByteArray(InputStream is) {
		byte[] result = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 4];
		int n = 0;
		try {
			while ((n = is.read(buffer)) != -1) {
				out.write(buffer, 0, n);
			}
		} catch (IOException e) {
			logger.error("{}",e);
		}
		result = out.toByteArray();
		try {
			out.close();
		} catch (IOException e) {
			logger.error("{}",e);
		}
		return result;
	}
	
	public static String getResouceRootPath(String fileName) {
		return FileUtil.class.getClassLoader().getResource(fileName).getPath();
	}
	
	/**
	 * 读取文件字符串
	 * @param path
	 * @return
	 */
	public static String readFile2Json(String path) {
		logger.info("Begin parsing file:{}", path);
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			FileInputStream fis = new FileInputStream(path);
			InputStreamReader isReader = new InputStreamReader(fis, "UTF-8");
			reader = new BufferedReader(isReader);
			String tmpStr = null;
			while (null != (tmpStr = reader.readLine())) {
				buffer.append(tmpStr);
			}
			reader.close();
		} catch (Exception e) {
			logger.error("{}",e);
		}finally{
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("{}",e);
				}
			}
		}
		logger.info("End parsing file:{}", path);
		return buffer.toString();
	}
	
	/**
	 * 解析json文件
	 * @param path
	 * @return
	 */
	public static JSONObject getJsonFromFile(String path) {
		String content = readFile2Json(path);
		return JSONObject.parseObject(content);
	}
	/**
	 * 解析json文件
	 * @param path
	 * @return
	 */
	public static JSONArray getJsonArrayFromFile(String path) {
		String content = readFile2Json(path);
		return JSONArray.parseArray(content);
	}
}
