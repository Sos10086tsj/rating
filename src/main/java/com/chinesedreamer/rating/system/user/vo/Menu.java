package com.chinesedreamer.rating.system.user.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Setter;


import lombok.Getter;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:51:59 
 * Copyright:   Copyright (c)2015
 */
public @Getter @Setter class Menu {
	private String name;//菜单名字
	private String url;//访问链接
	private BigDecimal seq;
	private List<Menu> subMenu;//子菜单
	
	public List<Menu> getSubMenu(){
		if (null == subMenu) {
			subMenu = new ArrayList<Menu>();
		}
		return subMenu;
	}
}
