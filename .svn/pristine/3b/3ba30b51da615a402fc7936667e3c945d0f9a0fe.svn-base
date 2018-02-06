layui.use(['element','layer','form','tree'], function(){
	var layer = layui.layer;
	var form = layui.form();
	var element = layui.element();
	freshStationList();
	$.ajaxSetup({
		  global: false,
		  type: "POST",
		  dataType:"json",
		  context:"application/json",
		  async:false
		});
	//绑定添加按钮的监听事件
	form.on('submit(addStation)',function(data){
		addStation(data);
		return false;
	});
	//绑定修改按钮的监听事件
	form.on('submit(modifyStation)',function(data){
		modifyStation(data);
		return false;
	});
});

function freshStationList(){
	var teamId = $("input[name='teamId']").val();
	//清空列表
	$("#tree").html("");
	//获取团队列表
	$.ajax({
		url:"getStationTree.action",
		data:{topTeamId:teamId},
		success:function(data){
			layui.tree({
				elem: '#tree', //传入元素选择器
				nodes:data,
				click:function(node){ //点击节点的回调函数
					showMember(node);
				}
			});
			//添加鼠标悬浮到节点上时，显示按钮
			$("li > a").hover(
					function () {
						$(this).children(".layui-btn-group").css("display","inline"); 
					},
					function (event) {
						$(this).children(".layui-btn-group").css("display","none");
					}
			);
		}
	});
}
/**
 * 展示添加页面
 * @param node
 */
function showAddDIV(node){
	//清空原来的数据
	$(".layui-input").val("");
	$(".layui-textarea").html("");
	$("#title").html("添加岗位");
	$("#addStation").siblings(".editdiv").hide();
	$("#addStation").show();
	if (node != null){
		$("input[name='parentId']").val(node.id);
		$("input[name='stationLevel']").val(parseInt(node.stationLevel)+1);
	}else{
		$("input[name='parentId']").val(null);
		$("input[name='stationLevel']").val(0);
	}
	
	//阻止事件冒泡
	event.stopPropagation();
}
/**
 * 添加岗位
 * @param data
 */
function addStation(data){
	var teamId = $("input[name='teamId']").val();
	var userId = $("input[name='userId']").val();
	data.field.topTeamId = teamId;
	data.field.operationBy = userId;
	$.ajax({
		url:"addStation.action",
		data:data.field,
		success:function(data){
			layer.msg(data.message);
			if (data.resultType == "0"){
				freshStationList();
			}
		}
	});
}

/**
 * 显示修改团队模块
 * @param node 团队对象
 */
function showModifyDIV(node){
	$("#title").html("修改团队");
	$("#modifyStation").siblings(".editdiv").hide();
	$("#modifyStation").show();
	$("#modifyStationForm input[name='id']").val(node.id);
	$("#modifyStationForm input[name='name']").val(node.name);
	$("#modifyStationForm input[name='stationCode']").val(node.stationCode);
	$("#modifyStationForm input[name='sort']").val(node.sort);
	$("#modifyStationForm textarea[name='remark']").val(node.remark);
	//获取岗位列表
	$.ajax({
		url:"getInnerStation.aciton",
		data:{id:node.id},
		success:function(data){
			//为下拉菜单填充数据
			putDataToSelect("parentStation",data,node.parentId);
		}
	});
	//阻止事件冒泡
	event.stopPropagation();
}
/**
 * 填充下来菜单
 * @param id 下拉菜单节点的id
 * @param data 下拉菜单的内容
 * @param selectId 被选中的id
 */
function putDataToSelect(id,data,selectId){
	var select = "";
	var hasParent = false;
	debugger;
	for (var i in data){
		if (data[i].id==selectId && selectId != null){
			//select 默认选中第一个option
			//将ID和level拼在一起，以便在修改了父团队后，对level进行修改
			select = " <option value='"+data[i].id+"}|{"+data[i].stationLevel+"'>"+data[i].name+"</option> \n" + select;
			hasParent = true;
		}else{
			select = select + " <option value='"+data[i].id+"}|{"+data[i].stationLevel+"'>"+data[i].name+"</option> \n"
		}
	}
	if (!hasParent){
		select = "<option value=\"\"></option>" + select;
	}
	select = "<select name=\"parentId\" lay-verify=\"required\">"+select + "</select>"
	$("#"+id).html(select);
	//更新渲染
	layui.form().render('select');
}
/**
 * 修改岗位
 * @param data
 */
function modifyStation(data){
	var teamId = $("input[name='teamId']").val();
	var userId = $("input[name='userId']").val();
	data.topTeamId = teamId;
	data.operatioBy= userId;
	$.ajax({
		url:"modifyStation.action",
		data:data.field,
		success:function(data){
			layer.msg(data.message);
			if (data.resultType == "0"){
				freshStationList();
			}
		}
	});
}
/**
 * 删除岗位
 * @param node
 */
function confirmDelete(node){
	layer.confirm('确定删除 '+node.name+' 及其下属岗位？', {
		  btn: ['是','否'] //按钮
		}, function(){
			deleteStation(node.id);
		}, function(){
			layer.close(layer.index);
		});
	//阻止事件冒泡
	event.stopPropagation();
}

function deleteStation(id){
	var teamId = $("input[name='teamId']").val();
	var userId = $("input[name='userId']").val();
	$.ajax({
		url:"deleteStation.action",
		data:{id:id,topTeamId:teamId,operationBy:userId},
		success:function(data){
			layer.msg(data.message);
			freshStationList();
		}
	});
}
/**
 * 展示人员列表
 * @param node
 */
function showMember(node){
	$("#memberList").siblings(".editdiv").hide();
	$("#memberList").show();
	$.ajax({
		url:"getMemberByStation.action",
		data:{id:node.id},
		success:function(data){
			putDataToTable(data);
		}
	});
}


/**
 * 将数据填充到table中
 * @param data
 */
function putDataToTable(data){
	$("#memberTable tbody").remove();
	var content = "<tbody>";
	if (data.length == 0){
		content = content + "<tr><td class='tabletips' colspan=\"4\">此团队还没有任何成员</tr>"
	}else{
		for (var i in data){
			content = content + "<tr><td><input type=\"hidden\" value=\""+data[i].MEMBERID+"\"><a class=\"member\" style=\"cursor: pointer;\">"+data[i].MEMBERNAME + "</a></td><td>"+data[i].MEMBEREMAIL + "</td>"+
			"<td>"+data[i].TEAMNAME + "</td>";
		}
	}
	
	content = content + "</tbody>";
	$("#memberTable").append(content);
	//为成员姓名绑定点击事件
	$(".member").click(function(){
		var id = $(this).prev().val();
		parent.editMember(id); 
	});
}