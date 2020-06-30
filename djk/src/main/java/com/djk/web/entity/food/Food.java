package com.djk.web.entity.food;

import java.io.Serializable;
import java.util.List;

import com.djk.common.DataModel;
/**
 * 
 * <p>Table: <strong>food</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>食物名称</td></tr>
 *   <tr><td>alias</td><td>{@link java.lang.String}</td><td>alias</td><td>varchar</td><td>食物别名</td></tr>
 *   <tr><td>code</td><td>{@link java.lang.String}</td><td>code</td><td>varchar</td><td>编号</td></tr>
 *   <tr><td>firstCategoryId</td><td>{@link java.lang.Integer}</td><td>first_category_id</td><td>int</td><td>第一分类id</td></tr>
 *   <tr><td>firstCategoryName</td><td>{@link java.lang.String}</td><td>first_category_name</td><td>varchar</td><td>第一分类名称</td></tr>
 *   <tr><td>secondCategoryId</td><td>{@link java.lang.Integer}</td><td>second_category_id</td><td>int</td><td>第二分类id</td></tr>
 *   <tr><td>secondCategoryName</td><td>{@link java.lang.String}</td><td>second_category_name</td><td>varchar</td><td>第二分类名称</td></tr>
 *   <tr><td>threeCategoryId</td><td>{@link java.lang.Integer}</td><td>three_category_id</td><td>int</td><td>第三分类id</td></tr>
 *   <tr><td>threeCategoryName</td><td>{@link java.lang.String}</td><td>three_category_name</td><td>varchar</td><td>第三分类名称</td></tr>
 *   <tr><td>firstOriginId</td><td>{@link java.lang.Integer}</td><td>first_origin_id</td><td>int</td><td>一级产地id</td></tr>
 *   <tr><td>firstOriginName</td><td>{@link java.lang.String}</td><td>first_origin_name</td><td>varchar</td><td>一级产地名称</td></tr>
 *   <tr><td>secondOriginId</td><td>{@link java.lang.Integer}</td><td>second_origin_id</td><td>int</td><td>二级产地id</td></tr>
 *   <tr><td>secondOriginName</td><td>{@link java.lang.String}</td><td>second_origin_name</td><td>varchar</td><td>二级产地名称</td></tr>
 *   <tr><td>threeOriginId</td><td>{@link java.lang.Integer}</td><td>three_origin_id</td><td>int</td><td>三级产地id</td></tr>
 *   <tr><td>threeOriginName</td><td>{@link java.lang.String}</td><td>three_origin_name</td><td>varchar</td><td>三级产地名称</td></tr>
 *   <tr><td>seasonId</td><td>{@link java.lang.Integer}</td><td>season_id</td><td>int</td><td>时令id</td></tr>
 *   <tr><td>seasonName</td><td>{@link java.lang.String}</td><td>season_name</td><td>varchar</td><td>时令名称</td></tr>
 *   <tr><td>supplierId</td><td>{@link java.lang.Integer}</td><td>supplier_id</td><td>int</td><td>供应商id</td></tr>
 *   <tr><td>supplierName</td><td>{@link java.lang.String}</td><td>supplier_name</td><td>varchar</td><td>供应商名称</td></tr>
 *   <tr><td>unitId</td><td>{@link java.lang.Integer}</td><td>unit_id</td><td>int</td><td>计算单位id</td></tr>
 *   <tr><td>unitName</td><td>{@link java.lang.String}</td><td>unit_name</td><td>varchar</td><td>计算单位名称</td></tr>
 *   <tr><td>unitNum</td><td>{@link java.lang.Integer}</td><td>unit_num</td><td>int</td><td>计算单位数值</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>int</td><td>状态：0，删除，1正常</td></tr>
 * </table>
 *
 */
public class Food  extends DataModel<Food> implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String name;
 	private java.lang.String alias;
 	private java.lang.String code;
 	private java.lang.Integer firstCategoryId;
 	private java.lang.String firstCategoryName;
 	private java.lang.Integer secondCategoryId;
 	private java.lang.String secondCategoryName;
 	private java.lang.Integer threeCategoryId;
 	private java.lang.String threeCategoryName;
 	private java.lang.Integer firstOriginId;
 	private java.lang.String firstOriginName;
 	private java.lang.Integer secondOriginId;
 	private java.lang.String secondOriginName;
 	private java.lang.Integer threeOriginId;
 	private java.lang.String threeOriginName;
 	private java.lang.Integer seasonId;
 	private java.lang.String seasonName;
 	private java.lang.Integer supplierId;
 	private java.lang.String supplierName;
 	private java.lang.Integer unitId;
 	private java.lang.String unitName;
 	private java.lang.Integer unitNum;
 	private java.lang.Integer state;
 	private List<String> nutritionName;
 	private List<String> nutritionUnit;
 	private List<String> nutritionValue;
 	private List<String> publicCategoryCheck;
 	private String codeValue;
 	private String publicCategoryNames;// 食物公共分类
 	
 	

	public String getPublicCategoryNames() {
		return publicCategoryNames;
	}

	public void setPublicCategoryNames(String publicCategoryNames) {
		this.publicCategoryNames = publicCategoryNames;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public List<String> getPublicCategoryCheck() {
		return publicCategoryCheck;
	}

	public void setPublicCategoryCheck(List<String> publicCategoryCheck) {
		this.publicCategoryCheck = publicCategoryCheck;
	}

	public List<String> getNutritionName() {
		return nutritionName;
	}

	public void setNutritionName(List<String> nutritionName) {
		this.nutritionName = nutritionName;
	}

	public List<String> getNutritionUnit() {
		return nutritionUnit;
	}

	public void setNutritionUnit(List<String> nutritionUnit) {
		this.nutritionUnit = nutritionUnit;
	}

	public List<String> getNutritionValue() {
		return nutritionValue;
	}

	public void setNutritionValue(List<String> nutritionValue) {
		this.nutritionValue = nutritionValue;
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
     * 获取食物名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置食物名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取食物别名
     */
	public java.lang.String getAlias(){
		return this.alias;
	}
 		
	/**
     * 设置食物别名
     */
	public void setAlias(java.lang.String alias){
		this.alias = alias;
	}
 		
	/**
     * 获取编号
     */
	public java.lang.String getCode(){
		return this.code;
	}
 		
	/**
     * 设置编号
     */
	public void setCode(java.lang.String code){
		this.code = code;
	}
 		
	/**
     * 获取第一分类id
     */
	public java.lang.Integer getFirstCategoryId(){
		return this.firstCategoryId;
	}
 		
	/**
     * 设置第一分类id
     */
	public void setFirstCategoryId(java.lang.Integer firstCategoryId){
		this.firstCategoryId = firstCategoryId;
	}
 		
	/**
     * 获取第一分类名称
     */
	public java.lang.String getFirstCategoryName(){
		return this.firstCategoryName;
	}
 		
	/**
     * 设置第一分类名称
     */
	public void setFirstCategoryName(java.lang.String firstCategoryName){
		this.firstCategoryName = firstCategoryName;
	}
 		
	/**
     * 获取第二分类id
     */
	public java.lang.Integer getSecondCategoryId(){
		return this.secondCategoryId;
	}
 		
	/**
     * 设置第二分类id
     */
	public void setSecondCategoryId(java.lang.Integer secondCategoryId){
		this.secondCategoryId = secondCategoryId;
	}
 		
	/**
     * 获取第二分类名称
     */
	public java.lang.String getSecondCategoryName(){
		return this.secondCategoryName;
	}
 		
	/**
     * 设置第二分类名称
     */
	public void setSecondCategoryName(java.lang.String secondCategoryName){
		this.secondCategoryName = secondCategoryName;
	}
 		
	/**
     * 获取第三分类id
     */
	public java.lang.Integer getThreeCategoryId(){
		return this.threeCategoryId;
	}
 		
	/**
     * 设置第三分类id
     */
	public void setThreeCategoryId(java.lang.Integer threeCategoryId){
		this.threeCategoryId = threeCategoryId;
	}
 		
	/**
     * 获取第三分类名称
     */
	public java.lang.String getThreeCategoryName(){
		return this.threeCategoryName;
	}
 		
	/**
     * 设置第三分类名称
     */
	public void setThreeCategoryName(java.lang.String threeCategoryName){
		this.threeCategoryName = threeCategoryName;
	}
 		
	/**
     * 获取一级产地id
     */
	public java.lang.Integer getFirstOriginId(){
		return this.firstOriginId;
	}
 		
	/**
     * 设置一级产地id
     */
	public void setFirstOriginId(java.lang.Integer firstOriginId){
		this.firstOriginId = firstOriginId;
	}
 		
	/**
     * 获取一级产地名称
     */
	public java.lang.String getFirstOriginName(){
		return this.firstOriginName;
	}
 		
	/**
     * 设置一级产地名称
     */
	public void setFirstOriginName(java.lang.String firstOriginName){
		this.firstOriginName = firstOriginName;
	}
 		
	/**
     * 获取二级产地id
     */
	public java.lang.Integer getSecondOriginId(){
		return this.secondOriginId;
	}
 		
	/**
     * 设置二级产地id
     */
	public void setSecondOriginId(java.lang.Integer secondOriginId){
		this.secondOriginId = secondOriginId;
	}
 		
	/**
     * 获取二级产地名称
     */
	public java.lang.String getSecondOriginName(){
		return this.secondOriginName;
	}
 		
	/**
     * 设置二级产地名称
     */
	public void setSecondOriginName(java.lang.String secondOriginName){
		this.secondOriginName = secondOriginName;
	}
 		
	/**
     * 获取三级产地id
     */
	public java.lang.Integer getThreeOriginId(){
		return this.threeOriginId;
	}
 		
	/**
     * 设置三级产地id
     */
	public void setThreeOriginId(java.lang.Integer threeOriginId){
		this.threeOriginId = threeOriginId;
	}
 		
	/**
     * 获取三级产地名称
     */
	public java.lang.String getThreeOriginName(){
		return this.threeOriginName;
	}
 		
	/**
     * 设置三级产地名称
     */
	public void setThreeOriginName(java.lang.String threeOriginName){
		this.threeOriginName = threeOriginName;
	}
 		
	/**
     * 获取时令id
     */
	public java.lang.Integer getSeasonId(){
		return this.seasonId;
	}
 		
	/**
     * 设置时令id
     */
	public void setSeasonId(java.lang.Integer seasonId){
		this.seasonId = seasonId;
	}
 		
	/**
     * 获取时令名称
     */
	public java.lang.String getSeasonName(){
		return this.seasonName;
	}
 		
	/**
     * 设置时令名称
     */
	public void setSeasonName(java.lang.String seasonName){
		this.seasonName = seasonName;
	}
 		
	/**
     * 获取供应商id
     */
	public java.lang.Integer getSupplierId(){
		return this.supplierId;
	}
 		
	/**
     * 设置供应商id
     */
	public void setSupplierId(java.lang.Integer supplierId){
		this.supplierId = supplierId;
	}
 		
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
     * 获取计算单位id
     */
	public java.lang.Integer getUnitId(){
		return this.unitId;
	}
 		
	/**
     * 设置计算单位id
     */
	public void setUnitId(java.lang.Integer unitId){
		this.unitId = unitId;
	}
 		
	/**
     * 获取计算单位名称
     */
	public java.lang.String getUnitName(){
		return this.unitName;
	}
 		
	/**
     * 设置计算单位名称
     */
	public void setUnitName(java.lang.String unitName){
		this.unitName = unitName;
	}
 		
	/**
     * 获取计算单位数值
     */
	public java.lang.Integer getUnitNum(){
		return this.unitNum;
	}
 		
	/**
     * 设置计算单位数值
     */
	public void setUnitNum(java.lang.Integer unitNum){
		this.unitNum = unitNum;
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
 }