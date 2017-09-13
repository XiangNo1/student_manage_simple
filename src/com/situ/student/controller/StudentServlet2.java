package com.situ.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.pojo.Student;

public class StudentServlet2 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		List<Student> list = (List<Student>) req.getAttribute("list");
		for (Student student : list) {
			System.out.println(student);
		}
		 //3.控制界面跳转
		   //乱码问题
		    resp.setContentType("text/html;charset=utf-8");
		   PrintWriter printWriter = resp.getWriter();
		   printWriter.println("<form action='/Java1707Web2/find.do'>");
		   printWriter.println("<input type='submit' value='返回列表'/>");
		   printWriter.println("</form>");
		   printWriter.println("<table border='1' cellspacing='0'>");
		   printWriter.println("    <tr>");
		   printWriter.println("         <td>编号</td>");
		   printWriter.println("         <td>姓名</td>");
		   printWriter.println("         <td>年龄</td>");
		   printWriter.println("         <td>性别</td>");
		   printWriter.println("         <td>地址</td>");
		   printWriter.println("         <td>生日</td>");
		   printWriter.println("    </tr>");
		   for (Student student : list) {
		       printWriter.println("<tr>");
		       printWriter.println("    <td>" + student.getId() + "</td>");
		       printWriter.println("    <td>" + student.getName() + "</td>");
		       printWriter.println("    <td>" + student.getAge() + "</td>");
		       printWriter.println("    <td>" + student.getGender() + "</td>");
		       printWriter.println("    <td>" + student.getAddress() + "</td>");
		       printWriter.println("    <td>" + student.getBirthday() + "</td>");
		       printWriter.println("</tr>");
		   }
		   printWriter.println("</table>");
		   printWriter.close();
	
	}
	
}
