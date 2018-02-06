layui.use(['layer','form','laydate'], function(){
	var layer = layui.layer;
	var form = layui.form();
	var laydate = layui.laydate;
	dateOpt = {
		format: 'YYYY-MM-DD', //日期格式
		istime: false, //是否开启时间选择
		isclear: true, //是否显示清空
		istoday: true, //是否显示今天
		choose: function(dates){ //选择好日期的回调
			var newqueryParam = $("select[name='classify']").val();
			var newdate = $("input[name='date']").val();
			$.ajax({
				url:"getLogs.action",
				data:{logType:logType,userId:userId,topTeamId:topTeamId,queryParam:newqueryParam,date:newdate},
				success:function(data){
					renderLogs(data);
				}
			});
		  }
	}
	$('#date').click(function(){
	    dateOpt.elem = this;
	    laydate(dateOpt);
	  });
	//获取日志列表
	var logType = $("input[name='logType']").val();
	var userId = $("input[name='userId']").val();
	var topTeamId = $("input[name='teamId']").val();
	var queryParam = $("select[name='classify']").val();
	var date = $("input[name='date']").val();
	layer.load(2);
	if (logType == "apply"){
		$.ajax({
			url:"getApplyJoinLogs.action",
			data:{userId:userId,topTeamId:topTeamId},
			success:function(data){
				renderLogs(data);
			}
		});
	}else{
		$.ajax({
			url:"getLogs.action",
			data:{logType:logType,userId:userId,topTeamId:topTeamId,queryParam:queryParam,date:date},
			success:function(data){
				renderLogs(data);
			}
		});
	}
	
	//监听查询下拉框改变
	form.on('select(query)', function(data){
		var newqueryParam = $("select[name='classify']").val();
		var newdate = $("input[name='date']").val();
		$.ajax({
			url:"getLogs.action",
			data:{logType:logType,userId:userId,topTeamId:topTeamId,queryParam:newqueryParam,date:newdate},
			success:function(data){
				renderLogs(data);
			}
		});
	});

});


function renderLogs(data){
	$(".logList").html("");
	$(".logList").removeClass("background");
	if (data.length == 0){
		$(".logList").addClass("background");
		layer.close(layer.index);
		return ;
	}
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	for (i in data){
		var $li = $("<div class=\"li-log\"></div>");
		var $discreption = $("<div class=\"discreption\"></div>");
		var userUrl = 'redirectToEditMember.action?userId='+userId+'&teamMateId='+data[i].USERID+'&topTeamId='+teamId;
		var $user = $("<a class=\"operationBy\" class=\"name\" onclick=\"editMember('"+userUrl+"')\"></a>");
		$user.html(data[i].USERNAME);
		var $operation = $("<span class=\"operation\"></span>");
		var operationType = data[i].OPERATIONTYPE;
		var operation = "";
		switch(operationType){
		case "add" : operation ="添加了";break;
		case "modify" : operation = "修改了";break;
		case "delete" : operation = "删除了";break;
		case "apply"  : operation = "申请加入";break; 
		}
		var target = data[i].TARGET;
		var targetUrl = "#";
		switch(target){
		case "Task" : 
			operation +="任务：";
			targetUrl="redirectToModifyTask/"+data[i].TARGETID + "/" + userId + "/" + teamId;
			break;
		case "Quicktask" : 
			operation +="快速任务：";
			targetUrl="redirectToModifyQuickTask/"+data[i].TARGETID + "/" + userId + "/" + teamId;
			break;
		case "Project" :
			operation +="项目：";
			targetUrl = "redirectToModifyProject.action?userId="+userId+"&projectId="+data[i].TARGETID+"&projectName="+data[i].TARGETNAME+"&topTeamId="+teamId;
			break;
		case "Team" : operation +="团队：";break;
		case "Position" : operation +="职级：";break;
		case "Station" : operation +="岗位：";break;
		case "Member" : 
			operation +="成员：";
			targetUrl = 'redirectToEditMember.action?userId='+userId+'&teamMateId='+data[i].TARGETID+'&topTeamId='+teamId
			break;
		}
		$operation.html(operation);
		var $target = null;
		if (target == "Team"|| target == "Position" || target == "Station"|| target == "File"){
			$target = $("<span class=\"target\"></span>");
		}else{
			if (target == 'Task'|| target == "Quicktask"){
				$target = $("<a class=\"target\" onclick=\"showTaskModify('"+targetUrl+"')\"></a>");
			}else if (target == "Project"){
				$target = $("<a class=\"target\" onclick=\"showProjectModify('"+targetUrl+"')\"></a>");
			}else { //Member
				$target = $("<a class=\"target\" onclick=\"editMember('"+targetUrl+"')\"></a>");
			}
		}
		$target.html(data[i].TARGETNAME);
		$discreption.append($user);
		$discreption.append($operation);
		$discreption.append($target);
		
		var $time = $("<div class=\"time\"></div>");
		$time.html(data[i].OPERATIONTIME);
		
		$li.append($discreption);
		if (operationType == "apply"){
			$li.append("<div style=\"margin-left:50px;float:left;\"><a onclick=\"agreeJoin('"+data[i].USERID+"','"+data[i].TARGETID+"')\"class=\"target\">同意</a>|<a onclick=\"disagreeJoin('"+data[i].USERID+"','"+data[i].TARGETID+"')\" class=\"target\">拒绝</a></div>");
		}
		$li.append($time);
		$(".logList").append($li);
		layer.close(layer.index);
	}
}

function editMember(url){
	var height = $(window).height()-10;
	layer.open({
		type: 2,
		title: false,
		closeBtn: 0,
		shadeClose:true,
		shade:[0.1, '#393D49'],
		area: ['440px', height],
		offset: 'r', //右边弹出
		anim: 2,	//动画
		content: [url] //iframe的url
	});
}

function showTaskModify(url){
	layer.open({
	      type: 2,
	      title: '修改任务',
	      shadeClose: true,
	      shade: false,
	      maxmin: false, //开启最大化最小化按钮
	      area: ['60%', '60%'],
	      content: url
	    });
	event.stopPropagation();
}
function showProjectModify(url){
	layer.open({
	      type: 2,
	      title: '修改项目',
	      shadeClose: true,
	      shade: false,
	      maxmin: false, //开启最大化最小化按钮
	      area: ['100%', '100%'],
	      content: url
	    });
	event.stopPropagation();
}

function agreeJoin(userId,teamId){
	$.ajax({
		url:"agreeApplyJoin.action",
		data:{userId:userId,teamId:teamId},
		success:function(data){
			layer.msg(data.message);
		}
	});
}

function disagreeJoin(userId,teamId){
	$.ajax({
		url:"disagreeApplyJoin.action",
		data:{userId:userId,teamId:teamId},
		success:function(data){
			layer.msg(data.message);
		}
	});
}