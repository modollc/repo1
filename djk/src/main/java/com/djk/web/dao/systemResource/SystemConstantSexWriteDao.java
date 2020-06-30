package com.djk.web.dao.systemResource;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.web.entity.systemResource.SystemConstant;
import com.djk.web.entity.systemResource.SystemConstantAge;
import com.djk.web.entity.systemResource.SystemConstantSex;


@Repository
public interface SystemConstantSexWriteDao {
 
	SystemConstantSex get(java.lang.Integer id);
	
	Integer insert(SystemConstantSex systemConstantSex);
	
	Integer update(SystemConstantSex systemConstantSex);
	
	Integer delete(java.lang.Integer id);
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(SystemConstantSex entity);
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<SystemConstantSex> findList(Page<SystemConstantSex> page,SystemConstantSex entity);
	
	SystemConstantSex checkNameUnique(String sexName);
}