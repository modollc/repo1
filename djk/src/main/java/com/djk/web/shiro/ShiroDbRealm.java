package com.djk.web.shiro;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.djk.web.entity.resource.SystemResources;
import com.djk.web.entity.user.SysUser;
import com.djk.web.service.resource.ISystemResourcesRolesService;
import com.djk.web.service.user.ISysUserService;
import com.djk.web.util.ServiceResult;
import com.djk.web.util.StringUtil;


/**
 * shiro权限realm
 * 
 * @Filename: ShiroDbRealm.java
 * @Version: 1.0
 * @Author: 李连超
 *
 */
public class ShiroDbRealm extends AuthorizingRealm {

    Logger                               log = Logger.getLogger(this.getClass());

    @Resource
    private ISysUserService sysUserService;;
    
    @Resource(name = "systemResourcesRolesService")
	private ISystemResourcesRolesService systemResourcesRolesService;
    //@Resource
    //private ISystemResourcesRolesService systemResourcesRolesService;

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        log.info("Shiro开始登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        ServiceResult<List<SysUser>> userResult = sysUserService.getSysUserByUserName(token.getUsername());
        if (!userResult.getSuccess()) {
            log.error(userResult.getMessage());
            return null;
        }
        List<SysUser> sysUserList = userResult.getResult();
        // 账号不存在
        if (sysUserList == null || sysUserList.size() == 0) {
            log.error("用户不存在，用户名：" + token.getUsername());
            throw new UnknownAccountException();
        }
        // 账号名重复
        if (sysUserList.size() > 1) {
            log.error("用户重复，用户名：" + token.getUsername());
            return null;
        }

        SysUser sysUser = sysUserList.get(0);

        // 账号未启用
       /* if (systemAdmin.getStatus() != 1) {
            log.error("用户账号已停用，用户名：" + token.getUsername());
            throw new DisabledAccountException();
        }*/
        // 认证缓存信息
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPwd().toCharArray(),
            getName());

    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        log.info("---------------------doGetAuthorizationInfo start" + new Date());
        SysUser systemAdmin = (SysUser) principals.getPrimaryPrincipal();

        ServiceResult<List<SystemResources>> resourcesResult = systemResourcesRolesService.getResourceByUserId(systemAdmin.getId(), SystemResources.SCOPE_ADMIN);

        if (!resourcesResult.getSuccess()) {
            log.error(resourcesResult.getMessage());
        }

        List<SystemResources> resourceList = resourcesResult.getResult();
        if (resourceList == null || resourceList.size() == 0) {
            log.error("该用户所属角色没有任何权限");
        }

        Set<String> urlSet = new HashSet<String>();
        if (resourceList != null && resourceList.size() > 0) {
            for (SystemResources resource : resourceList) {
                String url = resource.getUrl();
                if (!StringUtil.isEmpty(url)) {
                    // 用逗号分隔资源表的url字段，该字段可存储多个url，并用英文逗号（，）分隔
                    String[] split = url.split(",");
                    for (String urlSplit : split) {
                        // 如果url中带参数，则截去参数部分
                        int indexOf = urlSplit.indexOf("?");
                        if (indexOf != -1) {
                            urlSplit = urlSplit.substring(0, indexOf);
                        }
                        urlSet.add(urlSplit);
                    }
                }
            }
        }
    	 /*Set<String> urlSet = new HashSet<String>();
    	 urlSet.add("/sys/resource/addWin");
    	 urlSet.add("/sys/resource/save");
    	 urlSet.add("/sys/resource/resTree");
    	 urlSet.add("/sys/role/role2Res");
    	 urlSet.add("/base/medicine/openAdd");
    	 urlSet.add("/base/medicine/getCaigouList");
    	 
    	 
    	 urlSet.add("/sys/resource/roleResTree");
    	 urlSet.add("/system/role/saveRoleRes");
    	 urlSet.add("/user/add");
    	 urlSet.add("/user/maintain");
    	 urlSet.add("/user/copyUser");
    	 urlSet.add("/user/editUser");
    	 urlSet.add("/user/update");
    	 urlSet.add("/user/del");
    	 
    	
    	 urlSet.add("/sys/resource/index");
    	 urlSet.add("/sys/resource/list");
    	 urlSet.add("/user/maintain");
    	 urlSet.add("/user/list");
    	 urlSet.add("/sys/role/list");*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
        log.info("---------------------doGetAuthorizationInfo end" + new Date());

        return info;
    }
}
