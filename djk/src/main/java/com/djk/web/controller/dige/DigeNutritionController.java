package com.djk.web.controller.dige;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.common.PageInfoBT;
import com.djk.common.StringUtils;
import com.djk.web.entity.dige.DigeNutrition;
import com.djk.web.service.dige.DigeNutritionService;
import com.djk.web.util.ResultMap;

@Controller
@RequestMapping("/dige/digeNutrition/")
public class DigeNutritionController {

	@Autowired
	private DigeNutritionService digeSceneService;

	
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "dige/nutrition/nutrition";
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
		DigeNutrition foodSupplier=new DigeNutrition();
		foodSupplier.setNutritionQuantity(request.getParameter("searchValue"));
		PageInfoBT<DigeNutrition> findPage = digeSceneService.findPage(foodSupplier);
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
		return "dige/nutrition/add";
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
		DigeNutrition foodSupplier = digeSceneService.get(id);
		dataMap.put("foodSupplier", foodSupplier);
		return "dige/nutrition/edit";
	}
	

	
	/**
	 * 删除
	 * @param request
	 * @param foodPhyState
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, DigeNutrition foodSupplier) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSupplier.getId()!=null){
			
			result = digeSceneService.delete(foodSupplier.getId().toString());
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
	public ResultMap save(HttpServletRequest request, DigeNutrition foodSupplier) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSupplier.getId()!=null){
			foodSupplier.preUpdate();
			result = digeSceneService.update(foodSupplier);
		}else{
			foodSupplier.preInsert();
			result = digeSceneService.insert(foodSupplier);
		}
		if(result>0){
			
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	/**
	 * 校验名称
	 * @param digeNutrition
	 * @return
	 */
    @RequestMapping(value = "checkNameUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(DigeNutrition digeNutrition)
    {
        int uniqueFlag = 0;
        if (digeNutrition != null)
        {
             Integer id = digeNutrition.getId();
             DigeNutrition info = digeSceneService.checkNameUnique(digeNutrition.getNutritionQuantity());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
