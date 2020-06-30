package com.djk.common;

import java.io.Serializable;

import com.baomidou.mybatisplus.plugins.Page;



/**
 * model基础支持类
 * @author jun
 *
 */
public abstract class BaseModel<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号
	 */
	private java.lang.Integer id;
	
	
	
	
	
	/**
	 * 当前实体分页对象
	 */
	private Page<T> page;
	
  





    /**
     * 插入之前执行方法,子类方法
     */
	public abstract void preInsert();
	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();
	public BaseModel() {
		super();
	}


	public BaseModel(java.lang.Integer id) {
		super();
		this.id = id;
	}


	public java.lang.Integer getId() {
		return id;
	}


	public void setId(java.lang.Integer id) {
		this.id = id;
	}






	
	public Page<T> getPage() {
		return page;
	}
	
	public void setPage(Page<T> page) {
		this.page = page;
	}


	
}
