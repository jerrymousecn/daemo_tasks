<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-ui.js"></script>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
var intervalId; 
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, ""); 
} 
function getprogress() {
	htmlobj=$.ajax({url:"getTaskProgress.jsp?taskid="+'<s:property value="taskid"/>',cache:false,async:false});
	str = trim(htmlobj.responseText);
	$("#myDiv").html(str);
	$( "#progressbar" ).progressbar({
	      value: parseInt(str)
	});
	if(parseInt(str)>=100)
	{
		clearInterval(intervalId);
	}
}

$(document).ready(function(){
	intervalId = setInterval(getprogress, 1000);
});

</script>
</head>
<body>
Task [<s:property value="taskName"/>]progress:
	<div id="progressbar"></div>
	<div id="myDiv"></div>
</body>
</html>
