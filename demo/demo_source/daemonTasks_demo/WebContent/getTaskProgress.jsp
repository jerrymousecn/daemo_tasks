<%@page import="cn.jerry.tools.tasks.TaskManager"%>
<%
String taskID = request.getParameter("taskid");
TaskManager taskManager = TaskManager.getInstance();
%>
<%=taskManager.getProgress(taskID)%>
