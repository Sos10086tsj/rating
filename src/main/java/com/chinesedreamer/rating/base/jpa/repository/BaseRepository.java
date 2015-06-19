package com.chinesedreamer.rating.base.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Description: 
 * @author Paris
 * @date Mar 26, 20155:13:10 PM
 * @version beta
 */
@NoRepositoryBean
public interface BaseRepository<M, ID extends Serializable> extends JpaRepository<M, ID>, JpaSpecificationExecutor<M> {

}
