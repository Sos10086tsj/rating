package com.chinesedreamer.rating.template.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 从supp 模板中copy出来的确定使用的模板，一旦生成后，不可变动
 * @author Paris
 * @date Jun 23, 201510:47:44 AM
 * @version beta
 */
@Entity
@Table(name = "rating_template")
public @Getter @Setter class RatingTemplate extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8320258747685521287L;

	@Column
	private String name;//卷名
	
	@Column(name = "rating_id")//关联的投票事项
	private Long ratingId;
}
