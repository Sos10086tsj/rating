package com.chinesedreamer.rating.system.user;

import java.util.Comparator;

import com.chinesedreamer.rating.system.user.vo.Menu;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午11:13:24 
 * Copyright:   Copyright (c)2015
 */
public class MenuComparator implements Comparator<Menu>{

	@Override
	public int compare(Menu o1, Menu o2) {
		return o1.getSeq().compareTo(o2.getSeq());
	}

}
