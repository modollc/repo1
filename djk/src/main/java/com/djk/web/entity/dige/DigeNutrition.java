package com.djk.web.entity.dige;

import java.io.Serializable;

import com.djk.common.DataModel;
/**
 * 消化率-营养需要量
 * <p>Table: <strong>dige_nutrition</strong>
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
 *   <tr><td>nutritionQuantity</td><td>{@link java.lang.String}</td><td>nutrition_quantity</td><td>varchar</td><td>营养需要量</td></tr>
 *   <tr><td>nutritionNumber</td><td>{@link java.lang.String}</td><td>nutrition_number</td><td>varchar</td><td>数值</td></tr>
 *   <tr><td>nutritionValue</td><td>{@link java.math.BigDecimal}</td><td>nutrition_value</td><td>decimal</td><td>系数</td></tr>
 * </table>
 *
 */
public class DigeNutrition  extends DataModel<DigeNutrition>{
 

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String nutritionQuantity;
 	private java.lang.String nutritionNumber;
 	private java.math.BigDecimal nutritionValue;
 	
 		
	
 		
	/**
     * 获取营养需要量
     */
	public java.lang.String getNutritionQuantity(){
		return this.nutritionQuantity;
	}
 		
	/**
     * 设置营养需要量
     */
	public void setNutritionQuantity(java.lang.String nutritionQuantity){
		this.nutritionQuantity = nutritionQuantity;
	}
 		
	/**
     * 获取数值
     */
	public java.lang.String getNutritionNumber(){
		return this.nutritionNumber;
	}
 		
	/**
     * 设置数值
     */
	public void setNutritionNumber(java.lang.String nutritionNumber){
		this.nutritionNumber = nutritionNumber;
	}
 		
	/**
     * 获取系数
     */
	public java.math.BigDecimal getNutritionValue(){
		return this.nutritionValue;
	}
 		
	/**
     * 设置系数
     */
	public void setNutritionValue(java.math.BigDecimal nutritionValue){
		this.nutritionValue = nutritionValue;
	}
 }