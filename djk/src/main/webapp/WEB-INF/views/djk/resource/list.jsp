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




<script language="javascript">
	$(function() {
		$('#a-gridSearch').click(function() {
			$('#dataGrid').datagrid('reload', queryParamsHandler());
		});
		
		$("#a-gridAdd").click(function() {
			var selected = $('#dataGrid').treegrid('getSelected');
			var id = "";
			if(selected)
				id = selected.id;
			else
				id="1";
			$("#addResources").window({
				width : 1100,
				height : 480,
				href : "/sys/resource/addWin?id="+id,
				title : "资源添加",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});
		
		$("#a-gridEdit").click(function() {
			var selected = $('#dataGrid').treegrid('getSelected');
			$("#addResources").window({
				width : 1100,
				height : 480,
				href : "/sys/resource/editWin?id="+selected.id,
				title : "资源编辑",
				modal : true,
				shadow : false,
				collapsible : false,
				minimizable : false,
				maximizable : false
			});
		});

		$("#a-gridRemove").click(function() {
			var selected = $('#dataGrid').datagrid('getSelected');
			if (!selected) {
				$.messager.alert('提示', '请选择操作行。');
				return;
			}
			if (selected.children.length > 0 || selected.state == 'closed') {
				$.messager.alert('提示', '该节点下有子节点,不允许删除。');
				return;
			}
			$.messager.confirm('确认', '确定删除此资源吗？此操作不可撤消', function(r) {
				if (r) {
					$.messager.progress({
						text : "提交中..."
					});
					$.ajax({
						url : "/sys/resource/del?id=" + selected.id,
						cache : false,
						success : function(e) {
							$('#dataGrid').treegrid('reload');
							$.messager.progress('close');
							$.messager.show({
								title:'提示',
								msg:e,
								showType:'show'
							});
						}
					});
				}
			});
		});
		
		
	})

	function typeFormat(value, row, index) {
		return value == 1 ? "菜单" : value == 0 ? "根节点" : "按钮";
	}
	
	function scopeFormat(value, row, index) {
		return value == 1 ? "商家" : "平台";
	}

	function statusFormat(value, row, index) {
		return "1";
	}

	function afterDataGridLoaded() {
	}

	function dataGridLoadFail() {
		alert("服务器异常");
	}

	function dataGridLoadSuccess(row, data) {
		$('#dataGrid').treegrid('expand',1);
	}

	function cc(row) {
	}

	function bl(row, param) {
		if (!row) {
			param.id = 0;
		}
	}
	
	
	function closeW(){
		$("#addResources,window.parent.document").window("close");
	}
	
	function loadSuccess(){
		var t = $("#pid").combotree('tree');
	    var node = t.tree('getSelected');
	    if (node){
			t.tree('expandTo', node.target);
	    }
	}
	function addBtn(){
		var isValid = $("#addResourcesForm").form('validate');
		if (isValid) {
			var type = $('#type').combobox('getValue');
			if (type == "") {
				$.messager.alert('提示', '请选择资源类型。');
				return;
			}
			
			var scope = $('#scope').combobox('getValue');
			if (scope == "") {
				$.messager.alert('提示', '请选择应用范围。');
				return;
			}
			
			$.messager.progress({
				text : "提交中..."
			});
			$("#addResourcesForm").form('submit', {
				url : "/sys/resource/save",
				success : function(e) {
					$.messager.progress('close');
					$.messager.show({
						title : '提示',
						msg : e,
						showType : 'show'
					});
					$('#dataGrid,window.parent.document').treegrid('reload');
					closeW();
				}
			});
		}
	}
	
	function editBtn(){
		var isValid = $("#addResourcesForm").form('validate');
		if (isValid) {
			var type = $('#type').combobox('getValue');
			if (type == "") {
				$.messager.alert('提示', '请选择资源类型。');
				return;
			}
			
			var scope = $('#scope').combobox('getValue');
			if (scope == "") {
				$.messager.alert('提示', '请选择应用范围。');
				return;
			}
			
			$.messager.progress({
				text : "提交中..."
			});
			$("#addResourcesForm").form('submit', {
				url : "/sys/resource/save",
				success : function(e) {
					$.messager.progress('close');
					$.messager.show({
						title : '提示',
						msg : e,
						showType : 'show'
					});
					$('#dataGrid,window.parent.document').treegrid('reload');
					closeW();
				}
			});
		}
	}
	
	
</script>
<body class="easyui-layout"  style="overflow-y: scroll">
<div id="searchbar" data-options="region:'north'"
	style="margin: 0 auto;" border="false">
	<h2 class="h2-title">
		资源列表 <span class="s-poar"></span>
	</h2>
	<div id="searchbox" class="head-seachbox">
		<div class="w-p99 marauto searchCont" style="display: none">
			<form class="form-search" action="/sys/resource/list" method="post"
				id="queryForm" name="queryForm">
				<div class="fluidbox">
					<p class="p4 p-item">
						<label class="lab-item">类型名称 :</label> <input type="text"
							class="txt" id="q_name" name="q_name" value="" />
					</p>
				</div>
			</form>
		</div>
	</div>
</div>

<div data-options="region:'center'" border="false">
	<table id="dataGrid" class="easyui-treegrid"
		data-options="rownumbers:false
						,singleSelect:true
						,autoRowHeight:true
						,animate:true
						,fitColumns:true
						,striped:true
						,pagination:false
						,toolbar:'#gridTools'
						,pageSize:'2'
						,fit:true
    					,url:'/sys/resource/list'
    					,queryParams:queryParamsHandler()
    					,onLoadSuccess:dataGridLoadSuccess
    					,onLoadError:dataGridLoadFail
						,onBeforeExpand:cc
						,treeField:'content'
						,idField:'id'
    					,method:'get'
						,onBeforeLoad:bl">
		<thead>
			<tr>
				<th field="content" width="120" align="left" halign="center">资源名称</th>
				<th field="url" width="120" align="left" halign="center">资源链接</th>
				<th field="type" width="60" align="center" formatter="typeFormat">资源类型</th>
				<th field="scope" width="60" align="center" formatter="scopeFormat">应用范围</th>
				<th field="status" width="60" align="center" formatter="statusFormat">状态</th>
			</tr>
		</thead>
	</table>

		<a id="a-gridSearch" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"></a>
	<div id="gridTools">
		<shiro:hasPermission name="/sys/resource/addWin"> 
		<a id="a-gridAdd" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="/sys/resource/editWin"> 
		<a id="a-gridEdit" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="/sys/resource/del"> 
		<a id="a-gridRemove" href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</shiro:hasPermission>
	</div>

	<div class="wrapper" id="addResources">
		
	</div>
</div>

</body>