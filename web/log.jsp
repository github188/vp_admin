<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志查询</title>
   <link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <script src="js/jquery/jquery.min.js"></script>
   <script src="js/bootstrap/js/bootstrap.min.js"></script>
   
   <!-- dataTables -->
   <link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet">
   <link href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.dataTables.min.css" rel="stylesheet">
   
   <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
   <script src="https://cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js "></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
   <script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js "></script>
   <script src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
   <script src="https://cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js "></script>
   <script src="http://cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
   <script type="text/javascript">
   	$(function(){
   		
   		$("#example").DataTable({
   			dom: 'Bfrtip',
            buttons: [
                      //'copyHtml5',
                      {
                    	extend:"excelHtml5",
                    	text:"导出",
                    	title:"日志"
                      },
                      //'csvHtml5',
                      //'pdfHtml5'
                      {
                    	extend:"print",
                    	text:"打印"
                      }
                  ],
   		 language: {
   			lengthMenu: "每页显示 _MENU_ 条记录",
   		    zeroRecords: "对不起，没有找到记录！",
   		    search:"搜索：",
   		    info: "第 _PAGE_ 页/共 _PAGES_页",
   		    infoEmpty: "没有数据",
   		    infoFiltered: "(filtered from _MAX_ total records)",
   		    zeroRecords:"没有记录",
   		    paginate:{
   		    	first:"首页",
   		        previous:"上一页",
   		        next:"下一页",
   		        last:"尾页"
   		    }
        },
         
   			//"processing":true,
   			ajax:{
   				url:"data/get_logdata1.json",
   				type:"POST"
   			},
   			columns:[
   			           	{data:"num"},{data:"name"},{data:"time"},{data:"type"},{data:"content"}
   			           ]
   		});
   		
   	});
   </script>
</head>
<body>
	<!-- 菜单 -->
	<jsp:include page="include/nav.jsp"/>
  	<!-- layout start -->
    <div id="cc" class="easyui-layout"  style="height:550px;">
    	<!-- foot -->
    	 <div data-options="region:'south'" style="height:50px;">
    	 	<jsp:include page="include/footer.jsp"/>
    	 </div>
    	 <!-- foot end-->
        <div  class="easyui-accordion" data-options="region:'west'" title="操作菜单" style="width:20%;padding:10px">
            
            	<div title="日志查询" style="padding:10px;">
            		 <ul id="search" class="easyui-tree" data-options="url:'data/get_log_tree1.json',method:'get'"></ul>
        		</div>
           		<div title="日志操作" style="padding:10px;">
           			<ul id="sysset" class="easyui-tree" data-options="url:'data/get_log_tree2.json',method:'get'"></ul>
        		</div>
        		
        </div>
        <div id="center" data-options="region:'center'" title="日志操作">
        
        	<table id="example" class="display" cellspacing="0" width="100%">
        		<thead>
        			<tr>
        				<th>序号</th>
        				<th>操作人员</th>
        				<th>操作时间</th>
        				<th>日志类型</th>
        				<th>操作内容</th>
        			</tr>
        		</thead>
        	</table>
        	
        </div>
        
    </div><!-- layout end -->

</body>
</html>