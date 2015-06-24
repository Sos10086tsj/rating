package com.chinesedreamer.rating.system.group.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.system.group.logic.UserGroupLogic;
import com.chinesedreamer.rating.system.group.model.UserGroup;

/**
 * Description: 
 * @author Paris
 * @date Jun 24, 20154:50:19 PM
 * @version beta
 */
@Service
public class UserGroupServcieImpl implements UserGroupServcie{
	@Resource
	private UserGroupLogic logic;

	@Override
	public List<SelectVo> getAllGroups() {
		List<SelectVo> vos = new ArrayList<SelectVo>();
		List<UserGroup> groups = this.logic.findAll();
		for (UserGroup userGroup : groups) {
			vos.add(new SelectVo(userGroup.getId().toString(), userGroup.getName()));
		}
		return vos;
	}
	
	
}
