package com.djk.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;





@Transactional(readOnly=true)
public abstract class CrudService<D extends BaseDao<T>,T extends DataModel<T>> {

	/**
	 * 持久层对象
	 */
	@Qualifier  
	private D dao;

	
	
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(T entity) {
		return dao.count(entity);
	}
	/**
	 * 查询不带分页列表
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity){
		return dao.findList(entity);
	}
	
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<T> findPage(T entity){
		Page<T> page = new PageFactory<T>().defaultPage();
		entity.setPage(page);
		page.setTotal(dao.count(entity));
		page.setRecords(dao.findList(page,entity));
		return new PageInfoBT<T>(page);
	}
	
	public int delete(String id){
		return dao.delete(id);
	}
	

	
}

