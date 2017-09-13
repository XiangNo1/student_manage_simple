<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/Java1707Web2/fileUploadServlet" method="post" enctype="multipart/form-data">
	用户名：<input type="text" name="name"/>
	<input type="file" name="fileUpload"/>
	<input type="submit" value="提交"/>
	
	</form>

</body>
</html>