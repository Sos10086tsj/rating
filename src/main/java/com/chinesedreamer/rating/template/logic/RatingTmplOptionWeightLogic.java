package com.chinesedreamer.rating.template.logic;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.template.model.RatingTmplOptionWeight;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月1日 下午8:15:47 
 * Copyright:   Copyright (c)2015
 */
public interface RatingTmplOptionWeightLogic extends BaseLogic<RatingTmplOptionWeight, Long>{
	public RatingTmplOptionWeight findByTmplIdAndOptionId(Long tmplId,Long optionId);
}
