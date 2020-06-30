<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<div id="sidebar">
<c:if test="${menu == 'system'}">
  		<ul class="Menu">
            <li class="Open">
            	<a href="javascript:void(0);" class="Off">科室管理</a>
                <ul class="SubMenu">
                	<li><a href="/department/departmentCatalogList"><i></i>科室目录</a></li>
                	<li><a href="系统管理_科室管理_科室维护.html"><i></i>科室维护</a></li>
                	<li><a href="系统管理_科室管理_科室列表.html" class="now"><i></i>科室列表</a></li>
            	</ul>
            </li>
            <li>
            	<a href="javascript:void(0);" class="Off">用户管理</a>
                <ul class="SubMenu">
                	<li><a href="/user/list"><i></i>用户列表</a></li>
                	<li><a href="/user/maintain"><i></i>用户维护</a></li>
            	</ul>
            </li>
            <li>
            	<a href="/sys/role/list">系统权限</a>
            </li>
            <li>
            	<a href="系统管理_系统日志.html" class="">系统日志</a>
            </li>
            <li>
            	<a href="/sys/dictionary/list?type=business">数据字典</a>
            </li>
            <li>
            	<a href="系统管理_药品调价管理.html">药品调价管理</a>
            </li>
        </ul>
       
</c:if>
<c:if test="${menu == 'base'}">
		<ul class="Menu">
           <li class="Open">
           	<a href="javascript:void(0);" class="Off">收费项目管理</a>
               <ul class="SubMenu">
               	<li><a href="${pageContext.request.contextPath}/charge/chargeCatalogList"><i></i>收费分类</a></li>
               	<li><a href="${pageContext.request.contextPath}/charge/chargeitemmaintain"><i></i>收费项目维护</a></li>
               	<li><a href="${pageContext.request.contextPath}/charge/chargeitemlist"><i></i>收费项目列表</a></li>
           	</ul>
           </li>
           <li>
           	<a href="javascript:void(0);" class="Off">诊疗项目管理</a>
               <ul class="SubMenu">
               	<li><a href="/base/cure/classify/list"><i></i>诊疗分类</a></li>
               	<li><a href="/base/cure/openAdd"><i></i>诊疗项目维护</a></li>
               	<li><a href="/base/cure/list"><i></i>诊疗项目收费关系列表</a></li>
           	</ul>
           </li>
           <li>
           	<a href="javascript:void(0);" class="Off">药品管理</a>
               <ul class="SubMenu">
               	<li><a href="/base/medicine/classify/list"><i></i>药品分类</a></li>
               	<li><a href="/base/medicine/openAdd"><i></i>药品维护</a></li>
               	<li><a href="/base/medicine/list"><i></i>药品列表</a></li>
                <li><a href="/base/medicine/saleList"><i></i>药品售价列表</a></li>
           	</ul>
           </li>
       </ul>
	</c:if>
	</div>