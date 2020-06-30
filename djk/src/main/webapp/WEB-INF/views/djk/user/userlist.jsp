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
	//初始化为科室下拉定义onchange 
	$(document).ready(function(){   
		$('#department').change(function(){   
			var departmentId = $("#department").val();
			$.ajax({
		        type:"POST",
		        url: "${pageContext.request.contextPath}/user/getBusinessByDepartmentId",
		        dataType: "json",
		        data: "departmentId="+departmentId,
		        cache:false,
		        success:function(data){
		        	debugger;
		            if (data) {
		            	var optionstring = "";  
		            	for (var i in data) {  
	                        var jsonObj =data[i];  
	                        optionstring += "<option value=\"" + jsonObj.businessId + "\" >" + jsonObj.businessName + "</option>";  
	                    }  
	                    $("#departmentBusiness").html("<option value=''>请选择...</option> "+optionstring);  
		            }else{
		            }
		        } 
		    });
		})   
	}) 
	//点击删除按钮
	var userId ;
	function delClick(id){
		userId = id;
	}
	
	//删除弹框,确认删除
	function sureDelete(){
		
		$.ajax({
	        type:"POST",
	        url: "${pageContext.request.contextPath}/user/del",
	        dataType: "json",
	        data: "id="+userId,
	        cache:false,
	        success:function(data){
	            if (data) {
	               window.location.reload();
	            }else{
	               alert("删除失败");
	            }
	        }
	    });
	};
	
	//重置密码
	function resetPwd(id){
		window.location.href = "${pageContext.request.contextPath}/user/resetPwd?id="+id;
	}
	
	//复制用户
	function copyUser(id){
		window.location.href = "${pageContext.request.contextPath}/user/copyUser?id="+id;
	}
	
	//编辑用户
	function editUser(id){
		window.location.href = "${pageContext.request.contextPath}/user/editUser?id="+id;
	}
</script>

     <div class="container-fluid">
    	<div class="snav"><a href="javascript:void(0);" class="back" onclick="javascript:history.back(-1);">返回</a><a href="javascript:void(0);">系统管理</a><span>&gt;</span><a href="javascript:void(0);">用户管理</a><span>&gt;</span><a href="javascript:void(0);">用户列表</a></div>
       	<div class="smenu"><h4>用户列表</h4></div>
       	<div class="search">
	        <form class="form" id="search_form" action="/user/list">
			        姓名：<input type="text" class="style_input2" id="q_name" name="q_name">
			        
			        <input type="button" value="查询" onclick="document:search_form.submit();" class="style_search_button" />
	        </form>
        </div>
        
        <table cellspacing="1" class="table_default">
            <thead>
                <tr>
                	<th class="style1">序号</th>
                    <th class="style4">姓名</th>
                    <th class="style4">用户名</th>
                    <th class="style4">操作时间</th>
                    <th class="style5">操作</th>
            	</tr>
            </thead>
			<tbody id="PageData">
                 <c:forEach items="${list}" var="user" varStatus='status'>
		            <tr>
		            	<td style="display:none">${user.id}</td>
		            	<td class="style1">${status.index+1}</td>
		            	<td >${user.name}</td>
		            	<td >${user.userName}</td>
		            	<td>
							<fmt:formatDate type="both"  dateStyle="medium" timeStyle="medium"  value="${user.operateTime}" />		            	
		            	</td>
		            	<td class="style5">
		            	<shiro:hasPermission name="/user/del">
		            		<a href="javascript:void(0);" class="del_renyuan"  onclick="delClick('${user.id}');" data-toggle="modal" data-target="#myModal">删除</a>
		            	</shiro:hasPermission>
		            	<shiro:hasPermission name="/user/resetPwd">
		            		<a href="javascript:void(0);" onclick="resetPwd('${user.id}');" >重置密码</a>
		            	</shiro:hasPermission>
		            	</td>
		            </tr>
		         </c:forEach>
             </tbody>
		</table>       
		<%@include file="../commons/pagination.jsp"%> 
    </div>
</body>
