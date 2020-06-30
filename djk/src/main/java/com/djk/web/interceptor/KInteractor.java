package com.djk.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
 
 
 
public class KInteractor implements HandlerInterceptor{
 
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	//会在action被调用之前执行   返回true通过  返回false不通过
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		//获取请求中的myToken的随机值
		String m=request.getParameter("myToken");
		
		//获取第一次访问页面时在session中设的值 （在自定义标签中做的操作）
		Object m1=request.getSession().getAttribute("myToken");
		//当m不为空   说明表单提交   需要判断是否是重复提交
		if(m!=null){
			//当m1不为空  session中有值   表单不是重复提交
			if(m1!=null){
				//为了防止表单参数被篡改   需要判断隐藏表单和session中的值是否相等
				if(m.equals(m1)){
					//把session中的唯一表示符清除
					request.getSession().removeAttribute("myToken");
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
}