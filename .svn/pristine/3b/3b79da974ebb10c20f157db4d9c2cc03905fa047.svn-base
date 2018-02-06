layui.use(['layer','form'], function(){
	var layer = layui.layer;
	var form = layui.form();
	var id = $("input[name='userId']").val();
	var isOngoing = $("input[name='isOngoing']").val();
	//绑定添加新项目按钮
	$("#btn_addProject").click(function(){
		showAddView();
	})
	//监听查询下拉框改变
	form.on('select(query)', function(data){
		var isCharge=null;
		var classify = $("select[name='classify']").val();
		var sort = $("select[name='sort']").val();
		if (classify == "isCharge"){
			isCharge="1"
		}else if (classify == "partIn"){
			isCharge="0";
		}
		var teamId = $("input[name='teamId']").val();
		$.ajax({
			url:"getProjectList.action",
			data:{id:id,topTeamId:teamId,orderBy:sort,isCharge:isCharge,isOngoing:isOngoing},
			success:function(data){
				renderingProList(data);
			}
		});
	});
	/*
	 * 获取项目列表
	 */
	
	$.ajax({
		url:"getProjectList.action",
		data:{id:id,orderBy:"createTime",isOngoing:isOngoing},
		success:function(data){
			renderingProList(data);
		}
	});
});

function showAddView(){
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	layer.open({
	      type: 2,
	      title: '添加新项目',
	      shadeClose: true,
	      shade: false,
	      maxmin: false, //开启最大化最小化按钮
	      area: ['60%', '80%'],
	      content: 'redirectToAddProject/'+userId+'/'+teamId
	    });
}

function renderingProList(data){
	$(".projectList").html("");
	$(".projectList").removeClass("background");
	var teamId = $("input[name='teamId']").val();
	if (data.length==0){
		if (!$(".projectList").hasClass("background"))
		$(".projectList").addClass("background");
		return ;
	}
	var userId = $("input[name='userId']").val();
	for (i in data){
		var $item = $("<dl id=\""+i+ "\" class=\"pro-item\"></dl>");
		var $inner = $("<div class=\"inner\"></div>");
		var $content = $("<div class=\"item-content\"></div>");
		var $img = $("<img src=\"static/images/project.png\" class=\"image\"></img>");
		var $name = $("<div class=\"pro-name\"></div>");
		$name.html(data[i].PROJECTNAME);
		var $leader = $("<span class=\"pro-leader\"></span>");
		$leader.html("负责人："+ data[i].LEADER);
		$inner.append($img);
		$content.append($name);
		$content.append("<br>");
		$content.append($leader);
		$inner.append($content);
		$item.html($inner);
		$(".projectList").append($item);
		$item.on('click',function(){
			var order = $(this).attr('id');
			window.location.href='redirectToProTask.action?userId='+userId + "&projectId=" +data[order].PROJECTID + "&projectName="+data[order].PROJECTNAME+"&topTeamId="+teamId; 
		});
	}
}