<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<c:set var="forum" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" /> --> 
<meta name="apple-mobile-web-app-capable" content="yes" /> 
<meta name="apple-mobile-web-app-status-bar-style" content="black" />        
<meta name="format-detection" content="telephone=no" />
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${theme.title}</title>
	<script  type="text/javascript"  src="${forum}/jscss/jquery.js"></script>
	<style  type="text/css"></style>
    <script  type="text/javascript"  src="${forum}/jscss/jquery.metadata.js"></script>
    <script  type="text/javascript"  src="${forum}/jscss/jquery.validate.js"></script>
	<link  rel="stylesheet"  type="text/css"  href="jscss/main.css"> 
	<% response.setHeader("cache-control", "no-store");%>
</head>
<body>


<div  style="margin: 15px auto; ">
	
<a href="./index.jsp" style="text-decoration: none;font-size: 17">返回主页</a>	<br/><br/>         
<center><form  id="SearchAction?method=search"  name="SearchAction?method=search"   action="#"  method="post"  class="simpleSearchForm">
	    <font  class="logoLabel">风过无痕</font>
	    <input  type="text"  name="queryString"  value=""  id="SearchAction?method=search_queryString"  class="queryString">
		<input  type="submit"  id="SearchAction?method=search_0"  value="搜 索">

	</form></center>

</div>

<div  class="menubar">
	<a  href="theme_query.action">主题列表</a>
</div>

<div  style="padding: 10px 30px; font-size: 12px; font-family:&#39;宋体&#39;">
	共有<font  color="red">${fn:length(theme.replies)+1}</font>篇帖子
</div>

	<table  class="postList"  cellspacing="0">
		<tbody>
			<tr  class="title">
	        <td  width="20"  class="num">1</td>
	        <td>${theme.title}</td>
		    </tr>
		    <tr  class="content">
		        <td></td>
		        <td><pre>${theme.content}</pre></td>
		    </tr>
		    <tr  class="info">
		        <td></td>
		        <td>
					作者：<font  color="blue">${theme.ip}</font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            <font  color="#999999">${fn:substring(theme.ptime,0,19)}</font>
		        </td>
		    </tr>
		</tbody>
	</table>
	<c:forEach items="${theme.replies}" var="reply" varStatus="num"> 
	<table  class="postList"  cellspacing="0">
        <tbody><tr  class="title">
            <td  width="20"  class="num">${num.count+1}</td>
            <td></td>
        </tr>
        <tr  class="content">
            <td></td>
            <td><pre>${reply.content}</pre></td>
        </tr>
        <tr  class="info">
            <td></td>
            <td>
				作者：<font  color="blue">${reply.ip}</font> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <font  color="#999999">${fn:substring(reply.rtime,0,19)}</font>
            </td>
        </tr>
   		</tbody>
    </table>
	</c:forEach>

<div  style="margin-bottom: 20px"></div>


<form  id="TopicAction_addNewReply"  name="TopicAction_addNewReply"  action="reply_save.action"  method="post"  class="addNewTopicForm">
    <input  type="hidden"  name="topicId"  value="67"  id="TopicAction_addNewReply_topicId">
    <table  class="publishArticleForm">
        <tbody><tr>
            <td  class="label">内　容:</td>
            <td><textarea  name="content"  cols=""  rows=""  id="TopicAction_addNewReply_content"  class="content {required:true, messages:{required:&#39;请填写文章内容！&#39;}}"></textarea></td>
        </tr>
        <tr>
            <td><input type="hidden" name=tid value="${theme.id}"/></td>
            <td>
            	<input  type="submit"  id="TopicAction_addNewReply_0"  value="发　表">
			</td>
        </tr>
    </tbody></table>
</form>




<script  type="text/javascript">
	$("form.addNewTopicForm").validate();
</script>




</body>
</html>
