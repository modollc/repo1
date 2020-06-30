package com.djk.web.controller.food;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.web.entity.food.FoodCategory;
import com.djk.web.entity.food.FoodPublicCategory;
import com.djk.web.entity.log.SysLog;
import com.djk.web.entity.user.SysUser;
import com.djk.web.service.food.FoodCatetoryService;
import com.djk.web.util.PagerInfo;
import com.djk.web.util.PaginationUtil;
import com.djk.web.util.WebUtil;

/**
 * 食物分类
 * @author llc
 *
 */
@Controller
@RequestMapping("/foodcategory")
public class FoodCategoryController {

	@Resource
	private FoodCatetoryService foodCatetoryService;
	
	/**
	 * 查询食物公共分类信息
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "publiccategory/list", method = { RequestMethod.POST })
    public @ResponseBody List<Map<String, Object>> getPublicCategoryList(Map<String, Object> dataMap,
    		HttpServletRequest request, Integer foodCategoryId, Integer pid, Integer id) {
		return foodCatetoryService.getPublicCategoryListByFoodCategoryId(foodCategoryId, pid, id);
    }
	/**
	 * 查询食物分类信息
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "list", method = { RequestMethod.POST })
	public @ResponseBody List<Map<String, Object>> chargeCatalogList(Map<String, Object> dataMap,HttpServletRequest request, Integer id) {
		return foodCatetoryService.getFoodCategoryListByPId(id);
	}
	
	
	/**
	 * 保存食物分类
	 * @param pId 食物分类父id
	 * @param name 食物分类名称
	 * @return
	 */
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public @ResponseBody FoodCategory addFoodCategory(HttpServletRequest request, Integer pid, String name, Boolean publicCategoryCopy) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return foodCatetoryService.addFoodCategory(pid, name, user.getId(), publicCategoryCopy);
	}
	
	/**
	 * 修改食物分类
	 * @param id 食物分类id
	 * @param name 食物分类名称
	 * @return
	 */
	@RequestMapping(value = "update", method = { RequestMethod.POST })
	public @ResponseBody FoodCategory updateFoodCategory(HttpServletRequest request, Integer id, String name) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return foodCatetoryService.updateFoodCategory(id, name, user.getId());
	}
	
	/**
	 * 验证分类下是否有子分类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "validateChild", method = { RequestMethod.POST })
	public @ResponseBody Boolean validateChild(Integer id) {
		return foodCatetoryService.validateChild(id);
	}
	
	/**
	 * 删除食物分类
	 * @param id 食物分类id
	 * @return
	 */
	@RequestMapping(value = "delete", method = { RequestMethod.POST })
	public @ResponseBody Boolean deleteFoodCategory(HttpServletRequest request, Integer id) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");

		// 查询食物分类是否被引用
		Integer count = foodCatetoryService.queryFoodGategoryRel(id);
		if(count != null && count > 0) {
			return false;
		}
		return foodCatetoryService.deleteFoodCategory(id, user.getId());
	}
	
	/**
	 * 保存公共分类
	 * @param pId 分类父id
	 * @param name 分类名称
	 * @return
	 */
	@RequestMapping(value = "addpublic", method = { RequestMethod.POST })
	public @ResponseBody FoodPublicCategory addPublicFoodCategory(HttpServletRequest request, Integer pid, String name, Integer foodCategoryId) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return foodCatetoryService.addPublicFoodCategory(pid, name, user.getId(), foodCategoryId);
	}
	
	/**
	 * 修改公共分类
	 * @param id 分类id
	 * @param name 分类名称
	 * @return
	 */
	@RequestMapping(value = "updatepublic", method = { RequestMethod.POST })
	public @ResponseBody FoodPublicCategory updatePublicFoodCategory(HttpServletRequest request, Integer id, String name) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return foodCatetoryService.updatePublicFoodCategory(id, name, user.getId());
	}
	
	/**
	 * 验证分类下是否有子分类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "validatepublicchild", method = { RequestMethod.POST })
	public @ResponseBody Boolean validatePublicChild(Integer id) {
		return foodCatetoryService.validatePublicChild(id);
	}
	
	/**
	 * 删除食物分类
	 * @param id 食物分类id
	 * @return
	 */
	@RequestMapping(value = "deletepublic", method = { RequestMethod.POST })
	public @ResponseBody Map<String, String> deletePublicFoodCategory(HttpServletRequest request, Integer id) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return foodCatetoryService.deletePublicFoodCategory(id, user.getId());
	}
	
	/**
	 * 复制食物分类及其公共分类信息
	 * @param id 食物分类id
	 * @return
	 */
	@RequestMapping(value = "copy", method = { RequestMethod.POST })
	public @ResponseBody FoodCategory copyFoodCategory(HttpServletRequest request, Integer id) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return foodCatetoryService.copyFoodCategory(id, user.getId());
		
	}
	
	/**
	 * 公共分类页面跳转
	 * @param dataMap
	 * @return
	 */
    @RequestMapping(value = "publiccategory.html")
    public String getPublicCategoryList(Map<String, Object> dataMap) {
    
    	return "food/publiccategory";
    }
    
    @RequestMapping(value = "validatacategoryname")
    public @ResponseBody Boolean validataCategoryName(Map<String, Object> dataMap, String name) {
    	
    	Integer count = foodCatetoryService.validataCategoryName(name);
    	if(count > 0) {
    		return true;
    	}else{
    		return false;
    	}
    }
}
