package com.chinesedreamer.rating.rating.vo;

import java.util.Date;
import java.util.List;

import com.chinesedreamer.rating.common.vo.SelectVo;

import lombok.Getter;
import lombok.Setter;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月12日 上午9:19:13 
 * Copyright:   Copyright (c)2015
 */
public @Getter @Setter class RatingVo {
	private Long id;//rating id
	private String name;
	private Date effFrom;
	private Date effTo;
	private List<SelectVo> templates;
}
