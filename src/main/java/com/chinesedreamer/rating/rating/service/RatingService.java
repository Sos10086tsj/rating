package com.chinesedreamer.rating.rating.service;

import java.util.List;

import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.vo.RatingCreateVo;
import com.chinesedreamer.rating.rating.vo.RatingPageVo;
import com.chinesedreamer.rating.rating.vo.RatingUserVo;
import com.chinesedreamer.rating.system.user.model.User;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:39:50 AM
 * @version beta
 */
public interface RatingService {
	/**
	 * 保存
	 * @param vo
	 */
	public void saveRating(RatingCreateVo vo);
	
	public List<Rating> findAll();
	
	/**
	 * 获取所有可用的模板，如A、B、C、D卷
	 * @return
	 */
	public List<SelectVo> getAllTemplates();
	
	/**
	 * 获取用户可见的投票列表
	 * @param user
	 * @return
	 */
	public List<RatingUserVo> getRatingUserVos(User user);
	
	/**
	 * 获取投票页
	 * @param tmplId
	 * @return
	 */
	public RatingPageVo getRatingVotePage(Long tmplId);
	
	/**
	 * 获取模板得分项
	 * @param tmplId
	 * @return
	 */
	public List<SelectVo> getTmplOptions(Long tmplId);
}
