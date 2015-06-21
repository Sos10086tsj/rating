package com.chinesedreamer.rating.common.utils;

import java.security.MessageDigest;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class EncryptionUtil {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EncryptionUtil.class);
	
	public static String md5L32(String unencryptedText){
		if (StringUtils.isEmpty(unencryptedText)) {
			LOGGER.info("unencryptedText is null.");
			return unencryptedText;
		}
		
		String ciphertext = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(unencryptedText.getBytes("UTF-8"));
			StringBuffer buffer = new StringBuffer();
			for (byte b : bytes) {
				int bt = b & 0xff;
				if (bt < 16) {
					buffer.append(0);
				}
				buffer.append(Integer.toHexString(bt));
				ciphertext = buffer.toString();
			}
			LOGGER.info("encrypt string {} to {};",unencryptedText,ciphertext);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return ciphertext;
	}
	
	/**
	 * 加盐算法
	 * @return
	 */
	private final static String[] hexDigits = { "a", "b", "c", "d", "e", "f", "g", "h",  
        "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",  
        "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H",  
        "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",  
        "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8",  
        "9", "0", ".", "-", "*", "/", "'", ":", ";", ">", "<", "~", "!",  
        "@", "#", "$", "%", "^", "&", "(", ")", "{", "}", "[", "]", "|" };
	public static String generateSalt(int size){
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		int temp = 0;
		for (int i = 0; i < size; i++) {
			temp = random.nextInt(hexDigits.length);
			buffer.append(hexDigits[temp]);
		}
		return buffer.toString();
	}
}
