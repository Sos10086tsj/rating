package com.chinesedreamer.rating.rating.service;

import java.util.List;

import com.chinesedreamer.rating.rating.model.Rating;
import com.chinesedreamer.rating.rating.vo.RatingCreateVo;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:39:50 AM
 * @version beta
 */
public interface RatingService {
	/**
	 * 鍒涘缓涓�鎶曠エ浜嬩欢
	 * @param vo
	 */
	public void saveRating(RatingCreateVo vo);
	
	public List<Rating> findAll();
}
