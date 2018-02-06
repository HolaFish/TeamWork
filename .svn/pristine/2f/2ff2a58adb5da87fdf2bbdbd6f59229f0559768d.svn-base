<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dynamic_logList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="../common/common.jsp"%>
	<script type="text/javascript" src="static/js/dynamic/dynamic_logList.js"></script>
	
	<style type="text/css">
		body {
			background-color: #eeeeee;
			font-family: 'PingFangSC-Light', 'Hiragino Sans GB',
				Microsoft YaHei Light, Microsoft YaHei, Arial, sans-serif;
		}
		a{
			cursor: pointer;
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
		.logList {
			padding:20px;
			background-color: #eee;
			position: absolute;
			top: 60px;
			bottom: 1px;
			left: 1px;
			right: 1px;
			overflow-y:auto; 
		}
		.li-log{
		    margin: 0 20px 20px 70px;
		    border: 1px solid #d5d5d5;
		    background: #fff;
		    position: relative;
		    border-radius: 3px;
		    height: 50px;
		    line-height: 50px;
		}
		.li-log:before{
			position: absolute;
		    content: "";
		    height: 9px;
		    width: 10px;
		    background-color: #fff;
		    border-style: solid;
		    border-color: #d5d5d5;
		    border-width: 1px 0 0 1px;
		    -webkit-transform: rotate(-45deg);
		    -moz-transform: rotate(-45deg);
		    -ms-transform: rotate(-45deg);
		    transform: rotate(-45deg);
		    top: 12px;
		    left: -6px;
		}
		.li-log:hover{
			background-color: #eee;
		}
		.discreption{
			float:left;
			margin-left: 20px;
		}
		.time{
			float: right;
			margin-right: 20px;
			color: #999;
		    font-size: 12px;
		}
		.name{
			color: #666;
			font-weight: bold;
		}
		.operation{
			color: #999;
		    font-size: 12px;
		    margin-left: 8px;
		}
		.target{
		    background: transparent;
		    color: #5d9cec;
		    text-decoration: none;
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
  	<input name="logType" type="hidden" value="${logType }">
  	<input name="teamId" type="hidden" value="${teamId }">
    <div class="container-fluid">
    	<div class="row">
    		<div id="query" class="col-md-12" style="background-color: #eee;">
    			<div class="queryParams">
    				<form class="layui-form">
    					<div class="select">
    						<select name="classify" lay-filter="query">
							  <option value="all" selected>全部</option>
							  <option value="myself">我自己的</option>
							  <option value="subordinates">下属的</option>
							</select> 
    					</div>
    					<div class="layui-inline">
						  <input id ="date" name ="date" class="layui-input" placeholder="日期" >
						</div>
					</form>
    			</div>
    		</div>
    		<div class="logList">
    			
			</div>
    	</div>
    </div>
  </body>
</html>
