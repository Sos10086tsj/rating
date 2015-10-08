package com.chinesedreamer.rating.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;

/**
 * Description: 评分模板，beta1.0不开发维护，创建默认的A、B、C、D模板
 * @author Paris
 * @date Jun 23, 201510:47:44 AM
 * @version beta
 */
@Entity
@Table(name = "rating_supp_template")
public class RatingSuppTemplate extends BaseVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5217988245551811317L;
	@Column
	private String name;//卷名
	
	@Column
	private String code;

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
