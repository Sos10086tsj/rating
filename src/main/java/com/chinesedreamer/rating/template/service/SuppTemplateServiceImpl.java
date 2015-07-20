package com.chinesedreamer.rating.template.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateLogic;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateOptionMappingLogic;
import com.chinesedreamer.rating.template.logic.RatingSuppTmplOptionWeightLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplate;
import com.chinesedreamer.rating.template.model.RatingSuppTemplateOptionMapping;
import com.chinesedreamer.rating.template.model.RatingSuppTmplOptionWeight;
import com.chinesedreamer.rating.template.vo.OptionVo;

/**
 * Description: 
 * @author Paris
 * @date Jul 20, 20158:34:32 AM
 * @version beta
 */
@Service
public class SuppTemplateServiceImpl implements SuppTemplateService{
	@Resource
	private RatingSuppTemplateLogic logic;
	@Resource
	private RatingSuppTemplateOptionMappingLogic suppTemplateOptionMappingLogic;
	@Resource
	private RatingSuppTmplOptionWeightLogic suppTmplOptionWeightLogic;
	@Override
	public List<RatingSuppTemplate> getAll() {
		return this.logic.findAll();
	}
	@Override
	public List<OptionVo> getTmplOptions(Long tmplId) {
		List<OptionVo> vos = new ArrayList<OptionVo>();
		List<RatingSuppTemplateOptionMapping> suppTemplateOptionMappings = this.suppTemplateOptionMappingLogic.findBySuppTmplId(tmplId);
		for (RatingSuppTemplateOptionMapping suppTemplateOptionMapping : suppTemplateOptionMappings) {
			OptionVo vo = new OptionVo();
			vo.setId(suppTemplateOptionMapping.getOptionId());
			vo.setName(suppTemplateOptionMapping.getOption().getName());
			vo.setCategoryCode(suppTemplateOptionMapping.getOption().getCategory().getCode());
			vo.setCategoryName(suppTemplateOptionMapping.getOption().getCategory().getLabel());
			RatingSuppTmplOptionWeight weight = this.suppTmplOptionWeightLogic.findBySuppTmplIdAndSuppOptionId(tmplId, suppTemplateOptionMapping.getOptionId());
			vo.setWeight(weight.getWeight());
			vos.add(vo);
		}
		return vos;
	}
	@Override
	public void updateSuppTmpl(Long tmplId, String options) {
		//1. delete exist supp template options
		List<RatingSuppTemplateOptionMapping> optionMappings = this.suppTemplateOptionMappingLogic.findBySuppTmplId(tmplId);
		for (RatingSuppTemplateOptionMapping optionMapping : optionMappings) {
			RatingSuppTmplOptionWeight weight = this.suppTmplOptionWeightLogic.findBySuppTmplIdAndSuppOptionId(tmplId, optionMapping.getOptionId());
			this.suppTmplOptionWeightLogic.delete(weight);
			this.suppTemplateOptionMappingLogic.delete(optionMapping);
		}
		//2. save new options
		JSONArray jsonArray = JSONArray.parseArray(options);
		for (Object object : jsonArray) {
			JSONObject option = (JSONObject)object;
			RatingSuppTemplateOptionMapping optionMapping = new RatingSuppTemplateOptionMapping();
			Long optionId = option.getLong("id");
			optionMapping.setOptionId(optionId);
			optionMapping.setSuppTmplId(tmplId);
			optionMapping = this.suppTemplateOptionMappingLogic.save(optionMapping);
			
			RatingSuppTmplOptionWeight weight = new RatingSuppTmplOptionWeight();
			weight.setSuppOptionId(optionId);
			weight.setSuppTmplId(tmplId);
			weight.setWeight(option.getBigDecimal("weight"));
			this.suppTmplOptionWeightLogic.save(weight);
		}
	}

}
