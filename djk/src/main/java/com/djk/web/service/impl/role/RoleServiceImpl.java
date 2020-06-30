package com.djk.web.service.impl.role;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.djk.web.dao.role.RoleDaoWrite;
import com.djk.web.entity.role.SysRole;
import com.djk.web.service.role.IRoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements IRoleService{

	@Resource
	private RoleDaoWrite roleDaoWrite;
	/**
	 * 查询角色信息
	 * @param dataMap
	 * @param start 
	 * @param size
	 * @return
	 */
	public List<SysRole> getSysRoleList(Map<String, String> queryMap, Integer start, Integer size) {
		return roleDaoWrite.getSysRoleList(queryMap, start, size);
	}
	
	/**
	 * 角色总记录数
	 * @param queryMap
	 * @return
	 */
	public Integer getSysRoleListCount(Map<String, String> queryMap) {
		return roleDaoWrite.getSysRoleListCount(queryMap);
	}
	
	/**
	 * 保存角色
	 * @param sysRole
	 */
	public void insert(SysRole sysRole) {
		roleDaoWrite.insert(sysRole);
	}
	
	/**
	 * 更新角色
	 * @param sysRole
	 * @return
	 */
	public Integer update(SysRole sysRole){
		return roleDaoWrite.update(sysRole);
	}
	
	/**
	 * 删除角色
	 * @param id
	 */
	public void deleteById(Integer id) {
		roleDaoWrite.deleteById(id);
	}
}
