package com.chinesedreamer.rating.system.security.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.common.utils.EncryptionUtil;
import com.chinesedreamer.rating.common.utils.IpUtil;
import com.chinesedreamer.rating.system.config.ConfigConstant;
import com.chinesedreamer.rating.system.config.logic.ConfigLogic;
import com.chinesedreamer.rating.system.config.model.Config;

@Service
public class SecurityServiceImpl implements SecurityService{
	private Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
	
	@Resource
	private ConfigLogic logic;

	@Override
	public boolean isSystemAuthorised() {
		Config config = this.logic.findByProperty(ConfigConstant.AUTHORISE_MAC);
		Config salt = this.logic.findByProperty(ConfigConstant.AUTHORISE_MAC_SALT);
		
		if (null == config) {//生成MAC
			config = new Config();
			config.setProperty(ConfigConstant.AUTHORISE_MAC);
			try {
				config.setPropertyValue(IpUtil.getLocalMac(IpUtil.getLocalhost()));
				this.logic.save(config);
			} catch (Exception e) {
				logger.error("",e);
			}
			return false;
		}
		Config passConfig = this.logic.findByProperty(ConfigConstant.AUTHORISE_MAC_PASS);
		if (null == passConfig) {
			return false;
		}
		String mac = config.getPropertyValue().toLowerCase();
		String pass = passConfig.getPropertyValue();
		if (EncryptionUtil.md5L32(mac + salt.getPropertyValue()).equals(pass)) {
			return true;
		}
		return false;
	}

	@Override
	public String encryptAuthorise(String mac) {
		return EncryptionUtil.md5L32(EncryptionUtil.md5L32(mac));
	}

	@Override
	public boolean authorise(String macPass) {
		Config config = this.logic.findByProperty(ConfigConstant.AUTHORISE_MAC);
		Config salt = this.logic.findByProperty(ConfigConstant.AUTHORISE_MAC_SALT);
		Config passConfig = this.logic.findByProperty(ConfigConstant.AUTHORISE_MAC_PASS);
		String mac = config.getPropertyValue().toLowerCase();
		if (EncryptionUtil.md5L32(mac + salt.getPropertyValue()).equals(macPass)) {
			if (null == passConfig) {
				passConfig = new Config();
				passConfig.setProperty(ConfigConstant.AUTHORISE_MAC_PASS);
			}
			passConfig.setPropertyValue(macPass);
			this.logic.save(passConfig);
			return true;
		}else {
			return false;
		}
	}

}
