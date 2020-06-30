package com.djk.web.controller.systemResource;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.common.PageInfoBT;
import com.djk.common.StringUtils;
import com.djk.web.entity.systemResource.SystemEnergy;
import com.djk.web.entity.systemResource.SystemHealth;
import com.djk.web.entity.systemResource.SystemUnit;
import com.djk.web.service.systemResource.ISystemUnitService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 系统公共资源---体积
 *
 */
@Controller
@RequestMapping(value = "/systemResource/systemUnit")
public class SystemUnitController {
	
	@Resource
	private ISystemUnitService systemUnitService  ;
	
	/**
	 * 跳转体积页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/unit/list";
	}
	
	/**
	 * 查询体积列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		SystemUnit systemUnit = new SystemUnit();
		systemUnit.setUnitName(request.getParameter("searchValue"));
		PageInfoBT<SystemUnit> findPage = systemUnitService.findPage(systemUnit);
		return findPage;
	}
	
	/**
	 * 添加体积
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/unit/add";
	}
	
	/**
	 * 保存体积
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, SystemUnit systemUnit) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemUnit.getId()!=null){
			systemUnit.preUpdate();
			result = systemUnitService.update(systemUnit);
		}else{
			systemUnit.preInsert();
			result = systemUnitService.insert(systemUnit);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑体积
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		SystemUnit systemUnit = systemUnitService.get(id);
		dataMap.put("systemUnit", systemUnit);
		return "systemSource/unit/edit";
	}
	
	/**
	 * 删除体积
	 * @param request
	 * @param systemEnergy
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, SystemEnergy systemEnergy) {
		ResultMap r = new ResultMap();
		Integer result =0;
		
		if(systemEnergy.getId()!=null){
			Integer isUsedInFood=systemUnitService.getIsUsedInFood(systemEnergy.getId());
			if(isUsedInFood == 0){
				result = systemUnitService.delete(systemEnergy.getId());
			}else{
				r.setCode("500");
				r.setMsg("该单位被食物使用，不可删除!");
			}
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	/**
	 * 校验名称
	 * @param peopleSleep
	 * @return
	 */
    @RequestMapping(value = "checkUnitUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(SystemUnit systemUnit)
    {
        int uniqueFlag = 0;
        if (systemUnit != null)
        {
             Integer id = systemUnit.getId();
             SystemUnit info = systemUnitService.checkNameUnique(systemUnit.getUnitName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
