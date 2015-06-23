package com.chinesedreamer.rating.template.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.template.OptionCategory;
import com.chinesedreamer.rating.template.mode.RatingSuppOption;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:43:01 AM
 * @version beta
 */
public interface RatingSuppOptionLogic extends BaseLogic<RatingSuppOption, Long>{
	public List<RatingSuppOption> findByCategoryOrderBySeqAsc(OptionCategory category);
	public RatingSuppOption findByCode(String code);
	public List<RatingSuppOption> findAllOrderBySeqAsc();
}
