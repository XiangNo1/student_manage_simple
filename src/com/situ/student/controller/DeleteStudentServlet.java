package com.situ.student.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.student.service.IStudentService;
import com.situ.student.service.StudentServiceImpl;

public class DeleteStudentServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	String id = req.getParameter("id");
	IStudentService studentService = new StudentServiceImpl();
	studentService.deleteById(Integer.parseInt(id));
	resp.sendRedirect("/Java1707Web2/find");
	}
}
