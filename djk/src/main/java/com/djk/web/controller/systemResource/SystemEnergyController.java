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
import com.djk.web.service.systemResource.ISystemEnergyService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 系统公共资源---能量
 *
 */
@Controller
@RequestMapping(value = "/systemResource/systemEnergy")
public class SystemEnergyController {
	
	@Resource
	private ISystemEnergyService systemEnergyService  ;
	
	/**
	 * 跳转能量页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/energy/list";
	}
	
	/**
	 * 查询能量列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		SystemEnergy systemEnergy = new SystemEnergy();
		systemEnergy.setEnergyName(request.getParameter("searchValue"));
		PageInfoBT<SystemEnergy> findPage = systemEnergyService.findPage(systemEnergy);
		return findPage;
	}
	
	/**
	 * 添加能量
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "systemSource/energy/add";
	}
	
	/**
	 * 保存能量
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, SystemEnergy systemEnergy) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(systemEnergy.getId()!=null){
			systemEnergy.preUpdate();
			result = systemEnergyService.update(systemEnergy);
		}else{
			systemEnergy.preInsert();
			result = systemEnergyService.insert(systemEnergy);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑能量
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		SystemEnergy systemEnergy = systemEnergyService.get(id);
		dataMap.put("systemEnergy", systemEnergy);
		return "systemSource/energy/edit";
	}
	
	/**
	 * 删除能量
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
			result = systemEnergyService.delete(systemEnergy.getId());
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
    @RequestMapping(value = "checkEnergyUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(SystemEnergy systemEnergy)
    {
        int uniqueFlag = 0;
        if (systemEnergy != null)
        {
             Integer id = systemEnergy.getId();
             SystemEnergy info = systemEnergyService.checkNameUnique(systemEnergy.getEnergyName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
