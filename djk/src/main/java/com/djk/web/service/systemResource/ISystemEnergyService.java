package com.djk.web.service.systemResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.systemResource.SystemEnergyWriteDao;
import com.djk.web.dao.systemResource.SystemHealthWriteDao;
import com.djk.web.entity.systemResource.SystemEnergy;
import com.djk.web.entity.systemResource.SystemHealth;

@Service
public class ISystemEnergyService {
	
	@Resource
	private SystemEnergyWriteDao systemEnergyWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<SystemEnergy> findPage(SystemEnergy entity){
		Page<SystemEnergy> page = new PageFactory<SystemEnergy>().defaultPage();
		entity.setPage(page);
		page.setTotal(systemEnergyWriteDao.count(entity));
		page.setRecords(systemEnergyWriteDao.findList(page,entity));
		return new PageInfoBT<SystemEnergy>(page);
	}
	
	public Integer insert(SystemEnergy systemEnergy){
		return systemEnergyWriteDao.insert(systemEnergy);
	}
	
	public SystemEnergy get(java.lang.Integer id){
	    return systemEnergyWriteDao.get(id);
	}
	
	public Integer update(SystemEnergy systemEnergy){
		return systemEnergyWriteDao.update(systemEnergy);
	}
	public Integer delete(java.lang.Integer id){
		return systemEnergyWriteDao.delete(id);
	}
	public SystemEnergy checkNameUnique(String energyName){
    	return systemEnergyWriteDao.checkNameUnique(energyName);
    }
}