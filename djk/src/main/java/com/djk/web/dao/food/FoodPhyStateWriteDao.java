package com.djk.web.dao.food;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;

import com.djk.web.entity.food.FoodPhyState;




@Repository
public interface FoodPhyStateWriteDao {
 
	FoodPhyState get(java.lang.Integer id);
	
	Integer insert(FoodPhyState foodPhyState);
	
	Integer update(FoodPhyState foodPhyState);
	
	FoodPhyState checkNameUnique(String foodStatus);
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(FoodPhyState entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<FoodPhyState> findList(Page<FoodPhyState> page,FoodPhyState entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<FoodPhyState> findList(FoodPhyState entity);
	
	
}