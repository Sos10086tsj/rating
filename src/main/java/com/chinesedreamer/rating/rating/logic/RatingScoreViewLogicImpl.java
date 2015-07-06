package com.chinesedreamer.rating.rating.logic;

import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	@PersistenceContext
	private EntityManager em;
	@Override
	public List<RatingScoreView> findByTmplId(Long tmplId) {
		return this.repository.findByTmplId(tmplId);
	}
	@Override
	public List<RatingScoreView> findByTmplIdAndScorer(Long tmplId, Long scorer) {
		return this.repository.findByTmplIdAndScorer(tmplId, scorer);
	}
	}

	private String generateCountSql(Long tmplId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT count(DISTINCT s.voter_id) ")
		.append(" FROM v_score s ")
		.append(" WHERE s.tmpl_id = ")
		.append(tmplId);
		return buffer.toString();
	}
}
