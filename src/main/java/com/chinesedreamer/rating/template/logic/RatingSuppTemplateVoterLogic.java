package com.chinesedreamer.rating.template.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplateVoter;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:27:48 AM
 * @version beta
 */
public interface RatingSuppTemplateVoterLogic extends BaseLogic<RatingSuppTemplateVoter, Long>{
	public List<RatingSuppTemplateVoter> findByGroupId(Long groupId);
}
