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
import com.djk.web.entity.food.FoodSeasonal;
import com.djk.web.entity.food.FoodSupplier;
import com.djk.web.service.food.FoodSupplierService;
import com.djk.web.util.ResultMap;
/**
 * 供应商
 * @author 邢广军
 *
 */
@Controller
@RequestMapping("/food/foodSupplier/")
public class FoodSupplierController {

	@Autowired
	private FoodSupplierService foodSupplierService;

	
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "food/supplier/foodSupplier";
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
		FoodSupplier foodSupplier=new FoodSupplier();
		foodSupplier.setSupplierName(request.getParameter("searchValue"));
		PageInfoBT<FoodSupplier> findPage = foodSupplierService.findPage(foodSupplier);
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
		return "food/supplier/add";
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
		FoodSupplier foodSupplier = foodSupplierService.get(id);
		dataMap.put("foodSupplier", foodSupplier);
		return "food/supplier/edit";
	}
	

	
	/**
	 * 删除
	 * @param request
	 * @param foodPhyState
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, FoodSupplier foodSupplier) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSupplier.getId()!=null){
			
			result = foodSupplierService.delete(foodSupplier.getId().toString());
			
			if(result.equals(3)){
				r.setCode("1");
				r.setMsg("供应商己经被食物应用不能删除!");
				return r;
			}
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
	public ResultMap save(HttpServletRequest request, FoodSupplier foodSupplier) {
		
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
	 * @param foodSupplier
	 * @return
	 */
    @RequestMapping(value = "checkNameUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(FoodSupplier foodSupplier)
    {
        int uniqueFlag = 0;
        if (foodSupplier != null)
        {
             Integer id = foodSupplier.getId();
             FoodSupplier info = foodSupplierService.checkNameUnique(foodSupplier.getSupplierName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
        return uniqueFlag;
    }
	
}
