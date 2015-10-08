package com.chinesedreamer.rating.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:12:48 AM
 * @version beta
 */
@Entity
@Table(name = "rating_supp_tmpl_option")
public class RatingSuppTemplateOptionMapping extends BaseEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1037652902932046004L;

	@Column(name = "supp_tmpl_id")
	private Long suppTmplId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supp_tmpl_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RatingSuppTemplate suppTemplate;
	
	@Column(name = "option_id")
	private Long optionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RatingSuppOption option;
	
	@Column
	private Float seq;

	public Long getSuppTmplId() {
		return suppTmplId;
	}

	public RatingSuppTemplate getSuppTemplate() {
		return suppTemplate;
	}

	public Long getOptionId() {
		return optionId;
	}

	public RatingSuppOption getOption() {
		return option;
	}

	public Float getSeq() {
		return seq;
	}

	public void setSuppTmplId(Long suppTmplId) {
		this.suppTmplId = suppTmplId;
	}

	public void setSuppTemplate(RatingSuppTemplate suppTemplate) {
		this.suppTemplate = suppTemplate;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public void setOption(RatingSuppOption option) {
		this.option = option;
	}

	public void setSeq(Float seq) {
		this.seq = seq;
	}
	
	
}
