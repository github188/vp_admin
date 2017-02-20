<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<table id="dg" data-options="toolbar:'#tb'" title="日志列表">
		
	</table>
	<div id="tb" style="padding:2px 5px;">
		日志时间  从: <input class="easyui-datetimebox" data-options="showSeconds:false" style="width:120px">
		至: <input class="easyui-datetimebox" data-options="showSeconds:false" style="width:120px">
                   操作人: 
        <select class="easyui-combobox" panelHeight="auto" style="width:100px">
            <option value="java">张三</option>
            <option value="c">李四</option>
            <option value="basic">王五</option>
            <option value="perl">赵六</option>
            <option value="python">王久</option>
        </select>
                   日志内容:<input class="easyui-textbox">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
   	</div>
	

	<script type="text/javascript">
		$(function(){
			$("#dg").datagrid({
				url:"data/get_log1.json",
				pagination:true,
				columns:[[
				          {field:'id',width:80,title:'序号',align:'center'},
				          {field:'name',width:120,title:'操作人',align:'center'},
				          {field:'content',width:200,title:'操作内容',align:'center'},
				          {field:'time',width:120,title:'操作时间',align:'center'},
				          {field:'remark',width:240,title:'备注',align:'center'}
				          ]]
			});
			
		});
		
	</script>