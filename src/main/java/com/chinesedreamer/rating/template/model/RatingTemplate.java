package com.chinesedreamer.rating.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 浠巗upp 妯℃澘涓璫opy鍑烘潵鐨勭‘瀹氫娇鐢ㄧ殑妯℃澘锛屼竴鏃︾敓鎴愬悗锛屼笉鍙彉鍔� * @author Paris
 * @date Jun 23, 201510:47:44 AM
 * @version beta
 */
@Entity
@Table(name = "rating_template")
public class RatingTemplate extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8320258747685521287L;

	@Column
	private String name;//鍗峰悕
	
	@Column(name = "rating_id")//鍏宠仈鐨勬姇绁ㄤ簨椤�	
	private Long ratingId;
	
	@Column
	private String code;

	public String getName() {
		return name;
	}

	public Long getRatingId() {
		return ratingId;
	}

	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
