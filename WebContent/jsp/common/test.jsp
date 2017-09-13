<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.situ.student.pojo.Student"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	<%
		pageContext.setAttribute("name", "zhangsan");
		request.setAttribute("name2", "lisi");
	%>
	${pageScope.name} ${requestScope.name}
	<hr/>
	<%
		Student student1 = new Student("zhangsan",21,"男","青岛",new Date());
	Student student2 = new Student("lisi",21,"男","青岛",new Date());
	List<Student> list = new ArrayList<Student>();
	list.add(student1);
	list.add(student2);
	application.setAttribute("list", list);
	%>
	${applicationScope.list[1].name}
	<br/>
	${name }<br/>
	${name2 }<br/>
	${list[0].name }
	${5>2 }
	${5+5 }
	${5-2 }
	
</body>
</html>