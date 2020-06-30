<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<div class="page">共${page.rowCount}条记录 分  ${page.pageCount}  页 当前第${page.num}页&nbsp;&nbsp;
<c:if test="${page.num != 1}">
<a href="${page.url}?${page.queryString}&page=1">首页</a>
<a href="${page.url}?${page.queryString}&page=${page.prev}">上一页</a>
</c:if>

<c:if test="${page.num != page.last}">
<a href="${page.url}?${page.queryString}&page=${page.next}">下一页</a>
<a href="${page.url}?${page.queryString}&page=${page.last}">尾页</a>
</c:if>

&nbsp;&nbsp;跳转到第<input id="tiaoye_id_random" type="text" class="page_input" />页  <a href="javascript:void(0);" onclick="tiaoye()" >确定</a></div>
<script>

$(function(){  
	var jsonstr = '${queryMap}'
	var json = JSON.parse(jsonstr);
	for(var p in json){//遍历json对象的每个key/value对,p为key
		var temp_id = '#'+p;
		$(temp_id).val(json[p]);
	}
});  

function tiaoye(){
	var a = $("#tiaoye_id_random").val();
	var b = '${page.pageCount}';
	if(parseInt(a)>parseInt(b) || parseInt(a)<1){
		alert("没有此页");
		return;
	}
	window.location.href="${page.url}?${page.queryString}&page="+$("#tiaoye_id_random").val();
}
	
</script>