package com.djk.web.service.systemResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.systemResource.SystemUnitWriteDao;
import com.djk.web.entity.systemResource.SystemEnergy;
import com.djk.web.entity.systemResource.SystemUnit;

@Service
public class ISystemUnitService {
	
	@Resource
	private SystemUnitWriteDao systemUnitWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<SystemUnit> findPage(SystemUnit entity){
		Page<SystemUnit> page = new PageFactory<SystemUnit>().defaultPage();
		entity.setPage(page);
		page.setTotal(systemUnitWriteDao.count(entity));
		page.setRecords(systemUnitWriteDao.findList(page,entity));
		return new PageInfoBT<SystemUnit>(page);
	}
	
	public Integer insert(SystemUnit systemUnit){
		return systemUnitWriteDao.insert(systemUnit);
	}
	
	public SystemUnit get(java.lang.Integer id){
	    return systemUnitWriteDao.get(id);
	}
	
	public Integer update(SystemUnit systemUnit){
		return systemUnitWriteDao.update(systemUnit);
	}
	public Integer delete(java.lang.Integer id){
		return systemUnitWriteDao.delete(id);
	}
	public SystemUnit checkNameUnique(String unitName){
    	return systemUnitWriteDao.checkNameUnique(unitName);
    }
	public Integer getIsUsedInFood(java.lang.Integer id){
		return systemUnitWriteDao.isUsedInFood(id);
	}
}