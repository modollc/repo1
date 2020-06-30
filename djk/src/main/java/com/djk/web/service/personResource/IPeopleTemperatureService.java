package com.djk.web.service.personResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.personResource.PeopleTemperatureWriteDao;
import com.djk.web.entity.personResource.PeopleAppetite;
import com.djk.web.entity.personResource.PeopleTemperature;

@Service
public class IPeopleTemperatureService {
	
	@Resource
	private PeopleTemperatureWriteDao peopleTemperatureWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<PeopleTemperature> findPage(PeopleTemperature entity){
		Page<PeopleTemperature> page = new PageFactory<PeopleTemperature>().defaultPage();
		entity.setPage(page);
		page.setTotal(peopleTemperatureWriteDao.count(entity));
		page.setRecords(peopleTemperatureWriteDao.findList(page,entity));
		return new PageInfoBT<PeopleTemperature>(page);
	}
	
	public Integer insert(PeopleTemperature peopleTemperature){
		return peopleTemperatureWriteDao.insert(peopleTemperature);
	}
	
	public PeopleTemperature get(java.lang.Integer id){
	    return peopleTemperatureWriteDao.get(id);
	}
	
	public Integer update(PeopleTemperature peopleTemperature){
		return peopleTemperatureWriteDao.update(peopleTemperature);
	}
	public Integer delete(java.lang.Integer id){
		return peopleTemperatureWriteDao.delete(id);
	}
	public PeopleTemperature checkNameUnique(String temperature){
    	return peopleTemperatureWriteDao.checkNameUnique(temperature);
    }
}