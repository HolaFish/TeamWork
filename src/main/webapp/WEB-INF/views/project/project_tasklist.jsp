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
	<style type="text/css">
		body {
			background-color: #eeeeee;
			font-family: 'PingFangSC-Light', 'Hiragino Sans GB',
				Microsoft YaHei Light, Microsoft YaHei, Arial, sans-serif;
		}
		
		.navigation {
			width: 100%;
			height: 82px;
			background-color: #fafafa;
		}
		.title-img{
			width: 66px; 
			height: 66px; 
			float: left;
			margin: 8px;
		}
		.title{
			font-size: 16px;
			font-weight: bold;
			margin-left: 20px; 
			margin-top: 5px;
		}
		.layui-this:after {
			background-color: #fb6e52;
		}
		
		.layui-this a {
			color: #fb6e52;
		}
		
		.layui-nav {
			background-color: #fafafa;
		}
		
		.layui-nav .layui-nav-item a {
			color: #3b3b3b;
		}
		
		.layui-nav .layui-nav-item:hover a {
			color: #3b3b3b;
		}
		.taskList{
			position: absolute;
			top:82;
			bottom: 0;
			left:0;
			right:0;
			background-color: #eeeeee;
			border-top-style: solid;
			border-top-width: 2px;
			border-top-color: #d5d5d5;
			overflow-y:scroll;
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
		.layui-colla-item{
			margin-top: 10px;
			margin-bottom: 10px;
		}
		.left-input{
			margin: 15px;
			width: 200px;
			float: left;
		}
		.layui-collapse{
			border-width:0px; 
		}
		.order{
			float: left;
			margin-left: 10px;
			margin-right: 20px;
		}
		.tips{
			width: 100%;
			text-align: center;
			color: #3b3b3b;
		}
		</style>
  </head>
  <body>
  	<input name="userId" type="hidden" value="${userId }">
  	<input name="teamId" type="hidden" value="${teamId }">
  	<input name="projectId" type="hidden" value="${projectId }">
  	<div class="navigation">
  		<img class="title-img" src="static/images/project.png">
  		<div style = "float: left">
  			<h2 class="title">${projectName }</h2>
  			<ul class="layui-nav" >
			 	<li class="layui-nav-item layui-this"><a href="redirectToProTask.action?userId=${userId }&projectId=${projectId}&projectName=${projectName}&topTeamId=${teamId}">任务</a></li>
			  	<li class="layui-nav-item"><a href="redirectToModifyProject.action?userId=${userId }&projectId=${projectId}&projectName=${projectName}&topTeamId=${teamId}">项目信息</a></li>
			</ul>
  		</div>
  	</div>
	<div class="taskList">
		<ul id="groupList" class="layui-collapse">
			<li id="addGroup">
				<form class="layui-form">
					<input   type="text" name="groupName" lay-verify="required" placeholder="新建任务分组" class="layui-input left-input">
					<button class="layui-btn " lay-submit style="float: left;margin-top: 15px;margin-left: -15px" lay-filter="addGroup">
							<i class="layui-icon">&#xe654;</i>
					</button>	
				</form>
			</li>
		</ul>
	</div>
	<script type="text/javascript" src="static/js/project/project_tasklist.js"></script>
	<script type="text/javascript">
	layui.use(['form','element'], function(){
			//渲染任务列表
			var project = ${project};
			var groups = project.taskGroups;
			if (groups.length > 0){
				for (i in groups){
					var tasks = groups[i].tasks;
					var $li = $("<li class=\"layui-colla-item\"></div>");
					var $GroupTitle = $("<div class=\"layui-colla-title\"></div>");
					$GroupTitle.html(groups[i].name+"（"+tasks.length + "）");
					var $tools = $("<div  class=\"layui-btn-group \" style=\"float: right\"></div>");
					var $addTaskBtn = $("<button class=\"layui-btn layui-btn-primary layui-btn-small\" onclick=\"showAddView('"+groups[i].id+"')\"title=\"添加任务\">"
					+"<i class=\"layui-icon\">&#xe654;</i>"
					+"</button>");
					var $deleteBtn = $("<button class=\"layui-btn layui-btn-primary layui-btn-small\" onclick=\"deleteTaskGroup('"+groups[i].id+"')\"title=\"删除分组\">"
							+"<i class=\"layui-icon\">&#xe640;</i>"
							+"</button>");
					$tools.append($addTaskBtn);
					$tools.append($deleteBtn);
					$GroupTitle.append($tools);
					$li.append($GroupTitle);
					
					var $content = $("<div class=\"layui-colla-content layui-show\"></div>");
					if (tasks.length > 0){
						var $ul_Task = $("<ul class=\"ul-task\"></ul>");
						for (j in tasks){
							var $li_Task = $("<li class=\"li-task\" onclick=\"showTaskModify('"+tasks[j].id+"')\"></li>");
							var $order = $("<div class=\"order\">"+(parseInt(j)+1)+"</div>");
							var $taskName = $("<div class=\"task-name\">"+tasks[j].name+"</div>");
							var tools = "<div class=\"tool\">"
									+"<div class=\"layui-btn-group\">";
									if (tasks[j].status == '0'){
										tools = tools + "<button class=\"layui-btn layui-btn-primary layui-btn-small\" title=\"标记为完成\" onclick=\"finishedTask('"+tasks[j].id+"')\">"+
										" <i id='"+tasks[j].id+"'class=\"layui-icon\">&#xe626;</i>";
									}else{
										tools = tools + "<button class=\"layui-btn layui-btn-primary layui-btn-small\" title=\"标记为未完成\" onclick=\"unfinishedTask('"+tasks[j].id+"')\">"+
										" <i id='"+tasks[j].id+"' class=\"layui-icon\">&#xe627;</i>";
									}
									tools = tools +"</button>"
									+" <button class=\"layui-btn layui-btn-primary layui-btn-small\"title=\"删除任务\" onclick=\"deleteTask('"+tasks[j].id+"')\">"
									+" <i class=\"layui-icon\">&#xe640;</i>"
									+" </button>"
									+"</div>"
									+"</div>";
							$tools = $(tools);
							$li_Task.append($order);
							$li_Task.append($taskName);
							$li_Task.append($tools);
							$ul_Task.append($li_Task);
						}
						$content.append($ul_Task);
					}else{
						$content.append("<div class=\"tips\">该分组还没有任务</div>");
					}
					$li.append($content);
					$("#addGroup").before($li);
				}
			}else{
				$("#addGroup").before("<div class=\"tips\">该项目还没有任何任务分组和任务</div>");
			}
			layui.form().render();
			layui.element().init(); 
		});
	</script>
</body>
</html>
