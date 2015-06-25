package com.chinesedreamer.rating.rating.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.rating.logic.RatingLogic;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.model.RatingStatus;
import com.chinesedreamer.rating.rating.vo.RatingCreateVo;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateLogic;
import com.chinesedreamer.rating.template.logic.RatingTemplateLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplate;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:42:27 AM
 * @version beta
 */
@Service
public class RatingServiceImpl implements RatingService{
	@Resource
	private RatingLogic logic;
	@Resource
	private RatingTemplateLogic templateLogic;
	@Resource
	private RatingSuppTemplateLogic suppTemplateLogic;
	
	@Override
	public void saveRating(RatingCreateVo vo) {
		//1. 保存投票事件信息
		Rating rating = new Rating();
		rating.setName(vo.getName());
		rating.setStatus(RatingStatus.ACTIVE);
		rating.setEffFrom(vo.getEffFrom());
		rating.setEffTo(vo.getEffTo());
		rating = this.logic.save(rating);
		
		//2. 保存模板
		this.templateLogic.copySuppTemplateToRating(rating, vo.getTemplateIds());
	}
	@Override
	public List<Rating> findAll() {
		return this.logic.findAll();
	}
	@Override
	public List<SelectVo> getAllTemplates() {
		List<SelectVo> vos = new ArrayList<SelectVo>();
		List<RatingSuppTemplate> suppTemplates = this.suppTemplateLogic.findAll();
		for (RatingSuppTemplate suppTemplate : suppTemplates) {
			vos.add(new SelectVo(suppTemplate.getId().toString(), suppTemplate.getName()));
		}
		return vos;
	}

}
