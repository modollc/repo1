package com.djk.web.service.nutrition;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;
import com.djk.web.dao.nutrition.NutritionCategoryWriteDao;
import com.djk.web.entity.nutrition.NutritionCategory;



@Service
public class NutritionCategoryService {

	@Autowired
	private NutritionCategoryWriteDao nutritionCategoryWriteDao;

	public NutritionCategory get(java.lang.Integer id){
		return nutritionCategoryWriteDao.get(id);
	}

	@Transactional(readOnly=false)
	public Integer insert(NutritionCategory foodSupplier){
		foodSupplier.preInsert();
		return nutritionCategoryWriteDao.insert(foodSupplier);
	}
	@Transactional(readOnly=false)
	public Integer update(NutritionCategory foodSupplier){
		foodSupplier.preUpdate();
		return nutritionCategoryWriteDao.update(foodSupplier);
	}

	@Transactional(readOnly=false)
	public int delete(String id){
		Integer result = nutritionCategoryWriteDao.checkDelete(id);
		if(result>0){
			return 3;
		}
		return nutritionCategoryWriteDao.delete(id);
	}
	/**
	 * 获取条数
	 * @param entity
	 * @return
	 */
	public int count(NutritionCategory entity){
		return nutritionCategoryWriteDao.count(entity);
	}

	/**
	 * 查询数据列表,如果需要分页,请设置分页对象,如:entity.setPage(new Page<T>());
	 * @param entity
	 * @return
	 */
	public List<NutritionCategory> findList(Page<NutritionCategory> page,NutritionCategory entity){
		return nutritionCategoryWriteDao.findList(page, entity);
	}


	/**
	 * 查询不带分页的列表
	 * @param entity
	 * @return
	 */
	public List<NutritionCategory> findList(NutritionCategory entity){
		return nutritionCategoryWriteDao.findList(entity);
	}


	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<NutritionCategory> findPage(NutritionCategory entity){
		Page<NutritionCategory> page = new PageFactory<NutritionCategory>().defaultPage();
		entity.setPage(page);
		page.setTotal(nutritionCategoryWriteDao.count(entity));
		page.setRecords(nutritionCategoryWriteDao.findList(page,entity));
		return new PageInfoBT<NutritionCategory>(page);
	}




	/**
	 * 根据pId查询下级分类信息
	 * @param pId 父id
	 * @return
	 */
	public  List<Map<String, Object>> getCategoryListByPId(Integer pId) {

		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();

		// 树根级
		if(pId == null) {
			pId = 0;
		}

		List<NutritionCategory> categoryList = nutritionCategoryWriteDao.getCategoryListByPId(pId);

		for(NutritionCategory nutritionCategory : categoryList) {
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", nutritionCategory.getId());// 根节点的ID
			root.put("name", nutritionCategory.getName()); // 根节点的名字
			root.put("isParent", nutritionCategoryWriteDao.isSubNodeById(nutritionCategory.getId())>0 ? true : false);//// 设置根节点为父节点
			if(pId == 0){
				root.put("open", true);// 是否展开
			}
			// 查询下级节点
			List<Map<String, Object>> returnList1 = new ArrayList<Map<String, Object>>();

			List<NutritionCategory> menus = nutritionCategoryWriteDao.getCategoryListByPId(nutritionCategory.getId());
			for (NutritionCategory menu : menus) {
				Map<String, Object> node = new HashMap<String, Object>();
				node.put("id", menu.getId());
				node.put("name", menu.getName());
				node.put("isParent", nutritionCategoryWriteDao.isSubNodeById(menu.getId())>0 ? true : false);
				returnList1.add(node);
			}

			root.put("children", returnList1);
			returnList.add(root);
		}

		return returnList;
	}



	/**
	 * 保存分类
	 * @param pId 分类父id
	 * @param name 分类名称
	 * @return
	 */
	public NutritionCategory addCategory(Integer pId, String name, Integer userId) {
		NutritionCategory category = new NutritionCategory();
		category.setPid(pId);
		category.setName(name);
		category.setState(1);
		category.setCreateBy(userId);
		category.setAlias(name);
		nutritionCategoryWriteDao.insert(category);
		NutritionCategory nutritionCategory1 = nutritionCategoryWriteDao.get(pId);
		NutritionCategory nutritionCategory = nutritionCategoryWriteDao.getCategory(category);
		NutritionCategory cg = new NutritionCategory();
		cg.setId(category.getId());
		cg.setPids(nutritionCategory1.getPids()+nutritionCategory.getId()+",");
		nutritionCategoryWriteDao.uppids(cg);
		return category; 
	}

	/**
	 * 保存分类
	 * @param pId 分类父id
	 * @param name 分类名称
	 * @return
	 */
	public NutritionCategory updateCategory(Integer id, String name, Integer userId) {
		NutritionCategory category = new NutritionCategory();
		category.setId(id);
		category.setName(name);
		category.setState(1);
		category.setUpdateBy(userId);
		category.setUpdateDate(new Date());
		nutritionCategoryWriteDao.update(category);
		return category; 
	}

	/**
	 * 分类
	 * @param id 分类id
	 * @return
	 */
	public Boolean deleteCategory(Integer id, Integer userId) {
		NutritionCategory foodCategory = new NutritionCategory();
		foodCategory.setId(id);
		foodCategory.setUpdateBy(userId);
		foodCategory.setState(2);
		Integer result = nutritionCategoryWriteDao.checkDelete(id.toString());
		if(result>0){
			return false;
		}
		
		int rel = nutritionCategoryWriteDao.update(foodCategory);//foodCategoryWriteDao.delete(id);
		return rel > 0 ? true : false;
	}
	/**
	 * 验证分类是否有子分类
	 * @param id
	 * @return
	 */
	public Boolean validateChild(Integer id) {
		return nutritionCategoryWriteDao.isSubNodeById(id)>0 ? true : false;
	}



	/**
	 * 复制食物分类
	 * @param id
	 * @param userId
	 * @return
	 */
	public int copyCategory(Integer id, Integer userId) {
		NutritionCategory selectChildrenById = nutritionCategoryWriteDao.selectChildrenById(id);
		
		int result = 0;
		String pids ="";

		//插入第一条数据
		if(selectChildrenById!=null){
			NutritionCategory category = new NutritionCategory();
			category.setPid(selectChildrenById.getPid());
			category.setState(1);
			category.setCreateBy(userId);
			category.setCreateDate(new Date());
			category.setName(selectChildrenById.getName()+"(1)");
			category.setAlias(selectChildrenById.getName()+"(1)");
			category.setCode(selectChildrenById.getCode()+"(1)");
			category.setRemarks(selectChildrenById.getRemarks());
			result=nutritionCategoryWriteDao.insert(category);
			//修改PIDS
			NutritionCategory cg = new NutritionCategory();
			cg.setId(category.getId());
			cg.setPids(selectChildrenById.getPids().replace(","+id+",", ","+category.getId()+","));
			pids=cg.getPids();
			result=nutritionCategoryWriteDao.uppids(cg);
			//批量插入    *****暂时不用*********
			//saveData(category.getId(), selectChildrenById.getList(),pids,userId);
			
		}
		
		

		return result;
	}

	/**
	 * 插入数据
	 * @param id
	 * @param list
	 * @param pids
	 * @param userId
	 */
    public void saveData(int id,Set<NutritionCategory> list,String pids,int userId) {	
		for (NutritionCategory nutritionCategory : list) {
			NutritionCategory category = new NutritionCategory();
			category.setPid(id);
			category.setState(1);
			category.setCreateBy(userId);
			category.setCreateDate(new Date());
			category.setName(nutritionCategory.getName()+"(1)");
			category.setAlias(nutritionCategory.getName()+"(1)");
			category.setCode(nutritionCategory.getCode()+"(1)");
			category.setRemarks(nutritionCategory.getRemarks());
			nutritionCategoryWriteDao.insert(category);
			
		
			NutritionCategory cg = new NutritionCategory();
			cg.setId(category.getId());
			cg.setPids(pids+category.getId()+",");
			nutritionCategoryWriteDao.uppids(cg);
			
			saveData(category.getId(),nutritionCategory.getList(),pids+category.getId()+",",userId);
		}
		
	}
	
	
	
	


}
