package com.chinesedreamer.rating.system.user.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:51:59 
 * Copyright:   Copyright (c)2015
 */
public class Menu {
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

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public BigDecimal getSeq() {
		return seq;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}
	
	
}
