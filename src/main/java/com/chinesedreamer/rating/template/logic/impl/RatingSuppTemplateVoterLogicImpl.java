package com.chinesedreamer.rating.template.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateVoterLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplateVoter;
import com.chinesedreamer.rating.template.repository.RatingSuppTemplateVoterRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:28:11 AM
 * @version beta
 */
@Service
public class RatingSuppTemplateVoterLogicImpl extends BaseLogicImpl<RatingSuppTemplateVoter, Long> implements RatingSuppTemplateVoterLogic{
	@Resource
	private RatingSuppTemplateVoterRepository repository;
	@Override
	public List<RatingSuppTemplateVoter> findByGroupId(Long groupId) {
		return this.repository.findByGroupId(groupId);
	}

}
