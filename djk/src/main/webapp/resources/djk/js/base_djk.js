$(document).ready(function(e) {
	
	//系统权限树状分类
	$(".tree li span").find("i").attr('class','icon_jia');
	
	$(".tree li").click(function(e) {
        $(this).children("ul").toggle();
        e.stopPropagation(); //阻止冒泡事件
    });	

	//table即时修改样式（zzl接手）
	$(".table_default .edit").click(function(){
		var thistext = $(this).parents("td").parents("tr").children('td').eq(1).html();
		//var thistext_id = $(this).parents("td").parents("tr").children('td').eq(0).html();
		//var thistext_pai = $(this).parents("td").parents("tr").children('td').eq(2).html();
		$(this).parents("td").parents("tr").children('td').eq(1).html("<input type='text' class='input' value='"+thistext+"' /><i class='glyphicon glyphicon-ok'></i>");
		//$(this).parents("td").parents("tr").eq(0).html("<td class='style1'>"+thistext_id+"</td><td name='edit'><input type='text' class='input' value='"+thistext+"' /></td><td class='style1'><input type='text' class='input' value='"+thistext_pai+"' /></td><td class='style5'><a href='javascript:void(0);'>确定</a><a href='javascript:void(0);' data-toggle='modal' data-target='#myModal'>删除</a></td>");
		$(this).unbind('click');
	});	
	
	$(".table_default .edit1").click(function(){
		var thistext = $(this).parents("td").parents("tr").children('td').eq(1).html();
		var thistext_id = $(this).parents("td").parents("tr").children('td').eq(0).html();
		var thistext_pai = $(this).parents("td").parents("tr").children('td').eq(2).html();
		//$(this).parents("td").parents("tr").children('td').eq(1).html("<input type='text' class='input' value='"+thistext+"' /><i class=''>✔</i>");
		$(this).parents("td").parents("tr").eq(0).html("<td class='style1'>"+thistext_id+"</td><td name='edit'><input type='text' class='input' value='"+thistext+"' autofocus /></td><td class='style1'><input type='text' class='input' value='"+thistext_pai+"' autofocus /></td><td class='style5'><a href='javascript:void(0);'>确定</a><a href='javascript:void(0);' data-toggle='modal' data-target='#myModal'>删除</a></td>");
		$(this).unbind('click');
	});	
	
	/*
	$(".table .del").click(function(){
		$(this).parents('tr').remove();
	});	
	*/
	
	//输入框自动获取焦点
	$("#inputfocus").select();
	
	
	
	//联想搜索
	$(".autosearch").click(function(){
		$(".searchul").css("display","none");
		$(this).find(".searchul").css("display","block");
		$(this).find(".searchul_name").css("display","block");
	});
	
	//联想内容填回搜索框
	$(".searchul").find("li").click(function() {
		var newbianhao = $(this).find(".bianhao").html();
		var newmingcheng = $(this).find(".mingcheng").html();
									
		$(this).parents(".searchul").parents(".autosearch").children("input").val(newbianhao);
		$(this).parents(".searchul").parents("td").next("td").html(newmingcheng);
	});
	
	$(".searchul_name").find("li").click(function() {
		var newbianhao = $(this).find(".bianhao").html();
		var newmingcheng = $(this).find(".mingcheng").html();
									
		$(this).parents(".searchul_name").parents(".autosearch").children("input").val(newmingcheng);
		$(this).parents(".searchul_name").parents("td").prev("td").html(newbianhao);
	});
	
	$(".remove_tr").click(function(){
		//alert("aaa");
		$(this).parents("tr").parents("tbody").find("#a1").remove();
		//$(this).parents("tbody").html();
		//alert("zz");
	});
							

});

