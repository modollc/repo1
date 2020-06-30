package com.djk.web.service.food;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;
import com.djk.common.PageFactory;
import com.djk.common.PageInfoBT;

import com.djk.web.dao.food.FoodCategoryWriteDao;
import com.djk.web.dao.food.FoodSupplierWriteDao;
import com.djk.web.dao.food.FoodWriteDao;

import com.djk.web.entity.food.Food;
import com.djk.web.entity.food.FoodNutritionComponent;
import com.djk.web.entity.food.FoodPublicCategory;
import com.djk.web.entity.food.FoodPublicCategoryCheck;
import com.djk.web.entity.food.FoodSeasonal;
import com.djk.web.entity.food.FoodSupplier;
import com.djk.web.entity.nutrition.NutritionComponent;
import com.djk.web.entity.systemResource.SystemWeight;
import com.djk.web.entity.user.SysUser;

@Service
@Transactional(readOnly=true)
public class FoodService {

	@Autowired
	private FoodWriteDao foodWriteDao;
	@Resource
	private FoodCategoryWriteDao foodCategoryWriteDao;
	
	/*  
	public Integer update(Food food){
	 return foodWriteDao.update(food);
	}
	
	public Integer insert(Food food){
		return foodWriteDao.insert(food);
	}
	 */
	
	/**
	 * 验证食物名称是否存在，存在返回true，不存在返回false
	 * @param name
	 * @return
	 */
	public Boolean validateFoodNameExist(String name) {
		int count = foodWriteDao.validateFoodNameExist(name);
		if(count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 查询食物营养组分中没有的营养组分
	 * @param id
	 * @return
	 */
	public List<NutritionComponent> getNutritionComponentList(Integer id) {
		return foodWriteDao.getNutritionComponentList(id);
	}
	
	public List<SystemWeight> findSystemWeightList() {
		return foodWriteDao.findSystemWeightList();
	}
	
	/**
	 * 根据食物id查询食物营养组分
	 * @param foodId
	 * @return
	 */
	public List<FoodNutritionComponent> getFoodNutritionComponentList(Integer foodId) {
		return foodWriteDao.getFoodNutritionComponentList(foodId);
	}
	
	/**
	 * 根据食物id查询食物信息
	 * @param id
	 * @return
	 */
	public Food get(java.lang.Integer id){
		return foodWriteDao.get(id);
    }
	
	/**
	 * 根据id删除食物
	 * @param id
	 * @return
	 */
	public int delete(Integer id){
	  return foodWriteDao.delete(id);
	}

	/**
	 * 根据分类id查询分类下食物信息
	 * @param foodCategoryId
	 * @return
	 */
	public List<Food> foodListByGategoryId(Integer foodCategoryId) {
		return foodWriteDao.foodListByGategoryId(foodCategoryId);
	}
	
	/**
	 * 查询食品营养组分
	 * @return
	 */
	public List<NutritionComponent> getComponentList() {
		return foodWriteDao.getComponentList();
	}
	
	public static void main(String[] args) {
		System.out.println(001+1);
	}
	
	/**
	 * 添加食物
	 * @param request
	 * @param food
	 */
	public void foodCreate(HttpServletRequest request, Food food) {
		
		// 查询食物所在分类下食物code最大值
		Integer codeMax = foodWriteDao.queryCodeMax(food.getFirstCategoryId());
		if(codeMax == null) {
			codeMax = 1;
		}else{
			codeMax = codeMax+1;
		}
		String pattern="000";
		java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		String code = df.format(codeMax);
		// 查询食物分类codevalue
		// 查询父级codeValue值
		String codeValue = foodCategoryWriteDao.queryParentCodeValueById(food.getFirstCategoryId());
		food.setCode(code);
		food.setCodeValue(codeValue+"_"+code);
		Map<String, String[]> map = request.getParameterMap();
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		food.setState(1);
		foodWriteDao.insert(food);
		
		// 根据食物分类id查询公共分类
		// 根据食物分类id查询食物分类公共分类
		List<FoodPublicCategory> list = foodCategoryWriteDao.getPublicCategoryListByfoodCategoryId(food.getFirstCategoryId());
		List<FoodPublicCategoryCheck> listCheck = new ArrayList<FoodPublicCategoryCheck>();
		for(FoodPublicCategory foodPublicCategory : list) {
			FoodPublicCategoryCheck foodPublicCategoryCheck = new FoodPublicCategoryCheck();
			foodPublicCategoryCheck.setCreateBy(user.getId());
			foodPublicCategoryCheck.setFoodCategoryId(food.getFirstCategoryId());
			foodPublicCategoryCheck.setFoodId(food.getId());
			foodPublicCategoryCheck.setName(foodPublicCategory.getName());
			foodPublicCategoryCheck.setPid(foodPublicCategory.getPid());
			foodPublicCategoryCheck.setState(1);
			foodPublicCategoryCheck.setOldId(foodPublicCategory.getOldId());
			// 遍历获取单选框值
			for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
				Map.Entry element = (Map.Entry) iter.next();
				// key值
				Object strKey = element.getKey();
				// value,数组形式
				String[] value = (String[]) element.getValue();
				if(strKey.toString().contains("radio")) {
					System.out.print(strKey.toString() + "=");
					for (int i = 0; i < value.length; i++) {
						System.out.println(value[i]+","+foodPublicCategory.getId());
						if(foodPublicCategory.getId().equals(Integer.parseInt(value[i]))) {
							// 选中
							foodPublicCategoryCheck.setCheck(1);
						}
					}
				}
			}
			
			foodWriteDao.insertPublicFoodCategoryCheck(foodPublicCategoryCheck);
			listCheck.add(foodPublicCategoryCheck);
		}
		// 遍历公共分类，修改分类父id
		for(int i = 0; i < listCheck.size(); i++) {
			for(int j = 0; j < listCheck.size(); j++) {
				if(listCheck.get(i).getPid() == listCheck.get(j).getOldId() || listCheck.get(i).getPid().equals(listCheck.get(j).getOldId())) {
					
					listCheck.get(i).setPid(listCheck.get(j).getId());
					listCheck.get(i).setUpdateBy(user.getId());
					foodWriteDao.updatePublicFoodCategoryCheck(listCheck.get(i));
				}
			}
		}
		
		List<String> nutritionNames = food.getNutritionName();
		List<String> nutritionUnits = food.getNutritionUnit();
		List<String> nutritionValues = food.getNutritionValue();
		
		for(int i=0; i<nutritionNames.size(); i++) {
			FoodNutritionComponent foodNutritionComponent = new FoodNutritionComponent();
			foodNutritionComponent.setFoodId(food.getId());
			foodNutritionComponent.setState(1);
			foodNutritionComponent.setNutritionComponentName(nutritionNames.get(i));
			foodNutritionComponent.setNutritionComponentUnit(nutritionUnits.get(i));
			if(nutritionValues.get(i) != null && !"".equals(nutritionValues.get(i))) {
				foodNutritionComponent.setNutritionComponentNum(new BigDecimal(nutritionValues.get(i)));
			}
			foodWriteDao.insertFoodNutritionComponent(foodNutritionComponent);
		}
	}
	
	/**
	 * 更新食物
	 * @param request
	 * @param food
	 */
	public void foodUpdate(HttpServletRequest request, Food food) {
		Map<String, String[]> map = request.getParameterMap();
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		food.setState(1);
		foodWriteDao.update(food);
		
		
		
		// 根据食物分类id查询公共分类
		// 根据食物分类id查询食物分类公共分类
		List<FoodPublicCategoryCheck> listCheckOld = foodWriteDao.getPublicCategoryCheckList(food.getId(), food.getFirstCategoryId());
		// 删除食物公务分类
		foodWriteDao.deletePublicFoodCategoryCheck(food.getId());
		List<FoodPublicCategoryCheck> listCheck = new ArrayList<FoodPublicCategoryCheck>();
	/*	if(listCheckOld != null && listCheckOld.size() > 0) {
			
			for(FoodPublicCategoryCheck foodPublicCategory : listCheckOld) {
				FoodPublicCategoryCheck foodPublicCategoryCheck = new FoodPublicCategoryCheck();
				foodPublicCategoryCheck.setCreateBy(user.getId());
				foodPublicCategoryCheck.setFoodCategoryId(food.getFirstCategoryId());
				foodPublicCategoryCheck.setFoodId(food.getId());
				foodPublicCategoryCheck.setName(foodPublicCategory.getName());
				foodPublicCategoryCheck.setPid(foodPublicCategory.getPid());
				foodPublicCategoryCheck.setState(1);
				foodPublicCategoryCheck.setOldId(foodPublicCategory.getOldId());
				// 遍历获取单选框值
				for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
					Map.Entry element = (Map.Entry) iter.next();
					// key值
					Object strKey = element.getKey();
					// value,数组形式
					String[] value = (String[]) element.getValue();
					if(strKey.toString().contains("radio")) {
						for (int i = 0; i < value.length; i++) {
							System.out.println(value[i]+","+foodPublicCategory.getId());
							if(foodPublicCategory.getId().equals(Integer.parseInt(value[i]))) {
								// 选中
								foodPublicCategoryCheck.setCheck(1);
							}
						}
					}
				}
				
				foodWriteDao.insertPublicFoodCategoryCheck(foodPublicCategoryCheck);
				listCheck.add(foodPublicCategoryCheck);
			}
		}else{*/
			// 根据食物分类id查询公共分类
			// 根据食物分类id查询食物分类公共分类
			List<FoodPublicCategory> list = foodCategoryWriteDao.getPublicCategoryListByfoodCategoryId(food.getFirstCategoryId());
			for(FoodPublicCategory foodPublicCategory : list) {
				FoodPublicCategoryCheck foodPublicCategoryCheck = new FoodPublicCategoryCheck();
				foodPublicCategoryCheck.setCreateBy(user.getId());
				foodPublicCategoryCheck.setFoodCategoryId(food.getFirstCategoryId());
				foodPublicCategoryCheck.setFoodId(food.getId());
				foodPublicCategoryCheck.setName(foodPublicCategory.getName());
				foodPublicCategoryCheck.setPid(foodPublicCategory.getPid());
				foodPublicCategoryCheck.setState(1);
				foodPublicCategoryCheck.setOldId(foodPublicCategory.getOldId());
				// 遍历获取单选框值
				for (Iterator iter = map.entrySet().iterator(); iter.hasNext();) {
					Map.Entry element = (Map.Entry) iter.next();
					// key值
					Object strKey = element.getKey();
					// value,数组形式
					String[] value = (String[]) element.getValue();
					if(strKey.toString().contains("radio")) {
						System.out.print(strKey.toString() + "=");
						for (int i = 0; i < value.length; i++) {
							System.out.println(value[i]+","+foodPublicCategory.getId());
							if(foodPublicCategory.getId().equals(Integer.parseInt(value[i]))) {
								// 选中
								foodPublicCategoryCheck.setCheck(1);
							}
						}
					}
				}
				
				foodWriteDao.insertPublicFoodCategoryCheck(foodPublicCategoryCheck);
				listCheck.add(foodPublicCategoryCheck);
			}
		//}
		
		
		// 遍历公共分类，修改分类父id
		for(int i = 0; i < listCheck.size(); i++) {
			for(int j = 0; j < listCheck.size(); j++) {
				if(listCheck.get(i).getPid() == listCheck.get(j).getOldId() || listCheck.get(i).getPid().equals(listCheck.get(j).getOldId())) {
					
					listCheck.get(i).setPid(listCheck.get(j).getId());
					listCheck.get(i).setUpdateBy(user.getId());
					foodWriteDao.updatePublicFoodCategoryCheck(listCheck.get(i));
				}
			}
		}
		
		// 删除营养组分
		foodWriteDao.deleteFoodNutritionComponent(food.getId());
		
		List<String> nutritionNames = food.getNutritionName();
		List<String> nutritionUnits = food.getNutritionUnit();
		List<String> nutritionValues = food.getNutritionValue();
		// 遍历营养组分名称、单位、值并保存
		for(int i=0; i<nutritionNames.size(); i++) {
			FoodNutritionComponent foodNutritionComponent = new FoodNutritionComponent();
			foodNutritionComponent.setFoodId(food.getId());
			foodNutritionComponent.setState(1);
			foodNutritionComponent.setNutritionComponentName(nutritionNames.get(i));
			foodNutritionComponent.setNutritionComponentUnit(nutritionUnits.get(i));
			if(nutritionValues.get(i) != null && !"".equals(nutritionValues.get(i))) {
				foodNutritionComponent.setNutritionComponentNum(new BigDecimal(nutritionValues.get(i)));
			}
			foodWriteDao.insertFoodNutritionComponent(foodNutritionComponent);
		}
	}
	
	/**
	 * 复制食物
	 * @param request
	 * @param food
	 */
	@Transactional
	public void foodCopy(HttpServletRequest request, Integer foodId) {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		
		Food food = foodWriteDao.get(foodId);
		food.setId(null);
		food.setName(newFoodName(food.getName()));
		food.setState(1);
		// 查询食物所在分类下食物code最大值
		Integer codeMax = foodWriteDao.queryCodeMax(food.getFirstCategoryId());
		if(codeMax == null) {
			codeMax = 1;
		}else{
			codeMax = codeMax+1;
		}
		String pattern="000";
		java.text.DecimalFormat df = new java.text.DecimalFormat(pattern);
		String code = df.format(codeMax);
		// 查询食物分类codevalue
		// 查询父级codeValue值
		String codeValue = foodCategoryWriteDao.queryParentCodeValueById(food.getFirstCategoryId());
		food.setCode(code);
		food.setCodeValue(codeValue+"_"+code);
		
		foodWriteDao.insert(food);
		
		
		// 根据食物分类id查询公共分类
		// 根据食物分类id查询食物分类公共分类
		List<FoodPublicCategory> list = foodCategoryWriteDao.getPublicCategoryListByfoodCategoryId(food.getFirstCategoryId());
		List<FoodPublicCategoryCheck> listCheck = new ArrayList<FoodPublicCategoryCheck>();
		for(FoodPublicCategory foodPublicCategory : list) {
			FoodPublicCategoryCheck foodPublicCategoryCheck = new FoodPublicCategoryCheck();
			foodPublicCategoryCheck.setCreateBy(user.getId());
			foodPublicCategoryCheck.setFoodCategoryId(food.getFirstCategoryId());
			foodPublicCategoryCheck.setFoodId(food.getId());
			foodPublicCategoryCheck.setName(foodPublicCategory.getName());
			foodPublicCategoryCheck.setPid(foodPublicCategory.getPid());
			foodPublicCategoryCheck.setState(1);
			foodPublicCategoryCheck.setOldId(foodPublicCategory.getOldId());
			
			foodWriteDao.insertPublicFoodCategoryCheck(foodPublicCategoryCheck);
			listCheck.add(foodPublicCategoryCheck);
		}
		// 遍历公共分类，修改分类父id
		for(int i = 0; i < listCheck.size(); i++) {
			for(int j = 0; j < listCheck.size(); j++) {
				if(listCheck.get(i).getPid() == listCheck.get(j).getOldId() || listCheck.get(i).getPid().equals(listCheck.get(j).getOldId())) {
					
					listCheck.get(i).setPid(listCheck.get(j).getId());
					listCheck.get(i).setUpdateBy(user.getId());
					foodWriteDao.updatePublicFoodCategoryCheck(listCheck.get(i));
				}
			}
		}
		
		// 查询食物营养组分
		List<FoodNutritionComponent> foodNutritionComponents = foodWriteDao.getFoodNutritionComponentList(foodId);
		// 遍历营养组分名称、单位、值并保存
		for(int i=0; i<foodNutritionComponents.size(); i++) {
			FoodNutritionComponent foodNutritionComponent = foodNutritionComponents.get(i);
			foodNutritionComponent.setFoodId(food.getId());
			foodNutritionComponent.setId(null);
			foodWriteDao.insertFoodNutritionComponent(foodNutritionComponent);
		}
	}
	
	/**
	 * 根据foodid查询食物下公共分类信息
	 * @param foodId
	 * @return
	 */
	public List<Integer> findPublicCategoryCheck(Integer foodId) {
		return foodWriteDao.findPublicCategoryCheck(foodId); 
	}
	
	
	/**
	 * 分页查询数据
	 * @param entity
	 * @return   正常返回Page<T> 由于Bootstrap Table表格数据要求，所以返回PageInfoBT<T>
	 * 把service层的分页信息，封装为bootstrap table通用的分页封装
	 */
	public PageInfoBT<Food> findPage(Food entity){
		Page<Food> page = new PageFactory<Food>().defaultPage();
		entity.setPage(page);
		page.setTotal(foodWriteDao.count(entity));
		page.setRecords(foodWriteDao.findList(page,entity));
		return new PageInfoBT<Food>(page);
	}
	
	/**
	 * 生成新的食物名称
	 * @param oldName
	 * @return
	 */
	public String newFoodName(String oldName) {
		// 存储过滤后的值
		List<Integer> list = new ArrayList<Integer>();
		String name = "";
		// 根据食物类型名称查询包含oldName数据信息
		//{"水果(3)","水果(10)","水果(2)","水果(测试)"};
		List<String> names = new ArrayList<String>();
		
		names = foodWriteDao.getFoodListByName(oldName);
		names.remove(oldName);
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
	
}