package com.chinesedreamer.rating.system.config.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.config.model.Config;
import com.chinesedreamer.rating.system.config.repository.ConfigRepository;

/**
 * Description: 
 * @author Paris
 * @date Jul 6, 20154:25:04 PM
 * @version beta
 */
@Service
public class ConfigLogicLogicImpl extends BaseLogicImpl<Config, Long> implements ConfigLogic{
	@Resource
	private ConfigRepository repository;
	@Override
	public Config findByProperty(String property) {
		return this.repository.findByProperty(property);
	}

}
