package com.djk.web.dao.nutrition;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.nutrition.NutritionCategory;





@Repository
public interface NutritionCategoryWriteDao {
 
	NutritionCategory get(java.lang.Integer id);
	
    NutritionCategory selectChildrenById(java.lang.Integer id);
	
	
	NutritionCategory getCategory(NutritionCategory digeDining);
	
	Integer insert(NutritionCategory digeDining);
	
	Integer update(NutritionCategory digeDining);
	
	
	Integer uppids(NutritionCategory digeDining);
	
	
	Integer checkDelete(String cid);
	
	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(NutritionCategory entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<NutritionCategory> findList(Page<NutritionCategory> page,NutritionCategory entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<NutritionCategory> findList(NutritionCategory entity);
	
	
	
	/**
	 * 根据id查询食物分类是否有子分类
	 * @param id
	 * @return
	 */
	public Integer isSubNodeById(int id);
	
	/**
	 * 根据pId查询下级分类信息
	 * @param pId 父id
	 * @return
	 */
	public List<NutritionCategory> getCategoryListByPId(int pId);
	
	
	
	/**
	 * 根据pId查询下级分类信息
	 * @param pId 父id
	 * @return
	 */
	public List<NutritionCategory> getCategoryListById(int id);
	
	
	
	
	
}