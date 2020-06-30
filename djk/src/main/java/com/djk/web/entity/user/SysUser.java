package com.djk.web.entity.user;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
/**
 * 
 * <p>Table: <strong>sys_user</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>姓名</td></tr>
 *   <tr><td>userName</td><td>{@link java.lang.String}</td><td>user_name</td><td>varchar</td><td>用户名</td></tr>
 *   <tr><td>pwd</td><td>{@link java.lang.String}</td><td>pwd</td><td>varchar</td><td>密码</td></tr>
 *   <tr><td>roleId</td><td>{@link java.lang.Integer}</td><td>role_id</td><td>int</td><td>roleId</td></tr>
 *   <tr><td>operateTime</td><td>{@link java.util.Date}</td><td>operate_time</td><td>datetime</td><td>操作时间</td></tr>
 * </table>
 *
 */
public class SysUser implements Serializable{
 
 	private java.lang.Integer id;
 	private java.lang.String name;
 	private java.lang.String userName;
 	private java.lang.String pwd;
 	private java.lang.Integer roleId;
 	private java.util.Date operateTime;
 	
 		
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
     * 获取姓名
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置姓名
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取用户名
     */
	public java.lang.String getUserName(){
		return this.userName;
	}
 		
	/**
     * 设置用户名
     */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
 		
 		
	/**
     * 获取密码
     */
	public java.lang.String getPwd(){
		return this.pwd;
	}
 		
	/**
     * 设置密码
     */
	public void setPwd(java.lang.String pwd){
		this.pwd = pwd;
	}
 		
	/**
     * 获取roleId
     */
	public java.lang.Integer getRoleId(){
		return this.roleId;
	}
 		
	/**
     * 设置roleId
     */
	public void setRoleId(java.lang.Integer roleId){
		this.roleId = roleId;
	}
 		
	/**
     * 获取操作时间
     */
	public java.util.Date getOperateTime(){
		return this.operateTime;
	}
 		
	/**
     * 设置操作时间
     */
	public void setOperateTime(java.util.Date operateTime){
		this.operateTime = operateTime;
	}

	
 		
 }