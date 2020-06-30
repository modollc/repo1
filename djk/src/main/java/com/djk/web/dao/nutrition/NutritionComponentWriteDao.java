package com.djk.web.dao.nutrition;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.nutrition.NutritionComponent;




@Repository
public interface NutritionComponentWriteDao {
	
	NutritionComponent get(java.lang.Integer id);
	
	Integer checkDelete(String name);
	
	
	Integer insert(NutritionComponent digeDining);
	
	Integer update(NutritionComponent digeDining);
	
	NutritionComponent checkNameUnique(String name);
	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(NutritionComponent entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<NutritionComponent> findList(Page<NutritionComponent> page,NutritionComponent entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<NutritionComponent> findList(NutritionComponent entity);
}