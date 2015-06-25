package com.chinesedreamer.rating.template.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.template.model.RatingTemplateOptionMapping;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:17:15 AM
 * @version beta
 */
public interface RatingTemplateOptionMappingLogic extends BaseLogic<RatingTemplateOptionMapping, Long>{
	public List<RatingTemplateOptionMapping> findByTmplId(Long tmplId);
}
