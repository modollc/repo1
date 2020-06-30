package com.djk.web.service.personResource;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.personResource.PeopleActiveWriteDao;
import com.djk.web.entity.personResource.PeopleActive;
import com.djk.web.entity.personResource.PeopleSleep;

@Service
public class IPeopleActiveService {
	
	@Resource
	private PeopleActiveWriteDao peopleActiveWriteDao;
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<PeopleActive> findPage(PeopleActive entity){
		Page<PeopleActive> page = new PageFactory<PeopleActive>().defaultPage();
		entity.setPage(page);
		page.setTotal(peopleActiveWriteDao.count(entity));
		page.setRecords(peopleActiveWriteDao.findList(page,entity));
		return new PageInfoBT<PeopleActive>(page);
	}
	
	public Integer insert(PeopleActive peopleActive){
		return peopleActiveWriteDao.insert(peopleActive);
	}
	
	public PeopleActive get(java.lang.Integer id){
	    return peopleActiveWriteDao.get(id);
	}
	
	public Integer update(PeopleActive peopleActive){
		return peopleActiveWriteDao.update(peopleActive);
	}
	public Integer delete(java.lang.Integer id){
		return peopleActiveWriteDao.delete(id);
	}
	public PeopleActive checkNameUnique(String activeName){
    	return peopleActiveWriteDao.checkNameUnique(activeName);
    }
}