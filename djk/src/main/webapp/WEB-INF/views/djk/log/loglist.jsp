<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@include file="../commons/head.jsp"%>

<body>
<div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">删除</h4><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">确认要删除？删除后无法恢复！</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onclick="sureDelete();">确定</button><button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</div>
	
<script>
	//table即时修改样式
	function edit(id){
		var thistext = $('#'+id).parents("td").parents("tr").children('td').eq(2).html();
		$('#'+id).parents("td").parents("tr").children('td').eq(2).html("<input type='text' id='"+id+"' class='input' value='"+thistext+"' onkeydown='javascript:if(event.keyCode==13) update("+id+");'/><i class='' onclick='update("+id+")'>✔</i>");
	}
	var departmentCatalogid ;
	//点击删除按钮
	function delClick(id){
		departmentCatalogid = id;
	};
	//删除弹框,确认删除
	function sureDelete(){
		$.ajax({
            type:"POST",
            url: "${pageContext.request.contextPath}/department/del",
            dataType: "json",
            data: "id="+departmentCatalogid,
            cache:false,
            success:function(data){//如果分类下有关联数据，不能删除！
            	if (data == "true") {
                	alert("删除成功");
	                window.location.reload();
	            }else if(data == "false"){
	               	alert("删除失败");
	            }else if(data == "hasDep"){
	            	alert("分类下有关联数据，不能删除！");
	            }
            }
        });
	};
	//更新科室目录
	function update(id){
		$.ajax({
            type:"POST",
            url: "${pageContext.request.contextPath}/department/update",
            dataType: "json",
            data:{
                //请求参数
            	"id":id,"name":$('#'+id).val()
            }, 
            cache:false,
            success:function(data){
                if (data == "true") {
                	alert("编辑成功");
	                window.location.reload();
	            }else if(data == "false"){
	               	alert("编辑失败");
	            }else if(data == "hasDep"){
	            	alert("您输入的分类名称已存在！");
	            }
                
            }
        });
	};
	//新增科室分类
	function add(value){
		$.ajax({
            type:"POST",
            url: "${pageContext.request.contextPath}/department/add",
            dataType: "json",
            data:{
            	"name":value
            }, 
            cache:false,
            success:function(data){
                if (data == "true") {
	               	alert("新增成功");
	                window.location.href = "${pageContext.request.contextPath}/department/departmentCatalogList";
	            }else if(data == "false"){
	               	alert("新增失败");
	            }else if(data == "hasDep"){
	            	alert("科室分类已存在！");
	            }
            }
        });
	}
	//收费项目维护
	function maintain(id){
		window.location.href = "${pageContext.request.contextPath}/department/departmentmaintain?id="+id;
	}
</script>
	 <div class="container-fluid">
    	<div class="snav"><a href="javascript:void(0);" class="back" onclick="javascript:history.back(-1);">返回</a><a href="javascript:void(0);">系统管理</a><span>&gt;</span><a href="javascript:void(0);">系统日志</a></div>
		<table cellspacing="1" class="table_default">
           <thead>
                <tr>
                	<th class="style1">序号</th>
                	<th class="style1">姓名</th>
                	<th>IP地址</th>
                    <th class="style5">登录时间</th>
            	</tr>
            </thead>
			<tbody id="PageData">
                 <c:forEach items="${list}" var="sysLog" varStatus='status'>
		            <tr>
		            	<td class="style1">${status.index+1+page.startRow}</td>
		            	<td style="display:none">${sysLog.id}</td>
		            	<td >${sysLog.userName}</td>
		            	<td >${sysLog.ipAddress}</td>
		            	<td>
							<fmt:formatDate type="both"  dateStyle="medium" timeStyle="medium"  value="${sysLog.loginTime}" />		            	
		            	</td>
		            </tr>
		         </c:forEach>
             </tbody>
         </table>
         <%@include file="../commons/pagination.jsp"%>
    </div>
</body>