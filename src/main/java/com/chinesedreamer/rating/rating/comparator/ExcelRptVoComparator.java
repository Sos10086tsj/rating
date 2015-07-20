package com.chinesedreamer.rating.rating.comparator;

import java.util.Comparator;

import com.chinesedreamer.rating.rating.vo.rpt.ExcelRptVo;

/**
 * Description: 
 * @author Paris
 * @date Jul 20, 20151:36:46 PM
 * @version beta
 */
public class ExcelRptVoComparator implements Comparator<ExcelRptVo>{

	@Override
	public int compare(ExcelRptVo o1, ExcelRptVo o2) {
		return o1.getCode().compareTo(o2.getCode());
	}

}
