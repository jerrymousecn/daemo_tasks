<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
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
<form id="myform1">
Task name: <input type="text" name="taskName" value="Test"/><br />
<input type="button" name="btnadd" onclick="addTask()" value="submit" />
</form>
</body>
</html>