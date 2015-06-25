package com.chinesedreamer.rating.rating.vo;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 用户查看投票列表
 * @author Paris
 * @date Jun 25, 201510:44:51 AM
 * @version beta
 */
public @Getter @Setter class RatingUserVo {
	private Long id;//投票id
	private String name;//投票事件
	private Date effFrom;//起始时间
	private Date effTo;//结束时间
	private List<RatingTemplateVo> templates;
}
