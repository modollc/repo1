package com.djk.web.entity.personResource;

import java.io.Serializable;

import com.djk.common.DataModel;
/**
 * 人需公共资源-家居人口
 * <p>Table: <strong>people_household</strong>
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
 *   <tr><td>household</td><td>{@link java.lang.String}</td><td>household</td><td>varchar</td><td>家居人口</td></tr>
 *   <tr><td>householdValue</td><td>{@link java.math.BigDecimal}</td><td>household_value</td><td>decimal</td><td>系数</td></tr>
*   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>char</td><td>删除标识  1、未删除 2、已删除</td></tr>
 * </table>
 *
 */
public class PeopleHousehold extends DataModel<PeopleHousehold>{
 
 	private java.lang.String household;
 	private java.math.BigDecimal householdValue;
 	
 	
 		
	/**
     * 获取家居人口
     */
	public java.lang.String getHousehold(){
		return this.household;
	}
 		
	/**
     * 设置家居人口
     */
	public void setHousehold(java.lang.String household){
		this.household = household;
	}
 		
	/**
     * 获取系数
     */
	public java.math.BigDecimal getHouseholdValue(){
		return this.householdValue;
	}
 		
	/**
     * 设置系数
     */
	public void setHouseholdValue(java.math.BigDecimal householdValue){
		this.householdValue = householdValue;
	}

	
 }