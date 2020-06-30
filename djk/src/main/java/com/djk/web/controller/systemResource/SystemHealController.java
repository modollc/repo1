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
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.systemResource.SystemHealth;
import com.djk.web.service.systemResource.ISystemHealService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 系统公共资源---健康状况
 *
 */
@Controller
@RequestMapping(value = "/systemResource/systemHealth")
public class SystemHealController {
	
	@Resource
	private ISystemHealService systemHealService  ;
	
	/**
	 * 跳转健康状况页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/healStatus/list";
	}
	
	/**
	 * 查询健康状况列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		SystemHealth systemHealth = new SystemHealth();
		systemHealth.setHealthName(request.getParameter("searchValue"));
		PageInfoBT<SystemHealth> findPage = systemHealService.findPage(systemHealth);
		return findPage;
	}
	
	/**
	 * 添加健康状况
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/healStatus/add";
	}
	
	/**
	 * 保存健康状况
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, SystemHealth systemHealth) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemHealth.getId()!=null){
			systemHealth.preUpdate();
			result = systemHealService.update(systemHealth);
		}else{
			systemHealth.preInsert();
			result = systemHealService.insert(systemHealth);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑健康状况
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		SystemHealth systemHealth = systemHealService.get(id);
		dataMap.put("systemHealth", systemHealth);
		return "systemSource/healStatus/edit";
	}
	
	/**
	 * 删除健康状况
	 * @param request
	 * @param systemHealth
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, SystemHealth systemHealth) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemHealth.getId()!=null){
			result = systemHealService.delete(systemHealth.getId());
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
    @RequestMapping(value = "checkHealthUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(SystemHealth systemHealth)
    {
        int uniqueFlag = 0;
        if (systemHealth != null)
        {
             Integer id = systemHealth.getId();
             SystemHealth info = systemHealService.checkNameUnique(systemHealth.getHealthName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
