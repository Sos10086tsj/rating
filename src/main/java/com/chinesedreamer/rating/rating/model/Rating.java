package com.chinesedreamer.rating.rating.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;

/**
 * Description: 管理员创建的投票事件
 * @author Paris
 * @date Jun 23, 201511:35:39 AM
 * @version beta
 */
@Entity
@Table(name = "rating")
public class Rating extends BaseVersionEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2814656939075090844L;

	@Column
	private String name;
	
	@Column(name = "eff_from")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date effFrom;
	
	@Column(name = "eff_to")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date effTo;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private RatingStatus status;

	public String getName() {
		return name;
	}

	public Date getEffFrom() {
		return effFrom;
	}

	public Date getEffTo() {
		return effTo;
	}

	public RatingStatus getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}

	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}

	public void setStatus(RatingStatus status) {
		this.status = status;
	}
	
	
}
