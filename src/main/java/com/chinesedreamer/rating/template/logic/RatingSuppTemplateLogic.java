package com.chinesedreamer.rating.template.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplate;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:53:25 AM
 * @version beta
 */
public interface RatingSuppTemplateLogic extends BaseLogic<RatingSuppTemplate, Long>{
	public List<RatingSuppTemplate> findAll();
	public List<RatingSuppTemplate> findByCode(String code);
}
