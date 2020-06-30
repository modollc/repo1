<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld"%>
<%@include file="../../commons/head.jsp"%>
<jsp:include page="../../head.jsp" />




<style>
.navMenu-item:nth-child(4) .navMenu-title {
    background: #c38447;
    color: #fff;
}
.navMenu-item:nth-child(4) .navMenu-item-content li:nth-child(5) a{
    color: #622f00;
   font-weight: bold;
}
</style>
<div class="container increase_food" uk-grid>
<div class="mbxue uk-width-1-1">
			<ul class="uk-breadcrumb">
				<li>大健康管理系统</li>
				<li>食物营养公共资源</li>
				<li><span> 烹饪方式</span></li>
			</ul>
			<div class="btn_cz">
				<div class="operation_area">
					<input id="cateId" type="hidden" value="0"> <a
						class="uk-button uk-button-primary" onclick="javascript:add()">新增</a>
					<button class="uk-button uk-button-default"
						onclick="javascript:edit()">编辑</button>
					<button class="uk-button uk-button-default"
						onclick="javascript:batchRemove()">删除</button>
		
				</div>
			</div>
		</div>
	<div class="wrapper wrapper-content uk-width-1-1">
		
		<table class="bootstrap-table">
		</table>
	</div>
</div>
<script src="../../../static/main/js/ea-table.js?v=1.1.6" ></script>
<script src="../../../static/main/system/cooking/cooking.js"></script>	
<jsp:include page="../../foot.jsp" />



