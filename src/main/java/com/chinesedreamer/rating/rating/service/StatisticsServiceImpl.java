package com.chinesedreamer.rating.rating.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.rating.constant.WeightConstant;
import com.chinesedreamer.rating.rating.logic.RatingLogic;
import com.chinesedreamer.rating.rating.logic.RatingScoreViewLogic;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.model.RatingScoreView;
import com.chinesedreamer.rating.rating.vo.rpt.RptScore;
import com.chinesedreamer.rating.rating.vo.rpt.RptVo;
import com.chinesedreamer.rating.system.group.UserGroupLevel;
import com.chinesedreamer.rating.system.group.logic.UserGroupLogic;
import com.chinesedreamer.rating.system.group.model.UserGroup;
import com.chinesedreamer.rating.system.user.UserPositionType;
import com.chinesedreamer.rating.system.user.logic.UserLogic;
import com.chinesedreamer.rating.template.OptionCategory;
import com.chinesedreamer.rating.template.logic.RatingSuppOptionLogic;
import com.chinesedreamer.rating.template.logic.RatingTemplateLogic;
import com.chinesedreamer.rating.template.logic.RatingTmplOptionWeightLogic;
import com.chinesedreamer.rating.template.model.RatingSuppOption;
import com.chinesedreamer.rating.template.model.RatingTemplate;
import com.chinesedreamer.rating.template.model.RatingTmplOptionWeight;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月1日 下午8:30:09 
 * Copyright:   Copyright (c)2015
 */
@Service
public class StatisticsServiceImpl implements StatisticsService{
	@Resource
	private RatingScoreViewLogic scoreViewLogic;
	@Resource
	private RatingTemplateLogic templateLogic;
	@Resource
	private RatingLogic ratingLogic;
	@Resource
	private RatingTmplOptionWeightLogic ratingTmplOptionWeightLogic;
	@Resource
	private UserLogic userLogic;
	@Resource
	private UserGroupLogic userGroupLogic;
	@Resource
	private RatingSuppOptionLogic suppOptionLogic;

	@Override
	public RptVo generateReport(Long tmplId) {
		RptVo vo = new RptVo();
		RatingTemplate template = this.templateLogic.findOne(tmplId);
		Rating rating  = this.ratingLogic.findOne(template.getRatingId());
		vo.setName(rating.getName());
		vo.setFrom(rating.getEffFrom());
		vo.setTo(rating.getEffTo());
		vo.setStatus(rating.getStatus().toString());
		this.getRptScores(vo, template);
		//获取总投票人数
		vo.setVoterNum(this.scoreViewLogic.count(tmplId).intValue());
		return vo;
	}

	private void getRptScores(RptVo vo,RatingTemplate template){
		List<RatingScoreView> scoreViews = this.scoreViewLogic.findByTmplId(template.getId());
		String code = template.getCode();
		Map<Long, List<RatingScoreView>> scoreViewMap = new HashMap<Long, List<RatingScoreView>>();
		//1. 分离得分用户
		for (RatingScoreView scoreView : scoreViews) {
			List<RatingScoreView> tmp = null;
			if (scoreViewMap.containsKey(scoreView.getScorer())) {
				tmp = scoreViewMap.get(scoreView.getScorer());
			}else {
				tmp = new ArrayList<RatingScoreView>();
			}
			tmp.add(scoreView);
			scoreViewMap.put(scoreView.getScorer(), tmp);
		}
		
		if (code.equals("A")) {//A卷
			this.statisticsA(vo, scoreViewMap, template);
		}else if (code.equals("B")) {//B卷
			this.statisticsB(vo, scoreViewMap, template);
		}else if (code.equals("C")) {//C卷
			this.statisticsC(vo, scoreViewMap, template);
		}else if (code.equals("D")) {//D卷
			this.statisticsD(vo, scoreViewMap, template);
		}
	}
	
	/**
	 * 统计A卷得分。A卷组内20%，组外10%，占总比例70%
	 * @param vo
	 * @param scoreViews
	 * @param template
	 */
	private void statisticsA(RptVo vo,Map<Long, List<RatingScoreView>> scoreViewMap,RatingTemplate template) {
		
		List<RptScore> scores = new ArrayList<RptScore>();
		
		//2. 根据每个用户计算得分
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			RptScore score = new RptScore();
			score.setName(this.userLogic.findOne(key).getName());
			score.setGroup(this.userGroupLogic.findOne(tmp.get(0).getScorerGroup()).getName());
			score.setPosition(UserPositionType.get(tmp.get(0).getScorerPosition()).getLabel());
			
			Set<Long> innerVoter = new HashSet<Long>();
			Set<Long> outerVoter = new HashSet<Long>();
			
			Map<Long, Float> innerMap = new HashMap<Long, Float>();
			Map<Long, Float> outerMap = new HashMap<Long, Float>();
			
			//获取总分与投票人数
			for (RatingScoreView scoreView : tmp) {
				RatingTmplOptionWeight weight = this.ratingTmplOptionWeightLogic.findByTmplIdAndOptionId(template.getId(), scoreView.getOptionId());
				if (scoreView.getVoterGroupId().equals(scoreView.getScorerGroup())) {
					innerVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (innerMap.containsKey(optionKey)) {
						value = innerMap.get(optionKey);
					}
					value+= scoreView.getScore() * weight.getWeight().floatValue();
					innerMap.put(optionKey, value);
				}else {
					outerVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (outerMap.containsKey(optionKey)) {
						value = outerMap.get(optionKey);
					}
					value+= scoreView.getScore() * weight.getWeight().floatValue();
					outerMap.put(optionKey, value);
				}
			}
			
			Map<String, Float> scoreMap = new HashMap<String, Float>();
			for (Long optionKey : innerMap.keySet()) {
				RatingSuppOption suppOption = this.suppOptionLogic.findOne(optionKey);
				Float innerValue = innerMap.get(optionKey);
				Float outerValue = outerMap.get(optionKey);
				float innerRate = 1.0f;
				float outerRate = 1.0f;
				float rate = 1.0f;
				if (suppOption.getCategory().equals(OptionCategory.BXDF)) {
					innerRate = WeightConstant.A_BXDF_INNER_PERCENT;
					outerRate = WeightConstant.A_BXDF_OUTER_PERCENT;
					rate = WeightConstant.A_BXDF_PERCENTF;
				}else if (suppOption.getCategory().equals(OptionCategory.NLDF)) {
					innerRate = WeightConstant.A_NLDF_INNER_PERCENT;
					outerRate = WeightConstant.A_NLDF_OUTER_PERCENT;
					rate = WeightConstant.A_NLDF_PERCENTF;
				}
				int innerNum = innerVoter.isEmpty() ? 1 : innerVoter.size();
				int outerNum = outerVoter.isEmpty() ? 1 : outerVoter.size();
				Float value = rate * ( (innerValue / innerNum) * innerRate + (outerValue / outerNum) * outerRate );
				scoreMap.put("option_" + optionKey, value);
			}
			
			score.setScores(scoreMap);
			scores.add(score);
		}

		vo.setScores(scores);
	}
	
	/**
	 * 统计B卷得分
	 * @param vo
	 * @param scoreViews
	 * @param template
	 */
	private void statisticsB(RptVo vo,Map<Long, List<RatingScoreView>> scoreViewMap,RatingTemplate template) {
		
		List<RptScore> scores = new ArrayList<RptScore>();
		
		//2. 根据每个用户计算得分
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			RptScore score = new RptScore();
			score.setName(this.userLogic.findOne(key).getName());
			score.setGroup(this.userGroupLogic.findOne(tmp.get(0).getScorerGroup()).getName());
			score.setPosition(UserPositionType.get(tmp.get(0).getScorerPosition()).getLabel());
			
			Set<Long> innerVoter = new HashSet<Long>();
			Set<Long> outerVoter = new HashSet<Long>();
			Set<Long> zongtiVoter = new HashSet<Long>();
			
			Map<Long, Float> innerMap = new HashMap<Long, Float>();
			Map<Long, Float> outerMap = new HashMap<Long, Float>();
			Map<Long, Float> zongtiMap = new HashMap<Long, Float>();
			
			//获取总分与投票人数
			for (RatingScoreView scoreView : tmp) {
				RatingTmplOptionWeight weight = this.ratingTmplOptionWeightLogic.findByTmplIdAndOptionId(template.getId(), scoreView.getOptionId());
				UserGroup userGroup = this.userGroupLogic.findOne(scoreView.getVoterGroupId());
				if (scoreView.getVoterGroupId().equals(scoreView.getScorerGroup()) && scoreView.getVoterPositionId().equals(UserPositionType.LEADER.getValue())) {//本组组长
					innerVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (innerMap.containsKey(optionKey)) {
						value = innerMap.get(optionKey);
					}
					value+= scoreView.getScore() * weight.getWeight().floatValue();
					innerMap.put(optionKey, value);
				}else if(!scoreView.getVoterGroupId().equals(scoreView.getScorerGroup()) && scoreView.getVoterPositionId().equals(UserPositionType.LEADER.getValue())){//外组组长
					outerVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (outerMap.containsKey(optionKey)) {
						value = outerMap.get(optionKey);
					}
					value+= scoreView.getScore() * weight.getWeight().floatValue();
					outerMap.put(optionKey, value);
				}else if (userGroup.getLevel().equals(UserGroupLevel.ZONGTI)) {//总体组
					zongtiVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (zongtiMap.containsKey(optionKey)) {
						value = zongtiMap.get(optionKey);
					}
					value+= scoreView.getScore() * weight.getWeight().floatValue();
					zongtiMap.put(optionKey, value);
				}
			}
			
			Map<String, Float> scoreMap = new HashMap<String, Float>();
			for (Long optionKey : innerMap.keySet()) {
				RatingSuppOption suppOption = this.suppOptionLogic.findOne(optionKey);
				Float innerValue = innerMap.get(optionKey);
				Float outerValue = outerMap.get(optionKey);
				Float zongtiValue = zongtiMap.get(optionKey);
				float innerRate = 1.0f;
				float outerRate = 1.0f;
				float zongtiRate = 1.0f;
				float rate = 1.0f;
				if (suppOption.getCategory().equals(OptionCategory.BXDF)) {
					innerRate = WeightConstant.B_BXDF_INNER_LEADER_PERCENT;
					outerRate = WeightConstant.B_BXDF_OUTER_LEADER_PERCENT;
					zongtiRate = WeightConstant.B_BXDF_ZONGTI_PERCENT;
					rate = WeightConstant.B_BXDF_PERCENTF;
				}else if (suppOption.getCategory().equals(OptionCategory.NLDF)) {
					innerRate = WeightConstant.B_NLDF_INNER_LEADER_PERCENT;
					outerRate = WeightConstant.B_NLDF_OUTER_LEADER_PERCENT;
					zongtiRate = WeightConstant.B_NLDF_ZONGTI_PERCENT;
					rate = WeightConstant.B_NLDF_PERCENTF;
				}
				int innerNum = innerVoter.isEmpty() ? 1 : innerVoter.size();
				int outerNum = outerVoter.isEmpty() ? 1 : outerVoter.size();
				int zongtiNum = zongtiVoter.isEmpty() ? 1 : zongtiVoter.size();
				Float value = rate * ( (innerValue / innerNum) * innerRate + (outerValue / outerNum) * outerRate + (zongtiValue / zongtiNum) * zongtiRate);
				scoreMap.put("option_" + optionKey, value);
			}
			
			score.setScores(scoreMap);
			scores.add(score);
		}

		vo.setScores(scores);
	}
	
	/**
	 * 统计C卷得分
	 * @param vo
	 * @param scoreViews
	 * @param template
	 */
	private void statisticsC(RptVo vo,Map<Long, List<RatingScoreView>> scoreViewMap,RatingTemplate template) {
		
		List<RptScore> scores = new ArrayList<RptScore>();
		
		
		//2. 根据每个用户计算得分
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			RptScore score = new RptScore();
			score.setName(this.userLogic.findOne(key).getName());
			score.setGroup(this.userGroupLogic.findOne(tmp.get(0).getScorerGroup()).getName());
			score.setPosition(UserPositionType.get(tmp.get(0).getScorerPosition()).getLabel());
			
			Set<Long> leaderVoter = new HashSet<Long>();
			Set<Long> zongtiVoter = new HashSet<Long>();
			
			Map<Long, Float> leaderMap = new HashMap<Long, Float>();
			Map<Long, Float> zongtiMap = new HashMap<Long, Float>();
			
			//获取总分与投票人数
			for (RatingScoreView scoreView : tmp) {
				RatingTmplOptionWeight weight = this.ratingTmplOptionWeightLogic.findByTmplIdAndOptionId(template.getId(), scoreView.getOptionId());
				UserGroup userGroup = this.userGroupLogic.findOne(scoreView.getVoterGroupId());
				if (!userGroup.equals(UserGroupLevel.ZONGTI) && scoreView.getVoterPositionId().equals(UserPositionType.LEADER.getValue())) {//非总体组组长
					leaderVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (leaderMap.containsKey(optionKey)) {
						value = leaderMap.get(optionKey);
					}
					value+= scoreView.getScore() * weight.getWeight().floatValue();
					leaderMap.put(optionKey, value);
				}else if (userGroup.getLevel().equals(UserGroupLevel.ZONGTI)) {//总体组
					zongtiVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (zongtiMap.containsKey(optionKey)) {
						value = zongtiMap.get(optionKey);
					}
					value+= scoreView.getScore() * weight.getWeight().floatValue();
					zongtiMap.put(optionKey, value);
				}
			}
			
			Map<String, Float> scoreMap = new HashMap<String, Float>();
			for (Long optionKey : leaderMap.keySet()) {
				RatingSuppOption suppOption = this.suppOptionLogic.findOne(optionKey);
				Float leaderValue = leaderMap.get(optionKey);
				Float zongtiValue = zongtiMap.get(optionKey);
				float leaderRate = 1.0f;
				float zongtiRate = 1.0f;
				float rate = 1.0f;
				if (suppOption.getCategory().equals(OptionCategory.WCRWQK)) {
					leaderRate = WeightConstant.C_LEADER_PERCENT;
					zongtiRate = WeightConstant.C_ZHNL_PERCENT;
					rate = WeightConstant.C_WCRWQK_PERCENT;
				}else if (suppOption.getCategory().equals(OptionCategory.ZZNL)) {
					leaderRate = WeightConstant.C_LEADER_PERCENT;
					zongtiRate = WeightConstant.C_ZONGTI_PERCENT;
					rate = WeightConstant.C_ZZNL_PERCENT;
				}else if (suppOption.getCategory().equals(OptionCategory.ZHNL)) {
					leaderRate = WeightConstant.C_LEADER_PERCENT;
					zongtiRate = WeightConstant.C_ZONGTI_PERCENT;
					rate = WeightConstant.C_ZHNL_PERCENT;
				}
				int leaderNum = leaderVoter.isEmpty() ? 1 : leaderVoter.size();
				int zongtiNum = zongtiVoter.isEmpty() ? 1 : zongtiVoter.size();
				Float value = rate * ( (leaderValue / leaderNum) * leaderRate + (zongtiValue / zongtiNum) * zongtiRate);
				scoreMap.put("option_" + optionKey, value);
			}
			
			score.setScores(scoreMap);
			scores.add(score);
		}

		vo.setScores(scores);
	}
	
	/**
	 * 统计D卷得分
	 * @param vo
	 * @param scoreViews
	 * @param template
	 */
	private void statisticsD(RptVo vo,Map<Long, List<RatingScoreView>> scoreViewMap,RatingTemplate template) {
		
		List<RptScore> scores = new ArrayList<RptScore>();
		//2. 根据每个用户计算得分
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			RptScore score = new RptScore();
			score.setName(this.userLogic.findOne(key).getName());
			score.setGroup(this.userGroupLogic.findOne(tmp.get(0).getScorerGroup()).getName());
			score.setPosition(UserPositionType.get(tmp.get(0).getScorerPosition()).getLabel());
			
			Set<Long> zuyuanVoter = new HashSet<Long>();
			Map<Long, Float> zuyuanMap = new HashMap<Long, Float>();
			
			//获取总分与投票人数
			for (RatingScoreView scoreView : tmp) {
				RatingTmplOptionWeight weight = this.ratingTmplOptionWeightLogic.findByTmplIdAndOptionId(template.getId(), scoreView.getOptionId());
				UserGroup userGroup = this.userGroupLogic.findOne(scoreView.getVoterGroupId());
				if (!userGroup.equals(UserGroupLevel.ZONGTI) && scoreView.getVoterPositionId().equals(UserPositionType.TEAM_MATE.getValue())) {//普通组组员
					zuyuanVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (zuyuanMap.containsKey(optionKey)) {
						value = zuyuanMap.get(optionKey);
					}
					value+= scoreView.getScore() * weight.getWeight().floatValue();
					zuyuanMap.put(optionKey, value);
				}
			}
			
			Map<String, Float> scoreMap = new HashMap<String, Float>();
			for (Long optionKey : zuyuanMap.keySet()) {
				RatingSuppOption suppOption = this.suppOptionLogic.findOne(optionKey);
				Float zuyuanValue = zuyuanMap.get(optionKey);
				float zuyuanRate = 1.0f;
				float rate = 1.0f;
				if (suppOption.getCategory().equals(OptionCategory.WCRWQK)) {
					zuyuanRate = WeightConstant.D_WCRWQK_PERCENT;
					rate = WeightConstant.D_ZUYUAN_PERCENT;
				}else if (suppOption.getCategory().equals(OptionCategory.ZZNL)) {
					zuyuanRate = WeightConstant.D_ZZNL_PERCENT;
					rate = WeightConstant.D_ZUYUAN_PERCENT;
				}else if (suppOption.getCategory().equals(OptionCategory.ZHNL)) {
					zuyuanRate = WeightConstant.D_ZHNL_PERCENT;
					rate = WeightConstant.D_ZUYUAN_PERCENT;
				}
				int zuyuanNum = zuyuanVoter.isEmpty() ? 1 : zuyuanVoter.size();
				Float value = rate * ( (zuyuanValue / zuyuanNum) * zuyuanRate);
				scoreMap.put("option_" + optionKey, value);
			}
			
			score.setScores(scoreMap);
			scores.add(score);
		}

		vo.setScores(scores);
	}
}
