<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.situ.student.pojo.*"%>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
		<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/bootstrap/css/bootstrap.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/lib/jquery/jquery-1.11.1.js"></script>
				<script type="text/javascript" src="${pageContext.request.contextPath }/lib/bootstrap/js/bootstrap.js"></script>
		
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
        <li><a href="${pageContext.request.contextPath}/student?method=findStudentServlet"> <span class="glyphicon glyphicon-user" aria-hidden="true"></span> 学生管理 <span class="sr-only">(current)</span></a></li>
        <li><a href="${pageContext.request.contextPath}/banji?method=findBanjiServlet"> <span class="glyphicon glyphicon-home" aria-hidden="true"></span> 班级管理 </a></li>
        <li><a href="${pageContext.request.contextPath}/banji?method=findKechengServlet"> <span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> 课程管理 </a></li>
        <li class="active"><a href="${pageContext.request.contextPath}/banji?method=findJiaowuServlet"> <span class="glyphicon glyphicon-tags" aria-hidden="true"></span> 教务管理 </a></li>
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
		                <a href="${pageContext.request.contextPath}/banji?method=findJiaowuServlet" class="list-group-item active">教务查询</a>
		                <a href="${pageContext.request.contextPath}/banji?method=manageJiaowuServlet" class="list-group-item">为班级添加课程</a>
		            </div>
		        </div>
		        <div class="col-md-10">
		            <ul class="nav nav-tabs">
		                <li class="active">
		                    <a href="${pageContext.request.contextPath}/banji?method=findJiaowuServlet">教务查询</a>
		                </li>
		                <li>
		                    <a href="${pageContext.request.contextPath}/banji?method=manageJiaowuServlet">为班级添加课程</a>
		                </li>
		            </ul>
				      
				      
				       <form id="searchForm" class="container" action="${pageContext.request.contextPath}/banji?method=searchBanjiKecheng" method="post">
					           	 班级:<select id="banjiSearch" name="banjiSearch">
					           	 <option value="">不限</option>
	        					 <c:forEach items="${banjiList }" var="banjiSearchs">
					              	 <option value="${banjiSearchs.id }">${banjiSearchs.name }</option>
					               
					     		  </c:forEach>        
					           	</select>
					           	&nbsp;&nbsp;&nbsp;
					      	 <button class="btn btn-primary">搜索</button>
					    </form>
				      
		            <table class="table">
		            <tr>
		            	<td>班级id</td>
		            	<td>班级名称</td>
		            	<td>课程</td>
		            </tr>
		                <c:forEach items="${requestScope.list.list }" var="banji">
				            <tr>
				               <td>${banji.id }</td>
				                	<td>${banji.name }</td>
				                	<td>
				                	<c:forEach items="${banji.list }" var="kecheng">
				                		${kecheng.id }:${kecheng.name }&nbsp;
				                		<a href="${pageContext.request.contextPath }/banji?method=deleteBanjiKecheng&&banji_id=${banji.id }&&kecheng_id=${kecheng.id }">删除</a>
				                		&nbsp;&nbsp;&nbsp;
				                	
				                	</c:forEach>
				                	
				                	</td>
				            </tr>
		                </c:forEach>
		            </table>
		      </div>
		      </div>
		      
		       <!-- 分页开始 -->
				<nav aria-label="Page navigation" class="pull-right">
				 <ul class="pagination">
			    <c:if test="${list.pageIndex==1}">
		              <li class="disabled">
		                 <a href="javascript:void(0);" aria-label="Previous">
		                   <span aria-hidden="true">&laquo;</span>
		                 </a>
		              </li>
          		 </c:if>
		           <c:if test="${list.pageIndex!=1}">
		              <li>
		                 <a href="${pageContext.request.contextPath}/banji?method=findJiaowuServlet&pageIndex=${list.pageIndex-1}" aria-label="Previous">
		                   <span aria-hidden="true">&laquo;</span>
		                 </a>
		              </li>
		           </c:if>

			   <c:forEach begin="1" end="${list.totalPage}" var="page">
              <c:if test="${list.pageIndex!=page}">
                   <li><a href="${pageContext.request.contextPath}/banji?method=findJiaowuServlet&pageIndex=${page}">${page}</a></li>
              </c:if>
              <!-- 遍历的时候page和pageIndex相等，高亮显示 -->
              <c:if test="${list.pageIndex==page}">
                   <li class="active"><a href="javascript:void(0);">${page}</a></li>
              </c:if>
           </c:forEach>

			  
			   
			 <c:if test="${list.pageIndex == list.totalPage}">
		              <li class="disabled">
		                 <a href="javascript:void(0);" aria-label="Previous">
		                   <span aria-hidden="true">&raquo;</span>
		                 </a>
		              </li>
          		 </c:if>
		           <c:if test="${list.pageIndex!=list.totalPage}">
		              <li>
		                 <a href="${pageContext.request.contextPath}/banji?method=findJiaowuServlet&pageIndex=${list.pageIndex+1}" aria-label="Previous">
		                   <span aria-hidden="true">&raquo;</span>
		                 </a>
		              </li>
		           </c:if>
			 
			 
			 
			  </ul>
				</nav>
				<!-- 分页结束 -->
		     
		
	<script>
	
	
	
	

	$(function(){
	       $("#banjiSearch option[value='${banjiId }']").prop("selected", true);
	    });
	
</script>	
	</body>
</html>
