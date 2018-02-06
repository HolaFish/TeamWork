/**
 * 	主页面的js
 */
layui.use(['element','layer','form'], function(){
	var layer = layui.layer;
	var form = layui.form();
	var element = layui.element();
});

function editMember(id){
	var height = $(window).height()-10;
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	layer.open({
		type: 2,
		title: false,
		closeBtn: 0,
		shadeClose:true,
		shade:[0.1, '#393D49'],
		area: ['440px', height],
		offset: 'r', //右边弹出
		anim: 2,	//动画
		content: ['redirectToEditMember.action?userId='+userId+'&teamMateId='+id+'&topTeamId='+teamId] //iframe的url
	});

}