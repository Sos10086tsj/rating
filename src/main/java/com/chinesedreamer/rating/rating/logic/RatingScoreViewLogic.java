package com.chinesedreamer.rating.rating.logic;

import java.math.BigInteger;
import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.rating.model.RatingScoreView;

/**
 * Description: 
 * @author Paris
 * @date Jul 2, 20159:47:56 AM
 * @version beta
 */
public interface RatingScoreViewLogic extends BaseLogic<RatingScoreView, Long>{
	public List<RatingScoreView> findByTmplId(Long tmplId);
	public List<RatingScoreView> findByTmplIdAndScorer(Long tmplId, Long scorer);
	public BigInteger count(Long tmplId);
}
