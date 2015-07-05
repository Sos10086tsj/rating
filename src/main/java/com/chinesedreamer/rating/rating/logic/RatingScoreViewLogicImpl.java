package com.chinesedreamer.rating.rating.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.rating.model.RatingScoreView;
import com.chinesedreamer.rating.rating.repository.RatingScoreViewRepository;

/**
 * Description: 
 * @author Paris
 * @date Jul 2, 20159:48:25 AM
 * @version beta
 */
@Service
public class RatingScoreViewLogicImpl extends BaseLogicImpl<RatingScoreView, Long> implements RatingScoreViewLogic{
	@Resource
	private RatingScoreViewRepository repository;
	@Override
	public List<RatingScoreView> findByTmplId(Long tmplId) {
		return this.repository.findByTmplId(tmplId);
	}
	@Override
	public List<RatingScoreView> findByTmplIdAndScorer(Long tmplId, Long scorer) {
		return this.repository.findByTmplIdAndScorer(tmplId, scorer);
	}

}
