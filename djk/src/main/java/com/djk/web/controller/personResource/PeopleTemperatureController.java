package com.djk.web.controller.personResource;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.common.PageInfoBT;
import com.djk.common.StringUtils;
import com.djk.web.entity.dige.DigeDining;
import com.djk.web.entity.personResource.PeopleAppetite;
import com.djk.web.entity.personResource.PeopleTemperature;
import com.djk.web.service.personResource.IPeopleTemperatureService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 人需公共资源---环境温度
 *
 */
@Controller
@RequestMapping(value = "/peopleResouce/peopleTemperature")
public class PeopleTemperatureController {
	
	@Resource
	private IPeopleTemperatureService peopleTemperatureService;
	
	/**
	 * 跳转环境温度页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/temperature/list";
	}
	
	/**
	 * 查询环境温度列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		PeopleTemperature peopleTemperature = new PeopleTemperature();
		peopleTemperature.setTemperature(request.getParameter("searchValue"));
		PageInfoBT<PeopleTemperature> findPage = peopleTemperatureService.findPage(peopleTemperature);
		return findPage;
	}
	
	/**
	 * 添加环境温度
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/temperature/add";
	}
	
	/**
	 * 保存环境温度
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, PeopleTemperature peopleTemperature) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleTemperature.getId()!=null){
			peopleTemperature.preUpdate();
			result = peopleTemperatureService.update(peopleTemperature);
		}else{
			peopleTemperature.preInsert();
			result = peopleTemperatureService.insert(peopleTemperature);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑环境温度
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		PeopleTemperature peopleTemperature = peopleTemperatureService.get(id);
		dataMap.put("peopleTemperature", peopleTemperature);
		return "peopleSource/temperature/edit";
	}
	
	/**
	 * 删除环境温度
	 * @param request
	 * @param peopleTemperature
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, PeopleTemperature peopleTemperature) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleTemperature.getId()!=null){
			result = peopleTemperatureService.delete(peopleTemperature.getId());
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
    @RequestMapping(value = "checkTemperatureUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(PeopleTemperature peopleTemperature)
    {
        int uniqueFlag = 0;
        if (peopleTemperature != null)
        {
             Integer id = peopleTemperature.getId();
             PeopleTemperature info = peopleTemperatureService.checkNameUnique(peopleTemperature.getTemperature());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
