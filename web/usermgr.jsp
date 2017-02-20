<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
   <link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery/jquery.min.js"></script>
   <script src="js/bootstrap/js/bootstrap.min.js"></script>
   
   <link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
   <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
   <script src="js/easyui/jquery.ittc.js"></script>
   <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
   <script type="text/javascript" src="js/easyui/jquery.ittc.js"></script>
   <script type="text/javascript">
   	$(function(){
   		
   	 	$("#center").panel({"href":"usermgr/userlist.jsp"});
   	 	
   	 	//右边部门用户tree
   		$(".usertree").tree({
   			url:"json_userAction_getUserByDept",
   			queryParams:{"dept.deptId":-1},
   			loadFilter:function(data){
   				var jsonData=eval("("+data+")");//转换为json对象  
   				return jsonData.success;
   			},
  		  // formatter:function(node){
  			//  return node.;
  		  // },
  		   onClick:function(node){
  			   if(typeof(node.children)=="object"){
  					$("#center").panel({"href":"easyui_userAction_goByDept_usermgr_userlist?dept.deptId="+node.id});
  			   }
  			  
  		   },onContextMenu: function(e,row){ //这个就是树形菜单加载鼠标右键菜单的代码
  			 	e.preventDefault();//屏蔽浏览器的菜单
  			 	
  			 	if(typeof(row.children)=="object"){
  			 		$('#mm').menu('show', {
  	  			 		left: e.pageX,
  	  			 		top: e.pageY,
  	  			 		onClick:function(item){
  	  			 			if(item.name=="new"){
  	  			 				$("#w_dept").window({"title":"新增部门"}).window("open");
  	  			 				$("#deptId").val("");
	  			 				$("#deptName").val("");
  	  			 			}else if(item.name=="edit"){
  	  			 				$("#w_dept").window({"title":"修改部门"}).window("open");
  	  			 				
  	  			 				$("#deptId").val(row.id);
  	  			 				$("#deptName").val(row.text);
  	  			 			}else if(item.name="delete"){

  	  			 			
  	  			 				
	  	  			 			 if(row){
	 	  							$.messager.confirm("删除角色","确定删除‘"+row.text+"’权限！",function(r){
	 	  								 if(r){

	 	  									var test =row.children;
	 	  									
	 	  									if(test==""){
	 	  	  	  			 					$.post("action_deptAction_deleteDept_usermgr_error",{'dept.deptId':row.id},function(){
	 	  	  	  			 					$.messager.alert("提示","删除成功！");
	 	  	  	  			 					$(".usertree").tree('reload');
	 	  	  	  			 					})
	 	  	  	  			 				}else {
	 	  	  	  			 					$.messager.alert("提示","部门下有用户不允许删除!");
	 	  	  	  			 				}
	 	  									
	 	  								} 
	 	  							});
	 	  						}
 
  	  			 			}
  	  			 			
  	  			 			else{
  	  			 			
  	  			 			}
  	  			 		}
  	  			 	});
  			 	}
  			 },
  	   });
   	 	
   	 $(".btnSave").click(function(){
   		 $(".formDept").submit();
   	 });
   	 	
   	 	
   	});
   </script>
</head>
<body>
<!-- 菜单 -->
<jsp:include page="include/nav.jsp"/>
    <div id="cc" class="easyui-layout"  style="height:550px;">
    
  
    	<div data-options="region:'south'" style="height:50px;">
    		<jsp:include page="include/footer.jsp"/>
    	</div>
    	<!-- 左边列表 -->
        <div  class="usertree" data-options="region:'west'" title="用户管理" style="width:20%;padding:10px">
        </div>
        <!-- 内容 -->
        <div id="center" class="easyui-tabs" data-options="region:'center'" title="用户信息" >
        	
        </div>
        
    </div>


	<!-- 左边列表右击弹出框 -->
	 <div id="mm" class="easyui-menu"  style="width:120px;">
        <div data-options="name:'new',iconCls:'icon-add'">新增部门</div>
        <div data-options="name:'edit',iconCls:'icon-edit'">编辑部门</div>
        <div data-options="name:'delete',iconCls:'icon-remove'">删除部门</div>
        <div class="menu-sep"></div>
        <div data-options="name:'exit'">退出</div>
    </div>
    <!-- 右击弹出框 END -->
    <!-- 添加/编辑部门弹出框 -->
    <div id="w_dept" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'" title="新增部门" style="width:300px;height:130px;padding:5px;">
    	
    	 <div class="easyui-layout" data-options="fit:true">
            
            <div data-options="region:'center'" style="padding:10px;">
            	<center>
                <form action="action_userAction_addDept_usermgr_error" method="post" class="formDept">
                	<input type="hidden" id="deptId" name="dept.deptId">
                	部门名称：<input type="text" id="deptName" name="dept.deptName" required="required">
                	
                </form>
                </center>
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
                <a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'" href="javascript:void(0)"   style="width:80px">保存</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#w_dept').window('close')" style="width:80px">退出</a>
            </div>
        </div>
    </div>
    <!-- 添加/编辑部门弹出框 end -->
       
</body>
</html>