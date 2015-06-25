package com.chinesedreamer.rating.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 模板（比如A卷）指定的参与投票的组或者职位
 * @author Paris
 * @date Jun 23, 201511:23:25 AM
 * @version beta
 */
@Entity
@Table(name = "rating_supp_template_voter")
public @Getter @Setter class RatingSuppTemplateVoter extends BaseEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8298698857398247304L;

	@Column(name = "supp_tmpl_id")
	private Long suppTmplId;
	
	@Column(name = "group_id")
	private Long groupId;//组织id
	
	@Column(name = "position_id")
	private Integer positionId;
}
