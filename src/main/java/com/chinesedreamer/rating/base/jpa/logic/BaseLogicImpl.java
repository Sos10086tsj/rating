package com.chinesedreamer.rating.base.jpa.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.chinesedreamer.rating.base.jpa.model.AbstractEntity;
import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;

/**
 * Description: 
 * @author Paris
 * @date Mar 27, 20158:13:24 AM
 * @version beta
 */

public class BaseLogicImpl<M extends AbstractEntity<?>, ID extends Serializable> implements BaseLogic<M, ID> {
	protected BaseRepository<M, ID> BaseRepository;

	@Autowired
    public void setBaseRepository(BaseRepository<M, ID> baseRepository) {
        this.BaseRepository = baseRepository;
    }

    @Override
    public M save(M m) {
        return BaseRepository.save(m);
    }

    @Override
    public M update(M m) {
        return BaseRepository.save(m);
    }

    @Override
    public void delete(ID id) {
        BaseRepository.delete(id);
    }

    @Override
    public void delete(M m) {
        BaseRepository.delete(m);
    }

    @Override
    public M findOne(ID id) {
        return BaseRepository.findOne(id);
    }

    @Override
    public boolean exists(ID id) {
        return BaseRepository.exists(id);
    }

    @Override
    public long count() {
        return BaseRepository.count();
    }

	@Override
	public Specification<M> getQuerySpecification(M entity, Root<M> root,CriteriaBuilder cb) {
		Specification<M> condition = new Specification<M>() {
			@Override
			public Predicate toPredicate(Root<M> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> pds = new ArrayList<Predicate>();
				return cb.and(pds.toArray(new Predicate[] {}));
			}
		};
		return condition;
	}
}
