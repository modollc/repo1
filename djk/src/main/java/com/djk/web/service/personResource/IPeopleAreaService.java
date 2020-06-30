package com.djk.web.service.personResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.personResource.PeopleAreaWriteDao;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.personResource.PeopleArea;

@Service
public class IPeopleAreaService {
	
	@Resource
	private PeopleAreaWriteDao peopleAreaWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<PeopleArea> findPage(PeopleArea entity){
		Page<PeopleArea> page = new PageFactory<PeopleArea>().defaultPage();
		entity.setPage(page);
		page.setTotal(peopleAreaWriteDao.count(entity));
		page.setRecords(peopleAreaWriteDao.findList(page,entity));
		return new PageInfoBT<PeopleArea>(page);
	}
	
	public Integer insert(PeopleArea peopleArea){
		return peopleAreaWriteDao.insert(peopleArea);
	}
	
	public PeopleArea get(java.lang.Integer id){
	    return peopleAreaWriteDao.get(id);
	}
	
	public Integer update(PeopleArea peopleArea){
		return peopleAreaWriteDao.update(peopleArea);
	}
	public Integer delete(java.lang.Integer id){
		return peopleAreaWriteDao.delete(id);
	}
	public PeopleArea checkNameUnique(String area){
    	return peopleAreaWriteDao.checkNameUnique(area);
    }
}