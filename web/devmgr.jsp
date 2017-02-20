<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设备管理</title>

<link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap 需要在jquery后引入 -->
<script src="js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
<script src="js/bootstrap/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.ittc.js"></script>
<script type="text/javascript">
$(function() {

	 $('#tt').tabs({
		 narrow:true
	});
	 
	/* 中间栏 */
	$("#center").panel({"href":"devmgr/ipclist.jsp"});
	
	//左边菜单tree物理分组
	$(".dev-tree").tree({
		url : "json_nvrAction_getIpcByNvr",
//		queryParams : {"nvr.nvrId" : -1},
		loadFilter : function(data) {
			var jsonData = eval("(" + data + ")");//转换为json对象  
			return jsonData.success;
		},formatter:function(node){
			return node.text;
		},onLoadSuccess	 : function(){
			$('.dev-tree').tree('collapseAll');
		},onLoadError : function(data) {
			alert("error");
		},onClick : function(node) {
			if(!(typeof(node.children)=="object")){
				
			}else{
				$("#center").panel({"href":"easyui_ipcAction_go_devmgr_ipclist?nvr.nvrId="+node.id});
			}
		},onContextMenu : function(e, row) { //这个就是树形菜单加载鼠标右键菜单的代码
			e.preventDefault();//屏蔽浏览器的菜单  
//			var node = $('#team').tree('getParent',	row.target);
//			if (node.text == "物理分组") {
			if(typeof(row.children)=="object"){
				$('#mm1').menu('show',{
					left : e.pageX,
					top : e.pageY,
					onClick : function(item) {
						if (item.name == "new") {
							$("#w_wl").window({"title" : "新增物理组"}).window("open");
							$(".nvrId").val("");
							$(".nvrName").val("");
							$(".nvrIp").val("");
							$(".nvrPort").val("");
							$(".nvrLoginName").val("");
							$(".nvrLoginPwd").val("");
							$(".brand").val("");
							$(".storage").val("");
						}else if (item.name == "edit") {
							$.ajax({
								url :"json_nvrAction_selectById",
								data:{'nvr.nvrId':row.id},
								type:"POST",
								cache:false,
								dataType:"json",
								success:function(data){
									var jsonData=eval("("+data+")");//转换为json对象  
								    var nvr=jsonData.data;
	
								    $("#w_wl").window({"title" : "修改物理组"}).window("open");
									$(".nvrId").val(nvr.nvrId);
									$(".nvrName").val(nvr.nvrName);
									$(".nvrIp").val(nvr.nvrIp);
									$(".nvrPort").val(nvr.nvrPort);
									$(".nvrLoginName").val(nvr.nvrLoginName);
									$(".nvrLoginPwd").val(nvr.nvrLoginPwd);
									$(".brand").val(nvr.brand);
									$(".storage").val(nvr.storage);
								},error:function(data){
									alert("error="+data);
								}
							});
							
							
						} else if (item.name = "delete") {
							var text =row.children;
							if (row) {
										$.messager.confirm("删除角色","确定删除‘"+ row.text+ "’权限！",
										function(r) {
											if (r) {//if 1
												if(text==""){//if 2
													$.post("json_nvrAction_deleteNvr",//异步删除
															{'nvr.nvrId':row.id},
															function(){
																$(".dev-tree").tree('reload');
															});
														$.messager.alert("提示","删除成功！");
												}else{
													$.messager.alert("提示","该nvr设备下有IPC设备，请勿删除");
												}	//end if2									
											}//end if1
										});//end confirm
							}//end if row
					    }//end delete
					}//end click
			});//end menu
		  }	//end typeof
		}
	});

	

	//左边树：组织管理
	$(".ipc-tree").tree({
		url : "json_locAction_getIpcByLoc",
		method:"post",
	//	queryParams : {"loc.locId" : -1},
		loadFilter : function(data) {
			var jsonData = eval("(" + data + ")");//转换为json对象  
			return jsonData.success;
		},formatter:function(node){
			return node.name;
		},onLoadSuccess	 : function(){
		
			//$('.ipc-tree').tree('collapseAll');
		},
		onLoadError : function(data) {
			alert("error");
		},onClick : function(node) {
			if(!(typeof(node.children)=="object")){
				
			}else{
				/* //将代码模块化，新建myaddTab方法
				var title = node.text;
				var id = node.id;
				var url = "easyui_ipcAction_go_devmgr_ipcIDlist?loc.locId="+id;
				//新增窗口
				myaddTab(title,url,id);	 */
			//	$("#center").panel({"href":"easyui_ipcAction_go_devmgr_ipcLoclist?locMenu.id="+node.id});
			}
		},onContextMenu: function(e,row){//这个就是树形菜单加载鼠标右键菜单的代码
			e.preventDefault();//屏蔽浏览器的菜单  
		//	alert("row="+row.name);
			if(typeof(row.children)=="object"){
				
				if(row.children==""){
					
					//添加摄像机和组织分组
					$('#mm2').menu('show',
							{
								left : e.pageX,
								top : e.pageY,
								onClick : function(item) {
									if(item.name =="new"){
										addLocChild(row);
									}else if(item.name == "edit"){
										editLoc(row);
									}else if(item.name == "delete"){
										deleteLoc(row);
									}else if(item.name =="newIpc" || item.name == "deleteIpc"){
										addAndDeleteIpc(row);
									}
								}
							});
				}else if(typeof(row.children[0].cameraId)=="number"){
					
					//添加摄像机
					$('#mm_ipc').menu('show',
							{
								left : e.pageX,
								top : e.pageY,
								onClick : function(item) {
									if(item.name =="newIpc" || item.name == "deleteIpc"){
										addAndDeleteIpc(row);
									}
								}
							});
				}else if(typeof(row.children[0].id)=="number"){
					
					//添加组织分组
					$('#mm_menu').menu('show',
							{
								left : e.pageX,
								top : e.pageY,
								onClick : function(item) {
							//		rigthClick(item);
									if(item.name == "new"){
										addLocChild(row);
									}else if(item.name == "edit"){
										editLoc(row);
									}else if(item.name == "delete")
										$.messager.alert("提示","请从最末级依次删除组织分组！");
								}
							});
				}
			
			}
		}

	});
		
});
	
	//得到IPC的名字，添加到多选框中
	function saveOrUpdateLoca(id){
		$.ajax({
			url:"json_ipcAction_getCameraDetailByLocId",
			data:"locMenu.id="+id,
			type:"post",
			success:function(data){
				var ipc=eval("(" + data + ")");
				$("#ipcCkb").empty();
				$("#ipcTemplate").tmpl(ipc.success).appendTo("#ipcCkb");
			}
		});
	}
	//添加或者删除ipc
	function addAndDeleteIpc(row){
		
		$.ajax({
			url : "json_locAction_selectById",
			data:{"locMenu.id" : row.id},
			type : "POST",
			cache : false,//是否调用缓存
			dataType:"json",
			success:function(data){
				var jsonData=eval("("+data+")");
				var locMenu = jsonData.data;
			//	alert("locMenu.parent.id="+locMenu.parent.id);
				$("#w_zz").window({"title" : "添加/删除摄像机"}).window("open");
				 if(locMenu.parent==null){
					$("#id").val(locMenu.id);
					$("#name").val(locMenu.name);
			//		$("#parent").val(locMenu.parent.id);
				}else{
					$("#id").val(locMenu.id);
					$("#name").val(locMenu.name);
					$("#parent").val(locMenu.parent.id);
				}
				
				saveOrUpdateLoca(row.id);
			},error:function(){
				alert("error="+data);
			}
		});
	}
	//增加子组织
	function addLocChild(row){
		$("#w_xzz").window({"titile" : "新增组织组"}).window("open");
		$(".id").val("");
		$(".name").val("");
		$(".parent").val(row.id);
	}
	//编辑组织分组
	function editLoc(row){
		
		$.ajax({
			url : "json_locAction_selectById",
			data:{"locMenu.id" : row.id},
			type : "POST",
			cache : false,//是否调用缓存
			dataType:"json",
			success:function(data){
				var jsonData=eval("("+data+")");
				var locMenu = jsonData.data;
				$("#w_xzz").window({"title":"修改组织组"}).window("open");
				$(".id").val(locMenu.id);
				$(".name").val(locMenu.name);
				$(".parent").val(locMenu.parent.id);
				
			},error:function(){
				alert("error="+data);
			}
		});
	}
	//删除组织分组
	function deleteLoc(row){
		if(row){
			$.messager.confirm("提示","确认删除‘"+row.name+"’吗？",
					function(r){
						if(r){
							$.post("action_locAction_deleteLoc_devmgr_error",{"locMenu.id":row.id},
									function(){$(".ipc-tree").tree('reload')});
							$.messager.alert("提示","删除成功");
						}
					})
		}
	}
	
	
	function refreshData(){
		$("#ipctab").datagrid("reload");
		$('.dev-tree').tree('reload');//
		//$('.ipc-tree').tree('reload');//
		$(".ipc-tree").tree("options").url="json_locAction_getIpcByLoc";
		$(".ipc-tree").tree("reload");
	
	}
</script>

<script type="text/javascript">
	//屏蔽浏览器右键在组织分组处的空白菜单，添加自定义菜单
	 $(window).ready(function() {
		//$('#xxxx').hide();
		$('#xxxx').bind("contextmenu", function(e) {
			$('#mm_blank').menu('show', {
				left : e.pageX,
				top : e.pageY,
				onClick : function() {
					$("#w_xzz").window({"title" : "新增组织组"}).window("open");
					$(".id").val("");
					$(".name").val("");
					$(".parent").val("");
					
				}
			});
			return false;
		});
	}); 

	//按下esc退出
	function keyPressHandler(event) {
		var e = window.event || event;
		var kC = e.keyCode;
		if (kC == 27) {

			$('#w_wl').window('close');
		}
	}

	//保存nvr设备
	function addNvr() {
		$("#addNvr").ajaxForm("json_nvrAction_addNvr");
		//$("#w_wl").window("close");
		//$(".dev-tree").tree('reload');

	}
	//保存组织
	function zzadd() {
		$(".formloc").ajaxForm("json_locAction_addLoc");
		//$("#w_zz").window("close");
		//$(".ipc-tree").tree("reload");
	}

	//增加多级树节点
	function myaddloc() {
		$(".saveLoc").ajaxForm("json_locAction_addLoc");
		//$("#w_xzz").window("close");
		//$(".ipc-tree").tree('reload');
		
	}
</script>
<script type="text/x-jquery-tmpl" id="ipcTemplate">

{{if checked}}
<input type="checkbox" checked="checked" name="ipcs.cameraId" value="{{= id}}">{{= name}}
{{else}}
<input type="checkbox" name="ipcs.cameraId" value="{{= id}}">{{= name}}
{{/if}}

{{if ($index+1)%3==0}}
<br/>
{{/if}}
</script>

</head>
<body onkeydown= "keyPressHandler(event)">
	<jsp:include page="include/nav.jsp" />

	<div id="cc" class="easyui-layout" style="height: 550px;">

		<div data-options="region:'south'" style="height: 50px;">

			<jsp:include page="include/footer.jsp" />
		</div>


		<!--   <div  class="dev-tree" data-options="region:'west'" title="设备管理" style="width:20%;padding:10px"> 
			
        </div>   -->
		<!-- 测试折叠选项  -->
		<!-- padding:10px" 距离左边的距离-->

		<div title="设备管理" data-options="region:'west'" style="width: 20%">
			<div class="easyui-tabs" id="tt" style="height: 100%">
				<div class="refresh-tree dev-tree" title="物理分组"  style="padding:10px"></div>
				<div id="xxxx" title="多级分组">
					<div class="refresh-tree ipc-tree"   style="padding:10px;height: 100%"></div>
				</div>
			</div>
			
			
		</div>
		


		<div id="center" data-options="region:'center'" title="设备信息">
			<!-- <table id="dg"></table> -->
			<div id="dg" style="width: 500px; height: 250px;"
				data-options="plain:true,border:false,selected:-1,fit:true"></div>
		</div>

	</div>
	<!-- 右击菜单 -->
	<div id="mm1" class="easyui-menu" style="width: 120px;">
		<div data-options="name:'new',iconCls:'icon-add'">新增物理组</div>
		<div data-options="name:'edit',iconCls:'icon-edit'">编辑物理组</div>
		<div data-options="name:'delete',iconCls:'icon-remove'">删除物理组</div>
		<div class="menu-sep"></div>
		<div data-options="name:'exit'">退出</div>
	</div>
	<!-- 右击菜单  end -->
	<!-- 右击菜单 -->
	<div id="mm2" class="easyui-menu" style="width: 120px;">
		<div data-options="name:'new',iconCls:'icon-add'">新增组织组</div>
		<div data-options="name:'edit',iconCls:'icon-edit'">编辑组织组</div>
		<div data-options="name:'delete',iconCls:'icon-remove'">删除组织组</div>
		<div class="menu-sep"></div>
		<div data-options="name:'newIpc',iconCls:'icon-add'">添加摄像机</div>
		<div data-options="name:'deleteIpc',iconCls:'icon-remove'">删除摄像机</div>
		<div class="menu-sep"></div>
		<div data-options="name:'exit'">退出</div>
	</div>
	<!-- 右击菜单  end -->
	
	<!-- 右键空白处新增组织组 -->
	<div id="mm_blank" class="easyui-menu" style="width:120px;">
		<div data-options="name:'new',iconCls:'icon-add'">新增组织组</div>	
	
	</div>
	<!-- 空白处右击end -->
	
	<!-- 右击添加菜单 -->
	<div id="mm_menu" class="easyui-menu" style="width: 120px;">
		<div data-options="name:'new',iconCls:'icon-add'">新增组织组</div>
		<div data-options="name:'edit',iconCls:'icon-edit'">编辑组织组</div>
		<div data-options="name:'delete',iconCls:'icon-remove'">删除组织组</div>
		<div class="menu-sep"></div>
		<div data-options="name:'exit'">退出</div>
	</div>
	<!-- 右击添加菜单  end -->
	<!-- 右击添加摄像机 -->
	<div id="mm_ipc" class="easyui-menu" style="width: 130px;">
		<div data-options="name:'newIpc',iconCls:'icon-edit'">添加/删除摄像机</div>
		
		<div class="menu-sep"></div>
		<div data-options="name:'exit'">退出</div>
	</div>
	<!-- 右击添加摄像机  end -->
	
	<!-- end -->
	<!-- 物理组新增右键菜单 -->
	<div id="w_wl" class="easyui-window"
		data-options="modal:true,closed:true,iconCls:'icon-save'"
		style="width: 300px; height: 280px; padding: 5px;">
		<div class="easyui-layout" data-options="fit:true">

			<div data-options="region:'center'" style="padding: 10px;">
				<center>
					<form id="addNvr"  method="post">
					<table style="border-collapse:separate; border-spacing:2px;">
						<tr>
							<!--  <td>NVR的ID:</td> -->
							<td>别名:<input name="nvr.nvrId" type="hidden" class="nvrId" /></td>
							<td><input name="nvr.nvrName" class="nvrName" /></td>
						</tr>
					
						<tr>
							<td>IP地址:</td>
							<td><input name="nvr.nvrIp" class="nvrIp" /></td>
						</tr>
						<tr>
							<td>端口号:</td>
							<td><input name="nvr.nvrPort" class="nvrPort" /></td>
						</tr>
						<tr>
							<td>登录名:</td>
							<td><input name="nvr.nvrLoginName" class="nvrLoginName" /></td>
						</tr>
						<tr>
							<td>登陆密码:</td>
							<td><input type="password" name="nvr.nvrLoginPwd" class="nvrLoginPwd" /></td>
						</tr>
						<tr>
							<td>品牌:</td>
							<td><input name="nvr.brand" class="brand" /></td>
						</tr>
						<tr>
							<td>存储空间:</td>
							<td><input name="nvr.storage" class="storage" /></td>
						</tr>

					</table>

				</form>
				</center>
			</div>
			<div data-options="region:'south',border:false"
				style="text-align: right; padding: 5px 0 0;">
				<a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'" href="javascript:void(0)"
					onclick="addNvr();" style="width: 80px">保存</a> 
				<a class="easyui-linkbutton"
					data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
					onclick="$('#w_wl').window('close')" style="width: 80px">退出</a>
			</div>
		</div>
	</div>
	<!-- end -->	
	
	<!-- 组织组右键添加摄像机菜单 -->
	<div id="w_zz" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width: 300px; height: 250px; padding: 5px;">
		<div class="easyui-layout" data-options="fit:true">

			<div data-options="region:'center'" style="padding: 10px;">
				<center>
				<form  method="post" class="formloc">
					<table>
						<tr>
							<td>组织名称：<input type="hidden" id="id" name="locMenu.id"></td>
							<td><input type="text" id="name" name="locMenu.name" required="required"></td>
							<td><input type="hidden" id="parent" name="locMenu.parent.id"></td>
						</tr>
						<tr>
							<td> 摄像机名：</td>
							<td id="ipcCkb">
								<input type="checkbox" >摄像机1<input type="checkbox">摄像机2<input type="checkbox">摄像机2
								<br/>
								<input type="checkbox">摄像机1<input type="checkbox">摄像机2<input type="checkbox">摄像机2
							</td>
						</tr>
					</table>

					
				</form>
				</center>
			</div>
			<div data-options="region:'south',border:false"
				style="text-align: right; padding: 5px 0 0;">
				<a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'" href="javascript:void(0)"
					onclick="zzadd();" style="width: 80px">保存</a> 
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
					onclick="$('#w_zz').window('close')" style="width: 80px">退出</a>
			</div>
		</div>
	</div>
	<!-- end -->
	
	<!-- 添加组织组 -->
	<div id="w_xzz" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'" title="新增组织组" style="width:300px;height:130px;padding:5px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'" style="padding:10px;">
				<center>
					<form method="post" class="saveLoc">
						<table>
							<tr>
								<td>组织名称:<input type="hidden" class="id" name="locMenu.id"></td>
								<td><input type="text" class="name" name="locMenu.name"></td>
								<td><input type="hidden" class="parent" name="locMenu.parent.id"></td>
							</tr>
						</table>
					</form>
				</center>
			</div>
			<div data-options="region:'south', border:false" style="text-align:right;padding:5px 0 0;">
				<a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="myaddloc();" style="width:80px">保存</a>
				<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#w_xzz').window('close')" style="width:80px">退出</a>
			</div>
		</div>
	</div>
	
	
	
	

</body>
</html>
