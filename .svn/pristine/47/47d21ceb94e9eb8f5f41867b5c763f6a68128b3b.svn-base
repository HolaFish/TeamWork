<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'project_add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="../common/common.jsp"%>
	<script type="text/javascript" src="static/js/project/project_add.js"></script>
	<style type="text/css">
		body{
			background-color: #eeeeee;
		}
		.my-form-label{
			width:10%;
			float: left;
			margin-left: 20px;
		}
		.my-input-block{
			width: 80%;
			float: left;
			margin-right: 20px;
		}
		.my-form-item{
			margin-left: 10px;
			margin-right: 10px;
			background-color: white;
		}
		.layui-form-item label{
			width: 100px
		}
		.layui-input-inline{
			margin-left: 10px;
		}
		.btn-add{
			cursor: pointer;
		}
		.btn-add:hover{
			color: #1E9FFF;
			
		}
	</style>
  </head>

  <body>
	<div style="position: absolute;top:10px;left:10px;right:10px;bottom: 10px;">
		<form id="scheduleForm" class="layui-form">
			<input type="hidden" name="id" value = "${id }">
			<div class="layui-form-item">
			    <label class="layui-form-label">日程描述</label>
			    <div class="layui-input-block">
			      	<textarea name="title" required  placeholder="请输入项目描述" class="layui-textarea">${title }</textarea>
			    </div>
			    
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">起止日期</label>
					<div class="layui-input-inline">
						<input type="text" name="start" required lay-verify="required"
							autocomplete="off" value="${start }"
							class="layui-input"
							onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
					</div>
					<div class="layui-form-mid">-</div>
					<div class="layui-input-inline">
						<input type="text" name="end" 
							autocomplete="off" class="layui-input" value="${end }"
							onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
			    <div class="layui-input-block">
			      <button class="layui-btn" lay-submit lay-filter="modify">修改</button>
			      <button class="layui-btn layui-btn-primary" lay-submit lay-filter="delete">删除</button>
			    </div>
			</div>
		</form>
	</div>
	 
  </body>
  <script type="text/javascript">
	  layui.use(['element','layer','form','laydate','upload'], function(){
			var element = layui.element();
			var form = layui.form();
			var layer = layui.layer;
			//监听提交按钮
			form.on('submit(modify)',function(data){
				$.ajax({
					url:"modifySchedule.action",
					data:data.field,
					success:function(data){
						layer.msg(data.message);
					}
				});
				return false;
			});
			form.on('submit(delete)',function(data){
				layer.confirm('确定删除改日程？', {
					  btn: ['是','否'] //按钮
					}, function(){
						$.ajax({
							url:"deleteSchedule.action",
							data:data.field,
							success:function(data){
								layer.msg(data.message);
							}
						});
					}, function(){
						layer.close(layer.index);
					});
				
				return false;
			});
			
		});
	</script>
</html>
