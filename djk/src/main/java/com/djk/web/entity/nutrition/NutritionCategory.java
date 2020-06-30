package com.djk.web.entity.nutrition;



import java.util.Set;

import com.djk.common.DataModel;
/**
 * 营养分类
 * <p>Table: <strong>nutrition_category</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>分类名称</td></tr>
 *   <tr><td>alias</td><td>{@link java.lang.String}</td><td>alias</td><td>varchar</td><td>别名</td></tr>
 *   <tr><td>createBy</td><td>{@link java.lang.Integer}</td><td>create_by</td><td>int</td><td>创建人</td></tr>
 *   <tr><td>createDate</td><td>{@link java.util.Date}</td><td>create_date</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateBy</td><td>{@link java.lang.Integer}</td><td>update_by</td><td>int</td><td>更新用户</td></tr>
 *   <tr><td>updateDate</td><td>{@link java.util.Date}</td><td>update_date</td><td>datetime</td><td>更新时间</td></tr>
 *   <tr><td>code</td><td>{@link java.lang.String}</td><td>code</td><td>varchar</td><td>分类编号</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>int</td><td>状态：0，删除，1正常</td></tr>
 * </table>
 *
 */
public class NutritionCategory extends DataModel<NutritionCategory> {
 
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 	private java.lang.String name;
 	private java.lang.String alias;
 	private java.lang.String code;
 	private java.lang.Integer state;
 	private java.lang.Integer pid;
 	private java.lang.String pids;
 	private Set<NutritionCategory> list;
 	
 	
 	
 		
	
 		
	/**
	 * @return the list
	 */
	public Set<NutritionCategory> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(Set<NutritionCategory> list) {
		this.list = list;
	}

	/**
	 * @return the pids
	 */
	public java.lang.String getPids() {
		return pids;
	}

	/**
	 * @param pids the pids to set
	 */
	public void setPids(java.lang.String pids) {
		this.pids = pids;
	}

	/**
	 * @return the pid
	 */
	public java.lang.Integer getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(java.lang.Integer pid) {
		this.pid = pid;
	}

	/**
     * 获取分类名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置分类名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取别名
     */
	public java.lang.String getAlias(){
		return this.alias;
	}
 		
	/**
     * 设置别名
     */
	public void setAlias(java.lang.String alias){
		this.alias = alias;
	}
 		
	
 		
	/**
     * 获取分类编号
     */
	public java.lang.String getCode(){
		return this.code;
	}
 		
	/**
     * 设置分类编号
     */
	public void setCode(java.lang.String code){
		this.code = code;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NutritionCategory [name=" + name + ", alias=" + alias
				+ ", code=" + code + ", state=" + state + ", pid=" + pid
				+ ", pids=" + pids + ", list=" + list + "]";
	}

	
	
 }