package com.chinesedreamer.rating.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Description: 
 * @author Paris
 * @date Jun 5, 20153:19:52 PM
 * @version beta
 */
public class ExcelUtil {
	
	public static String[] columnCharacters = {
		"A","B","C","D","E","F","G","H","I","J",
		"K","L","M","N","O","P","Q","R","S","T",
		"U","V","W","X","Y","Z"
	};
	
	public static Integer getCellIntegerValue(Cell cell){
		Integer value = null;
		if (null == cell) {
			return null;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			value = (int)cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING:
			if (!StringUtils.isEmpty(cell.getStringCellValue())) {
				value = Integer.valueOf(cell.getStringCellValue());
			}
			break;
		case Cell.CELL_TYPE_FORMULA:
			String valueStr = getFormulaValue(cell).toString();
			int index = valueStr.indexOf(".");
			if (-1 != index) {
				valueStr = valueStr.substring(0, index-1);
			}
			value = Integer.valueOf(valueStr);
			break;
		default:
			break;
		}
		return value;
	}
	
	public static Float getCellFloatValue(Cell cell){
		Double value = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			value = Double.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			if (!StringUtils.isEmpty(cell.getStringCellValue())) {
				value = Double.valueOf(cell.getStringCellValue());
			}
			break;
		case Cell.CELL_TYPE_FORMULA:
			value = Double.valueOf(getFormulaValue(cell).toString());
			break;
		default:
			break;
		}
		if (null == value) {
			return null;
		}
		return value.floatValue();
	}
	
	public static String getCellStringValue(Cell cell) {
		String value = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			value = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			value = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			value = String.valueOf(getFormulaValue(cell));
			break;
		default:
			break;
		}
		return value;
	}
	
	public static Object getFormulaValue(Cell cell) {
		Object obj = null;
		switch (cell.getCachedFormulaResultType()) {
		case Cell.CELL_TYPE_NUMERIC:
			obj = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator().evaluate(cell).getNumberValue();
			break;
		case Cell.CELL_TYPE_STRING:
			obj = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator().evaluate(cell).getNumberValue();
			break;
		default:
			break;
		}
		return obj;
	}
	
	public static String getColumnCharacter(int column){
		if (column <= 25) {
			return columnCharacters[column];
		}
		int prefix = (column + 1) / 26;
		int subfix = (column + 1) - prefix * 26;
		return columnCharacters[prefix - 1] + columnCharacters[subfix - 1];
	}
}
