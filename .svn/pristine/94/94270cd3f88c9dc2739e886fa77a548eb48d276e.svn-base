<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
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
		<script type="text/javascript" src="static/js/team/team_member.js"></script>
		<style type="text/css">
			body {
				background-color: #eeeeee;
			}
			.form_group {
				background-color: #ffffff;
				border-color: #d5d5d5;
				border-width: 1px;
				border-style: solid;
				margin: 10px;
				padding-top: 20px;
			}
			.layui-input{
					margin-right:5px;
				    border: 0px solid #e6e6e6;
			}
			.layui-input:hover{
				border: 1px solid #e6e6e6;
			}
			.input_focus{
				border: 1px solid #e6e6e6;
			}
		</style>
	</head>
	<body>
		<div class="container-fluid" style="padding-top:10px;">
			<input name="userId" type="hidden" value="${userId }"/>
			<input name="memId" type="hidden" value="${memId }"/>
			<input name="teamId" type="hidden" value="${teamId }"/>
			<div class="row">
			<form class="layui-form">
				<div class="form_group">
					<div class="layui-form-item">
						<label class="layui-form-label">姓名:</label>
						<div class="layui-input-block">
							<input type="text" name="name" required lay-verify="required"
								autocomplete="off" class="layui-input" value="${baseInfo.NAME }"> 
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">邮箱:</label>
						<div class="layui-input-block">
							<input type="text" name="email" required lay-verify="required"
								autocomplete="off" class="layui-input" value="${baseInfo.EMAIL }">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">电话:</label>
						<div class="layui-input-block">
							<input type="text" name="telNumber" required lay-verify="required"
								autocomplete="off" class="layui-input" value="${baseInfo.TELNUMBER }">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">性别:</label>
						<c:choose>
						    <c:when test="${baseInfo.GENDER == '0' }">
						        <div class="layui-input-block">
									<input type="radio" name="gender" value="0" title="男" checked> 
									<input type="radio" name="gender" value="1" title="女">
								</div>
						    </c:when>
						    <c:when test="${baseInfo.GENDER == '1' }">
						        <div class="layui-input-block">
									<input type="radio" name="gender" value="0" title="男" > 
									<input type="radio" name="gender" value="1" title="女" checked>
								</div>
						    </c:when>
						    <c:otherwise>
						        <div class="layui-input-block">
									<input type="radio" name="gender" value="0" title="男"> 
									<input type="radio" name="gender" value="1" title="女" >
								</div>
						    </c:otherwise>
						</c:choose>
						
					</div>
				</div>
				<div class="form_group">
					<div class="layui-form-item">
						<label class="layui-form-label" style="width: 100px">入职时间:</label>
						<div class="layui-input-block">
							<input type="text" name="entryTime" required lay-verify="required"
								 autocomplete="off" value="${baseInfo.ENTRYTIME }" class="layui-input"
								 onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">工号:</label>
						<div class="layui-input-block">
							<input type="text" name="employeNum" required lay-verify="required"
								 autocomplete="off" class="layui-input" value="${baseInfo.EMPLOYEENUM }">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">部门:</label>
						<div id="team" class="layui-input-block">
							<select name="team"  >
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">岗位:</label>
						<div class="layui-input-block">
							<select name="station"  >
								<option value=""></option>
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">职级:</label>
						<div class="layui-input-block">
							<select name="position"  >
								<option value=""></option>
								
							</select>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">上级:</label>
						<div class="layui-input-block">
							<select name="leader"  >
								<option value=""></option>
							</select>
						</div>
					</div>
				</div>
				<input lay-submit lay-filter="modifyMemberInfo" type="button" class="layui-btn" value="提交更改" style="float: right;margin: 20px;"/>
			</form>
		</div>
		</div>
	</body>
</html>
