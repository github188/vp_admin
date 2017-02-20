<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户操作</title>
<script type="text/javascript">
	//var myfrom=document.getElementById("myform1");

	
	function addUser()
	{
		var myform=document.forms[0];
		myform.action="addUser_success";
		myform.method="post";
		myform.submit();
	}
	
	function modifyUser()
	{
		var myform=document.forms[0];
		myform.action="modifyUser_success";
		myform.method="post";
		myform.submit();
	}
	
	function deleteUser()
	{
		var myform=document.forms[0];
		myform.action="deleteUser_success";
		myform.method="post";
		myform.submit();
	}
	
	function queryUser(){
		var myform=document.forms[0];
		myform.action="select_userMain";
		myform.method="post";
		myform.submit();
	}
	

</script>
</head>
<body>
	<form id="myform1" >
		用户名：<input type="text" name="user.name" value="${user.name }"/><br />
		密码：<input type="text" name="user.password" value="${user.password }"/><br />
		年龄：<input type="text" name="user.age" value="${user.age }"/><br />
		<input type="button" name="btnadd" onclick="addUser()" value="增加" />
		<input type="button" name="btnmodify" onclick="modifyUser()" value="修改" />
		<input type="button" name="btndel" onclick="deleteUser()" value="删除"  />
		<input type="button" name="btnQuery" onclick="queryUser()" value="查询"  />
 	</form>
 	<br>
 	
 	<a href="select_userMain">list</a>
 	<br>
 	<s:iterator value="list" id="user" status="s">
 		<s:property value="#s.index+1"/>
 		<s:property value="#user.name"/>	
 	</s:iterator>
 	
 	<br>
 	<table border="1">
 		<tr>
 			<th>序号</th><th>姓名</th><th>年龄</th><th>操作</th>
 		</tr>
 		<s:iterator value="list" id="user" status="s">
 		<tr>
 			
 			<td><s:property value="#s.index"/></td>
 			<td><s:property value="#user.name"/></td>
 			<td><s:property value="#user.age"/></td>
 			<td><a href="deleteUser_success?user.id=<s:property value='#user.id'/>">删除</a></td>
 			<td><a href="selectById_userMain?user.id=<s:property value='#user.id'/>">编辑</a></td>
 			
 		</tr>
 		</s:iterator>
 	</table>
 
 	
</body>
</html>