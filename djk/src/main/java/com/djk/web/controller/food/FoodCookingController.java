package com.djk.web.controller.food;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.common.PageInfoBT;
import com.djk.common.StringUtils;
import com.djk.web.entity.food.FoodCooking;
import com.djk.web.service.food.FoodCookingService;
import com.djk.web.util.ResultMap;

@Controller
@RequestMapping("/food/foodCooking/")
public class FoodCookingController {

	@Autowired
	private FoodCookingService foodSupplierService;

	
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "food/cooking/foodCooking";
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
		FoodCooking foodSupplier=new FoodCooking();
		foodSupplier.setCookingMethod(request.getParameter("searchValue"));
		PageInfoBT<FoodCooking> findPage = foodSupplierService.findPage(foodSupplier);
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
		return "food/cooking/add";
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
		FoodCooking foodSupplier = foodSupplierService.get(id);
		dataMap.put("foodSupplier", foodSupplier);
		return "food/cooking/edit";
	}
	

	
	/**
	 * 删除
	 * @param request
	 * @param foodPhyState
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, FoodCooking foodSupplier) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSupplier.getId()!=null){
			
			result = foodSupplierService.delete(foodSupplier.getId().toString());
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
	public ResultMap save(HttpServletRequest request, FoodCooking foodSupplier) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSupplier.getId()!=null){
			foodSupplier.preUpdate();
			result = foodSupplierService.update(foodSupplier);
		}else{
			foodSupplier.preInsert();
			result = foodSupplierService.insert(foodSupplier);
		}
		if(result>0){
			
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 校验名称
	 * @param foodCooking
	 * @return
	 */
    @RequestMapping(value = "checkNameUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(FoodCooking foodCooking)
    {
        int uniqueFlag = 0;
        if (foodCooking != null)
        {
             Integer id = foodCooking.getId();
             FoodCooking info = foodSupplierService.checkNameUnique(foodCooking.getCookingMethod());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
        return uniqueFlag;
    }
}
