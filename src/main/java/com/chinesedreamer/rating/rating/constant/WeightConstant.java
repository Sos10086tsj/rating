package com.chinesedreamer.rating.rating.constant;
/**
 * Description: 
 * @author Paris
 * @date Jul 2, 201511:12:22 AM
 * @version beta
 */
public class WeightConstant {
	//A表设置
	public static Float A_BXDF_PERCENTF = 0.7f;//A表表现
	public static Float A_NLDF_PERCENTF = 0.3f;//A表能力
	public static Float A_BXDF_INNER_PERCENT = 0.2f;//A表本组表现
	public static Float A_BXDF_OUTER_PERCENT = 0.1f;//A表外租表现
	public static Float A_NLDF_INNER_PERCENT = 1.0f;//A表本组能力
	public static Float A_NLDF_OUTER_PERCENT = 1.0f;//A表外租能力
	
	//B表设置
	public static Float B_BXDF_PERCENTF = 0.7f;//B表表现
	public static Float B_NLDF_PERCENTF = 0.3f;//B表能力
	public static Float B_BXDF_INNER_LEADER_PERCENT = 0.25f;//B表表现本组组长
	public static Float B_BXDF_ZONGTI_PERCENT = 0.25f;//B表表现总体组
	public static Float B_BXDF_OUTER_LEADER_PERCENT = 0.2f;//B表表现外组组长
	public static Float B_NLDF_INNER_LEADER_PERCENT = 0.4f;//B表能力本组组长
	public static Float B_NLDF_ZONGTI_PERCENT = 0.4f;//B表能力总体组
	public static Float B_NLDF_OUTER_LEADER_PERCENT = 0.2f;//B表能力外组组长
}
