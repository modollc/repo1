package com.djk.web.controller.resource;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.web.entity.resource.SystemRoles;
import com.djk.web.entity.user.SysUser;
import com.djk.web.service.resource.ISystemResourcesRolesService;
import com.djk.web.service.resource.ISystemRolesService;
import com.djk.web.util.BusinessException;
import com.djk.web.util.ConstantsEJS;
import com.djk.web.util.HttpJsonResult;
import com.djk.web.util.PagerInfo;
import com.djk.web.util.ServiceResult;
import com.djk.web.util.WebUtil;

/**
 * 角色管理controller
 *                       
 * @Filename: AdminRoleController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "/system/role")
public class AdminRoleController extends BaseController {
	@Resource(name = "systemRolesService")
    private ISystemRolesService          rolesService;
	@Resource(name = "systemResourcesRolesService")
    private ISystemResourcesRolesService resourcesRolesService;


    /**
     * 验证角色编码不重复
     * @param request
     * @param response
     * @param roleCode
     */
    @RequestMapping(value = "validateRole", method = { RequestMethod.POST })
    public void validateRole(HttpServletRequest request, HttpServletResponse response,
                             String roleCode) {
        response.setContentType("text/plain");
        boolean isValid = true;
        PrintWriter pw = null;

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("roleCode", roleCode);
            ServiceResult<List<SystemRoles>> serviceResult = rolesService.page(map, null);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            isValid = serviceResult.getResult().size() == 0;

            pw = response.getWriter();
        } catch (IOException e) {
            isValid = false;
            e.printStackTrace();
        }
        pw.print(isValid);
    }

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "admin/role/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SystemRoles>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SystemRoles>> serviceResult = rolesService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SystemRoles>> jsonResult = new HttpJsonResult<List<SystemRoles>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "role2Res", method = { RequestMethod.GET })
    public String role2Res(HttpServletRequest request, Integer id, String rolesName,
                           Map<String, Object> dataMap) {
        dataMap.put("id", id);
        dataMap.put("rolesName", rolesName);
        return "admin/role/resWin";
    }

    @RequestMapping(value = "save", method = { RequestMethod.POST })
    public void save(HttpServletRequest request, HttpServletResponse response, SystemRoles role) {
    	SysUser user = (SysUser) request.getSession().getAttribute("user");
        role.setUserId(user.getId());
        role.setStatus(ConstantsEJS.SYSTEM_ROLE_STATUS_1);
        ServiceResult<Integer> serviceResult = null;
        if (role.getId() != null && role.getId() != 0) {
            //编辑
            role.setUpdateTime(new Date());
            serviceResult = rolesService.updateSystemRoles(role);
        } else {
            //新增
            serviceResult = rolesService.saveSystemRoles(role);
        }
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.print(serviceResult.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存角色资源
     * @param request
     * @param response
     * @param roleId
     * @param resIds
     */
    @RequestMapping(value = "saveRoleRes", method = { RequestMethod.POST })
    public void saveRoleRes(HttpServletRequest request, HttpServletResponse response, String roleId,
                            String resIds) {
        response.setContentType("text/html;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;

        String[] resArr = resIds.split(",");
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            pw = response.getWriter();

            serviceResult = resourcesRolesService.save(roleId, resArr);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            msg = serviceResult.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
        ServiceResult<Boolean> serviceResult = rolesService.del(id);

        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.print(serviceResult.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
