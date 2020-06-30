package com.djk.web.service.impl.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.djk.web.dao.resource.SystemResourcesRolesWriteDao;
import com.djk.web.dao.user.SysUserWriteDao;
import com.djk.web.entity.resource.SystemResources;
import com.djk.web.entity.resource.SystemResourcesRoles;
import com.djk.web.service.resource.ISystemResourcesRolesService;
import com.djk.web.util.BusinessException;
import com.djk.web.util.ConstantsEJS;
import com.djk.web.util.PagerInfo;
import com.djk.web.util.ServiceResult;

@Service(value = "systemResourcesRolesService")
public class SystemResourcesRolesServiceImpl implements ISystemResourcesRolesService {

	@Resource
	private SystemResourcesRolesWriteDao systemResourcesRolesWriteDao;
	@Resource
    private DataSourceTransactionManager transactionManager;
	@Resource
	private SysUserWriteDao sysUserWriteDao;

	@Override
	public ServiceResult<SystemResourcesRoles> getSystemResourcesRolesById(Integer systemResourcesRolesId) {
		ServiceResult<SystemResourcesRoles> result = new ServiceResult<SystemResourcesRoles>();
		try {
			result.setResult(systemResourcesRolesWriteDao.get(systemResourcesRolesId));
		} catch (Exception e) {
			// log.error("根据id[" + systemResourcesRolesId + "]取得角色资源对应表时出现未知异常："
			// + e);
			result.setSuccess(false);
			result.setMessage("根据id[" + systemResourcesRolesId + "]取得角色资源对应表时出现未知异常");
		}
		return result;
	}

	@Override
	public ServiceResult<Integer> saveSystemResourcesRoles(SystemResourcesRoles systemResourcesRoles) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
		try {
			result.setResult(systemResourcesRolesWriteDao.save(systemResourcesRoles));
			result.setMessage("保存成功！");
		} catch (Exception e) {
			// log.error("保存角色资源对应表时出现未知异常：" + e);
			result.setSuccess(false);
			result.setMessage("保存角色资源对应表时出现未知异常");
		}
		return result;
	}

	@Override
	public ServiceResult<Integer> updateSystemResourcesRoles(SystemResourcesRoles systemResourcesRoles) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
		try {
			result.setResult(systemResourcesRolesWriteDao.update(systemResourcesRoles));
			result.setMessage("修改成功！");
		} catch (Exception e) {
			// log.error("更新角色资源对应表时出现未知异常：" + e);
			result.setSuccess(false);
			result.setMessage("更新角色资源对应表时出现未知异常");
		}
		return result;
	}

	@Override
	public ServiceResult<List<SystemResourcesRoles>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<SystemResourcesRoles>> serviceResult = new ServiceResult<List<SystemResourcesRoles>>();
		try {
			Integer start = 0, size = 0;
			if (pager != null) {
				pager.setRowsCount(systemResourcesRolesWriteDao.getCount(queryMap));
				start = pager.getStart();
				size = pager.getPageSize();
			}
			queryMap.put("start", start + "");
			queryMap.put("size", size + "");
			List<SystemResourcesRoles> list = systemResourcesRolesWriteDao.page(queryMap);
			serviceResult.setResult(list);
		} catch (BusinessException e) {
			serviceResult.setMessage(e.getMessage());
			serviceResult.setSuccess(Boolean.FALSE);
		} catch (Exception e) {
			e.printStackTrace();
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
			// log.error("[SystemResourcesRolesServiceImpl][page] param1:"
			// + JSON.toJSONString(queryMap) + " &param2:" +
			// JSON.toJSONString(pager));
			// log.error("[SystemResourcesRolesServiceImpl][page] exception:" +
			// e.getMessage());
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Boolean> del(Integer id) {

		ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
		try {
			if (id == null)
	            throw new BusinessException("删除角色资源对应表[" + id + "]时出错");
			sr.setResult(systemResourcesRolesWriteDao.del(id) > 0);
		} catch (Exception e) {
			// log.error("[SystemResourcesRolesServiceImpl][del] exception:" +
			// e.getMessage());
			e.printStackTrace();
		}
		return sr;
	}

	@Override
	public ServiceResult<Boolean> save(String roleId, String[] resArr) {
		ServiceResult<Boolean> serRes = new ServiceResult<Boolean>();
		try {

			serRes.setResult(this.saveSystemResourcesRoles(roleId, resArr));
			serRes.setMessage("保存成功。");
		} catch (Exception e) {
			serRes.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, e.getMessage());
			e.printStackTrace();
		}
		return serRes;
	}
	
	public boolean saveSystemResourcesRoles(String roleId, String[] resArr) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //删除此角色之前的资源关联
            systemResourcesRolesWriteDao.delByRole(roleId);

            //保存选中的资源
            for (String resId : resArr) {
                SystemResourcesRoles srr = new SystemResourcesRoles();
                srr.setResourcesId(Integer.valueOf(resId));
                srr.setRolesId(Integer.valueOf(roleId));
                systemResourcesRolesWriteDao.save(srr);
            }

            transactionManager.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(status);
            throw e;
        }
        return true;
    }

	@Override
	public ServiceResult<List<SystemResources>> getResourceByRoleId(Integer roleId, Integer scope) {
		ServiceResult<List<SystemResources>> serRes = new ServiceResult<List<SystemResources>>();
		try {
			if (roleId == null)
	            throw new BusinessException("未指定角色");
			serRes.setResult(systemResourcesRolesWriteDao.getResourceByRoleId(roleId, scope));
		} catch (Exception e) {
			serRes.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, e.getMessage());
			e.printStackTrace();
		}
		return serRes;
	}
	
	@Override
	public ServiceResult<List<SystemResources>> getResourceByUserId(Integer userId, Integer scope) {
		ServiceResult<List<SystemResources>> serRes = new ServiceResult<List<SystemResources>>();
		try {
			List<Map<String, Object>> list = sysUserWriteDao.selectSysUserRoleByUserId(userId);
			String roleIds = "";
			for (Map<String, Object> map : list) {
				roleIds += map.get("role_id") + ",";
			}
	        if ("".equals(roleIds))
	            throw new BusinessException("未指定角色");
			serRes.setResult(systemResourcesRolesWriteDao.getResourceByRoleIds("("+roleIds.substring(0,roleIds.length()-1)+")", scope));
		} catch (Exception e) {
			serRes.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, e.getMessage());
			e.printStackTrace();
		}
		return serRes;
	}

	@Override
	public List<SystemResources> getResourceByPid(Integer pid, Integer roleId, Integer scope) throws BusinessException {
		if (pid == null)
            throw new BusinessException("未指定父资源");
        if (roleId == null)
            throw new BusinessException("未指定角色");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pid", pid);
        map.put("roleId", roleId);
        map.put("scope", scope);
		return systemResourcesRolesWriteDao.getResourceByPid(map);
	}
}