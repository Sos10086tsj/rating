package com.chinesedreamer.rating.system.config.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 
 * @author Paris
 * @date Jul 6, 20154:22:29 PM
 * @version beta
 */
@Entity
@Table(name = "config")
public @Getter @Setter class Config extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7084549355774697667L;

	@Column
	private String property;
	
	@Column(name = "property_value")
	private String propertyValue;
}
