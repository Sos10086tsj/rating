package com.chinesedreamer.rating.system.config.repository;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.system.config.model.Config;

/**
 * Description: 
 * @author Paris
 * @date Jul 6, 20154:23:49 PM
 * @version beta
 */
public interface ConfigRepository extends BaseRepository<Config, Long>{
	public Config findByProperty(String property);
}
