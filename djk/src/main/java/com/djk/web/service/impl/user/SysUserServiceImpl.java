package com.djk.web.service.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djk.web.dao.user.SysUserWriteDao;
import com.djk.web.entity.user.SysUser;
import com.djk.web.service.user.ISysUserService;
import com.djk.web.util.Md5;
import com.djk.web.util.PagerInfo;
import com.djk.web.util.ServiceResult;

@Service(value = "sysUserService")
public class SysUserServiceImpl implements ISysUserService {

	private static Logger log = LogManager.getLogger(SysUserServiceImpl.class);

	@Resource
	private SysUserWriteDao sysUserWriteDao;

	@Override
	public SysUser getSysUserById(Integer sysUserId) {
		return sysUserWriteDao.get(sysUserId);
	}

	@Override
	@Transactional
	public Boolean saveSysUser(SysUser sysUser, String[] roleIds) {
		sysUserWriteDao.insert(sysUser);
		Map<String, Object> map = null;
		if(roleIds != null){
			for (int i = 0; i < roleIds.length; i++) {
				map = new HashMap<String, Object>();
				map.put("userId", sysUser.getId());
				map.put("roleId", roleIds[i]);
				sysUserWriteDao.insertSysUserRole(map);
			}
		}
		return true;
	}

	@Override
	public Boolean updateSysUser(SysUser sysUser) {
		return sysUserWriteDao.update(sysUser) > 0;
	}

	@Override
	@Transactional
	public Boolean updateSysUserAndRole(SysUser sysUser, String[] roleIds) {
		sysUserWriteDao.update(sysUser);
		sysUserWriteDao.deleteSysUserRoleByUserId(sysUser.getId());
		if(roleIds != null){
			Map<String, Object> map = null;
			for (int i = 0; i < roleIds.length; i++) {
				map = new HashMap<String, Object>();
				map.put("userId", sysUser.getId());
				map.put("roleId", roleIds[i]);
				sysUserWriteDao.insertSysUserRole(map);
			}
		}
		return true;
	}

	@Override
	public List<SysUser> page(Map<String, String> queryMap, PagerInfo page) {
		return sysUserWriteDao.page(queryMap, page.getStart(), page.getPageSize());
	}

	@Override
	public Integer count(Map<String, String> queryMap) {
		return sysUserWriteDao.count(queryMap);
	}

	@Override
	public Boolean del(Integer id) {
		return sysUserWriteDao.del(id) > 0;
	}

	@Override
	public Integer validHasUser(String userName) {
		return sysUserWriteDao.validHasUser(userName);
	}

	/**
	 * 根据用户登录名查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public ServiceResult<List<SysUser>> getSysUserByUserName(String userName) {
		ServiceResult<List<SysUser>> result = new ServiceResult<List<SysUser>>();
		try {
			result.setResult(sysUserWriteDao.getSysUserByUserName(userName));
		} catch (Exception e) {
			log.error("根据userName[" + userName + "]取得系统管理员表时出现未知异常：" + e);
			result.setSuccess(false);
			result.setMessage("根据userName[" + userName + "]取得系统管理员表时出现未知异常");
		}
		return result;
	}

	/**
	 * 更新密码
	 * 
	 * @param confirmInPassword
	 * @param userId
	 */
	public void updatePassword(String confirmInPassword, Integer userId) {
		SysUser sysUser = new SysUser();
		sysUser.setPwd(Md5.getMd5String(confirmInPassword));
		sysUser.setId(userId);
		sysUserWriteDao.updatePassword(sysUser);
	}

	@Override
	public String selectSysUserRoleByUserId(Integer userId) {
		List<Map<String, Object>> list = sysUserWriteDao.selectSysUserRoleByUserId(userId);
		String roleIds = "";
		for (Map<String, Object> map : list) {
			roleIds += map.get("role_id") + ",";
		}
		return roleIds;
	}
}