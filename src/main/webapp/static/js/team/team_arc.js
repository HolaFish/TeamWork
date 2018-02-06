layui.use(['element','layer','form','tree'], function(){
	var layer = layui.layer;
	var form = layui.form();
	var element = layui.element();
	$.ajaxSetup({
		  global: false,
		  type: "POST",
		  dataType:"json",
		  context:"application/json",
		  async:false
		});
	//获取团队列表
	freshTeamList();
	//添加团队
	form.on('submit(addTeam)', function(data){
		addTeam();
		return false;
	});
	//修改团队
	form.on('submit(modifyTeam)', function(data){
		
		modifyTeam();
		return false;
	});

});
/**
 * 展示添加团队的div
 * @param parentId 父团队id
 * @param teamLevle 当前级别
 */
function showAddDIV(node){
	//清空原来的数据
	$(".layui-input").val("");
	$(".layui-textarea").html("");
	$("#title").html("添加团队");
	$("#addTeam").siblings(".editdiv").hide();
	$("#addTeam").show();
	if (node != null){
		$("input[name='parentId']").val(node.id);
		$("input[name='teamLevel']").val(parseInt(node.teamLevel)+1);
	}else{
		$("input[name='parentId']").val(null);
		$("input[name='teamLevel']").val(0);
	}
	
	//阻止事件冒泡
	event.stopPropagation();
}
/**
 * 添加团队
 */
function addTeam(){
	var data = $("#addTeamForm").serializeArray();
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	data.push({name:"operationBy",value: userId});
	data.push({name:"topTeamId",value:teamId});
	$.ajax({
		url:"addTeam.action",
		data:data,
		success: function (data){
			if (data.resultType == "0"){
				layer.msg("成功");
				freshTeamList();
			}else{
				layer.msg(data.message);
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
	$("#modifyTeam").siblings(".editdiv").hide();
	$("#modifyTeam").show();
	$("#modifyTeamForm input[name='id']").val(node.id);
	$("#modifyTeamForm input[name='name']").val(node.name);
	$("#modifyTeamForm input[name='teamCode']").val(node.teamCode);
	$("#modifyTeamForm input[name='sort']").val(node.sort);
	$("#modifyTeamForm textarea[name='remark']").val(node.remark);
	var topTeamId = $("input[name='teamId']").val();
	debugger;
	$(".modify_member").each(function(i){
		if (this.value == node.leaderId){
			this.selected = true;
		}else{
			this.selected = false;
		}
	});
	//获取部门列表
	$.ajax({
		url:"getTeamList.aciton",
		data:{topTeamId:topTeamId},
		success:function(data){
			//为下拉菜单填充数据
			putDataToSelect("parentTeam",data,node.parentId);
		}
	});
	//阻止事件冒泡
	event.stopPropagation();
}
/**
 * 填充下拉菜单
 * @param id 下拉菜单节点的id
 * @param data 下拉菜单的内容
 * @param selectId 被选中的id
 */
function putDataToSelect(id,data,selectId){
	var select = "";
	var hasParent = false;
	for (var i in data){
		if (data[i].id==selectId && selectId != null){
			//select 默认选中第一个option
			//将ID和level拼在一起，以便在修改了父团队后，对level进行修改
			select = " <option value='"+data[i].id+"}|{"+data[i].teamLevel+"'>"+data[i].name+"</option> \n" + select;
			hasParent = true;
		}else{
			select = select + " <option value='"+data[i].id+"}|{"+data[i].teamLevel+"'>"+data[i].name+"</option> \n"
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
 * 修改团队
 */
function modifyTeam(){
	var data = $("#modifyTeamForm").serializeArray();
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	data.push({name:"operationBy",value: userId});
	data.push({name:"topTeamId",value:teamId});
	$.ajax({
		url:"modifyTeam.action",
		data:data,
		success:function(data){
			if (data.resultType == "0"){
				layer.msg("成功");
				freshTeamList();
			}else{
				layer.msg(data.message);
			}
		}
	});
}
/**
 * 确认删除
 * @param id
 * @param name
 */
function confirmDelete(node){
	layer.confirm('确定删除 '+node.name+' 及其下属团队？', {
		  btn: ['是','否'] //按钮
		}, function(){
			deleteTeam(node.id);
		}, function(){
			layer.close(layer.index);
		});
	//阻止事件冒泡
	event.stopPropagation();
}
/**
 * 删除团队
 * @param id 
 */
function deleteTeam(id){
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	$.ajax({
		url:"deleteTeam.action",
		data:{id:id,operationBy:userId,topTeamId:teamId},
		success:function(data){
			if (data.resultType == "0"){
				layer.msg("成功");
				freshTeamList();
			}else{
				layer.msg(data.message);
			}
		}
	});
}

function freshTeamList(){
	var topTeamId = $("input[name='teamId']").val();
	//清空列表
	$("#tree").html("");
	//获取团队列表
	$.ajax({
		url:"getTeamTree.action",
		data:{topTeamId:topTeamId},
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
 * 展示队员信息
 * @param node
 */
function showMember(node){
	$("#memberList").siblings(".editdiv").hide();
	$("#memberList").show();
	$.ajax({
		url:"getMember.action",
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