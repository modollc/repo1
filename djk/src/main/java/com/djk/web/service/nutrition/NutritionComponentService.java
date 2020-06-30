package com.djk.web.service.nutrition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.nutrition.NutritionComponentWriteDao;
import com.djk.web.entity.nutrition.NutritionComponent;



@Service
@Transactional(readOnly=true)
public class NutritionComponentService {

	@Autowired
	private NutritionComponentWriteDao nutritionComponent;
	
    public NutritionComponent get(java.lang.Integer id){
    return nutritionComponent.get(id);
    }
     
    @Transactional(readOnly=false)
	public Integer insert(NutritionComponent foodSupplier){
    	foodSupplier.preInsert();
		return nutritionComponent.insert(foodSupplier);
	}
    @Transactional(readOnly=false)
	public Integer update(NutritionComponent foodSupplier){
    	foodSupplier.preUpdate();
	 return nutritionComponent.update(foodSupplier);
	}
	
    @Transactional(readOnly=false)
	public int delete(String id){
      int result=nutritionComponent.checkDelete(nutritionComponent.get(Integer.parseInt(id)).getName());
	  if(result>0){
		  return 3;
	  }
      return nutritionComponent.delete(id);
	}
    
    
    public NutritionComponent checkNameUnique(String name){
    	return nutritionComponent.checkNameUnique(name);
    }
    
    /**
     * 复制营养
     * @param id
     * @return
     */
    public Integer copy(Integer id){
    	NutritionComponent nutritionComponent2 = nutritionComponent.get(id);
    	nutritionComponent2.setId(null);
    	nutritionComponent2.setName(nutritionComponent2.getName()+"(1)");
    	return nutritionComponent.insert(nutritionComponent2);
    }
    
    
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(NutritionComponent entity){
	   return nutritionComponent.count(entity);
	}
	
	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<NutritionComponent> findList(Page<NutritionComponent> page,NutritionComponent entity){
		return nutritionComponent.findList(page, entity);
	}
	
	
	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<NutritionComponent> findList(NutritionComponent entity){
	  return nutritionComponent.findList(entity);
	}
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<NutritionComponent> findPage(NutritionComponent entity){
		Page<NutritionComponent> page = new PageFactory<NutritionComponent>().defaultPage();
		entity.setPage(page);
		page.setTotal(nutritionComponent.count(entity));
		page.setRecords(nutritionComponent.findList(page,entity));
		return new PageInfoBT<NutritionComponent>(page);
	}
}
