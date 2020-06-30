package com.djk.web.entity.food;


import com.djk.common.DataModel;
/**
 * 食物营养公共资源-时令
 * <p>Table: <strong>food_seasonal</strong>
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
 *   <tr><td>seasonal</td><td>{@link java.lang.String}</td><td>seasonal</td><td>varchar</td><td>时令</td></tr>
 *   <tr><td>seasonalValue</td><td>{@link java.math.BigDecimal}</td><td>seasonal_value</td><td>decimal</td><td>系数</td></tr>
 * </table>
 *
 */
public class FoodSeasonal extends DataModel<FoodSeasonal> {
 
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	private java.lang.String seasonal;
 	private java.math.BigDecimal seasonalValue;
 	
 		
	
 		
	/**
     * 获取时令
     */
	public java.lang.String getSeasonal(){
		return this.seasonal;
	}
 		
	/**
     * 设置时令
     */
	public void setSeasonal(java.lang.String seasonal){
		this.seasonal = seasonal;
	}
 		
	/**
     * 获取系数
     */
	public java.math.BigDecimal getSeasonalValue(){
		return this.seasonalValue;
	}
 		
	/**
     * 设置系数
     */
	public void setSeasonalValue(java.math.BigDecimal seasonalValue){
		this.seasonalValue = seasonalValue;
	}
 }