package com.chinesedreamer.rating.template.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.template.logic.RatingTemplateVoterLogic;
import com.chinesedreamer.rating.template.model.RatingTemplateVoter;

@Service
public class RatingTemplateVoterServiceImpl implements RatingTemplateVoterService{
	
	@Resource
	private RatingTemplateVoterLogic logic;

	@Override
	public List<RatingTemplateVoter> findByTmplIdAndGroupIdAndPositionId(Long tmplId, Long groupId,
			Integer positionId) {
		return this.logic.findByTmplIdAndGroupIdAndPositionId(tmplId, groupId, positionId);
	}

	@Override
	public List<RatingTemplateVoter> findByTmplIdAndGroupId(Long tmplId, Long groupId) {
		return this.logic.findByTmplIdAndGroupId(tmplId, groupId);
	}

}
