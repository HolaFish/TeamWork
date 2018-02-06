
layui.use(['element','layer','form'], function(){
		var layer = layui.layer;
		var form = layui.form();
		var element = layui.element();
		form.on('checkbox(rememberUser)', function(data){
			/**
			 * 当记住用户被选中时，记住密码才可选
			 */
			 if (data.elem.checked){
				 $("#psdCheckbox").removeAttr("disabled");
				 form.render('checkbox');
			 }else{
				 $('#psdCheckbox').prop('checked',false);
				 $("#psdCheckbox").attr("disabled","disabled");
	                 
				 form.render('checkbox');
			 }
			});        
		/**
		 * 登录
		 */
		form.on("submit(login)",function(data){
			layer.load(2);
			login();
			return false;
		});
		/**
		 * 注册
		 */
		form.on("submit(register)",function(data){
			layer.load(2);
			register();
			return false;
		});
		
		/**
		 * 自定义验证
		 */
		form.verify({
			//第二次的密码是否与第一次的密码一致
			passWordTwo: function(value){
				var passWordOne = $("#r_password").val();
				if (passWordOne != value){
					return "两次输入的密码不一样！";
				}
			}

			//我们既支持上述函数式的方式，也支持下述数组的形式
			//数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
			,pass: [
			        /^[\S]{6,12}$/
			        ,'密码必须6到12位，且不能出现空格'
			        ] 
		});      

	});

/**
 * 验证两次密码是否相同
 * @returns {Boolean}
 */
function validatePasswordSame(){
	var pOne = $("input[name='passWord']").val();
	var pTwo = $("input[name='passWordTwo']").val();
	if (pOne!=pTwo){
		var id = $("input[name='passWordTwo']").attr("id");
		layer.tips('两次输入的密码不一致','#'+id,{
			  tips: [1, '#FF5722']});
		return false;
	}
	return true;
}
/**
 * 登录
 */
function login(){
	var data = $("#loginForm").serializeArray();
	$.ajax({
		url:"login.action",
		data:data,
		type:"post",
		success:function(data){
			layer.closeAll("loading");
			if(data.resultType=="0"){
				window.location.href="main/"+data.obj.id;
			}else if (data.resultType=="1"){
				layer.open({content:data.message,title:'警告',icon:0});
			}else{
				layer.open({content:data.message,title:'错误',icon:5});
			}

		}
	});
}
/**
 * 注册
 */
function register(){
	var data = $("#registerForm").serializeArray();
	$.ajax({
		url:"register.action",
		data:data,
		type:"post",
		success:function(data){
			layer.closeAll("loading"); 
			if(data.resultType=="0"){
				layer.open({title:'成功',content:data.message,icon:1});
			}else if (data.resultType=="1"){
				layer.open({title:'警告',content:data.message,icon:0});
			}else{
				layer.open({title:'错误',content:data.message,icon:5});
			}

		}
	});
}


