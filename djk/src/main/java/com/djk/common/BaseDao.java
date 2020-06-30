package com.djk.common;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

public interface BaseDao<T> {

	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(T entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<T> findList(Page<T> page,T entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity);
	
	
	
	
}