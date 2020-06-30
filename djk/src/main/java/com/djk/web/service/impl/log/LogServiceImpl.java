package com.djk.web.service.impl.log;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.djk.web.dao.log.LogDaoWrite;
import com.djk.web.entity.log.SysLog;
import com.djk.web.service.log.ILogService;

/**
 * 日志service
 * @author 李连超
 *
 */
@Service(value = "logService")
public class LogServiceImpl implements ILogService {

	@Resource
	private LogDaoWrite logDaoWrite;
	/**
	 * 保存日志
	 * @param sysRole
	 */
	public void insert(SysLog sysLog) {
		logDaoWrite.insert(sysLog);
	}
	@Override
	public List<SysLog> page(Map<String, String> queryMap, Integer start,
			Integer size) {
		return logDaoWrite.page(queryMap,start,size);
	}
	@Override
	public Integer count(Map<String, String> queryMap) {
		return logDaoWrite.count(queryMap);
	}
}
