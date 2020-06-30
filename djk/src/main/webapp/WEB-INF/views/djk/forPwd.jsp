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
            <a href="#"><img class="logo" src="../../resources/djk/images/logo.jpg"></a>
            <span class="hydl">找回密码</span>
        </div>
    </header>
    <div class="banner">
        <div class="uk-container">
            <div class="box">
                <img src="../../resources/djk/images/lust_01.jpg">
               
                    <div class="uk-margin">
                        <div class="uk-inline">
                            <span class="uk-form-icon" uk-icon="icon: user"></span>
                            <input class="uk-input uk-form-width-large" type="text" placeholder="输入用户名" id="name" name="name">
                        </div>
                    </div>
                    <div class="uk-margin">
                        <div class="uk-inline">
                          <span class="uk-form-icon" uk-icon="icon: phone"></span> <input
											class="uk-input uk-form-width-large" type="text" id="phone"
											name="phone" placeholder="手机号"> <a class="yzm"
											id="btn" href="#" onclick="sendemail()">发送验证码</a>
                        </div>
                    </div>
                    <div class="uk-margin">
                        <div class="uk-inline">
                            <span class="uk-form-icon" uk-icon="icon: unlock"></span>
                            <input class="uk-input uk-form-width-large" type="text" placeholder="验证码" id="verifyCode" name="verifyCode">
                        </div>
                    </div>
                    <button class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom"  onclick="sub_login()">下一步</button>
               
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
		var countdown=60; 
		var code = "";
		function sendemail(){
			if($("#phone").val()==""||$("#phone").val()==null){
				alert('请输入手机号！');
				return false;
			}
			//验证手机
			if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test($("#phone").val()))){ 
				  alert("不是完整的11位手机号或者正确的手机号前七位"); 
				  return false; 
			} 

			$.ajax( {
				url : "/sendMsg",
				dataType : "json",
				type : "GET",
				data:{phone:$("#phone").val()},
				success : function(data) {
					if(data.status=="true"){
						code = data.message;
						var obj = $("#btn");
						settime(obj);
					}else{
						alert(data.message);
						return false;
					}
					
				},error:function(r){
					alert(r);
					return false;
				}
			});
			
			
			

		
			
			
		}
		function settime(obj) { //发送验证码倒计时
			if (countdown == 0) { 
				obj.attr('disabled',false); 
				//obj.removeattr("disabled"); 
				obj.html("获取验证码");
				$(".yzm").css("pointer-events","initial");
				$(".yzm").css("color","#2fb6ed");
				countdown = 60; 
				return;
			} else { 
				obj.attr('disabled',true);
				obj.html("重新发送(" + countdown + ")");
				$(".yzm").css("pointer-events","none");
				$(".yzm").css("color","#666");
				countdown--; 
			} 
		setTimeout(function() { 
			settime(obj) }
			,1000) 
		}
		
		
		function sub_login(){
			
			var name = $("#name").val();
			var str = /\w[a-zA-Z0-9_]*/;
			if (name == "" ) {
				 alert("请输入用户名！");
				return false;
			}
			if(!name.match(str)){
				 alert("用户名只能为英文、数字或下划线！");
				return false;
			}
			
			 var verifyCode = $("#verifyCode").val();
			 if (verifyCode == '') {
				alert("请输入验证码！");
				return false;
			}
			
			if (verifyCode.length != 6) {
				alert("请输入6位验证码！");
				return false;
			}
			
			if(verifyCode!=code){
				alert("请输入正确的验证码！");
				return false;
			} 
			 
		
			
			$.ajax( {
				url : "/forgetUser",
				dataType : "json",
				type : "post",
				data:{userName:name},
				success : function(data) {
					if (data.status != "false") {
						
					    location.href = '/forGetPwd1?userId='+data.message;
					}else {
						 alert('用户名不存在');	
					}
				},error:function(data){
					alert('用户名不存在');	
				}
			});
		}
		
		
		
	</script>
</body>
</html>