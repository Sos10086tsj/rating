package com.chinesedreamer.rating.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

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
	
	//des
	/**
	 * 加密函数 输入： 要加密的文件，密码（由0-F组成，共48个字符，表示3个8位的密码）如： 
     * AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746 其中： AD67EA2F3BE6E5AD 
     * DES密码一 D368DFE03120B5DF DES密码二 92A8FD8FEC2F0746 DES密码三 输出： 
     * 对输入的文件加密后，保存到同一文件夹下增加了".license"扩展名的文件中。 
	 * @param file
	 * @param key
	 */
	public static void encrypt(File file, String key) {
		try {
			if (key.length() == 48) {
				byte[] bytK1 = string2Byte(key.substring(0, 16));
				byte[] bytK2 = string2Byte(key.substring(16, 32));
				byte[] bytK3 = string2Byte(key.substring(32, 48));
				
				FileInputStream fis = new FileInputStream(file);
				byte[] bytIn = new byte[(int)file.length()];
				for (int i = 0; i < file.length() ; i++) {
					bytIn[i] = (byte) fis.read();
				}
				
				//加密
				byte[] bytOut = encryptByDES(encryptByDES(encryptByDES(bytIn, bytK1), bytK2), bytK3);
				String fileOut = file.getPath() + ".license";
				FileOutputStream fos = new FileOutputStream(fileOut);
				for (int i = 0; i < bytOut.length; i++) {
					fos.write((int) bytOut[i]);
				}
				fos.close();
				fis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 字符串转byte数组
	 * @param str 16进制
	 * @return
	 */
	private static byte[] string2Byte(String str) {
		byte[] byt = new byte[str.length() / 2];
		for (int i = 0; i < byt.length / 2 ; i++) {
			Integer itg = new Integer(16 * BinaryUtil.hex2Decimal(str.charAt(2 * i))
					+ BinaryUtil.hex2Decimal(str.charAt(2*i + 1))
					);
			byt[i] = itg.byteValue();
		}
		return byt;
	}
	
	/**
	 * 用DES方法加密输入的字节 bytKey需为8字节长，是加密的密码 
	 * @param bytP
	 * @param bytKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByDES(byte[] bytP, byte[] bytKey) throws Exception{
		DESKeySpec dks = new DESKeySpec(bytKey);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(dks);
		Cipher cip = Cipher.getInstance("DES");
		cip.init(Cipher.ENCRYPT_MODE, sk);
		return cip.doFinal(bytP);
	}
	
	/**
	 * 用DES方法解密输入的字节 bytKey需为8字节长，是解密的密码 
	 * @param bytE
	 * @param bytKey
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByDES(byte[] bytE, byte[] bytKey) throws Exception {  
        DESKeySpec desKS = new DESKeySpec(bytKey);  
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");  
        SecretKey sk = skf.generateSecret(desKS);  
        Cipher cip = Cipher.getInstance("DES");  
        cip.init(Cipher.DECRYPT_MODE, sk);  
        return cip.doFinal(bytE);  
    }  
	
	/**
	 * 解密函数 输入： 要解密的文件，密码（由0-F组成，共48个字符，表示3个8位的密码）如： 
     * AD67EA2F3BE6E5ADD368DFE03120B5DF92A8FD8FEC2F0746 其中： AD67EA2F3BE6E5AD 
     * DES密码一 D368DFE03120B5DF DES密码二 92A8FD8FEC2F0746 DES密码三 输出： 
     * 对输入的文件解密后，保存到用户指定的文件中。
	 * @param fileIn
	 * @param key
	 */
	public static String decrypt(File fileIn, String key) {
		try {
			if (key.length() == 48) {
				String path = fileIn.getPath();
				//path = path.substring(0, path.length() - 5);
				
				byte[] bytK1 = string2Byte(key.substring(0, 16));
				byte[] bytK2 = string2Byte(key.substring(16, 32));
				byte[] bytK3 = string2Byte(key.substring(32, 48));
				
				FileInputStream fis = new FileInputStream(path);
				byte[] bytIn = new byte[(int) fileIn.length()];
				for (int i = 0; i < fileIn.length(); i++) {
					bytIn[i] = (byte) fis.read();
				}
				
				//解密
				byte[] bytOut = decryptByDES(decryptByDES(decryptByDES(bytIn, bytK3), bytK2), bytK1);
				
				fis.close();
				
				return new String(bytOut);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
