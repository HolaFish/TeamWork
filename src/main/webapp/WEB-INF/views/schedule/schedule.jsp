<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="../common/common.jsp"%>
	<link rel="stylesheet" type="text/css" href="static/css/fullcalendar.css">
	<link rel="stylesheet" type="text/css" href="static/css/fancybox.css">
	<style type="text/css">
		#calendar{width:960px; margin:20px auto 10px auto}
		.fancy{width:450px; height:auto}
		.fancy h3{height:30px; line-height:30px; border-bottom:1px solid #d3d3d3; font-size:14px}
		.fancy form{padding:10px}
		.fancy p{height:28px; line-height:28px; padding:4px; color:#999}
		.input{height:20px; line-height:20px; padding:2px; border:1px solid #d3d3d3; width:100px}
		.btn{-webkit-border-radius: 3px;-moz-border-radius:3px;padding:5px 12px; cursor:pointer}
		.btn_ok{background: #360;border: 1px solid #390;color:#fff}
		.btn_cancel{background:#f0f0f0;border: 1px solid #d3d3d3; color:#666 }
		.btn_del{background:#f90;border: 1px solid #f80; color:#fff }
		.sub_btn{height:32px; line-height:32px; padding-top:6px; border-top:1px solid #f0f0f0; text-align:right; position:relative}
		.sub_btn .del{position:absolute; left:2px}
	</style>
	<script src='static/js/fullcalendar.min.js'></script>
	<script src='static/js/jquery.fancybox-1.3.1.pack.js'></script>
	<script type="text/javascript">
	layui.use(['element','layer','form'], function(){
		var layer = layui.layer;
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			events:"getSchedule/${userId}",
			dayClick: function(date, allDay, jsEvent, view) {
				var selDate =$.fullCalendar.formatDate(date,'yyyy-MM-dd HH:mm:ss');
				var height = $(window).height()-10;
				layer.open({
				      type: 2,
				      title: '添加日程',
				      shadeClose: true,
				      shade: false,
				      maxmin: false, //开启最大化最小化按钮
				      area: ['700', '300'],
				      content: ["redirectToAddSchedule/${userId}/"+selDate+"/"+allDay]
				    });
	    	},
	    	eventClick: function(calEvent, jsEvent, view) {
	    		layer.open({
				      type: 2,
				      title: '查看日程',
				      shadeClose: true,
				      shade: false,
				      maxmin: false, //开启最大化最小化按钮
				      area: ['700', '300'],
				      content: ["redirectTocheckSchedule.action?id="+calEvent.id]
				    });
	        }
		});
		
	});
	
	 
	</script>
</head>

<body>
<div id="main" style="width:100%">
   <div id='calendar'></div>
</div>
</body>
</html>
