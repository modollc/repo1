package com.djk.web.entity.personResource;


import com.djk.common.DataModel;
/**
 * 人需公共资源-睡眠
 * <p>Table: <strong>people_sleep</strong>
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
 *   <tr><td>sleepQuality</td><td>{@link java.lang.String}</td><td>sleep_quality</td><td>varchar</td><td>睡眠质量</td></tr>
 *   <tr><td>sleepTime</td><td>{@link java.lang.String}</td><td>sleep_time</td><td>varchar</td><td>时长</td></tr>
 *   <tr><td>sleepValue</td><td>{@link java.math.BigDecimal}</td><td>sleep_value</td><td>decimal</td><td>系数</td></tr>
 *   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>char</td><td>删除标识  1、未删除 2、已删除</td></tr>
 * </table>
 *
 */
public class PeopleActive extends DataModel<PeopleActive>{
 
 	private java.lang.String activeName;
 	private java.math.BigDecimal activeValue;
 	
	public java.lang.String getActiveName() {
		return activeName;
	}
	public void setActiveName(java.lang.String activeName) {
		this.activeName = activeName;
	}
	public java.math.BigDecimal getActiveValue() {
		return activeValue;
	}
	public void setActiveValue(java.math.BigDecimal activeValue) {
		this.activeValue = activeValue;
	}
 	
 }