package com.djk.web.service.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.food.FoodOriginWriteDao;
import com.djk.web.entity.food.FoodOrigin;

@Service
@Transactional(readOnly=true)
public class FoodOriginService {

	@Autowired
	private FoodOriginWriteDao foodOriginWriteDao;
	
    public FoodOrigin get(java.lang.Integer id){
    return foodOriginWriteDao.get(id);
    }
    
    public FoodOrigin checkNameUnique(String originName){
    	return foodOriginWriteDao.checkNameUnique(originName);
    }
    
    public FoodOrigin getName(String originName){
        return foodOriginWriteDao.getName(originName);
    } 
    @Transactional(readOnly=false)
	public Integer insert(FoodOrigin foodSupplier){
    	foodSupplier.preInsert();
		return foodOriginWriteDao.insert(foodSupplier);
	}
    @Transactional(readOnly=false)
	public Integer update(FoodOrigin foodSupplier){
    	foodSupplier.preUpdate();
	 return foodOriginWriteDao.update(foodSupplier);
	}
	
    @Transactional(readOnly=false)
	public int delete(String id){
      int result=foodOriginWriteDao.checkDelete(foodOriginWriteDao.get(Integer.parseInt(id)).getOriginName());
	  if(result>0){
		  return 3;
	  }
      return foodOriginWriteDao.delete(id);
	}
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(FoodOrigin entity){
	   return foodOriginWriteDao.count(entity);
	}
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<FoodOrigin> findList(Page<FoodOrigin> page,FoodOrigin entity){
		return foodOriginWriteDao.findList(page, entity);
	}
	
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<FoodOrigin> findList(FoodOrigin entity){
	  return foodOriginWriteDao.findList(entity);
	}
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<FoodOrigin> findPage(FoodOrigin entity){
		Page<FoodOrigin> page = new PageFactory<FoodOrigin>().defaultPage();
		entity.setPage(page);
		page.setTotal(foodOriginWriteDao.count(entity));
		page.setRecords(foodOriginWriteDao.findList(page,entity));
		return new PageInfoBT<FoodOrigin>(page);
	}
	
	/**
	 * 根据父id查询地区信息
	 * @param parentId
	 * @return
	 */
	public List<FoodOrigin> findListByPId(Integer parentId) {
		return foodOriginWriteDao.findListByPId(parentId);
	}
}
