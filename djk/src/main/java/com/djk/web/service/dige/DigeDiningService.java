package com.djk.web.service.dige;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.dige.DigeDiningWriteDao;
import com.djk.web.entity.dige.DigeDining;
import com.djk.web.entity.dige.DigeEatfast;


@Service
@Transactional(readOnly=true)
public class DigeDiningService {

	@Autowired
	private DigeDiningWriteDao digeSceneWriteDao;
	
    public DigeDining get(java.lang.Integer id){
    return digeSceneWriteDao.get(id);
    }
     
    @Transactional(readOnly=false)
	public Integer insert(DigeDining foodSupplier){
    	foodSupplier.preInsert();
		return digeSceneWriteDao.insert(foodSupplier);
	}
    @Transactional(readOnly=false)
	public Integer update(DigeDining foodSupplier){
    	foodSupplier.preUpdate();
	 return digeSceneWriteDao.update(foodSupplier);
	}
	
    @Transactional(readOnly=false)
	public int delete(String id){
	  return digeSceneWriteDao.delete(id);
	}
    
    public DigeDining checkNameUnique(String mealQuantity){
    	return digeSceneWriteDao.checkNameUnique(mealQuantity);
    }
    
    
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(DigeDining entity){
	   return digeSceneWriteDao.count(entity);
	}
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<DigeDining> findList(Page<DigeDining> page,DigeDining entity){
		return digeSceneWriteDao.findList(page, entity);
	}
	
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<DigeDining> findList(DigeDining entity){
	  return digeSceneWriteDao.findList(entity);
	}
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<DigeDining> findPage(DigeDining entity){
		Page<DigeDining> page = new PageFactory<DigeDining>().defaultPage();
		entity.setPage(page);
		page.setTotal(digeSceneWriteDao.count(entity));
		page.setRecords(digeSceneWriteDao.findList(page,entity));
		return new PageInfoBT<DigeDining>(page);
	}
}
