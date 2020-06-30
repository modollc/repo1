package com.djk.web.service.personResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.personResource.PeopleAppetiteWriteDao;
import com.djk.web.dao.personResource.PeopleMovementWriteDao;
import com.djk.web.dao.personResource.PeopleSleepWriteDao;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.personResource.PeopleAppetite;
import com.djk.web.entity.personResource.PeopleMovement;
import com.djk.web.entity.personResource.PeopleSleep;

@Service
public class IPeopleAppetiteService {
	
	@Resource
	private PeopleAppetiteWriteDao peopleAppetiteWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<PeopleAppetite> findPage(PeopleAppetite entity){
		Page<PeopleAppetite> page = new PageFactory<PeopleAppetite>().defaultPage();
		entity.setPage(page);
		page.setTotal(peopleAppetiteWriteDao.count(entity));
		page.setRecords(peopleAppetiteWriteDao.findList(page,entity));
		return new PageInfoBT<PeopleAppetite>(page);
	}
	
	public Integer insert(PeopleAppetite peopleAppetite){
		return peopleAppetiteWriteDao.insert(peopleAppetite);
	}
	
	public PeopleAppetite get(java.lang.Integer id){
	    return peopleAppetiteWriteDao.get(id);
	}
	
	public Integer update(PeopleAppetite peopleAppetite){
		return peopleAppetiteWriteDao.update(peopleAppetite);
	}
	public Integer delete(java.lang.Integer id){
		return peopleAppetiteWriteDao.delete(id);
	}
	public PeopleAppetite checkNameUnique(String appetiteName){
    	return peopleAppetiteWriteDao.checkNameUnique(appetiteName);
    }
}