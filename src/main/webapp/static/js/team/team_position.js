layui.use(['element','layer','form','tree'], function(){
	var layer = layui.layer;
	
	var element = layui.element();
	$.ajaxSetup({
		  global: false,
		  type: "POST",
		  dataType:"json",
		  context:"application/json",
		  async:false
		});
	//获取职级列表
	 freshPositionList();
	
});
/**
 * 获取职级列表
 */
function freshPositionList(){
	$.ajax({
		url:"getPosition.action",
		success:function(data){
			romanceList(data);
		}
	});
}
/**
 * 渲染职级列表
 * @param data
 */
function romanceList(data){
	var form = layui.form();
	//清空原来的列表
	$("#list").html("");
	for (var i in data){
		var item = $("<li>" +
				"		<div>" +
				"           <input name=\"id\" type=\"hidden\" value="+data[i].id+"> "+
				"			<input name=\"name\" type=\"text\" class=\"layui-input my_input position\" value="+data[i].name+">" +
				"			<div class=\"my_btnGroup\">" +
				"				<a class=\"my_btn my_btn_delete\"><i class=\"layui-icon\">&#xe640;</i></a> " +
				"				<a class=\"my_btn my_btn_member\"><i class=\"layui-icon\">&#xe612;</i></a>" +
				"			</div>" +
				"		</div>" +
				"	  </li>");
		$("#list").append(item);
	}
	item = $("<li><form id=\"addPositionForm\" class=\"layui-form\">" +
			"	<div class=\"layui-form-item\">" +
			"			<input id=\"newPosition\" name=\"name\"type=\"text\" required lay-verify=\"required\" placeholder=\"请输入职级\"" +
			"				class=\"layui-input my_input\">" +
			"				<div class=\"my_btnGroup\">" +
			"					<a lay-filter=\"addPosition\" lay-submit  class=\"my_btn\"><i class=\"layui-icon\">&#xe608;</i>添加</a>" +
			"				</div>" +
			"	</div></form>" +
			"</li>");
	$("#list").append(item);
	//重新渲染
	form.render();
	//监听添加职级按钮
	form.on('submit(addPosition)', function(data){
		addPosition(data.field);
		return false;
	});
	//监听职级input值变化
	$(".position").change(function(){
		var teamId = $("input[name='teamId']").val();
		var userId = $("input[name='userId']").val();
		//获取修改后的职级名称
		var name = $(this).val();
		//获取职级id
		var id = $(this).siblings('input').val();
		$.ajax({
			url:"modifyPosition",
			data:{id:id,name:name,operationBy:userId,topTeamId:teamId},
			success:function(data){
				layer.msg(data.message);
				if (data.resultType == "0"){
					freshPositionList();
				}
			}
		});
	});
	
	//监听删除按钮
	$(".my_btn_delete").click(function(){
		//获取职级名称
		var name = $(this).parent().parent().children("input[name='name']").val();
		//获取职级id
		var id = $(this).parent().parent().children("input[name='id']").val();
		var teamId = $("input[name='teamId']").val();
		var userId = $("input[name='userId']").val();
		layer.confirm('确定删除 '+name+' ？', {
			  btn: ['是','否'] //按钮
			}, function(){
				
				$.ajax({
					url:"deletePosition.action",
					data:{id:id,operationBy:userId,topTeamId:teamId},
					success:function(data){
						layer.msg(data.message);
						if (data.resultType == "0"){
							freshPositionList();
						}
					}
				});
			}, function(){
				layer.close(layer.index);
			});
		
	});
	
	/*
	 * 监听获取人员列表按钮
	 */
	$('.my_btn_member').click(function(){
		var id = $(this).parent().parent().children("input[name='id']").val();
		$.ajax({
			url:"getMemberByPosition.action",
			data:{id:id},
			success:function(data){
				$("#memberList").siblings(".editdiv").hide();
				$("#memberList").show();
				putDataToTable(data);
			}
		});
	});
}
/**
 * 添加职级
 */
function addPosition(data){
	var teamId = $("input[name='teamId']").val();
	var userId = $("input[name='userId']").val();
	data.topTeamId = teamId;
	data.operationBy = userId;
	$.ajax({
		url:"addPostion.action",
		data:data,
		success:function(data){
			layer.msg(data.message);
			if (data.resultType == "0"){
				freshPositionList();
			}
		}
	});
}
/**
 * 将数据填充到table中
 * @param data
 */
function putDataToTable(data){
	debugger;
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