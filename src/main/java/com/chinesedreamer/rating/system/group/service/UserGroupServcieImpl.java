package com.chinesedreamer.rating.system.group.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.system.group.UserGroupLevel;
import com.chinesedreamer.rating.system.group.logic.UserGroupLogic;
import com.chinesedreamer.rating.system.group.model.UserGroup;
import com.chinesedreamer.rating.system.group.vo.GroupVo;
import com.chinesedreamer.rating.system.user.UserPositionType;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateLogic;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateVoterLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplate;
import com.chinesedreamer.rating.template.model.RatingSuppTemplateVoter;

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
	@Resource
	private RatingSuppTemplateVoterLogic suppTemplateVoterLogic;
	@Resource
	private RatingSuppTemplateLogic suppTemplateLogic;

	@Override
	public List<SelectVo> getAllGroups() {
		List<SelectVo> vos = new ArrayList<SelectVo>();
		List<UserGroup> groups = this.logic.findAll();
		for (UserGroup userGroup : groups) {
			vos.add(new SelectVo(userGroup.getId().toString(), userGroup.getName()));
		}
		return vos;
	}

	@Override
	public List<GroupVo> getAllGroupVos() {
		List<GroupVo> vos = new ArrayList<GroupVo>();
		List<UserGroup> groups = this.logic.findAll();
		for (UserGroup userGroup : groups) {
			GroupVo vo = new GroupVo();
			vo.setId(userGroup.getId());
			vo.setLevel(userGroup.getLevel().getValue());
			vo.setLevelName(userGroup.getLevel().getLabel());
			vo.setName(userGroup.getName());
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public UserGroup create(GroupVo vo) {
		//如果已经存在，更新。如果新的，创建
		UserGroup group = null;
		if (null == vo.getId()) {
			group = new UserGroup();
		}else {
			group = this.logic.findOne(vo.getId());
		}
		group.setLevel(UserGroupLevel.get(vo.getLevel()));
		group.setName(vo.getName());
		group = this.logic.save(group);
		
		//配置模板
		this.initSuppTemplate(group);
		return group;
	}
	
	private void initSuppTemplate(UserGroup group) {
		//如果已经存在，删除
		List<RatingSuppTemplateVoter> exists = this.suppTemplateVoterLogic.findByGroupId(group.getId());
		for (RatingSuppTemplateVoter exist : exists) {
			this.suppTemplateVoterLogic.delete(exist);
		}
		//A表	组员
		//B表	组长、总体组
		//C表	组长、总体组
		//D表	组员
		Set<RatingSuppTemplate> applyTemplates = new HashSet<RatingSuppTemplate>();
		if (group.getLevel().equals(UserGroupLevel.ZONGTI)) {
			applyTemplates.addAll(this.suppTemplateLogic.findByCode("B"));
			applyTemplates.addAll(this.suppTemplateLogic.findByCode("C"));
			for (RatingSuppTemplate applyTemplate : applyTemplates) {
				RatingSuppTemplateVoter voter = new RatingSuppTemplateVoter();
				voter.setGroupId(group.getId());
				voter.setSuppTmplId(applyTemplate.getId());
				this.suppTemplateVoterLogic.save(voter);
			}
		}else if (group.getLevel().equals(UserGroupLevel.PUTONG)) {
			//组员
			applyTemplates.addAll(this.suppTemplateLogic.findByCode("A"));
			applyTemplates.addAll(this.suppTemplateLogic.findByCode("D"));
			for (RatingSuppTemplate applyTemplate : applyTemplates) {
				RatingSuppTemplateVoter voter = new RatingSuppTemplateVoter();
				voter.setGroupId(group.getId());
				voter.setSuppTmplId(applyTemplate.getId());
				voter.setPositionId(UserPositionType.TEAM_MATE.getValue());
				this.suppTemplateVoterLogic.save(voter);
			}
			applyTemplates.clear();
			applyTemplates.addAll(this.suppTemplateLogic.findByCode("B"));
			applyTemplates.addAll(this.suppTemplateLogic.findByCode("C"));
			for (RatingSuppTemplate applyTemplate : applyTemplates) {
				RatingSuppTemplateVoter voter = new RatingSuppTemplateVoter();
				voter.setGroupId(group.getId());
				voter.setSuppTmplId(applyTemplate.getId());
				voter.setPositionId(UserPositionType.LEADER.getValue());
				this.suppTemplateVoterLogic.save(voter);
			}
		}
	}
}
