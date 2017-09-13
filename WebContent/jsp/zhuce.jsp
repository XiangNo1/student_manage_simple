<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/bootstrap/css/bootstrap.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery/jquery-1.11.1.js"></script>
				<script type="text/javascript" src="${pageContext.request.contextPath }/lib/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<div style="width:30%; margin-top:150px;" class="container">
	<form action="/Java1707Web2/student?method=zhuce" method="post">
		账号：<input class="form-control" type="text" name="name"/><br/>
		密码：<input class="form-control" type="text" name="password"/>
		<input class="btn btn-primary" type="submit" value="注册"/>
	</form>
	</div>
</body>
</html>