package com.chinesedreamer.rating.rating.vo;

import lombok.Getter;
import lombok.Setter;

/** 
 * Description: 投票页vo
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月25日 下午8:39:20 
 * Copyright:   Copyright (c)2015
 */
public @Getter @Setter class RatingPageVo {
	private Long ratingId;//投票时间id
	private String ratingName;//投票时间名称
	private Long tmplId;//模板id
	private String tmplName;//模板名称
}
