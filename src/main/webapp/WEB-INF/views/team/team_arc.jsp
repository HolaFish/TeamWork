<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<script type="text/javascript" src="static/js/team/team_arc.js"></script>
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
	</style>
	
  </head>
  
  <body>
	  <div class="container-fluid">
	  <input type="hidden" name = "teamId" value="${teamId }">
	  <input type="hidden" name = "userId" value="${userId }">
		<div class="row">
			<div class="col-md-3" style="height:100%;">
				<div style="background-color: white;float:left; position: absolute;width:100%; top: 20px;bottom: 10px; border-color: #D5D5D5;border-width:1px;border-style: solid; padding-right: 15px;">
					<div class="title">
						<span >团队列表</span>
					</div>
					<hr>
					<div id="list">
						<ul id="tree"></ul>
					</div>
				</div>
			</div>
			<div  class="col-md-9" style="height:100%;">
				<div id="detail" style="background-color: white;float:left;position: absolute;left:0px; top: 20px;bottom: 10px; right:0px;border-color: #D5D5D5;border-width:1px;border-style: solid;">
					<div class="title">
						<span id="title">成员列表</span>
					</div>
					<hr>
					<div id="editContent">
						<div id="addTeam" class="editdiv">
							<form id="addTeamForm" class="layui-form" >
								<div class="layui-form-item">
								    <label class="layui-form-label">团队名称：</label>
								    <div class="layui-input-block">
								      <input type="text" name="name" required  lay-verify="required" placeholder="请输入团队名称" autocomplete="off" class="layui-input">
								    </div>
								 </div>
								 <div class="layui-form-item">
								    <label class="layui-form-label">团队编码：</label>
								    <div class="layui-input-block">
								      <input type="text" name="teamCode" required  lay-verify="required" placeholder="请输入团队编码" autocomplete="off" class="layui-input">
								    </div>
								 </div>
								 <div class="layui-form-item">
								    <label class="layui-form-label">组长：</label>
								    <div class="layui-input-block">
								      <select name="leaderId" lay-verify="required">
								        <option value=""></option>
								        <c:forEach items="${teamMember }" var = "member">
								        	<option value="${member.MEMBERID }">${member.MEMBERNAME }</option>
								        </c:forEach>
								      </select>
								    </div>
								  </div>
								 <div class="layui-form-item">
								    <label class="layui-form-label">显示顺序：</label>
								    <div class="layui-input-block">
								      <input type="text" name="sort" required  lay-verify="required" placeholder="请输入显示顺序" autocomplete="off" class="layui-input">
								    </div>
								 </div>
								 <div class="layui-form-item layui-form-text">
								    <label class="layui-form-label">备注：</label>
								    <div class="layui-input-block">
								      <textarea name="remark" placeholder="请输入备注" class="layui-textarea"></textarea>
								    </div>
								 </div>
								 <div class="layui-form-item">
								    <div class="layui-input-block">
								      <button class="layui-btn" lay-submit lay-filter="addTeam">立即提交</button>
								      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
								    </div>
								 </div>
								 <input type="hidden" name="parentId">
								 <input type="hidden" name="teamLevel">
							</form>
						</div>
						<div id="modifyTeam" class="editdiv">
							<form id="modifyTeamForm" class="layui-form" >
								<div class="layui-form-item">
								    <label class="layui-form-label">团队名称：</label>
								    <div class="layui-input-block">
								      <input type="text" name="name" required  lay-verify="required" placeholder="请输入团队名称" autocomplete="off" class="layui-input">
								    </div>
								 </div>
								 <div class="layui-form-item">
								    <label class="layui-form-label">团队编码：</label>
								    <div class="layui-input-block">
								      <input type="text" name="teamCode" required  lay-verify="required" placeholder="请输入团队编码" autocomplete="off" class="layui-input">
								    </div>
								 </div>
								 <div class="layui-form-item">
								    <label class="layui-form-label">组长：</label>
								    <div class="layui-input-block">
								      <select name="leaderId" lay-verify="required">
								        <option value=""></option>
								        <c:forEach items="${teamMember }" var = "member">
								        	<option class="modify_member" value="${member.MEMBERID }">${member.MEMBERNAME }</option>
								        </c:forEach>
								      </select>
								    </div>
								  </div>
								 <div class="layui-form-item">
								    <label class="layui-form-label">上级部门：</label>
								    <div id="parentTeam" class="layui-input-block">
								    </div>
								 </div>
								 <div class="layui-form-item">
								    <label class="layui-form-label">显示顺序：</label>
								    <div class="layui-input-block">
								      <input type="text" name="sort" required  lay-verify="required" placeholder="请输入显示顺序" autocomplete="off" class="layui-input">
								    </div>
								 </div>
								 <div class="layui-form-item layui-form-text">
								    <label class="layui-form-label">备注：</label>
								    <div class="layui-input-block">
								      <textarea name="remark" placeholder="请输入备注" class="layui-textarea"></textarea>
								    </div>
								 </div>
								 <div class="layui-form-item">
								    <div class="layui-input-block">
								      <button class="layui-btn" lay-submit lay-filter="modifyTeam">立即提交</button>
								      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
								    </div>
								 </div>
								 <input type="hidden" name="id">
							</form>
						</div>
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
