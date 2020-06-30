package com.djk.web.dao.systemResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.systemResource.SystemHealth;


@Repository
public interface SystemHealthWriteDao {
 
	SystemHealth get(java.lang.Integer id);
	
	Integer insert(SystemHealth systemHealth);
	
	Integer update(SystemHealth systemHealth);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(SystemHealth entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<SystemHealth> findList(Page<SystemHealth> page,SystemHealth entity);
	
	SystemHealth checkNameUnique(String healthName);
}