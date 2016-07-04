package com.chinesedreamer.rating.rating.logic;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.rating.model.RatingResult;

/**
 * Description: 
 * @author Paris
 * @date Jul 2, 20159:47:56 AM
 * @version beta
 */
public interface RatingResultLogic extends BaseLogic<RatingResult, Long>{
	public List<RatingResult> findByTmplId(Long tmplId);
	public List<RatingResult> findByTmplIdIn(List<Long> tmplIds);
	
	public BigInteger count(Long tmplId);
	public List<RatingResult> findByTmplIdAndScorer(Long tmplId, Long scorer);
	
	/**
	 * 获取某个某次投票中，某个用户对另外一个用户的投票
	 * @param tmplId
	 * @param voter
	 * @param scorer
	 * @return
	 */
	public List<RatingResult> findVote(Long tmplId, Long voter, Long scorer);
	
	public void deletes(Collection<RatingResult> results);
}
