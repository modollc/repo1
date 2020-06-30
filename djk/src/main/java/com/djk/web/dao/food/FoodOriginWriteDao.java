package com.djk.web.dao.food;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.food.FoodOrigin;



@Repository
public interface FoodOriginWriteDao {
 
	FoodOrigin get(java.lang.Integer id);
	
	FoodOrigin getName(String originName);
	
	Integer insert(FoodOrigin foodOrigin);
	
	Integer update(FoodOrigin foodOrigin);
	
	Integer checkDelete(String originName);
	
	FoodOrigin checkNameUnique(String originName);
	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(FoodOrigin entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<FoodOrigin> findList(Page<FoodOrigin> page,FoodOrigin entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<FoodOrigin> findList(FoodOrigin entity);
	
	/**
	 * 根据父id查询地区信息
	 * @param parentId
	 * @return
	 */
	public List<FoodOrigin> findListByPId(Integer parentId);
}