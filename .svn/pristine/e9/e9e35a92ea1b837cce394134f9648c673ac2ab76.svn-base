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
		body{
			background-color: #eeeeee;
		}
		.my-form-label{
			width:10%;
			float: left;
			margin-left: 20px;
		}
		.my-input-block{
			width: 80%;
			float: left;
			margin-right: 20px;
		}
		.my-form-item{
			margin-left: 10px;
			margin-right: 10px;
			background-color: white;
		}
		.layui-form-item label{
			width: 100px
		}
		.layui-input-inline{
			margin-left: 10px;
		}
		.btn-add{
			cursor: pointer;
		}
		.btn-add:hover{
			color: #1E9FFF;
			
		}
	</style>
  </head>

  <body>
	<div style="position: absolute;top:10px;left:10px;right:10px;bottom: 10px;">
		<form  class="layui-form">
			<div class="layui-form-item">
			    <label class="layui-form-label">项目名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
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
			      <select id="leader" name="leader">
			      	<option value="">请选择负责人</option>
			      </select>
			    </div>
			</div>
			<div class="layui-form-item">
			    <label class="layui-form-label">项目描述</label>
			    <div class="layui-input-block">
			      	<textarea name="description" required  placeholder="请输入项目描述" class="layui-textarea"></textarea>
			    </div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">参与人员</label>
				<div class="layui-input-block">
					<div  id="teamMember" class="layui-collapse">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">起止日期</label>
					<div class="layui-input-inline">
						<input type="text" name="startTime" required lay-verify="required"
							autocomplete="off" value="${baseInfo.entryTime }"
							class="layui-input"
							onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
					</div>
					<div class="layui-form-mid">-</div>
					<div class="layui-input-inline">
						<input type="text" name="endTime" required lay-verify="required"
							autocomplete="off" class="layui-input"
							onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
					</div>
				</div>
			</div>
			<input type="hidden" name="teamId" value="${teamId }">
			<input type="hidden" name="operationBy" value="${userId }">
			<input type="hidden" name="topTeamId" value="${teamId }">
			<button class="layui-btn" lay-submit lay-filter="addProject" style="float: right;margin-right: 20px;">保存</button>
		</form>
	</div>
	  <script type="text/javascript">
	  layui.use(['element','layer','form','laydate','upload'], function(){
			var element = layui.element();
			var form = layui.form();
			var layer = layui.layer;
			//监听提交按钮
			form.on('submit(addProject)',function(data){
				//获取负责团队
				var teamNodes = $('#tree').treeview('getChecked');
				var teams = new Array();
				for (i in teamNodes){
					teams.push(teamNodes[i].id);
				}
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
					url:"addProject.action",
					data:data.field,
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
		
			
		});

		//递归获取所有的结点id
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
		function getTeamMember(id){
			$.ajax({
				url:"getTeamMember.action",
				data:{id:id},
				success:function(data){
					var $team =$('<div class="layui-colla-item"></div>');
					var $leaderSelect = $('<optgroup></optgroup>');
					$leaderSelect.attr('label',data[0].TEAMNAME);
					$leaderSelect.attr('id','select'+data[0].TEAMID);
					var $head = $('<h2 class="layui-colla-title">'+data[0].TEAMNAME+'</h2>');
					var teamid = data[0].TEAMID;
					$team.attr('id',teamid);
					var $items = $('<div class="layui-colla-content"></div>');
					for (i in data){
						$item = $('<input type="checkbox">');
						$item.attr('name','member');
						$item.attr('title',data[i].MEMBERNAME);
						$item.attr('value',data[i].MEMBERID);
						$items.append($item);
						
						$leader = $('<option></option>');
						$leader.attr('value',data[i].MEMBERID);
						$leader.html(data[i].MEMBERNAME);
						$leaderSelect.append($leader);
					}
					$team.append($head);
					$team.append($items);
					$('#teamMember').append($team);
					$('#leader').append($leaderSelect);
					layui.form().render();
					layui.element().init(); 
				}
			});
		}
	</script>
  </body>
</html>
