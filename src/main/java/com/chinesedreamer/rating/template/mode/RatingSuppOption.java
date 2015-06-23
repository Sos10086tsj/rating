package com.chinesedreamer.rating.template.mode;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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
public @Getter @Setter class RatingSuppOption extends BaseVersionEntity<Long>{

	@Column
	private String code = UUID.randomUUID().toString();//随机生成，唯一值
	
	@Column
	private String name;
	
	@Column(name = "category")
	@Enumerated(EnumType.STRING)
	private OptionCategory category;
	
	@Column
	private BigDecimal seq;
}
