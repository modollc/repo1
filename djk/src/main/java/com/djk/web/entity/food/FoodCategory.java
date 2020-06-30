package com.djk.web.entity.food;

import java.io.Serializable;

import com.djk.common.DataModel;
/**
 * 食物分类
 * <p>Table: <strong>food_category</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>createBy</td><td>{@link java.lang.Integer}</td><td>create_by</td><td>int</td><td>创建用户id</td></tr>
 *   <tr><td>createDate</td><td>{@link java.util.Date}</td><td>create_date</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateBy</td><td>{@link java.lang.Integer}</td><td>update_by</td><td>int</td><td>更新用户id</td></tr>
 *   <tr><td>updateDate</td><td>{@link java.util.Date}</td><td>update_date</td><td>datetime</td><td>更新时间</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>食物分类名称</td></tr>
 *   <tr><td>code</td><td>{@link java.lang.String}</td><td>code</td><td>varchar</td><td>食物分类编号</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>int</td><td>状态：0，删除，1正常</td></tr>
 *   <tr><td>pid</td><td>{@link java.lang.Integer}</td><td>pid</td><td>int</td><td>父id</td></tr>
 * </table>
 *
 */
public class FoodCategory extends DataModel<FoodCategory> implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String name;
 	private java.lang.Integer code;
 	private java.lang.Integer state;
 	private java.lang.Integer pid;
 	private boolean open;
 	private boolean isParent;
 	private java.lang.Integer oldId;
 	private String codeValue;
 	
 		
	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public java.lang.Integer getOldId() {
		return oldId;
	}

	public void setOldId(java.lang.Integer oldId) {
		this.oldId = oldId;
	}

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
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
     * 获取食物分类名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置食物分类名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取食物分类编号
     */
	public java.lang.Integer getCode(){
		return this.code;
	}
 		
	/**
     * 设置食物分类编号
     */
	public void setCode(java.lang.Integer code){
		this.code = code;
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
 		
	/**
     * 获取父id
     */
	public java.lang.Integer getPid(){
		return this.pid;
	}
 		
	/**
     * 设置父id
     */
	public void setPid(java.lang.Integer pid){
		this.pid = pid;
	}
 }