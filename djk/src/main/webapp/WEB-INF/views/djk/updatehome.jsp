<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0,minimun-scale=1.0,user-scalable=yes">
<meta name="format-detection" content="telephone=no"/>
<meta name="apple-mobile-web-app-title" content="KeFei">
<title>登录-法律援助系统</title>
<script src="../../../resources/djk/js/jquery.js"></script>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link href="../../../resources/djk/css/base.css" rel="stylesheet" type="text/css">
</head>

<script type="text/javascript">

var upHome={
		customvalidInPassword : function() {//验证新密码
	    		var exist = false;
	    		var inPassword = $("#inPassword").val();
	    		if(inPassword != "") {
	    			$.ajax({
						url: "/validateUserPassword.action",
					    type: "post",
					    dataType: "json", // 使用jsonp格式
					    async: false,
					    data:{"password":inPassword},
					    success: function (data) {
						    if(false == data) {
						    	exist = false;
						    	return false;
						    }
						    	exist = true;
								return true;
						}
					});
	    		}else{
	    			return false;
	    		}
	    		return exist;
	    	},	
	    	customvalidInOldPassword : function() {//验证原始密码
	    		var exist = false;
	    		var inOldPassword = $("#inOldPassword").val();
	    		if(inOldPassword != "") {
	    			$.ajax({
						url: "/validateUserPassword.action",
					    type: "post",
					    dataType: "json", // 使用jsonp格式
					    async: false,
					    data:{"password":inOldPassword},
					    success: function (data) {
						    if(false == data) {
						    	exist = false;
						    	return false;
						    }
						    	exist = true;
								return true;
						}
					});
	    		}else{
	    			return false;
	    		}
	    		return exist;
	    	},	
	    	customvalidConfirmInPassword : function() {//验证确认密码
	    		var exist = false;
	    		var confirmInPassword = $("#confirmInPassword").val();
	    		if(confirmInPassword != "") {
	    			$.ajax({
						url: "/validateUserPassword.action",
					    type: "post",
					    dataType: "json", // 使用jsonp格式
					    async: false,
					    data:{"password":confirmInPassword},
					    success: function (data) {
						    if(false == data) {
						    	exist = false;
						    	return false;
						    }
						    	exist = true;
								return true;
						}
					});
	    		}else{
	    			return false;
	    		}
	    		return exist;
	    	},
		updatePassword : function() {
			
			var inOldPassword = $("#inOldPassword").val();
    		var oldPassword = $("#oldPassword").val();
    		var inPassword = $("#inPassword").val();
    		var confirmInPassword = $("#confirmInPassword").val();
    		var userId = $("#userId").val();
    		
    		
    	   		$.ajax({//验证用户输入原密码与原密码是否一致
					url: "/validatePassword.action",
				    type: "post",
				    dataType: "json", // 使用jsonp格式
				    data:{"inOldPassword":inOldPassword, "oldPassword":oldPassword, "inPassword":inPassword, 
				    "confirmInPassword":confirmInPassword, "userId":userId},
				    success: function (data) {
				   
				    	if(data.message == "2") {
				    		alert("输入密码为空，请重新输入");
							return;
				    	} else if(data.message == "3") {
				    		alert("输入确认密码为空，请重新输入");
							return;
				    	}else if(data.message == "1") {//用户输入原密码与原密码不一致
				    		alert("输入原密码与原密码不一致，请重新输入");
				    		return;
				    	} else if(data.message == "4") {//输入确认密码与密码不一致
				    		alert("输入确认密码与密码不相等，请重新输入");
							return;
				    	} else if(data.message == "TRUE") {
				    		alert("操作成功");
				    		window.location.reload();
				    	}else if(data.message == "ERROR") {
				    		alert("操作失败");
							return;
				    	}
				    }
				});
    		
			
		}
};

function refreshCode(){
	$("#code_img").attr("src","/verify.html?d"+new Date().getTime());
}				
</script>
<body onload="Start()">

<div class="uhome">
	<div class="version">V1.0.0</div>
	<div class="center">
    	<h1>个人中心-法律援助系统</h1>
    	<form  class="editpwd">
    	    <input type="hidden" name="oldPassword" id="oldPassword" value="${user.pwd}">
		 	<input type="hidden" name="userId" id="userId" value="${user.id}">
        	<div id="message" align="center" style="color: red;font-size 16px;"></div>
        	<h3>修改密码</h3>
            <label><i>原密码</i><input type="password" class="input" name="inOldPassword" id="inOldPassword"/></label>
            <label><i>新密码</i><input type="password" class="input" name="inPassword" id="inPassword"/></label>
            <label><i>确认新密码</i><input type="password" class="input" name="confirmInPassword" id="confirmInPassword"/></label>
            <input type="button" value="确定" class="button" onclick="upHome.updatePassword()" />
        </form>
    </div>
</div>

<div class="copyright">&copy;2018 法律援助&nbsp;&nbsp;Power by <a href="http://www.mododata.com" target="_blank">www.mododata.com</a>&nbsp;[<a href="http://www.miibeian.gov.cn/" target="_blank">黑ICP备10000000号</a>]</div>

</body>
</html>
