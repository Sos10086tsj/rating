package com.chinesedreamer.rating.rating.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 用户对是否参与某个投票
 * @author Paris
 * @date Jun 25, 201510:51:23 AM
 * @version beta
 */
public @Getter @Setter class RatingTemplateVo {
	private Long id;
	private String code;
	private String name;
	private Boolean voted;
}
