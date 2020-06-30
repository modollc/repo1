package com.djk.web.service.impl.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.djk.web.dao.resource.SystemResourcesWriteDao;
import com.djk.web.entity.resource.SystemResources;
import com.djk.web.service.resource.ISystemResourcesService;
import com.djk.web.util.BusinessException;
import com.djk.web.util.ConstantsEJS;
import com.djk.web.util.PagerInfo;
import com.djk.web.util.ServiceResult;

@Service(value = "systemResourcesService")
public class SystemResourcesServiceImpl implements ISystemResourcesService {

	@Resource
	private SystemResourcesWriteDao systemResourcesWriteDao;

	/**
	 * 根据id取得资源表
	 * 
	 * @param systemResourcesId
	 * @return
	 */
	@Override
	public ServiceResult<SystemResources> getSystemResourcesById(Integer systemResourcesId) {
		ServiceResult<SystemResources> result = new ServiceResult<SystemResources>();
		try {
			result.setResult(systemResourcesWriteDao.get(systemResourcesId));
		} catch (Exception e) {
			// log.error("根据id[" + systemResourcesId + "]取得资源表时出现未知异常：" + e);
			result.setSuccess(false);
			result.setMessage("根据id[" + systemResourcesId + "]取得资源表时出现未知异常");
		}
		return result;
	}

	/**
	 * 保存资源表
	 * 
	 * @param systemResources
	 * @return
	 */
	@Override
	public ServiceResult<Integer> saveSystemResources(SystemResources systemResources) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
		try {
			result.setResult(systemResourcesWriteDao.save(systemResources));
			result.setMessage("保存成功");
		} catch (Exception e) {
			// log.error("保存资源表时出现未知异常：" + e);
			result.setSuccess(false);
			result.setMessage("保存资源表时出现未知异常");
		}
		return result;
	}

	/**
	 * 更新资源表
	 * 
	 * @param systemResources
	 * @return
	 */
	@Override
	public ServiceResult<Integer> updateSystemResources(SystemResources systemResources) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
		try {
			result.setResult(systemResourcesWriteDao.update(systemResources));
			result.setMessage("更新成功");
		} catch (Exception e) {
			// log.error("更新资源表时出现未知异常：" + e);
			result.setSuccess(false);
			result.setMessage("更新资源表时出现未知异常");
		}
		return result;
	}

	@Override
	public ServiceResult<List<SystemResources>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<SystemResources>> serviceResult = new ServiceResult<List<SystemResources>>();
		try {
			Integer start = 0, size = 0;
			if (pager != null) {
				pager.setRowsCount(systemResourcesWriteDao.getCount(queryMap));
				start = pager.getStart();
				size = pager.getPageSize();
			}
			queryMap.put("start", start + "");
			queryMap.put("size", size + "");
			List<SystemResources> list = systemResourcesWriteDao.page(queryMap);
			serviceResult.setResult(list);
		} catch (BusinessException e) {
			serviceResult.setMessage(e.getMessage());
			serviceResult.setSuccess(Boolean.FALSE);
		} catch (Exception e) {
			e.printStackTrace();
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
			// log.error("[SystemResourcesServiceImpl][page] param1:" +
			// JSON.toJSONString(queryMap)
			// + " &param2:" + JSON.toJSONString(pager));
			// log.error("[SystemResourcesServiceImpl][page] exception:" +
			// e.getMessage());
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Boolean> del(Integer id) {

		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
		try {
			if (id == null)
				throw new BusinessException("删除资源表[" + id + "]时出错");
			result.setResult(systemResourcesWriteDao.del(id) > 0);
			result.setMessage("删除成功");
		} catch (Exception e) {
			// log.error("[SystemResourcesServiceImpl][del] exception:" +
			// e.getMessage());
			result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ServiceResult<List<SystemResources>> getByPid(Integer pid) {
		ServiceResult<List<SystemResources>> serviceResult = new ServiceResult<List<SystemResources>>();
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pid", pid);
			serviceResult.setResult(systemResourcesWriteDao.list(param));
		} catch (BusinessException e) {
			serviceResult.setMessage(e.getMessage());
			serviceResult.setSuccess(Boolean.FALSE);
		} catch (Exception e) {
			e.printStackTrace();
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
			// log.error("[SystemResourcesServiceImpl][getByPid] pid:" + pid);
			// log.error("[SystemResourcesServiceImpl][getByPid] exception:" +
			// e.getMessage());
		}
		return serviceResult;
	}

	/**
	 * 查询ztree的数据
	 * 
	 * @param queryMap
	 * @return
	 */
	@Override
	public ServiceResult<List<Map<String, Object>>> getZtreeList(Map<String, Object> queryMap) {
		ServiceResult<List<Map<String, Object>>> serviceResult = new ServiceResult<List<Map<String, Object>>>();
		try {
			serviceResult.setResult(systemResourcesWriteDao.getZtreeList(queryMap));
		} catch (BusinessException e) {
			serviceResult.setMessage(e.getMessage());
			serviceResult.setSuccess(Boolean.FALSE);
		} catch (Exception e) {
			e.printStackTrace();
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
			// log.error("[SystemResourcesServiceImpl][getByPid] pid:" + pid);
			// log.error("[SystemResourcesServiceImpl][getByPid] exception:" +
			// e.getMessage());
		}
		return serviceResult;
	}
}