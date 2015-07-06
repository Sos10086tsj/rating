package com.chinesedreamer.rating.system.config.logic;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.system.config.model.Config;

/**
 * Description: 
 * @author Paris
 * @date Jul 6, 20154:24:36 PM
 * @version beta
 */
public interface ConfigLogic extends BaseLogic<Config, Long>{
	public Config findByProperty(String property);
}
