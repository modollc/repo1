package com.djk.web.dao.personResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.personResource.PeopleAppetite;
import com.djk.web.entity.personResource.PeopleMovement;


@Repository
public interface PeopleAppetiteWriteDao {
 
	PeopleAppetite get(java.lang.Integer id);
	
	Integer insert(PeopleAppetite peopleAppetite);
	
	Integer update(PeopleAppetite peopleAppetite);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(PeopleAppetite entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<PeopleAppetite> findList(Page<PeopleAppetite> page,PeopleAppetite entity);
	
	PeopleAppetite checkNameUnique(String appetiteName);
}