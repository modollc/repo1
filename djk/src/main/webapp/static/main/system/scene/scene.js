var prefix = "/dige/digeScene"
$(function() {
	
	  var  columns = [{
          checkbox: true
      },
      {
          field: 'id',
          title: 'ID'
      },
        {
            field: 'sceneName',
            title: '场景名'
        },
        {
            field: 'reference',
            title: '参考值'
        },
        {
            field: 'sceneValue',
            title: '系数'
        }];
	
	
	var url = prefix + "/list";
	$.initTable(columns, url);
});



/*岗位管理-新增*/
function add() {
    var url = prefix + '/add';
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

//批量删除
function batchRemove() {
	var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
        return row;
    });
	
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", modal_status.WARNING);
		return;
	}
	$.modalConfirm("确认要删除选中的【" + rows[0].sceneName + "】条数据吗?", function() {
		_ajax(prefix + '/delete', { "id": rows[0].id }, "post");
	});
}
