package com.chinesedreamer.rating.template.vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jul 17, 20153:42:22 PM
 * @version beta
 */
public @Getter @Setter class OptionVo {
	private Long id;
	private String name;
	private String categoryCode;
	private String categoryName;
	
	private BigDecimal weight;//模板时才需要
	
	private String operation;
}
