package com.chinesedreamer.rating.template.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplateOptionMapping;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:19:17 AM
 * @version beta
 */
public interface RatingSuppTemplateOptionMappingLogic extends BaseLogic<RatingSuppTemplateOptionMapping, Long>{
	public List<RatingSuppTemplateOptionMapping> findBySuppTmplId(Long suppTmplId);
	public RatingSuppTemplateOptionMapping findBySuppTmplIdAndOptionId(Long suppTmplId, Long optionId);
}
