<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<%@ include file="common/common.jsp"%>
		<style type="text/css">
			body {
				background-color: #eeeeee;
			}
			.form_group {
				background-color: #ffffff;
				border-color: #d5d5d5;
				border-width: 1px;
				border-style: solid;
				margin: 10px;
				padding-top: 20px;
			}
			.layui-input{
					margin-right:5px;
				    border: 0px solid #e6e6e6;
			}
			.layui-input:hover{
				border: 1px solid #e6e6e6;
			}
			.input_focus{
				border: 1px solid #e6e6e6;
			}
			#btn_modifyPsd{
				float: right;
				margin-right: 20px;
			}
		</style>
		<script type="text/javascript">
layui.use(['element','layer','form'], function(){
	var layer = layui.layer;
	var form = layui.form();
	var element = layui.element();
	/*
	 * 设置登录框的垂直位置
	 */
	var height = $(window).height()/2-200;

	$("#mainContainer").css({
		top:height
	});
	//当邮箱框改变时，验证邮箱格式
	$("#email").change(function(){
		var result = validateEmail("email");
		if (result == 1){
			//邮箱为空
			layer.msg('必填项不能为空', {icon: 5, anim:6 });
			$("#email").css({"border-color":"#FD6434"});
		}else if (result == 2){
			//邮箱格式不正确
			layer.msg('邮箱格式不正确', {icon: 5, anim:6 });
			$("#email").css({"border-color":"#FD6434"});
		}{ 
			//邮箱填写正确
			$("#email").css({"border-color":"#E6E6E6" });
			
		}
	});
	/**
	 * 重置密码
	 */
	form.on("submit(getBackPWD)",function(data){
		getBackPWD();
		return false;
	});
	
	/**
	 * 自定义验证
	 */
	form.verify({
		//第二次的密码是否与第一次的密码一致
		passWordTwo: function(value){
			var passWordOne = $("#r_password").val();
			if (passWordOne != value){
				return "两次输入的密码不一样！";
			}
		}

		//我们既支持上述函数式的方式，也支持下述数组的形式
		//数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
		,pass: [
		        /^[\S]{6,12}$/
		        ,'密码必须6到12位，且不能出现空格'
		        ] 
	});      
});

function getValidateCode(time){
	var result = validateEmail("email");
	if (result == 1){
		//邮箱为空
		layer.msg('必填项不能为空', {icon: 5, anim:6 });
		$("#email").css({"border-color":"#FD6434"});
	}else if (result == 2){
		//邮箱格式不正确
		layer.msg('邮箱格式不正确', {icon: 5, anim:6 });
		$("#email").css({"border-color":"#FD6434"});
	}else{
		//邮箱填写正确
		$("#email").css({"border-color":"#E6E6E6"});
		var email = $("#email").val();
		
		$.ajax({
			url:"getValidateCode.action",
			type:"post",
			dateType:"json",
			data:{email:email},
			success:function(data){
				if (data.resultType=="1"){
					layer.msg(data.message, {icon: 0, anim:6 });
				}else if (data.resultType == "2"){
					layer.msg(data.message, {icon: 5, anim:6 });
					
				}else{
					$("#getValidate").addClass("layui-btn-disabled");
					showtime(time);
					$(".newPassWord").removeAttr("disabled");
					$("#btn_modifyPsd").removeClass("layui-btn-disabled");
				}
			}
		});
	}
}
function showtime(t){
	for(var i = 1;i <= t;i++) { 
        window.setTimeout("update_p(" + i + ","+t+")", i * 1000); 
    } 
	
} 
 
function update_p(num , t) { 
    if(num == t) { 
    	document.takeBackPsdFrom.getValidate.value =" 重新发送 "; 
    	$("#getValidate").removeClass("layui-btn-disabled");
    } 
    else { 
        printnr = t-num; 
        document.takeBackPsdFrom.getValidate.value = " (" + printnr +")秒后重新发送"; 
    } 
} 
/*
 * 验证邮箱
 */
function validateEmail(id){
	var value = $("#"+id).val();
	//验证空值
	if (value == null || value == ""){
		return 1;
	}
	//验证邮箱格式
	var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	if (!myreg.test(value)){
		return 2;
	}
	return 0;
}	
/**
 * 重置密码
 */
function getBackPWD(){
	layer.load(2);
	var data = $("#takeBackPsdFrom").serializeArray();
	$.ajax({
		url:"resetPsd.action",
		data:data,
		type:"post",
		success:function(data){
			layer.closeAll("loading"); 
			if(data.resultType=="0"){
				layer.open({title:'成功',content:data.message,icon:1});
			}else{
				layer.open({title:'错误',content:data.message,icon:5});
			}
		}
	});
}

	</script>
	</head>
	<body>
		<div class="container-fluid" style="padding-top:10px;">
			<div class="row">
			<form id="takeBackPsdFrom" name="takeBackPsdFrom" class="layui-form">
					<div class="layui-form-item" style="margin-top: 10px;">
					    <label class="layui-form-label">邮箱</label>
					    <div class="layui-input-block">
					      <input id="email"type="text" name="email" lay-verify="required|email"placeholder="请输入注册邮箱" autocomplete="off" class="layui-input">
					    </div>
					</div>
					<div class="layui-form-item">
					    <label class="layui-form-label">验证码</label>
					    <div class="layui-input-inline" style="margin-left: 30px">
					      <input type="text" name="validateCode" placeholder="请输入验证码" lay-verify="required" autocomplete="off" class="layui-input">
					    </div>
					    <input type="button" id="getValidate"  onclick="getValidateCode(60)" value="获取验证码" class="layui-btn layui-btn-primary">
					</div>
					<div class="layui-form-item" style="margin-top: 10px;">
					    <label class="layui-form-label">密码</label>
					    <div class="layui-input-block">
					      <input id="r_password"type="password" name="newPassWord" placeholder="请输入密码" lay-verify="required"autocomplete="off" class="layui-input newPassWord" disabled="disabled">
					    </div>
					</div>
					<div class="layui-form-item" style="margin-top: 10px;">
					    <label class="layui-form-label">密码</label>
					    <div class="layui-input-block">
					      <input id="passwordTwo"type="password" name="title" placeholder="请确认密码"lay-verify="required|passWordTwo" autocomplete="off" class="layui-input newPassWord" disabled="disabled">
					    </div>
					</div>
					<bottun type="button" id="btn_modifyPsd"  lay-submit="" lay-filter="getBackPWD" class="layui-btn btn_submit  layui-btn-disabled">修改密码</bottun>
				</form>
			</div>
		</div>
	</body>
</html>
