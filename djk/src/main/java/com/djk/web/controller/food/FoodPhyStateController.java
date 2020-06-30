package com.djk.web.controller.food;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.common.PageInfoBT;
import com.djk.common.StringUtils;

import com.djk.web.entity.food.FoodPhyState;
import com.djk.web.entity.nutrition.NutritionComponent;

import com.djk.web.service.food.FoodPhyStateService;

import com.djk.web.util.ResultMap;

/**
 * 食物物理状态
 * @author 邢广军
 *
 */
@Controller
@RequestMapping("/food/foodPhyState/")
public class FoodPhyStateController {

	
	@Resource(name="foodPhyStateService")
	private FoodPhyStateService foodPhyStateService;

	
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		
		
		return "food/foodPhyState";
	}
	
	
	
	/**
	 * 
	 * @Title: list
	 * @Description: TODO 默认列表
	 * @param @param dataMap
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		FoodPhyState foodPhyState=new FoodPhyState();
		foodPhyState.setFoodStatus(request.getParameter("searchValue"));
		PageInfoBT<FoodPhyState> findPage = foodPhyStateService.findPage(foodPhyState);
		return findPage;
	}
	
	/**
	 * 添加
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "food/add";
	}
	
	/**
	 * 修改
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		FoodPhyState foodPhyState = foodPhyStateService.get(id);
		dataMap.put("foodPhyState", foodPhyState);
		return "food/edit";
	}
	

	
	/**
	 * 删除
	 * @param request
	 * @param foodPhyState
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, FoodPhyState foodPhyState) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodPhyState.getId()!=null){
			
			result = foodPhyStateService.delete(foodPhyState.getId().toString());
		}
		if(result>0){
			
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	
	/**
	 * 
	 * @Title: add
	 * @Description: TODO 新增户
	 * @param @param request
	 * @param @param sysuser
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, FoodPhyState foodPhyState) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodPhyState.getId()!=null){
			foodPhyState.preUpdate();
			result = foodPhyStateService.update(foodPhyState);
		}else{
			foodPhyState.preInsert();
			result = foodPhyStateService.insert(foodPhyState);
		}
		if(result>0){
			
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	
	
	/**
	 * 校验名称
	 * @param foodPhyState
	 * @return
	 */
    @RequestMapping(value = "checkNameUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(FoodPhyState foodPhyState)
    {
        int uniqueFlag = 0;
        if (foodPhyState != null)
        {
             Integer id = foodPhyState.getId();
             FoodPhyState info = foodPhyStateService.checkNameUnique(foodPhyState.getFoodStatus());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
        return uniqueFlag;
    }
}
