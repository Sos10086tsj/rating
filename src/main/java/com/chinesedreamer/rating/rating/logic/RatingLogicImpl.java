package com.chinesedreamer.rating.rating.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.repository.RatingRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:39:22 AM
 * @version beta
 */
@Service
public class RatingLogicImpl extends BaseLogicImpl<Rating, Long> implements RatingLogic{
	@Resource
	private RatingRepository repository;

	@Override
	public List<Rating> findAll() {
		return this.repository.findAll();
	}

}
