package com.djk.web.entity.nutrition;

import java.io.Serializable;

import com.djk.common.DataModel;
/**
 * 
 * <p>Table: <strong>nutrition_component</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>营养分类id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>营养成分名称</td></tr>
 *   <tr><td>unit</td><td>{@link java.lang.String}</td><td>unit</td><td>varchar</td><td>营养成分单位</td></tr>
 *   <tr><td>nutritionCategoryId</td><td>{@link java.lang.Integer}</td><td>nutrition_category_id</td><td>int</td><td>nutritionCategoryId</td></tr>
 *   <tr><td>nutritionCategoryName</td><td>{@link java.lang.String}</td><td>nutrition_category_name</td><td>varchar</td><td>营养分类名称</td></tr>
 *   <tr><td>nutritionCategoryCode</td><td>{@link java.lang.String}</td><td>nutrition_category_code</td><td>varchar</td><td>营养分类编码</td></tr>
 *   <tr><td>createBy</td><td>{@link java.lang.String}</td><td>create_by</td><td>varchar</td><td>创建人id</td></tr>
 *   <tr><td>createDate</td><td>{@link java.util.Date}</td><td>create_date</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateBy</td><td>{@link java.util.Date}</td><td>update_by</td><td>datetime</td><td>更新人</td></tr>
 *   <tr><td>updateDate</td><td>{@link java.util.Date}</td><td>update_date</td><td>datetime</td><td>更新时间</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>int</td><td>状态：0，删除，1.正常</td></tr>
 * </table>
 *
 */
public class NutritionComponent extends DataModel<NutritionComponent> {
 

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String name;
 	private java.lang.String unit;
 	private java.lang.Integer nutritionCategoryId;
 	private java.lang.String nutritionCategoryName;
 	private java.lang.String nutritionCategoryCode;
 	private java.lang.Integer state;
 	private java.lang.String pids;
 	
 	
 	
 
 		
	/**
	 * @return the pids
	 */
	public java.lang.String getPids() {
		return pids;
	}

	/**
	 * @param pids the pids to set
	 */
	public void setPids(java.lang.String pids) {
		this.pids = pids;
	}

	/**
     * 获取营养成分名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置营养成分名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取营养成分单位
     */
	public java.lang.String getUnit(){
		return this.unit;
	}
 		
	/**
     * 设置营养成分单位
     */
	public void setUnit(java.lang.String unit){
		this.unit = unit;
	}
 		
	/**
     * 获取nutritionCategoryId
     */
	public java.lang.Integer getNutritionCategoryId(){
		return this.nutritionCategoryId;
	}
 		
	/**
     * 设置nutritionCategoryId
     */
	public void setNutritionCategoryId(java.lang.Integer nutritionCategoryId){
		this.nutritionCategoryId = nutritionCategoryId;
	}
 		
	/**
     * 获取营养分类名称
     */
	public java.lang.String getNutritionCategoryName(){
		return this.nutritionCategoryName;
	}
 		
	/**
     * 设置营养分类名称
     */
	public void setNutritionCategoryName(java.lang.String nutritionCategoryName){
		this.nutritionCategoryName = nutritionCategoryName;
	}
 		
	/**
     * 获取营养分类编码
     */
	public java.lang.String getNutritionCategoryCode(){
		return this.nutritionCategoryCode;
	}
 		
	/**
     * 设置营养分类编码
     */
	public void setNutritionCategoryCode(java.lang.String nutritionCategoryCode){
		this.nutritionCategoryCode = nutritionCategoryCode;
	}
 		
	
 		
	/**
     * 获取状态：0，删除，1.正常
     */
	public java.lang.Integer getState(){
		return this.state;
	}
 		
	/**
     * 设置状态：0，删除，1.正常
     */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
 }