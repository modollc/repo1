package com.djk.web.dao.personResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.personResource.PeopleSleep;


@Repository
public interface PeopleActiveWriteDao {
 
	PeopleActive get(java.lang.Integer id);
	
	Integer insert(PeopleActive peopleActive);
	
	Integer update(PeopleActive peopleActive);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(PeopleActive entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<PeopleActive> findList(Page<PeopleActive> page,PeopleActive entity);
	
	PeopleActive checkNameUnique(String activeName);
}