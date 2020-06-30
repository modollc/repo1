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

import com.djk.web.entity.food.FoodPhyState;
import com.djk.web.entity.food.FoodSeasonal;

import com.djk.web.service.food.FoodSeasonalService;
import com.djk.web.util.ResultMap;

/**
 * 时令
 * @author 邢广军
 *
 */
@Controller
@RequestMapping("/food/foodSeasonal/")
public class FoodSeasonalController {

	
	@Autowired
	private FoodSeasonalService foodSeasonalService;

	
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "food/sl/foodSeasonal";
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
		FoodSeasonal foodSeasonal=new FoodSeasonal();
		foodSeasonal.setSeasonal(request.getParameter("searchValue"));
		PageInfoBT<FoodSeasonal> findPage = foodSeasonalService.findPage(foodSeasonal);
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
		return "food/sl/add";
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
		FoodSeasonal foodSeasonal = foodSeasonalService.get(id);
		dataMap.put("foodSeasonal", foodSeasonal);
		return "food/sl/edit";
	}
	

	
	/**
	 * 删除
	 * @param request
	 * @param foodPhyState
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, FoodSeasonal foodSeasonal) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSeasonal.getId()!=null){
			
			result = foodSeasonalService.delete(foodSeasonal.getId().toString());
			if(result.equals(3)){
				r.setCode("1");
				r.setMsg("时令被食物应用不能删除!");
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
	public ResultMap save(HttpServletRequest request, FoodSeasonal foodSeasonal) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSeasonal.getId()!=null){
			foodSeasonal.preUpdate();
			result = foodSeasonalService.update(foodSeasonal);
		}else{
			foodSeasonal.preInsert();
			result = foodSeasonalService.insert(foodSeasonal);
		}
		if(result>0){
			
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	
	/**
	 * 校验名称
	 * @param foodSeasonal
	 * @return
	 */
    @RequestMapping(value = "checkNameUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(FoodSeasonal foodSeasonal)
    {
        int uniqueFlag = 0;
        if (foodSeasonal != null)
        {
             Integer id = foodSeasonal.getId();
             FoodSeasonal info = foodSeasonalService.checkNameUnique(foodSeasonal.getSeasonal());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
        return uniqueFlag;
    }
}
