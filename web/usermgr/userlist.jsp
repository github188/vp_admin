<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
	<table id="dg"  title="用户信息"></table>
	
	<!-- 添加/编辑 用户信息 -->
	 <div id="w_add_user" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'" title="新增用户" style="width:400px;height:220px;padding:5px;">
    	 <div class="easyui-layout" data-options="fit:true">
            
            <div data-options="region:'center'" style="padding:10px;">
            	<center>
                <form id ="addUserForm">
                	 <table>
		                <tr>
		                    <td>用户名称:</td>
		                    <td>
		                    <input type="hidden" name="user.userId" class="userId">

		                    <input name="user.userName" class="userName"/></td>
		                </tr>
		                <tr>
		                    <td>真实姓名:</td>
		                    <td><input name="user.realName" class="realName "/></td>
		                </tr>
		                <tr>
		                    <td>职位:</td>
		                    <td><input name="user.position" class="position"/></td>
		                </tr>
		                <tr>
		                	<td>角色:</td>
		                	<td><select class="roleselect" name="user.role.roleId" style="width:100%" id ="role">
		                	</select>
		                	</td>
		                </tr>
		                <tr>
		                    <td>所属部门:</td>
		                    <td><select class="deptselect" name="user.dept.deptId" style="width:100%" id ="dept">
		                    
		                    	</select>
            				</td>
		                </tr> 
		            </table>   
		        </form>
                </center>
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
                <a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="adduser();"  style="width:80px">保存</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="close_W();" style="width:80px">退出</a>
            </div>
        </div>
    </div>
	<!-- 添加/编辑 用户信息  end-->
	
	<script type="text/javascript">
		$(function(){
		//alert("${test}");
		/**  查询部门 添加到select
			yangzhiyu 
		**/
			$.ajax({
				url :"json_deptAction_getAllDept",
				type:"POST",
				cache:false,
				dataType:"json",
				success:function(data){
					var jsonData=eval("("+data+")");//转换为json对象  
				    var dept=jsonData.data;
					var str ="";
					for(var i =0 ;i<dept.length;i++){
						str += "<option value = "+dept[i].deptId+">"+dept[i].deptName+"</option>";
					} 
					$("#dept").html("");//拼接前清空，否则点击左边菜单会加载多次
					$("#dept").append(str);
				},error:function(data){
					alert(data);
				}
			});
			
		
		
		/*  查询角色  添加到select
			yangzhiyu 
		*/
			$.ajax({
				url :"json_roleAction_getAllRole",
				type:"POST",
				cache:false,
				dataType:"json",
				success:function(data){
					var jsonData=eval("("+data+")");//转换为json对象  
				    var role=jsonData.data;
					var str ="";
					for(var i =0 ;i<role.length;i++){
						str += "<option value = "+role[i].roleId+">"+role[i].roleName+"</option>";
					} 
					$("#role").html("");//拼接前清空，否则点击左边菜单会加载多次
					$("#role").append(str);
				},error:function(data){
					
				}
			
			});
			
			//用户列表操作菜单
			var toolbar = [{
				text:'新建',
				iconCls:'icon-add',
				handler:function(){
					cleanForm();
					$("#w_add_user").window({"title":"新增用户"}).window("open");
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					var row=$("#dg").datagrid("getSelected");
					if(row){
						hiddendUser(row);
					}
					else{
						$.messager.alert("提示","请选择要删除的行!");
					}	
				}
			},'-',{
				text:'编辑',
				iconCls:'icon-edit',
				handler:function(){
					cleanForm();
					var row=$("#dg").datagrid("getSelected");
					if(row){
						$("#w_add_user").window({"title":"修改用户"}).window("open");
						$(".userId").val(row.userId);
						$(".roleId").val(row.role.roleId);
						$(".userName").val(row.userName);
						$(".realName").val(row.realName);
						$(".position").val(row.position);
						$(".deptselect").val(row.dept.deptId);
						$(".roleselect").val(row.role.roleId);
						
						$("#editDialog").find("input[name='name']").val(row.productid);
					}else{
						$.messager.alert("提示","请选择要编辑的行!");
					}
				}
			},'-',{
				text:'刷新',
				iconCls:'icon-reload',
				handler:function(){
					$("#dg").datagrid("reload");
					}
			},'-'];
			
			//装载数据
			$("#dg").datagrid({
				url:"json_userAction_getUserByPage?dept.deptId=${dept.deptId}",
			   // queryParams:{
				//	"dept.deptId":-2
				//}, 
				loadFilter:function(data){
					var jsonData=eval("("+data+")");//转换为json对象  
					
					return jsonData;
				},
				toolbar:toolbar,
				pagination:true,
				rownumbers:true,//显示行编号
				singleSelect:true,//单行选择
				columns:[[
				         
				          {field:'userName',width:100,title:'用户名称'},
				          {field:'realName',width:80,title:'真实姓名'},
				          {field:'dept',width:80,title:'所属部门',
				        	  formatter:function(value,row,index){
				        		  return value.deptName;
				        		}  
				          },{
				        	  field:'role',width:100,title:'角色',align:'center',
				        	  formatter:function(value,row,index){
				        		  return value.roleName;
				        		} 
				        	  
				          },{
				        	  field:'position',width:240,title:'职位',align:'center'
					        } ,{
					        	field:'flag',width:60,title:'用户状态',align:'center',
					        	formatter:function(value,row,index){
					        		
					        		if(value==1){
					        			return "正常";
					        		}else{
					        			return "禁用";
					        		}
					        	}  
				          } 
				        ]]
			});
			
			
			//分页事情 
			var data="dept.deptId=${dept.deptId}";//在Action声明int型变量pageNum(页面)和pageSize(每页显示条数)
			var pg=$("#dg").easyuiPage("json_userAction_getUserByPage",data);
			
			
		});
		
		
		//添加用户
		function adduser(){

			//异步提交表单
		 	$("#addUserForm").ajaxForm("json_userAction_createUser");
			$('#w_add_user').window('close');
			$('.usertree').tree('reload');
			$("#dg").datagrid("reload");  

		}
		
		
		//清空内容
		function cleanForm(){
			$(".userId").val("");
			$(".roleId").val("");
			$(".userName").val("");
			$(".realName").val("");
			$(".position").val("");
			$(".deptselect").val("");
			$(".roleselect").val("");
		}
		
		
		//关闭窗口
		function close_W(){
			$("#w_add_user").window("close");
		}
		
		//删除用户
		function hiddendUser(data){
			$.messager.confirm("删除用户","确定删除‘"+data.userName+"’用户！",function(r){
					if(r){
						$.post("json_userAction_modifyUser",
								 {'user.userId':data.userId,'user.flag':data.flag}, 
								function(){
									
								}
							);
						$('#dg').datagrid('reload');
						//更新左边部门用户列表 jiaodd Add
						$('.usertree').tree('reload');
					//$(".usertree").tree('reload');
					}
				});
		}
		
	</script>	
