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
	<script type="text/javascript" src="static/js/team/team_invite.js"></script>
	<style type="text/css">
		body {
			background-color: #eeeeee;
		}
		
		.main-div {
			background-color: white;
			float: left;
			position: absolute;
			width: 98%;
			top: 20px;
			bottom: 10px;
			border-color: #D5D5D5;
			border-width: 1px;
			border-style: solid;
		}
		
		.head {
			margin-top: 10px;
			paddding: 10px;
		
		}
		.tabletips{
			text-align: center;
			color:#ccc;
		}
	</style>
</head>

<body>
	  <div class="container-fluid">
	  <input type="hidden" name="userId"value="${userId }">
		<div class="row">
			<div class="col-md-12" style="height:100%;">
				<div class="main-div">
					<div class="head">
						<div style="margin-left: 10px">
							<form id="inviteForm"class="layui-form">
								<div class="layui-form-item">
									<div style="float: left;padding-left:10px; padding-right: 10px;">
										<input type="text" name="inviteeName" required lay-verify="required"
											placeholder="请输入姓名" autocomplete="off" class="layui-input">
									</div>
									<div style="float: left;padding-right: 10px;">
										<input type="text" name="inviteeEMail" required lay-verify="required|email"
											placeholder="请输入邮箱" autocomplete="off" class="layui-input">
									</div>
									<input type="hidden" name="inviterId"value="${userId }">
									<input type="hidden" name="teamId" value="${teamId }">
									<button class="layui-btn" lay-submit lay-filter="invite">邀请</button>
								</div>
							</form>
						</div>
					</div>
					<div>
						<table id="inviteTable" class="layui-table" lay-skin="line">
							<colgroup>
								<col width="150">
								<col width="300">
								<col width="200">
								<col>
							</colgroup>
							<thead>
								<tr>
									<th>姓名</th>
									<th>邮箱</th>
									<th>邀请时间</th>
									<th>状态</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	  </div>
  </body>
</html>
