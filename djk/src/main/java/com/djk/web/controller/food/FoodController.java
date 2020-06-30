package com.djk.web.controller.food;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.common.PageInfoBT;
import com.djk.web.entity.food.Food;
import com.djk.web.entity.food.FoodCategory;
import com.djk.web.entity.food.FoodNutritionComponent;
import com.djk.web.entity.food.FoodOrigin;
import com.djk.web.entity.food.FoodPublicCategory;
import com.djk.web.entity.food.FoodSeasonal;
import com.djk.web.entity.food.FoodSupplier;
import com.djk.web.entity.food.Tree;
import com.djk.web.entity.nutrition.NutritionComponent;
import com.djk.web.entity.systemResource.SystemWeight;
import com.djk.web.service.food.FoodCatetoryService;
import com.djk.web.service.food.FoodOriginService;
import com.djk.web.service.food.FoodSeasonalService;
import com.djk.web.service.food.FoodService;
import com.djk.web.service.food.FoodSupplierService;
import com.djk.web.util.ResultMap;

@Controller
public class FoodController {

	@Resource
	private FoodSupplierService foodSupplierService;
	@Resource
	private FoodSeasonalService foodSeasonalService;
	@Resource
	private FoodOriginService foodOriginService;
	@Resource
	private FoodService foodService;
	@Resource
	private FoodCatetoryService foodCatetoryService;
	
	/**
	 * 食物维护跳转
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/foodmain.html")
    public String chargeCatalogList(Map<String, Object> dataMap,HttpServletRequest request,
    		String foodCategoryName, Integer foodCategoryId) throws Exception {
		dataMap.put("foodCategoryName", foodCategoryName);
		dataMap.put("foodCategoryId", foodCategoryId);
		return "food/foodmain";
    }
	

	/**
	 * 跳转到食物详情页面
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/foodbrower", method = { RequestMethod.GET })
	public String foodBrower(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		Food food = foodService.get(id);
		// 查询供应商列表
		List<FoodSupplier> foodSupplierList = foodSupplierService.findList(null);
		// 时令查询
		List<FoodSeasonal> foodSeasonalList = foodSeasonalService.findList(null);
		// 营养组分
		List<FoodNutritionComponent> foodNutritionComponentList = foodService.getFoodNutritionComponentList(id);
		// 公共分类
		List<Map<String,Object>>  foodPublicCategoryList= foodCatetoryService.getPublicCategoryCheckListByFoodCategoryId(food.getId(), food.getFirstCategoryId(), null, null);
		// 重量单位
		List<SystemWeight> systemWeightList = foodService.findSystemWeightList();
		
		dataMap.put("systemWeightList", systemWeightList);
		dataMap.put("foodSupplierList", foodSupplierList);
		dataMap.put("foodSeasonalList", foodSeasonalList);
		dataMap.put("foodNutritionComponentList", foodNutritionComponentList);
		dataMap.put("food", food);
		dataMap.put("foodPublicCategoryList", foodPublicCategoryList);
		return "food/foodbrower";
	}
	
	/**
	 * 验证食物名称是否存在，存在返回true，不存在返回false
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/validatefoodnameexist")
	public @ResponseBody Boolean validateFoodNameExist(String name) {
		return foodService.validateFoodNameExist(name);
	}
	
	/**
	 * 食物添加页跳转
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/foodadd")
    public String foodAdd(Map<String, Object> dataMap,HttpServletRequest request, Food food) throws Exception {
	
		// 查询供应商列表
		List<FoodSupplier> foodSupplierList = foodSupplierService.findList(null);
		// 时令查询
		List<FoodSeasonal> foodSeasonalList = foodSeasonalService.findList(null);
		// 营养组分
		List<NutritionComponent> nutritionComponentList = foodService.getComponentList();
		// 重量单位
		List<SystemWeight> systemWeightList = foodService.findSystemWeightList();
		dataMap.put("systemWeightList", systemWeightList);
		//findOriginList(); 
		dataMap.put("foodSupplierList", foodSupplierList);
		dataMap.put("foodSeasonalList", foodSeasonalList);
		dataMap.put("food", food);
		dataMap.put("nutritionComponentList", nutritionComponentList);
		return "food/foodadd";
    }
	
	/**
	 * 食物添加
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/foodcreate")
	public String foodCreate(Map<String, Object> dataMap,HttpServletRequest request, Food food) throws Exception {
		//获取请求中的myToken的随机值
		String m=request.getParameter("myToken");
		
		//获取第一次访问页面时在session中设的值 （在自定义标签中做的操作）
		Object m1=request.getSession().getAttribute("myToken");
		
		//当m不为空   说明表单提交   需要判断是否是重复提交
		if(m!=null){
			//当m1不为空  session中有值   表单不是重复提交
			if(m1!=null){
				//为了防止表单参数被篡改   需要判断隐藏表单和session中的值是否相等
				if(m.equals(m1)){
					//把session中的唯一表示符清除
					request.getSession().removeAttribute("myToken");
					foodService.foodCreate(request, food);
				}
			}
		}
		
		dataMap.put("foodCategoryName", food.getFirstCategoryName());
		dataMap.put("foodCategoryId", food.getFirstCategoryId());
		FoodCategory foodCategory = foodCatetoryService.get(food.getFirstCategoryId());
		dataMap.put("foodCategoryPId", foodCategory.getPid());
		return "food/foodmain";
	}
	
	/**
	 * 更新食物
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/foodupdate")
	public String foodUpdate(Map<String, Object> dataMap,HttpServletRequest request, Food food) throws Exception {
		
		foodService.foodUpdate(request, food);
		dataMap.put("foodCategoryName", food.getFirstCategoryName());
		dataMap.put("foodCategoryId", food.getFirstCategoryId());
		FoodCategory foodCategory = foodCatetoryService.get(food.getFirstCategoryId());
		dataMap.put("foodCategoryPId", foodCategory.getPid());
		/*String pIds = foodCatetoryService.getParentIdStr(food.getFirstCategoryId());
		dataMap.put("pIds", pIds);*/
		return "food/foodmain";
	}
	
	/**
	 * 复制食物
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/foodcopy")
	public @ResponseBody ResultMap foodCopy(Map<String, Object> dataMap,HttpServletRequest request, Integer foodId) throws Exception {
		int i = 0;
		try{
			foodService.foodCopy(request, foodId);
		}catch(Exception e) {
			i = 1;
			e.printStackTrace();
		}
		
		ResultMap r = new ResultMap();
		if(i == 0) {
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	
	/**
	 * 查询食物公共分类信息
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/publiccategorycheck/list", method = { RequestMethod.POST })
    public @ResponseBody List<Map<String, Object>> getPublicCategoryCheckList(Map<String, Object> dataMap,
    		HttpServletRequest request, Integer foodCategoryId, Integer pid, Integer id) {
		// 公共分类
		List<Map<String,Object>>  foodPublicCategoryList= foodCatetoryService.getPublicCategoryCheckListByFoodCategoryId(id, foodCategoryId, null, null);
		return foodPublicCategoryList;
	}
			
	/**
	 * 跳转到编辑食物页面
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/foodedit", method = { RequestMethod.GET })
	public String foodFdit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		Food food = foodService.get(id);
		// 查询供应商列表
		List<FoodSupplier> foodSupplierList = foodSupplierService.findList(null);
		// 时令查询
		List<FoodSeasonal> foodSeasonalList = foodSeasonalService.findList(null);
		// 食物营养组分
		List<FoodNutritionComponent> foodNutritionComponentList = foodService.getFoodNutritionComponentList(id);
		// 食物中不存在的营养组分
		//List<NutritionComponent> nutritionComponentList = foodService.getNutritionComponentList(id);
		// 公共分类
		List<Map<String,Object>>  foodPublicCategoryList= foodCatetoryService.getPublicCategoryCheckListByFoodCategoryId(food.getId(), food.getFirstCategoryId(), null, null);
		// 重量单位
		List<SystemWeight> systemWeightList = foodService.findSystemWeightList();
		
		//dataMap.put("nutritionComponentList", nutritionComponentList);
		dataMap.put("systemWeightList", systemWeightList);
		dataMap.put("foodSupplierList", foodSupplierList);
		dataMap.put("foodSeasonalList", foodSeasonalList);
		dataMap.put("foodNutritionComponentList", foodNutritionComponentList);
		dataMap.put("food", food);
		dataMap.put("foodPublicCategoryList", foodPublicCategoryList);
		return "food/foodedit";
	}
	
	/**
	 * 根据foodid查询食物下公共分类信息
	 * @param dataMap
	 * @param request
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/findpubliccategorycheck", method = { RequestMethod.GET })
	public @ResponseBody List<Integer> findPublicCategoryCheck(Map<String, Object> dataMap, HttpServletRequest request,Integer foodId) throws Exception {
		List<Integer> publicCategoryCheckIds = foodService.findPublicCategoryCheck(foodId);
		return publicCategoryCheckIds;
	}
	
	
	/**
	 * 删除食物
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deletefood", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap deleteFood(HttpServletRequest request, Food food) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(food.getId()!=null){
			result = foodService.delete(food.getId());
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 跳转食物信息页面
	 * @return
	 */
	@RequestMapping(value = "/foodlist")
	public String foodlist(Map<String, Object> dataMap, HttpServletRequest request, Integer foodCategoryId) {
		dataMap.put("foodCategoryId", foodCategoryId);
		return "food/foodlist";
	}
	
	/**
	 * 根据食物分类id查询分类下食物信息
	 * @return
	 */
	@RequestMapping(value = "/foodlistbygategoryid")
	public @ResponseBody PageInfoBT<Food> foodListByGategoryId(Map<String, Object> dataMap, 
			HttpServletRequest request, Integer foodCategoryId, String searchValue,
			Food food) {
		food.setName(searchValue);
		food.setFirstCategoryId(foodCategoryId);
		PageInfoBT<Food> findPage = foodService.findPage(food);
		return findPage;
	}
	
	/**
	 * 查询产地信息
	 * @return
	 */
	@RequestMapping(value = "/findOriginList")
	public @ResponseBody List<Map<String, List<String>>> findOriginList() {
		
		List<Map<String, List<String>>> result = new ArrayList<Map<String, List<String>>>();
		Map<String, List<String>> oneMap = new HashMap<String, List<String>>();
		Map<String, List<String>> twoMap = new HashMap<String, List<String>>();
		Map<String, List<String>> threeMap = new HashMap<String, List<String>>();
		
		List<FoodOrigin> oneFindList = foodOriginService.findListByPId(0);

		List<String> oneList = new ArrayList<String>();
		for(int i=0;i<oneFindList.size();i++){ //一级
			FoodOrigin foodOrigin = oneFindList.get(i);
			oneList.add(foodOrigin.getOriginName());
			List<FoodOrigin> twoFindList = foodOriginService.findListByPId(foodOrigin.getId());
			List<String> twoList = new ArrayList<String>();
			for(int j=0; j<twoFindList.size(); j++) {
				FoodOrigin twoFoodOrigin = twoFindList.get(j);
				twoList.add(twoFoodOrigin.getOriginName());
				List<FoodOrigin> threeFindList = foodOriginService.findListByPId(twoFoodOrigin.getId());
				List<String> threeList = new ArrayList<String>();
				for(int z=0; z<threeFindList.size(); z++) {
					FoodOrigin threeFoodOrigin = threeFindList.get(z);
					threeList.add(threeFoodOrigin.getOriginName());
				}
				if(threeList != null && threeList.size() > 0) {
					threeMap.put("0_"+i+"_"+j, threeList);
				}
			}
			if(twoList != null && twoList.size() > 0) {
				twoMap.put("0_"+i, twoList);
			}
		}
		
		oneMap.put("0", oneList);
		result.add(oneMap);
		result.add(twoMap);
		result.add(threeMap);
		
		return result;
	}
}
