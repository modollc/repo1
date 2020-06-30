<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../../resources/djk/css/uikit.min.css" />
<script src="../../resources/djk/js/uikit.min.js"></script>
<script src="../../resources/djk/js/uikit-icons.js"></script>
    <title>找回密码</title>
    <style>
        header{
            box-shadow: 5px 4px 15px rgba(0,0,0,.1);
        }
        .sign_in footer .bottom {
            margin: 0;
            padding: 0;
            text-align: center;

        }
        .sign_in footer .bottom::after {
            height: 0px;
        }
        .sign_in footer {
            background: #ffffff;
            padding: 0;
            margin: 0;
        }
        .sign_in footer .bottom p, .sign_in footer .bottom p a  {
            color: #b3b3b3;
        }
        .sign_in .banner{
            width: 100%;
            height: 650px;
            background-size: cover;
            border-bottom: 1px solid #e0e0e0;
        }
        .sign_in .banner .uk-container{
            text-align: center;
        }
        .sign_in .logo_s_c {
            padding: 24px 0 24px 0;
        }
        .sign_in .logo_s_c span{
            font-size: 23px;
            position: relative;
            top: 5px;
            margin: 0 0 0 10px;
            display: inline-block;
        }
        .sign_in footer .bottom p {
            margin: 10px 0 0 0;
        }
        .logo_s_c p{
            float: right;
            margin: 0;
            line-height: 36px;
            margin-top: 8px;
            color: #888787;
            margin-right: 25px;
        }
        .logo_s_c p a{
            color: #fb0000;
        }
        .banner .box{
            display: inline-block;
            width: 410px;
            background: #fff;
            margin: 70px 0 0 0;
            padding: 20px;
        }
        .banner .box form{
            margin: 70px 0 0 0;
        }
        .banner .box form .uk-input {
            height: 45px;
        }
        .banner .box .uk-switcher{
            padding: 35px 25px 25px 25px;
            margin: 0;
        }
        .uk-tab{
            margin: 0;
            display: inline-block;
            width: 100%;
            text-align: center;
            padding: 25px 0 20px 0;
        }
        .uk-tab li{
            display: inline-block;
            margin-left: 0;
            line-height: 10px;
            
        }
        .uk-tab li a{
            font-size: 18px;
        }
        .uk-tab>.uk-active>a {
            color: #494848;
            border-color: transparent;
        }
        .uk-tab::before {
            left: 0px;
        }
        .wjmm{
            text-align: right;
            color: #464646;
            line-height: 1;
            font-size: 13px;
        }
        .wjmm a{
            color: #464646;
        }
        .banner .box .uk-switcher li button{
            font-size: 18px;
            letter-spacing: 5px;
        }
        .ljzc_t{
            padding: 15px 25px 15px 0;
            margin: 0;
            text-align: right;
            border-top: 1px solid #e4e4e4;
        }
        .ljzc_t a{
            color: #cf0900;
            font-size: 15px;
        }
        .uk-inline a.yzm{
            position: absolute;
            right: 5px;
            top: 10px;
            font-size: 13px; 
            color: #2fb6ed;
        }
        .uk-margin {
            margin-bottom: 25px;
        }
        .uk-button-primary {
            background-color: #e2231a;
            height: 50px;
        }
        .uk-button-primary:focus, .uk-button-primary:hover {
            background-color: #ba150d;
        }
        @media screen and (max-width: 1440px) {
            .sign_in footer {
                padding: 0 0 40px 0;
            }
            .sign_in .banner{
                height: calc(100vh - 262px);
            }
        }
    </style>
</head>
<body class="sign_in">
    <header>
        <div class="logo_s_c uk-container">
            <a href="./"><img class="logo" src="../../resources/djk/images/logo.jpg"></a>
            <span class="hydl">找回密码</span>
        </div>
    </header>
    <div class="banner">
        <div class="uk-container">
            <div class="box">
                <img src="../../resources/djk/images/lust_02.jpg">
                    <input class="uk-input uk-form-width-large" type="hidden" id="id" name="id" value="${userId}">
                    <div class="uk-margin">
                        <div class="uk-inline">
                            <span class="uk-form-icon" uk-icon="icon: lock"></span>
                            <input class="uk-input uk-form-width-large" type="password" placeholder="设置6至20位新登录密码" id="pwd1" name="pwd1">
                        </div>
                    </div>
                    <div class="uk-margin">
                        <div class="uk-inline">
                            <span class="uk-form-icon" uk-icon="icon: lock"></span>
                            <input class="uk-input uk-form-width-large" type="password" placeholder="请再次输入新登录密码" id="pwd2" name="pwd2">
                        </div>
                    </div>
                    <button class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom" onclick="sub_login()">下一步</button>
               
            </div>
        </div>
    </div>
    <footer>
        <div class="bottom">
            <div class="uk-container">
                
            </div>
        </div>
        <div class="clearfix"></div>
    </footer>
      <script src="../../resources/djk/js/jquery.min.js"></script>
  
	<script type="text/javascript"> 
		
		
		function sub_login(){
			
			var pwd1 = $("#pwd1").val();
			var pwd2 = $("#pwd2").val();
			if (pwd1 == "" ) {
				 alert("请输入密码！");
				return false;
			}
			if(pwd1.length<6||pwd1.length>20){
			    alert("密码长度为6到20位！");
				return false;
			}
			if (pwd2 == "" ) {
				 alert("请再次输入密码！");
				return false;
			}
			
			if(pwd2!=pwd1){
			  alert("两次密码不相等！");
			  return false;
			}
		
			var userId = $("#id").val();
			$.ajax( {
				url : "/forGetPwd2",
				dataType : "json",
				type : "get",
				data:{userId:userId,pwd:pwd1},
				success : function(data) {
                    if (data.status != "false") {
						
                    	location.href = '/forGetPwd3';
					}else {
						 alert('修改失败！');	
					}
					
				}
			});
		}
		
		
		
	</script>
</body>
</html>