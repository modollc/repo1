package com.djk.web.service.systemResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.systemResource.SystemHealthWriteDao;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.systemResource.SystemHealth;

@Service
public class ISystemHealService {
	
	@Resource
	private SystemHealthWriteDao systemHealWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<SystemHealth> findPage(SystemHealth entity){
		Page<SystemHealth> page = new PageFactory<SystemHealth>().defaultPage();
		entity.setPage(page);
		page.setTotal(systemHealWriteDao.count(entity));
		page.setRecords(systemHealWriteDao.findList(page,entity));
		return new PageInfoBT<SystemHealth>(page);
	}
	
	public Integer insert(SystemHealth systemHealth){
		return systemHealWriteDao.insert(systemHealth);
	}
	
	public SystemHealth get(java.lang.Integer id){
	    return systemHealWriteDao.get(id);
	}
	
	public Integer update(SystemHealth systemHealth){
		return systemHealWriteDao.update(systemHealth);
	}
	public Integer delete(java.lang.Integer id){
		return systemHealWriteDao.delete(id);
	}
	public SystemHealth checkNameUnique(String healthName){
    	return systemHealWriteDao.checkNameUnique(healthName);
    }
}