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
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.personResource.PeopleHousehold;
import com.djk.web.entity.personResource.PeopleSleep;
import com.djk.web.service.personResource.IPeopleHouseholdService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 人需公共资源---家居人口
 *
 */
@Controller
@RequestMapping(value = "/peopleResouce/peopleHousehold")
public class PeopleHouseholdController {
	
	@Resource
	private IPeopleHouseholdService peopleHouseholdService;
	
	/**
	 * 跳转家居人口页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/household/list";
	}
	
	/**
	 * 查询家居人口列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		PeopleHousehold peopleHousehold = new PeopleHousehold();
		peopleHousehold.setHousehold(request.getParameter("searchValue"));
		PageInfoBT<PeopleHousehold> findPage = peopleHouseholdService.findPage(peopleHousehold);
		return findPage;
	}
	
	/**
	 * 添加家居人口
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/household/add";
	}
	
	/**
	 * 保存家居人口
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, PeopleHousehold peopleHousehold) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleHousehold.getId()!=null){
			peopleHousehold.preUpdate();
			result = peopleHouseholdService.update(peopleHousehold);
		}else{
			peopleHousehold.preInsert();
			result = peopleHouseholdService.insert(peopleHousehold);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑家居人口
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		PeopleHousehold peopleHousehold = peopleHouseholdService.get(id);
		dataMap.put("peopleHousehold", peopleHousehold);
		return "peopleSource/household/edit";
	}
	
	/**
	 * 删除家居人口
	 * @param request
	 * @param peopleHousehold
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, PeopleHousehold peopleHousehold) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleHousehold.getId()!=null){
			result = peopleHouseholdService.delete(peopleHousehold.getId());
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
    @RequestMapping(value = "checkHouseholdUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(PeopleHousehold peopleHousehold)
    {
        int uniqueFlag = 0;
        if (peopleHousehold != null)
        {
             Integer id = peopleHousehold.getId();
             PeopleHousehold info = peopleHouseholdService.checkNameUnique(peopleHousehold.getHousehold());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
