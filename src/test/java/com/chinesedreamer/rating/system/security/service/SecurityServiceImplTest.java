package com.chinesedreamer.rating.system.security.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.chinesedreamer.rating.base.SpringTest;

public class SecurityServiceImplTest extends SpringTest{
	@Resource
	private SecurityService securityService;
	@Test
	public void testIsSystemAuthorised() {
		this.securityService.isSystemAuthorised();
	}

}
