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
import com.djk.web.entity.personResource.PeopleArea;
import com.djk.web.entity.personResource.PeopleHousehold;
import com.djk.web.entity.personResource.PeopleSleep;
import com.djk.web.service.personResource.IPeopleAreaService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 人需公共资源---人均面积
 *
 */
@Controller
@RequestMapping(value = "/peopleResouce/peopleArea")
public class PeopleAreaController {
	
	@Resource
	private IPeopleAreaService peopleAreaService;
	
	/**
	 * 跳转人均面积页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/area/list";
	}
	
	/**
	 * 查询人均面积列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		PeopleArea peopleArea = new PeopleArea();
		peopleArea.setArea(request.getParameter("searchValue"));
		PageInfoBT<PeopleArea> findPage = peopleAreaService.findPage(peopleArea);
		return findPage;
	}
	
	/**
	 * 添加人均面积
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/area/add";
	}
	
	/**
	 * 保存人均面积
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, PeopleArea peopleArea) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleArea.getId()!=null){
			peopleArea.preUpdate();
			result = peopleAreaService.update(peopleArea);
		}else{
			peopleArea.preInsert();
			result = peopleAreaService.insert(peopleArea);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	/**
	 * 编辑人均面积
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		PeopleArea peopleArea = peopleAreaService.get(id);
		dataMap.put("peopleArea", peopleArea);
		return "peopleSource/area/edit";
	}
	
	/**
	 * 删除人均面积
	 * @param request
	 * @param peopleSleep
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, PeopleSleep peopleSleep) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleSleep.getId()!=null){
			result = peopleAreaService.delete(peopleSleep.getId());
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
    @RequestMapping(value = "checkAreaUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(PeopleArea peopleArea)
    {
        int uniqueFlag = 0;
        if (peopleArea != null)
        {
             Integer id = peopleArea.getId();
             PeopleArea info = peopleAreaService.checkNameUnique(peopleArea.getArea());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
