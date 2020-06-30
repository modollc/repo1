<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- tokenName值是选填项 -->
<%@attribute name="tokenName" required="false" %>
<%
	//产生一个随机数
	String ranStr=UUID.randomUUID().toString();
	//当tokenName值为空时使用myToken
	String key=(tokenName==null?"myToken":tokenName);
	//把随机数放入session中
	session.setAttribute(key,ranStr);
%>
<!-- 隐藏表单域 -->
<input type='hidden' name='<%=key %>' value='<%=ranStr %>'>