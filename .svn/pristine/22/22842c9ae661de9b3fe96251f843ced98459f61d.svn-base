layui.use(['element','layer','form','laydate'], function(){
	var form = layui.form();
	$(".layui-collapse").sortable();
	$(".ul-task").sortable();
	//监听添加分组按钮
	form.on('submit(addGroup)',function(data){
		var projectId = $("input[name='projectId']").val();
		var topTeamId = $("input[name='teamId']").val();
		var operationBy = $("input[name='userId']").val();
		$.ajax({
			url:"addTaskGroup.action",
			data:{projectId:projectId,groupName:data.field.groupName,topTeamId:topTeamId,operationBy:operationBy},
			success:function(data){
				layer.msg(data.message);
				addGroup(data.obj);
			}
		});
		return false;
	})
});

function addGroup(data){
	var $li=$("<li class=\"layui-colla-item\"></li>");
	var $title=$("<div class=\"layui-colla-title\"></div>");
	$title.html(data.name);
	var $tool = $("<div style=\"float: right\"> "
						+"<div class=\"layui-btn-group\">"
						+"	<button id=\"add\" class=\"layui-btn layui-btn-primary layui-btn-small\">"
						+"		<i class=\"layui-icon\">&#xe654;</i>"
						+"	</button>"
						+"	<button id=\"delete\"class=\"layui-btn layui-btn-primary layui-btn-small\">"
						+"		<i class=\"layui-icon\">&#xe640;</i>"
						+"	</button>"
						+"</div>"
					+"</div>");
	$title.append($tool);
	var $taskList  = $("<div class=\"layui-colla-content layui-show\">此分组还没有任务</div>");
	$li.append($title);
	$li.append($taskList);
	$("#add").click(function(){
		showAddView(data.id);
	});
	refreshPage();
}
/**
 * 显示添加任务窗口
 * @param groupId
 */
function showAddView(groupId){
	var userId = $("input[name='userId']").val();
	var projectId = $("input[name='projectId']").val();
	var teamId = $("input[name='teamId']").val();
	layer.open({
	      type: 2,
	      title: '添加新任务',
	      shadeClose: true,
	      shade: false,
	      maxmin: false, //开启最大化最小化按钮
	      area: ['60%', '60%'],
	      content: 'redirectToAddTask/'+userId+"/"+projectId+"/"+groupId+"/"+teamId
	    });
	event.stopPropagation();
}
/**
 * 删除任务分组
 * @param groupId
 */
function deleteTaskGroup(groupId){
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	layer.confirm('确定删除该分组及其任务？', {
		  btn: ['是','否'] //按钮
		}, function(){
			$.ajax({
				url:"deleteTaskGroup.action",
				data:{groupId:groupId,operationBy:userId,topTeamId:teamId},
				success:function(data){
					layer.msg(data.message);
				}
			});
		}, function(){
			layer.close(layer.index);
		});
	//阻止事件冒泡
	event.stopPropagation();
}

function deleteTask(taskId){
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	layer.confirm('确定删除该任务？', {
		  btn: ['是','否'] //按钮
		}, function(){
			$.ajax({
				url:"deleteTask.action",
				data:{taskId:taskId,operationBy:userId,topTeamId:teamId},
				success:function(data){
					layer.msg(data.message);
					refreshPage()
				}
			});
		}, function(){
			layer.close(layer.index);
		});
	//阻止事件冒泡
	event.stopPropagation();
}
/**
 * 展示修改任务弹窗
 */
function showTaskModify(taskId){
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	layer.open({
	      type: 2,
	      title: '修改任务',
	      shadeClose: true,
	      shade: false,
	      maxmin: false, //开启最大化最小化按钮
	      area: ['60%', '60%'],
	      content: 'redirectToModifyTask/'+taskId+"/"+userId +"/" + teamId
	    });
	event.stopPropagation();
}
/**
 * 将任务标记为完成
 * @param taskId
 */
function finishedTask(taskId){
	$.ajax({
		url:"finishedTask.action",
		data:{id:taskId}
	});
	$("#"+taskId).html("&#xe627;");
	$("#"+taskId).parent().attr("onclick","unfinishedTask('"+taskId+"')");
	//阻止事件冒泡
	event.stopPropagation();
}
/**
 * 将任务标记为未完成
 * @param taskId
 */
function unfinishedTask(taskId){
	$.ajax({
		url:"unfinishedTask.action",
		data:{id:taskId}
	});
	$("#"+taskId).html("&#xe626;");
	$("#"+taskId).parent().attr("onclick","finishedTask('"+taskId+"')");
	//阻止事件冒泡
	event.stopPropagation();
}
function refreshPage(){
	location.reload();
}