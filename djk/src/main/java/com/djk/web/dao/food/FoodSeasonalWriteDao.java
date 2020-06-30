package com.djk.web.dao.food;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.food.FoodSeasonal;


@Repository
public interface FoodSeasonalWriteDao {
 
	FoodSeasonal get(java.lang.Integer id);
	
	Integer insert(FoodSeasonal foodSeasonal);
	
	Integer update(FoodSeasonal foodSeasonal);
	FoodSeasonal checkNameUnique(String seasonal);
	
	/**
	 * 检查删除
	 * @param seasonName
	 * @return
	 */
	Integer checkDelete(String seasonName);
	
	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(FoodSeasonal entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<FoodSeasonal> findList(Page<FoodSeasonal> page,FoodSeasonal entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<FoodSeasonal> findList(FoodSeasonal entity);
}