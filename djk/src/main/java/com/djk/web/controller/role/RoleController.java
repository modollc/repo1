package com.djk.web.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.web.entity.role.SysRole;
import com.djk.web.service.role.IRoleService;


/**
 * 角色controller
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "/sys/role/")
public class RoleController {

	@Resource
	private IRoleService roleService;
	
	/**
	 * 角色列表
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap) {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("q_name", "");
		List<SysRole> list = roleService.getSysRoleList(queryMap, 0, 20);
		int count = roleService.getSysRoleListCount(queryMap);
		dataMap.put("list", list);
		dataMap.put("count", count);
		return "role/rolelist";
	}

	/**
	 * 添加角色
	 * @param dataMap
	 * @param sysRole
	 * @return
	 */
	@RequestMapping(value = "add", method = { RequestMethod.POST })
	@ResponseBody
	public String add(Map<String, Object> dataMap, SysRole sysRole) {
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("q_name", sysRole.getName());
		int i = roleService.getSysRoleListCount(queryMap);
		//存在角色
		if (i > 0) {
			return "exist";
		} else {//不存在角色
			roleService.insert(sysRole);
			return "no_exist";
		}
	}

	/**
	 * 更新角色
	 * @param dataMap
	 * @param sysRole
	 * @return
	 */
	@RequestMapping(value = "update", method = { RequestMethod.POST })
	public @ResponseBody String update(Map<String, Object> dataMap, SysRole sysRole) {
		try{
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("q_name", sysRole.getName());
			int i = roleService.getSysRoleListCount(queryMap);
			if (i > 0) {
				return "exist";
			} else {
				roleService.update(sysRole);
				return "no_exist";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	/**
	 * 删除角色
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteById", method = { RequestMethod.POST })
	public @ResponseBody String deleteById(HttpServletRequest request, @RequestParam("id") Integer id) {
		try{
			//分配角色的不能进行删除,需校验
			
			roleService.deleteById(id);
			return "no_exist";
			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	/**
	 * 角色分配
	 * @param request
	 * @param id
	 * @param rolesName
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "role2Res", method = { RequestMethod.GET })
    public String role2Res(HttpServletRequest request, Integer id, String rolesName,
                           Map<String, Object> dataMap) {
        dataMap.put("id", id);
        dataMap.put("rolesName", rolesName);
        return "role/resWin";
    }
}
