layui.use(['upload','layer'], function(){
	var layer = layui.layer;
	var userId = $("input[name='userId']").val();
	var teamId = $("input[name='teamId']").val();
	var type = $("input[name='type']").val();
	var path = $("input[name='path']").val();
	var path = path.replace(/\//g,'%2F');
	
	layui.upload({
	  url: 'uploadFile/'+userId + "/" + teamId + "/" + type+"/" + path
	  ,success: function(data){
	   layer.msg(data.message);
	  }
	});      
	      
});