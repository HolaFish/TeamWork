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
		<script type="text/javascript" src="static/js/task/task_list.js"></script>
	<style type="text/css">
		body{
			background-color: #eeeeee;
		}
		#query{
			height: 60px;
			border-bottom-color: #bebebe;
			border-bottom-style: solid;
			border-bottom-width: 1px;
		}
		.queryParams {
			width: 400px;
			margin: 10px;
		}
		
		.addproject {
			float: right;
			margin-right: 10px;
		}
		
		.select {
			float: left;
			width: 200px;
		}
		.taskList{
			position: absolute;
			top:82;
			bottom: 0;
			left:0;
			right:0;
			overflow-y:auto;
		}
		.li-task{
		    margin: 0;
		    padding: 0;
		    list-style: none;
		    min-height: 44px;
		    line-height: 44px;
		    cursor: pointer;
		    position: relative;
			border-style: solid;
			border-width: 1px;
			border-color: #d5d5d5;
			background-color: white;
		}
		.li-task:hover{
			background-color: #eee;
		}
		.li-task:hover .tool{
			display: block;
		}
		.task-name{
			float: left;
		}
		.tool{
			float:right;
			display: none;
		}
		.order{
			float: left;
			margin-left: 10px;
			width: 50px;
		}
		.tips{
			width: 100%;
			text-align: center;
			color: #3b3b3b;
		}
		.background{
			background-image:url(static/images/nodata.png);
			background-position: center center; 
			background-repeat: no-repeat;
		}
	</style>
  </head>
  <body>
  	<input name="userId" type="hidden" value="${userId }">
  	<input name="isOngoing" type="hidden" value="${isOngoing }">
  	<input name="teamId" type="hidden" value="${teamId }">
    <div class="container-fluid">
    	<div class="row">
    		<div id="query" class="col-md-12" style="background-color: #eee;">
    			<div class="queryParams">
    				<form class="layui-form">
    					<div class="select">
    						<select name="classify" lay-filter="query">
							  <option value="all" selected>全部</option>
							  <option value="isCharge">负责的任务</option>
							  <option value="partIn">参与的任务</option>
							  <option value="isCreate">创建的任务</option>
							</select> 
    					</div>
	    				<div class="select">
	    					<select name="sort" lay-filter="query">
							  <option value="createTime" selected>按任务创建时间排列</option>
							  <option value="updateTime">按任务更新时间排列</option>
							  <option value="endTime">按任务结束时间排列</option>
							</select>
	    				</div>
						
					</form>
    			</div>
    			<div class="addProject">
    				<button id = "btn_addQuickTask" class="layui-btn">
					  <i class="layui-icon">&#xe608;</i> 添加快速任务
					</button>
    			</div>  
    		</div>
    		<div id="taskList" class="taskList" >
			</div>
  </body>
  </html>