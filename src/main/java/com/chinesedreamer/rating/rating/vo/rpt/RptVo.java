package com.chinesedreamer.rating.rating.vo.rpt;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jul 1, 201510:12:40 AM
 * @version beta
 */
public @Getter @Setter class RptVo {
	
	//投票事件信息
	private String name;//标题
	private Date from;//开始时间
	private Date to;//结束时间
	private String status;//状态
	private Integer voterNum;//参与投票的人
	
	private List<RptScore> scores;//统计列表
}
