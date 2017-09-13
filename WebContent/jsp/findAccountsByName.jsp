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
	

		<form action="${pageContext.request.contextPath }/student?method=updateAccounts" method="post">
 		帐号：<input class="form-control" type="text" name="name" value="${requestScope.list[0].name }" readonly="readonly"/><br/>
   		    密码：<input class="form-control" type="text" name="password" value="${requestScope.list[0].password }"/><br/>
     	  <p><button class="btn btn-primary" type="submit">修改</button></p>
   	 </form>	

</body>
</html>