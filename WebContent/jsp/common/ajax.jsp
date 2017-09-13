<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-1.11.1.js"></script>

</head>
<body>

	<input type="button" value="get方式访问服务器" onclick="fn1()"/>
	<input type="button" value="json" onclick="fn2()"/>
	
	<script type="text/javascript">
	function fn1(){
		// 1）创建Ajax引擎对象
		var xmlHttpRequest = new XMLHttpRequest();
		// 2）为Ajax引擎对象绑定监听（监听服务器已将数据响应给引擎）
		xmlHttpRequest.onreadystatechange = function(){
			// 5）接受响应数据
			if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.readyState == 200){
				var result = xmlHttpRequest.responseText;
				alert(result);
			}
		}	
		// 3）绑定提交地址
		xmlHttpRequest.open("GET","${pageContext.request.contextPath}/ajax?name=zhangsan");
		
		// 4）发送请求
		xmlHttpRequest.send();
		

	}
	function fn2(){
		$.get(
				"${pageContext.request.contextPath}/json",
				{"name" : name, "age" : 20},
				function(data){
					alert(data.name + " " + data.age);
					
				},
				"json"
				
				);
	}
	

</script>
</body>
</html>