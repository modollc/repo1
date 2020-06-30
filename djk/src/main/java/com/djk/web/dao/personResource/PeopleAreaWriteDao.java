package com.djk.web.dao.personResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.personResource.PeopleArea;


@Repository
public interface PeopleAreaWriteDao {
 
	PeopleArea get(java.lang.Integer id);
	
	Integer insert(PeopleArea peopleArea);
	
	Integer update(PeopleArea peopleArea);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(PeopleArea entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<PeopleArea> findList(Page<PeopleArea> page,PeopleArea entity);
	
	PeopleArea checkNameUnique(String area);
}