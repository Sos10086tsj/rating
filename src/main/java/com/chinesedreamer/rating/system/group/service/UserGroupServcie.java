package com.chinesedreamer.rating.system.group.service;

import java.util.List;

import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.system.group.model.UserGroup;
import com.chinesedreamer.rating.system.group.vo.GroupVo;

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
	
	public List<GroupVo> getAllGroupVos();
	
	public UserGroup create(GroupVo vo);
}
