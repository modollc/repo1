package com.djk.web.entity.food;

import java.io.Serializable;
/**
 * 食品营养组分
 * <p>Table: <strong>food_nutrition_component</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>nutritionComponentName</td><td>{@link java.lang.String}</td><td>nutrition_component_name</td><td>varchar</td><td>营养组分名称</td></tr>
 *   <tr><td>nutritionComponentUnit</td><td>{@link java.lang.String}</td><td>nutrition_component_unit</td><td>varchar</td><td>营养组分单位</td></tr>
 *   <tr><td>nutritionComponentNum</td><td>{@link java.math.BigDecimal}</td><td>nutrition_component_num</td><td>decimal</td><td>营养组分数量</td></tr>
 *   <tr><td>foodId</td><td>{@link java.lang.Integer}</td><td>food_id</td><td>int</td><td>食品id</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>int</td><td>状态：0，删除，1正常</td></tr>
 * </table>
 *
 */
public class FoodNutritionComponent implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String nutritionComponentName;
 	private java.lang.String nutritionComponentUnit;
 	private java.math.BigDecimal nutritionComponentNum;
 	private java.lang.Integer foodId;
 	private java.lang.Integer state;
 	
 		
	/**
     * 获取id
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
	/**
     * 获取营养组分名称
     */
	public java.lang.String getNutritionComponentName(){
		return this.nutritionComponentName;
	}
 		
	/**
     * 设置营养组分名称
     */
	public void setNutritionComponentName(java.lang.String nutritionComponentName){
		this.nutritionComponentName = nutritionComponentName;
	}
 		
	/**
     * 获取营养组分单位
     */
	public java.lang.String getNutritionComponentUnit(){
		return this.nutritionComponentUnit;
	}
 		
	/**
     * 设置营养组分单位
     */
	public void setNutritionComponentUnit(java.lang.String nutritionComponentUnit){
		this.nutritionComponentUnit = nutritionComponentUnit;
	}
 		
	/**
     * 获取营养组分数量
     */
	public java.math.BigDecimal getNutritionComponentNum(){
		return this.nutritionComponentNum;
	}
 		
	/**
     * 设置营养组分数量
     */
	public void setNutritionComponentNum(java.math.BigDecimal nutritionComponentNum){
		this.nutritionComponentNum = nutritionComponentNum;
	}
 		
	/**
     * 获取食品id
     */
	public java.lang.Integer getFoodId(){
		return this.foodId;
	}
 		
	/**
     * 设置食品id
     */
	public void setFoodId(java.lang.Integer foodId){
		this.foodId = foodId;
	}
 		
	/**
     * 获取状态：0，删除，1正常
     */
	public java.lang.Integer getState(){
		return this.state;
	}
 		
	/**
     * 设置状态：0，删除，1正常
     */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
 }