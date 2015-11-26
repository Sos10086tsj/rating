package com.chinesedreamer.rating.common.utils;

/**
 * 进制转换util类
 * @author Paris
 *
 */
public class BinaryUtil {

	/**
	 * 十六进制转10进制
	 * @param c
	 * @return
	 */
	public static int hex2Decimal(char c) {
		int r = 0;
		if (c == "0".charAt(0)) {
			r = 0;
		}
		if (c == "1".charAt(0)) {
			r = 1;
		}
		if (c == "2".charAt(0)) {
			r = 2;
		}
		if (c == "3".charAt(0)) {
			r = 3;
		}
		if (c == "4".charAt(0)) {
			r = 4;
		}
		if (c == "5".charAt(0)) {
			r = 5;
		}
		if (c == "6".charAt(0)) {
			r = 6;
		}
		if (c == "7".charAt(0)) {
			r = 7;
		}
		if (c == "8".charAt(0)) {
			r = 8;
		}
		if (c == "9".charAt(0)) {
			r = 9;
		}
		if (c == "A".charAt(0)) {
			r = 10;
		}
		if (c == "B".charAt(0)) {
			r = 11;
		}
		if (c == "C".charAt(0)) {
			r = 12;
		}
		if (c == "D".charAt(0)) {
			r = 13;
		}
		if (c == "E".charAt(0)) {
			r = 14;
		}
		if (c == "F".charAt(0)) {
			r = 15;
		}
		return r;
	}
}
