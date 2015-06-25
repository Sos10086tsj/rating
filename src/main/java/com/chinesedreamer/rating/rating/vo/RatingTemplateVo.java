package com.chinesedreamer.rating.rating.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jun 25, 201510:51:23 AM
 * @version beta
 */
public @Getter @Setter class RatingTemplateVo {
	private Long id;
	private String name;
	private Boolean voted;
}
