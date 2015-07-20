/**
 * 
 */
package com.chinesedreamer.rating.common.io;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dev-laiting download组件
 */
public interface DownloadComponent {

	/**
	 * 下载
	 * 
	 * @param request
	 * @param response
	 * @param filePath
	 * @throws IOException
	 */
	public void download(HttpServletRequest request,
			HttpServletResponse response, String filePath) throws IOException;

	/**
	 * 下载
	 * 
	 * @param request
	 * @param response
	 * @param filePath
	 * @param displayName
	 * @throws IOException
	 */
	public void download(HttpServletRequest request,
			HttpServletResponse response, String filePath, String displayName)
			throws IOException;

}
