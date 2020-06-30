package com.djk.web.controller.log;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.djk.web.entity.log.SysLog;
import com.djk.web.service.log.ILogService;
import com.djk.web.util.PagerInfo;
import com.djk.web.util.PaginationUtil;
import com.djk.web.util.WebUtil;

@Controller
@RequestMapping("/sys/log/")
public class SysLogController {
	
	@Resource
	private ILogService logService;
	/**
	 * @author zhangzl
	* @Title: chargeCatalogList 
	* @Description: TODO 系统日志列表
	* @param @param dataMap
	* @param @param request
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
    public String chargeCatalogList(Map<String, Object> dataMap,HttpServletRequest request) throws Exception {
		Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
    	List<SysLog> list = logService.page(queryMap,pager.getStart(),pager.getPageSize());
    	pager.setRowsCount(logService.count(queryMap));
    	// 分页对象
		PaginationUtil pm = null;
		pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager.getPageIndex()), pager.getRowsCount(), pager.getUrl(), pager.getQueryString());
		dataMap.put("page", pm);
    	dataMap.put("list", list);
		return "log/loglist";
    }
}
