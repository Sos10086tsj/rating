package com.chinesedreamer.rating.template.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;
import com.chinesedreamer.rating.template.OptionCategory;

/**
 * Description: 得分项
 * @author Paris
 * @date Jun 23, 201510:34:07 AM
 * @version beta
 */
@Entity
@Table(name = "rating_supp_options")
public class RatingSuppOption extends BaseVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8054279250993946961L;

	@Column
	private String code = UUID.randomUUID().toString();//随机生成，唯一值
	
	@Column
	private String name;
	
	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	private OptionCategory category;
	
	@Column
	private Float seq;
	
	@Column(name = "deleted", columnDefinition="TINYINT(1)")
	private Boolean deleted = Boolean.FALSE;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public OptionCategory getCategory() {
		return category;
	}

	public Float getSeq() {
		return seq;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(OptionCategory category) {
		this.category = category;
	}

	public void setSeq(Float seq) {
		this.seq = seq;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
