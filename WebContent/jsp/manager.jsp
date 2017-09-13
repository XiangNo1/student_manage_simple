<%@page import="com.situ.student.pojo.Accounts"%>
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
	
		
		    
	<table style='width:66%' cellspacing='0' class="container table table-bordered table-hover table-condensed">
		<tr>
			<td>帐号</td>
			<td>密码</td>
			<td>删除</td>
			<td>修改</td>
		</tr>
	<c:forEach items="${requestScope.list }" var="accounts">
		<tr>
			<td>${accounts.name }</td>
			<td>${accounts.password }</td>
			<td><a href='${pageContext.request.contextPath }/student?method=deleteAccounts&name=${accounts.name}'>删除</a></td>
			<td><a href='${pageContext.request.contextPath }/student?method=findAccountsByName&name=${accounts.name}'>修改</a></td>
		</tr>
		</c:forEach>
	
		<tr>
			<td colspan="4"><a href="${pageContext.request.contextPath }/html/login.html">返回登录界面</a></td>
		</tr>
	</table>
</body>
</html>