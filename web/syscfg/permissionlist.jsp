<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
	<table id="dg"  title="用户信息"></table>
<!-- 编辑/添加角色 弹出框--> 
 	<div id="w_add_role" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'" 
 		title="新增用户" style="width:420px;height:200px;padding:5px;">
    	 <div class="easyui-layout" data-options="fit:true">
            
            <div data-options="region:'center'" style="padding:5px;">
            	<center>
                <form id="createRole" method="post">
                	 <table >
		                <tr>
		                    <td>角色名称:<input type="hidden" name="role.roleId" class="roleId"/></td>
		                    <td><input name="role.roleName" class="rolename"/></td>
		                   <!--  <td><input name ="role.roleId"  class="roleId" type="hidden">  -->
		                </tr>
		                <tr>
		                    <td>角色权限:</td>
		                    <td id ="permission">
		                    	
            				</td>
		                </tr>
		                
		            </table>   
		             
		        </form>
                </center>
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
                <a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'" href="javascript:void(0)"  
                	style="width:80px" onclick="addRole();">保存</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" 
                	onclick="$('#w_add_role').window('close');" style="width:80px">退出</a>
            </div>           
        </div>
    </div>

    <!-- 添加角色 end -->
    
	
	
	<script type="text/javascript">
	   $(function(){
			 
		   
		   $.ajax({
				url :"json_permissionAciton_selectAllPermission",
				type:"POST",
				cache:false,
				dataType:"json",
				success:function(data){
					var jsonData=eval("("+data+")");//转换为json对象  
				    var permission=jsonData.data;
					var str ="";
					
//					for(var i =0 ;i<1;i++)
					for(var i =0 ;i<permission.length;i++){
						if(i!=0&&i%2==0){
							str += "<input class='aesyui-textbox' type='checkbox'  name ='permissions.permissionId'  value ="
							+permission[i].permissionId+">" 
							+permission[i].permissionName+"<br>";
						}else{
							str += "<input class='aesyui-textbox' type='checkbox'  name ='permissions.permissionId' value =" 
							+permission[i].permissionId+">" 
							+permission[i].permissionName;
						}
					} 
						$("#permission").html("");
						
						$("#permission").append(str);
						
					
				},error:function(data){
					alert(data);
				}
			
			});
		   
		  
		   
		   //菜单
			var toolbar = [{
				text:'新建',
				iconCls:'icon-add',
				handler:function(){
					closewindow();
					
					$("#w_add_role").window({"title":"新增角色"}).window("open");
					
				}
			},'-',{
				text:'编辑',
				iconCls:'icon-edit',
				handler:function(){
					var row=$("#dg").datagrid("getSelected");
					if(row){
						
						$("#w_add_role").window({"title":"修改角色"}).window("open");
						
						$(".roleId").val(row.roleId);
						$(".rolename").val(row.roleName);
						
						var id =row.roleId;
						
						initialize(id);
						
						
					}else{
						$.messager.alert("提示","请选择要编辑的行!");
					}
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){
					var row=$("#dg").datagrid("getSelected");
					if(row){
						
						deleteRolePermission(row);
						
					}else{$.messager.alert("提示","请选择要编辑的行!");}
					
				}
			},'-',{
				text:'刷新',
				iconCls:'icon-reload',
				handler:function(){
					$("#dg").datagrid("reload");
					}
			},'-'];
			
		   //加载权限
			$("#dg").datagrid({
				
				//url:"json_permissionAciton_selectPermissionByRidUid",
				url : "json_permissionAciton_selectPermissionByRidUid?role.roleId=${role.roleId}",

				loadFilter:function(data){
					var jsonData=eval("("+data+")");//转换为json对象  
					//alert(jsonData);
					return jsonData;	
				},
				rownumbers:true,//显示行编号
				singleSelect:true,//单行选择
				toolbar:toolbar,
				pagination:true,
				columns:[[
				          {
				        	field:'roleName',width:80,title:'角色名称',
				        	
				        },{
				        	field:'rolepermissions',width:200,title:'角色权限',align:"center",
				        	formatter:function(value,row,index){
				        		var v="";
				        		for(var i=0;i<value.length;i++){
				        			if((i+1)%3==0){
				        				v+="<br/>";
				        			}
				        			/* if(i!=(value.length-1)){
				        				v+=value[i].permission.permissionName+",";
				        			}else{
				        				v+=value[i].permission.permissionName;
				        			} */
				        			v+="【"+value[i].permission.permissionName+"】";
				        			
				        		}
				        		return v;
				        	} 
				        },{
				        	field:'users',width:260,title:'角色用户',align:"center",
				        	formatter:function(value,row,index){
				        		var v="";
				        		for(var i=0;i<value.length;i++){
				        			 if((i+1)%4==0){
				        				v+="<br/>";
				        			} 
				        			 if(value[i].flag==1 &&value[i].superAdmin==0){
				        				/* if(i!=(value.length-1)){
				        					v+=value[i].userName+",";
				        				}else{
				        					v+=value[i].userName;
				        				} */
				        				v+="【"+value[i].userName+"】";
				        				
				        			}
				        			
				        		}
				        		return v;
				        	} 
				        },/**{
				        	field:'status',width:60,title:'是否激活',align:'center',
				        	formatter:function(value,row,index){
				        		if(value=="1"){
				        			return "激活";
				        		}else{
				        			return "禁用";
				        		}
				        	}  
				          }*/
				        ]]
			});
		   
		   
			//分页事件
			   var data ="";
			   var pg =$("#dg").easyuiPage("json_permissionAciton_selectPermissionByRidUid",data);

		   
	   });
	   
	</script>	
