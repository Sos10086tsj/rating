package com.chinesedreamer.rating.system.security.service;

import java.io.File;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinesedreamer.rating.common.io.PropertiesUtils;
import com.chinesedreamer.rating.common.utils.EncryptionUtil;
import com.chinesedreamer.rating.common.utils.IpUtil;
import com.chinesedreamer.rating.common.utils.LicenseUtil;
import com.chinesedreamer.rating.common.utils.ThreadUtils;
import com.chinesedreamer.rating.system.config.ConfigConstant;
import com.chinesedreamer.rating.system.config.logic.ConfigLogic;
import com.chinesedreamer.rating.system.config.model.Config;
import com.chinesedreamer.rating.system.security.constant.ActivationState;
import com.chinesedreamer.rating.system.security.constant.License;
import com.chinesedreamer.rating.system.security.runner.LicenseRunner;
import com.chinesedreamer.rating.system.security.vo.LisenceVo;

@Service
public class SecurityServiceImpl implements SecurityService{
	private Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
	
	@Resource
	private ConfigLogic logic;

	@Override
	public LisenceVo isSystemAuthorised() {
		LisenceVo vo = new LisenceVo();
		try {
			//检查是否激活
			String mac = IpUtil.getLocalMac(IpUtil.getLocalhost());
			String licensePath = LicenseUtil.getLicensePath();
			File license = new File(licensePath);
			String activationJsonStr = EncryptionUtil.decrypt(license, 
					EncryptionUtil.md5L32(mac).substring(0, 16)
					+ EncryptionUtil.md5L32(mac).substring(8, 24)
					+ EncryptionUtil.md5L32(mac).substring(16, 32)
					);
			if (null != activationJsonStr) {
				JSONObject activationJson = JSON.parseObject(activationJsonStr);
				Integer status = activationJson.getInteger("status");
				if (status == ActivationState.ACTIVE.getValue()) {
					vo.setState(ActivationState.ACTIVE);
					return vo;
				}
			}else{//检查是否在试用期
				PropertiesUtils propertiesUtils = new PropertiesUtils("probation.properties");
				String probation = propertiesUtils.getProperty("probation");
				activationJsonStr = EncryptionUtil.decrypt(license, 
						EncryptionUtil.md5L32(probation).substring(0, 16)
						+ EncryptionUtil.md5L32(probation).substring(8, 24)
						+ EncryptionUtil.md5L32(probation).substring(16, 32)
						);
				if (null != activationJsonStr){
					JSONObject activationJson = JSON.parseObject(activationJsonStr);
					Integer status = activationJson.getInteger("status");
					if (status == ActivationState.PROBATION.getValue()) {
						int remaining = this.isProbation();
						if (remaining > 0) {
							if (!License.PROBATION_RUNNING) {
								License.PROBATION_RUNNING = true;
								LicenseRunner runner = new LicenseRunner();
								Thread thread = new Thread(runner, "license.runner");
								thread.start();
							}
							vo.setState(ActivationState.PROBATION);
							int days = remaining / 60 / 60 / 24;
							if (days * 60 *60 *24 < remaining) {
								days ++;
							}
							vo.setRemaingDay(days);
						}else {
							vo.setState(ActivationState.PROBATION_OVERDUE);
						}
						return vo;
					}
				}
			}
		} catch (Exception e) {
			this.logger.error("{}",e);
		}
		
		vo.setState(ActivationState.REJECT);
		return vo;
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

	/**
	 * 检查适用是否过期
	 * @return
	 */
	private int isProbation() {
		try {
			PropertiesUtils propertiesUtils = new PropertiesUtils("probation.properties");
			String probation = propertiesUtils.getProperty("probation");
			
			String licensePath = LicenseUtil.getLicensePath();
			File license = new File(licensePath);

			
			String availableStr = EncryptionUtil.decrypt(license, 
					EncryptionUtil.md5L32(probation).substring(0, 16)
					+ EncryptionUtil.md5L32(probation).substring(8, 24)
					+ EncryptionUtil.md5L32(probation).substring(16, 32)
					);
			JSONObject availableJson = JSON.parseObject(availableStr);
			Integer available = availableJson.getInteger("remaining");
			
			String passStr = propertiesUtils.getProperty("probation.n");
			
			Integer pass = Integer.valueOf(passStr);
			
			if (pass < available) {
				return available - pass;
			}else {
				Thread thread = ThreadUtils.getThread("license.runner");
				if (null != thread) {
					thread.interrupt();
				}
			}
		} catch (Exception e) {
			this.logger.error("{}",e);
		}
		return -1;
	}
}
