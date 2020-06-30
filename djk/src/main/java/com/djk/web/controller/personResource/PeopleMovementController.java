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
import com.djk.web.entity.personResource.PeopleMovement;
import com.djk.web.service.personResource.IPeopleMovementService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 人需公共资源---运动
 *
 */
@Controller
@RequestMapping(value = "/peopleResouce/peopleMovement")
public class PeopleMovementController {
	
	@Resource
	private IPeopleMovementService peopleMovementService ;
	
	/**
	 * 跳转运动页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/movement/list";
	}
	
	/**
	 * 查询运动列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		PeopleMovement peopleMovement  = new PeopleMovement();
		peopleMovement.setMovementName(request.getParameter("searchValue"));
		PageInfoBT<PeopleMovement> findPage = peopleMovementService.findPage(peopleMovement);
		return findPage;
	}
	
	/**
	 * 添加运动
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/movement/add";
	}
	
	/**
	 * 保存运动
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, PeopleMovement peopleMovement) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleMovement.getId()!=null){
			peopleMovement.preUpdate();
			result = peopleMovementService.update(peopleMovement);
		}else{
			peopleMovement.preInsert();
			result = peopleMovementService.insert(peopleMovement);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑运动
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		PeopleMovement peopleMovement = peopleMovementService.get(id);
		dataMap.put("peopleMovement", peopleMovement);
		return "peopleSource/movement/edit";
	}
	
	/**
	 * 删除运动
	 * @param request
	 * @param peopleMovement
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, PeopleMovement peopleMovement) {
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleMovement.getId()!=null){
			result = peopleMovementService.delete(peopleMovement.getId());
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
    @RequestMapping(value = "checkMovementUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(PeopleMovement peopleMovement)
    {
        int uniqueFlag = 0;
        if (peopleMovement != null)
        {
             Integer id = peopleMovement.getId();
             PeopleMovement info = peopleMovementService.checkMovementUnique(peopleMovement.getMovementName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
