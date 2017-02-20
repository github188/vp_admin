<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
   <link href="js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
   	<script src="js/jquery/jquery.min.js"></script>
   	<script src="js/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
    
	<script src="https://code.highcharts.com/highcharts.js"></script>

    
    <style type="text/css">
    body{
    	font-size: 14px;
    	text-align: center;
    	margin: auto 0;
    }
    </style>
    <script type="text/javascript">
    	$(function(){
    		$("#home_menu1").tree({
    			url:"data/get_home1.json",
    			onClick:function(node){
    				$("#center").panel({href:node.url});
    			},onBeforeExpand:function(){
    				
    			}
    		});
    		
    		$("#sysset").tree({
    			onClick:function(node){
    				//$("#center").panel({href:node.url});
    				if(node.text=="退出系统"){
    					$("#center").panel({href:"home/logout.jsp"});
    				}else if(node.text=="修改密码"){
    					$("#w_modify_pwd").window({"title":"修改密码"}).window("open");
    				}else if(node.text=="信息修改"){
    					$("#w_modify_info").window({"title":"修改密码"}).window("open");
    				}
    			}
    		});
    		
    		$("#btn").click(function(){
    			$("#center").panel({
    				regin:"center",
    				href:"testpage.jsp"
    			});
    		});
    		
			//$("#center").panel("refresh");
			

	        // Build the chart
	       //var chart=$('#container').highcharts({
	      var chart=new Highcharts.Chart({
	            chart: {
	                plotBackgroundColor: null,
	                plotBorderWidth: null,
	                plotShadow: false,
	                type: 'pie',
	                renderTo: 'container1'
	            },
	            title: {
	                text: 'Browser market shares January, 2015 to May, 2015'
	            },
	            tooltip: {
	                pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
	            },
	            credits: {
	                enabled:false//去掉水印
	      		},
	            plotOptions: {
	                pie: {
	                    allowPointSelect: true,
	                    cursor: 'pointer',
	                    dataLabels: {
	                        enabled: false
	                    },
	                    showInLegend: true
	                }
	            },
	            series: [{
	                name: '占',
	                colorByPoint: true
	                /**
	                data: **/
	            }]
	        });
	        
	     
           	$.ajax({
           		url:"data/get_home_highcharts.json",
           		//url:"data/get_home1.json",
           		//type:"POST",
           		//dataType:"json",
           		success:function(data){
           			//alert(data);
           			//var jsonData=eval("("+data+")");//转换为json对象  
           			chart.series[0].setData(data);
           			var title = {
           				    text:"摄像机品牌分布",
           				};
           			chart.setTitle(title);
           		},error:function(data){
           			alert("error"+data);
           		}
           	});
           
            var chart2=new Highcharts.Chart({
            	chart: {
                    type: 'column',
                    margin: [ 50, 50, 100, 80],
                    renderTo: 'container2'
                },
                credits: {
	                enabled:false//去掉水印
	      		},
                title: {
                    text: '摄像机数量'
                },
                tooltip: {
                    pointFormat: '摄像机数量: <b>{point.y:.1f} 台</b>',
                },xAxis: {
                    categories: ['大华','海康','宇视','华为', '安讯士','ITTC']
                         },
               yAxis: {
                    min: 0,
                    title: {
                        text: '摄像机数量(台)'
                    }
                },series: [{
                    name: '摄像机',
                    data: [344, 218, 201, 50, 196, 205],
                    dataLabels: {
                        enabled: true,
                        rotation: -90,
                        color: '#FFFFFF',
                        align: 'right',
                        x: 4,
                        y: 10,
                        style: {
                            fontSize: '13px',
                            fontFamily: 'Verdana, sans-serif',
                            textShadow: '0 0 3px black'
                        }
                    }
                }]
            });
            
           

    });
    	
    
    </script>
</head>
<body>

	 <jsp:include page="include/nav.jsp"/> 
      
  
  	<!-- layout start -->
    <div id="cc" class="easyui-layout"  style="height:550px;">
    
    	 <div data-options="region:'south'" style="height:50px;">
    	 	
    	 	<jsp:include page="include/footer.jsp"/>
    	 </div>
        <div  class="easyui-accordion" data-options="region:'west'" title="菜单" style="width:20%;padding:10px">
            
            	<div title="平台介绍" style="padding:10px;" id="home_menu1">
            		 
        		</div>
        		<!-- 
        		<div title="系统工具" style="padding:10px;">
            		 <ul class="easyui-tree" data-options="url:'data/tree_data1.json',method:'get',animate:true"></ul>
        		</div>
        		 -->
           		<div title="系统设置" style="padding:10px;">
           			<ul id="sysset" class="easyui-tree" data-options="url:'data/tree_data2.json',method:'get'"></ul>
        		</div>
        		
        </div>
        <div id="center" data-options="region:'center'" title="主页介绍">
        	
        	<div id="container1" style="height:400px;min-width:450px;max-width:600px;margin: 0 auto;border: 0px solid red;float: left;"></div>
        	<div id="container2" style="height:400px;min-width:450px;max-width:600px;margin: 0 auto;border: 0px solid red;float: left;"></div>
        		
        </div>
        
    </div><!-- layout end -->


	<!-- 修改密码 弹出框--> 
 	<div id="w_modify_pwd" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'" title="新增用户" style="width:400px;height:200px;padding:5px;">
    	 <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'center'" style="padding:10px;">
            	<center>
                <form action="#" method="post">
                	 <table>
		                <tr>
		                    <td>原密码:</td>
		                    <td><input type="password" name="" class="" /></td>
		                </tr>
		                <tr>
		                    <td>新密码:</td>
		                    <td><input type="password" name="" class=""/></td>
		                </tr>
		                <tr>
		                    <td>确认密码:</td>
		                    <td><input type="password" name="" class=""/></td>
		                </tr>
		            </table>   
		        </form>
                </center>
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
                <a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'" href="javascript:void(0)"  style="width:80px">保存</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#w').window('close')" style="width:80px">退出</a>
            </div>
        </div>
    </div>
    <!-- 修改密码 end -->
    
    	<!-- 修改信息 弹出框--> 
 	<div id="w_modify_info" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'" title="新增用户" style="width:400px;height:200px;padding:5px;">
    	 <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'center'" style="padding:10px;">
            	<center>
                <form action="#" method="post">
                	 <table>
		                <tr>
		                    <td>用户名:</td>
		                    <td><input  name="" class="" value='${user.name}'/></td>
		                </tr>
		                <tr>
		                    <td>职位:</td>
		                    <td><input  name="" class=""/></td>
		                </tr>
		                <tr>
		                    <td>联系方式:</td>
		                    <td><input  name="" class=""/></td>
		                </tr>
		            </table>   
		        </form>
                </center>
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
                <a class="easyui-linkbutton btnSave" data-options="iconCls:'icon-ok'" href="javascript:void(0)"  style="width:80px">保存</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#w').window('close')" style="width:80px">退出</a>
            </div>
        </div>
    </div>
    <!-- 修改信息 end -->
</body>
</html>