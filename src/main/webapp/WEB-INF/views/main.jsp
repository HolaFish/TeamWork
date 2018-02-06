<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    <title>Team Work</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="main page">
	<%@ include file="common/common.jsp"%>
	<script type="text/javascript" src="static/js/common.js"></script>
	<style type="text/css">
		.fa{
			margin-right: 10px;
		}
		.vav-child{
			height: 300px;
			width: 600px;
			padding: 20px;
			background-color: #eeeeee;
			color: black;
		}
	</style>
	<script>
	layui.use(['element','layer','form'], function(){
		  var form = layui.form();
		  var teams = ${teams};
		  var defaultTeamId = '${defaultTeam.id}';
		  for (i in teams){
			  var $options = null;
			  if (teams[i].id==defaultTeamId){
				  $options = $("<option value=\""+teams[i].id+"\" selected>"+teams[i].name+"</option>");
			  }else{
				  $options = $("<option value=\""+teams[i].id+"\" >"+teams[i].name+"</option>");
			  }
			  $("select[name='changeTeam']").append($options);
		  }
		  form.render('select'); 
	  });
		function reinitIframeEND(){  
		  var iframe = document.getElementById("iframepage");  
		  try{  
			  var height = $(window).height()-65;
		      iframe.height = height;  
		  }catch (ex){}  
		  
		  }
		  function iframeResize(){
			  var iframe = document.getElementById("iframepage");  
			  try{  
				  var height = $(window).height()-65;
			      iframe.height = height;  
			  }catch (ex){}  
			  
		  }
		  
	</script>
  </head>
 
<body onresize="iframeResize()">
	<div class="container-fluid">
		<!-- 横向导航栏 -->
		<div class="row">
			<div class="col-md-12" style=" margin-left: -15px;">
				<ul class="layui-nav" lay-filter="" style="margin-right: -30px;">
					<li class="layui-nav-item col-md-2" style="margin-left: -30px; max-width: 210px">
						<div style="line-height: 30px;">
							${userName }
							<br/>
							<small>${defaultTeam.name }</small>
						</div>
						<!--  <dl class="vav-child layui-nav-child">-->
						<dl class="layui-nav-child">
							<!-- 二级菜单 -->
							 <dd><a href="redirectToEditMember.action?userId=${userId}&teamMateId=${userId }&topTeamId=${userId}" target="iframepage">个人设置</a></dd>
							 <dd><a href="redirectToResetPsd.action" target="iframepage">修改密码</a></dd>
						     <dd><a href="quit.action">退出</a></dd>
							<!--  
							<form class="layui-form">
								<div class="layui-form-item">
									<label class="layui-form-label">切换团队</label>
									<div class="layui-input-block">
										<select name="changeTeam" lay-verify="required">
											<option value=""></option>
										</select>
									</div>
								</div>
							</form>-->
						</dl>
					</li>
					<li class="layui-nav-item layui-this"><a href="redirectToTask/${userId }/${defaultTeam.id}" target="iframepage"><i class="fa fa-bars" aria-hidden="true"></i>任务</a></li>
				  	<li class="layui-nav-item "><a href="redirectToProject/${userId }/${defaultTeam.id}" target="iframepage"><i class="fa fa-qrcode" aria-hidden="true"></i>项目</a></li>
				  	<li class="layui-nav-item"><a href="redirectToTeam/${userId }/${defaultTeam.id}" target="iframepage"><i class=" fa fa-group "></i>团队</a></li>
				  	<li class="layui-nav-item"><a href="redirectToCanlendar/${userId }/${defaultTeam.id}" target="iframepage"><i class=" fa fa-calendar "></i>日程安排</a></li>
				  	<li class="layui-nav-item"><a href="redirectToDynamic/${userId }/${defaultTeam.id}" target="iframepage"><i class=" fa fa-book "></i>团队工作动态</a></li>
				</ul>
			</div>
			<br>
			<iframe src="redirectToTask/${userId }/${defaultTeam.id}"id="iframepage" name="iframepage" frameBorder=0 scrolling=no width="100%" onLoad="reinitIframeEND();" ></iframe>
		</div>
		
		</div>
	</div>
     
</body>
</html>
