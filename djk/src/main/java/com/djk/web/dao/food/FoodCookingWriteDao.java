package com.djk.web.dao.food;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.food.FoodCooking;



@Repository
public interface FoodCookingWriteDao {
 
	FoodCooking get(java.lang.Integer id);
	
	Integer insert(FoodCooking foodCooking);
	
	Integer update(FoodCooking foodCooking);

	FoodCooking checkNameUnique(String cookingMethod);
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(FoodCooking entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<FoodCooking> findList(Page<FoodCooking> page,FoodCooking entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<FoodCooking> findList(FoodCooking entity);
}