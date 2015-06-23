package com.chinesedreamer.rating.rating.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:40:34 AM
 * @version beta
 */
public @Getter @Setter class RatingCreateVo {
	private Long id;
	private String name;
	private Long[] templateIds;
	private Date effFrom;
	private Date effTo;
}
