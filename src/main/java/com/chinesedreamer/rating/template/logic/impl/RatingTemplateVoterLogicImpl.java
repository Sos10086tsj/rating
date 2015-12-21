package com.chinesedreamer.rating.template.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.template.logic.RatingTemplateVoterLogic;
import com.chinesedreamer.rating.template.model.RatingTemplateVoter;
import com.chinesedreamer.rating.template.repository.RatingTemplateVoterRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:28:11 AM
 * @version beta
 */
@Service
public class RatingTemplateVoterLogicImpl extends BaseLogicImpl<RatingTemplateVoter, Long> implements RatingTemplateVoterLogic{
	@Resource
	private RatingTemplateVoterRepository repository;
	@Override
	public List<RatingTemplateVoter> findByTmplIdAndGroupIdAndPositionId(
			Long tmplId, Long groupId, Integer positionId) {
		return this.repository.findByTmplIdAndGroupIdAndPositionId(tmplId, groupId, positionId);
	}

	@Override
	public List<RatingTemplateVoter> findByTmplIdAndGroupId(Long tmplId,
			Long groupId) {
		return this.repository.findByTmplIdAndGroupId(tmplId, groupId);
	}

	@Override
	public List<RatingTemplateVoter> findByTmplId(Long tmplId) {
		return this.repository.findByTmplId(tmplId);
	}

}
