package com.djk.web.service.systemResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.systemResource.SystemConstantSexWriteDao;
import com.djk.web.entity.systemResource.SystemConstant;
import com.djk.web.entity.systemResource.SystemConstantSex;

@Service
public class ISystemConstantSexService {
	
	@Resource
	private SystemConstantSexWriteDao systemConstantSexWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<SystemConstantSex> findPage(SystemConstantSex entity){
		Page<SystemConstantSex> page = new PageFactory<SystemConstantSex>().defaultPage();
		entity.setPage(page);
		page.setTotal(systemConstantSexWriteDao.count(entity));
		page.setRecords(systemConstantSexWriteDao.findList(page,entity));
		return new PageInfoBT<SystemConstantSex>(page);
	}
	
	public Integer insert(SystemConstantSex systemConstantSex){
		return systemConstantSexWriteDao.insert(systemConstantSex);
	}
	
	public SystemConstantSex get(java.lang.Integer id){
	    return systemConstantSexWriteDao.get(id);
	}
	
	public Integer update(SystemConstantSex systemConstantSex){
		return systemConstantSexWriteDao.update(systemConstantSex);
	}
	public Integer delete(java.lang.Integer id){
		return systemConstantSexWriteDao.delete(id);
	}
	public SystemConstantSex checkNameUnique(String sexName){
    	return systemConstantSexWriteDao.checkNameUnique(sexName);
    }
}