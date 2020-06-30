package com.djk.web.entity.dige;



import com.djk.common.DataModel;
/**
 * 消化-率场景
 * <p>Table: <strong>dige_scene</strong>
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
 *   <tr><td>sceneName</td><td>{@link java.lang.String}</td><td>scene_name</td><td>varchar</td><td>场景名称</td></tr>
 *   <tr><td>reference</td><td>{@link java.lang.String}</td><td>reference</td><td>varchar</td><td>参考值</td></tr>
 *   <tr><td>sceneValue</td><td>{@link java.math.BigDecimal}</td><td>scene_value</td><td>decimal</td><td>场景系数</td></tr>
 * </table>
 *
 */
public class DigeScene extends DataModel<DigeScene> {
 
 	
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String sceneName;
 	private java.lang.String reference;
 	private java.math.BigDecimal sceneValue;
 	
 
 		
	/**
     * 获取场景名称
     */
	public java.lang.String getSceneName(){
		return this.sceneName;
	}
 		
	/**
     * 设置场景名称
     */
	public void setSceneName(java.lang.String sceneName){
		this.sceneName = sceneName;
	}
 		
	/**
     * 获取参考值
     */
	public java.lang.String getReference(){
		return this.reference;
	}
 		
	/**
     * 设置参考值
     */
	public void setReference(java.lang.String reference){
		this.reference = reference;
	}
 		
	/**
     * 获取场景系数
     */
	public java.math.BigDecimal getSceneValue(){
		return this.sceneValue;
	}
 		
	/**
     * 设置场景系数
     */
	public void setSceneValue(java.math.BigDecimal sceneValue){
		this.sceneValue = sceneValue;
	}
 }