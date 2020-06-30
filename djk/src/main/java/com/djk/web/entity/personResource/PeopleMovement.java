package com.djk.web.entity.personResource;

import java.io.Serializable;

import com.djk.common.DataModel;
/**
 * 人需公共资源-运动
 * <p>Table: <strong>people_movement</strong>
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
 *   <tr><td>movementName</td><td>{@link java.lang.String}</td><td>movement_name</td><td>varchar</td><td>名称</td></tr>
 *   <tr><td>movementTime</td><td>{@link java.lang.String}</td><td>movement_time</td><td>varchar</td><td>运动时长</td></tr>
 *   <tr><td>movementNum</td><td>{@link java.math.BigDecimal}</td><td>movement_num</td><td>decimal</td><td>运动量</td></tr>
	*   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>char</td><td>删除标识  1、未删除 2、已删除</td></tr>
 * </table>
 *
 */
public class PeopleMovement extends DataModel<PeopleMovement>{
 
 	private java.lang.String movementName;
 	private java.lang.String movementTime;
 	private java.math.BigDecimal movementNum;
 		
 		
	/**
     * 获取名称
     */
	public java.lang.String getMovementName(){
		return this.movementName;
	}
 		
	/**
     * 设置名称
     */
	public void setMovementName(java.lang.String movementName){
		this.movementName = movementName;
	}
 		
	/**
     * 获取运动时长
     */
	public java.lang.String getMovementTime(){
		return this.movementTime;
	}
 		
	/**
     * 设置运动时长
     */
	public void setMovementTime(java.lang.String movementTime){
		this.movementTime = movementTime;
	}
 		
	/**
     * 获取运动量
     */
	public java.math.BigDecimal getMovementNum(){
		return this.movementNum;
	}
 		
	/**
     * 设置运动量
     */
	public void setMovementNum(java.math.BigDecimal movementNum){
		this.movementNum = movementNum;
	}

	
 }