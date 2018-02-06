<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'project_add.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@ include file="../common/common.jsp"%>
<script type="text/javascript" src="static/js/project/project_add.js"></script>
<style type="text/css">
body {
			background-color: #eeeeee;
			font-family: 'PingFangSC-Light', 'Hiragino Sans GB',
				Microsoft YaHei Light, Microsoft YaHei, Arial, sans-serif;
		}

.my-form-label {
	width: 10%;
	float: left;
	margin-left: 20px;
}

.my-input-block {
	width: 80%;
	float: left;
	margin-right: 20px;
}

.my-form-item {
	margin-left: 10px;
	margin-right: 10px;
	background-color: white;
}

.layui-form-item label {
	width: 100px
}

.layui-input-inline {
	margin-left: 10px;
}

.btn-add {
	cursor: pointer;
}

.btn-add:hover {
	color: #1E9FFF;
}
.project{
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
</style>
</head>

<body>
<body>
	<input name="userId" type="hidden" value="${userId }">
	<input name="projectId" type="hidden" value="${projectId }">
	<input name="teamId" type="hidden" value="${teamId } }">
	<div class="navigation">
		<img class="title-img" src="static/images/project.png">
		<div style="float: left">
			<h2 class="title">${projectName }</h2>
			<ul class="layui-nav">
				<li class="layui-nav-item "><a
					href="redirectToProTask.action?userId=${userId }&projectId=${projectId}&projectName=${projectName}&topTeamId=${teamId}">任务</a></li>
				<li class="layui-nav-item layui-this"><a
					href="redirectToModifyProject.action?userId=${userId }&projectId=${projectId}&projectName=${projectName}&topTeamId=${teamId}">项目信息</a></li>
			</ul>
		</div>
	</div>
	<div class="project">
		<div
			style="position: absolute;top:10px;left:10px;right:10px;bottom: 10px;">
			<form class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label">项目名称</label>
					<div class="layui-input-block">
						<input type="text" name="name" value="${project.name }" required
							lay-verify="required" placeholder="请输入标题" autocomplete="off"
							class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-form-item">
						<label class="layui-form-label">负责团队</label>
						<div class="layui-input-block">
							<div class="layui-collapse">
								<div class="layui-colla-item">
									<h2 class="layui-colla-title">团队列表</h2>
									<div class="layui-colla-content layui-show">
										<div id="tree"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">负责人</label>
					<div class="layui-input-block">
						<select id="leaderid" name="leaderid">
							<option value="">请选择负责人</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">项目描述</label>
					<div class="layui-input-block">
						<textarea name="description" required placeholder="请输入项目描述"
							class="layui-textarea">${project.description }</textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">参与人员</label>
					<div class="layui-input-block">
						<div id="teamMember" class="layui-collapse"></div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">起止日期</label>
						<div class="layui-input-inline">
							<input type="text" name="startTime" required
								lay-verify="required" autocomplete="off"
								value="${project.startTime }" class="layui-input"
								onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
						</div>
						<div class="layui-form-mid">-</div>
						<div class="layui-input-inline">
							<input type="text" name="endTime" required lay-verify="required"
								autocomplete="off" class="layui-input"
								value="${project.endTime }"
								onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
						</div>
					</div>
				</div>
				<input name="teamId" type="hidden" value="${teamId }">
				<input name="topTeamId" type="hidden" value="${teamId }">
				<input name="operationBy" type="hidden" value="${userId }">
				<button class="layui-btn" lay-submit lay-filter="finishedProject"
					style="float: right;margin-right: 20px;">标记为完成</button>
				<button class="layui-btn" lay-submit lay-filter="modifyProject"
					style="float: right;margin-right: 20px;">保存</button>
			</form>
		</div>
	</div>

	<script type="text/javascript">
	  layui.use(['element','layer','form','laydate','upload'], function(){
			var element = layui.element();
			var form = layui.form();
			var layer = layui.layer;
			//监听提交按钮
			form.on('submit(modifyProject)',function(data){
				//获取负责团队
				var teamNodes = $('#tree').treeview('getChecked');
				var teams = new Array();
				for (i in teamNodes){
					teams.push(teamNodes[i].id);
				}
				data.field.id='${project.id}';
				data.field.userId = '${userId}';
				data.field.teams = teams;
				var members = new Array();
				//获取参与人员
				$("input[name='member']").each(function(){
					if (this.checked){
						members.push(this.value);
					}
				});
				data.field.members = members;
				$.ajax({
					url:"modifyProject.action",
					data:data.field,
					success:function(data){
						layer.msg(data.message);
					}
				});
				return false;
			});
			form.on('submit(finishedProject)',function(data){
				
				id='${project.id}';
				
				$.ajax({
					url:"finishedProject.action",
					data:{projectId:id},
					success:function(data){
						layer.msg(data.message);
					}
				});
				return false;
			});
			//团队树
			$('#tree').treeview({
		        data: ${teamTree},//数据
		        showIcon: false,
		        showCheckbox: true,
		        levels:1,
		        onNodeChecked: function(event, node) { //选中节点
		        	//获取选中团队的人员
		            getTeamMember(node.id);
		        	//当节点为展开状态，选中全部子节点
		        	if (node.state.expanded){
		        		var selectNodes = getNodeIdArr(node);//获取所有子节点
			            if(selectNodes){ //子节点不为空，则选中所有子节点
			                $('#tree').treeview('checkNode', [ selectNodes,{ silent: true }]);
			            }
		        		var nodes = node.nodes;
		        		for (i in nodes){
		        			var $existItem = $('#'+nodes[i].id).html();
							if ($existItem == null){//人员列表还没有展示出来
								getTeamMember(nodes[i].id);
							}
		        		}
		        	}
		        },
		        onNodeUnchecked: function (event, node) { //取消选中节点
		        	$("#"+node.id).remove();
		        	$("#select"+node.id).remove();
		        	if (node.state.expanded){
		        		var selectNodes = getNodeIdArr(node);//获取所有子节点
			            if(selectNodes){ //子节点不为空，则取消选中所有子节点
			                $('#tree').treeview('uncheckNode', [ selectNodes,{ silent: true }]);
			            }
		        		var nodes = node.nodes;
		        		for (i in nodes){
		        			$("#"+nodes[i].id).remove();
		        			$("#select"+nodes[i].id).remove();
		        		}
		        	}
		        	layui.form().render();
					layui.element().init(); 
		        }
			});
			//展开所有节点
			$('#tree').treeview('expandAll');
			//选中负责团队
			var teams = ${teams};
			var node = ${teamTree};
			for (var i in teams){
				var nodeId = getNodeIdById(node,teams[i].ID,0);
				//选中节点
				$('#tree').treeview('checkNode', [ nodeId,{ silent: true }]);
				getTeamMember(teams[i].ID);
			}
			layui.form().render();
			layui.element().init(); 
		});
		//递归获取所有的nodeId
		function getNodeIdArr( node ){
		        var ts = [];
		        if(node.nodes){
		            for(x in node.nodes){
		                ts.push(node.nodes[x].nodeId);
		                if(node.nodes[x].nodes){
		                var getNodeDieDai = getNodeIdArr(node.nodes[x]);
		                    for(j in getNodeDieDai){
		                        ts.push(getNodeDieDai[j]);
		                    }
		                }
		            }
		        }else{
		            ts.push(node.nodeId);
		        }
		   return ts;
		}
		//根据id获取nodeid
		function getNodeIdById(node,id,nodeId){
	        for (var i in node){
	        	if (node[i].id==id){
	        		return nodeId
	        	}else if (node[i].nodes){
	        		nodeId++;
	        		result = getNodeIdById(node[i].nodes,id,nodeId);
	        		if (result != null){
	        			return result;
	        		}
	        	}
	        	nodeId++;
	        }
	        return null;
		}
		function getTeamMember(id){
			$.ajax({
				url:"getProjectPartInMemberByTeamId.action",
				data:{projectId:'${projectId}',teamId:id},
				success:function(data){
					if (data.length > 0){
						var leaderId='${project.leaderid}';
						var $leaderSelect = $('<optgroup></optgroup>');
						$leaderSelect.attr('label',data[0].TEAMNAME);
						$leaderSelect.attr('id','select'+data[0].TEAMID);
						var $team =$('<div class="layui-colla-item"></div>');
						var $head = $('<h2 class="layui-colla-title">'+data[0].TEAMNAME+'</h2>');
						var teamid = data[0].TEAMID;
						$team.attr('id',teamid);
						var $items = $('<div class="layui-colla-content layui-show"></div>');
						for (i in data){
							$item = null;
							if (data[i].ISTHIS=="1"){
								$item = $('<input type="checkbox" checked>');
							}else{
								$item = $('<input type="checkbox">');
							}
							$item.attr('name','member');
							$item.attr('title',data[i].MEMBERNAME);
							$item.attr('value',data[i].MEMBERID);
							$items.append($item);
							
							if (data[i].MEMBERID==leaderId){
								$leader = $('<option selected></option>');
							}else{
								$leader = $('<option></option>');
							}
							$leader.attr('value',data[i].MEMBERID);
							$leader.html(data[i].MEMBERNAME);
							$leaderSelect.append($leader);
						}
						$team.append($head);
						$team.append($items);
						$('#teamMember').append($team);
						$('#leaderid').append($leaderSelect);
						layui.form().render();
						layui.element().init(); 
					}
					
				}
			});
		}
	</script>
</body>
</html>
