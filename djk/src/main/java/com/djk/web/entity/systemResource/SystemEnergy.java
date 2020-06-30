package com.djk.web.entity.systemResource;


import com.djk.common.DataModel;
/**
 * 系统公共资源 - 能量单位

 * <p>Table: <strong>system_energy</strong>
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
 *   <tr><td>energyName</td><td>{@link java.lang.String}</td><td>energy_name</td><td>varchar</td><td>单位名称</td></tr>
 *   <tr><td>energyNum</td><td>{@link java.lang.Integer}</td><td>energy_num</td><td>int</td><td>数量</td></tr>
 *   <tr><td>energyValue</td><td>{@link java.math.BigDecimal}</td><td>energy_value</td><td>decimal</td><td>换算单位</td></tr>
	*   <tr><td>delFlag</td><td>{@link java.lang.String}</td><td>del_flag</td><td>char</td><td>删除标识  1、未删除 2、已删除</td></tr>
 * </table>
 *
 */
public class SystemEnergy extends DataModel<SystemEnergy> {
 
 	private java.lang.String energyName;
 	private java.lang.Integer energyNum;
 	private java.math.BigDecimal energyValue;
 	
	/**
     * 获取单位名称
     */
	public java.lang.String getEnergyName(){
		return this.energyName;
	}
 		
	/**
     * 设置单位名称
     */
	public void setEnergyName(java.lang.String energyName){
		this.energyName = energyName;
	}
 		
	/**
     * 获取数量
     */
	public java.lang.Integer getEnergyNum(){
		return this.energyNum;
	}
 		
	/**
     * 设置数量
     */
	public void setEnergyNum(java.lang.Integer energyNum){
		this.energyNum = energyNum;
	}
 		
	/**
     * 获取换算单位
     */
	public java.math.BigDecimal getEnergyValue(){
		return this.energyValue;
	}
 		
	/**
     * 设置换算单位
     */
	public void setEnergyValue(java.math.BigDecimal energyValue){
		this.energyValue = energyValue;
	}
	
 }