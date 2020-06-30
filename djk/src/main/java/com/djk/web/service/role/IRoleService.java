package com.djk.web.service.role;

import java.util.List;
import java.util.Map;

import com.djk.web.entity.role.SysRole;

/**
 * 
 * @author 李连超
 *
 */
public interface IRoleService {

	/**
	 * 查询角色信息
	 * @param queryMap
	 * @param start 
	 * @param size
	 * @return
	 */
	public List<SysRole> getSysRoleList(Map<String, String> queryMap, Integer start, Integer size);
	
	/**
	 * 角色总记录数
	 * @param queryMap
	 * @return
	 */
	public Integer getSysRoleListCount(Map<String, String> queryMap);
	
	/**
	 * 保存角色
	 * @param sysRole
	 */
	public void insert(SysRole sysRole);
	
	/**
	 * 更新角色
	 * @param sysRole
	 * @return
	 */
	public Integer update(SysRole sysRole);
	
	/**
	 * 删除角色
	 * @param id
	 */
	public void deleteById(Integer id);
}
