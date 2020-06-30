package com.djk.web.dao.dige;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.dige.DigeScene;



@Repository
public interface DigeSceneWriteDao {
 
	DigeScene get(java.lang.Integer id);
	
	DigeScene checkNameUnique(String sceneName);
	
	Integer insert(DigeScene digeScene);
	
	Integer update(DigeScene digeScene);
	
	public int delete(String id);
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(DigeScene entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<DigeScene> findList(Page<DigeScene> page,DigeScene entity);
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<DigeScene> findList(DigeScene entity);
}