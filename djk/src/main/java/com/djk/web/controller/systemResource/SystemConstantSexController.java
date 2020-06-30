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
import com.djk.web.entity.systemResource.SystemConstantSex;
import com.djk.web.service.systemResource.ISystemConstantSexService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 系统公共资源---性别常量
 *
 */
@Controller
@RequestMapping(value = "/systemResource/systemConstant")
public class SystemConstantSexController {
	
	@Resource
	private ISystemConstantSexService systemConstantSexService  ;
	
	
	/**
	 * 查询性别常量列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "listSex", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		SystemConstantSex systemConstantSex = new SystemConstantSex();
		systemConstantSex.setSexName(request.getParameter("searchValue"));
		PageInfoBT<SystemConstantSex> findPage = systemConstantSexService.findPage(systemConstantSex);
		return findPage;
	}
	
	/**
	 * 添加性别
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addSex", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/constant/sexConstant/add";
	}
	
	/**
	 * 保存性别
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "saveSex", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, SystemConstantSex systemConstantSex) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemConstantSex.getId()!=null){
			systemConstantSex.preUpdate();
			result = systemConstantSexService.update(systemConstantSex);
		}else{
			systemConstantSex.preInsert();
			result = systemConstantSexService.insert(systemConstantSex);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑性别常量
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "editSex", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		SystemConstantSex systemConstantSex = systemConstantSexService.get(id);
		dataMap.put("systemConstantSex", systemConstantSex);
		return "systemSource/constant/sexConstant/edit";
	}
	
	/**
	 * 删除性别常量
	 * @param request
	 * @param systemConstantSex
	 * @return
	 */
	@RequestMapping(value = "deleteSex", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, SystemConstantSex systemConstantSex) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemConstantSex.getId()!=null){
			result = systemConstantSexService.delete(systemConstantSex.getId());
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
    @RequestMapping(value = "checkSexUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(SystemConstantSex systemConstantSex)
    {
        int uniqueFlag = 0;
        if (systemConstantSex != null)
        {
             Integer id = systemConstantSex.getId();
             SystemConstantSex info = systemConstantSexService.checkNameUnique(systemConstantSex.getSexName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
