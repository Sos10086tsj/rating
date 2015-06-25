package com.chinesedreamer.rating.rating.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.rating.logic.RatingLogic;
import com.chinesedreamer.rating.rating.logic.RatingUserVoteLogic;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.model.RatingStatus;
import com.chinesedreamer.rating.rating.vo.RatingCreateVo;
import com.chinesedreamer.rating.rating.vo.RatingPageVo;
import com.chinesedreamer.rating.rating.vo.RatingTemplateVo;
import com.chinesedreamer.rating.rating.vo.RatingUserVo;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.template.logic.RatingSuppTemplateLogic;
import com.chinesedreamer.rating.template.logic.RatingTemplateLogic;
import com.chinesedreamer.rating.template.logic.RatingTemplateOptionMappingLogic;
import com.chinesedreamer.rating.template.logic.RatingTemplateVoterLogic;
import com.chinesedreamer.rating.template.model.RatingSuppTemplate;
import com.chinesedreamer.rating.template.model.RatingTemplate;
import com.chinesedreamer.rating.template.model.RatingTemplateOptionMapping;
import com.chinesedreamer.rating.template.model.RatingTemplateVoter;

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
	@Resource
	private RatingTemplateVoterLogic templateVoterLogic;
	@Resource
	private RatingUserVoteLogic ratingUserVoteLogic;
	@Resource
	private RatingTemplateOptionMappingLogic templateOptionMappingLogic;
	
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
	@Override
	public List<RatingUserVo> getRatingUserVos(User user) {
		List<RatingUserVo> vos = new ArrayList<RatingUserVo>();
		List<Rating> ratings = this.logic.findAll();
		for (Rating rating : ratings) {
			vos.add(this.getRatingUserVo(user, rating));
		}
		return vos;
	}

	/**
	 * 封装用户查看的投票vo
	 * @param user
	 * @param rating
	 * @return
	 */
	private RatingUserVo getRatingUserVo(User user, Rating rating){
		RatingUserVo vo = new RatingUserVo();
		vo.setId(rating.getId());
		vo.setName(rating.getName());
		vo.setEffFrom(rating.getEffFrom());
		vo.setEffTo(rating.getEffTo());
		List<RatingTemplateVo> templateVos = new ArrayList<RatingTemplateVo>();
		//获取所有模板 RatingTemplateVo
		List<RatingTemplate> templates = this.templateLogic.findByRatingId(rating.getId());
		for (RatingTemplate template : templates) {
			List<RatingTemplateVoter> votes = this.templateVoterLogic.findByTmplIdAndGroupIdAndPositionId(template.getId(), user.getGroupId(), user.getPositionId());//非总体组可以查到对应的数据
			if (null == votes || votes.isEmpty()) {
				votes = this.templateVoterLogic.findByTmplIdAndGroupId(template.getId(), user.getGroupId());//总体组可以查到
			}
			if (null != votes && !votes.isEmpty()) {
				for (RatingTemplateVoter vote : votes) {//找到A、D或者B、C
					RatingTemplateVo rtVo = new RatingTemplateVo();
					rtVo.setId(vote.getTmplId());
					rtVo.setName(template.getName());
					rtVo.setVoted((null == this.ratingUserVoteLogic.findByRatingIdAndTmplIdAndUserId(rating.getId(), template.getId(), user.getId())) ? Boolean.FALSE : Boolean.TRUE);
					templateVos.add(rtVo);
				}
			}
		}
		vo.setTemplates(templateVos);
		return vo;
	}
	@Override
	public RatingPageVo getRatingVotePage(Long tmplId) {
		RatingPageVo vo = new RatingPageVo();
		RatingTemplate rt = this.templateLogic.findOne(tmplId);
		vo.setTmplId(tmplId);
		vo.setTmplName(rt.getName());
		vo.setRatingId(rt.getRatingId());
		Rating r = this.logic.findOne(rt.getRatingId());
		vo.setRatingName(r.getName());
		return vo;
	}
	@Override
	public List<SelectVo> getTmplOptions(Long tmplId) {
		List<SelectVo> vos = new ArrayList<SelectVo>();
		List<RatingTemplateOptionMapping> options = this.templateOptionMappingLogic.findByTmplId(tmplId);
		for (RatingTemplateOptionMapping option : options) {
			vos.add(new SelectVo(option.getOptionId().toString(), option.getOption().getName()));
		}
		return vos;
	}
}
