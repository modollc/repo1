package com.djk.web.dao.dige;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.dige.DigeNutrition;


@Repository
public interface DigeNutritionWriteDao {
 
	DigeNutrition get(java.lang.Integer id);
	
	Integer insert(DigeNutrition digeNutrition);
	
	Integer update(DigeNutrition digeNutrition);
	
	DigeNutrition checkNameUnique(String nutritionQuantity);
	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(DigeNutrition entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<DigeNutrition> findList(Page<DigeNutrition> page,DigeNutrition entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<DigeNutrition> findList(DigeNutrition entity);
}