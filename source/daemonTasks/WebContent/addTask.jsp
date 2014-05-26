<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>Add Task</title>

<script type="text/javascript">
	function addTask()
	{
		var myform=document.forms[0];
		myform.action="addTask";
		myform.method="post";
		myform.submit();
	}
</script>
</head>
<body>
<s:a href="index.jsp">HOME</s:a><br/>
<form id="myform1">
Task name: 
<s:textfield name="taskName" value="Test"></s:textfield><br/>
<input type="button" name="btnadd" onclick="addTask()" value="submit" />
</form>
</body>
</html>