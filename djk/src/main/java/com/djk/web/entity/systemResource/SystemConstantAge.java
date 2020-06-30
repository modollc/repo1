package com.djk.web.entity.systemResource;

import java.io.Serializable;

import com.djk.common.DataModel;
/**
 * 系统公共资源-年龄划分段

 * <p>Table: <strong>system_constant_age</strong>
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
 *   <tr><td>ageName</td><td>{@link java.lang.String}</td><td>age_name</td><td>varchar</td><td>年龄名称</td></tr>
 *   <tr><td>minValue</td><td>{@link java.math.BigDecimal}</td><td>min_value</td><td>decimal</td><td>最小值</td></tr>
 *   <tr><td>maxValue</td><td>{@link java.math.BigDecimal}</td><td>max_value</td><td>decimal</td><td>最大值</td></tr>
 	*   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>char</td><td>删除标识  1、未删除 2、已删除</td></tr>
 * </table>
 *
 */
public class SystemConstantAge extends DataModel<SystemConstantAge> {
 
 	private java.lang.String ageName;
 	private java.math.BigDecimal minValue;
 	private java.math.BigDecimal maxValue;
 	
 		
	/**
     * 获取年龄名称
     */
	public java.lang.String getAgeName(){
		return this.ageName;
	}
 		
	/**
     * 设置年龄名称
     */
	public void setAgeName(java.lang.String ageName){
		this.ageName = ageName;
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

	
 }