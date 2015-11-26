package com.chinesedreamer.rating.common.utils;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class EncryptionUtilTest {

	@Test
	public void test() throws Exception {
//		String mac = "12345678123456781234567812345678";
		String mac = IpUtil.getLocalMac(IpUtil.getLocalhost());
		EncryptionUtil.encrypt(new File("C:/Users/Paris/Desktop/test/rating"), 
				EncryptionUtil.md5L32(mac).substring(0, 16)
				+ EncryptionUtil.md5L32(mac).substring(8, 24)
				+ EncryptionUtil.md5L32(mac).substring(16, 32)
				);
	}

	@Test
	public void testDecrypt() throws Exception{
		String mac = "12345678123456781234567812345678";
				//IpUtil.getLocalMac(IpUtil.getLocalhost());
		String license = EncryptionUtil.decrypt(new File("C:/Users/Paris/Desktop/test/rating.license"), 
				EncryptionUtil.md5L32(mac).substring(0, 16)
				+ EncryptionUtil.md5L32(mac).substring(8, 24)
				+ EncryptionUtil.md5L32(mac).substring(16, 32)
				);
		System.out.println("license:" + license);
	}
	
	@Test
	public void testPath(){
		URL p1 = EncryptionUtilTest.class.getResource("/");
		System.out.println(p1.getPath());
		
		URL p2 = EncryptionUtilTest.class.getClassLoader().getResource("rating.license");
		System.out.println("p2:" + p2.getPath());
		System.out.println("p2 file:" + p2.getFile());
		
		File file = new File(p2.getPath());
		System.out.println("file:      " + file.getName());
	}
}
