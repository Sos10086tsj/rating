package com.chinesedreamer.rating.template.logic;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTmplOptionWeight;

/**
 * Description: 
 * @author Paris
 * @date Jul 20, 20158:55:14 AM
 * @version beta
 */
public interface RatingSuppTmplOptionWeightLogic extends BaseLogic<RatingSuppTmplOptionWeight, Long>{
	public RatingSuppTmplOptionWeight findBySuppTmplIdAndSuppOptionId(Long suppTmplId, Long suppOptionId);
}
