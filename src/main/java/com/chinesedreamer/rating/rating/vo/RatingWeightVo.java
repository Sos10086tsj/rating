package com.chinesedreamer.rating.rating.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月12日 上午9:40:47 
 * Copyright:   Copyright (c)2015
 */
public @Getter @Setter class RatingWeightVo {
	private String categoryCode;
	private String category;
	private Long id;
	private String name;
	private BigDecimal weight;
}
