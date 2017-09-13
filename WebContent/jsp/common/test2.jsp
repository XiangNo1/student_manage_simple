<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.situ.student.pojo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set value="18" var="age"></c:set>
	<c:if test="${age == 18}">今年18岁</c:if>
	<hr/>
	<c:set value="70" var="score"></c:set>
	<c:choose>
		<c:when test="${score >=90 && score <=100 }">优秀</c:when>
		<c:when test="${score >=80 && score <=90 }">良好</c:when>
		<c:when test="${score >=70 && score <=80 }">一般</c:when>
		<c:when test="${score >=60 && score <=70 }">合格</c:when>
		<c:otherwise>不及格</c:otherwise>
	</c:choose>
	
	<hr/>
	<c:forEach begin="0" end="6" var="i">${i }</c:forEach>
	<hr/>
	<%
		Student student1 = new Student("zhangsan",21,"男","青岛",new Date());
		Student student2 = new Student("lisi",21,"男","青岛",new Date());
		List<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		application.setAttribute("list", list);
	 %>
	<c:forEach items="${list }" var="student">${student.name } -- ${student.age }</c:forEach>
	<hr/>
	<%
		Map<String, Student> map = new HashMap<String, Student>();
		Student student3 = new Student("zhangsan",21,"男","青岛",new Date());
		Student student4 = new Student("lisi",21,"男","青岛",new Date());
		map.put("stu1", student3);
		map.put("stu2", student4);
		application.setAttribute("map", map);
	%>
	<c:forEach items="${map }" var="entry">${entry.key } -- ${entry.value.name } </c:forEach>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

</body>
</html>