package com.djk.web.service.user;

import java.util.List;
import java.util.Map;

import com.djk.web.entity.user.SysUser;
import com.djk.web.util.PagerInfo;
import com.djk.web.util.ServiceResult;

public interface ISysUserService {

	/**
	 * 根据id取得sys_user对象
	 * 
	 * @param sysUserId
	 * @return
	 */
	SysUser getSysUserById(Integer sysUserId);

	/**
	 * 保存sys_user对象
	 * 
	 * @param sysUser
	 * @return
	 */
	Boolean saveSysUser(SysUser sysUser, String[] roleIds);
	
	/**
	 * 更新sys_user对象
	 * 
	 * @param sysUser
	 * @return
	 */
	Boolean updateSysUser(SysUser sysUser);


	/**
	 * 更新sys_user对象
	 *  tjl
	 * @param sysUser
	 * @return
	 */
	Boolean updateSysUserAndRole(SysUser sysUser, String[] roleIds);

	/**
	 * 
	 * @Title: page
	 * @Description: TODO
	 * @param @param queryMap
	 * @param @param start
	 * @param @param size
	 * @param @return 设定文件
	 * @return List<SysUser> 返回类型
	 * @throws
	 */
	List<SysUser> page(Map<String, String> queryMap, PagerInfo page);

	/**
	 * 
	 * @Title: count
	 * @Description: TODO
	 * @param @param queryMap
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @throws
	 */
	Integer count(Map<String, String> queryMap);

	/**
	 * 
	 * @Title: del
	 * @Description: TODO 删除用户
	 * @param @param id
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	Boolean del(Integer id);

	/**
	 * 
	 * @Title: validHasUser
	 * @Description: TODO 插入数据前首先验证库中是否有该用户
	 * @param @param userName
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @throws
	 */
	Integer validHasUser(String userName);

	/**
	 * 根据用户登录名查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public ServiceResult<List<SysUser>> getSysUserByUserName(String userName);

	/**
	 * 更新密码
	 * 
	 * @param confirmInPassword
	 * @param userId
	 */
	public void updatePassword(String confirmInPassword, Integer userId);

	/**
	 * 根据用户ID查询用户和角色关系表 田金龙
	 * 
	 * @param userId
	 * @return
	 */
	String selectSysUserRoleByUserId(Integer userId);
}