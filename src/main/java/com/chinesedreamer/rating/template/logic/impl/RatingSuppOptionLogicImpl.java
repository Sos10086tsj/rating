package com.chinesedreamer.rating.template.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.template.OptionCategory;
import com.chinesedreamer.rating.template.logic.RatingSuppOptionLogic;
import com.chinesedreamer.rating.template.mode.RatingSuppOption;
import com.chinesedreamer.rating.template.repository.RatingSuppOptionRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:43:24 AM
 * @version beta
 */
@Service
public class RatingSuppOptionLogicImpl extends BaseLogicImpl<RatingSuppOption, Long> implements RatingSuppOptionLogic{
	@Resource
	private RatingSuppOptionRepository repository;
	@Override
	public List<RatingSuppOption> findByCategoryOrderBySeqAsc(
			OptionCategory category) {
		return this.repository.findByCategoryOrderBySeqAsc(category);
	}

	@Override
	public RatingSuppOption findByCode(String code) {
		return this.findByCode(code);
	}

	@Override
	public List<RatingSuppOption> findAllOrderBySeqAsc() {
		return this.findAllOrderBySeqAsc();
	}

}
