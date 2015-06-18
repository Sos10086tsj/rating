package com.chinesedreamer.rating.base.jpa.logic;

import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.chinesedreamer.rating.base.jpa.model.AbstractEntity;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20158:12:22 AM
 * @version beta
 */
public interface BaseLogic<M extends AbstractEntity<?>, ID extends Serializable> {
	/**
	 * 保存单个实体
	 * @param m 实体
	 * @return 返回保存的实体
	 */
	public M save(M m);

	/**
	 * 更新单个实体
	 * @param m 实体
	 * @return 返回更新的实体
	 */
	public M update(M m);

	/**
	 * 根据主键删除相应实体
	 * @param id 主键
	 */
	public void delete(ID id);

	/**
	 * 删除实体
	 * @param m 实体
	 */
	public void delete(M m);

	/**
	 * 按照主键查询
	 * @param id 主键
	 * @return 返回id对应的实体
	 */
	public M findOne(ID id);

	/**
	 * 实体是否存在
	 * @param id 主键
	 * @return 存在 返回true，否则false
	 */
	public boolean exists(ID id);

	/**
	 * 统计实体总数
	 * @return 实体总数
	 */
	public long count();

	/**
	 * @param entity
	 * @param root
	 * @param cb
	 * @return
	 */
	public Specification<M> getQuerySpecification(M entity, Root<M> root, CriteriaBuilder cb);
}
