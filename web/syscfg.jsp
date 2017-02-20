<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限配置</title>
   <link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
   <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
   <script src="js/jquery/jquery.min.js"></script>
   <script src="js/bootstrap/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
   
   <script type="text/javascript">
   $(function(){
	   
	$("#center").panel({"href":"syscfg/permissionlist.jsp"});
	   
	$(".easyui-tree").tree({
 			//url:"data/get_syscfg1.json",
 			url:"json_permissionAciton_getPermissionTree",
 			//queryParams:{"dept.deptId":-1},
 			loadFilter:function(data){
 				var jsonData=eval("("+data+")");//转换为json对象  
 				return jsonData.success;
 			},
		   /* formatter:function(node){
			  //alert(typeof(node.roleName));
			   if(typeof(node.children)=="object"||typeof(node.roleName)=="string"){
				  return node.roleName;
			   }else{
				  return node.userName;
			   }
			  
		   }, */
		   onClick:function(node){
			  if(typeof(node.children)=="object")
			  {
				  $("#center").panel({"href":"easyui_userAction_goByDept_syscfg_permissionlist?role.roleId="+node.id});
				  
			  }
			  
		   },onContextMenu: function(e,row){ //这个就是树形菜单加载鼠标右键菜单的代码
			 	e.preventDefault();//屏蔽浏览器的菜单 
			 		$('#mm_role').menu('show', {
	  			 		left: e.pageX,
	  			 		top: e.pageY,
	  			 		onClick:function(item){
	  			 			if(item.name=="search"){
	 	  			 			$.ajax({
	 	  		 				  url:"data/get_syscfg3.json",
	 	  		 				  success:function(data){
	 	  		 					 $("#dg").datagrid("loadData",data);
	 	  		 				  }
	 	  		 			  });
	  			 				
	  			 			}else if(item.name=="new"){
	  			 				closewindow();
	  			 				$("#w_add_role").window({"title":"新增角色"}).window("open");
	  			 				
	  			 			}else if(item.name=="edit"){
	  			 				$("#w_add_role").window({"title":"修改角色"}).window("open");
	  			 			
	  						
	  							$(".roleId").val(row.id);
	  							$(".rolename").val(row.text);
	  							
	  							var id =row.id;
	  						
	  							initialize(id);
	  			 				
	  			 				
	  			 			}else if(item.name="delete"){
	  			 				//alert(row.roleId);
	 	  			 			//var row=$("#dg").datagrid("getSelected");
	 	  						if(row){
	 	  							$.messager.confirm("删除角色","确定删除‘"+row.text+"’权限！",function(r){
	 	  								if(r){
	 	  									var id =row.id;
	 	  									if(id ==1)
	 	  										{
	 	  										$.messager.alert("提示","【普通用户】不允许删除！");
	 	  										}
	 	  									else{
	 	  									$.post("json_roleAction_delectRolePermission",
	 	  											{'role.roleId' :id},
	 	  											function(){
	 	  												$('#dg').datagrid('reload');
	 	  												//更新左边部门用户列表 jiaodd Add
	 	  												$('.easyui-tree').tree('reload');
	 	  											});
	 	  									
	 	  									$.messager.alert("提示","删除成功！");
	 	  								}}
	 	  							});
	 	  						}
	  			 			}else{
	  			 				
	  			 			}
	  			 		}
	  			 	});
			 },
	   });
   });
   
   
  
   
   function addRole(){

	   $.ajax({
		  // url:"action_roleAction_createRolePermission_syscfg_error",
		   url:"json_roleAction_addRole",
		   data:$('#createRole').serialize(),
		   type:"POST",
		   cache: false,
		   dataType:"json",
		   success:function(data){
			   //alert(data);
			   var jsonData=eval("("+data+")");//转换为json对象  
			   if(jsonData.success){
				   $.messager.alert("提示","操作成功！");
			   }else{
				   $.messager.alert("提示","操作失败！");
			   }
			   $('#w_add_role').window('close');
			   $('.easyui-tree').tree('reload');
			   $("#dg").datagrid("reload");
		   }
	   });
   }
   
   function deleteRolePermission(data){
	   $.messager.confirm("删除角色","确定删除‘"+data.roleName+"’权限！",function(r){
			if(r){
				
				$.post("json_roleAction_delectRolePermission",
						{'role.roleId' :data.roleId},
						function(){
							$('#dg').datagrid('reload');
							//更新左边部门用户列表 jiaodd Add
							$('.easyui-tree').tree('reload');
						});
				
				$.messager.alert("提示","删除成功！");
			}
		});
   }
  
  function  initialize(data){
	 //alert(data);
	  $.ajax({
		  url:"json_roleAction_selectRolePermissionByRoleId",
		  data:{'role.roleId': data},
		  type:"POST",
		  cache:false,
		  dataTpye:"json",
		  success:function(data){
			  var jsonData=eval("("+data+")");
			  var datas =jsonData.success;
			  $("#permission").find("input").each(function(){
				  $(this).prop("checked",false);
					 for(var i=0;i<datas.length;i++)
						 {
						 if($(this).val()==datas[i])
							{
							//$(this).attr("checked","cheecked");
							//this.setAttribute("checked","true");
							$(this).prop("checked",true);
							}
						 }

				 }) 
		  }
	  });
}
   
  function closewindow(){
	  
	  $('#w_add_role').window('close');
	  $(".roleId").val("");
	  $(".rolename").val("");
	  $(".roleId").val("");
	  $("#permission").find("input").each(function(){
		  $(this).prop("checked",false);
	  });
  }
  
   </script>
</head>
<body>
	<!-- 导航菜单 -->
	<jsp:include page="include/nav.jsp"/>
	<!-- 主框架 -->
    <div id="cc" class="easyui-layout"  style="height:550px;">
    
    	 <div data-options="region:'south'" style="height:50px;">
    	 	<jsp:include page="include/footer.jsp"/>
    	 </div>
    	 <!-- 左边 -->
        <div  class="easyui-tree" data-options="region:'west'" title="角色列表" style="width:20%;padding:10px">
          
        </div>
        <!-- 中间 -->
        <div id="center"  data-options="region:'center'" title="权限信息" >
        	
        		<table id="dg"  title="角色权限用户信息"></table>
        	 
        </div>
    </div>
    <!-- 主框架 end -->
    
    <!-- 右击出现的菜单，默认隐藏 -->
    <div id="mm_role" class="easyui-menu"  style="width:120px;">
    	<div data-options="name:'search',iconCls:'icon-search'">显示权限</div>
        <div data-options="name:'new',iconCls:'icon-add'">新建角色</div>
        <div data-options="name:'edit',iconCls:'icon-edit'">编辑角色</div>
        <div data-options="name:'delete',iconCls:'icon-remove'">删除角色</div>
        <div class="menu-sep"></div>
        <div data-options="name:'exit'">退出</div>
    </div>
    <!-- 右击出现的菜单，默认隐藏  end-->
    
    
    
   
</body>
</html>