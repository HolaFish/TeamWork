/**
 * 
 */
layui.use(['layer','form'], function(){
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	var layer = layui.layer;
	var form = layui.form();
	//监听添加快速任务按钮
	$("#btn_addQuickTask").click(function(){
		showAddView();
	});
	
	/*
	 * 获取任务列表
	 */
	var isOngoing = $("input[name='isOngoing']").val();
	layer.load(2);
	$.ajax({
		url:"getTasks.action",
		data:{userId:userId,topTeamId:teamId,isOngoing:isOngoing,orderBy:"createTime"},
		success:function(data){
			renderTaskList(data);
			layer.close(layer.index);
		}
	});
	//监听查询下拉框改变
	form.on('select(query)', function(data){
		layer.load(2);
		var classify = $("select[name='classify']").val();
		var sort = $("select[name='sort']").val();
		var isCharge=null;
		var isCreate=null;
		switch (classify){
		case "isCharge" :isCharge="1";break;
		case "partIn":isCharge="0";break;
		case "isCreate" : isCreate="1";break;
		}
		$.ajax({
			url:"getTasks.action",
			data:{userId:userId,topTeamId:teamId,isOngoing:isOngoing,isCharge:isCharge,isCreate:isCreate,orderBy:sort},
			success:function(data){
				renderTaskList(data);
				layer.close(layer.index);
			}
		});
	});
	
});
//展示添加快速任务弹窗
function showAddView(){
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	layer.open({
	      type: 2,
	      title: '添加快速任务',
	      shadeClose: true,
	      shade: false,
	      maxmin: false, //开启最大化最小化按钮
	      area: ['60%', '80%'],
	      content: 'redirectToAddQuickTask/'+userId+'/'+teamId
	    });
}
/**
 * 渲染任务列表
 * @param data
 */
function renderTaskList(tasks){
	$("#taskList").html("");
	$(".taskList").removeClass("background");
	var $ul_Task = $("<ul class=\"ul-task\"></ul>");
	if (tasks.length==0){
		if (!$(".taskList").hasClass("background"))
			$(".taskList").addClass("background");
			return ;
	}
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
		layui.form().render();
		layui.element().init(); 
	}
	$("#taskList").append($ul_Task);
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
					setTimeOut(refreshPage(),'3000');
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
	      content: 'redirectToModifyQuickTask/'+taskId+"/"+userId+"/"+teamId
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