package com.djk.web.dao.resource;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.djk.web.entity.resource.SystemResources;

@Repository
public interface SystemResourcesWriteDao {

	SystemResources get(java.lang.Integer id);

	Integer save(SystemResources systemResources);

	Integer update(SystemResources systemResources);

	Integer getCount(Map<String, String> queryMap);

	List<SystemResources> page(Map<String, String> queryMap);

	List<SystemResources> list(Map<String, Object> queryMap);

	Integer del(Integer id);

	/**
	 * 查询ztree的数据
	 * 
	 * @param queryMap
	 * @return
	 */
	public List<Map<String, Object>> getZtreeList(Map<String, Object> queryMap);

}
