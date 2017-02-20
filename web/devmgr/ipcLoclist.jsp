<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<table id="ipctab" class="easyui-datagrid" title="IPC信息">

</table>
<div id="w_add_ipc" class="easyui-window"
	data-options="modal:true,closed:true,iconCls:'icon-save'" title="新增用户"
	style="width: 400px; height: 200px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<%-- <input type="hidden" value="${node }" id="XXX"> --%>
		<div data-options="region:'center'" style="padding: 10px;">
			<center>
				<form id="addipc" action="action_ipcAction_addIpc_ipcIDlist_error"	method="post">
					<table>
						<tr>
							<td><input name="ipc.cameraId" type="hidden" class="cameraId"/></td>							
						</tr>
						
						<tr>
							<td>IP:</td>
							<td><input name="ipc.cameraIp" class="cameraIp" /></td>
						</tr>
						<tr>
							<td>别名:</td>
							<td><input name="ipc.cameraName" class="cameraName " /></td>
						</tr>
						<tr>
							<td>端口号:</td>
							<td><input name="ipc.cameraPort" class="cameraPort" /></td>
						</tr>
						<tr>
							<td>登录名:</td>
							<td><input name="ipc.cameraLoginName" class="cameraLoginName" /></td>
						</tr>
						<tr>
							<td>登录密码:</td>
							<td><input name="ipc.cameraPwd" class="cameraPwd" /></td>
						</tr>
						<tr>
							<td>MAC地址:</td>
							<td><input name="ipc.cameraMac" class="cameraMac" /></td>
						</tr>
						<tr>
							<td>对应NVR通道号:</td>
							<td><input name="ipc.channelId" class="channelId" /></td>
						</tr>
					</table>
				</form>
			</center>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="addipc();"
				style="width: 80px">保存</a> 
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="close_w();"
				style="width: 80px">退出</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	//关闭窗口
	function close_w(){
		$("#w_add_ipc").window("close");
	}
	
	
	//删除IPC设备
	function deletess(data){
		$.post("action_ipcAction_deleteIpc_devmgr_error",{
			'ipc.cameraId':data.cameraId
			},function(){
				$("#ipctab").datagrid("reload");
			})
	}
	
	//保存IPC设备
	function addipc(){
		$("#addipc").submit();
	}


</script>

<script type="text/javascript">
	
	$(function() {
	//	alert($('loc.locId'));
		/* 表格上方的各个 功能按钮的实现*/
		var toolbar = [
				{
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						//alert('save')
						var row = $("#ipctab").datagrid("getSelected");
						if (row) {
							//$("#w_add_user").children("input[name='userName']").val(row.userName);

							$("#w_add_ipc").window({
								"title" : "修改IPC信息"
							}).window("open");
							$(".cameraId").val(row.cameraId);
							$(".cameraIp").val(row.cameraIp);
							$(".cameraName").val(row.cameraName);
							$(".cameraPort").val(row.cameraPort);
							$(".cameraLoginName").val(row.cameraLoginName);
							$(".cameraPwd").val(row.cameraPwd);
							$(".cameraMac").val(row.cameraMac);
							$(".channelId").val(row.channelId);

							//alert(row.userName);

							$("#editDialog").find("input[name='name']").val(
									row.productid);
						} else {
							$.messager.alert("提示", "请选择要编辑的行!");
						}
					}
				},
				'-',
				{
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						var row = $("#ipctab").datagrid("getSelected");
						if (row) {
							$.messager.confirm("提示","确认删除‘"+row.cameraName+"’的权限吗？",
									function(r){
										if(r){
											deletess(row);
											$.messager.alert("提示","删除成功");
										}								
									})
						} else {
							$.messager.alert("提示", "请选择要删除的行!");
						}

					}
				},
				 '-', {
					text : '刷新',
					iconCls : 'icon-reload',
					handler : function() {
						$("#ipctab").datagrid("reload");
						//alert('刷新')
					}
				}, '-' ];
		//显示窗体表格
		$("#ipctab").datagrid({

			//	alert("${node}"),
			//	url:"json_nvrAction_selectById",
			url : "json_ipcAction_selectAllByLoc?locMenu.id=${locMenu.id}",
			//	queryParams:{"ipc.cameraId":"${node}"},
			loadFilter : function(data) {
				var jsonData = eval("(" + data + ")");//转换为json对象  
				return jsonData;
			},
			runnumbers:true,//显示编号
			dingleSelect:true,//单行选择
			pagination : true,
			toolbar : toolbar,
			columns : [ [  {
				field : "cameraName", width : 120, title : "IPC名称",
			},{
				field : "cameraIp", width : 140, title : "IP"
			},{
				field : "cameraPort", width : 60, title : "端口号"
			}, {
				field : "cameraLoginName", width : 100, title : "登录名"
			}, {
				field : "cameraMac", width : 120, title : "MAC地址"
			}] ]
		});
		//分页事情 
		var data="";//在Action声明int型变量pageNum(页面)和pageSize(每页显示条数)
		var pg=$("#ipctab").easyuiPage("json_ipcAction_selectAll",data);
	});
</script>
<script type="text/javascript">
	//删除IPC
	function deletess(data){
		$.post("action_ipcAction_deleteIpc_devmgr_error",{
				'ipc.cameraId' : data.cameraId
				}, function() {
					$("#ipctab").datagrid("reload");
				})
			}
</script>


