package com.chinesedreamer.rating.common.io;

import org.junit.Test;

import com.chinesedreamer.rating.system.security.service.SecurityServiceImpl;

public class PropertiesUtilsTest {

	@Test
	public void testSetProperty() {
		String applicationPath = SecurityServiceImpl.class.getClassLoader().getResource("application.properties").getPath();
		System.out.println(applicationPath);
		
		int classIndex = applicationPath.lastIndexOf("classes");
		System.out.println(classIndex);
		
		String path = applicationPath.substring(0, classIndex);
		System.out.println(path);
	}

}
