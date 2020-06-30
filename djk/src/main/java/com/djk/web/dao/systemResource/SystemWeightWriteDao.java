package com.djk.web.dao.systemResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.systemResource.SystemHealth;
import com.djk.web.entity.systemResource.SystemWeight;


@Repository
public interface SystemWeightWriteDao {
 
	SystemWeight get(java.lang.Integer id);
	
	Integer insert(SystemWeight systemWeight);
	
	Integer update(SystemWeight systemWeight);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(SystemWeight entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<SystemWeight> findList(Page<SystemWeight> page,SystemWeight entity);
	
	SystemWeight checkNameUnique(String weightName);
}