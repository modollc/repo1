package com.djk.web.service.log;

import java.util.List;
import java.util.Map;

import com.djk.web.entity.log.SysLog;

/**
 * 日志接口
 * @author 李连超
 *
 */
public interface ILogService {

	/**
	 * 保存日志
	 * @param sysRole
	 */
	public void insert(SysLog sysLog);
	
	List<SysLog> page(Map<String, String> queryMap,Integer start,Integer size);
	
	Integer count(Map<String, String> queryMap);
}
