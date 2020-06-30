package com.djk.web.service.systemResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.systemResource.SystemConstantWriteDao;
import com.djk.web.entity.systemResource.SystemConstant;
import com.djk.web.entity.systemResource.SystemConstantAge;

@Service
public class ISystemConstantService {
	
	@Resource
	private SystemConstantWriteDao systemConstantWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<SystemConstant> findPage(SystemConstant entity){
		Page<SystemConstant> page = new PageFactory<SystemConstant>().defaultPage();
		entity.setPage(page);
		page.setTotal(systemConstantWriteDao.count(entity));
		page.setRecords(systemConstantWriteDao.findList(page,entity));
		return new PageInfoBT<SystemConstant>(page);
	}
	
	public Integer insert(SystemConstant systemConstant){
		return systemConstantWriteDao.insert(systemConstant);
	}
	
	public SystemConstant get(java.lang.Integer id){
	    return systemConstantWriteDao.get(id);
	}
	
	public Integer update(SystemConstant systemConstant){
		return systemConstantWriteDao.update(systemConstant);
	}
	public Integer delete(java.lang.Integer id){
		return systemConstantWriteDao.delete(id);
	}
	public SystemConstant checkNameUnique(String constantName){
    	return systemConstantWriteDao.checkNameUnique(constantName);
    }
}