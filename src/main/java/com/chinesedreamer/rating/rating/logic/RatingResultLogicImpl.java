package com.chinesedreamer.rating.rating.logic;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.rating.model.RatingResult;
import com.chinesedreamer.rating.rating.repository.RatingResultRepository;

/**
 * Description: 
 * @author Paris
 * @date Jul 2, 20159:48:25 AM
 * @version beta
 */
@Service
public class RatingResultLogicImpl extends BaseLogicImpl<RatingResult, Long> implements RatingResultLogic{
	@Resource
	private RatingResultRepository repository;
	@PersistenceContext
	private EntityManager em;
	@Override
	public List<RatingResult> findByTmplId(Long tmplId) {
		return this.repository.findByTmplId(tmplId);
	}
	@Override
	public BigInteger count(Long tmplId) {
		Query query = em.createNativeQuery(this.generateCountSql(tmplId));
		return (BigInteger)query.getSingleResult();
	}
	
	public List<RatingResult> findByTmplIdAndScorer(Long tmplId, Long scorer) {
		return this.repository.findByTmplIdAndScorer(tmplId, scorer);
	}

	private String generateCountSql(Long tmplId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT count(DISTINCT s.voter_id) ")
		.append(" FROM rating_result s ")
		.append(" WHERE s.tmpl_id = ")
		.append(tmplId);
		return buffer.toString();
	}
	@Override
	public List<RatingResult> findByTmplIdIn(List<Long> tmplIds) {
		return this.repository.findByTmplIdIn(tmplIds);
	}
	@Override
	public List<RatingResult> findVote(Long tmplId, Long voter, Long scorer) {
		return this.repository.findByTmplIdAndVoterIdAndScorer(tmplId, voter, scorer);
	}
	@Override
	public void deletes(Collection<RatingResult> results) {
		this.repository.deleteInBatch(results);
	}
	@Override
	public List<RatingResult> findByTmplIdAndVoterId(Long tmplId, Long voterId) {
		return this.repository.findByTmplIdAndVoterId(tmplId, voterId);
	}

}