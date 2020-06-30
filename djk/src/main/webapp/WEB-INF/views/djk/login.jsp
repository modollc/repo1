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

var login={
		sub_login:function() {
		
		var name = $("#name").val();
		var str = /\w[a-zA-Z0-9_]*/;
		if (name == "" ) {
			 $("#message").text("");// 清空数据
             $("#message").append("请输入用户名！"); // 添加Html内容，不能用Text 或 Val
			return false;
		}
		if(!name.match(str)){
			 $("#message").text("");// 清空数据
             $("#message").append("用户名只能为英文、数字或下划线！"); // 添加Html内容，不能用Text 或 Val
			return false;
		}
		
		var verifyCode = $("#verifyCode").val();
		if (verifyCode == '') {
			$("#message").text("");// 清空数据
             $("#message").append("请输入验证码！"); // 添加Html内容，不能用Text 或 Val
			return false;
		}
		
		if (verifyCode.length != 4) {
			$("#message").text("");// 清空数据
             $("#message").append("请输入4位验证码！"); // 添加Html内容，不能用Text 或 Val
			return false;
		}
		
		var password = $("#password").val();
		if (password == "" ) {
			 $("#message").text("");// 清空数据
             $("#message").append("请输入密码！"); // 添加Html内容，不能用Text 或 Val
			return false;
		}
		
		$.ajax( {
			url : "/login.html",
			dataType : "json",
			type : "post",
			data:{userName:name,password:password,verifyCode:verifyCode},
			success : function(data) {
				if (data.status != "false") {
						location.href = '/main.html';
				}else {
						$("#message").text("");// 清空数据
             			$("#message").append(data.message); // 添加Html内容，不能用Text 或 Val
						$("#password").val("");
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

<div class="login">
	<div class="version">V1.0.0</div>
	<div class="center">
    	<h1>大健康系统</h1>
    	<form  class="loginform">
        	<div id="message" align="center" style="color: red;font-size 16px;"></div>
        	<h3>用户登录</h3>
            <label><i>用户名</i><input type="text" class="input" name="name" id="name" maxlength='20'/></label>
            <label><i>密&nbsp;&nbsp;&nbsp;&nbsp;码</i><input type="password" class="input" name="password" id="password" maxlength='16'/></label>
            <label><input type="text" class="input1" placeholder="验证码" id="verifyCode" name="verifyCode" maxlength='4'>
            	<img style="cursor:pointer;" src="/verify.html" id="code_img" alt="点击刷新验证码" onclick="refreshCode();"  />
					    				<a href='javascript:void(0);' onclick="refreshCode();">看不清，换一张</a></label>
            <input type="button" value="登录" class="button" onclick="login.sub_login()" />
        </form>
    </div>
</div>

<div class="copyright">&copy;2018 大健康&nbsp;&nbsp;Power by <a href="http://www.mododata.com" target="_blank">www.mododata.com</a>&nbsp;[<a href="http://www.miibeian.gov.cn/" target="_blank">黑ICP备10000000号</a>]</div>

<script src="../../../resources/djk/js/base_djk.js" type="text/javascript" language="javascript"></script>

</body>
</html>
