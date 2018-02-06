<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<%@ include file="common/common.jsp"%>
	<!-- 注册登录的js -->
	<script src="static/js/base.js" type="text/javascript"></script>
	<style type="text/css">
		body{
			background-image: url(static/images/login-background.jpg) ;
			background-size:cover;
		}
		.layui-tab-title li{
			width: 50%;
			font-size:16px;
		}
		.login-container{
			position:relative;
			background-color: #fbfbfb;
			height: 350px;
			border-radius:5px 5px 5px 5px;
		}
		.input{
			margin-left:80px
		}
		.btn_submit{
			position: relative;
			bottom: 0;
			width: 100%;
		}
	</style>
	<script type="text/javascript">
		$(function(){
			/*
			 * 设置登录框的垂直位置
			 */
			var height = $(window).height()/2-200;

			$(".login-container").css({
				top:height
			});
			//获取cookie中的用户名
			var userName = $.cookie("loginedUser");
			var passWord = $.cookie("loginedUserPsd");
			if (userName != null){
				$("#login_userName").val(userName);
				$("#userCheckbox").attr("checked",true);
				$("#psdCheckbox").removeAttr("disabled");
				
			}
			if (passWord != null){
				$("#login_passWord").val(passWord);
				$("#psdCheckbox").attr("checked",true);
			}
		});
	</script>
  </head>
  
  <body>
    <div class="container-fluid">
    	<div class="row">
    		<div class="col-md-4"></div>
    		<div id="login" class="login-container col-md-4 col-lg-4 layui-tab  layui-tab-brief" lay-filter="loginAndRegister">
    			<ul class="layui-tab-title">
				    <li class="layui-this">登录</li>
				    <li>注册</li>
				  </ul>
				  <div class="layui-tab-content" style="height:260px">
				  <!-- 登录  -->
				    <div class="layui-tab-item layui-show" >
				    	<form id="loginForm" class='layui-form'>
					    	<div class="layui-form-item" style="margin-top: 20px;">
		    					<label class="layui-form-label">账号：</label>
		    					<div class="layui-input-block input">
		      						<input id="login_userName" type="text" name="username"  lay-verify="required|email" placeholder="请输入用户名或邮箱" autocomplete="off" class="layui-input login_input">
		   						</div>
	  						</div>
	  						<div class="layui-form-item">
		  						<label class="layui-form-label">密码：</label>
		    					<div class="layui-input-block input">
		      						<input id="login_passWord"type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input login_input">
		   						</div>
	   						</div>
	   						<div class="layui-form-item row">
	   							<div class="col-md-3"></div>
								<div class="col-xs-6 col-sm-6 col-md-3">
									<input id="userCheckbox" lay-filter="rememberUser" type="checkbox" name="rememberUser" title="记住用户" >
								</div>
								<div class="col-xs-6 col-sm-6 col-md-3">
									<input id="psdCheckbox" type="checkbox" name="rememberPsd" title="记住密码" disabled="disabled"> 
								</div>
								<div class="col-md-3"></div>
							</div>
	  						<a href="redirectToGetBackPsd.action" style="float:right">忘记密码？</a>
	  						<hr>
	  						<button id="btn_login" lay-submit=""  lay-filter="login" class="layui-btn btn_submit">登录</button> 
  						</form>
  						
				    </div>
				     <!-- 注册  -->
				    <div class="layui-tab-item">
				    	<form id="registerForm" class="layui-form" >
				    		<div class="layui-form-item" style="margin-top: 20px;">
		    					<label class="layui-form-label">邮箱：</label>
		    					<div class="layui-input-block input">
		      						<input id="r_userName" type="text" name="userName"   lay-verify="required|email" placeholder="请输入邮箱地址" autocomplete="off" class="layui-input regist_input">
		   						</div>
	  						</div>
	  						<div class="layui-form-item" style="margin-top: 20px;">
		  						<label class="layui-form-label">密码：</label>
		    					<div class="layui-input-block input">
		      						<input id="r_password" type="password" name="passWord" lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input regist_input">
		   						</div>
	   						</div>
	   						<div class="layui-form-item" style="margin-top: 20px;">
		  						<label class="layui-form-label">密码：</label>
		    					<div class="layui-input-block input">
		      						<input id="r_passwordTwo" type="password" name="passWordTwo"   lay-verify="required|passWordTwo" placeholder="请确认密码" autocomplete="off" class="layui-input regist_input">
		   						</div>
	   						</div>
	   						<hr>
	  						<button id="btn_register" lay-submit="" lay-filter="register" class="layui-btn btn_submit">注册</button>
  						</form>
  						
				    </div>
				    
				  </div>
    		</div>
    		
    		
    		
    	</div>
    </div>
  </body>
</html>
