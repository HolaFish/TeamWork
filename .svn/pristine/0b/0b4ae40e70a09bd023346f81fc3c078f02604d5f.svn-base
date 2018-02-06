/**
 * 	主页面的js
 */
layui.use(['element','layer','form','laydate'], function(){
	var layer = layui.layer;
	var element = layui.element();
	var laydate = layui.laydate;
	var form = layui.form();
});

function editMember(id){
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
		btn: ['提交更改'],
		content: ['redirectToEditMember.action'], //iframe的url
		end: function(){ //此处用于演示
		
		}

	});

}