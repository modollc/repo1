package com.djk.web.service.systemResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.systemResource.SystemConstantAgeWriteDao;
import com.djk.web.entity.systemResource.SystemConstantAge;
import com.djk.web.entity.systemResource.SystemHealth;

@Service
public class ISystemConstantAgeService {
	
	@Resource
	private SystemConstantAgeWriteDao systemConstantAgeWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<SystemConstantAge> findPage(SystemConstantAge entity){
		Page<SystemConstantAge> page = new PageFactory<SystemConstantAge>().defaultPage();
		entity.setPage(page);
		page.setTotal(systemConstantAgeWriteDao.count(entity));
		page.setRecords(systemConstantAgeWriteDao.findList(page,entity));
		return new PageInfoBT<SystemConstantAge>(page);
	}
	
	public Integer insert(SystemConstantAge systemConstantAge){
		return systemConstantAgeWriteDao.insert(systemConstantAge);
	}
	
	public SystemConstantAge get(java.lang.Integer id){
	    return systemConstantAgeWriteDao.get(id);
	}
	
	public Integer update(SystemConstantAge systemConstantAge){
		return systemConstantAgeWriteDao.update(systemConstantAge);
	}
	public Integer delete(java.lang.Integer id){
		return systemConstantAgeWriteDao.delete(id);
	}
	public SystemConstantAge checkNameUnique(String ageName){
    	return systemConstantAgeWriteDao.checkNameUnique(ageName);
    }
	
}