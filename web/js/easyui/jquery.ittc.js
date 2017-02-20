(function ($) {
	$.fn.extend({
		"easyuiPage": function (url,data) {
			///<summary>
			///url:获取JSON的路径
			///data除了pageNum和pageSize的参数
            /// 在Action声明int型变量pageNum(页面)和pageSize(每页显示条数)
            ///</summary>
			var $this=$(this);
			var pg = $this.datagrid("getPager");
            if(pg){
            	$(pg).pagination({
            		pageSize:10,
         			pageList:[5,10,15],
         			beforePageText: '第',//页数文本框前显示的汉字 
         			afterPageText: '页    共 {pages} 页', 
         			displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
         			onBeforeRefresh:function(){
         						
         			},onRefresh:function(pageNum,pageSize){
         				$this.datagrid("reload");
         			},onChangePageSize:function(pageSize){
         						
         			},onSelectPage:function(pageNum,pageSize){
         				$.ajax({
         					url:url,
         					data:data+"&pageNum="+parseInt(pageNum)+"&pageSize="+(pageSize),
         					type:"POST",
         					cache:false,
         					dataType:"json",
         					success:function(json){
         						$this.datagrid("loadData",json);		
         					}
         				});
         			}
         		});
         	}else{
         		 $.messager.alert("提示","每页获取到datagrid组件！")
         	}
          },"ajaxForm":function(url){
        	  	///<summary>
  				///url:获取JSON的路径
  				///返回的JSON有success的返回结果
             	/// 
              	///</summary>
        	  $this=$(this);
        	  $.ajax({
        		   url:url,
        		   data:$this.serialize(),
        		   type:"POST",
        		   cache: false,
        		   dataType:"json",
        		   success:function(data){
        			   var jsonData=eval("("+data+")");//转换为json对象  
        			   if(jsonData.success){
        				  
        				   $(".refresh-tree").tree('reload');
        				   $(".refresh-dg").datagrid("reload");
        				   $($this).parents('.easyui-window').window('close');
        				   $.messager.alert("提示","操作成功！");
        			   }else{
        				   $.messager.alert("提示","操作失败！");
        			   }
        			   //$('#w_add_role').window('close');
        			  // $('.easyui-tree').tree('reload');
        			  // $("#dg").datagrid("reload");
        		   },error:function(data){
        			   $.messager.alert("提示","操作失败！"+data);
        		   }
        	   });
        	  
          },"ittc":function(){
        	  $.messager.alert("提示","测试ittc")
          }
	});
})(jQuery);