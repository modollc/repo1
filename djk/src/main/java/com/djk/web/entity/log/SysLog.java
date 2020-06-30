package com.djk.web.entity.log;

import java.util.Date;

/**
 * 角色实体
 * @author 李连超
 *
 */
public class SysLog {

	private Integer id;//主键
	private String ipAddress;//ip地址
	private String userName;//登录名
	private Date loginTime;//登录时间
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	} 
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
