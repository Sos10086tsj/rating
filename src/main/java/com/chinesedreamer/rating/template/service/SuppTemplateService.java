package com.chinesedreamer.rating.template.service;

import java.math.BigDecimal;
import java.util.List;

import com.chinesedreamer.rating.template.model.RatingSuppTemplate;
import com.chinesedreamer.rating.template.vo.OptionVo;

/**
 * Description: 
 * @author Paris
 * @date Jul 20, 20158:34:21 AM
 * @version beta
 */
public interface SuppTemplateService {
	public List<RatingSuppTemplate> getAll();
	/**
	 * 获取指定模板已配置的得分项
	 * @param tmplId
	 * @return
	 */
	public List<OptionVo> getTmplOptions(Long tmplId);
	
	public void updateSuppTmpl(Long tmplId, String options);
	
	/**
	 * supp template 添加得分项
	 * @param tmplId
	 * @param optionId
	 * @param weight
	 */
	public void addOption2SuppTmpl(Long tmplId, Long optionId, BigDecimal weight);
}
