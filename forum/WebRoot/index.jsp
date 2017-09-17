<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<c:set var="forum" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>风过无痕</title>
	<script  type="text/javascript"  src="jscss/jquery.js"></script>
    <script  type="text/javascript"  src="jscss/jquery.metadata.js"></script>
    <script  type="text/javascript"  src="jscss/jquery.validate.js"></script>
  	<link  rel="stylesheet"  type="text/css"  href="jscss/main.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css" >
		body
		{ 
			background-image:url(image/mainC.jpg);
			background-repeat:no-repeat;
			background-attachment:fixed;
			background-position:center;
		}
		.bottom
		{
			position: fixed; /*or前面的是absolute就可以用*/  
       		bottom: 20px;  
       		left:44%;
		}
	</style>
  </head>
  
  <body>
  	<center>
    	 <font  class="logoLabel"><a  href="theme_query.action">风过无痕</a></font>
  	</center>
  	<div class="bottom">电脑版&nbsp;&nbsp;|&nbsp;&nbsp;<a href="index.html">移动版</a></div>
  </body>
</html>
