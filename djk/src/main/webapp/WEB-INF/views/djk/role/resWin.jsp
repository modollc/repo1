<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>

<script src="../../../resources/djk/js/jquery-1.8.2.min.js"></script>
<script src="../../../resources/djk/easyui/jquery.easyui.min.js"></script>
<script src="../../../resources/djk/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="../../../resources/djk/js/func.js"></script>
<script src="../../../resources/djk/js/jquery.form.js"></script>
<script src="../../../resources/djk/js/jquery.ui.widget.js"></script>
<script src="../../../resources/djk/js/jquery.fileupload.js"></script>
<script src="../../../resources/djk/js/jquery.filedownload.js"></script>
<script src="../../../resources/djk/js/jquery.multifile.upload.js"></script>

<link rel="stylesheet" href="../../../resources/djk/easyui/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../../../resources/djk/easyui/themes/icon.css" type="text/css"></link>
<link rel="stylesheet" href="../../../resources/djk/css/style.css" type="text/css"></link>



<link rel="stylesheet"
	href="../../../resources/djk/colorbox/colorbox.css"
	type="text/css"></link>
<script type="text/javascript"
	src="../../../resources/djk/colorbox/jquery.colorbox-min.js"></script>


<script language="javascript">
$(function(){
	$("#resourceTree").tree({
		method: "get",
		checkbox: true,
		cascadeCheck: false,
		onLoadSuccess:function(){
			$("#resourceTree").tree('expandAll');
		},
		url: "${pageContext.request.contextPath}/sys/resource/roleResTree?roleId=${id}"
	});
	
	$('#saveRuleRes').click(function() {
		var checked = $("#resourceTree").tree('getChecked');
		
		$(this).find('.l-btn-text').html('提交中...');
		$(this).attr('disabled',true);
		
		var resids = new Array();
		$.each(checked,function(idx,e){
			resids.push(e.id);
		});
		$("#resIds").val(resids.toString());
		$("#roleResForm").form('submit', {
			url : "${pageContext.request.contextPath}/system/role/saveRoleRes",
			success : function(e) {
				$(this).find('.l-btn-text').html('保存');
				$(this).attr('disabled',false);
				$.messager.progress('close');
				$.messager.show({
					title : '提示',
					msg : e,
					showType : 'show'
				});
				closeWin();
			}
		});
	});
	
});

function closeWin(){
	window.opener=null;
	window.open('','_self');
	window.close();
}
</script>
<body class="easyui-layout"  style="overflow-y: scroll">
<form id="roleResForm" method="post">
	<div style="margin-top: 5px;">
		<label class="lab-item">角色名称: </label><span id="roleNameSpan">${rolesName}</span><br />
		<label style="color: red; font-weight: bold; margin-left: 15px;">新分配的功能及菜单需要重新登录后才能生效
		</label> <input id="resIds" type="hidden" name="resIds"> <input
			id="roleId" type="hidden" name="roleId" value="${id}">
	</div>

	<ul id="resourceTree"
		style="margin-top: 10px; margin-left: 10px; max-height: 370px; overflow: auto; border: 1px solid #86a3c4;">
		<div style="padding: 12px 140px;text-align: center;">数据加载中...</div>
	</ul>
	<div>
		<p class="p-item p-btn">
			<a id="saveRuleRes" class="easyui-linkbutton" iconCls="icon-save">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-delete"
				onclick="closeWin();">关闭</a>
		</p>
	</div>
</form>
</body>