package com.djk.web.service.resource;

import java.util.List;
import java.util.Map;

import com.djk.web.entity.resource.SystemResources;
import com.djk.web.util.PagerInfo;
import com.djk.web.util.ServiceResult;

/**
 * 系统资源管理
 *                       
 * @Filename: ISystemResourcesService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISystemResourcesService {

    /**
     * 根据id取得资源表
     * @param  systemResourcesId
     * @return
     */
    ServiceResult<SystemResources> getSystemResourcesById(Integer systemResourcesId);

    /**
     * 保存资源表
     * @param  systemResources
     * @return
     */
    ServiceResult<Integer> saveSystemResources(SystemResources systemResources);

    /**
    * 更新资源表
    * @param  systemResources
    * @return
    */
    ServiceResult<Integer> updateSystemResources(SystemResources systemResources);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SystemResources>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    ServiceResult<List<SystemResources>> getByPid(Integer pid);
    
    /**
	 * 查询ztree的数据
	 * 
	 * @param queryMap
	 * @return
	 */
    ServiceResult<List<Map<String, Object>>> getZtreeList(Map<String, Object> queryMap);
}