var prefix = "/systemResource/systemConstant"
$(function() {
	var tabIndex =0;
	
	var urlBase = prefix + "/listBase";
	var urlSex = prefix + "/listSex";
	var urlAge = prefix + "/listAge";
	
	$(".uk-tab li").click(function(){
		$(".uk-tab li").removeClass("uk-active");
		$(this).addClass("uk-active");
        $(".uk-switcher li").removeClass("uk-active");
        $(".uk-switcher li:nth-child(" + $(".uk-active").index()+1 + ")").addClass("uk-active");
        tabIndex = $(".uk-active").index();
        if(tabIndex == 0){
    		$.initTableBase(columnsBase, urlBase);
    	}else if(tabIndex == 1){
    		$.initTableSex(columnsSex, urlSex);
    	}else{
    		$.initTableAge(columnsAge, urlAge);
    	}
    })
	  var  columnsBase = [{
          checkbox: true
      },
      {
          field: 'id',
          title: 'ID'
      },
        {
            field: 'constantName',
            title: '常量名称'
        },
        {
        	field: 'minValue',
        	title: '最小值'
        },
        {
            field: 'maxValue',
            title: '最大值'
        },
        {
            field: 'constantValue',
            title: '增幅'
        }];
	
	  var  columnsSex = [{
          checkbox: true
      },
      {
          field: 'id',
          title: 'ID'
      },
        {
            field: 'sexName',
            title: '性别名称'
        }];
	  
	  var  columnsAge = [{
          checkbox: true
      },
      {
          field: 'id',
          title: 'ID'
      },
        {
            field: 'ageName',
            title: '年龄段名称'
        },
        {
            field: 'minValue',
            title: '最小值'
        },{
            field: 'maxValue',
            title: '最大值'
        }];
	

	
	if(tabIndex == 0){
		$.initTableBase(columnsBase, urlBase);
	}else if(tabIndex == 1){
		$.initTableSex(columnsSex, urlSex);
	}else{
		$.initTableAge(columnsAge, urlAge);
	}
});


//----------------------------------------基本常量-------------------------------------------------------------------------
function addBase() {
    var url = prefix + '/addBase';
    layer_showAuto("新增", url);
}

function editBase() {
	//使用getSelections即可获得，row是json格式的数据
	var rows = $(".bootstrap-tableBase").bootstrapTable('getSelections', function (row) {
	          return row;
	});
	//var rows = $.getSelections("roleId");
	if (rows.length == 0) {
		$.modalMsg("请选择要修改的数据", modal_status.WARNING);
		return;
	}
    var url = prefix + '/editBase?id=' + rows[0].id;
    layer_showAuto("修改", url);
}

//批量删除
function batchRemoveBase() {
	var rows = $(".bootstrap-tableBase").bootstrapTable('getSelections', function (row) {
        return row;
    });
	
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", modal_status.WARNING);
		return;
	}
	$.modalConfirm("确认要删除选中的【" + rows[0].constantName + "】条数据吗?", function() {
		_ajax(prefix + '/deleteBase', { "id": rows[0].id }, "post");
		$('.bootstrap-tableBase').bootstrapTable('refresh');
	});
}
//----------------------------------------性别-------------------------------------------------------------------------
function addSex() {
	var url = prefix + '/addSex';
	layer_showAuto("新增", url);
}

/*岗位管理-修改*/
function editSex() {
	//使用getSelections即可获得，row是json格式的数据
	var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
		return row;
	});
	//var rows = $.getSelections("roleId");
	if (rows.length == 0) {
		$.modalMsg("请选择要修改的数据", modal_status.WARNING);
		return;
	}
	var url = prefix + '/editSex?id=' + rows[0].id;
	layer_showAuto("修改", url);
}

//批量删除
function batchRemoveSex() {
	var rows = $(".bootstrap-table").bootstrapTable('getSelections', function (row) {
		return row;
	});
	
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", modal_status.WARNING);
		return;
	}
	$.modalConfirm("确认要删除选中的【" + rows[0].constantName + "】条数据吗?", function() {
		_ajax(prefix + '/deleteSex', { "id": rows[0].id }, "post");
	});
}
//----------------------------------------年龄段划分-------------------------------------------------------------------------
function addAge() {
	var url = prefix + '/addAge';
	layer_showAuto("新增", url);
}

/*岗位管理-修改*/
function editAge() {
	//使用getSelections即可获得，row是json格式的数据
	var rows = $(".bootstrap-tableAge").bootstrapTable('getSelections', function (row) {
		return row;
	});
	//var rows = $.getSelections("roleId");
	if (rows.length == 0) {
		$.modalMsg("请选择要修改的数据", modal_status.WARNING);
		return;
	}
	var url = prefix + '/editAge?id=' + rows[0].id;
	layer_showAuto("修改", url);
}

//批量删除
function batchRemoveAge() {
	var rows = $(".bootstrap-tableAge").bootstrapTable('getSelections', function (row) {
		return row;
	});
	
	if (rows.length == 0) {
		$.modalMsg("请选择要删除的数据", modal_status.WARNING);
		return;
	}
	$.modalConfirm("确认要删除选中的【" + rows[0].ageName + "】条数据吗?", function() {
		_ajax(prefix + '/deleteAge', { "id": rows[0].id }, "post");
	});
}

$.initTableBase = function (_columns, _url) {
    $('.bootstrap-tableBase').bootstrapTable({
        method: 'get',                // 请求方式（*）
        dataType: "json",             // 返回格式（*）
        url: _url,                    // 请求后台的URL（*）
        pagination: true,             // 是否显示分页（*）
		pageSize: 13,                 // 每页的记录行数（*）
        pageNumber: 1,                // 初始化加载第一页，默认第一页
		pageList: [13, 30, 50],       // 可供选择的每页的行数（*）
		search: true,                 // 是否显示搜索框功能
		singleSelect: false,          // 是否禁止多选
        iconSize: 'outline',          // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
        toolbar: '#toolbar',          // 指定工作栏
        sidePagination: "server",     // 启用服务端分页 
		showRefresh: true,            // 是否显示刷新按钮
		showColumns: true,            // 是否显示隐藏某列下拉框
		showToggle: false,             // 是否显示详细视图和列表视图的切换按钮
        cache: false,                 // 是否使用缓存
        showExport: true,             // 是否支持导出文件
        singleSelect: true,           //只能单选
        queryParams: function(params) {
            return {
                // 传递参数查询参数
                pageSize:       params.limit,
                pageNum:        params.offset / params.limit + 1,
                searchValue:    params.search,
                orderByColumn:  params.sort,
                isAsc:          params.order
            };
        },
        columns: _columns
    });
}

$.initTableSex = function (_columns, _url) {
    $('.bootstrap-tableSex').bootstrapTable({
        method: 'get',                // 请求方式（*）
        dataType: "json",             // 返回格式（*）
        url: _url,                    // 请求后台的URL（*）
        pagination: true,             // 是否显示分页（*）
		pageSize: 13,                 // 每页的记录行数（*）
        pageNumber: 1,                // 初始化加载第一页，默认第一页
		pageList: [13, 30, 50],       // 可供选择的每页的行数（*）
		search: true,                 // 是否显示搜索框功能
		singleSelect: false,          // 是否禁止多选
        iconSize: 'outline',          // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
        toolbar: '#toolbar',          // 指定工作栏
        sidePagination: "server",     // 启用服务端分页 
		showRefresh: true,            // 是否显示刷新按钮
		showColumns: true,            // 是否显示隐藏某列下拉框
		showToggle: false,             // 是否显示详细视图和列表视图的切换按钮
        cache: false,                 // 是否使用缓存
        showExport: true,             // 是否支持导出文件
        singleSelect: true,           //只能单选
        queryParams: function(params) {
            return {
                // 传递参数查询参数
                pageSize:       params.limit,
                pageNum:        params.offset / params.limit + 1,
                searchValue:    params.search,
                orderByColumn:  params.sort,
                isAsc:          params.order
            };
        },
        columns: _columns
    });
}

$.initTableAge = function (_columns, _url) {
    $('.bootstrap-tableAge').bootstrapTable({
        method: 'get',                // 请求方式（*）
        dataType: "json",             // 返回格式（*）
        url: _url,                    // 请求后台的URL（*）
        pagination: true,             // 是否显示分页（*）
		pageSize: 13,                 // 每页的记录行数（*）
        pageNumber: 1,                // 初始化加载第一页，默认第一页
		pageList: [13, 30, 50],       // 可供选择的每页的行数（*）
		search: true,                 // 是否显示搜索框功能
		singleSelect: false,          // 是否禁止多选
        iconSize: 'outline',          // 图标大小：undefined默认的按钮尺寸 xs超小按钮sm小按钮lg大按钮
        toolbar: '#toolbar',          // 指定工作栏
        sidePagination: "server",     // 启用服务端分页 
		showRefresh: true,            // 是否显示刷新按钮
		showColumns: true,            // 是否显示隐藏某列下拉框
		showToggle: false,             // 是否显示详细视图和列表视图的切换按钮
        cache: false,                 // 是否使用缓存
        showExport: true,             // 是否支持导出文件
        singleSelect: true,           //只能单选
        queryParams: function(params) {
            return {
                // 传递参数查询参数
                pageSize:       params.limit,
                pageNum:        params.offset / params.limit + 1,
                searchValue:    params.search,
                orderByColumn:  params.sort,
                isAsc:          params.order
            };
        },
        columns: _columns
    });
    
    
}
