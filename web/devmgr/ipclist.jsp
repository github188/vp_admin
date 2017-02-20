<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<table id="ipctab"  class="refresh-dg" title="IPC信息"><!-- 删除 class="easyui-datagrid"后能显示分页 -->

</table>
<div id="w_add_ipc" class="easyui-window"
	data-options="modal:true,closed:true,iconCls:'icon-save'" title="新增用户"
	style="width: 400px; height: 320px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<%-- <input type="hidden" value="${node }" id="XXX"> --%>
		<div data-options="region:'center'" style="padding: 10px;">
			<center>
				<form id="addipc"	method="post">
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
							<td>对应NVR:</td>
							<td>
								<select id="nvrSelect" name="nvrs.nvrId" style="width: 150px">
									<option value="0">大华</option>
									<option value="1">海康</option>
									<option value="2" selected="selected">华为</option>
									<option value="3">宇视</option>
									<option value="4">皓维</option>
								</select>
							</td>
						</tr>
					</table>
				</form>
			</center>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="addipc();" style="width: 80px">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="close_w();" style="width: 80px">退出</a>
		</div>
	</div>
</div>


<!-- <script type="text/javascript">
//得到树左边的IPC的IDZ
var thisURL = request.getPrameters("nd");
alert(thisURL);
var  getval =thisURL.split('?')[1]; 
var showval = getval.split("=")[1];
alert("node的id"+showval);
</script> 
 -->
<script type="text/x-jquery-tmpl" id="nvrTemplate">
{{if selected}}
<option value="{{= id}}" selected="{{= selected}}">{{= name}}</option>
{{else}}
<option value="{{= id}}" >{{= name}}</option>
{{/if}}
</script>

<script type="text/javascript">
	$(function() {
		/* 表格上方的各个 功能按钮的实现*/
		var toolbar = [
				{
					text : '新建',
					iconCls : 'icon-add',
					handler : function() {
					
						$("#w_add_ipc").window({"title" : "新增IPC"}).window("open");
						$('#addipc')[0].reset();
						/**
						$(".cameraId").val("");
						$(".cameraIp").val("");
						$(".cameraName").val("");
						$(".cameraPort").val("");
						$(".cameraLoginName").val("");
						$(".cameraPwd").val("");
						$(".cameraMac").val("");
					**/	
						loadNvrList(-1);
					}
				},
				'-',
				{
					text : '删除',
					iconCls : 'icon-remove',
					handler : function() {
						var row = $("#ipctab").datagrid("getSelected");
						if (row) {
							$.messager.confirm("删除角色","确认删除‘"+row.text+"’权限？",
								function(r){
									if(r){
										deletes(row);
									}
								}		
							);
							
						} else {
							$.messager.alert("提示", "请选择要删除的行!");
						}

					}
				},
				'-',
				{
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						//alert('save')
						var row = $("#ipctab").datagrid("getSelected");
						if (row) {
							myeditshow(row);
						} else {
							$.messager.alert("提示", "请选择要编辑的行!");
						}
					}
				}, '-', {
					text : '刷新',
					iconCls : 'icon-reload',
					handler : function() {
						$("#ipctab").datagrid("reload");
						//alert('刷新')
					}
				}, '-' ];
		//显示窗体表格
		$("#ipctab").datagrid({
			//	url:"json_nvrAction_selectById",
			url : "json_ipcAction_selectAll?nvr.nvrId=${nvr.nvrId}",
			//		queryParams:{"nvr.nvrId":-2},
			loadFilter : function(data) {
				var jsonData = eval("(" + data + ")");//转换为json对象  
				return jsonData;
			},
			rownumbers:true,//显示行编号
			singleSelect:true,//单行选择
			pagination : true,
			toolbar : toolbar, //表格上面功能按钮
			columns : [ [  {
				field : "cameraName",
				width : 100,
				title : "IPC名称"
			},{
				field : "cameraIp",
				width : 120,
				title : "IP"
			},{
				field : "cameraPort",
				width : 80,
				title : "端口号"
			}, {
				field : "cameraLoginName",
				width : 120,
				title : "登录名"
			}, {
				field : "cameraMac",
				width : 140,
				title : "MAC地址"
			}] ],
			onDblClickRow : function(index, row){
				myeditshow(row);
			}
		});
		//分页事情 
		var data="";//在Action声明int型变量pageNum(页面)和pageSize(每页显示条数)
		var pg=$("#ipctab").easyuiPage("json_ipcAction_selectAll",data);
		
		

	});
</script>
<script type="text/javascript">
	//修改行数据
	function myeditshow(row){
		$("#w_add_ipc").window({"title" : "修改IPC信息"}).window("open");
		
		loadNvrList(row.cameraId);
		
		
		$(".cameraId").val(row.cameraId);
		$(".cameraIp").val(row.cameraIp);
		$(".cameraName").val(row.cameraName);
		$(".cameraPort").val(row.cameraPort);
		$(".cameraLoginName").val(row.cameraLoginName);
		$(".cameraPwd").val(row.cameraPwd);
		$(".cameraMac").val(row.cameraMac);
		
		//刷新列表
		//$(".dev-tree").tree('reload');
		//$(".ipc-tree").tree('reload');
		
	}
	
	
	//添加ipc
	function addipc() {
		
		$("#addipc").ajaxForm("json_ipcAction_addIpc");
		//$("#w_add_ipc").window("close");
		
	}

	//关闭窗口
	function close_w() {
		$("#w_add_ipc").window("close");
	}

	//删除用户
	function deletes(data) {
//		alert(data.cameraId)
		$.post("json_ipcAction_deleteIpc", {
			'ipc.cameraId' : data.cameraId
		}, function() {
			$("#ipctab").datagrid("reload");
		})
	}
	
	function loadNvrList(cameraId){
		
		$.ajax({
			url:"json_ipcAction_getNvrDetailByCameraId",
			data:"ipc.cameraId="+cameraId,
			type:"POST",
			cache:false,
			dataType:"json",
			success:function(data){
				//alert(data);
				 var jsonData=eval("("+data+")");//转换为json对象  
				 var nvr=jsonData.success;
				
				$("#nvrSelect").empty();
				$("#nvrTemplate").tmpl(nvr).appendTo("#nvrSelect");
			}
		});
	}
</script>











