package com.chinesedreamer.rating.base.jpa.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract  class BaseEntity<ID extends Serializable> extends AbstractEntity<ID>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 361549063336380570L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected ID id;
	
	@Override
	public ID getId() {
		return id;
	}
	@Override
	public void setId(ID id) {
		this.id = id;
	}

	
}
