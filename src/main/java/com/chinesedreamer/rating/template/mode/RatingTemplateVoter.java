package com.chinesedreamer.rating.template.mode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;
import com.chinesedreamer.rating.system.user.UserPositionType;

/**
 * Description: 模板（比如A卷）指定的参与投票的组或者职位
 * @author Paris
 * @date Jun 23, 201511:23:25 AM
 * @version beta
 */
@Entity
@Table(name = "rating_template_voter")
public @Getter @Setter class RatingTemplateVoter extends BaseEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7962008253084373636L;

	@Column(name = "tmpl_id")
	private Long tmplId;
	
	@Column(name = "group_id")
	private Long groupId;//组织id
	
	@Column(name = "position_id")
	@Enumerated(EnumType.ORDINAL)
	private UserPositionType position;
}
