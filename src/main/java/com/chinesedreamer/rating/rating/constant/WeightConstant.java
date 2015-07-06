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
	
	public static Float C_WCRWQK_PERCENTF = 1.0f;//C表	完成任务情况
	public static Float C_WCRWQK_LEADER_PERCENTF = 0.7f;//C表完成任务情况	组长
	public static Float C_WCRWQK_ZONGTI_PERCENTF = 0.7f;//C表成任务情况		总体组
	public static Float C_ZZNL_PERCENTF = 1.0f;//C表		组织能力
	public static Float C_ZZNL_LEADER_PERCENTF = 0.7f;//C表组织能力		组长
	public static Float C_ZZNL_ZONGTI_PERCENTF = 0.7f;//C表组织能力	总体组
	public static Float C_ZHNL_PERCENTF = 1.0f;//C表		综合能力
	public static Float C_ZHNL_LEADER_PERCENTF = 0.7f;//C表综合能力		组长
	public static Float C_ZHNL_ZONGTI_PERCENTF = 0.7f;//C表综合能力	总体组
	
	public static Float D_WCRWQK_PERCENTF = 1.0f;//D表	完成任务情况
	public static Float D_WCRWQK_ZUYUAN_PERCENTF = 0.3f;//D表完成任务情况	组员
	public static Float D_ZZNL_PERCENTF = 1.0f;//D表	组织能力
	public static Float D_ZZNL_ZUYUAN_PERCENTF = 0.3f;//D表组织能力	组员
	public static Float D_ZHNL_PERCENTF = 1.0f;//D表	综合能力
	public static Float D_ZHNL_ZUYUAN_PERCENTF = 0.3f;//D表综合能力	组员
}
