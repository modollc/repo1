package com.djk.web.dao.role;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.djk.web.entity.role.SysRole;

/**
 * 角色dao
 * @author 李连超
 *
 */
@Repository
public interface RoleDaoWrite {

	/**
	 * 查询角色信息
	 * @param dataMap
	 * @param start 
	 * @param size
	 * @return
	 */
	public List<SysRole> getSysRoleList(@Param("queryMap") Map<String, String> queryMap, @Param("start") Integer start, @Param("size") Integer size);
	
	/**
	 * 角色总记录数
	 * @param queryMap
	 * @return
	 */
	public Integer getSysRoleListCount(@Param("queryMap") Map<String, String> queryMap);
	
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
