<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>团队管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="common/common.jsp"%>
	<script type="text/javascript" src="static/js/main.js"></script>
	<style type="text/css">
		body{
			background-color: #eeeeee;
		}
	</style>
  </head>
  
  <body>
    <div class="container-fluid">
		<div class="row" style="bottom:0px;">
			<div class = "col-md-2" >
				<ul class="layui-nav layui-nav-tree" lay-filter="test"
					style="height:100%;margin-left: -20px;margin-right: -20px">
					<!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
					<li class="layui-nav-item layui-nav-itemed"><a href="javascript:;">团队结构</a></li>
					<li class="layui-nav-item"><a href="javascript:;">邀请列表</a></li>
					<li class="layui-nav-item"><a href="javascript:;">岗位设置</a></li>
					<li class="layui-nav-item"><a href="javascript:;">职级设置</a></li>
				</ul>
			</div>
			<div id="mainContent" class="col-md-10" style="height:100%;margin-left: -40px;margin-right: -20px;">
				<div class="row">
					<div id="list" class="col-md-2" style="height:100%;">
						<div style="background-color: white;float:left; position: absolute;width:100%; top: 20px;bottom: 10px; border-color: #D5D5D5;border-width:1px;border-style: solid; ">
							
						</div>
					</div>
					<div id="detail" class="col-md-10" style="height:100%;">
						<div style="background-color: white;float:left;position: absolute;width:100%; top: 20px;bottom: 10px; border-color: #D5D5D5;border-width:1px;border-style: solid;border-left-width: 0px; "></div>
					</div>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
