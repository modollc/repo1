package com.djk.web.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.web.entity.role.SysRole;
import com.djk.web.entity.user.SysUser;
import com.djk.web.service.role.IRoleService;
import com.djk.web.service.user.ISysUserService;
import com.djk.web.util.Md5;
import com.djk.web.util.PagerInfo;
import com.djk.web.util.PaginationUtil;
import com.djk.web.util.RedisUtil;
import com.djk.web.util.WebUtil;

/**
 * @author zhangzl
 * @ClassName: SysUserController
 * @Description: TODO 系统用户
 * @author A18ccms a18ccms_gmail_com
 * @date 2017-11-15 上午10:05:49
 * 
 */
@Controller
@RequestMapping("/user/")
public class SysUserController {

	@Resource
	private ISysUserService sysUserService;

	@Resource
	private IRoleService roleService;
	
	@Resource
	private RedisUtil redis;
	
	@Resource  
    private RedisTemplate<String, SysRole> redisTemplate;
	@Resource  
	private RedisTemplate<String, List<SysRole>> redisTemplate2;

	/**
	 * 
	 * @Title: list
	 * @Description: TODO 默认列表
	 * @param @param dataMap
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	public String list(Map<String, Object> dataMap, HttpServletRequest request) throws Exception {
		Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		List<SysUser> userList = sysUserService.page(queryMap, pager);// 用户列表
		pager.setRowsCount(sysUserService.count(queryMap));
					
		// 数据源）
		
		/*for(SysUser user : userList) {
			redis.set(user.getId().toString(), user.getName());
			//redis.getRedisTemplate().setValueSerializer(user);
			redis.getRedisTemplate().opsForValue().set("user", user);
		}*/
		
		/*SysRole role = new SysRole();
		role.setId(1);
		role.setName("roleoo");
		SysRole role2 = new SysRole();
		role2.setId(12);
		role2.setName("roleoo2");
		List<SysRole> list = new ArrayList<SysRole>();
		list.add(role);
		list.add(role2);
		
		redisTemplate.opsForValue().set("role", role);
		redisTemplate2.opsForValue().set("roles", list);
		
		SysRole r = redisTemplate.opsForValue().get("role");
		redisTemplate.delete("role");
		SysRole rr = redisTemplate.opsForValue().get("role");
		List<SysRole> roles = redisTemplate2.opsForValue().get("roles");
		
		System.out.println(r.getName());
		
		String userName = (String) redis.get("231");
		//Set<Object> user = (Set<Object>) redis.hashKeys("user");
		RedisSerializer<SysUser> u = (RedisSerializer<SysUser>) redis.getRedisTemplate().getValueSerializer();
		SysUser user = (SysUser) redis.getRedisTemplate().opsForValue().get("user");
		System.out.println("============user=========="+userName);*/
		
		dataMap.put("list", userList);
		// 分页对象
		PaginationUtil pm = null;
		pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager.getPageIndex()), pager.getRowsCount(), pager.getUrl(), pager.getQueryString());
		dataMap.put("page", pm);
		return "user/userlist";
	}

	/**
	 * 
	 * @Title: del
	 * @Description: TODO 删除用户
	 * @param @param request
	 * @param @param id
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public Boolean del(HttpServletRequest request, @RequestParam("id") Integer id) {
		Boolean success = sysUserService.del(id);
		return success;
	}

	/**
	 * 
	 * @Title: itemEdit
	 * @Description: TODO 重置密码跳转页面
	 * @param @param dataMap
	 * @param @param id
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "resetPwd", method = { RequestMethod.GET })
	public String resetPwd(Map<String, Object> dataMap, Integer id) throws Exception {
		dataMap.put("userId", id);
		return "user/resetPwd";
	}

	/**
	 * 
	 * @Title: updatePwd
	 * @Description: TODO 重置密码(更新数据)
	 * @param @param request
	 * @param @param baseChargeCatalog
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public Boolean updatePwd(HttpServletRequest request, SysUser sysuser) {
		String s = Md5.getMd5String("djk@190418");
		sysuser.setPwd(s);
		Boolean success = sysUserService.updateSysUser(sysuser);
		return success;
	}

	/**
	 * 
	 * @Title: add
	 * @Description: TODO 用户维护(用户新增)
	 * @param @param dataMap
	 * @param @param id
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping(value = "maintain", method = { RequestMethod.GET })
	public String maintain(Map<String, Object> dataMap, Integer id) throws Exception {
		// 获取角色 tjl
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put("q_name", "");
		List<SysRole> roleList = roleService.getSysRoleList(queryMap, 0, 20);

		dataMap.put("roleList", roleList);

		return "user/useradd";
	}


	/**
	 * 
	 * @Title: add
	 * @Description: TODO 新增用户
	 * @param @param request
	 * @param @param sysuser
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public String add(HttpServletRequest request, SysUser sysuser) {
		// 插入数据前首先验证库中是否有该用户
		Integer hasUser = sysUserService.validHasUser(sysuser.getUserName());
		if (hasUser > 0) {
			return "hasUser";
		} else {
			String[] roleIds = request.getParameterValues("roleIds");
			sysuser.setPwd(Md5.getMd5String("123456"));
			Boolean success = sysUserService.saveSysUser(sysuser, roleIds);
			if (success) {
				return "true";
			}
			return "false";
		}
	}


	/**
	 * 
	 * @Title: update
	 * @Description: TODO 编辑用户
	 * @param @param request
	 * @param @param sysuser
	 * @param @return 设定文件
	 * @return Boolean 返回类型
	 * @throws
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(HttpServletRequest request, SysUser sysuser) {
		String[] roleIds = request.getParameterValues("roleIds");
		Boolean success = sysUserService.updateSysUserAndRole(sysuser, roleIds);
		if (success) {
			return "true";
		}
		return "false";
	}
}
