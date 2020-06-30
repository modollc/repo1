package com.djk.web.service.dige;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.dige.DigeNutritionWriteDao;
import com.djk.web.entity.dige.DigeNutrition;


@Service
@Transactional(readOnly=true)
public class DigeNutritionService {


	@Autowired
	private DigeNutritionWriteDao digeNutritionWriteDao;
	
    public DigeNutrition get(java.lang.Integer id){
    return digeNutritionWriteDao.get(id);
    }
     
    @Transactional(readOnly=false)
	public Integer insert(DigeNutrition foodSupplier){
    	foodSupplier.preInsert();
		return digeNutritionWriteDao.insert(foodSupplier);
	}
    @Transactional(readOnly=false)
	public Integer update(DigeNutrition foodSupplier){
    	foodSupplier.preUpdate();
	 return digeNutritionWriteDao.update(foodSupplier);
	}
	
    @Transactional(readOnly=false)
	public int delete(String id){
	  return digeNutritionWriteDao.delete(id);
	}
    
    public DigeNutrition checkNameUnique(String nutritionQuantity){
    	return digeNutritionWriteDao.checkNameUnique(nutritionQuantity);
    }
    
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(DigeNutrition entity){
	   return digeNutritionWriteDao.count(entity);
	}
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<DigeNutrition> findList(Page<DigeNutrition> page,DigeNutrition entity){
		return digeNutritionWriteDao.findList(page, entity);
	}
	
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<DigeNutrition> findList(DigeNutrition entity){
	  return digeNutritionWriteDao.findList(entity);
	}
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<DigeNutrition> findPage(DigeNutrition entity){
		Page<DigeNutrition> page = new PageFactory<DigeNutrition>().defaultPage();
		entity.setPage(page);
		page.setTotal(digeNutritionWriteDao.count(entity));
		page.setRecords(digeNutritionWriteDao.findList(page,entity));
		return new PageInfoBT<DigeNutrition>(page);
	}
}
