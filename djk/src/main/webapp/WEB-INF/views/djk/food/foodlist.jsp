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
<%
System.out.println(request.getParameter("foodCategoryId"));
%>
<div class="container increase_food" uk-grid>
	<div class="wrapper wrapper-content">
		
		<table class="bootstrap-table">
		</table>
	</div>
</div>
<script src="../../static/main/js/ea-table.js?v=1.1.6" ></script>
<script type="text/javascript">
var prefix = "/foodlistbygategoryid?foodCategoryId=<%=request.getParameter("foodCategoryId")%>"
$(function() {
	
	  var  columns = [{
          checkbox: true
      },
      {
          field: 'id',
          title: 'ID'
      },
        {
            field: 'name',
            title: '食物名称'
        },
        {
            field: 'alias',
            title: '别名'
        },
        {
            field: 'firstCategoryName',
            title: '食物类别'
        },
        {
            field: 'firstCategoryName',
            title: '公共分类'
        },
        {
            field: 'unitName',
            title: '单位'
        },
        {
            field: 'firstOriginName',
            title: '产地'
        },
        {
            field: 'seasonName',
            title: '时令'
        },
        {
            field: 'supplierName',
            title: '供应商'
        }
        ];
	
	
	var url = prefix;
	$.initTable(columns, url);
});

function add() {
	window.location.href="/foodadd";
}

function editFood() {
  //使用getSelections即可获得，row是json格式的数据
	var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
	          return row;
	});
	//var rows = $.getSelections("roleId");
	if (rows.length == 0) {
		$.modalMsg("请选择要修改的数据", modal_status.WARNING);
		return;
	}
	alert(rows[0].id);
    var url = prefix + '/edit?id=' + rows[0].id;
  	  //foodedit
}
</script>
<jsp:include page="../foot.jsp" />



