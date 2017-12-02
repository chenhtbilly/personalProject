<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="sysListen" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询页面</title>
</head>
<body>
	<center>
		${requestScope.message} <br /> 
		${requestScope.maxId} <br /> 
		<h2>近期性能数据</h2>
		<table width="800" border="1">
			<tr>
				<th scope="col">时间点</th>
				<th scope="col">CPU使用率(%)</th>
				<th scope="col">内存使用率(%)</th>
				<th scope="col">硬盘使用率(%)</th>
				<th scope="col">发送字节数(b)</th>
				<th scope="col">接收字节数(b)</th>
			</tr>
			<c:forEach items="${requestScope.infos}" var="monitorInfo" varStatus="count">
				<tr>
					<td>${monitorInfo.savedTime}</td>
					<td>${monitorInfo.cpuRatio}</td>
					<td>${monitorInfo.memoryPercent}</td>
					<td>${monitorInfo.discUsage}</td>
					<td>${monitorInfo.txBytes}</td>
					<td>${monitorInfo.rxBytes}</td>
				</tr>
			</c:forEach>			
		</table>
	</center>
</body>
</html>


