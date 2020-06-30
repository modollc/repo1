package com.djk.web.service.personResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.personResource.PeopleSleepWriteDao;
import com.djk.web.entity.dige.DigeDining;
import com.djk.web.entity.dige.DigeNutrition;
import com.djk.web.entity.personResource.PeopleSleep;

@Service
public class IPeopleSleepService {
	
	@Resource
	private PeopleSleepWriteDao peopleSleepWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<PeopleSleep> findPage(PeopleSleep entity){
		Page<PeopleSleep> page = new PageFactory<PeopleSleep>().defaultPage();
		entity.setPage(page);
		page.setTotal(peopleSleepWriteDao.count(entity));
		page.setRecords(peopleSleepWriteDao.findList(page,entity));
		return new PageInfoBT<PeopleSleep>(page);
	}
	
	public Integer insert(PeopleSleep peopleSleep){
		return peopleSleepWriteDao.insert(peopleSleep);
	}
	
	public PeopleSleep get(java.lang.Integer id){
	    return peopleSleepWriteDao.get(id);
	}
	
	public Integer update(PeopleSleep peopleSleep){
		return peopleSleepWriteDao.update(peopleSleep);
	}
	public Integer delete(java.lang.Integer id){
		return peopleSleepWriteDao.delete(id);
	}
	
	public PeopleSleep checkNameUnique(String sleepQuantity){
    	return peopleSleepWriteDao.checkNameUnique(sleepQuantity);
    }
}