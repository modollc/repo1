package com.djk.web.dao.dige;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.dige.DigeEatfast;



@Repository
public interface DigeEatfastWriteDao {
 
	DigeEatfast get(java.lang.Integer id);
	
	Integer insert(DigeEatfast digeEatfast);
	
	Integer update(DigeEatfast digeEatfast);
	
	DigeEatfast checkNameUnique(String eatFast);
	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(DigeEatfast entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<DigeEatfast> findList(Page<DigeEatfast> page,DigeEatfast entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<DigeEatfast> findList(DigeEatfast entity);
}