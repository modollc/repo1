package com.djk.web.service.systemResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.systemResource.SystemWeightWriteDao;
import com.djk.web.entity.systemResource.SystemHealth;
import com.djk.web.entity.systemResource.SystemWeight;

@Service
public class ISystemWeightService {
	
	@Resource
	private SystemWeightWriteDao systemWeightWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<SystemWeight> findPage(SystemWeight entity){
		Page<SystemWeight> page = new PageFactory<SystemWeight>().defaultPage();
		entity.setPage(page);
		page.setTotal(systemWeightWriteDao.count(entity));
		page.setRecords(systemWeightWriteDao.findList(page,entity));
		return new PageInfoBT<SystemWeight>(page);
	}
	
	public Integer insert(SystemWeight systemWeight){
		return systemWeightWriteDao.insert(systemWeight);
	}
	
	public SystemWeight get(java.lang.Integer id){
	    return systemWeightWriteDao.get(id);
	}
	
	public Integer update(SystemWeight systemWeight){
		return systemWeightWriteDao.update(systemWeight);
	}
	public Integer delete(java.lang.Integer id){
		return systemWeightWriteDao.delete(id);
	}
	public SystemWeight checkNameUnique(String weightName){
    	return systemWeightWriteDao.checkNameUnique(weightName);
    }
}