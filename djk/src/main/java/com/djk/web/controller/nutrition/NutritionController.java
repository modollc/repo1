package com.djk.web.controller.nutrition;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.common.PageInfoBT;
import com.djk.common.StringUtils;
import com.djk.web.entity.nutrition.NutritionCategory;
import com.djk.web.entity.nutrition.NutritionComponent;
import com.djk.web.entity.user.SysUser;
import com.djk.web.service.nutrition.NutritionCategoryService;
import com.djk.web.service.nutrition.NutritionComponentService;
import com.djk.web.util.ResultMap;

/**
 * 营养维护
 * @author 邢广军
 *
 */
@Controller
@RequestMapping("/nutrition/nutritionList/")
public class NutritionController {

	@Autowired
	private NutritionComponentService nutritionComponentService;

	@Autowired
	private NutritionCategoryService nutritionCategoryService;


	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "nutrition/nutrition";
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
		String fid = request.getParameter("cateId");
		NutritionComponent nutritionComponent=new NutritionComponent();
		nutritionComponent.setName(request.getParameter("searchValue"));
		if(!"0".equals(fid)){
			nutritionComponent.setNutritionCategoryId(Integer.parseInt(fid));
		}
		
		PageInfoBT<NutritionComponent> findPage = nutritionComponentService.findPage(nutritionComponent);
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
		String fId = request.getParameter("fId");
		String fName = request.getParameter("fName");
		NutritionCategory nutritionCategory = nutritionCategoryService.get(Integer.parseInt(fId));
		dataMap.put("fId", fId);
		dataMap.put("fName", fName);
		dataMap.put("pids", nutritionCategory.getPids());
		return "nutrition/add";
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
		NutritionComponent foodSupplier = nutritionComponentService.get(id);
		dataMap.put("foodSupplier", foodSupplier);
		return "nutrition/edit";
	}

	
	
	/**
	 * 复制营养
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "copy", method = { RequestMethod.POST })
	@ResponseBody
	public ResultMap copy(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(id!=null){

			result = nutritionComponentService.copy(id);
		}
		if(result>0){

			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
		
	}


	/**
	 * 删除
	 * @param request
	 * @param foodPhyState
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, NutritionComponent foodSupplier) {

		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSupplier.getId()!=null){

			result = nutritionComponentService.delete(foodSupplier.getId().toString());
			if(result.equals(3)){
				
				r.setCode("1");
				r.setMsg("营养名称己被食物引用不能删除!");
				
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
	public ResultMap save(HttpServletRequest request, NutritionComponent foodSupplier) {

		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSupplier.getId()!=null){
			foodSupplier.preUpdate();
			result = nutritionComponentService.update(foodSupplier);
		}else{
			NutritionCategory nutritionCategory = nutritionCategoryService.get(1);
			//NutritionCategory nutritionCategory = nutritionCategoryService.get(foodSupplier.getNutritionCategoryId());
			foodSupplier.setNutritionCategoryCode(nutritionCategory.getCode());
			foodSupplier.preInsert();
			result = nutritionComponentService.insert(foodSupplier);
		}
		if(result>0){

			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	
	
	
	
	////////////////////////////////////分类/////////////////////////////////////////////////
	/**
	 * 
	 * @Title: 分类树
	 * @Description: TODO 默认列表
	 * @param @param dataMap
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "tree", method = { RequestMethod.POST })
	@ResponseBody
	public List<Map<String, Object>> tree(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {

		List<Map<String, Object>> list = nutritionCategoryService.getCategoryListByPId(id);
		return list;
	}
	
	/**
	 * 添加分类
	 * @param pId 分类父id
	 * @param name 分类名称
	 * @return
	 */
	@RequestMapping(value = "tree/add", method = { RequestMethod.POST })
	public @ResponseBody NutritionCategory addFoodCategory(HttpServletRequest request, Integer pid, String name) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return nutritionCategoryService.addCategory(pid, name, user.getId());
	}
	
	
	/**
	 * 修改分类
	 * @param id 食物分类id
	 * @param name 食物分类名称
	 * @return
	 */
	@RequestMapping(value = "tree/edit", method = { RequestMethod.POST })
	public @ResponseBody NutritionCategory updateFoodCategory(HttpServletRequest request, Integer id, String name) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return nutritionCategoryService.updateCategory(id, name, user.getId());
	}
	
	
	
	/**
	 * 删除分类
	 * @param id 分类id
	 * @return
	 */
	@RequestMapping(value = "tree/delete", method = { RequestMethod.POST })
	public @ResponseBody Boolean deleteFoodCategory(HttpServletRequest request, Integer id) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		return  nutritionCategoryService.deleteCategory(id, user.getId());
		
	}
	
	
	/**
	 * 验证分类下是否有子分类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "tree/validateChild", method = { RequestMethod.POST })
	public @ResponseBody Boolean validateChild(Integer id) {
		return nutritionCategoryService.validateChild(id);
	}
	
	
	
	
	/**
	 * 复制分类
	 * @param id 分类id
	 * @return
	 */
	@RequestMapping(value = "tree/copy", method = { RequestMethod.POST })
	public @ResponseBody ResultMap copyFoodCategory(HttpServletRequest request, Integer id) {
		SysUser user = (SysUser) request.getSession().getAttribute("user"); 
		ResultMap r = new ResultMap();
		Integer result =nutritionCategoryService.copyCategory(id, user.getId());
		
		if(result>0){

			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
		
	}
	
	
	
	/**
	 * 校验名称
	 * @param nutritionComponent
	 * @return
	 */
    @RequestMapping(value = "checkNameUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(NutritionComponent nutritionComponent)
    {
        int uniqueFlag = 0;
        if (nutritionComponent != null)
        {
             Integer id = nutritionComponent.getId();
             NutritionComponent info = nutritionComponentService.checkNameUnique(nutritionComponent.getName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
        return uniqueFlag;
    }
	

}
