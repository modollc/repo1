package com.djk.web.entity.food;


import com.djk.common.DataModel;
/**
 * 食物营养公共资源-产地
 * <p>Table: <strong>food_origin</strong>
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
 *   <tr><td>originName</td><td>{@link java.lang.String}</td><td>origin_name</td><td>varchar</td><td>产地名称</td></tr>
 *   <tr><td>parentId</td><td>{@link java.lang.Integer}</td><td>parent_id</td><td>int</td><td>上级ID</td></tr>
 * </table>
 *
 */
public class FoodOrigin extends DataModel<FoodOrigin> {
 
 
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.String originName;
 	private java.lang.Integer parentId;
 	
 		

	/**
     * 获取产地名称
     */
	public java.lang.String getOriginName(){
		return this.originName;
	}
 		
	/**
     * 设置产地名称
     */
	public void setOriginName(java.lang.String originName){
		this.originName = originName;
	}
 		
	/**
     * 获取上级ID
     */
	public java.lang.Integer getParentId(){
		return this.parentId;
	}
 		
	/**
     * 设置上级ID
     */
	public void setParentId(java.lang.Integer parentId){
		this.parentId = parentId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FoodOrigin [originName=" + originName + ", parentId="
				+ parentId + "]";
	}
	
	
 }