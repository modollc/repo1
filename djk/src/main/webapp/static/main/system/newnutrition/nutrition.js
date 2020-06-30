var prefix = "/nutrition/nutritionList"
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
			title: '营养名称'
		},
		{
			field: 'nutritionCategoryName',
			title: '营养类别'
		},
		{
			field: 'unit',
			title: '单位'
		}];


		var url = prefix + "/list";
		//$.initTable(columns, url);
		$.initTableParams(columns,url,queryParams);
	});
function queryParams(params) {
    var param = {};
    param['pageSize'] = params.limit;   //页面大小
    param['pageNum'] =  params.offset / params.limit + 1;   //页码
    param['searchValue'] =   params.search;   //页码
    param['orderByColumn'] =   params.sort;   //页码
    param['isAsc'] =   params.isAsc;   //页码
    param['cateId'] =  $("#cateId").val();   //页码
    return param;
}
/*岗位管理-新增*/
function add1() {

	if(fId==""||fName==""){
		alert('请选择分类！');
		return;
	}

	var url = prefix + '/add?fId='+fId+'&fName='+fName;
	//var url = prefix + '/add';
	layer_showAuto("新增", url);
}

/*岗位管理-修改*/
function edit() {
	//使用getSelections即可获得，row是json格式的数据
	var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
		return row;
	});
	//var rows = $.getSelections("roleId");
	if (rows.length == 0) {
		$.modalMsg("请选择要修改的数据", modal_status.WARNING);
		return;
	}
	var url = prefix + '/edit?id=' + rows[0].id;
	layer_showAuto("修改", url);
}


/*岗位管理-修改*/
function copy() {
	//使用getSelections即可获得，row是json格式的数据
	var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
		return row;
	});
	//var rows = $.getSelections("roleId");
	if (rows.length == 0) {
		$.modalMsg("请选择要复制的数据", modal_status.WARNING);
		return;
	}
	var url = prefix + '/copy'; 
	$.ajax({
		type: "post",
		url : url,
		data: {id:rows[0].id},
		dataType: "json",
		success : function(data) {
			 if (data.code == 0) {
			    	parent.layer.msg("保存成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
						$.parentReload();
					});
			    } else {
			    	$.modalAlert(data.msg, 500);
			    }
		},error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	})
}





//批量删除
function batchRemove() {
	var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
		return row;
	});

	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", modal_status.WARNING);
		return;
	}
	$.modalConfirm("确认要删除选中的【" + rows[0].name + "】条数据吗?", function() {
		_ajax(prefix + '/delete', { "id": rows[0].id }, "post");
	});
}
