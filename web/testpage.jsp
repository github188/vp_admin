<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页页面</title>

<link rel="stylesheet" href="js/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
		<table  class="table table-hover">
			<caption>用户基本信息分页展示</caption>
			<thead>
			<tr>
				<th>序号</th>
				<th>名称</th>
				<th>密码</th>
			</tr>
			</thead>
			<tbody>
			<s:iterator value="pageResultSet.list" id="user" status="s">
			<tr>
				<td><s:property value="#s.index+1"/></td>
	 			<td><s:property value="#user.id"/>/<s:property value="#user.name"/></td>
	 			<td><s:property value="#user.password"/></td>
	 		</tr>
			</s:iterator>
			</tbody>
		</table>
		
		<ul class="pager">
		
			<s:if test="pageResultSet.pageInfo.hasPrevious==true">
			<li class="previous">
				<a href="action_testAction_test_testpage_index?pageResultSet.pageInfo.currentPage=${pageResultSet.pageInfo.currentPage-1 }">&larr;上一页</a>
			</li>
			
			</s:if>
			<s:else>
				<li class="previous disabled"><a href="#">&larr; 上一页</a></li>
			</s:else>

			<li class="disabled">
				<a href="#">${pageResultSet.pageInfo.currentPage }/${pageResultSet.pageInfo.totalPage }</a>
			</li>
			
			<s:if test="pageResultSet.pageInfo.hasNext==true">
			<li class="next"><a href="action_testAction_test_testpage_index?pageResultSet.pageInfo.currentPage=${pageResultSet.pageInfo.currentPage+1 }">下一页 &rarr;</a></li>
			</s:if>
			<s:else>
  			<li class="next disabled"><a href="#">下一页 &rarr;</a></li>
  			</s:else>
		</ul>
		
		
</body>
</html>