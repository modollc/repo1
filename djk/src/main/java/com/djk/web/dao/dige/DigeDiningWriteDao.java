package com.djk.web.dao.dige;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.dige.DigeDining;




@Repository
public interface DigeDiningWriteDao {
 
	DigeDining get(java.lang.Integer id);
	
	Integer insert(DigeDining digeDining);
	
	Integer update(DigeDining digeDining);
	
	DigeDining checkNameUnique(String mealQuantity);
	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(DigeDining entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<DigeDining> findList(Page<DigeDining> page,DigeDining entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<DigeDining> findList(DigeDining entity);
}