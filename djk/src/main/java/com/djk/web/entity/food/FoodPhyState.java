package com.djk.web.entity.food;


import com.djk.common.DataModel;
/**
 * 食物营养公共资源-食物物理状态
 * <p>Table: <strong>food_phy_state</strong>
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
 *   <tr><td>foodStatus</td><td>{@link java.lang.String}</td><td>food_status</td><td>varchar</td><td>食物生理状态</td></tr>
 *   <tr><td>foodValue</td><td>{@link java.math.BigDecimal}</td><td>food_value</td><td>decimal</td><td>系数</td></tr>
 * </table>
 *
 */
public class FoodPhyState extends DataModel<FoodPhyState> {
 
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

 	private java.lang.String foodStatus;
 	private java.math.BigDecimal foodValue;
 	


 	
 		
	/**
     * 获取食物生理状态
     */
	public java.lang.String getFoodStatus(){
		return this.foodStatus;
	}
 		
	/**
     * 设置食物生理状态
     */
	public void setFoodStatus(java.lang.String foodStatus){
		this.foodStatus = foodStatus;
	}
 		
	/**
     * 获取系数
     */
	public java.math.BigDecimal getFoodValue(){
		return this.foodValue;
	}
 		
	/**
     * 设置系数
     */
	public void setFoodValue(java.math.BigDecimal foodValue){
		this.foodValue = foodValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FoodPhyState [foodStatus=" + foodStatus + ", foodValue="
				+ foodValue + "]";
	}

	
	
	
 }