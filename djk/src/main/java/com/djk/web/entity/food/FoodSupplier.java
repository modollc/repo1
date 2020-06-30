package com.djk.web.entity.food;



import com.djk.common.DataModel;
/**
 * 食物营养公共资源-供应商
 * <p>Table: <strong>food_supplier</strong>
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
 *   <tr><td>supplierName</td><td>{@link java.lang.String}</td><td>supplier_name</td><td>varchar</td><td>供应商名称</td></tr>
 *   <tr><td>supplierValue</td><td>{@link java.math.BigDecimal}</td><td>supplier_value</td><td>decimal</td><td>系数</td></tr>
 *   <tr><td>supplierBrand</td><td>{@link java.lang.String}</td><td>supplier_brand</td><td>varchar</td><td>品牌</td></tr>
 * </table>
 *
 */
public class FoodSupplier extends DataModel<FoodSupplier> {
 

 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String supplierName;
 	private java.math.BigDecimal supplierValue;
 	private java.lang.String supplierBrand;
 	
 
	/**
     * 获取供应商名称
     */
	public java.lang.String getSupplierName(){
		return this.supplierName;
	}
 		
	/**
     * 设置供应商名称
     */
	public void setSupplierName(java.lang.String supplierName){
		this.supplierName = supplierName;
	}
 		
	/**
     * 获取系数
     */
	public java.math.BigDecimal getSupplierValue(){
		return this.supplierValue;
	}
 		
	/**
     * 设置系数
     */
	public void setSupplierValue(java.math.BigDecimal supplierValue){
		this.supplierValue = supplierValue;
	}
 		
	/**
     * 获取品牌
     */
	public java.lang.String getSupplierBrand(){
		return this.supplierBrand;
	}
 		
	/**
     * 设置品牌
     */
	public void setSupplierBrand(java.lang.String supplierBrand){
		this.supplierBrand = supplierBrand;
	}
 }