package com.chinesedreamer.rating.rating.vo.rpt;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: 
 * @author Paris
 * @date Jul 1, 201510:12:40 AM
 * @version beta
 */
public class RptVo {
	
	//投票事件信息
	private String name;//标题
	private Date from;//开始时间
	private Date to;//结束时间
	private String status;//状态
	private Integer voterNum;//参与投票的人
	
	private List<Map<String, String>> scores;//统计列表

	public String getName() {
		return name;
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

	public String getStatus() {
		return status;
	}

	public Integer getVoterNum() {
		return voterNum;
	}

	public List<Map<String, String>> getScores() {
		return scores;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setVoterNum(Integer voterNum) {
		this.voterNum = voterNum;
	}

	public void setScores(List<Map<String, String>> scores) {
		this.scores = scores;
	}
	
	
}
