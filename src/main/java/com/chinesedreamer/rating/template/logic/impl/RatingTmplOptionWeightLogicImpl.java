package com.chinesedreamer.rating.template.logic.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.template.logic.RatingTmplOptionWeightLogic;
import com.chinesedreamer.rating.template.model.RatingTmplOptionWeight;
import com.chinesedreamer.rating.template.repository.RatingTmplOptionWeightRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月1日 下午8:16:31 
 * Copyright:   Copyright (c)2015
 */
@Service
public class RatingTmplOptionWeightLogicImpl extends BaseLogicImpl<RatingTmplOptionWeight, Long> implements RatingTmplOptionWeightLogic{
	@Resource
	private RatingTmplOptionWeightRepository repository;
	@Override
	public RatingTmplOptionWeight findByTmplIdAndOptionId(Long tmplId,Long optionId){
		return this.repository.findByTmplIdAndOptionId(tmplId, optionId);
	}
}
