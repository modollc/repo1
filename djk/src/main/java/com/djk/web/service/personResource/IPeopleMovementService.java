package com.djk.web.service.personResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.personResource.PeopleMovementWriteDao;
import com.djk.web.dao.personResource.PeopleSleepWriteDao;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.personResource.PeopleMovement;
import com.djk.web.entity.personResource.PeopleSleep;

@Service
public class IPeopleMovementService {
	
	@Resource
	private PeopleMovementWriteDao peopleMovementWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<PeopleMovement> findPage(PeopleMovement entity){
		Page<PeopleMovement> page = new PageFactory<PeopleMovement>().defaultPage();
		entity.setPage(page);
		page.setTotal(peopleMovementWriteDao.count(entity));
		page.setRecords(peopleMovementWriteDao.findList(page,entity));
		return new PageInfoBT<PeopleMovement>(page);
	}
	
	public Integer insert(PeopleMovement peopleMovement){
		return peopleMovementWriteDao.insert(peopleMovement);
	}
	
	public PeopleMovement get(java.lang.Integer id){
	    return peopleMovementWriteDao.get(id);
	}
	
	public Integer update(PeopleMovement peopleMovement){
		return peopleMovementWriteDao.update(peopleMovement);
	}
	public Integer delete(java.lang.Integer id){
		return peopleMovementWriteDao.delete(id);
	}
	public PeopleMovement checkMovementUnique(String movement){
    	return peopleMovementWriteDao.checkNameUnique(movement);
    }
}