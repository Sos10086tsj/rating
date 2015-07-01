package com.chinesedreamer.rating.rating.vo.rpt;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jul 1, 201510:00:26 AM
 * @version beta
 */
public @Getter @Setter class RptScore {
	//得分人信息
	private String name;//得分人姓名
	private Long groupId;//得分人所在组
	private Integer positionId;//得分人所在职位
	private Map<String, Integer> scores;//得分
}
