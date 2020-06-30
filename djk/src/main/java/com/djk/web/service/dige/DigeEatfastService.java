package com.djk.web.service.dige;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.dige.DigeEatfastWriteDao;
import com.djk.web.entity.dige.DigeEatfast;


@Service
@Transactional(readOnly=true)
public class DigeEatfastService {

	@Autowired
	private DigeEatfastWriteDao digeSceneWriteDao;
	
    public DigeEatfast get(java.lang.Integer id){
    return digeSceneWriteDao.get(id);
    }
     
    @Transactional(readOnly=false)
	public Integer insert(DigeEatfast foodSupplier){
    	foodSupplier.preInsert();
		return digeSceneWriteDao.insert(foodSupplier);
	}
    @Transactional(readOnly=false)
	public Integer update(DigeEatfast foodSupplier){
    	foodSupplier.preUpdate();
	 return digeSceneWriteDao.update(foodSupplier);
	}
	
    @Transactional(readOnly=false)
	public int delete(String id){
	  return digeSceneWriteDao.delete(id);
	}
    
    public DigeEatfast checkNameUnique(String eatFast){
    	return digeSceneWriteDao.checkNameUnique(eatFast);
    }
    
    
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(DigeEatfast entity){
	   return digeSceneWriteDao.count(entity);
	}
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<DigeEatfast> findList(Page<DigeEatfast> page,DigeEatfast entity){
		return digeSceneWriteDao.findList(page, entity);
	}
	
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<DigeEatfast> findList(DigeEatfast entity){
	  return digeSceneWriteDao.findList(entity);
	}
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<DigeEatfast> findPage(DigeEatfast entity){
		Page<DigeEatfast> page = new PageFactory<DigeEatfast>().defaultPage();
		entity.setPage(page);
		page.setTotal(digeSceneWriteDao.count(entity));
		page.setRecords(digeSceneWriteDao.findList(page,entity));
		return new PageInfoBT<DigeEatfast>(page);
	}
}
