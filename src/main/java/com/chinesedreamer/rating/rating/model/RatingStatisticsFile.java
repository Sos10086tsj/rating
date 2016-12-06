package com.chinesedreamer.rating.rating.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.rating.attachment.model.Attachment;
import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;

/**
 * Description:
 * Auth:Paris
 * Date:Dec 5, 2016
**/
@Entity
@Table(name="rating_statistics_file")
public class RatingStatisticsFile extends BaseVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8225273171724612427L;

	@Column(name="tmpl_ids")
	private String tmplIds;
	
	@Column(name="statistics_date")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date statisticsDate;
	
	@Column(name="attach_id")
	private Long attachId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="attach_id",referencedColumnName="id",updatable=false,insertable =false)
	private Attachment attachment;

	public String getTmplIds() {
		return tmplIds;
	}

	public Date getStatisticsDate() {
		return statisticsDate;
	}

	public Long getAttachId() {
		return attachId;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setTmplIds(String tmplIds) {
		this.tmplIds = tmplIds;
	}

	public void setStatisticsDate(Date statisticsDate) {
		this.statisticsDate = statisticsDate;
	}

	public void setAttachId(Long attachId) {
		this.attachId = attachId;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	
	
}
