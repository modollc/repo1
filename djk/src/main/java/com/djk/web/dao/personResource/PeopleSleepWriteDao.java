package com.djk.web.dao.personResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.dige.DigeDining;
import com.djk.web.entity.dige.DigeNutrition;
import com.djk.web.entity.personResource.PeopleSleep;


@Repository
public interface PeopleSleepWriteDao {
 
	PeopleSleep get(java.lang.Integer id);
	
	Integer insert(PeopleSleep peopleSleep);
	
	Integer update(PeopleSleep peopleSleep);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(PeopleSleep entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<PeopleSleep> findList(Page<PeopleSleep> page,PeopleSleep entity);
	
	PeopleSleep checkNameUnique(String sleepQuantity);
}