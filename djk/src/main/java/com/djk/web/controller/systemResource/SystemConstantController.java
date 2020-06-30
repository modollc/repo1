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
import com.djk.web.entity.systemResource.SystemConstant;
import com.djk.web.entity.systemResource.SystemConstantAge;
import com.djk.web.service.systemResource.ISystemConstantService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 系统公共资源---系统常量
 *
 */
@Controller
@RequestMapping(value = "/systemResource/systemConstant")
public class SystemConstantController {
	
	@Resource
	private ISystemConstantService systemConstantService  ;
	
	/**
	 * 跳转基本常量页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/constant/baseConstant/list";
	}
	
	/**
	 * 查询基本常量列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listBase", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		SystemConstant systemConstant = new SystemConstant();
		systemConstant.setConstantName(request.getParameter("searchValue"));
		PageInfoBT<SystemConstant> findPage = systemConstantService.findPage(systemConstant);
		return findPage;
	}
	
	/**
	 * 添加基本常量
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addBase", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/constant/baseConstant/add";
	}
	
	/**
	 * 保存常量
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "saveBase", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, SystemConstant systemConstant) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemConstant.getId()!=null){
			systemConstant.preUpdate();
			result = systemConstantService.update(systemConstant);
		}else{
			systemConstant.preInsert();
			result = systemConstantService.insert(systemConstant);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑基本常量
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "editBase", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		SystemConstant systemConstant = systemConstantService.get(id);
		dataMap.put("systemConstant", systemConstant);
		return "systemSource/constant/baseConstant/edit";
	}
	
	/**
	 * 删除基本常量
	 * @param request
	 * @param systemConstant
	 * @return
	 */
	@RequestMapping(value = "deleteBase", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, SystemConstant systemConstant) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemConstant.getId()!=null){
			result = systemConstantService.delete(systemConstant.getId());
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
    @RequestMapping(value = "checkConstantUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(SystemConstant systemConstant)
    {
        int uniqueFlag = 0;
        if (systemConstant != null)
        {
             Integer id = systemConstant.getId();
             SystemConstant info = systemConstantService.checkNameUnique(systemConstant.getConstantName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
