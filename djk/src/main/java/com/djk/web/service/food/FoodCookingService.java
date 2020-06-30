package com.djk.web.service.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.food.FoodCookingWriteDao;
import com.djk.web.entity.food.FoodCooking;



@Service
@Transactional(readOnly=true)
public class FoodCookingService {

	@Autowired
	private FoodCookingWriteDao foodSupplierWriteDao;
	
    public FoodCooking get(java.lang.Integer id){
    return foodSupplierWriteDao.get(id);
    }
    
    public FoodCooking checkNameUnique(String cookingMethod){
    	return foodSupplierWriteDao.checkNameUnique(cookingMethod);
    }
	
	public Integer insert(FoodCooking foodSupplier){
		return foodSupplierWriteDao.insert(foodSupplier);
	}
	
	public Integer update(FoodCooking foodSupplier){
	 return foodSupplierWriteDao.update(foodSupplier);
	}
	
	
	public int delete(String id){
	  return foodSupplierWriteDao.delete(id);
	}
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(FoodCooking entity){
	   return foodSupplierWriteDao.count(entity);
	}
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<FoodCooking> findList(Page<FoodCooking> page,FoodCooking entity){
		return foodSupplierWriteDao.findList(page, entity);
	}
	
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<FoodCooking> findList(FoodCooking entity){
	  return foodSupplierWriteDao.findList(entity);
	}
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<FoodCooking> findPage(FoodCooking entity){
		Page<FoodCooking> page = new PageFactory<FoodCooking>().defaultPage();
		entity.setPage(page);
		page.setTotal(foodSupplierWriteDao.count(entity));
		page.setRecords(foodSupplierWriteDao.findList(page,entity));
		return new PageInfoBT<FoodCooking>(page);
	}
}
