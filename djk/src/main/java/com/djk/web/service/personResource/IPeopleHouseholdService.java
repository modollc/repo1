package com.djk.web.service.personResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.personResource.PeopleHouseholdWriteDao;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.personResource.PeopleHousehold;

@Service
public class IPeopleHouseholdService {
	
	@Resource
	private PeopleHouseholdWriteDao peopleHouseholdWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<PeopleHousehold> findPage(PeopleHousehold entity){
		Page<PeopleHousehold> page = new PageFactory<PeopleHousehold>().defaultPage();
		entity.setPage(page);
		page.setTotal(peopleHouseholdWriteDao.count(entity));
		page.setRecords(peopleHouseholdWriteDao.findList(page,entity));
		return new PageInfoBT<PeopleHousehold>(page);
	}
	
	public Integer insert(PeopleHousehold peopleHousehold){
		return peopleHouseholdWriteDao.insert(peopleHousehold);
	}
	
	public PeopleHousehold get(java.lang.Integer id){
	    return peopleHouseholdWriteDao.get(id);
	}
	
	public Integer update(PeopleHousehold peopleHousehold){
		return peopleHouseholdWriteDao.update(peopleHousehold);
	}
	public Integer delete(java.lang.Integer id){
		return peopleHouseholdWriteDao.delete(id);
	}
	public PeopleHousehold checkNameUnique(String household){
    	return peopleHouseholdWriteDao.checkNameUnique(household);
    }
}