<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@include file="../commons/head.jsp"%>
<body>

<script>
	//重置密码
	function resetPwd(id){
		$.ajax({
	        type:"POST",
	        url: "${pageContext.request.contextPath}/user/updatePwd",
	        dataType: "json",
	        data:{
	            //请求参数
	        	"id":id
	        },
	        cache:false,
	        success:function(data){
	            if (data) {
	               $('#Mask').css('display','none');
	               alert("重置密码成功");
	               window.location.href = "${pageContext.request.contextPath}/user/list"
	            }else{
	               alert("重置密码失败");
	            }
	        }
	    });
	}
</script>

     <div class="container-fluid">
         <div class="snav"><a href="javascript:void(0);" class="back" onclick="javascript:history.back(-1);">返回</a><a href="javascript:void(0);">系统管理</a><span>&gt;</span><a href="javascript:void(0);">用户管理</a><span>&gt;</span><a href="javascript:void(0);">重置密码</a></div>
		<div class="smenu"><h4>重置密码</h4></div>
			
		<div class="tab">
			<div class="title"><a name="tab1" class="now">基本信息</a></div>
			<div class="content show" name="tab1_content">
				<form class="form">
                	确定重置密码为：djk@190418
					<div class="button_block">
						<input type="button" value="确定" class="style_button" onclick="resetPwd('${userId}');" /><input type="button" value="取消" class="style_button style_button_cancel" onclick="javascript:history.back(-1);" />
					</div>
				</form>
			</div>
		</div> 
    </div>
</body>