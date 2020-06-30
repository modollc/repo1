package com.djk.web.entity.systemResource;

import java.io.Serializable;

import com.djk.common.DataModel;
/**
 * 系统公共资源-基本常量
 * <p>Table: <strong>system_constant</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>编号</td></tr>
 *   <tr><td>createBy</td><td>{@link java.lang.Integer}</td><td>create_by</td><td>int</td><td>创建者</td></tr>
 *   <tr><td>createDate</td><td>{@link java.util.Date}</td><td>create_date</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateBy</td><td>{@link java.lang.Integer}</td><td>update_by</td><td>int</td><td>更新者</td></tr>
 *   <tr><td>updateDate</td><td>{@link java.util.Date}</td><td>update_date</td><td>datetime</td><td>更新时间</td></tr>
 *   <tr><td>remarks</td><td>{@link java.lang.String}</td><td>remarks</td><td>varchar</td><td>备注信息</td></tr>
 *   <tr><td>constantName</td><td>{@link java.lang.String}</td><td>constant_name</td><td>varchar</td><td>常量名称</td></tr>
 *   <tr><td>minValue</td><td>{@link java.math.BigDecimal}</td><td>min_value</td><td>decimal</td><td>最小值</td></tr>
 *   <tr><td>maxValue</td><td>{@link java.math.BigDecimal}</td><td>max_value</td><td>decimal</td><td>最大值</td></tr>
 *   <tr><td>constantValue</td><td>{@link java.math.BigDecimal}</td><td>constant_value</td><td>decimal</td><td>增幅值</td></tr>
 *   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>char</td><td>删除标识  1、未删除 2、已删除</td></tr>
 * </table>
 *
 */
public class SystemConstant extends DataModel<SystemConstant> {
 
 	private java.lang.String constantName;
 	private java.math.BigDecimal minValue;
 	private java.math.BigDecimal maxValue;
 	private java.math.BigDecimal constantValue;
 	
 		
	/**
     * 获取常量名称
     */
	public java.lang.String getConstantName(){
		return this.constantName;
	}
 		
	/**
     * 设置常量名称
     */
	public void setConstantName(java.lang.String constantName){
		this.constantName = constantName;
	}
 		
	/**
     * 获取最小值
     */
	public java.math.BigDecimal getMinValue(){
		return this.minValue;
	}
 		
	/**
     * 设置最小值
     */
	public void setMinValue(java.math.BigDecimal minValue){
		this.minValue = minValue;
	}
 		
	/**
     * 获取最大值
     */
	public java.math.BigDecimal getMaxValue(){
		return this.maxValue;
	}
 		
	/**
     * 设置最大值
     */
	public void setMaxValue(java.math.BigDecimal maxValue){
		this.maxValue = maxValue;
	}
 		
	/**
     * 获取增幅值
     */
	public java.math.BigDecimal getConstantValue(){
		return this.constantValue;
	}
 		
	/**
     * 设置增幅值
     */
	public void setConstantValue(java.math.BigDecimal constantValue){
		this.constantValue = constantValue;
	}

	
 }