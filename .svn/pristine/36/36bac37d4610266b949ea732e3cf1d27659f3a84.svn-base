layui.use(['element','layer','form','tree'], function(){
	var layer = layui.layer;
	var form = layui.form();
	var element = layui.element();
	/**
	 * 监听邀请按钮
	 */
	form.on('submit(invite)',function(data){
		invitePeople(data.field);
	
		return false;
	});
	
	/**
	 * 监听团队下拉框选中
	 */
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	getInviteList(userId,teamId);
		      
});

/**
 * 填充下来菜单
 * @param id 下拉菜单节点的id
 * @param data 下拉菜单的内容
 */
function putDataToSelect(id,data){
	var select = "";
	for (var i in data){
		select = select + " <option value='"+data[i].id+"'>"+data[i].name+"</option> \n"
	}
	select = "<option value=\"\">请选择团队</option>" + select;
	select = "<select name=\"team\" lay-verify=\"required\" lay-filter=\"teamlist\">"+select + "</select>"
	$("#"+id).html(select);
	//更新渲染
	layui.form().render('select');
}
/**
 * 邀请成员加入团队
 */
function invitePeople(data){
	layer.load(1, {
		  shade: [0.1,'#fff'] //0.1透明度的白色背景
		});
	$.ajax({
		url:"invitePeople.action",
		data:data,
		success:function(data){
			layer.close(layer.index);
			layer.msg(data.message);
		}
	});
}
/**
 * 获取邀请列表
 * @param id 
 */
function getInviteList(userId,teamId){
	$.ajax({
		url:"getInviteList.action",
		data:{teamId:teamId,inviterId:userId},
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
	debugger;
	$("#inviteTable tbody").remove();
	var content = "<tbody>";
	if (data.length == 0){
		content = content + "<tr><td class='tabletips' colspan=\"4\">你还没有为此团队邀请过成员</tr>"
	}else{
		for (var i in data){
			content = content + "<tr><td>"+data[i].INVITEENAME + "</td><td>"+data[i].INVITEEEMAIL + "</td>"+
			"<td>"+data[i].INVITETIME + "</td>";
			if (data[i].INVITESTATUS == 0){
				content = content + "<td>邀请已发出</td></tr>"  
			}else{
				content = content + "<td>已接受</td></tr>"  
			}
		}
	}
	
	content = content + "</tbody>";
	$("#inviteTable").append(content);
}