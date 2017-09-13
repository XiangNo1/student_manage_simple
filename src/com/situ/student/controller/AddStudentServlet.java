package com.situ.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.exception.NameRepeatException;
import com.situ.student.pojo.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.StudentServiceImpl;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

public class AddStudentServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
           throws ServletException, IOException {
       //1.接收参数
       String name = req.getParameter("name");
       String age = req.getParameter("age");
       String gender = req.getParameter("gender");
       String address = req.getParameter("address");
       String birthday = req.getParameter("birthday");
       System.out.println("name:" + name);
       System.out.println("age:" + age);
       System.out.println("gender:" + gender);
       System.out.println("address:" + address);
       System.out.println("birthday:" + birthday);
       //2.处理业务
       SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
       Date date = null;
       try {
          date = simpleDateFormat.parse(birthday);
       } catch (ParseException e) {
          e.printStackTrace();
       }

       
   
       /*System.out.println("请求方式： " + req.getMethod());
       System.out.println("访问路径： " + req.getServletPath());
       System.out.println("HTTP协议： " + req.getProtocol());
       Enumeration<String> enumeration = req.getHeaderNames();
       while (enumeration.hasMoreElements()) {
		String key =  enumeration.nextElement();
		String value = req.getHeader(key);
		System.out.println(key + " : " + value);
	}*/
        
       Student student = new Student(name, Integer.parseInt(age), gender,address,date);
       IStudentService studentService = new StudentServiceImpl();
       boolean result = false;
       try {
           result = studentService.add(student);
       } catch (NameRepeatException e) {
           e.printStackTrace();
       }
       //3.输出响应
      /*  resp.setContentType("text/html;charset=utf-8");
       PrintWriter printWriter = resp.getWriter();
       if (result) {
           printWriter.println("保存成功");
       } else {
           printWriter.println("保存失败");
       }*/
       //重定向
       resp.sendRedirect("/Java1707Web2/find");
    }
}

