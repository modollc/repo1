package com.djk.web.dao.personResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.personResource.PeopleAppetite;
import com.djk.web.entity.personResource.PeopleSleep;
import com.djk.web.entity.personResource.PeopleTemperature;


@Repository
public interface PeopleTemperatureWriteDao {
 
	PeopleTemperature get(java.lang.Integer id);
	
	Integer insert(PeopleTemperature peopleTemperature);
	
	Integer update(PeopleTemperature peopleTemperature);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(PeopleTemperature entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<PeopleTemperature> findList(Page<PeopleTemperature> page,PeopleTemperature entity);
	
	PeopleTemperature checkNameUnique(String temperature);
}