package com.djk.web.entity.dige;

import java.io.Serializable;

import com.djk.common.DataModel;
/**
 * 消化率-用餐量
 * <p>Table: <strong>dige_dining</strong>
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
 *   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>char</td><td>删除标记(0 正常，1己删除)</td></tr>
 *   <tr><td>mealQuantity</td><td>{@link java.lang.String}</td><td>meal_quantity</td><td>varchar</td><td>用餐量</td></tr>
 *   <tr><td>mealScope</td><td>{@link java.lang.String}</td><td>meal_scope</td><td>varchar</td><td>用餐范围</td></tr>
 *   <tr><td>mealValue</td><td>{@link java.math.BigDecimal}</td><td>meal_value</td><td>decimal</td><td>用餐系数</td></tr>
 * </table>
 *
 */
public class DigeDining extends DataModel<DigeDining>{
 
 	
 	private java.lang.String mealQuantity;
 	private java.lang.String mealScope;
 	private java.math.BigDecimal mealValue;
 	
 		
	
 		
	/**
     * 获取用餐量
     */
	public java.lang.String getMealQuantity(){
		return this.mealQuantity;
	}
 		
	/**
     * 设置用餐量
     */
	public void setMealQuantity(java.lang.String mealQuantity){
		this.mealQuantity = mealQuantity;
	}
 		
	/**
     * 获取用餐范围
     */
	public java.lang.String getMealScope(){
		return this.mealScope;
	}
 		
	/**
     * 设置用餐范围
     */
	public void setMealScope(java.lang.String mealScope){
		this.mealScope = mealScope;
	}
 		
	/**
     * 获取用餐系数
     */
	public java.math.BigDecimal getMealValue(){
		return this.mealValue;
	}
 		
	/**
     * 设置用餐系数
     */
	public void setMealValue(java.math.BigDecimal mealValue){
		this.mealValue = mealValue;
	}
 }