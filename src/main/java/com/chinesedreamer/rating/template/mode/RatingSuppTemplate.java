package com.chinesedreamer.rating.template.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;

/**
 * Description: 评分模板，beta1.0不开发维护，创建默认的A、B、C、D模板
 * @author Paris
 * @date Jun 23, 201510:47:44 AM
 * @version beta
 */
@Entity
@Table(name = "rating_supp_template")
public @Getter @Setter class RatingSuppTemplate extends BaseVersionEntity<Long>{

	@Column
	private String name;//卷名
}
