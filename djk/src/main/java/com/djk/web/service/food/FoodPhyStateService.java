package com.djk.web.service.food;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.food.FoodPhyStateWriteDao;
import com.djk.web.entity.food.FoodPhyState;
import com.djk.web.entity.food.FoodSeasonal;

@Service(value = "foodPhyStateService")
@Transactional(readOnly=true)
public class FoodPhyStateService {

	@Autowired
	private FoodPhyStateWriteDao dao;

	public  FoodPhyState get(java.lang.Integer id){
		return dao.get(id);
	}
	
	public FoodPhyState checkNameUnique(String foodStatus){
		return dao.checkNameUnique(foodStatus);
	}

	public Integer insert(FoodPhyState foodPhyState){
		return dao.insert(foodPhyState);
	}

	public Integer update(FoodPhyState foodPhyState){
		return dao.update(foodPhyState);
	}
	
	
	public int delete(String id){
		  return dao.delete(id);
		}
		/**
		 * 获取条数
		 * @param entity
		 * @return
		 */
		public int count(FoodPhyState entity){
		   return dao.count(entity);
		}
		
		/**
		 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
		 * @param entity
		 * @return
		 */
		public List<FoodPhyState> findList(Page<FoodPhyState> page,FoodPhyState entity){
			return dao.findList(page, entity);
		}
		
		
		/**
		 * 查询不带分页的列表
		 * @param entity
		 * @return
		 */
		public List<FoodPhyState> findList(FoodPhyState entity){
		  return dao.findList(entity);
		}
		
		
		/**
		 * 分页查询数据
		 * @param entity
		 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
		 * 把service层的分页信息，封装为bootstrap table通用的分页封装
		 */
		public PageInfoBT<FoodPhyState> findPage(FoodPhyState entity){
			Page<FoodPhyState> page = new PageFactory<FoodPhyState>().defaultPage();
			entity.setPage(page);
			page.setTotal(dao.count(entity));
			page.setRecords(dao.findList(page,entity));
			return new PageInfoBT<FoodPhyState>(page);
		}
}
