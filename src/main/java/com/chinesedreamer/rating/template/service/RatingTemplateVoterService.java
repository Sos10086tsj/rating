package com.chinesedreamer.rating.template.service;

import java.util.List;

import com.chinesedreamer.rating.template.model.RatingTemplateVoter;

public interface RatingTemplateVoterService {
	/**
	 * 根据模板、组别和职位查找参与投票的人
	 * @param tmplId
	 * @param groupId
	 * @param position
	 * @return
	 */
	public List<RatingTemplateVoter> findByTmplIdAndGroupIdAndPositionId(Long tmplId, Long groupId, Integer positionId);
	
	/**
	 * 根据模板、组别和职位查找参与投票的人
	 * @param tmplId
	 * @param groupId
	 * @return
	 */
	public List<RatingTemplateVoter> findByTmplIdAndGroupId(Long tmplId, Long groupId);
	
	/**
	 * 根据模板查找参与投票的人
	 * @param tmplId
	 * @return
	 */
	public List<RatingTemplateVoter> findByTmplId(Long tmplId);
}
