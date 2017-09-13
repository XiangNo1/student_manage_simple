package com.situ.student.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.situ.student.pojo.Accounts;

public class MyHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession httpSession = se.getSession();
		ServletContext servletContext = httpSession.getServletContext();
		List<Accounts> onlineStudentList = (List<Accounts>) servletContext.getAttribute("onlineStudentList");
		Accounts accounts = (Accounts) httpSession.getAttribute("accounts");
		if (accounts != null) {
			httpSession.removeAttribute("userName");
			onlineStudentList.remove(accounts);
		}
	}

}
