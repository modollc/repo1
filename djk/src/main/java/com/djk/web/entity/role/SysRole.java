package com.djk.web.entity.role;

import java.io.Serializable;

/**
 * 角色实体
 * @author 李连超
 *
 */
public class SysRole implements Serializable{

	private Integer id;//主键
	private String name;//角色名称
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
