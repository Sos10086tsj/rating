package com.chinesedreamer.rating.rating.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.rating.constant.WeightConstant;
import com.chinesedreamer.rating.rating.logic.RatingLogic;
import com.chinesedreamer.rating.rating.logic.RatingScoreViewLogic;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.model.RatingScoreView;
import com.chinesedreamer.rating.rating.vo.rpt.RptVo;
import com.chinesedreamer.rating.system.config.ConfigConstant;
import com.chinesedreamer.rating.system.config.logic.ConfigLogic;
import com.chinesedreamer.rating.system.config.model.Config;
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
import com.chinesedreamer.rating.template.util.RatingSuppTmplScoerUtil;

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
	@Resource
	private ConfigLogic configLogic;

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
		
		List<Map<String, String>> scores = null;
		if (null != vo && null != vo.getScores() && !vo.getScores().isEmpty()) {
			scores = vo.getScores();
		}else {
			scores = new ArrayList<Map<String, String>>();
		}	
		Config config = this.configLogic.findByProperty(ConfigConstant.STATISTICS_FORMAT);
		
		//2. 根据每个用户计算得分
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			Map<String, String> score = new HashMap<String, String>();
			//score.setName(this.userLogic.findOne(key).getName());
			//score.setGroup(this.userGroupLogic.findOne(tmp.get(0).getScorerGroup()).getName());
			//score.setPosition(UserPositionType.get(tmp.get(0).getScorerPosition()).getLabel());
			score.put("name", this.userLogic.findOne(key).getName());
			score.put("user_id", key.toString());
			
			Set<Long> innerVoter = new HashSet<Long>();
			Set<Long> outerVoter = new HashSet<Long>();
			
			Map<Long, Float> innerMap = new HashMap<Long, Float>();
			Map<Long, Float> outerMap = new HashMap<Long, Float>();
			
			//获取总分与投票人数
			for (RatingScoreView scoreView : tmp) {
				if (scoreView.getVoterGroupId().equals(scoreView.getScorerGroup())) {
					innerVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (innerMap.containsKey(optionKey)) {
						value = innerMap.get(optionKey);
					}
					value+= scoreView.getScore() ;
					innerMap.put(optionKey, value);
				}else {
					outerVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (outerMap.containsKey(optionKey)) {
						value = outerMap.get(optionKey);
					}
					value+= scoreView.getScore() ;
					outerMap.put(optionKey, value);
				}
			}
			
			Float total = 0.00000f;
			
			for (Long optionKey : innerMap.keySet()) {
				RatingTmplOptionWeight weight = this.ratingTmplOptionWeightLogic.findByTmplIdAndOptionId(template.getId(), optionKey);
				
				RatingSuppOption suppOption = this.suppOptionLogic.findOne(optionKey);
				Float innerValue = innerMap.get(optionKey);
				innerValue = (null == innerValue ? 0 : innerValue);
				Float outerValue = outerMap.get(optionKey);
				outerValue = (null == outerValue ? 0 : outerValue);
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
				Float value = (innerValue / innerNum) * innerRate + (outerValue / outerNum) * outerRate ;
				score.put("option_" + optionKey, this.formatScore(value,config));
				
				total += weight.getWeight().floatValue() / 100 * value * rate;
			}
			score.put("total", this.formatScore(total,config));
			List<Map<String, String>> removes = new ArrayList<Map<String,String>>();
			for (Map<String, String> exist : scores) {
				if (exist.get("user_id").equals(score.get("user_id"))) {//存在
					for (String existKey : score.keySet()) {
						if (!existKey.equals("user_id") && !existKey.equals("name")) {
							Float v1 = Float.parseFloat(score.get(existKey));
							Float v2 = exist.containsKey(existKey) ? Float.parseFloat(exist.get(existKey)) : 0.0f;
							Float v = v1+v2;
							score.put(existKey, this.formatScore(v,config));
						}
					}
					removes.add(exist);
					//scores.remove(exist);
				}
			}
			for (Map<String, String> map : removes) {
				scores.remove(map);
			}
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
		List<Map<String, String>> scores = null;
		if (null != vo && null != vo.getScores() && !vo.getScores().isEmpty()) {
			scores = vo.getScores();
		}else {
			scores = new ArrayList<Map<String, String>>();
		}	
		Config config = this.configLogic.findByProperty(ConfigConstant.STATISTICS_FORMAT);

		//2. 根据每个用户计算得分
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			Map<String, String> score = new HashMap<String, String>();
//			score.setName(this.userLogic.findOne(key).getName());
//			score.setGroup(this.userGroupLogic.findOne(tmp.get(0).getScorerGroup()).getName());
//			score.setPosition(UserPositionType.get(tmp.get(0).getScorerPosition()).getLabel());
			score.put("name", this.userLogic.findOne(key).getName());
			score.put("user_id", key.toString());
			
			Set<Long> innerVoter = new HashSet<Long>();
			Set<Long> outerVoter = new HashSet<Long>();
			Set<Long> zongtiVoter = new HashSet<Long>();
			
			Map<Long, Float> innerMap = new HashMap<Long, Float>();
			Map<Long, Float> outerMap = new HashMap<Long, Float>();
			Map<Long, Float> zongtiMap = new HashMap<Long, Float>();
			
			//获取总分与投票人数
			for (RatingScoreView scoreView : tmp) {
				
				UserGroup voterGroup = this.userGroupLogic.findOne(scoreView.getVoterGroupId());
				if (scoreView.getVoterGroupId().equals(scoreView.getScorerGroup()) 
						&&
						scoreView.getVoterPositionId().equals(UserPositionType.LEADER.getValue())) {//本组组长
					innerVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (innerMap.containsKey(optionKey)) {
						value = innerMap.get(optionKey);
					}
					value+= scoreView.getScore() ;
					innerMap.put(optionKey, value);
				}else if(!scoreView.getVoterGroupId().equals(scoreView.getScorerGroup()) && scoreView.getVoterPositionId().equals(UserPositionType.LEADER.getValue())){
					outerVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (outerMap.containsKey(optionKey)) {
						value = outerMap.get(optionKey);
					}
					value+= scoreView.getScore() ;
					outerMap.put(optionKey, value);
				}else if (voterGroup.getLevel().equals(UserGroupLevel.ZONGTI)) {
					zongtiVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (zongtiMap.containsKey(optionKey)) {
						value = zongtiMap.get(optionKey);
					}
					value+= scoreView.getScore() ;
					zongtiMap.put(optionKey, value);
				}
			}
			
			Float total = 0.00000f;
			for (Long optionKey : innerMap.keySet()) {
				RatingTmplOptionWeight weight = this.ratingTmplOptionWeightLogic.findByTmplIdAndOptionId(template.getId(), optionKey);
				
				RatingSuppOption suppOption = this.suppOptionLogic.findOne(optionKey);
				Float innerValue = innerMap.get(optionKey);
				innerValue = (null == innerValue ? 0 : innerValue);
				Float outerValue = outerMap.get(optionKey);
				outerValue = (null == outerValue ? 0 : outerValue);
				Float zongtiValue = zongtiMap.get(optionKey);
				zongtiValue = (null == zongtiValue ? 0 : zongtiValue);
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
				Float value =  (innerValue / innerNum) * innerRate + (outerValue / outerNum) * outerRate + (zongtiValue / zongtiNum) * zongtiRate;
				score.put("option_" + optionKey, this.formatScore(value,config));
				total += rate * weight.getWeight().floatValue() / 100 * value;
			}
			score.put("total", this.formatScore(total,config));
			List<Map<String, String>> removes = new ArrayList<Map<String,String>>();
			for (Map<String, String> exist : scores) {
				if (exist.get("user_id").equals(score.get("user_id"))) {//存在
					for (String existKey : score.keySet()) {
						if (!existKey.equals("user_id") && !existKey.equals("name")) {
							Float v1 = Float.parseFloat(score.get(existKey));
							Float v2 = exist.containsKey(existKey) ? Float.parseFloat(exist.get(existKey)) : 0.0f;
							Float v = v1+v2;
							score.put(existKey, this.formatScore(v,config));
						}
					}
					removes.add(exist);
					//scores.remove(exist);
				}
			}
			for (Map<String, String> map : removes) {
				scores.remove(map);
			}
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
		List<Map<String, String>> scores = null;
		if (null != vo && null != vo.getScores() && !vo.getScores().isEmpty()) {
			scores = vo.getScores();
		}else {
			scores = new ArrayList<Map<String, String>>();
		}	
		Config config = this.configLogic.findByProperty(ConfigConstant.STATISTICS_FORMAT);
		//2. 根据每个用户计算得分
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			Map<String, String> score = new HashMap<String, String>();
//			score.setName(this.userLogic.findOne(key).getName());
//			score.setGroup(this.userGroupLogic.findOne(tmp.get(0).getScorerGroup()).getName());
//			score.setPosition(UserPositionType.get(tmp.get(0).getScorerPosition()).getLabel());
			score.put("name", this.userLogic.findOne(key).getName());
			score.put("user_id", key.toString());
			
			Set<Long> leaderVoter = new HashSet<Long>();
			
			Map<Long, Float> leaderMap = new HashMap<Long, Float>();//总体组、组长投票一起统计
			
			//获取总分与投票人数
			for (RatingScoreView scoreView : tmp) {
				
				UserGroup voterGroup = this.userGroupLogic.findOne(scoreView.getVoterGroupId());
				if (scoreView.getVoterPositionId().equals(UserPositionType.LEADER.getValue())) {//组长
					leaderVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (leaderMap.containsKey(optionKey)) {
						value = leaderMap.get(optionKey);
					}
					value+= scoreView.getScore() ;
					leaderMap.put(optionKey, value);
				}else if (voterGroup.getLevel().equals(UserGroupLevel.ZONGTI)) {
					leaderVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (leaderMap.containsKey(optionKey)) {
						value = leaderMap.get(optionKey);
					}
					value+= scoreView.getScore() ;
					leaderMap.put(optionKey, value);
				}
			}
			Float total = 0.00000f;
			for (Long optionKey : leaderMap.keySet()) {
				RatingTmplOptionWeight weight = this.ratingTmplOptionWeightLogic.findByTmplIdAndOptionId(template.getId(), optionKey);
				
				RatingSuppOption suppOption = this.suppOptionLogic.findOne(optionKey);
				Float leaderValue = leaderMap.get(optionKey);
				leaderValue = (null == leaderValue ? 0 : leaderValue);
				float leaderRate = 1.0f;
				float rate = 1.0f;
				if (suppOption.getCategory().equals(OptionCategory.WCRWQK)) {
					leaderRate = WeightConstant.C_WCRWQK_LEADER_PERCENTF;
					rate = WeightConstant.C_WCRWQK_PERCENTF;
				}else if (suppOption.getCategory().equals(OptionCategory.ZZNL)) {
					leaderRate = WeightConstant.C_ZZNL_LEADER_PERCENTF;
					rate = WeightConstant.C_ZZNL_PERCENTF;
				}else if (suppOption.getCategory().equals(OptionCategory.ZHNL)) {
					leaderRate = WeightConstant.C_ZHNL_LEADER_PERCENTF;
					rate = WeightConstant.C_ZHNL_PERCENTF;
				}
				int leaderNum = leaderVoter.isEmpty() ? 1 : leaderVoter.size();
				Float value =  (leaderValue / leaderNum) * leaderRate ;
				score.put("option_" + optionKey, this.formatScore(value,config));
				total += rate * weight.getWeight().floatValue() / 100 * value;
			}
			score.put("total", this.formatScore(total,config));
			List<Map<String, String>> removes = new ArrayList<Map<String,String>>();
			for (Map<String, String> exist : scores) {
				if (exist.get("user_id").equals(score.get("user_id"))) {//存在
					for (String existKey : score.keySet()) {
						if (!existKey.equals("user_id") && !existKey.equals("name")) {
							Float v1 = Float.parseFloat(score.get(existKey));
							Float v2 = exist.containsKey(existKey) ? Float.parseFloat(exist.get(existKey)) : 0.0f;
							Float v = v1+v2;
							score.put(existKey, this.formatScore(v,config));
						}
					}
					removes.add(exist);
					//scores.remove(exist);
				}
			}
			for (Map<String, String> map : removes) {
				scores.remove(map);
			}
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
		List<Map<String, String>> scores = null;
		if (null != vo && null != vo.getScores() && !vo.getScores().isEmpty()) {
			scores = vo.getScores();
		}else {
			scores = new ArrayList<Map<String, String>>();
		}	
		Config config = this.configLogic.findByProperty(ConfigConstant.STATISTICS_FORMAT);
		//2. 根据每个用户计算得分
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			Map<String, String> score = new HashMap<String, String>();
//			score.setName(this.userLogic.findOne(key).getName());
//			score.setGroup(this.userGroupLogic.findOne(tmp.get(0).getScorerGroup()).getName());
//			score.setPosition(UserPositionType.get(tmp.get(0).getScorerPosition()).getLabel());
			score.put("name", this.userLogic.findOne(key).getName());
			score.put("user_id", key.toString());
			
			Set<Long> zuyuanVoter = new HashSet<Long>();

			Map<Long, Float> zuyuanMap = new HashMap<Long, Float>();
			
			//获取总分与投票人数
			for (RatingScoreView scoreView : tmp) {
				
				if (scoreView.getVoterPositionId().equals(UserPositionType.TEAM_MATE.getValue())) {//组远
					zuyuanVoter.add(scoreView.getVoterId());
					Long optionKey = scoreView.getOptionId();
					Float value = 0.0000f;
					if (zuyuanMap.containsKey(optionKey)) {
						value = zuyuanMap.get(optionKey);
					}
					value+= scoreView.getScore() ;
					zuyuanMap.put(optionKey, value);
				}
			}
			Float total = 0.00000f;
			for (Long optionKey : zuyuanMap.keySet()) {
				RatingTmplOptionWeight weight = this.ratingTmplOptionWeightLogic.findByTmplIdAndOptionId(template.getId(), optionKey);
				
				RatingSuppOption suppOption = this.suppOptionLogic.findOne(optionKey);
				Float zuyuanValue = zuyuanMap.get(optionKey);
				zuyuanValue = (null == zuyuanValue ? 0 : zuyuanValue);
				float zuyuanRate = 1.0f;
				float rate = 1.0f;
				if (suppOption.getCategory().equals(OptionCategory.WCRWQK)) {
					zuyuanRate = WeightConstant.D_WCRWQK_ZUYUAN_PERCENTF;
					rate = WeightConstant.D_WCRWQK_PERCENTF;
				}else if (suppOption.getCategory().equals(OptionCategory.ZZNL)) {
					zuyuanRate = WeightConstant.D_ZZNL_ZUYUAN_PERCENTF;
					rate = WeightConstant.D_WCRWQK_PERCENTF;
				}else if (suppOption.getCategory().equals(OptionCategory.ZHNL)) {
					zuyuanRate = WeightConstant.D_ZHNL_ZUYUAN_PERCENTF;
					rate = WeightConstant.D_WCRWQK_PERCENTF;
				}
				int zuyuanNum = zuyuanVoter.isEmpty() ? 1 : zuyuanVoter.size();
				Float value =   (zuyuanValue / zuyuanNum) * zuyuanRate;
				score.put("option_" + optionKey, this.formatScore(value,config));
				total += rate * weight.getWeight().floatValue() / 100 * value;
			}
			score.put("total", this.formatScore(total,config));
			List<Map<String, String>> removes = new ArrayList<Map<String,String>>();
			for (Map<String, String> exist : scores) {
				if (exist.get("user_id").equals(score.get("user_id"))) {//存在
					for (String existKey : score.keySet()) {
						if (!existKey.equals("user_id") && !existKey.equals("name")) {
							Float v1 = Float.parseFloat(score.get(existKey));
							Float v2 = exist.containsKey(existKey) ? Float.parseFloat(exist.get(existKey)) : 0.0f;
							Float v = v1+v2;
							score.put(existKey, this.formatScore(v,config));
						}
					}
					removes.add(exist);
					//scores.remove(exist);
				}
			}
			for (Map<String, String> map : removes) {
				scores.remove(map);
			}
			scores.add(score);
		}

		vo.setScores(scores);
	}

	@Override
	public List<Map<String, String>> userDetails(Long tmplId, Long user) {
		List<Map<String, String>> rstMap = new ArrayList<Map<String,String>>();
		
		RatingTemplate template = this.templateLogic.findOne(tmplId);
		List<RatingScoreView> scoreViews = this.scoreViewLogic.findByTmplIdAndScorer(tmplId, user);
		String code = template.getCode();
		Map<Long, List<RatingScoreView>> scoreViewMap = new HashMap<Long, List<RatingScoreView>>();
		//1. 根据投票用户分离
		for (RatingScoreView scoreView : scoreViews) {
			List<RatingScoreView> tmp = null;
			if (scoreViewMap.containsKey(scoreView.getVoterId())) {
				tmp = scoreViewMap.get(scoreView.getVoterId());
			}else {
				tmp = new ArrayList<RatingScoreView>();
			}
			tmp.add(scoreView);
			scoreViewMap.put(scoreView.getVoterId(), tmp);
		}
		
		if (code.equals("A")) {//A卷
			this.userDetailA(rstMap, scoreViewMap, template);
		}else if (code.equals("B")) {//B卷
			this.userDetailB(rstMap, scoreViewMap, template);
		}else if (code.equals("C")) {//C卷
			this.userDetailC(rstMap, scoreViewMap, template);
		}else if (code.equals("D")) {//D卷
			this.userDetailD(rstMap, scoreViewMap, template);
		}
		
		return rstMap;
	}
	
	private void userDetailA(List<Map<String, String>> rstMap,Map<Long, List<RatingScoreView>> scoreViewMap,RatingTemplate template){
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			Map<String, String> score = new HashMap<String, String>();
			score.put("name", this.userLogic.findOne(key).getName());
			score.put("user_id", key.toString());
			boolean duplicate = false;
			Map<String, String> exist = null;
			for (Map<String, String> rst : rstMap) {
				if (rst.get("user_id").equals(score.get("user_id"))) {//重复
					duplicate = true;
					exist = rst;
					break;
				}
			}
			rstMap.remove(exist);
			for (RatingScoreView scoreView : tmp) {
				Long optionKey = scoreView.getOptionId();
				if (scoreView.getVoterGroupId().equals(scoreView.getScorerGroup())) {
					score.put("source", "本组");
				}else {
					score.put("source", "外组");
				}
				if (duplicate) {
					score.put("option_" + optionKey, 
							String.valueOf((scoreView.getScore() + Float.parseFloat(exist.get("option_" + optionKey)))));
				}else {
					score.put("option_" + optionKey, scoreView.getScore().toString());
				}
			}
			rstMap.add(score);
		}
	}
	
	private void userDetailB(List<Map<String, String>> rstMap,Map<Long, List<RatingScoreView>> scoreViewMap,RatingTemplate template){
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			Map<String, String> score = new HashMap<String, String>();
			score.put("name", this.userLogic.findOne(key).getName());
			score.put("user_id", key.toString());
			boolean duplicate = false;
			Map<String, String> exist = null;
			for (Map<String, String> rst : rstMap) {
				if (rst.get("user_id").equals(score.get("user_id"))) {//重复
					duplicate = true;
					exist = rst;
					break;
				}
			}
			rstMap.remove(exist);
			for (RatingScoreView scoreView : tmp) {
				Long optionKey = scoreView.getOptionId();
				UserGroup voterGroup = this.userGroupLogic.findOne(scoreView.getVoterGroupId());
				if (scoreView.getVoterGroupId().equals(scoreView.getScorerGroup()) 
						&&
						scoreView.getVoterPositionId().equals(UserPositionType.LEADER.getValue())) {//本组组长
					score.put("source", "本组组长");
				}else if(!scoreView.getVoterGroupId().equals(scoreView.getScorerGroup()) 
						&& scoreView.getVoterPositionId().equals(UserPositionType.LEADER.getValue())){
					score.put("source", "外组组长");
				}else if (voterGroup.getLevel().equals(UserGroupLevel.ZONGTI)) {
					score.put("source", "总体组");
				}
				if (duplicate) {
					score.put("option_" + optionKey, 
							String.valueOf((scoreView.getScore() + Float.parseFloat(exist.get("option_" + optionKey)))));
				}else {
					score.put("option_" + optionKey, scoreView.getScore().toString());
				}
			}
			rstMap.add(score);
		}
	}
	
	private void userDetailC(List<Map<String, String>> rstMap,Map<Long, List<RatingScoreView>> scoreViewMap,RatingTemplate template){
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			Map<String, String> score = new HashMap<String, String>();
			score.put("name", this.userLogic.findOne(key).getName());
			score.put("user_id", key.toString());
			boolean duplicate = false;
			Map<String, String> exist = null;
			for (Map<String, String> rst : rstMap) {
				if (rst.get("user_id").equals(score.get("user_id"))) {//重复
					duplicate = true;
					exist = rst;
					break;
				}
			}
			rstMap.remove(exist);
			for (RatingScoreView scoreView : tmp) {
				Long optionKey = scoreView.getOptionId();
				UserGroup voterGroup = this.userGroupLogic.findOne(scoreView.getVoterGroupId());
				if (scoreView.getVoterPositionId().equals(UserPositionType.LEADER.getValue())) {//组长
					score.put("source", "组长");
				}else if (voterGroup.getLevel().equals(UserGroupLevel.ZONGTI)) {
					score.put("source", "总体组");
				}
				if (duplicate) {
					score.put("option_" + optionKey, 
							String.valueOf((scoreView.getScore() + Float.parseFloat(exist.get("option_" + optionKey)))));
				}else {
					score.put("option_" + optionKey, scoreView.getScore().toString());
				}
			}
			rstMap.add(score);
		}
	}
	
	private void userDetailD(List<Map<String, String>> rstMap,Map<Long, List<RatingScoreView>> scoreViewMap,RatingTemplate template){
		for (Long key : scoreViewMap.keySet()) {
			List<RatingScoreView> tmp = scoreViewMap.get(key);
			Map<String, String> score = new HashMap<String, String>();
			score.put("name", this.userLogic.findOne(key).getName());
			score.put("user_id", key.toString());
			boolean duplicate = false;
			Map<String, String> exist = null;
			for (Map<String, String> rst : rstMap) {
				if (rst.get("user_id").equals(score.get("user_id"))) {//重复
					duplicate = true;
					exist = rst;
					break;
				}
			}
			rstMap.remove(exist);
			for (RatingScoreView scoreView : tmp) {
				Long optionKey = scoreView.getOptionId();
				if (scoreView.getVoterPositionId().equals(UserPositionType.TEAM_MATE.getValue())) {//组远
					score.put("source", "本组");
				}
				if (duplicate) {
					score.put("option_" + optionKey, 
							String.valueOf((scoreView.getScore() + Float.parseFloat(exist.get("option_" + optionKey)))));
				}else {
					score.put("option_" + optionKey, scoreView.getScore().toString());
				}
			}
			rstMap.add(score);
		}
	}
	
	private String formatScore(Float score, Config config) {
		if (null == config) {
			return score.toString();
		}
		Integer format = Integer.parseInt(config.getPropertyValue());
		Float finalScore = (float)(Math.round(score*format))/format;
		return finalScore.toString();
	}

	/********** 统计时，A、B，C、D一起统计 ******/
	@Override
	public RptVo generateReport(String tmplIds) {
		List<RatingTemplate> tmpls = new ArrayList<RatingTemplate>();
		String[] ids = tmplIds.split(",");
		for (String id : ids) {
			if (StringUtils.isNotEmpty(id)) {
				tmpls.add(this.templateLogic.findOne(Long.parseLong(id)));
			}
		}
		RptVo vo = new RptVo();
		Rating rating  = this.ratingLogic.findOne(tmpls.get(0).getRatingId());
		vo.setName(rating.getName());
		vo.setFrom(rating.getEffFrom());
		vo.setTo(rating.getEffTo());
		vo.setStatus(rating.getStatus().toString());
		this.getRptScores(vo, tmpls);
		return vo;
	}
	
	private void getRptScores(RptVo vo,List<RatingTemplate> templates){
		for (RatingTemplate template : templates) {
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
	}

	@Override
	public List<Map<String, String>> userDetailsByRatingId(Long ratingId,Long user) {
		List<Map<String, String>> rstMap = new ArrayList<Map<String,String>>();
		List<RatingTemplate> tmpls = new ArrayList<RatingTemplate>();
		List<String> codes = RatingSuppTmplScoerUtil.getTmplCodeByUser(this.userLogic.findOne(user));
		for (String code : codes) {
			tmpls.add(this.templateLogic.findByRatingIdAndCode(ratingId, code));
		}
		for (RatingTemplate tmpl : tmpls) {
			Long tmplId = tmpl.getId();
			RatingTemplate template = this.templateLogic.findOne(tmplId);
			List<RatingScoreView> scoreViews = this.scoreViewLogic.findByTmplIdAndScorer(tmplId, user);
			String code = template.getCode();
			Map<Long, List<RatingScoreView>> scoreViewMap = new HashMap<Long, List<RatingScoreView>>();
			//1. 根据投票用户分离
			for (RatingScoreView scoreView : scoreViews) {
				List<RatingScoreView> tmp = null;
				if (scoreViewMap.containsKey(scoreView.getVoterId())) {
					tmp = scoreViewMap.get(scoreView.getVoterId());
				}else {
					tmp = new ArrayList<RatingScoreView>();
				}
				tmp.add(scoreView);
				scoreViewMap.put(scoreView.getVoterId(), tmp);
			}
			
			if (code.equals("A")) {//A卷
				this.userDetailA(rstMap, scoreViewMap, template);
			}else if (code.equals("B")) {//B卷
				this.userDetailB(rstMap, scoreViewMap, template);
			}else if (code.equals("C")) {//C卷
				this.userDetailC(rstMap, scoreViewMap, template);
			}else if (code.equals("D")) {//D卷
				this.userDetailD(rstMap, scoreViewMap, template);
			}
		}
		
		
		return rstMap;
	}

	@Override
	public int[] generateChart(Long ratingId) {
		List<RatingTemplate> templates = this.templateLogic.findByRatingId(ratingId);
		StringBuffer ab = new StringBuffer();
		StringBuffer cd = new StringBuffer();
		for (RatingTemplate template : templates) {
			if (template.getCode().equals("A")) {
				ab.append(template.getId())
				.append(",");
			}else if (template.getCode().equals("B")) {
				ab.append(template.getId())
				.append(",");
			}else if (template.getCode().equals("C")) {
				cd.append(template.getId())
				.append(",");
			}else if (template.getCode().equals("D")) {
				cd.append(template.getId())
				.append(",");
			}
		}
		RptVo abRpt = this.generateReport(ab.toString());
		RptVo cdRpt = this.generateReport(cd.toString());
		int[] data = new int[]{0,0,0,0,0,0,0,0,0,0};
		Config config = this.configLogic.findByProperty(ConfigConstant.STATISTICS_FORMAT);
		for (Map<String, String> score : abRpt.getScores()) {
			Float total = Float.parseFloat(score.get("total"));
			int index = this.getIndex(total, config);
			int tmp = data[index];
			tmp ++;
			data[index] = tmp;
		}
		for (Map<String, String> score : cdRpt.getScores()) {
			Float total = Float.parseFloat(score.get("total"));
			int index = this.getIndex(total, config);
			int tmp = data[index];
			tmp ++;
			data[index] = tmp;
		}
		return data;
	}
	
	private int getIndex(Float total, Config config) {
		Integer format = Integer.parseInt(config.getPropertyValue());
		int value = (int)(format * total);
		int percent = 5 * format / 10;
		int index = value / percent;
		return index == 10 ? 9 : index;
	}
}
