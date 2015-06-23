package com.chinesedreamer.rating.template.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.template.mode.RatingSuppTempalteOptionMapping;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:18:55 AM
 * @version beta
 */
public interface RatingSuppTempalteOptionMappingRepository extends BaseRepository<RatingSuppTempalteOptionMapping, Long>{
	public List<RatingSuppTempalteOptionMapping> findBySuppTmplIdOrderBySqlAsc(Long suppTmplId);
}
