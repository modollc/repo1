package com.djk.web.dao.systemResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.systemResource.SystemConstantAge;
import com.djk.web.entity.systemResource.SystemHealth;


@Repository
public interface SystemConstantAgeWriteDao {
 
	SystemConstantAge get(java.lang.Integer id);
	
	Integer insert(SystemConstantAge systemConstantAge);
	
	Integer update(SystemConstantAge systemConstantAge);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(SystemConstantAge entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<SystemConstantAge> findList(Page<SystemConstantAge> page,SystemConstantAge entity);
	
	SystemConstantAge checkNameUnique(String ageName);
}