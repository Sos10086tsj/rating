package com.chinesedreamer.rating.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 模板（比如A卷）指定的参与投票的组或者职位
 * @author Paris
 * @date Jun 23, 201511:23:25 AM
 * @version beta
 */
@Entity
@Table(name = "rating_template_voter")
public class RatingTemplateVoter extends BaseEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7962008253084373636L;

	@Column(name = "tmpl_id")
	private Long tmplId;
	
	@Column(name = "group_id")
	private Long groupId;//组织id
	
	@Column(name = "position_id")
	private Integer positionId;

	public Long getTmplId() {
		return tmplId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setTmplId(Long tmplId) {
		this.tmplId = tmplId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	
	
}
