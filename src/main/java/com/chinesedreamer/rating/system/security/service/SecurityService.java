package com.chinesedreamer.rating.system.security.service;

import com.chinesedreamer.rating.system.security.vo.LisenceVo;

public interface SecurityService {
	/**
	 * 系统是否已授权
	 * @return
	 */
	public LisenceVo isSystemAuthorised();
	
	/**
	 * 加密mac地址
	 * @param mac
	 * @return
	 */
	public String encryptAuthorise(String mac);
	
	/**
	 * 授权
	 * @param macPass
	 * @return
	 */
	public boolean authorise(String macPass);
}
