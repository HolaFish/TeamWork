<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<%@ include file="../common/common.jsp"%>
	<script type="text/javascript" src="static/js/team/team_position.js"></script>
	<style type="text/css">
		body{
				background-color: #eeeeee;
			}
		.title{
			    font-weight: bold;
			    margin: 10px 10px;
			    color: #666;
		}
		.layui-tree li .tree-btn{
			position: absolute;
			right: 15px;
			display: none;
		}
		.layui-tree li a{
			width: 100%;
		}
		.layui-form-item label{
			width: 100px
		}
		.editdiv{
			position: relative;
			display: none;
		}
		.tabletips{
			text-align: center;
			color:#ccc;
		}
		.my_btn{
			cursor: pointer;
		}
		.my_btnGroup{
			float:left;
			height: 38px;
			line-height: 38px;
		}
		.my_input{
			width:80%;
			float: left
		}
		li{
			height: 38px;
			margin-top: 10px;
			margin-left: 5px;
		}
	</style>
	
  </head>
  
  <body>
	  <div class="container-fluid">
	  <input type="hidden" name="userId" value="${userId }"/>
	  <input type="hidden" name="teamId" value="${teamId }"/>
		<div class="row">
			<div class="col-md-3" style="height:100%;">
				<div style="background-color: white;float:left; position: absolute;width:100%; top: 20px;bottom: 10px; border-color: #D5D5D5;border-width:1px;border-style: solid; padding-right: 15px;">
					<div class="title">
						<span >职级结构</span>
					</div>
					<hr>
					<div>
						<ul id="list"></ul>
					</div>
				</div>
			</div>
			<div  class="col-md-9" style="height:100%;">
				<div id="detail" style="background-color: white;float:left;position: absolute;left:0px; top: 20px;bottom: 10px; right:0px;border-color: #D5D5D5;border-width:1px;border-style: solid;">
					<div class="title">
						<span id="title">成员列表</span>
					</div>
					<hr>
					<div id="memberList" class="editdiv">
						<table id="memberTable" class="layui-table" lay-skin="line">
							<colgroup>
								<col width="300">
								<col width="300">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>姓名</th>
									<th>邮箱</th>
									<th>部门</th>
								</tr>
							</thead>
						</table>
					</div>
					</div>
				</div>
			</div>
		</div>
	  </div>
  </body>
</html>
