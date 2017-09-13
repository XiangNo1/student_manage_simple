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
	
	
		
		<form style='float:left; margin-left:30%;margin-top:50px;margin-bottom:20px;' action='${pageContext.request.contextPath }/jsp/add_student.jsp' method="post">
			<input class="btn btn-primary" type='submit' value='添加'/>
		</form>
		<form style='float:left;margin-top:50px;margin-bottom:20px;' action='${pageContext.request.contextPath }/html/search_student.html' method="post">
			<input class="btn btn-primary" type='submit' value='查询'/>
			  欢迎回来: ${accounts.name } <a href="${pageContext.request.contextPath }/logout">退出登陆</a>
			  <a href="/Java1707Web2/jsp/zhuce.jsp">注册账号</a>
		<a href="/Java1707Web2/student?method=findAccounts">管理员登录</a>
		<a href="/Java1707Web2/student?method=findNowAccounts">当前管理员</a>
		
		</form>
		
		<div style='clear:both;'></div>
		<div>  
    <form id="searchForm" style='width:66%' class="container" action="${pageContext.request.contextPath}/student?method=searchByCondition" method="post">
    	<input type="hidden" name="pageIndex" id="pageIndex" />
  	 	    姓名:<input type="text" name="name" value="${searchCondition.name}"/>
    	   年龄:<input type="text" name="age" value="${searchCondition.age}"/>
   		    性别:<select id="gender" name="gender">
               <option value="">不限</option>
               <option value="男">男</option>
               <option value="女">女</option>
           	</select>&nbsp;&nbsp;&nbsp;
      	 <button class="btn btn-primary">搜索</button>
    </form>
</div>
		
		
	<table style='width:66%' cellspacing='0' class="container table table-bordered table-hover table-condensed">
		<tr>
			<td>编号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>性别</td>
			<td>地址</td>
			<td>生日</td>
			<td>删除</td>
			<td>修改</td>
		</tr>
	<c:forEach items="${requestScope.pageBean.list}" var="student">
		<tr>
			<td>${student.id}</td>
			<td>${student.name }</td>
			<td>${student.age }</td>
			<td>${student.gender }</td>
			<td>${student.address }</td>
			<td>${student.birthday }</td>
			<td><a href='javascript:delStudent(${student.id });'>删除</a></td>
			<td><a href='${pageContext.request.contextPath }/student?method=findById&id=${student.id }'>修改</a></td>
		</tr>
		</c:forEach>
		
	</table>
	<div class="container"  style='width:26%'>
					<nav aria-label="Page navigation">
			  <ul class="pagination">
			    <c:if test="${pageBean.pageIndex==1}">
		              <li class="disabled">
		                 <a href="javascript:void(0);" aria-label="Previous">
		                   <span aria-hidden="true">&laquo;</span>
		                 </a>
		              </li>
          		 </c:if>
		           <c:if test="${pageBean.pageIndex!=1}">
		              <li>
		                 <a href="javascript:goPage('${pageBean.pageIndex-1}');" aria-label="Previous">
		                   <span aria-hidden="true">&laquo;</span>
		                 </a>
		              </li>
		           </c:if>

			   <c:forEach begin="1" end="${pageBean.totalPage}" var="page">
              <c:if test="${pageBean.pageIndex!=page}">
                   <li><a href="javascript:goPage('${page }');">${page}</a></li>
              </c:if>
              <!-- 遍历的时候page和pageIndex相等，高亮显示 -->
              <c:if test="${pageBean.pageIndex==page}">
                   <li class="active"><a href="javascript:void(0);">${page}</a></li>
              </c:if>
           </c:forEach>

			  
			   
			 <c:if test="${pageBean.pageIndex == pageBean.totalPage}">
		              <li class="disabled">
		                 <a href="javascript:void(0);" aria-label="Previous">
		                   <span aria-hidden="true">&raquo;</span>
		                 </a>
		              </li>
          		 </c:if>
		           <c:if test="${pageBean.pageIndex!=pageBean.totalPage}">
		              <li>
		                 <a href="javascript:goPage('${pageBean.pageIndex+1}');" aria-label="Previous">
		                   <span aria-hidden="true">&raquo;</span>
		                 </a>
		              </li>
		           </c:if>
			 
			 
			 
			  </ul>
			</nav>
</div>
<script>
	function goPage(pageIndex){
		$("#pageIndex").val(pageIndex);
		$("#searchForm").submit();
	}
	
	
	$(function(){
	       $("#gender option[value='${searchCondition.gender}']").prop("selected", true);
	    });
	function delStudent(id) {
	       var isDel = confirm("您确认要删除吗？");
	       if (isDel) {
	           //要删除
	           location.href = "${pageContext.request.contextPath}/student?method=deleteStudentServlet&id="
	                  + id;
	       }
	    }


</script>
</body>
</html>