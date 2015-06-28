package com.chinesedreamer.rating.rating.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.rating.model.RatingScore;
import com.chinesedreamer.rating.rating.repository.RatingScoreRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 25, 20151:11:48 PM
 * @version beta
 */
@Service
public class RatingScoreLogicImpl extends BaseLogicImpl<RatingScore, Long> implements RatingScoreLogic{
	@Resource
	private RatingScoreRepository repository;
	@Override
	public List<RatingScore> findAll() {
		return this.repository.findAll();
	}

}
