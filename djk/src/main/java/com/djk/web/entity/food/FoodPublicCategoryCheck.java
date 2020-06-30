package com.djk.web.entity.food;

import java.io.Serializable;
/**
 * 食物公共分类功能类别选择表
 * <p>Table: <strong>food_public_category_check</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>createBy</td><td>{@link java.lang.Integer}</td><td>create_by</td><td>int</td><td>创建人id</td></tr>
 *   <tr><td>createDate</td><td>{@link java.util.Date}</td><td>create_date</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateBy</td><td>{@link java.lang.Integer}</td><td>update_by</td><td>int</td><td>更新人id</td></tr>
 *   <tr><td>updateDate</td><td>{@link java.util.Date}</td><td>update_date</td><td>datetime</td><td>更新时间</td></tr>
 *   <tr><td>pid</td><td>{@link java.lang.Integer}</td><td>pid</td><td>int</td><td>公共类别父id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>公共类别名称</td></tr>
 *   <tr><td>foodCategoryId</td><td>{@link java.lang.Integer}</td><td>food_category_id</td><td>int</td><td>食物分类id</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>int</td><td>数据状态，1.正常，2.删除</td></tr>
 *   <tr><td>check</td><td>{@link java.lang.Integer}</td><td>check</td><td>int</td><td>是否选中，1.选中，2.未选中</td></tr>
 * </table>
 *
 */
public class FoodPublicCategoryCheck implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.Integer createBy;
 	private java.util.Date createDate;
 	private java.lang.Integer updateBy;
 	private java.util.Date updateDate;
 	private java.lang.Integer pid;
 	private java.lang.String name;
 	private java.lang.Integer foodCategoryId;
 	private java.lang.Integer state;
 	private java.lang.Integer check;
 	private java.lang.Integer foodId;
	private java.lang.Integer oldId;
	
 	
 		
	public java.lang.Integer getOldId() {
		return oldId;
	}

	public void setOldId(java.lang.Integer oldId) {
		this.oldId = oldId;
	}

	public java.lang.Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(java.lang.Integer foodId) {
		this.foodId = foodId;
	}

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
     * 获取创建人id
     */
	public java.lang.Integer getCreateBy(){
		return this.createBy;
	}
 		
	/**
     * 设置创建人id
     */
	public void setCreateBy(java.lang.Integer createBy){
		this.createBy = createBy;
	}
 		
	/**
     * 获取创建时间
     */
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
 		
	/**
     * 设置创建时间
     */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
 		
	/**
     * 获取更新人id
     */
	public java.lang.Integer getUpdateBy(){
		return this.updateBy;
	}
 		
	/**
     * 设置更新人id
     */
	public void setUpdateBy(java.lang.Integer updateBy){
		this.updateBy = updateBy;
	}
 		
	/**
     * 获取更新时间
     */
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}
 		
	/**
     * 设置更新时间
     */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
 		
	/**
     * 获取公共类别父id
     */
	public java.lang.Integer getPid(){
		return this.pid;
	}
 		
	/**
     * 设置公共类别父id
     */
	public void setPid(java.lang.Integer pid){
		this.pid = pid;
	}
 		
	/**
     * 获取公共类别名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置公共类别名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取食物分类id
     */
	public java.lang.Integer getFoodCategoryId(){
		return this.foodCategoryId;
	}
 		
	/**
     * 设置食物分类id
     */
	public void setFoodCategoryId(java.lang.Integer foodCategoryId){
		this.foodCategoryId = foodCategoryId;
	}
 		
	/**
     * 获取数据状态，1.正常，2.删除
     */
	public java.lang.Integer getState(){
		return this.state;
	}
 		
	/**
     * 设置数据状态，1.正常，2.删除
     */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
 		
	/**
     * 获取是否选中，1.选中，2.未选中
     */
	public java.lang.Integer getCheck(){
		return this.check;
	}
 		
	/**
     * 设置是否选中，1.选中，2.未选中
     */
	public void setCheck(java.lang.Integer check){
		this.check = check;
	}
 }