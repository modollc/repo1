package com.djk.web.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.djk.web.entity.user.SysUser;


@Repository
public interface SysUserWriteDao {
 
	SysUser get(java.lang.Integer id);
	
	Integer insert(SysUser sysUser);
	
	Integer update(SysUser sysUser);
	
	List<SysUser> page(@Param("queryMap") Map<String, String> queryMap,@Param("start") Integer start, @Param("size") Integer size);

	Integer count(@Param("queryMap") Map<String, String> queryMap);
	
	Integer del(java.lang.Integer id);
	
	Integer validHasUser(java.lang.String userName);
	
	/**
	 * 根据用户登录名查询用户信息
	 * @param userName
	 * @return
	 */
	List<SysUser> getSysUserByUserName(@Param("userName") String userName);
	
	/**
 	 * 更新密码
 	 * @param confirmInPassword
 	 * @param userId
 	 */
 	public void updatePassword(SysUser sysUser); 
 	
 	
 	/**
 	 * 插入用户和角色关系表 田金龙
 	 * @param map
 	 * @return
 	 */
 	Integer insertSysUserRole(Map<String,Object> map);
 	

 	/**
 	 * 根据用户ID删除用户和角色关系表 田金龙
 	 * @param userId
 	 * @return
 	 */
 	Integer deleteSysUserRoleByUserId(Integer userId);
 	
 	/**
 	 * 根据用户ID查询用户和角色关系表 田金龙
 	 * @param userId
 	 * @return
 	 */
 	List<Map<String,Object>> selectSysUserRoleByUserId(Integer userId);
}