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
import com.djk.web.entity.personResource.PeopleSleep;
import com.djk.web.service.personResource.IPeopleActiveService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 人需公共资源---活跃度
 *
 */
@Controller
@RequestMapping(value = "/peopleResouce/peopleActive")
public class PeopleActiveController {
	
	@Resource
	private IPeopleActiveService peopleActiveService ;
	
	/**
	 * 跳转活跃度页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/active/list";
	}
	
	/**
	 * 查询活跃度列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		PeopleActive peopleActive  = new PeopleActive();
		peopleActive.setActiveName(request.getParameter("searchValue"));
		PageInfoBT<PeopleActive> findPage = peopleActiveService.findPage(peopleActive);
		return findPage;
	}
	
	/**
	 * 添加活跃度
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/active/add";
	}
	
	/**
	 * 保存活跃度
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, PeopleActive peopleActive) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleActive.getId()!=null){
			peopleActive.preUpdate();
			result = peopleActiveService.update(peopleActive);
		}else{
			peopleActive.preInsert();
			result = peopleActiveService.insert(peopleActive);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	/**
	 * 编辑活跃度
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		PeopleActive peopleActive = peopleActiveService.get(id);
		dataMap.put("peopleActive", peopleActive);
		return "peopleSource/active/edit";
	}
	
	/**
	 * 删除活跃度
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
			result = peopleActiveService.delete(peopleSleep.getId());
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	/**
	 * 添加修改活跃度---校验名称
	 * @param peopleSleep
	 * @return
	 */
    @RequestMapping(value = "checkActiveUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(PeopleActive peopleActive)
    {
        int uniqueFlag = 0;
        if (peopleActive != null)
        {
             Integer id = peopleActive.getId();
             PeopleActive info = peopleActiveService.checkNameUnique(peopleActive.getActiveName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
