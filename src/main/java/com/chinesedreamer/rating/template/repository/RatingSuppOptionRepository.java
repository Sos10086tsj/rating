package com.chinesedreamer.rating.template.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.template.OptionCategory;
import com.chinesedreamer.rating.template.mode.RatingSuppOption;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:39:53 AM
 * @version beta
 */
public interface RatingSuppOptionRepository extends BaseRepository<RatingSuppOption, Long>{
	public List<RatingSuppOption> findByCategoryOrderBySeqAsc(OptionCategory category);
	public RatingSuppOption findByCode(String code);
	public List<RatingSuppOption> findAllOrderBySeqAsc();
}
