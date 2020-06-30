package com.djk.web.dao.food;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.food.Food;
import com.djk.web.entity.food.FoodNutritionComponent;
import com.djk.web.entity.food.FoodPublicCategoryCheck;
import com.djk.web.entity.nutrition.NutritionComponent;
import com.djk.web.entity.systemResource.SystemWeight;


@Repository
public interface FoodWriteDao {
 
	/**
	 * 验证食物名称是否存在，存在返回true，不存在返回false
	 * @param name
	 * @return
	 */
	public Integer validateFoodNameExist(String name);
	
	/**
	 * 查询食物营养组分中没有的营养组分
	 * @param id
	 * @return
	 */
	public List<NutritionComponent> getNutritionComponentList(Integer id); 
	
	/**
	 * 根据食物分类id查询分类下食物code最大值
	 * @param foodCategoryId
	 * @return
	 */
	public Integer queryCodeMax(Integer foodCategoryId);
	
	/**
	 * 根据食物名称查询食物信息列表
	 * @param name
	 * @return
	 */
	public List<String> getFoodListByName(String name);
	
	/**
	 * 查询重量单位
	 * @return
	 */
	public List<SystemWeight> findSystemWeightList(); 
	
	/**
	 * 根据食物id查询食物营养组分
	 * @param foodId
	 * @return
	 */
	public List<FoodNutritionComponent> getFoodNutritionComponentList(Integer foodId); 
	
	/**
	 * 查询食物营养组分
	 * @return
	 */
	public List<NutritionComponent> getComponentList();
	
	/**
	 * 查询食物信息
	 * @return
	 */
	public Food get(Integer id);
	
	/**
	 * 保存食物信息
	 * @param food
	 * @return
	 */
	public Integer insert(Food food);
	
	/**
	 * 更新食物信息
	 * @param food
	 * @return
	 */
	public Integer update(Food food);
	
	/**
	 * 删除食物信息
	 * @param id 食物id
	 * @return
	 */
	public Integer delete(Integer id);
	
	/**
	 * 删除食物营养组分信息
	 * @param foodId 食物id
	 * @return
	 */
	public Integer deleteFoodNutritionComponent(Integer foodId);
	
	/**
	 * 删除食物公共分类信息
	 * @param foodId 食物id
	 * @return
	 */
	public Integer deletePublicFoodCategoryCheck(Integer foodId);
	
	/**
     * 保存食物选择公共分类
     * @param pId 分类父id
     * @param name 分类名称
     * @return
     */ 
    public Integer insertPublicFoodCategoryCheck(FoodPublicCategoryCheck foodPublicCategoryCheck);
    
    /**
     * 更新食物选择公共分类
     * @param foodPublicCategory
     * @return
     */
    public Integer updatePublicFoodCategoryCheck(FoodPublicCategoryCheck foodPublicCategoryCheck);
    
    /**
     * 保存食物营养组分
     * @param foodNutritionComponent
     * @return
     */
    public Integer insertFoodNutritionComponent(FoodNutritionComponent foodNutritionComponent);
    
    /**
	 * 根据分类id查询分类下食物信息
	 * @param foodCategoryId
	 * @return
	 */
	public List<Food> foodListByGategoryId(Integer foodCategoryId);
	
	/**
	 * 统计分类下食物数量
	 * @param food
	 * @return
	 */
	public Integer count(Food food);
	
	/**
	 * 查询分类下食物列表
	 * @param food
	 * @return
	 */
	public List<Food> findList(Page<Food> page, Food food);
	
	/**
	 * 根据foodid查询食物下公共分类信息
	 * @param foodId
	 * @return
	 */
	public List<Integer> findPublicCategoryCheck(Integer foodId); 
	
	/**
	 * 根据foodid,分类id查询食物下公共分类信息
	 * @param foodId
	 * @return
	 */
	public List<FoodPublicCategoryCheck> getPublicCategoryCheckList(@Param("foodId") Integer foodId, @Param("foodCategoryId") Integer foodCategoryId); 
}