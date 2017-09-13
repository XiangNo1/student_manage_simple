package com.situ.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.pojo.Accounts;

public class LogoutServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if (session != null) {
			/*session.removeAttribute("userName");
			List<Accounts> onlineStudentList = (List<Accounts>) getServletContext().getAttribute("onlineStudentList");
			Accounts accounts = (Accounts) session.getAttribute("accounts");
			System.out.println(accounts + "guanli");
			onlineStudentList.remove(accounts);
			System.out.println(onlineStudentList+"退出");*/
			session.invalidate();
		}
		resp.sendRedirect(req.getContextPath() + "/html/login.html");
	}
}
