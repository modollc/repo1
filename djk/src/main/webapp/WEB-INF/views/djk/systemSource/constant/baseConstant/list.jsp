<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld"%>
<%@include file="../../../commons/head.jsp"%>
<jsp:include page="../../../head.jsp" />
<style>
.navMenu-item:nth-child(6) .navMenu-title {
     background: #c38447;
    color: #fff;
}
.navMenu-item:nth-child(6) .navMenu-item-content li:nth-child(1) a{
    color: #622f00;
   font-weight: bold;
}
</style>
<div class="container increase_food" uk-grid>
	<div class="wrapper wrapper-content uk-width-1-1" >
		<div class="uk-width-1-1 sxx digestibility">
     <div class="uk-position-relative uk-margin-medium">
         <ul uk-tab class="uk-tab">
             <li aria-expanded="true" class="uk-active" ><a href="#">基本常量</a></li>
             <li aria-expanded="false" class=""><a href="#">性别</a></li>
             <li aria-expanded="false" class=""><a href="#">年龄段划分</a></li>
         </ul>
         <ul class="uk-switcher uk-margin">
         	
             <li class="uk-active">
             	<div class="btn-group hidden-xs" id="toolbarBase" role="group">
					<button class="btn btn-outline btn-default" onclick="javascript:addBase()">
					    <i class="fa fa-plus"></i> 新增
					</button>
					
					<button class="btn btn-outline btn-default" onclick="javascript:editBase()">
					    <i class="fa fa-plus"></i> 修改
					</button>
					
					<button class="btn btn-outline btn-default" onclick="javascript:batchRemoveBase()">
					    <i class="fa fa-plus"></i> 删除
					</button>
			    </div>
				<table class="bootstrap-tableBase"></table>
             </li>
             <li class="">
	             <div class="btn-group hidden-xs" id="toolbarSex" role="group">
					<button class="btn btn-outline btn-default" onclick="javascript:addSex()">
					    <i class="fa fa-plus"></i> 新增
					</button>
					
					<button class="btn btn-outline btn-default" onclick="javascript:editSex()">
					    <i class="fa fa-plus"></i> 修改
					</button>
					
					<button class="btn btn-outline btn-default" onclick="javascript:batchRemoveSex()">
					    <i class="fa fa-plus"></i> 删除
					</button>
			    </div>
				<table class="bootstrap-tableSex"></table>
             </li>
             <li class="">
	            <div class="btn-group hidden-xs" id="toolbarAge" role="group">
					<button class="btn btn-outline btn-default" onclick="javascript:addAge()">
					    <i class="fa fa-plus"></i> 新增
					</button>
					
					<button class="btn btn-outline btn-default" onclick="javascript:editAge()">
					    <i class="fa fa-plus"></i> 修改
					</button>
					
					<button class="btn btn-outline btn-default" onclick="javascript:batchRemoveAge()">
					    <i class="fa fa-plus"></i> 删除
					</button>
			    </div>
				<table class="bootstrap-tableAge"></table>
             </li>
         </ul>
     </div>
     
 </div>
		
	</div>
</div>
<script src="../../../../static/main/js/ea-table.js?v=1.1.6" ></script>
<script src="../../../../static/main/systemResource/constant/baseConstant/constant.js"></script>	
<jsp:include page="../../../foot.jsp" />



