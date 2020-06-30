<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>

<%@include file="../commons/head.jsp"%>


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">删除</h4><button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">确认要删除？删除后无法恢复！</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="roleDelete();">确定</button><button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>

 <div class="container-fluid">
 
    <div class="snav"><a href="javascript:void(0);" class="back" onclick="javascript:history.back(-1);">返回</a><a href="javascript:void(0);">系统管理</a><span>&gt;</span><a href="javascript:void(0);">系统权限</a></div>
       	<div class="smenu"><h4>系统权限</h4></div>

		<table cellspacing="1" class="table_default">
            <thead>
                <tr>
                	<th class="style1">序号</th>
                    <th>系统权限</th>
                    <th class="style5">操作</th>
            	</tr>
            </thead>
             <tbody>
                 <c:forEach var="r" items="${list}"  varStatus="c" >
		            <tr>
		            	<td class="style1">${requestScope.offset+c.index+1}</td>
		            	<td style="display:none">${r.id}</td>
						<td name="edit">${r.name}</td>
		            	<td class="style5">
		            		<a href="javascript:void(0);" onclick="maintain('${r.id}');"></a>
		            		<shiro:hasPermission name="/system/role/saveRoleRes">
		            		<a href="javascript:void(0);" onclick="distribution('${r.id}','${r.name}');">分配权限</a>
		            		</shiro:hasPermission>
		            		<shiro:hasPermission name="/sys/role/update">
		            		<a href="javascript:void(0);" id="edit${r.id}" onclick="edit('${r.id}');">编辑</a>
		            		</shiro:hasPermission>
		            		<shiro:hasPermission name="/sys/role/deleteById">
		            		<a href="javascript:void(0);" class="del" onclick="delClick('${r.id}');" data-toggle="modal" data-target="#myModal">删除</a>
		            		</shiro:hasPermission>
		            	</td>
		            </tr>
		         </c:forEach>
		         <tr>
					<td class="style1">${list.size()+1}</td>
					<td><input id="newRecord" type="text" value="" class="input"  maxlength='20' onkeydown="javascript:if(event.keyCode==13) add($('#newRecord').val());" />
					<shiro:hasPermission name="/sys/role/add">
					<i onclick='add($("#newRecord").val())'>✔</i>
					</shiro:hasPermission>
					</td>
					<td class="style5"></td>
                </tr>
             </tbody>
         </table>
  </div>  

</body>
<script>
	//table即时修改样式
	function edit(id){
		var thistext = $('#edit'+id).parents("td").parents("tr").children('td').eq(2).html();
		$('#edit'+id).parents("td").parents("tr").children('td').eq(2).html("<input type='text' id='"+id+"' class='input' maxlength='20' value='"+thistext+"' onkeydown='javascript:if(event.keyCode==13) update("+id+");'/><i class='' onclick='update("+id+")'>✔</i>");
	}
	var roleId ;
	//点击删除按钮
	function delClick(id){
		roleId = id;
		$('#Mask').css('display','block');
	};
	//删除弹框,确认删除
	function roleDelete(){
		$.ajax({
            type:"POST",
            url: "${pageContext.request.contextPath}/sys/role/deleteById",
            dataType: "json",
            data: "id="+roleId,
            cache:false,
            success:function(data){
                if (data == 'no_exist') {
                   $('#Mask').css('display','none');
                   window.location.href="/sys/role/list";
                }else if(data == 'exist'){
                	alert("角色下有关联数据，不能删除！");
                	window.location.href="/sys/role/list";
                }else if(data == 'error'){
                	alert("系统异常，请联系管理员！");
                }
            }
        });
	};
	//更新
	function update(id){
		$.ajax({
            type:"POST",
            url: "${pageContext.request.contextPath}/sys/role/update",
            dataType: "json",
            data:{
                //请求参数
            	"id":id,"name":$('#'+id).val()
            }, 
            cache:false,
            success:function(data){
                if (data == 'no_exist') {
                	alert("编辑成功");
                	window.location.href="/sys/role/list";
                }else if(data == 'exist'){
                   alert("角色已存在");
                }else if(data == 'error'){
                	alert("系统异常，请联系管理员！");
                }
            }
        });
	};
	//新增
	function add(value){
		$.ajax({
            type:"POST",
            url: "${pageContext.request.contextPath}/sys/role/add",
            dataType: "json",
            data:{
            	"name":value
            }, 
            cache:false,
            success:function(data){
                if (data == 'no_exist') {
                	alert("新增成功");
                   window.location.href="/sys/role/list";
                }else if(data == 'exist'){
                   alert("新增失败，您输入的角色名称已存在");
                }else if(data == 'error'){
                	alert("系统异常，请联系管理员！");
                }
            }
        });
	}
	//分配权限
	function distribution(id,name){
		window.open ("${pageContext.request.contextPath}/sys/role/role2Res?id="+id+"&rolesName="+name,'newwindow','height=500,width=800,top=100,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no') 
	}
</script>
</html>
