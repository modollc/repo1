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
import com.djk.web.entity.dige.DigeNutrition;
import com.djk.web.entity.personResource.PeopleSleep;
import com.djk.web.service.personResource.IPeopleSleepService;
import com.djk.web.util.ResultMap;
/**
 * 
 * @author zhangzl
 * 人需公共资源---睡眠
 *
 */
@Controller
@RequestMapping(value = "/peopleResouce/peopleSleep")
public class PeopleSleepController {
	
	@Resource
	private IPeopleSleepService peopleSleepService;
	
	/**
	 * 跳转睡眠页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/sleep/list";
	}
	
	/**
	 * 查询睡眠列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public Object list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		PeopleSleep peopleSleep = new PeopleSleep();
		peopleSleep.setSleepQuality(request.getParameter("searchValue"));
		PageInfoBT<PeopleSleep> findPage = peopleSleepService.findPage(peopleSleep);
		return findPage;
	}
	
	/**
	 * 添加睡眠
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "add", method = { RequestMethod.GET })
	public String add(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		return "peopleSource/sleep/add";
	}
	
	/**
	 * 保存睡眠
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, PeopleSleep peopleSleep) {
		
		ResultMap r = new ResultMap();
		Integer result =0;
		if(peopleSleep.getId()!=null){
			peopleSleep.preUpdate();
			result = peopleSleepService.update(peopleSleep);
		}else{
			peopleSleep.preInsert();
			result = peopleSleepService.insert(peopleSleep);
		}
		if(result>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
	
	/**
	 * 编辑睡眠
	 * @param dataMap
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "edit", method = { RequestMethod.GET })
	public String edit(Map<String, Object> dataMap, HttpServletRequest request,Integer id) throws Exception {
		PeopleSleep peopleSleep = peopleSleepService.get(id);
		dataMap.put("peopleSleep", peopleSleep);
		return "peopleSource/sleep/edit";
	}
	
	/**
	 * 删除睡眠
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
			result = peopleSleepService.delete(peopleSleep.getId());
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
    @RequestMapping(value = "checkSleepUnique", method = RequestMethod.POST)
    @ResponseBody
    public int checkNameUnique(PeopleSleep peopleSleep)
    {
        int uniqueFlag = 0;
        if (peopleSleep != null)
        {
             Integer id = peopleSleep.getId();
             PeopleSleep info = peopleSleepService.checkNameUnique(peopleSleep.getSleepQuality());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
       return uniqueFlag;
    }
}
