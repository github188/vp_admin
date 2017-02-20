<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 
<table class="easyui-datagrid" title="设备类型列表" style="width:700px;height:250px"
			data-options="rownumbers:true,singleSelect:true,url:'data/get_devmgr2.json',method:'get',toolbar:toolbar">
		 -->
	<table id="dg" class="easyui-datagrid" title="设备类型列表">
		
	</table>
	

	<script type="text/javascript">
		$(function(){
			
			var toolbar = [{
				text:'新建',
				iconCls:'icon-add',
				handler:function(){
					alert('add')
				
				}
			},'-',{
				text:'删除',
				iconCls:'icon-remove',
				handler:function(){alert('cut')}
			},'-',{
				text:'编辑',
				iconCls:'icon-edit',
				handler:function(){
					//alert('save')
					var row=$("#dg").datagrid("getSelected");
					if(row){
						$("#editDialog").dialog("open").dialog({
							title:"修改信息",
							width:350,
							height:250,
							modal:true
						});
						//alert(row.itemid);
						$("#editDialog").children("input[name='id']").val(row.itemid);
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
					//alert('刷新')
					}
			},'-'];
			
			$("#dg").datagrid({
				url:"data/get_devmgr2.json",
				toolbar:toolbar,
				pagination:true,
				columns:[[
				          {field:'itemid',width:80,title:'Item ID'},
				          {field:'productid',width:100,title:'Product'},
				          {field:'listprice',width:80,title:'List Price'},
				          {field:'unitcost',width:80,title:'Unit Cost'},
				          {field:'attr1',width:240,title:'Attribute'},
				          {field:'status',width:60,title:'Status',align:'center'}
				          ]]
			});
			
		});
		
	</script>
	
	 <div id="editDialog" style="display: none;">
    	<form class="easyui-form" action="#" method="post">
    		<table cellpadding="5">
    			<tr>
    				<td>ID:</td>
    				<td>
    					<input class="easyui-textbox" type="text" name="id" >
    				</td>
    			</tr>
    			<tr>
    				<td>Name:</td>
    				<td>
    					<input class="easyui-textbox" type="text" name="name"  required="required">
    				</td>
    			</tr>
    		</table>
	    	<a class="easyui-linkbutton">提交</a>
	    	
    	</form>
    </div>