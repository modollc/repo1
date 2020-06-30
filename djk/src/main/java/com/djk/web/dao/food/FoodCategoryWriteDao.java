package com.djk.web.dao.food;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.djk.common.BaseDao;
import com.djk.web.entity.food.FoodCategory;
import com.djk.web.entity.food.FoodPhyState;
import com.djk.web.entity.food.FoodPublicCategory;
import com.djk.web.entity.food.FoodPublicCategoryCheck;


/**
 * 食物分类
 * @author llc
 *
 */
@Repository
public interface FoodCategoryWriteDao extends BaseDao<FoodCategory>{
 
	FoodCategory get(java.lang.Integer id);
	
	Integer insert(FoodCategory foodCategory);
	
	Integer update(FoodCategory foodCategory);
	
	List<FoodCategory> page(@Param("queryMap") Map<String, String> queryMap,@Param("start") Integer start, @Param("size") Integer size);

	Integer count(@Param("queryMap") Map<String, String> queryMap);
	
	/**
	 * // 查询公共分类是否被引用
	 * @param id
	 * @return
	 */
	public Integer queryFoodPublicCategoryRel(Integer id);
	
	// 查询食物分类是否被引用
	public Integer queryFoodGategoryRel(Integer id);
	
	/**
	 * 获取所有父类id用“,”分割
	 * @param id
	 * @return
	 */
	public String getParentIdStr(Integer id);
	
	/**
	 * 根据父类id查询父类codevalue值
	 * @param id
	 * @return
	 */
	public String queryParentCodeValueById(Integer id);
	
	/**
	 * 根据分类pid查询分类code最大值
	 * @param pId
	 * @return
	 */
	public Integer queryCodeMaxByPId(Integer pId);
	
	/**
	 * 根据pId查询下级分类信息
	 * @param pId 父id
	 * @return
	 */
	public List<FoodCategory> getFoodCategoryListByPId(int pId);
	
	/**
	 * 根据id查询本机及所有子级分类信息
	 * @param id
	 * @return
	 */
	public List<FoodCategory> getFoodCategoryList(int id);
	
	/**
	 * 根据id查询食物分类是否有子分类
	 * @param id
	 * @return
	 */
	public Integer isSubNodeById(int id);
	
	/**
	 * 根据id查询公共分类是否有子分类
	 * @param id
	 * @return
	 */
	public Integer isSubNodePublicById(int id);
	
	/**
	 * 根据id查询菜单是否有子菜单
	 * @param id
	 * @return
	 */
	public Integer isPublicSubNodeById(int id);
	
	/**
	 * 根据id查询菜单是否有子菜单
	 * @param id
	 * @return
	 */
	public Integer isPublicSubNodeCheckById(int id);
	
	/**
	 * 根据id删除分类
	 * @param id
	 * @return
	 */
	public Integer delete(int id);
	
	/**
	 * 根据id，查询公共分类
	 * @param id
	 * @return
	 */
	public FoodPublicCategory getPublicCategoryById(@Param("id") Integer id);
	
	/**
	 * 根据食物分类id，公共分类id查询公共分类信息
	 * @param foodCategoryId
	 * @param pId
	 * @return
	 */
	public List<FoodPublicCategory> getPublicCategoryList(@Param("foodCategoryId") Integer foodCategoryId, @Param("pId") Integer pId);
	
	/**
	 * 根据食物分类id，公共分类id查询公共分类信息
	 * @param foodCategoryId
	 * @param pId
	 * @return
	 */
	public List<FoodPublicCategory> getPublicCategoryCheckList(@Param("foodId") Integer foodId, @Param("pId") Integer pId);
	
	/**
	 * 根据食物分类id查询公共分类信息
	 * @param foodCategoryId
	 * @return
	 */
	public List<FoodPublicCategory> getPublicCategoryListByfoodCategoryId(@Param("foodCategoryId") Integer foodCategoryId);
	
	  /**
     * 保存公共分类
     * @param pId 分类父id
     * @param name 分类名称
     * @return
     */ 
    public Integer insertPublicFoodCategory(FoodPublicCategory foodPublicCategory);
    
    /**
     * 更新公共分类
     * @param foodPublicCategory
     * @return
     */
    public Integer updatePublicFoodCategory(FoodPublicCategory foodPublicCategory);
    
    /**
     * 根据分类名称模糊查询分类信息
     * @param name
     * @return
     */
    public List<String> getFoodCategoryListByName(@Param("name") String name);
    
    /**
	 * 验证食物分类名称是否存在
	 * @param name
	 * @return
	 */
	public Integer validataCategoryName(String name);
	
}