package com.djk.web.service.food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djk.common.CrudService;
import com.djk.web.dao.food.FoodCategoryWriteDao;
import com.djk.web.entity.food.FoodCategory;
import com.djk.web.entity.food.FoodPublicCategory;

@Service(value = "foodCatetoryService")
@Transactional(readOnly=true)
public class FoodCatetoryService extends CrudService<FoodCategoryWriteDao,FoodCategory>{
	
	@Resource
	private FoodCategoryWriteDao foodCategoryWriteDao;
	
	// 查询食物分类是否被引用
	public Integer queryFoodGategoryRel(Integer id) {
		return foodCategoryWriteDao.queryFoodGategoryRel(id);
	}
	
	/**
	 * 获取所有父类id用“,”分割
	 * @param id
	 * @return
	 */
	public String getParentIdStr(Integer id) {
		return foodCategoryWriteDao.getParentIdStr(id);
	}
	
	/**
	 * 根据id查询食物分类
	 * @param id
	 * @return
	 */
	public FoodCategory get(java.lang.Integer id) {
		return foodCategoryWriteDao.get(id);
	}
	
	/**
	 * 根据pId查询下级分类信息
	 * @param pId 父id
	 * @return
	 */
	public  List<Map<String, Object>> getFoodCategoryListByPId(Integer pId) {
		
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		 
		// 树根级
		if(pId == null) {
			pId = 0;
		}
		 
		List<FoodCategory> foodCategoryList = foodCategoryWriteDao.getFoodCategoryListByPId(pId);
		
		for(FoodCategory foodCategory : foodCategoryList) {
			 Map<String, Object> root = new HashMap<String, Object>();
			 root.put("id", foodCategory.getId());// 根节点的ID
		     root.put("name", foodCategory.getName()); // 根节点的名字
		     root.put("isParent", foodCategoryWriteDao.isSubNodeById(foodCategory.getId())>0 ? true : false);//// 设置根节点为父节点
		     if(pId == 0){
		    	 root.put("open", true);// 是否展开
		     }
			 // 查询下级节点
			 List<Map<String, Object>> returnList1 = new ArrayList<Map<String, Object>>();
			 
			 List<FoodCategory> menus = foodCategoryWriteDao.getFoodCategoryListByPId(foodCategory.getId());
			 for (FoodCategory menu : menus) {
				 Map<String, Object> node = new HashMap<String, Object>();
				 node.put("id", menu.getId());
				 node.put("name", menu.getName());
				 node.put("isParent", foodCategoryWriteDao.isSubNodeById(menu.getId())>0 ? true : false);
				 returnList1.add(node);
			 }
			 
			 root.put("children", returnList1);
			 returnList.add(root);
		}
        
        return returnList;
	}
	
	
	/**
	 * 保存食物分类
	 * @param pId 食物分类父id
	 * @param name 食物分类名称
	 * @return
	 */
	public FoodCategory addFoodCategory(Integer pId, String name, Integer userId, Boolean publicCategoryCopy) {
		FoodCategory foodCategory = new FoodCategory();
		foodCategory.setPid(pId);
		foodCategory.setName(name);
		foodCategory.setState(1);
		foodCategory.setCreateBy(userId);
		// 查询本级code最大值
		Integer maxCode = foodCategoryWriteDao.queryCodeMaxByPId(pId);
		if(maxCode == null) {
			maxCode = 1;
		}else{
			maxCode = maxCode + 1;
		}
		// 查询父级codeValue值
		String codeValue = foodCategoryWriteDao.queryParentCodeValueById(pId);
		foodCategory.setCodeValue(codeValue+"_"+maxCode);
		foodCategory.setCode(maxCode);
		foodCategoryWriteDao.insert(foodCategory);
		// 拷贝父类公共分类信息
    	if(publicCategoryCopy) {
    		// 根据食物分类id查询食物分类公共分类
    		List<FoodPublicCategory> list = foodCategoryWriteDao.getPublicCategoryListByfoodCategoryId(pId);
    		for(FoodPublicCategory foodPublicCategory : list) {
    			foodPublicCategory.setId(null);
    			foodPublicCategory.setFoodCategoryId(foodCategory.getId());
    			foodCategoryWriteDao.insertPublicFoodCategory(foodPublicCategory);
    		}
    		// 遍历公共分类，修改分类父id
    		for(int i = 0; i < list.size(); i++) {
    			for(int j = 0; j < list.size(); j++) {
    				if(list.get(i).getPid() == list.get(j).getOldId()) {
    					list.get(i).setpId(list.get(j).getId());
    					foodCategoryWriteDao.updatePublicFoodCategory(list.get(i));
    				}
    			}
    		}
    	}
		return foodCategory; 
	}
	
	/**
	 * 保存食物分类
	 * @param pId 食物分类父id
	 * @param name 食物分类名称
	 * @return
	 */
	public FoodCategory updateFoodCategory(Integer id, String name, Integer userId) {
		FoodCategory foodCategory = new FoodCategory();
		foodCategory.setId(id);
		foodCategory.setName(name);
		foodCategory.setState(1);
		foodCategory.setUpdateBy(userId);
		foodCategoryWriteDao.update(foodCategory);
		return foodCategory; 
	}
	
	/**
	 * 删除食物分类
	 * @param id 食物分类id
	 * @return
	 */
	public Boolean deleteFoodCategory(Integer id, Integer userId) {
		FoodCategory foodCategory = new FoodCategory();
		foodCategory.setId(id);
		foodCategory.setUpdateBy(userId);
		foodCategory.setState(2);
		int rel = foodCategoryWriteDao.update(foodCategory);//foodCategoryWriteDao.delete(id);
		return rel > 0 ? true : false;
	}
	
	/**
	 * 验证分类是否有子分类
	 * @param id
	 * @return
	 */
	public Boolean validateChild(Integer id) {
		return foodCategoryWriteDao.isSubNodeById(id)>0 ? true : false;
	}
	
	/**
	 * 根据食物分类id查询食物公共分类信息
	 * @param foodCategoryId
	 * @return
	 */
	public List<Map<String, Object>> getPublicCategoryListByFoodCategoryId(Integer foodCategoryId, Integer pid, Integer id) {
		List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();
		if(foodCategoryId == null) {
			return nodes;
		}
		Map<String, Object> node = new HashMap<String, Object>();
		if(pid == null) {
			node.put("id", 1);
			node.put("name", "公共分类");
			node.put("isParent", true);
			node.put("open", true);
		}else{
			node.put("id", id);
		}
		
		buildChildNodes(node, foodCategoryId); 
		if(node.get("children") == null) {
			node.put("isParent", false);
			node.put("open", false);
		}
		nodes.add(node);
		return nodes;
	}
	
	
	  // 递归子节点
    public void buildChildNodes(Map<String, Object> node, Integer foodCategoryId) {
    	List<Map<String, Object>> children = getChildNodes(node, foodCategoryId);
        if (!children.isEmpty()) {
            for (Map<String, Object> child : children) {
                buildChildNodes(child, foodCategoryId);
            }
            node.put("children", children);
        }
    }

    // 获取父节点下所有的子节点
    public List<Map<String, Object>> getChildNodes(Map<String, Object> pnode, Integer foodCategoryId) {
        List<FoodPublicCategory> nodes = foodCategoryWriteDao.getPublicCategoryList(foodCategoryId, (Integer)pnode.get("id"));
        List<Map<String, Object>> childNodes = new ArrayList<Map<String, Object>>();
        
        if(nodes != null) {
        	for (FoodPublicCategory n : nodes) {
        		if (n.getPid().equals((Integer)pnode.get("id"))) {
        			Map<String, Object> node = new HashMap<String, Object>();
        			node.put("id", n.getId());
        			node.put("foodCategoryId", foodCategoryId);
        			node.put("name", n.getName());
        			node.put("open", true);
        			node.put("isParent", foodCategoryWriteDao.isPublicSubNodeById(n.getId()) > 0 ? true : false);
        			childNodes.add(node);
        		}
        	}
        }
        return childNodes;
    }
    
    /**
     * 根据食物分类id查询食物公共分类信息
     * @param foodCategoryId
     * @return
     */
    public List<Map<String, Object>> getPublicCategoryCheckListByFoodCategoryId(Integer foodId, Integer foodCategoryId, Integer pid, Integer id) {
    	List<Map<String, Object>> nodes = new ArrayList<Map<String, Object>>();
    	if(foodCategoryId == null) {
    		return nodes;
    	}
    	Map<String, Object> node = new HashMap<String, Object>();
    	if(pid == null) {
    		node.put("id", 1);
    		node.put("name", "公共分类");
    		node.put("isParent", true);
    		node.put("checked", false);
    		node.put("test", 1);
    		node.put("open", true);
    	}else{
    		node.put("id", id);
    	}
    	
    	buildChildCheckNodes(node, foodCategoryId, foodId); 
    	if(node.get("children") == null) {
			node.put("isParent", false);
			node.put("open", false);
		}
    	nodes.add(node);
    	return nodes;
    }
    
    
    // 递归子节点
    public void buildChildCheckNodes(Map<String, Object> node, Integer foodCategoryId, Integer foodId) {
    	List<Map<String, Object>> children = getChildCheckNodes(node, foodCategoryId, foodId);
    	if (!children.isEmpty()) {
    		for (Map<String, Object> child : children) {
    			buildChildCheckNodes(child, foodCategoryId, foodId);
    		}
    		node.put("children", children);
    	}
    }
    
    // 获取父节点下所有的子节点
    public List<Map<String, Object>> getChildCheckNodes(Map<String, Object> pnode, Integer foodCategoryId, Integer foodId) {
    	List<FoodPublicCategory> nodes = foodCategoryWriteDao.getPublicCategoryList(foodCategoryId, (Integer)pnode.get("id"));//foodCategoryWriteDao.getPublicCategoryCheckList(foodId, (Integer)pnode.get("id"));
    	List<Map<String, Object>> childNodes = new ArrayList<Map<String, Object>>();
    	
    	if(nodes != null) {
    		for (FoodPublicCategory n : nodes) {
    			if (n.getPid().equals((Integer)pnode.get("id"))) {
    				Map<String, Object> node = new HashMap<String, Object>();
    				node.put("id", n.getId());
    				node.put("foodCategoryId", foodCategoryId);
    				node.put("name", n.getName());
    				node.put("test", 1);
    				node.put("checked", true);
    				node.put("open", true);
    				//node.put("isParent", foodCategoryWriteDao.isPublicSubNodeCheckById(n.getId()) > 0 ? true : false);
    				node.put("isParent", foodCategoryWriteDao.isPublicSubNodeById(n.getId()) > 0 ? true : false);
    				childNodes.add(node);
    			}
    		}
    	}
    	return childNodes;
    }
    
    /**
     * 保存公共分类
     * @param pId 分类父id
     * @param name 分类名称
     * @return
     */ 
    public FoodPublicCategory addPublicFoodCategory(Integer pId, String name, Integer userId, Integer foodCategoryId) {
    	FoodPublicCategory foodPublicCategory = new FoodPublicCategory();
    	foodPublicCategory.setpId(pId);
    	foodPublicCategory.setName(name);
    	foodPublicCategory.setState(1);
    	foodPublicCategory.setCreateBy(userId);
    	foodPublicCategory.setFoodCategoryId(foodCategoryId);
    	foodCategoryWriteDao.insertPublicFoodCategory(foodPublicCategory);
    	return foodPublicCategory; 
    }
    
    /**
	 * 保存公共分类
	 * @param pId 分类父id
	 * @param name 分类名称
	 * @return
	 */
	public FoodPublicCategory updatePublicFoodCategory(Integer id, String name, Integer userId) {
		FoodPublicCategory foodPublicCategory = new FoodPublicCategory();
		foodPublicCategory.setId(id);
		foodPublicCategory.setName(name);
		foodPublicCategory.setState(1);
		foodPublicCategory.setUpdateBy(userId);
		foodCategoryWriteDao.updatePublicFoodCategory(foodPublicCategory);
		return foodPublicCategory; 
	}
	
	/**
	 * 删除公共分类
	 * @param id 食物分类id
	 * @return
	 */
	public Map<String, String> deletePublicFoodCategory(Integer id, Integer userId) {
		Map<String, String> map = new HashMap<String, String>();
		// 查询公共分类是否被引用
		int count = foodCategoryWriteDao.queryFoodPublicCategoryRel(id);
		if(count > 0) {
			map.put("success", "false");
			map.put("msg", "公共分类在食物中被引用，不能删除");
			return map;
		}
		
		FoodPublicCategory foodPublicCategory = new FoodPublicCategory();
		foodPublicCategory.setId(id);
		foodPublicCategory.setUpdateBy(userId);
		foodPublicCategory.setState(2);
		int rel = foodCategoryWriteDao.updatePublicFoodCategory(foodPublicCategory);//foodCategoryWriteDao.delete(id);
		if(rel > 0) {
			map.put("success", "true");
			map.put("msg", "删除成功");
		}
		return map;
	}
	
	/**
	 * 验证公共分类是否有子分类
	 * @param id
	 * @return
	 */
	public Boolean validatePublicChild(Integer id) {
		return foodCategoryWriteDao.isSubNodePublicById(id)>0 ? true : false;
	}
	
	/**
	 * 生成新的食物分类名称
	 * @param oldName
	 * @return
	 */
	public String newFoodCategoryName(String oldName) {
		// 存储过滤后的值
		List<Integer> list = new ArrayList<Integer>();
		String name = "";
		// 根据食物类型名称查询包含oldName数据信息
		//{"水果(3)","水果(10)","水果(2)","水果(测试)"};
		List<String> names = new ArrayList<String>();
		
		names = foodCategoryWriteDao.getFoodCategoryListByName(oldName);
		names.remove(oldName);
		/*if(names != null && names.size() == 1 && oldName.equals(names.get(0))) {
			return oldName+"(1)";
		}*/
		
		// 从查询的食物分类名称中查找包含“（）”，并且“（）”中为数字的，将所有数字保存到list中
		for(int i=0; i<names.size(); i++) {
			if(names.get(i).lastIndexOf("(") > 0 && names.get(i).lastIndexOf(")") > 0) {
				String str = names.get(i).substring(names.get(i).lastIndexOf("(")+1).split("\\)")[0];
				if(isNumeric(str)) {
					list.add(Integer.parseInt(str));
				}
			}else{
				name = oldName+"(1)";
			}
		}
		if(list.size() > 0) {
			// 排序，从数字中查找最大值
			Collections.sort(list);
			name = oldName+"("+(list.get(list.size()-1).intValue()+1)+")";
		}else{
			// 如果list中没有添加数据，需要从新生成食物分类名称
			name = oldName+"(1)";
		}
		return name;
	}
	
	/**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
           Pattern pattern = Pattern.compile("[0-9]*");
           Matcher isNum = pattern.matcher(str);
           if( !isNum.matches() ){
               return false;
           }
           return true;
    }
	
	/**
	 * 复制食物分类
	 * @param id
	 * @param userId
	 * @return
	 */
	public FoodCategory copyFoodCategory(Integer id, Integer userId) {
		List<FoodCategory> foodCategoryList = foodCategoryWriteDao.getFoodCategoryList(id);
		Integer newPId = null;
		Integer oldPId = null;
		FoodCategory newFoodCategory = null;
		// 
		for(FoodCategory foodCategory : foodCategoryList) {
			foodCategory.setId(null);
			foodCategory.setCreateBy(userId);
			foodCategory.setCreateDate(new Date());
			
			oldPId = foodCategory.getOldId();
			if(id == oldPId || id.equals(oldPId)) {
				// 查询本级code最大值
				Integer maxCode = foodCategoryWriteDao.queryCodeMaxByPId(foodCategory.getPid());
				if(maxCode == null) {
					maxCode = 1;
				}else{
					maxCode = maxCode + 1;
				}
				// 查询父级codeValue值
				String codeValue = foodCategoryWriteDao.queryParentCodeValueById(foodCategory.getPid());
				foodCategory.setCodeValue(codeValue+"_"+maxCode);
				foodCategory.setCode(maxCode);
				foodCategory.setName(newFoodCategoryName(foodCategory.getName()));
				foodCategoryWriteDao.insert(foodCategory);
				newPId = foodCategory.getId();
				newFoodCategory = foodCategory;
			}
			//foodCategoryWriteDao.insert(foodCategory);
			// 
			/*if(id == oldPId || id.equals(oldPId)) {
				newPId = foodCategory.getId();
				newFoodCategory = foodCategory;
			}*/
		}
		
		/*for(int i = 0; i < foodCategoryList.size(); i++) {
			for(int j = 0; j < foodCategoryList.size(); j++) {
				if(foodCategoryList.get(i).getPid() == foodCategoryList.get(j).getOldId() || foodCategoryList.get(i).getPid().equals(foodCategoryList.get(j).getOldId())) {
					foodCategoryList.get(i).setPid(foodCategoryList.get(j).getId());
					foodCategoryList.get(i).setUpdateBy(userId);
					foodCategoryWriteDao.update(foodCategoryList.get(i));
				}
			}
		}*/
		
		// 根据食物分类id查询食物分类公共分类
		List<FoodPublicCategory> list = foodCategoryWriteDao.getPublicCategoryListByfoodCategoryId(id);
		for(FoodPublicCategory foodPublicCategory : list) {
			foodPublicCategory.setId(null);
			foodPublicCategory.setFoodCategoryId(newPId);
			foodPublicCategory.setCreateBy(userId);
			foodPublicCategory.setCreateDate(new Date());
			foodCategoryWriteDao.insertPublicFoodCategory(foodPublicCategory);
		}
		// 遍历公共分类，修改分类父id
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.size(); j++) {
				if(list.get(i).getPid() == list.get(j).getOldId() || list.get(i).getPid().equals(list.get(j).getOldId())) {
					list.get(i).setpId(list.get(j).getId());
					list.get(i).setUpdateBy(userId);
					foodCategoryWriteDao.updatePublicFoodCategory(list.get(i));
				}
			}
		}
		return newFoodCategory;
	}
	
	/**
	 * 验证食物分类名称是否存在
	 * @param name
	 * @return
	 */
	public Integer validataCategoryName(String name) {
		return foodCategoryWriteDao.validataCategoryName(name);
	}
}
