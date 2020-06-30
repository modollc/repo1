package com.djk.web.dao.systemResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.systemResource.SystemEnergy;
import com.djk.web.entity.systemResource.SystemHealth;
import com.djk.web.entity.systemResource.SystemUnit;
import com.djk.web.entity.systemResource.SystemWeight;


@Repository
public interface SystemUnitWriteDao {
 
	SystemUnit get(java.lang.Integer id);
	
	Integer insert(SystemUnit systemUnit);
	
	Integer update(SystemUnit systemUnit);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(SystemUnit entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<SystemUnit> findList(Page<SystemUnit> page,SystemUnit entity);
	
	SystemUnit checkNameUnique(String unitName);

	public int isUsedInFood(java.lang.Integer id);
}