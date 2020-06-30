package com.djk.web.dao.log;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.djk.web.entity.log.SysLog;
/**
 * 保存日志dao
 * @author 李连超
 *
 */
@Repository
public interface LogDaoWrite {

	/**
	 * 保存日志
	 * @param sysRole
	 */
	public void insert(SysLog sysLog);
	
	List<SysLog> page(@Param("queryMap") Map<String, String> queryMap,
			@Param("start") Integer start, @Param("size") Integer size);
	
	Integer count(@Param("queryMap") Map<String, String> queryMap);
}
