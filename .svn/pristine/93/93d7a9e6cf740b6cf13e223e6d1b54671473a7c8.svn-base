<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>激活用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="common/common.jsp"%>
	<style type="text/css">
		body{
			background-image: url(static/images/login-background.jpg) ;
			background-size:cover;
			font-size: 75%;
			font-family: Microsoft YaHei,"微软雅黑",simsun,"宋体",Arial;
			}
		.login-container{
			position:relative;
			background-color: #fbfbfb;
			top:100px;
			border-radius:5px 5px 5px 5px;
			border-color:#ddd;
			border-style:solid;
			border-width:1px;
			text-align: center;
			float: left;
		}
		.my-button{
			width: 200px;
			margin-top: 20px;
		}
		.layui-form-label{
			width: 105px;
		}
		p{
			color: #999;
			margin-top: 5px;
			margin-bottom: 5px;
		}
		.my-hide{
			display: none;
		}
		 .layui-form-mid{
		 	padding: 0px;
		 }
	</style>
  </head>

<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4 login-container">
				<label style="margin-top: 30px;">${message }</label>
				<br>
				<div id="active_success">
					 <button id="btn-createTeam"class="layui-btn my-button">创建团队</button>
					 <p>（创建新的团队）</p>
					 <div id="createTeam" class="my-hide">
					 		<form id="modifyTeamForm" class="layui-form" >
					 			<input type="hidden" name="leaderId" value="${userId }">
								<div class="layui-form-item">
								    <label class="layui-form-label">团队名称：</label>
								    <div class="layui-input-block">
								      <input type="text" name="name" required  lay-verify="required" placeholder="请输入团队名称" autocomplete="off" class="layui-input">
								    </div>
								 </div>
								 <button class="layui-btn" lay-submit lay-filter="addTeam">立即提交</button>
							</form>
					 </div>
					 <button id="btn-joinTeam"class="layui-btn my-button">加入团队</button>
					 <p>（加入新的团队）</p>
					 <div id="joinTeam" class="my-hide">
					 	<form id="modifyTeamForm" class="layui-form" >
					 		<div class="layui-form-item">
							    <label class="layui-form-label">团队名称</label>
							    <div class="layui-input-inline">
							      <input type="text" name="teamName" required lay-verify="required" placeholder="请输入团队名称" autocomplete="off" class="layui-input">
							    </div>
							    <div class="layui-form-mid layui-word-aux">  
								    <a id="queryTeam" class="layui-btn layui-btn-primary">
									    <i class="layui-icon">&#xe615;</i>
								  	</a>
							  	</div>
							</div>
					 	</form>
					 	<table id="teamList" class="layui-table my-hide">
						  <colgroup>
						    <col width="150">
						    <col width="200">
						    <col>
						  </colgroup>
						  <thead>
						    <tr>
						      <th>团队</th>
						      <th>负责人</th>
						      <th></th>
						    </tr> 
						  </thead>
						  <tbody>
						    
						  </tbody>
						</table>
					 </div>
				</div>
				<br>
				<a id = "back" href="<%=basePath%>" style="color: #01AAED ; margin-bottom: 20px">返回登陆</a>  	
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
	layui.use(['element','layer','form','tree'], function(){
		var form = layui.form();
		var layer = layui.layer;
		var type = ${type};
		if (type != 0){
			$(".layui-icon").html("&#x1007;");
			$(".layui-icon").css('color','#FF5722');
			$("#back").html("返回");
		}
	
		//监听创建团队按钮
		$("#btn-createTeam").click(function(){
			//展示创建团队表单
			$("#createTeam").show("slow",function(){
				$(this).siblings(".my-hide").hide();
			});
		});
		//监听加入团队按钮
		$("#btn-joinTeam").click(function(){
			//展示搜索团队表单
			$("#joinTeam").show("slow",function(){
				$(this).siblings(".my-hide").hide();
			});
		});
		//监听查找团队按钮
		$("#queryTeam").click(function(){
			var teamName = $("input[name='teamName']").val();
			$.ajax({
				url:"getTeamsByName",
				data:{teamName:teamName},
				success:function(data){
					debugger;
					if (data != null && data.length != 0){
						var tbody = "";
						for (var i in data){
							tbody += "<tr><td>"+data[i].TEAMNAME+"</td><td>" + data[i].LEADERNAME + "</td><td><a onclick=\"applyJoinTeam('${userId}','"+data[i].ID+"')\">加入团队</a>";
						}
						$("tbody").html(tbody);
						$("#teamList").removeClass("my-hide");
					}else{
						$("tbody").html("<tr><td colspan=\"3\">未查找到任何团队！</td></tr>");
					}
				}
			});
		});
		//监听添加团队提交按钮
		form.on('submit(addTeam)',function(data){
			$.ajax({
				url:"addTeam.action",
				data:data.field,
				success:function(data){
					if (data.resultType!="0"){
						layer.msg(data.message);
					}else{
						window.location.href="main/${userId}"
					}
				}
			});
			return false;
		})
	});
	function applyJoinTeam(userId,teamId){
		$.ajax({
			url:"applyJoinTeam.action",
			data:{userId:userId,teamId:teamId},
			success:function(data){
				if(data.resultType=="0"){
					layer.msg(data.message);
				}
			}
		});
	}
	</script>
</html>
