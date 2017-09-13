<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/bootstrap.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/jquery-1.11.1.js">
	</script>
</head>
<body>

	<table style='width:66%' cellspacing='0' class="container table table-bordered table-hover table-condensed">
		<tr>
			<td>帐号</td>
			<td>密码</td>
		</tr>
	<c:forEach items="${onlineStudentList }" var="accounts">
		<tr>
			<td>${accounts.name }</td>
			<td>${accounts.password }</td>
		</tr>
		</c:forEach>
	
		<tr>
			<td colspan="4"><a href="${pageContext.request.contextPath }/student?method=findStudentServlet">返回</a></td>
		</tr>
	</table>


</body>
</html>