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
	<script type="text/javascript" src="static/js/document/document_list.js"></script>
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
			float: left;
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
		
	
		.background{
			background-image:url(static/images/nodata.png);
			background-position: center center; 
			background-repeat: no-repeat;
		}
		</style>
  </head>
  <body>
  	<input name="userId" type="hidden" value="${userId }">
  	<input name="type" type="hidden" value="${type }">
  	<input name="teamId" type="hidden" value="${teamId }">
  	<input name="path" type = "hidden" value = "${path }">
    <div class="container-fluid">
    	<div class="row">
    		<div id="query" class="col-md-12" style="background-color: #eee;">
    			<div class="queryParams">
    				<span id = "nav" class="layui-breadcrumb">
					</span>
    			</div>
    			<div class="addProject">
					<input type="file" name="uploadFile" class="layui-upload-file">
				</div>
    		</div>
    		<div class="projectList table" >
			</div>
    	</div>
    </div>
    <script type="text/javascript">
    	$(function(){
    		debugger;
    		var path = '${path}';
    		var paths = path.split("/");
    		paths[0] = "root";
    		for (i in paths){
    			var $nav = $("<a></a>");
    			if (paths[i] == "root"){
    				$nav.html("/");
    			}else{
    				$nav.html(paths[i]);
    			}
    			var href = "";
    			for (var j = 0 ; j <= i ; j++){
    				href +=paths[j];
    			}
    			$nav.attr("href","redirectTo${type}Doc/${userId}/${teamId}/"+href);
    			$("#nav").append($nav);
    		}
    	})
    </script>
  </body>
</html>
