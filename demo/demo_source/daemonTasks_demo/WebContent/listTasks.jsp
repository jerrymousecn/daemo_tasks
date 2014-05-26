<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>Add Task</title>

<script type="text/javascript">
	function delTask(taskID) {
		var myform = document.forms[0];
		myform.action = "delTask?taskID="+taskID;
		myform.method = "post";
		myform.submit();
	}
	function stopTask(taskID) {
		var myform = document.forms[0];
		myform.action = "stopTask?taskID="+taskID;
		myform.method = "post";
		myform.submit();
	}
	function refresh() {
		var myform = document.forms[0];
		myform.action = "listTasks";
		myform.method = "post";
		myform.submit();
	}
</script>
</head>
<body>
<s:a href="index.jsp">HOME</s:a><br/>
<s:if test="#taskMap.size()==0">
	No tasks.
	Click <s:a href="addTask.jsp">Add Task</s:a> to add tasks.
</s:if>
<form id="myform1">
<table>
<s:else>
<tr>
	<td>Task Name</td>
	<td>Progress</td>
	<td>Operation</td>
</tr>
</s:else>
<s:iterator value="taskMap" id="column">
	<tr>
		<td><s:property value="value.taskName"/></td>
		<td><s:property value="value.progress"/></td>
		<td>
		<s:if test="%{value.isTerminated}">
			<input type=button onclick="delTask('<s:property value="value.taskID"/>')" value="Delete"></input>
		</s:if>
		<s:else>
			<input type=button onclick="stopTask('<s:property value="value.taskID"/>')" value="Stop"></input>
		</s:else>
		</td>
	</tr>
</s:iterator>
	<tr>
	<td colspan="3"><s:submit value="Refresh" onclick="refresh()"></s:submit></td> 
	</tr>
</table>
</form>
</body>
</html>