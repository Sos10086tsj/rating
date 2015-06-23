package com.chinesedreamer.rating.template.logic.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.template.logic.RatingTemplateLogic;
import com.chinesedreamer.rating.template.mode.RatingSuppTempalteOptionMapping;
import com.chinesedreamer.rating.template.mode.RatingSuppTemplate;
import com.chinesedreamer.rating.template.mode.RatingSuppTemplateVoter;
import com.chinesedreamer.rating.template.mode.RatingSuppTmplOptionWeight;
import com.chinesedreamer.rating.template.mode.RatingTemplate;
import com.chinesedreamer.rating.template.mode.RatingTemplateOptionMapping;
import com.chinesedreamer.rating.template.mode.RatingTemplateVoter;
import com.chinesedreamer.rating.template.mode.RatingTmplOptionWeight;
import com.chinesedreamer.rating.template.repository.RatingSuppTempalteOptionMappingRepository;
import com.chinesedreamer.rating.template.repository.RatingSuppTemplateRepository;
import com.chinesedreamer.rating.template.repository.RatingSuppTemplateVoterRepository;
import com.chinesedreamer.rating.template.repository.RatingSuppTmplOptionWeightRepository;
import com.chinesedreamer.rating.template.repository.RatingTempalteOptionMappingRepository;
import com.chinesedreamer.rating.template.repository.RatingTemplateRepository;
import com.chinesedreamer.rating.template.repository.RatingTemplateVoterRepository;
import com.chinesedreamer.rating.template.repository.RatingTmplOptionWeightRepository;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:55:42 AM
 * @version beta
 */
@Service
public class RatingTemplateLogicImpl extends BaseLogicImpl<RatingTemplate, Long> implements RatingTemplateLogic{
	@Resource
	private RatingTemplateRepository repository;
	@Resource
	private RatingSuppTemplateRepository suppTemplateRepository;
	@Resource
	private RatingTemplateVoterRepository templateVoterRepository;
	@Resource
	private RatingSuppTemplateVoterRepository suppTemplateVoterRepository;
	@Resource
	private RatingTempalteOptionMappingRepository tempalteOptionMappingRepository;
	@Resource
	private RatingSuppTempalteOptionMappingRepository suppTempalteOptionMappingRepository;
	@Resource
	private RatingTmplOptionWeightRepository tmplOptionWeightRepository;
	@Resource
	private RatingSuppTmplOptionWeightRepository suppTmplOptionWeightRepository;
	
	@Override
	public List<RatingTemplate> findByRatingId(Long ratingId) {
		return this.repository.findByRatingId(ratingId);
	}
	
	@Override
	public void copySuppTemplateToRating(Rating rating, Long[] suppTemplateIds) {
		for (Long suppTemplateId : suppTemplateIds) {
			RatingSuppTemplate rst = this.suppTemplateRepository.findOne(suppTemplateId);
			RatingTemplate rt = new RatingTemplate();
			rt.setName(rst.getName());
			rt.setRatingId(rating.getId());
			this.repository.save(rt);
			//2. 投票人数据保存
			List<RatingSuppTemplateVoter> voters = this.suppTemplateVoterRepository.findBySuppTmplId(suppTemplateId);
			for (RatingSuppTemplateVoter suppVoter : voters) {
				RatingTemplateVoter voter = new RatingTemplateVoter();
				voter.setGroupId(suppVoter.getGroupId());
				voter.setPositionId(suppVoter.getGroupId());
				voter.setTmplId(rt.getId());
				this.templateVoterRepository.save(voter);
			}
			//3. option保存
			List<RatingSuppTempalteOptionMapping> suppTemplateOptionMappings = this.suppTempalteOptionMappingRepository.findBySuppTmplIdOrderBySqlAsc(suppTemplateId);
			for (RatingSuppTempalteOptionMapping suppTemplateOptionMapping : suppTemplateOptionMappings) {
				RatingTemplateOptionMapping templateOptionMapping = new RatingTemplateOptionMapping();
				templateOptionMapping.setOptionId(suppTemplateOptionMapping.getOptionId());
				templateOptionMapping.setSeq(suppTemplateOptionMapping.getSeq());
				templateOptionMapping.setTmplId(rt.getId());
				this.tempalteOptionMappingRepository.save(templateOptionMapping);
				//4. 保存权重计算
				RatingSuppTmplOptionWeight suppWeight = this.suppTmplOptionWeightRepository.findBySuppTmplIdAndSuppOptionId(suppTemplateId, suppTemplateOptionMapping.getOptionId());
				RatingTmplOptionWeight weight = new RatingTmplOptionWeight();
				weight.setOptionId(suppTemplateOptionMapping.getOptionId());
				weight.setTmplId(rt.getId());
				weight.setWeight(suppWeight.getWeight());
				this.tmplOptionWeightRepository.save(weight);
			}
		}
	}

}
