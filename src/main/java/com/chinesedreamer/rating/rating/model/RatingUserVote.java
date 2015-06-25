package com.chinesedreamer.rating.rating.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;
import com.chinesedreamer.rating.system.group.model.UserGroup;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.template.model.RatingTemplate;

/**
 * Description: 用户投票记录表
 * @author Paris
 * @date Jun 25, 201511:12:17 AM
 * @version beta
 */
@Entity
@Table(name = "rating_user_vote")
public @Getter @Setter class RatingUserVote extends BaseVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3688299357277903955L;

	@Column(name = "rating_id")
	private Long ratingId;//投票事件的ID
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rating_id", referencedColumnName = "id", insertable = false, updatable =false)
	private Rating rating;
	
	@Column(name = "tmpl_id")
	private Long tmplId;//模板id
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tmpl_id", referencedColumnName = "id", insertable = false, updatable =false)
	private RatingTemplate template;
	
	@Column(name = "user_id")
	private Long userId;//投票用户id
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable =false)
	private User user;
	
	@Column(name = "group_id")
	private Long groupId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", referencedColumnName = "id", insertable = false, updatable =false)
	private UserGroup userGroup;
	
	@Column(name = "position_id")
	private Integer positionId;
	
	@Column(name = "vote_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date voteDate;
}
