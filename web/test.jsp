<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
   	<script src="js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<title>测试</title>
</head>
<body>
<form action="action_locAction_testLocMenu_testadd_testadd" method="post">
	<h1>添加摄像机</h1>
	ID:<input type="text" name="ipc.cameraId"><br>
	IP:<input type="text" name="ipc.cameraIp"><br>
	Port:<input type="text" name="ipc.cameraPort"><br>
	Name:<input type="text" name="ipc.cameraName"><br>
	LoginName:<input type="text" name="ipc.cameraLoginName"><br>
	PWD:<input type="text" name="ipc.cameraPwd"><br>
	NVR:<select name="locs.locId" multiple="multiple">
			<option value="1">大门口</option>
			<option value="2">海康</option>
			<option value="3">华为</option>
		</select><br>
	<button type="submit">提交</button>
</form>
<div id="menuTree"></div>

<script type="text/javascript">
$(function(){
	$("#menuTree").tree({
		url:"json_locAction_testLocMenu",
		loadFilter : function(data) {
			//alert(data);
			var jsonData = eval("(" + data + ")");//转换为json对象  
			return jsonData.success;
		},formatter:function(node){
			
			return node.name=="undefined"?node.cameraName: node.name;
			
		},onBeforeExpand:function(){
			
		},onClick:function(node){
		
			   if(typeof(node.nvrName)=="string"){
					alert(node.nvrName);
 			   }
		} 
 		   
	});
});
</script>
</body>
</html>