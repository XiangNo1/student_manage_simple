<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/bootstrap/css/bootstrap.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery/jquery-1.11.1.js"></script>
				<script type="text/javascript" src="${pageContext.request.contextPath }/lib/bootstrap/js/bootstrap.js"></script>

</script>
</head>
<body>
		<div class="container-fluid">
		
		<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">教务管理系统</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${pageContext.request.contextPath}/student?method=findStudentServlet"> <span class="glyphicon glyphicon-user" aria-hidden="true"></span> 学生管理 <span class="sr-only">(current)</span></a></li>
        <li><a href="${pageContext.request.contextPath}/banji?method=findBanjiServlet"> <span class="glyphicon glyphicon-home" aria-hidden="true"></span> 班级管理 </a></li>
        <li><a href="${pageContext.request.contextPath}/banji?method=findKechengServlet"> <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> 课程管理 </a></li>
        <li><a href="${pageContext.request.contextPath}/banji?method=findJiaowuServlet"> <span class="glyphicon glyphicon-tags" aria-hidden="true"></span> 教务管理 </a></li>
        <li><a href="#"> 欢迎回来:${accounts.name }</a> </li>
        
        
        
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
      
        <li><a href="${pageContext.request.contextPath }/logout"> 退出登陆</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Admin <span class="caret"></span></a>
          <ul class="dropdown-menu">
          
          <c:forEach items="${onlineStudentList }" var="accounts">
			<li><a href="#">${accounts.name } :  ${accounts.password }</a></li>
		</c:forEach>
		
          </ul>
        </li>
        
       
        
        
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
		</div>
		
		
		
		    <div class="row">
		        <div class="col-md-2">
		            <div class="list-group">
		                <a href="${pageContext.request.contextPath}/student?method=findStudentServlet" class="list-group-item">学生管理</a>
		                <a href="${pageContext.request.contextPath}/student?method=addStudentToServlet" class="list-group-item active">添加学生</a>
		            </div>
		        </div>
		        <div class="col-md-10">
		            <ul class="nav nav-tabs">
		                <li>
		                    <a href="${pageContext.request.contextPath}/student?method=findStudentServlet">学生管理</a>
		                </li>
		                <li class="active">
		                	<a href="${pageContext.request.contextPath}/student?method=addStudentToServlet">添加学生</a>
		                </li>
		            </ul>
				   <div style="width:60%; margin-top:20px;">
		            <div class="alert alert-danger" role="alert">请注意不要添加重复的姓名的学生！！！</div>
 <form action="/Java1707Web2/student?method=addStudentServlet" method="post">
   	    姓名：<input class="form-control" type="text" name="name" id="name"/><br/>
   	    <span id="nameInfo"></span><br/>
  	     年龄：<input class="form-control" type="text" name="age"/><br/>
	       性别：<input class="form-control" type="text" name="gender"/><br/>
	       地址：<input class="form-control" type="text" name="address"/><br/>
	          生日(xxxx-xx-xx)：<input class="form-control" type="text" name="birthday"/><br/>
	         班级:<select id="banji" name="banji"  class="form-control">
	         <c:forEach items="${banjiList }" var="banjis">
					               <option value="${banjis.name }">${banjis.name }</option>
					               
					       </c:forEach>        
					           	</select>
       <p><button class="btn btn-primary" type="submit">保存</button></p>
    </form>
</div>
</div>
</div>
<script type="text/javascript">



$(function() {
	$("#name").blur(function(){
        var name = $(this).val();
        $.post(
			"${pageContext.request.contextPath}/student?method=checkName",
					{"name":name},
					function(data) {
						if(data.isExit){
							$("#nameInfo").html("该用户已经存在");
							$("#nameInfo").css("color", "red");
						}else{
							$("#nameInfo").html("该名称可用");
							$("#nameInfo").css("color", "green");
						}
					},
					"json"
		);
	});
});
</script>
		
	</body>
</html>