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
	<%@ include file="../common/common.jsp"%>
	<script type="text/javascript" src="static/js/team/team_main.js"></script>
	<style type="text/css">
		body{
			background-color: #eeeeee;
		}
		.title{
			    font-weight: bold;
			    margin: 10px 10px;
			    color: #666;
		}
		.layui-nav-item {
			padding-left: 10px;
			padding-right: 10px;
		}
		.fa{
			margin-right: 10px;
		}
		.detail{
			margin-left: 200px;
			margin-right: 10px;
		}
	</style>
  </head>
  
  <body>
    <div class="container-fluid">
    	<input type="hidden" name="userId" value="${userId }"/>
    	<input type="hidden" name="teamId" value="${teamId }"/>
		<div class="row" style="bottom:0px;">
			<ul class="layui-nav layui-nav-tree layui-nav-side" lay-filter="test">
				<li class="layui-nav-item my-nav-item"><a href="redirectToTeamArchitecture/${userId }/${teamId}" target="content"><i class="fa fa-sitemap"></i>团队结构</a></li>
				<li class="layui-nav-item my-nav-item"><a href="redirectToInvitePeople/${userId }/${teamId}" target="content"><i class="fa fa-user-plus"></i>邀请列表</a></li>
				<li class="layui-nav-item my-nav-item"><a href="redirectToStation/${userId }/${teamId}" target="content"><i class="fa fa-street-view"></i>岗位设置</a></li>
				<li class="layui-nav-item my-nav-item"><a href="redirectToPosition/${userId }/${teamId}" target="content"><i class="fa fa-id-card-o"></i>职级设置</a></li>
			</ul>
			<div class = "detail">
				<iframe id="mainContent" name="content"  frameBorder=0 scrolling=no style="height:100%" width="100%">
				</iframe>
			</div>
		</div>
	</div>
  </body>
</html>
