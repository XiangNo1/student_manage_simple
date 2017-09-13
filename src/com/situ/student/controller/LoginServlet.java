package com.situ.student.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.StudentServiceImpl;

public class LoginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		
		String checkCode = req.getParameter("checkCode");
		String checkCodeSession = (String) req.getSession().getAttribute("checkCodeSession");
		if (checkCode == null || checkCode.equals("")) {
			resp.sendRedirect(req.getContextPath() + "/html/login.html");
			return;
		}
		if (!checkCode.equalsIgnoreCase(checkCodeSession)) {
			resp.sendRedirect(req.getContextPath() + "/html/login.html");
			return;
		}
		
		
		
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		IStudentService studentService = new StudentServiceImpl();
		List<Accounts> list = studentService.findAccountsByName(name);
		String name2 = null;
		String password2 = null;
		for (Accounts accounts : list) {
			System.out.println(accounts.getName());
			System.out.println(accounts.getPassword());
			if (name.equals(accounts.getName()) && password.equals(accounts.getPassword())) {
				name2 = accounts.getName();
				password2 = accounts.getPassword();
				
			}
		}
		if(name2 != null){
			HttpSession session = req.getSession();
			List<Accounts> onlineStudentList = (List<Accounts>) getServletContext().getAttribute("onlineStudentList");
			Accounts accounts = new Accounts(name2, password2);
			session.setAttribute("userName", name2);
			session.setAttribute("accounts", accounts);
			onlineStudentList.add(accounts);
			System.out.println("LoginServlet.service() 登录页面" );
			System.out.println(onlineStudentList);
			resp.sendRedirect(req.getContextPath() + "/student?method=findStudentServlet");
		}
		else{
			resp.sendRedirect(req.getContextPath() + "/html/fail.html");
		}
	}
}
