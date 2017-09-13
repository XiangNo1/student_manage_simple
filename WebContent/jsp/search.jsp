<%@page import="com.situ.student.pojo.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap.css" />
</head>
<body>

		
	<form style='float:left; margin-left:60%;margin-top:50px;margin-bottom:20px;' action='${pageContext.request.contextPath }/student?method=findStudentServlet' method="post">
		<input class="btn btn-primary" type='submit' value='返回列表'/>
	</form>
		<table style='width:66%' cellspacing='0' class="container table table-bordered table-hover table-condensed">
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>性别</td>
			<td>地址</td>
			<td>生日</td>
		</tr>
	<c:forEach items="${requestScope.list }" var="student">
		<tr>
			<td>${student.id }</td>
			<td>${student.name }</td>
			<td>${student.age }</td>
			<td>${student.gender }</td>
			<td>${student.address }</td>
			<td>${student.birthday }</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>