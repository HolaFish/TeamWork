<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="../common/common.jsp"%>
	<script type="text/javascript" src="static/js/project/project_list.js"></script>
	<style type="text/css">
		body {
			background-color: #eeeeee;
			font-family: 'PingFangSC-Light', 'Hiragino Sans GB',
				Microsoft YaHei Light, Microsoft YaHei, Arial, sans-serif;
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
		
		.projectList {
			padding:20px;
			background-color: #eee;
			position: absolute;
			top: 60px;
			bottom: 1px;
			left: 1px;
			right: 1px;
			overflow-y:auto; 
		}
		
		.pro-item {
			width: 340px;
		    float: left;
		    height: 130px;
		    background-color: #fff;
		    cursor: pointer;
		    border-color: rgba(0,0,0,0);
		    border-width: 1px;
		    border-style: solid;
		    margin: 0 0 20px 20px;
		    padding: 30px;
		    border-color: #ddd;
		    transition: all .15s linear;
		}
		
		.pro-item:hover {
			background: #fff;
			transform: translate3d(0,-3px,0);
			box-shadow: 5px 10px 12px rgba(0,0,0,0.13);
		}
		
		.pro-item:hover .pro-name {
			color: #248bca;
		}
		
		.item-content {
			margin-left: 10px;
			height: 66px;
			line-height: 33px;
		}
		
		.inner {
			vertical-align: middle;
			padding-left: 10%;
		}
		
		.image {
			height: 66px;
			width: 66px;
			float: left;
		}
		
		.pro-name {
			color: #333;
			font-size: 16px;
			float: left;
			margin-left: 20px;
		}
		
		.pro-leader {
			color: #999;
			font-size: 12px;
			margin-left: 20px;
			float:left;
		}
		dt{
			    float: left;
			    height: 66px;
			    width: 66px;
			    line-height: 66px;
			    border-radius: 5px;
			    overflow: hidden;
			    position: relative;
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
							  <option value="isCharge">负责的项目</option>
							  <option value="partIn">参与的项目</option>
							</select> 
    					</div>
	    				<div class="select">
	    					<select name="sort" lay-filter="query">
							  <option value="createTime" selected>按项目创建时间排列</option>
							  <option value="updateTime">按项目更新时间排列</option>
							  <option value="endTime">按项目结束时间排列</option>
							</select>
	    				</div>
						
					</form>
    			</div>
    			<div class="addProject">
    				<button id = "btn_addProject" class="layui-btn">
					  <i class="layui-icon">&#xe608;</i> 添加新项目
					</button>
    			</div>  
    		</div>
    		<div class="projectList table" >
			</div>
    	</div>
    </div>
  </body>
</html>
