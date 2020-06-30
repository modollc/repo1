<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>九宏大健康管理系统</title>
<link rel="stylesheet" href="../../resources/djk/css/uikit.min.css">
<link rel="stylesheet" href="../../resources/djk/css/zTreeStyle.css"
	type="text/css">

<!-- Data Tables -->
<link rel="shortcut icon" href="favicon.ico">
<link href="../../resources/djk/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<!-- 自定义css -->
<link rel="stylesheet" href="../../resources/djk/css/style.css"
	type="text/css">
<link href="../../resources/djk/css/dataTables.bootstrap.css"
	rel="stylesheet">
<!-- jquery -->
<script type="text/javascript"
	src="../../resources/djk/js/jquery.min.js?v=2.1.4"></script>
<!-- uikit框架引用js -->
<script src="../../resources/djk/js/uikit.min.js"></script>
<script src="../../resources/djk/js/uikit-icons.min.js"></script>
<!-- zTree引用js -->
<script type="text/javascript"
	src="../../resources/djk/js/jquery.ztree.all.min.js"></script>
<!-- Data Tables -->
<script src="../../resources/djk/js/jquery.jeditable.js"></script>
<script src="../../resources/djk/js/jquery.dataTables.js"></script>
<script src="../../resources/djk/js/dataTables.bootstrap.js"></script>


<link href="../../static/css/bootstrap.min.css" rel="stylesheet" /> 
<link href="../../static/css/font-awesome.css" rel="stylesheet" />
<!-- bootstrap-table 表格插件样式 -->
<link href="../../static/ajax/libs/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" />
<link href="../../static/ajax/libs/jqTreeGrid/jquery.treegrid.css" rel="stylesheet" />
<link href="../../static/css/animate.css" rel="stylesheet" />
<link href="../../static/css/style.css" rel="stylesheet" />
<link href="../../static/css/checkbox.css" rel="stylesheet" />



 <!-- <script src="../../static/js/jquery.min.js" ></script> -->
	<!-- <script src="../../static/js/bootstrap.min.js" ></script> -->
	
	<!-- bootstrap-table 表格插件 -->
	<script src="../../static/ajax/libs/bootstrap-table/bootstrap-table.min.js" ></script>
	<script src="../../static/ajax/libs/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" ></script>
	<script src="../../static/ajax/libs/bootstrap-table/extensions/mobile/bootstrap-table-mobile.min.js" ></script>
	<script src="../../static/ajax/libs/bootstrap-table/extensions/toolbar/bootstrap-table-toolbar.min.js" ></script>
	<!-- jquery-validate 表单验证插件 -->
	<script src="../../static/ajax/libs/validate/jquery.validate.min.js" ></script>
	<script src="../../static/ajax/libs/validate/messages_zh.min.js" ></script>
	<script src="../../static/ajax/libs/validate/jquery.validate.extend.js"></script>
	<!-- jquery-validate 表单树插件 -->
	<script src="../../static/ajax/libs/jqTreeGrid/jquery.treegrid.min.js" ></script>
	<script src="../../static/ajax/libs/jqTreeGrid/jquery.treegrid.extension.js" ></script>
	<!-- jquery-export 表格导出插件 -->
	<script src="../../static/ajax/libs/bootstrap-table/extensions/export/bootstrap-table-export.js" ></script>
	<script src="../../static/ajax/libs/bootstrap-table/extensions/export/tableExport.js" ></script>
	<script src="../../static/ajax/libs/layer/layer.min.js" ></script>
	<script src="../../static/main/js/common.js?v=1.1.6" ></script>
	<script src="../../static/main/js/ea-ui.js?v=1.1.6" ></script>  
    <script src="../../static/main/js/ea-table.js?v=1.1.6" ></script>  

</head>
<style>

</style>
<body>
	<div class="top">
		<div class="uk-container">
			<h3><img src="../../resources/djk/images/logo.jpg">大健康管理系统</h3>
		</div>	
	</div>
	
	<div class="box uk-container">
	   <header>
            <section id="nav-content" class="nav-menu-content-box">
                <div class="nav-menu-content">
                    <div class="nav-menu-content-item">
                        <div class="nav-menu-content-cell">
                            <div class="nav-menu-content-sell">
                                <div class="nav-menu-content-list">
                                    <div class="nav-menu-content-info">
                                        <div class="navMenu-item navMenu-item-one">
                                            <a href="javascript:;" class="navMenu-title">食物维护</a>
                                            <div class="navMenu-item-content">
                                                <ul>
                                                <li>
                                                        <a href="/foodmain.html">食物维护</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="navMenu-item navMenu-item-one">
                                            <a href="javascript:;" class="navMenu-title">营养维护</a>
                                            <div class="navMenu-item-content">
                                                <ul>
                                                 <li>
                                                        <a href="/nutrition/nutritionList/index">营养维护</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="navMenu-item navMenu-item-one">
                                            <a href="javascript:;" class="navMenu-title">消化率</a>
                                            <div class="navMenu-item-content">
                                                <ul>
                                                    <li>
                                                        <a href="/dige/digeScene/index">场景</a>
                                                    </li>
                                                    <li>
                                                        <a href="/dige/digeEatfast/index">吃饭速度</a>
                                                    </li>
                                                    <li>
                                                        <a href="/dige/digeDining/index">用餐量</a>
                                                    </li>
                                                    <li>
                                                        <a href="/dige/digeNutrition/index">营养需要量</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="navMenu-item navMenu-item-two">
                                            <a href="javascript:;" class="navMenu-title">食物营养公共资源</a>
                                            <div class="navMenu-item-content">
                                                <ul>
                                                    <li>
                                                        <a href="/food/foodPhyState/index">食物物理状态</a>
                                                    </li>
                                                    <li>
                                                        <a href="/food/foodOrigin/index">产地</a>
                                                    </li>
                                                    <li>
                                                        <a href="/food/foodSeasonal/index">时令</a>
                                                    </li>
                                                    <li>
                                                        <a href="/food/foodSupplier/index">供应商</a>
                                                    </li>
                                                    <li>
                                                        <a href="/food/foodCooking/index">烹饪方式</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="navMenu-item navMenu-item-three">
                                            <a href="javascript:;" class="navMenu-title">人需公共资源
                                            </a>
                                            <div class="navMenu-item-content">
                                                <ul>
                                                    <li style="width:100%">
                                                        <a href="/peopleResouce/peopleSleep/index">睡眠</a>
                                                    </li>
                                                    <li style="width:100%">
                                                        <a href="/peopleResouce/peopleActive/index">活跃度</a>
                                                    </li>
                                                    <li>
                                                        <a href="/peopleResouce/peopleMovement/index">运动</a>
                                                    </li>
                                                    <li>
                                                        <a href="/peopleResouce/peopleAppetite/index">食欲状况</a>
                                                    </li>
                                                    <li>
                                                        <a href="/peopleResouce/peopleTemperature/index">环境温度</a>
                                                    </li>
                                                    <li>
                                                        <a href="/peopleResouce/peopleHousehold/index">家居人口</a>
                                                    </li>
                                                    <li>
                                                        <a href="/peopleResouce/peopleArea/index">人均面积</a>
                                                    </li>
                                                    
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="navMenu-item navMenu-item-three">
                                            <a href="javascript:;" class="navMenu-title">系统公共资源
                                            </a>
                                            <div class="navMenu-item-content">
                                                <ul>
                                                    <li style="width:100%">
                                                        <a href="/systemResource/systemConstant/index">常量</a>
                                                    </li>
                                                    <li style="width:100%">
                                                        <a href="/systemResource/systemHealth/index">健康状况</a>
                                                    </li>
                                                    <li>
                                                        <a href="/systemResource/systemEnergy/index">能量单位</a>
                                                    </li>
                                                    <li>
                                                        <a href="/systemResource/systemWeight/index">重量单位</a>
                                                    </li>
                                                    <li>
                                                        <a href="/systemResource/systemUnit/index">体积单位</a>
                                                    </li>   
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="navMenu-item navMenu-item-one">
                                            <a href="javascript:;" class="navMenu-title">测算</a>
                                            <div class="navMenu-item-content">
                                                <ul>
                                                <li style="width:100%">
                                                        <a href="javascript:;">测算</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="navMenu-item navMenu-item-three">
                                            <a href="javascript:;" class="navMenu-title">系统管理
                                            </a>
                                            <div class="navMenu-item-content">
                                                <ul>
                                                    <li style="width:100%">
                                                        <a href="/sys/role/list">角色管理</a>
                                                    </li>
                                                    <li style="width:100%">
                                                        <a href="/sys/resource/index">系统资源</a>
                                                    </li>
                                                    <li style="width:100%">
                                                        <a href="/user/list">系统用户管理</a>
                                                    </li>
                                                    <li>
                                                        <a href="/sys/log/list">系统日志</a>
                                                    </li>
                                                    
                                                </ul>
                                            </div>
                                        </div>
                                        <div class="navMenu-item navMenu-item-one">
                                            <a href="javascript:;" class="navMenu-title">会员管理</a>
                                            <div class="navMenu-item-content">
                                                <ul></ul>
                                            </div>
                                        </div>
<!--                                        <div class="tree">  
							                <ul>
							                   <shiro:hasPermission name="/sys/role/list"> 
							                    <li>
							                        <span><a href="/sys/role/list" target="iframe">系统权限</a></span>
							                    </li>
							                    </shiro:hasPermission>
							                    <shiro:hasPermission name="/sys/resource/index"> 
							                    <li>
							                        <span><a href="/sys/resource/index" target="iframe">系统资源</a></span>
							                    </li>
							                    </shiro:hasPermission>
							                    <shiro:hasPermission name="/user">                            
							                    <li>
							                        <span>用户管理<i class="glyphicon glyphicon-chevron-right"></i></span>
							                        <ul>
							                            <li>
							                                <span><a href="/user/list" target="iframe"><em></em>用户列表</a></span>
							                            </li>
							                        </ul>
							                    </li>
							                    </shiro:hasPermission>
							                    <shiro:hasPermission name="/log"> 
							                    <li>
							                        <span><a href="/sys/log/list" target="iframe">系统日志</a></span>
							                    </li>
							                    </shiro:hasPermission>
							                </ul>
							            </div>
							         -->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </header>        