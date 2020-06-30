package com.djk.web.entity.food;


import com.djk.common.DataModel;
/**
 * 食物营养公共资源-烹饪方式
 * <p>Table: <strong>food_cooking</strong>
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
 *   <tr><td>cookingMethod</td><td>{@link java.lang.String}</td><td>cooking_method</td><td>varchar</td><td>烹饪方式</td></tr>
 *   <tr><td>cookingValue</td><td>{@link java.math.BigDecimal}</td><td>cooking_value</td><td>decimal</td><td>系数</td></tr>
 * </table>
 *
 */
public class FoodCooking extends DataModel<FoodCooking> {
 
 	
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String cookingMethod;
 	private java.math.BigDecimal cookingValue;
 	
 		
	
	/**
     * 获取烹饪方式
     */
	public java.lang.String getCookingMethod(){
		return this.cookingMethod;
	}
 		
	/**
     * 设置烹饪方式
     */
	public void setCookingMethod(java.lang.String cookingMethod){
		this.cookingMethod = cookingMethod;
	}
 		
	/**
     * 获取系数
     */
	public java.math.BigDecimal getCookingValue(){
		return this.cookingValue;
	}
 		
	/**
     * 设置系数
     */
	public void setCookingValue(java.math.BigDecimal cookingValue){
		this.cookingValue = cookingValue;
	}
 }