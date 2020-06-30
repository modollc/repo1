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
import com.djk.web.entity.personResource.PeopleAppetite;
import com.djk.web.entity.personResource.PeopleMovement;
import com.djk.web.service.personResource.IPeopleAppetiteService;
import com.djk.web.service.personResource.IPeopleMovementService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 人需公共资源---食欲
 *
 */
@Controller
@RequestMapping(value = "/peopleResouce/peopleAppetite")
public class PeopleAppetiteController {
	
	@Resource
	private IPeopleAppetiteService peopleAppetiteService ;
	
	/**
	 * 跳转食欲页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/appetite/list";
	}
	
	/**
	 * 查询食欲列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		PeopleAppetite peopleAppetite  = new PeopleAppetite();
		peopleAppetite.setAppetiteName(request.getParameter("searchValue"));
		PageInfoBT<PeopleAppetite> findPage = peopleAppetiteService.findPage(peopleAppetite);
		return findPage;
	}
	
	/**
	 * 添加食欲
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/appetite/add";
	}
	
	/**
	 * 保存食欲
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, PeopleAppetite peopleAppetite) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleAppetite.getId()!=null){
			peopleAppetite.preUpdate();
			result = peopleAppetiteService.update(peopleAppetite);
		}else{
			peopleAppetite.preInsert();
			result = peopleAppetiteService.insert(peopleAppetite);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 食欲编辑
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		PeopleAppetite peopleAppetite = peopleAppetiteService.get(id);
		dataMap.put("peopleAppetite", peopleAppetite);
		return "peopleSource/appetite/edit";
	}
	
	/**
	 * 食欲删除
	 * @param request
	 * @param peopleAppetite
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, PeopleAppetite peopleAppetite) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleAppetite.getId()!=null){
			result = peopleAppetiteService.delete(peopleAppetite.getId());
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
    @RequestMapping(value = "checkAppetiteUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(PeopleAppetite peopleAppetite)
    {
        int uniqueFlag = 0;
        if (peopleAppetite != null)
        {
             Integer id = peopleAppetite.getId();
             PeopleAppetite info = peopleAppetiteService.checkNameUnique(peopleAppetite.getAppetiteName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
