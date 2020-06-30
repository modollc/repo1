package com.djk.web.dao.food;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.food.FoodNutritionComponent;
import com.djk.web.entity.food.FoodSupplier;


@Repository
public interface FoodSupplierWriteDao {
 
	FoodSupplier get(java.lang.Integer id);
	
	Integer insert(FoodSupplier foodSeasonal);
	FoodSupplier checkNameUnique(String supplierName);
	Integer update(FoodSupplier foodSeasonal);
	
	
	/**
	 * 检查删除
	 * @param supplierName
	 * @return
	 */
	Integer checkDelete(String supplierName);
	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(FoodSupplier entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<FoodSupplier> findList(Page<FoodSupplier> page,FoodSupplier entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<FoodSupplier> findList(FoodSupplier entity);
	
}