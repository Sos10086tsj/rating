package com.chinesedreamer.rating.rating.service;

import java.util.List;
import java.util.Map;

import com.chinesedreamer.rating.common.vo.OptionTitle;
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
	public List<OptionTitle> getTmplOptions(Long tmplId);
	
	/**
	 * 获取用户投票信息
	 * @param tmplId
	 * @param user
	 * @return
	 */
	public Map<String, Object> getUserRatingVote(Long tmplId,User user);
	
	/**
	 * 获取得分选项
	 * @return
	 */
	public List<SelectVo> getAllScores();
	
	/**
	 * 提交投票
	 * @param datasource
	 * @param tmplId
	 * @param user
	 */
	public void submitVote(String datasource, Long tmplId, User user);
}
