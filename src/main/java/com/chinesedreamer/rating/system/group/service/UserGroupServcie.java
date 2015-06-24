package com.chinesedreamer.rating.system.group.service;

import java.util.List;

import com.chinesedreamer.rating.common.vo.SelectVo;

/**
 * Description: 
 * @author Paris
 * @date Jun 24, 20154:49:51 PM
 * @version beta
 */
public interface UserGroupServcie {
	/**
	 * 获取所有组
	 * @return
	 */
	public List<SelectVo> getAllGroups();
}
