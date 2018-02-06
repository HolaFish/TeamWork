layui.use(['element','layer','form','laydate'], function(){
	var layer = layui.layer;
	var element = layui.element();
	var laydate = layui.laydate;
	var form = layui.form();
	$.ajaxSetup({
		  global: false,
		  type: "POST",
		  dataType:"json",
		  context:"application/json",
		  async:false
		});
	$(".layui-input").focusin(function(){
		$(this).addClass("input_focus");
	});
	$(".layui-input").focusout(function(){
		$(this).removeClass("input_focus");
	});
	
	var userId = $("input[name='memId']").val();
	var teamId = $("input[name='teamId']").val();
	/*
	 * 获取团队，填充团队下拉框
	 */
	$.ajax({
		url:"getMemberPositionInfo.action",
		type:"post",
		data:{id:userId,topTeamId:teamId},
		success:function(data){
			putDataToSelect(data);
			form.render('select');
		}
	});
	
	form.on('submit(modifyMemberInfo)',function(data){
		modifyMember(data.field);
		return false;
	})
});

/**
 * 填充下拉框
 * @param data
 */
function putDataToSelect(data){
	var teams = data.teams;
	var teamOptions = splitJoinSelect(teams);
	$("select[name='team']").html(teamOptions);
	var stations = data.stations;
	if (stations != null){
		var stationOptions = splitJoinSelect(stations);
		$("select[name='station']").html(stationOptions);
	}
	var positions = data.positions;
	if (positions != null){
		var positionOptions = splitJoinSelect(positions);
		$("select[name='position']").html(positionOptions);
	}
	var leaders = data.leaders;
	if (leaders != null){
		debugger;
		var leaderOptions = splitJoinSelect(leaders);
		$("select[name='leader']").html(leaderOptions);
	}
}
/**
 * 拼接下拉框 
 * @param data
 * @returns {String}
 */
function splitJoinSelect(data){
	var hasthis = false;
	var options="";
	for (var i in data){
		if (data[i].ISTHIS == 1){
			options = options + "<option value='"+data[i].ID+"' selected>"+data[i].NAME+"</option>";
			hasthis = true;
		}else{
			options = options + "<option value='"+data[i].ID+"'>"+data[i].NAME+"</option>";
		}
	}
	if (!hasthis){
		options = "<option value=\"\">请选择</option>" + options;
	}
	return options;
}

function modifyMember(data){
	var memId = $("input[name='memId']").val();
	data.id=memId;
	var teamId = $("input[name='teamId']").val();
	var userId = $("input[name='userId']").val();
	data.topTeamId = teamId;
	data.operationBy = userId;
	$.ajax({
		url:"modifyMemberInfo.action",
		data:data,
		success:function(data){
			layer.msg(data.message);
		}
	});
}