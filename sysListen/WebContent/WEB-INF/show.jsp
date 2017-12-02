<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="sysListen" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>性能信息图</title>

<script type="text/javascript">
	function myrefresh() {
		window.location.reload();
	}
	setTimeout('myrefresh()', 20000); //20秒刷新一次
</script>

</head>
<body>
	<center>
		${requestScope.message} <br />
		<h2>
			<font color="#6600CC">近期性能数据</font>
		</h2>
		<img alt="性能信息图" src="${sysListen}/${requestScope.image}" height="450"
			width="1300" />
	</center>
</body>
</html>


