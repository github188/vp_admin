<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">

<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<title>用户登录</title>
<script type="text/javascript">
$(function(){
	function changeCode(){
		$("#showVerifyCode").src=$("#showVerifyCode").src+"?nocache="+new Date().getTime(); 
	}
	
	$("button[type='submit']").click(function(){
		var verifyCode=$("#verifyCode").val();
		var name=$("#name").val();
		var pwd=$("#pwd").val();
		if(name==""){
			$.messager.alert("提示","请输入用户名！");
			return false;
		}
		if(verifyCode==""){
			$.messager.alert("提示","请输入验证码！");
			return false;
		}
	});
});

</script>
</head>
<body>

<div class="container">
	<div class="jumbotron">
   		<h1 class="text-center">可视化泛安防综合监管平台</h1>
      	<div class="panel panel-info">
   			<div class="panel-heading">
      			<h3 class="panel-title text-center">用户登录</h3>
   			</div>
		   	<div class="panel-body">
		   		<form action="userLogin">
		  			<div class="form-group">
		    			<label for="name">用户</label>
		    		<input type="text" name="user.userName" class="form-control" id="name" placeholder="工号">
		  			</div>
		  			<div class="form-group">
		    			<label for="pwd">密码</label>
		    			<input type="password" name="user.password" class="form-control" id="pwd" placeholder="密码">
		  			</div>
		  			<div>
		  				<label for="imgVcode">验证码</label>
		  				<input name="verifyCode" id="verifyCode" type="text" size="4"/> 
		  				<img id="showVerifyCode" src="imagecode" /><a href="" onclick="changeCode()">看不清,换一个</a>
		  			</div>
		  			<div class="checkbox">
		    			<label><input type="checkbox"> 记住
		    			</label>
		  			</div>
		  			<button type="submit" class="btn btn-default">登录</button>
				</form>
		   	</div>
		</div>
      
   </div>
</div>

</body>
</html>