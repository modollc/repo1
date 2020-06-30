<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"  %>
<%@taglib prefix="shiro" uri="/WEB-INF/tld/shiro.tld" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0,minimum-scale=1.0,user-scalable=yes" />
	<meta name="format-detection" content="telephone=no"/>
	<meta name="apple-mobile-web-app-title" content="KeFei">
	<title>大健康系统</title>
	<link rel="stylesheet" href="../../resources/djk/css/bootstrap.css">
	<link rel="stylesheet" href="../resources/djk/css/djk.css">
	<link rel="icon" href="../resources/djk/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
	<script src="../resources/djk/js/jquery.min.js"></script>
	<script src="../../resources/djk/js/bootstrap.min.js"></script>
</head>
<body onload="Start()">

<nav class="navbar" role="navigation">
	<img src="../../resources/djk/img/logo.png" alt="logo" style="height:45px;margin:5px;" />
</nav>

<ul id="myTab" class="nav nav-tabs">
	<li class="active"><a href="#home" data-toggle="tab">首页</a></li>
	<li><a href="#xitong" data-toggle="tab">系统管理</a></li>
    <div class="pull-right"><div class="logininfo"><time id="time">111</time><a href="/updatehome">${user.name}</a><span>|</span><a href="/exit">退出登录</a></div></div>
</ul>

<div class="container-fluid">

    <div class="row">
        <div class="col-lg-2">
        	<div id="myTabContent" class="tab-content">
	            <div class="tab-pane fade in active" id="home">
                	<div class="tree">
                        <ul>
                            <li>
                                <span>选择一级分类&nbsp;↑</span>
                            </li>
                        </ul>
                    </div>
                </div>
            
                <div class="tab-pane fade" id="xitong">
                    <div class="tree">
                        <ul>
                           <shiro:hasPermission name="/sys/role/list"> 
                            <li>
                                <span><a href="/sys/role/list" target="iframe">系统权限</a></span>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/sys/resource/index"> 
                            <li>
                                <span><a href="/sys/resource/index" target="iframe">系统资源</a></span>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/user">                            
                            <li>
                                <span>用户管理<i class="glyphicon glyphicon-chevron-right"></i></span>
                                <ul>
                                    <li>
                                        <span><a href="/user/list" target="iframe"><em></em>用户列表</a></span>
                                    </li>
                                </ul>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="/log"> 
                            <li>
                                <span><a href="/sys/log/list" target="iframe">系统日志</a></span>
                            </li>
                            </shiro:hasPermission>
                            
                        </ul>
                    </div>
                </div>
	        </div>
        </div>
        <div class="col-lg-10"><iframe class="iframe" name="iframe" src=""></iframe></div>
    </div>

</div>

<div class="container-fluid"><div class="row"><div class="col-lg-12 text-center">&copy;&nbsp;2018&nbsp;&nbsp;Power by <a href="http://www.mododata.com" target="_blank">www.mododata.com</a>&nbsp;[<a href="http://www.miibeian.gov.cn/" target="_blank">黑ICP备10000000号</a>]</div></div></div>

<script type="text/javascript">

$(function(){
	$('.tree li').find(' > ul > li').css("display","none");
	$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', '收起');
	$('.tree li.parent_li > span').on('click', function (e) {
	var children = $(this).parent('li.parent_li').find(' > ul > li');
		if (children.is(":visible")) {
			children.hide('fast');
			$(this).attr('title', '展开').find(' > i').addClass('glyphicon-chevron-right').removeClass('glyphicon-chevron-down');
		} else {
			children.show('fast');
			$(this).attr('title', '收起').find(' > i').addClass('glyphicon-chevron-down').removeClass('glyphicon-chevron-right');
		}
		e.stopPropagation();
	});
});

//二级左侧菜单样式
$(".tree ul li").find("span").click(function(){
	$(".tree ul li").find("span").attr("class", "");
	$(this).attr("class", "now");
});
	
function Start(){ ResizePage();showTime(); };
window.onresize = function(){ ResizePage(); };
$(window).scroll(function () { ResizePage(); });
	
//改变页面宽高
function ResizePage(){$(".iframe").css("height",$(window).height()-153);};
$(document).ready(function(){$(".iframe").css("height",$(window).height()-153);});
	
	
//定义函数：构建要显示的时间日期字符串
function showTime()
{
 var today = new Date();
 var year = today.getFullYear();
 var month = today.getMonth()+1;
 var day = today.getDate();
 var hours = today.getHours();
 var minutes = today.getMinutes();
 var seconds = today.getSeconds();
 month  = month<10  ? "0"+month : month;
 day  = day <10  ? "0"+day : day;
 hours  = hours<10  ? "0"+hours : hours;
 minutes = minutes<10 ? "0"+minutes : minutes;
 seconds = seconds<10 ? "0"+seconds : seconds;
 var str = year+"年"+month+"月"+day+"日 "+hours+":"+minutes+":"+seconds;
 var obj = document.getElementById("time");
 obj.innerHTML = str;
 window.setTimeout("showTime()",1000);
}

//右下角弹出提示及关闭
$(".tips").animate({bottom:"5px"},500);
$(".close").click(function(){$(".tips").animate({bottom:"-185px"},500);});
$(".tips .button").find("a").click(function(){$(".tips").animate({bottom:"-185px"},500);});
</script>

</body>
</html>