/**
 * 
 */
package com.chinesedreamer.rating.common.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dev-laiting
 * 
 */
public class DefaultDownloadComponent implements DownloadComponent {

	private final Logger logger = LoggerFactory
			.getLogger(DefaultDownloadComponent.class);

	private String attachmentDir;

	public DefaultDownloadComponent() {
		PropertiesUtils propertiesUtils = new PropertiesUtils("config.properties");
		this.attachmentDir = propertiesUtils.getProperty(ConfigPropertiesConstant.fILE_OUTPUT_PATH);
	}

	public DefaultDownloadComponent(String attachmentDir) {
		this.attachmentDir = attachmentDir;
	}

	@Override
	public void download(HttpServletRequest request,
			HttpServletResponse response, String filePath) throws IOException {
		download(request, response, filePath, "");
	}

	@Override
	public void download(HttpServletRequest request,
			HttpServletResponse response, String filePath, String displayName)
			throws IOException {
		// download目标file
//		String absFilePath = attachmentDir + File.separator + filePath;
		File file = new File(filePath);

		// file显示文件名
		if (StringUtils.isEmpty(displayName)) {
			displayName = file.getName();
		}

		// 判断文件是否可以下载
		if (!file.exists() || !file.canRead()) {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("您下载的文件不存在！");
			return;
		}

		// 判断浏览器类型
		String userAgent = request.getHeader("User-Agent");
		boolean isFirefox = (userAgent != null)
				&& (userAgent.toLowerCase().indexOf("firefox") != -1);
		boolean isChrome = (userAgent != null)
				&& (userAgent.toLowerCase().indexOf("chrome") != -1);

		response.reset();
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "must-revalidate, no-transform");
		response.setDateHeader("Expires", 0L);
		response.setContentType("application/x-download");
		response.setContentLength((int) file.length());

		String displayFilename = displayName.substring(displayName
				.lastIndexOf("_") + 1);
		displayFilename = displayFilename.replace(" ", "_");
		if (isFirefox) {
			//firefox浏览器
			displayFilename = new String(displayFilename.getBytes("UTF-8"),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ displayFilename);
		} else if (isChrome) {
			//chrom浏览器
			displayFilename = new String(displayFilename.getBytes("UTF-8"),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ displayFilename);
		} else {
			//默认
			displayFilename = URLEncoder.encode(displayFilename, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ displayFilename + "\"");
		}

		BufferedInputStream is = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			is = new BufferedInputStream(new FileInputStream(file));
			IOUtils.copy(is, os);
		} catch (Exception ex) {
			logger.error("下载文件时发生异常：", ex);
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

}
