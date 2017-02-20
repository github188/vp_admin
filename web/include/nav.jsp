<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<link rel="icon" type="image/x-icon" href="images/icon.ico" />    
	<link rel="shortcut icon" type="image/x-icon" href="images/icon.ico" />    
	<link rel="bookmark" type="image/x-icon" href="images/icon.ico" />

   <link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
   <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
   <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
   <!-- jquery模板 -->
	<script src="js/jquery/jquery.tmpl.js" type="text/javascript"></script>
	
	<script type="text/javascript" src="js/easyui/jquery.ittc.js"></script>
<style type="text/css">
body { padding-top: 70px; }
</style>
   <script type="text/javascript">
   $(function(){
	   
	   
	   /**
	   $(".ittcnav").children("li").click(function(){
		   $(".active").removeClass("active");
		 	var clazz=$(this).attr("class");
		 	if(url.indexOf(clazz)!=-1){
		 		
		   }
	   });
	   **/
	   var url=window.location.href;
	   var menu=["home","preview","devmgr","usermgr","syscfg","log"];
	   $.each(menu,function(i,v){
		   if(url.indexOf(v)!=-1){
			   $("."+v).addClass("active");
		   }
	   });
	   
   });
   
   </script>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
   <div class="navbar-header">
   		<a class="navbar-brand" href="#">ITTC</a>
   		<!-- 
   		<img alt="可视化泛安防综合监管平台" src="images/logo.png" width="80px" height="80px">
      	 -->
   </div>
   <div>
   		<ul class="nav nav-pills  navbar-right ittcnav" style="border: 0px solid red;">
			<li class="home"><a href="menu_home">平台主页</a></li>
			<li class="preview"><a href="menu_preview">实时预览</a></li>
			<li class="devmgr"><a href="menu_devmgr">设备管理</a></li>
			<li class="usermgr"><a href="menu_usermgr">用户管理</a></li>
			<li class="syscfg"><a href="menu_syscfg">权限配置</a></li>
			<li class="log"><a href="menu_log">日志查询</a></li>
		</ul>
   		
   </div>
</nav>

  


