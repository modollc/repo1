package com.djk.web.controller.food;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.common.StringUtils;
import com.djk.web.entity.food.FoodOrigin;
import com.djk.web.entity.food.Tree;
import com.djk.web.service.food.FoodOriginService;
import com.djk.web.util.ResultMap;

@Controller
@RequestMapping("/food/foodOrigin/")
public class FoodOriginController {

	@Autowired
	private FoodOriginService foodOriginService;


	@RequestMapping(value = "index", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {

	
		return "food/origin/foodOrigin";
	}



	/**
	 * 
	 * @Title: list
	 * @Description: TODO 默认列表
	 * @param @param dataMap
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public List<Tree> list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {

		List<Tree> listAll = new ArrayList<Tree>();

		List<FoodOrigin> findList = foodOriginService.findList(new FoodOrigin());

		for(int i=0;i<findList.size();i++){ //一级
			FoodOrigin foodOrigin = findList.get(i);
			if(foodOrigin.getParentId()==0){
				Tree tree=new Tree();
				tree.setId(foodOrigin.getId());
				tree.setpId(foodOrigin.getParentId());
				tree.setName(foodOrigin.getOriginName());
				if(i==0){
					tree.setOpen(true);
				}
				listAll.add(tree);
				//二级
				for (FoodOrigin foodOrigin2 : findList) {
					if(foodOrigin2.getParentId().equals(tree.getId())){
						Tree tree2=new Tree();
						tree2.setId(foodOrigin2.getId());
						tree2.setpId(foodOrigin2.getParentId());
						tree2.setName(foodOrigin2.getOriginName());
						listAll.add(tree2);

						for (FoodOrigin foodOrigin3 : findList) {

							if(foodOrigin3.getParentId().equals(tree2.getId())){
								Tree tree3=new Tree();
								tree3.setId(foodOrigin3.getId());
								tree3.setpId(foodOrigin3.getParentId());
								tree3.setName(foodOrigin3.getOriginName());
								listAll.add(tree3);
								
								
								for (FoodOrigin foodOrigin4 : findList) {

									if(foodOrigin4.getParentId().equals(tree3.getId())){
										Tree tree4=new Tree();
										tree4.setId(foodOrigin4.getId());
										tree4.setpId(foodOrigin4.getParentId());
										tree4.setName(foodOrigin4.getOriginName());
										listAll.add(tree4);
									}
								}

								
								
							}
						}

					}

				}

			}
		}

		return listAll;
	}

	private int checkNameUnique(FoodOrigin foodOrigin)
    {
        int uniqueFlag = 0;
        if (foodOrigin != null)
        {
             Integer id = foodOrigin.getId();
             FoodOrigin info = foodOriginService.checkNameUnique(foodOrigin.getOriginName());
             if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId()) && info.getId() != id)
             {
            	 uniqueFlag=1;
             }
        }
        return uniqueFlag;
    }
	
	
	/**
	 * 添加
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	public ResultMap add(Map<String, Object> dataMap, HttpServletRequest request,FoodOrigin foodSupplier) throws Exception {
		ResultMap r = new ResultMap();
	   
		
		int result = checkNameUnique(foodSupplier);
		if(result>0){
			r.setCode("1");
			r.setMsg("产地已经存在!");
			return r;
		}
		
	    Integer insert = foodOriginService.insert(foodSupplier);
		if(insert>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}

	/**
	 * 修改
	 * @param dataMap
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "edit", method = { RequestMethod.POST })
	public ResultMap edit(Map<String, Object> dataMap, HttpServletRequest request,FoodOrigin foodSupplier) throws Exception {
		ResultMap r = new ResultMap();
		int result = checkNameUnique(foodSupplier);
		if(result>0){
			r.setCode("1");
			r.setMsg("产地已经存在!");
			return r;
		}
	    Integer insert = foodOriginService.update(foodSupplier);
		if(insert>0){
			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}



	/**
	 * 删除
	 * @param request
	 * @param foodPhyState
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap delete(HttpServletRequest request, FoodOrigin foodSupplier) {

		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSupplier.getId()!=null){

			result = foodOriginService.delete(foodSupplier.getId().toString());
			if(result.equals(3)){
				r.setCode("1");
				r.setMsg("产地已经被食物应用不能删除!");
				return r;
			}
		}
		if(result>0){

			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}


	/**
	 * 
	 * @Title: add
	 * @Description: TODO 新增户
	 * @param @param request
	 * @param @param sysuser
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMap save(HttpServletRequest request, FoodOrigin foodSupplier) {

		ResultMap r = new ResultMap();
		Integer result =0;
		if(foodSupplier.getId()!=null){
			foodSupplier.preUpdate();
			result = foodOriginService.update(foodSupplier);
		}else{
			foodSupplier.preInsert();
			result = foodOriginService.insert(foodSupplier);
		}
		if(result>0){

			r.setCode("0");
			r.setMsg("操作成功!");
		}
		return r;
	}
}
