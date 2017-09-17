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
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主题列表-风过无痕</title>
    <script  type="text/javascript"  src="${forum}/jscss/jquery.js"></script>
    <script  type="text/javascript"  src="${forum}/jscss/jquery.metadata.js"></script>
    <script  type="text/javascript"  src="${forum}/jscss/jquery.validate.js"></script>
    <script  type="text/javascript">
        $(function() {
            $("tbody.list>tr:even").css("backgroundColor", "#EEEEEE");
        });
    </script>
	<link  rel="stylesheet"  type="text/css"  href="jscss/main.css">
<style  type="text/css"></style></head>
<body>


<div  style="margin: 15px auto; ">
	
<a href="./index.jsp" style="text-decoration: none;font-size: 17">返回主页</a>	<br/><br/>	        
<center><form  id="SearchAction?method=search"  name="SearchAction?method=search"   action="#"  method="post"  class="simpleSearchForm">
	    <font  class="logoLabel">风过无痕</font>
	    <input  type="text"  name="queryString"  value=""  id="SearchAction?method=search_queryString"  class="queryString required">
		<input  type="submit"  id="SearchAction?method=search_0"  value="搜 索">

	</form></center>
</div>

<div  class="menubar">
</div>

<table  cellspacing="0">
<tbody  class="list topicList">
    
    <tr  class="title"  style="background-color: rgb(238, 238, 238);">
        <td  width="25">编号</td>
        <td  width="25">回复</td>
        <td  width="500">标题</td>
        <td  width="110">作者</td>
        <td  width="145">最后回复时间</td>
    </tr>

    
    <c:forEach items="${requestScope.themeList}" var="theme" varStatus="num">
        <tr  class="data">
            <td  class="num">${theme.id}</td>
            <td  class="num">
				${fn:length(theme.replies)}
			</td>
            <td><a  href="theme_get.action?id=${theme.id}">${theme.title}</a></td>
            <td  class="info">${theme.ip}</td>
            <td  class="info">${fn:substring(theme.lastptime,0,19)}</td>
        </tr>
    </c:forEach>
    
	<!-- 主题总数为奇数则有灰色背景，否则为白色背景-->
    <tr 
    	<c:if test="${requestScope.themeSize%2==1}">
    		style="background-color: rgb(238, 238, 238);"
    	</c:if>
    >
        <td  colspan="5"  class="num">共有主题数<font  color="red">${requestScope.themeSize}</font>个</td>
    </tr>
    </tbody>
</table>

<div  style="margin-bottom: 15px"></div>


<form  id="TopicAction_addNewTopic"  name="TopicAction_addNewTopic"  action="theme_save.action"  method="post"  class="addNewTopicForm">
    <table  class="publishArticleForm">
        <tbody><tr>
            <td>标　题:</td>
            <td><input  type="text"  name="title"  value=""  id="TopicAction_addNewTopic_title"  class="title {required:true, maxlength:255, messages:{required:&#39;请填写文章标题！&#39;,maxlength:&#39;标题字数超过了255的长度限制!&#39;}}"></td>
        </tr>
        <tr>
            <td>内　容:</td>
            <td><textarea  name="content"  cols=""  rows=""  id="TopicAction_addNewTopic_content"  class="content {required:true, messages:{required:&#39;请填写文章内容！&#39;}}"></textarea></td>
        </tr>
        <tr>
            <td></td>
            <td><input  type="submit"  id="TopicAction_addNewTopic_0"  value="发　表">
</td>
        </tr>
    </tbody></table>
</form>

<script  type="text/javascript">
	$("form.addNewTopicForm").validate();
</script>


</body>
</html>