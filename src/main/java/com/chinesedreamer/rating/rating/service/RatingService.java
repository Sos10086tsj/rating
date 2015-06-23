package com.chinesedreamer.rating.rating.service;

import com.chinesedreamer.rating.rating.vo.RatingCreateVo;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:39:50 AM
 * @version beta
 */
public interface RatingService {
	/**
	 * 创建一次投票事件
	 * @param vo
	 */
	public void saveRating(RatingCreateVo vo);
}
