package com.djk.web.service.dige;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.dige.DigeSceneWriteDao;
import com.djk.web.entity.dige.DigeScene;


@Service
@Transactional(readOnly=true)
public class DigeSceneService {

	@Autowired
	private DigeSceneWriteDao digeSceneWriteDao;
	
    public DigeScene get(java.lang.Integer id){
    return digeSceneWriteDao.get(id);
    }
    
    public DigeScene checkNameUnique(String sceneName){
    	return digeSceneWriteDao.checkNameUnique(sceneName);
    }
     
    @Transactional(readOnly=false)
	public Integer insert(DigeScene foodSupplier){
    	foodSupplier.preInsert();
		return digeSceneWriteDao.insert(foodSupplier);
	}
    @Transactional(readOnly=false)
	public Integer update(DigeScene foodSupplier){
    	foodSupplier.preUpdate();
	 return digeSceneWriteDao.update(foodSupplier);
	}
	
    @Transactional(readOnly=false)
	public int delete(String id){
	  return digeSceneWriteDao.delete(id);
	}
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(DigeScene entity){
	   return digeSceneWriteDao.count(entity);
	}
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<DigeScene> findList(Page<DigeScene> page,DigeScene entity){
		return digeSceneWriteDao.findList(page, entity);
	}
	
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<DigeScene> findList(DigeScene entity){
	  return digeSceneWriteDao.findList(entity);
	}
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<DigeScene> findPage(DigeScene entity){
		Page<DigeScene> page = new PageFactory<DigeScene>().defaultPage();
		entity.setPage(page);
		page.setTotal(digeSceneWriteDao.count(entity));
		page.setRecords(digeSceneWriteDao.findList(page,entity));
		return new PageInfoBT<DigeScene>(page);
	}
}
