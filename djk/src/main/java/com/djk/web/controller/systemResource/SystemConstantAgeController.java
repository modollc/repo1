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
import com.djk.web.entity.systemResource.SystemConstantAge;
import com.djk.web.entity.systemResource.SystemEnergy;
import com.djk.web.entity.systemResource.SystemHealth;
import com.djk.web.service.systemResource.ISystemConstantAgeService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 系统公共资源---常量
 *
 */
@Controller
@RequestMapping(value = "/systemResource/systemConstant")
public class SystemConstantAgeController {
	
	@Resource
	private ISystemConstantAgeService systemConstantAgeService  ;
	
	
	/**
	 * 查询年龄基础常量列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listAge", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		SystemConstantAge systemConstantAge = new SystemConstantAge();
		systemConstantAge.setAgeName(request.getParameter("searchValue"));
		PageInfoBT<SystemConstantAge> findPage = systemConstantAgeService.findPage(systemConstantAge);
		return findPage;
	}
	
	/**
	 * 添加常量
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addAge", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/constant/ageConstant/add";
	}
	
	/**
	 * 保存常量
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "saveAge", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, SystemConstantAge systemConstantAge) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemConstantAge.getId()!=null){
			systemConstantAge.preUpdate();
			result = systemConstantAgeService.update(systemConstantAge);
		}else{
			systemConstantAge.preInsert();
			result = systemConstantAgeService.insert(systemConstantAge);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑年龄常量
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "editAge", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		SystemConstantAge systemConstantAge = systemConstantAgeService.get(id);
		dataMap.put("systemConstantAge", systemConstantAge);
		return "systemSource/constant/ageConstant/edit";
	}
	
	/**
	 * 删除年龄常量
	 * @param request
	 * @param systemConstantAge
	 * @return
	 */
	@RequestMapping(value = "deleteAge", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, SystemConstantAge systemConstantAge) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemConstantAge.getId()!=null){
			result = systemConstantAgeService.delete(systemConstantAge.getId());
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
    @RequestMapping(value = "checkAgeUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(SystemConstantAge systemConstantAge)
    {
        int uniqueFlag = 0;
        if (systemConstantAge != null)
        {
             Integer id = systemConstantAge.getId();
             SystemConstantAge info = systemConstantAgeService.checkNameUnique(systemConstantAge.getAgeName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
