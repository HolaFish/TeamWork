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
			    <label class="layui-form-label">任务名称</label>
			    <div class="layui-input-block">
			      <input type="text" name="name" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
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
			    <label class="layui-form-label">任务描述</label>
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
			<input type="hidden" name = "userId" value="${userId }">
			<input type="hidden" name = "teamId" value="${teamId }">
			<input type="hidden" name = "createPeople" value="${userId }">
			<input type="hidden" name = "topTeamId" value="${teamId }">
			<input type="hidden" name = "operationBy" value="${userId }">
			<button class="layui-btn" lay-submit lay-filter="addTask" style="float: right;margin-right: 20px;">保存</button>
		</form>
	</div>
	  <script type="text/javascript">
	  layui.use(['element','layer','form','laydate','upload'], function(){
			var element = layui.element();
			var form = layui.form();
			var layer = layui.layer;
			//监听提交按钮
			form.on('submit(addTask)',function(data){
				//获取参与人员
				var members = new Array();
				$("input[name='member']").each(function(){
					if (this.checked){
						members.push(this.value);
					}
				});
				debugger;
				data.field.members = members;
				data.field.project='${projectId}';
				data.field.groupId = '${groupId}';
				$.ajax({
					url:"addQuickTask.action",
					data:data.field,
					success:function(data){
						layer.msg(data.message);
						window.parent.refreshPage();
					}
				});
				return false;
			});
			var teamMembers = ${members};
			for (j in teamMembers){
				var data = teamMembers[j];
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
	</script>
  </body>
</html>
