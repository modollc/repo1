package com.djk.web.service.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.food.FoodSeasonalWriteDao;
import com.djk.web.entity.food.FoodSeasonal;

@Service
@Transactional(readOnly=true)
public class FoodSeasonalService {

	@Autowired
	private FoodSeasonalWriteDao foodSeasonalWriteDao;
	
    public FoodSeasonal get(java.lang.Integer id){
    return foodSeasonalWriteDao.get(id);
    }
	
	public Integer insert(FoodSeasonal foodSeasonal){
		return foodSeasonalWriteDao.insert(foodSeasonal);
	}
	
	public Integer update(FoodSeasonal foodSeasonal){
	 return foodSeasonalWriteDao.update(foodSeasonal);
	}
	
	public FoodSeasonal checkNameUnique(String seasonal){
		return foodSeasonalWriteDao.checkNameUnique(seasonal);
	}
	public int delete(String id){
		
	  int result=foodSeasonalWriteDao.checkDelete(foodSeasonalWriteDao.get(Integer.parseInt(id)).getSeasonal());
	  if(result>0){
		  return 3;
	  }
	  return foodSeasonalWriteDao.delete(id);
	}
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(FoodSeasonal entity){
	   return foodSeasonalWriteDao.count(entity);
	}
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<FoodSeasonal> findList(Page<FoodSeasonal> page,FoodSeasonal entity){
		return foodSeasonalWriteDao.findList(page, entity);
	}
	
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<FoodSeasonal> findList(FoodSeasonal entity){
	  return foodSeasonalWriteDao.findList(entity);
	}
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<FoodSeasonal> findPage(FoodSeasonal entity){
		Page<FoodSeasonal> page = new PageFactory<FoodSeasonal>().defaultPage();
		entity.setPage(page);
		page.setTotal(foodSeasonalWriteDao.count(entity));
		page.setRecords(foodSeasonalWriteDao.findList(page,entity));
		return new PageInfoBT<FoodSeasonal>(page);
	}

}
