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
import com.djk.web.entity.systemResource.SystemWeight;
import com.djk.web.service.systemResource.ISystemWeightService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 系统公共资源---重量
 *
 */
@Controller
@RequestMapping(value = "/systemResource/systemWeight")
public class SystemWeightController {
	
	@Resource
	private ISystemWeightService systemWeightService  ;
	
	/**
	 * 跳转重量页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/weight/list";
	}
	
	/**
	 * 查询重量列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		SystemWeight systemWeight = new SystemWeight();
		systemWeight.setWeightName(request.getParameter("searchValue"));
		PageInfoBT<SystemWeight> findPage = systemWeightService.findPage(systemWeight);
		return findPage;
	}
	
	/**
	 * 添加重量
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/weight/add";
	}
	
	/**
	 * 保存重量
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, SystemWeight systemWeight) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemWeight.getId()!=null){
			systemWeight.preUpdate();
			result = systemWeightService.update(systemWeight);
		}else{
			systemWeight.preInsert();
			result = systemWeightService.insert(systemWeight);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑重量
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		SystemWeight systemWeight = systemWeightService.get(id);
		dataMap.put("systemWeight", systemWeight);
		return "systemSource/weight/edit";
	}
	
	/**
	 * 删除重量
	 * @param request
	 * @param systemWeight
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, SystemWeight systemWeight) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemWeight.getId()!=null){
			result = systemWeightService.delete(systemWeight.getId());
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	/**
	 * 校验名称
	 * @param systemWeight
	 * @return
	 */
    @RequestMapping(value = "checkWeightUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(SystemWeight systemWeight)
    {
        int uniqueFlag = 0;
        if (systemWeight != null)
        {
             Integer id = systemWeight.getId();
             SystemWeight info = systemWeightService.checkNameUnique(systemWeight.getWeightName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
