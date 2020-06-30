package com.djk.web.controller.login;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djk.common.SmsService;
import com.djk.common.StringPublic;
import com.djk.web.entity.log.SysLog;
import com.djk.web.entity.user.SysUser;
import com.djk.web.service.log.ILogService;
import com.djk.web.service.user.ISysUserService;
import com.djk.web.util.Md5;
import com.djk.web.util.RequestInfoUtil;
import com.djk.web.util.ServiceResult;

/**
 * 登录
 * @author 李连超
 *
 */
@Controller
public class LoginController {

	private static Logger  log = LogManager.getLogger(LoginController.class);

	@Resource
	private ISysUserService sysUserService;//用户service
	@Resource
	private ILogService logService;//日志service

	//密码验证消息提醒
	public enum PasswordValMsg {
		/**
		 * 1.用户输入原密码与原密码不一致
		 * 2.输入密码为空
		 * 3.输入密码为空
		 * 4.输入确认密码与密码不一致
		 * TRUE操作成功
		 * ERROR操作失败
		 */
		MSG0, MSG1, MSG2, MSG3, MSG4, ERROR, TRUE
	}

	//用户登录名称
	public static final String USER_NAME = "USERNAME";
	// 请求头key
	public final static String HEADER_KEY = "x-requested-with";
	// ajax 请求头包含字符串
	public final static String HEADER_STR = "XMLHttpRequest";


	/**
	 * 跳转test页面测试用
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public String test(Map<String, Object> dataMap) {

		return "test";
	}



	/**
	 * 跳转登录页面
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/", method = { RequestMethod.GET })
	public String index(Map<String, Object> dataMap) {
		Subject subject = SecurityUtils.getSubject();
		//从shiro中获取用户对象
		SysUser user = (SysUser) subject.getPrincipal();
		if(user != null) {
			//重定向
			return "index";
		}
		return "newlogin";
	}

	/**
	 * 跳转个人中心
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/updatehome", method = { RequestMethod.GET })
	public String updatehome(Map<String, Object> dataMap) {

		return "updatehome";
	}

	
	/**
	 * 跳转修改密码页
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/forGetPwd", method = { RequestMethod.GET })
	public String forGetPwd(Map<String, Object> dataMap) {

		return "forPwd";
	}
	/**
	 * 跳转修改密码页
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/forGetPwd1", method = { RequestMethod.GET })
	public String forGetPwd1(Map<String, Object> dataMap,HttpServletRequest request) {
		String userId = request.getParameter("userId");
		dataMap.put("userId", userId);
		return "forPwd1";
	}
	
	
	/**
	 * 跳转修改密码页
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/forGetPwd2", method = { RequestMethod.GET })
	public @ResponseBody Map<String,Object> forGetPwd2(Map<String, Object> dataMap,HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String pwd = request.getParameter("pwd");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//执行密码修改
		try {
			sysUserService.updatePassword(pwd, Integer.parseInt(userId));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("message", "修改失败");
			resultMap.put("status", "false");
			return resultMap;
		}
		
		
		
		resultMap.put("message", "修改成功");
		resultMap.put("status", "true");
		return resultMap;
	}
	/**
	 * 跳转修改密码页
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/forGetPwd3", method = { RequestMethod.GET })
	public String forGetPwd3(Map<String, Object> dataMap) {
		
		
		
		return "forPwd2";
	}

	/**
	 * 修改密码
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/forgetUser", method = { RequestMethod.POST })
	public @ResponseBody Map<String,Object> forgetUser(Map<String, Object> dataMap,HttpServletRequest request) {

		String userName = request.getParameter("userName");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//1.判断是否为空，为空直接返回提醒
		if(userName == null || "".equals(userName)) {
			resultMap.put("message", "用户名为空，请重新输入！");
			resultMap.put("status", "false");
			return resultMap;
		}
		ServiceResult<List<SysUser>> result = sysUserService.getSysUserByUserName(userName);
		if(result.getResult()==null){
			resultMap.put("message", "用户不存在，请重新输入！");
			resultMap.put("status", "false");
			return resultMap;
		}
		resultMap.put("message", result.getResult().get(0).getId());
		resultMap.put("status", "true");
		return resultMap;
	}
	
	
	
	/**
	 * 发送验证码
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/sendMsg", method = { RequestMethod.GET })
	public @ResponseBody Map<String,Object>  sendMsg(Map<String, Object> dataMap, HttpServletRequest request) {

		
		String phone = request.getParameter("phone");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//1.判断是否为空，为空直接返回提醒
		if(phone == null || "".equals(phone)) {
			resultMap.put("message", "手机号为空，请重新输入！");
			resultMap.put("status", "false");
			return resultMap;
		}
		
		SmsService ss = new SmsService();
		String name=StringPublic.MSG_USERNAME;   //短信服务商的用户名
		String pass=StringPublic.MSG_PASSWORD;  //短信服务商的密码

		String checkCode = "";
	    for(int i=0;i<6;i++){
	      checkCode+=(int)(Math.random()*10);
	    }
		String message= StringPublic.MSG_REG.replace("#msg#", checkCode);
		if(!"-12".equals(ss.sendSms(name, pass, phone, message, ""))){
			resultMap.put("message", checkCode);
			resultMap.put("status", "true");
			request.getSession().setAttribute("code", checkCode); 
			return resultMap;
		}

		return resultMap;

	}





	/**
	 * 主页
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/main", method = { RequestMethod.GET })
	public String main(Map<String, Object> dataMap, HttpServletRequest request) {

		Subject subject = SecurityUtils.getSubject();
		//从shiro中获取用户对象
		SysUser user = (SysUser) subject.getPrincipal();
		if(user == null) {

			subject.logout();

			HttpSession session = request.getSession();
			Enumeration<?> em = session.getAttributeNames();
			//清空session
			while (em.hasMoreElements()) {
				session.removeAttribute(em.nextElement().toString());
			}
			//清除cookie
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for (Cookie cookie : cookies) {
					cookie.setMaxAge(0);
				}
			}
			//重定向
			return "redirect:/";
		}
		//	return "main";




		return "index";
	}

	/**
	 * 登录
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public @ResponseBody Map<String,Object> login(String userName, String password, String phoneCode, HttpSession session, 
			String verifyCode, HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		//1.判断用户名是否为空，为空直接返回提醒
		if(userName == null || "".equals(userName)) {
			resultMap.put("message", "用户名为空，请重新输入！");
			resultMap.put("status", "false");
			return resultMap;
		}

		//1.1判断验证码
		/*String verify_number = (String) session.getAttribute("code");
		if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
			resultMap.put("message", "验证码不正确，请重新输入！");
			resultMap.put("status", "false");
			return resultMap;
		}*/

		

		//4.判断密码是否为空，为空返回提醒
		if(password == null || "".equals(password)) {
			resultMap.put("message", "密码为空，请重新输入！");
			resultMap.put("status", "false");
			return resultMap;
		}

	

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName,
				Md5.getMd5String(password).toCharArray());
		// token.setRememberMe(true);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			log.error("账号不存在：{}", e);
			resultMap.put("message", "账号不存在");
			resultMap.put("status", "false");
			return resultMap;
		} catch (DisabledAccountException e) {
			log.error("账号未启用：{}", e);
			resultMap.put("message", "账号未启用");
			resultMap.put("status", "false");
			return resultMap;
		} catch (IncorrectCredentialsException e) {
			log.error("密码错误：{}", e);
			resultMap.put("message", "账号或密码错误");
			resultMap.put("status", "false");
			return resultMap;
		} catch (RuntimeException e) {
			log.error("未知错误,请联系管理员：{}", e);
			resultMap.put("message", "未知错误,请联系管理员");
			resultMap.put("status", "false");
			return resultMap;
		}

		//保存登录日志
		SysLog sysLog = new SysLog();
		sysLog.setIpAddress(RequestInfoUtil.getHostIp(request));
		sysLog.setUserName(userName);
		logService.insert(sysLog);
		//从shiro中获取用户对象
		SysUser user = (SysUser) subject.getPrincipal();
		//用户信息存储session中
		session.setAttribute("user", user);

		return resultMap;
	}

	/**
	 * 登录
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/home.html", method = { RequestMethod.GET })
	public String home(Map<String, Object> dataMap) {
		dataMap.put("menu", "home");
		return "home";
	}

	/**
	 * 系统管理
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/system.html", method = { RequestMethod.GET })
	public String system(Map<String, Object> dataMap) {
		//dataMap.remove("menu");
		dataMap.put("menu", "system");
		return "system";
	}

	/**
	 * 基础维护
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "/base.html", method = { RequestMethod.GET })
	public String base(Map<String, Object> dataMap) {
		//dataMap.remove("menu");
		dataMap.put("menu", "base");
		return "base";
	}

	/**
	 * 验证原密码是否正确
	 * @方法名称: validatePassword
	 * @功能描述: 
	 * @作者:admin
	 * @创建时间:2016-10-17 上午9:20:10
	 * @param oldPassword 原密码
	 * @param inOldPassword 输入原密码
	 * @return ReturnMsg
	 */
	@RequestMapping(value="/validatePassword", method = { RequestMethod.POST })
	public @ResponseBody Map<String, Object> validatePassword(String oldPassword, String inOldPassword, 
			String inPassword, String confirmInPassword, HttpSession session, Integer userId, 
			HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Object sessionUser =  session.getAttribute("user");
		String header = request.getHeader(HEADER_KEY);
		if(sessionUser == null) {
			if (header != null && header.equalsIgnoreCase(HEADER_STR)){ 
				resultMap.put("message", String.valueOf(PasswordValMsg.MSG0.ordinal()));
				return resultMap;
			}
		}

		SysUser user = sysUserService.getSysUserById(userId);
		oldPassword = user.getPwd();

		//用md5加密用户输入原密码
		String md5Password = Md5.getMd5String(inOldPassword);
		//判断用户输入的原密码是否正确,不正确返回1
		//用户输入原密码为空时，返回1
		if(inOldPassword == null || "".equals(inOldPassword) || oldPassword == null || "".equals(oldPassword)) {
			resultMap.put("message", String.valueOf(PasswordValMsg.MSG1.ordinal()));
			return resultMap;
		}
		//输入原密码与密码不相等返回1
		if(!oldPassword.equals(md5Password)) {
			resultMap.put("message", String.valueOf(PasswordValMsg.MSG1.ordinal()));
			return resultMap;
		}
		//输入密码、确认密码不能为空
		//判断用户输入确认密码是否正确，不正确返回2
		if(confirmInPassword == null || "".equals(inPassword)) {
			resultMap.put("message", String.valueOf(PasswordValMsg.MSG2.ordinal()));
			return resultMap;
		}
		//判断用户输入密码是否正确，不正确返回3
		if(inPassword == null || "".equals(inPassword)) {
			resultMap.put("message", String.valueOf(PasswordValMsg.MSG3.ordinal()));
			return resultMap;
		}
		//判断输入密码与输入确认密码是否正确，不正确返回4
		if(!confirmInPassword.equals(inPassword)) {
			resultMap.put("message", String.valueOf(PasswordValMsg.MSG4.ordinal()));
			return resultMap;
		}

		try{
			//执行密码修改
			sysUserService.updatePassword(confirmInPassword, userId);
		}catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", String.valueOf(PasswordValMsg.ERROR.name()));
			return resultMap;
		}

		resultMap.put("message", String.valueOf(PasswordValMsg.TRUE.name()));
		return resultMap;
	}

	@RequestMapping(value = "/exit", method = { RequestMethod.GET })
	public String exit(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		HttpSession session = request.getSession();
		Enumeration<?> em = session.getAttributeNames();
		//清空session
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
		//清除cookie
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
		}
		//重定向
		return "redirect:/";
	}

}
