package com.djk.web.entity.dige;



import com.djk.common.DataModel;
/**
 * 消化率-吃饭速度
 * <p>Table: <strong>dige_eatfast</strong>
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
 *   <tr><td>eatFast</td><td>{@link java.lang.String}</td><td>eat_fast</td><td>varchar</td><td>吃饭速度</td></tr>
 *   <tr><td>eatTime</td><td>{@link java.lang.String}</td><td>eat_time</td><td>varchar</td><td>时间范围</td></tr>
 *   <tr><td>eatValue</td><td>{@link java.math.BigDecimal}</td><td>eat_value</td><td>decimal</td><td>系数</td></tr>
 * </table>
 *
 */
public class DigeEatfast extends DataModel<DigeEatfast> {
 

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String eatFast;
 	private java.lang.String eatTime;
 	private java.math.BigDecimal eatValue;
 	
 		
	
 		
	/**
     * 获取吃饭速度
     */
	public java.lang.String getEatFast(){
		return this.eatFast;
	}
 		
	/**
     * 设置吃饭速度
     */
	public void setEatFast(java.lang.String eatFast){
		this.eatFast = eatFast;
	}
 		
	/**
     * 获取时间范围
     */
	public java.lang.String getEatTime(){
		return this.eatTime;
	}
 		
	/**
     * 设置时间范围
     */
	public void setEatTime(java.lang.String eatTime){
		this.eatTime = eatTime;
	}
 		
	/**
     * 获取系数
     */
	public java.math.BigDecimal getEatValue(){
		return this.eatValue;
	}
 		
	/**
     * 设置系数
     */
	public void setEatValue(java.math.BigDecimal eatValue){
		this.eatValue = eatValue;
	}
 }