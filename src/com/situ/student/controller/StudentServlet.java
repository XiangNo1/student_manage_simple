package com.situ.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.websocket.Session;

import org.apache.catalina.deploy.SessionConfig;

import com.situ.student.exception.NameRepeatException;
import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Banji;
import com.situ.student.pojo.PageBean;
import com.situ.student.pojo.SearchCondition;
import com.situ.student.pojo.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.StudentServiceImpl;
import com.sun.xml.internal.bind.api.ErrorListener;

public class StudentServlet extends BaceServlet {

	/*@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();
		if ("/add.do".equals(path)) {
			 addStudentServlet(req, resp);
		}else if("/find.do".equals(path)) {
			findStudentServlet(req, resp);
		}else if ("/delete.do".equals(path)) {
			deleteStudentServlet(req, resp);
		}else if ("/update.do".equals(path)) {
			updateStudentServlet(req, resp);
		}else if ("/search.do".equals(path)) {
			searchStudentServlet(req, resp); 
		}else if("/findById.do".equals(path)){
			findById(req, resp);
		}else if("/zhuce.do".equals(path)){
			zhuce(req, resp);
		}else if("/findAccounts.do".equals(path)){
			findAccounts(req,resp);
		}else if ("/findAccountsByName.do".equals(path)) {
			findAccountsByName(req, resp);
		}else if ("/updateAccounts.do".equals(path)) {
			updateAccounts(req, resp);
		}else if ("/deleteAccounts.do".equals(path)) {
			deleteAccounts(req, resp);
		}
	
	}*/
	private void addStudentToServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/jsp/add_student.jsp").forward(req, resp);
		
	}
	
	
	private void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String[] ids = req.getParameterValues("selectIds");
		IStudentService studentService = new StudentServiceImpl();
		if (ids != null && ids.length == 0)  {
			studentService.deleteAll(ids);
		}
		resp.sendRedirect("/Java1707Web2/student?method=findStudentServlet");
		
	}
	
	private void findNowAccounts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();
		ServletContext servletContext = session.getServletContext();
		List<Accounts> onlineStudentList = (List<Accounts>) getServletContext().getAttribute("onlineStudentList");
		System.out.println(onlineStudentList);
		req.setAttribute("onlineStudentList", onlineStudentList);
		req.getRequestDispatcher("/jsp/findNowAccounts.jsp").forward(req, resp);
	}
	
	private void checkName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		IStudentService studentService = new StudentServiceImpl();
		String name = req.getParameter("name");
		boolean isExit = studentService.checkName(name);
		resp.setContentType("charset=utf-8");
		resp.getWriter().write("{\"isExit\":" + isExit + "}");
	}
	private void pageList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		/*private Integer pageIndex;
		private Integer pageSize;
		private Integer totalCount;
		private Integer totalPage;
		private List<Student> list = new ArrayList<Student>();*/
		String pageIndexStr = req.getParameter("pageIndex");
		String pageSizeStr = req.getParameter("pageSize");
		int pageIndex = 1;
		if (pageIndexStr!= null && !pageIndexStr.equals("")) {
			pageIndex = Integer.parseInt(pageIndexStr);
		}
		int pageSize = 3;
		if (pageSizeStr != null && !pageSizeStr.equals("")) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		IStudentService studentService = new StudentServiceImpl();
		PageBean pageBean = studentService.getPageBean(pageIndex,pageSize);
		System.out.println(pageBean);
		req.setAttribute("pageBeen", pageBean);
		req.getRequestDispatcher("/jsp/find.jsp").forward(req, resp);
		
		
	}
	
	
	private void searchByCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
	   /* String name = req.getParameter("name");
	    String age = req.getParameter("age");
	    String gender = req.getParameter("gender");
	    SearchCondition searchCondition = new SearchCondition(name, age, gender);
	    System.out.println(searchCondition.toString());
	    IStudentService studentService = new StudentServiceImpl();
	    List<Student> list = studentService.searchByCondition(searchCondition);
	    req.setAttribute("searchCondition", searchCondition);
	    req.setAttribute("list", list);
	    req.getRequestDispatcher("/jsp/find.jsp").forward(req, resp);*/
		
		/*
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");*/
		   //1.接收请求参数，封装成对象
		   //2.业务处理
		  // IStudentService studentService = new StudentServiceImpl();
		  // List<Student> list = studentService.findAll();
		   //3.控制界面跳转
		   //乱码问题
		/*   HttpSession session = req.getSession(false);
	       if (session == null) {
	           resp.sendRedirect(req.getContextPath() + "/html/login.html");
	           return;
	       }
	       String userName = (String) session.getAttribute("userName");
	       if (userName == null) {
	           resp.sendRedirect(req.getContextPath() + "/html/login.html");
	           return;
	       }*/
		String name = req.getParameter("name");
	    String age = req.getParameter("age");
	    String gender = req.getParameter("gender");
	    String address = req.getParameter("address");
	    String banji = req.getParameter("banji");
	    System.out.println(banji + " servlet");
	    SearchCondition searchCondition = new SearchCondition(name, age, gender,address,banji);

	       String pageIndexStr = req.getParameter("pageIndex");
			String pageSizeStr = req.getParameter("pageSize");
			int pageIndex = 1;
			if (pageIndexStr!= null && !pageIndexStr.equals("")) {
				pageIndex = Integer.parseInt(pageIndexStr);
			}
			int pageSize = 3;
			if (pageSizeStr != null && !pageSizeStr.equals("")) {
				pageSize = Integer.parseInt(pageSizeStr);
			}
			IStudentService studentService = new StudentServiceImpl();
			searchCondition.setPageIndex(pageIndex);
			searchCondition.setPageSize(pageSize);
			PageBean pageBean = studentService.getPageBean2(searchCondition);
			System.out.println(pageBean);
			List<Banji> banji1 = new ArrayList<Banji>();
			banji1 = studentService.findBanji();
			System.out.println(banji1);
			HttpSession session = req.getSession();
			session.setAttribute("banjiList", banji1);
			req.setAttribute("searchCondition", searchCondition);
			req.setAttribute("pageBean", pageBean);
			//req.getRequestDispatcher("/jsp/searchByCondition.jsp").forward(req, resp);
			req.getRequestDispatcher("/jsp/find.jsp").forward(req, resp);
	    /*String name = req.getParameter("name");
	    String age = req.getParameter("age");
	    String gender = req.getParameter("gender");
	    SearchCondition searchCondition = new SearchCondition(name, age, gender);
	    IStudentService studentService = new StudentServiceImpl();
	    List<Student> list = studentService.searchByCondition(searchCondition);
	    req.setAttribute("searchCondition", searchCondition);
	    req.setAttribute("list", list);
	    req.getRequestDispatcher("/jsp/find.jsp").forward(req, resp);*/
	}

	private void deleteAccounts(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String name = req.getParameter("name");
		IStudentService studentService = new StudentServiceImpl();
		studentService.deleteAccounts(name);
		resp.sendRedirect("/Java1707Web2/student?method=findAccounts");
	}

	private void updateAccounts(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String name = req.getParameter("name");
		   String password = req.getParameter("password");
		   System.out.println(password);
		   Accounts accounts = new Accounts(name, password);
		   //2.处理业务
		   IStudentService studentService = new StudentServiceImpl();
		   int result = studentService.updateAccounts(accounts);
		   resp.sendRedirect("/Java1707Web2/student?method=findAccounts");
	}

	private void findAccountsByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String name = req.getParameter("name");
		IStudentService studentService = new StudentServiceImpl();
		List<Accounts> list = studentService.findAccountsByName(name);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/findAccountsByName.jsp").forward(req, resp);
	}

	private void findAccounts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		IStudentService studentService = new StudentServiceImpl();
		List<Accounts> list = studentService.findAccounts();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/manager.jsp").forward(req, resp);
	}

	private void zhuce(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		System.out.println(name);
		System.out.println(password);
		IStudentService studentService = new StudentServiceImpl();
		studentService.zhuce(name, password);
		resp.sendRedirect("/Java1707Web2/html/login.html");
		
	}

	private void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		IStudentService studentService = new StudentServiceImpl();
		String id = req.getParameter("id");
	   List<Student> list = studentService.findById(Integer.parseInt(id));
	   req.setAttribute("list", list);
	   req.getRequestDispatcher("/jsp/update.jsp").forward(req, resp);
	}

	private void searchStudentServlet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("text/html");
		   //1.接收请求参数，封装成对象
		   //2.业务处理
		   IStudentService studentService = new StudentServiceImpl();
			String name = req.getParameter("name");
		   String age = req.getParameter("age");
		   String gender = req.getParameter("gender");
		   String address = req.getParameter("address");
		   String birthday1 = req.getParameter("birthday1");
		   String birthday2 = req.getParameter("birthday2");
		   System.out.println("name:" + name);
		   System.out.println("age:" + age);
		   System.out.println("gender:" + gender);
		   System.out.println("address:" + address);
		   System.out.println("birthday1:" + birthday1);
		   System.out.println("birthday2:" + birthday2);
		   List<Student> list = null;
		   if(name.length()>0){
			    list = studentService.findStudentByName(name);
		   }else
		   if(age.length()>0){
			   list = studentService.findStudentByAge(Integer.parseInt(age));
		   }else
		   if(gender.length()>0){
			    list = studentService.findStudentByGender(gender);
		   }else
		   if(address.length()>0){
			   list = studentService.findStudentByAddress(address);
		   }else
		   if(birthday1.length()>0 && birthday2.length()>0){
			    list = studentService.findStudentByBirthday(birthday1, birthday2);
		   }
		   req.setAttribute("list", list);
		   req.getRequestDispatcher("/jsp/search.jsp").forward(req, resp);
	}

	private void updateStudentServlet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
			String id = req.getParameter("id");
			String name = req.getParameter("name");
		   String age = req.getParameter("age");
		   String gender = req.getParameter("gender");
		   String address = req.getParameter("address");
		   String birthday = req.getParameter("birthday");
		   String banji = req.getParameter("banji");
		   System.out.println("id:" + id);
		   System.out.println("name:" + name);
		   System.out.println("age:" + age);
		   System.out.println("gender:" + gender);
		   System.out.println("address:" + address);
		   System.out.println("birthday:" + birthday);
		   System.out.println("banji:" + banji);

		   //2.处理业务
		   SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
		   Date date = null;
		   try {
		      date = simpleDateFormat.parse(birthday);
		   } catch (ParseException e) {
		      e.printStackTrace();
		   }
		   Student student = new Student(Integer.parseInt(id),name, Integer.parseInt(age), gender,address,date,banji);
		   IStudentService studentService = new StudentServiceImpl();
		   int result = studentService.update(student);
		   resp.sendRedirect("/Java1707Web2/student?method=findStudentServlet");
	}

	private void deleteStudentServlet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String id = req.getParameter("id");
		IStudentService studentService = new StudentServiceImpl();
		studentService.deleteById(Integer.parseInt(id));
		resp.sendRedirect("/Java1707Web2/student?method=findStudentServlet");
	}

	private void findStudentServlet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		   //1.接收请求参数，封装成对象
		   //2.业务处理
		  // IStudentService studentService = new StudentServiceImpl();
		  // List<Student> list = studentService.findAll();
		   //3.控制界面跳转
		   //乱码问题
		  /* HttpSession session = req.getSession(false);
	       if (session == null) {
	           resp.sendRedirect(req.getContextPath() + "/html/login.html");
	           return;
	       }
	       String userName = (String) session.getAttribute("userName");
	       if (userName == null) {
	           resp.sendRedirect(req.getContextPath() + "/html/login.html");
	           return;
	       }*/
	       String pageIndexStr = req.getParameter("pageIndex");
			String pageSizeStr = req.getParameter("pageSize");
			int pageIndex = 1;
			if (pageIndexStr!= null && !pageIndexStr.equals("")) {
				pageIndex = Integer.parseInt(pageIndexStr);
			}
			int pageSize = 3;
			if (pageSizeStr != null && !pageSizeStr.equals("")) {
				pageSize = Integer.parseInt(pageSizeStr);
			}
			IStudentService studentService = new StudentServiceImpl();
			PageBean pageBean = studentService.getPageBean(pageIndex,pageSize);
			System.out.println(pageBean);
			List<Banji> banji = new ArrayList<Banji>();
			banji = studentService.findBanji();
			System.out.println(banji);
			HttpSession session = req.getSession();
			session.setAttribute("banjiList", banji);
			req.setAttribute("pageBean", pageBean);
			req.getRequestDispatcher("/jsp/find.jsp").forward(req, resp);
			
	      /* req.setAttribute("list", list);
	       req.getRequestDispatcher("/jsp/find.jsp").forward(req, resp);*/
		   
	}

	private void addStudentServlet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");*/
		//1.接收参数
		   String name = req.getParameter("name");
		   String age = req.getParameter("age");
		   String gender = req.getParameter("gender");
		   String address = req.getParameter("address");
		   String birthday = req.getParameter("birthday");
		   String banji = req.getParameter("banji");
		   
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
		    
		   Student student = new Student(name, Integer.parseInt(age), gender,address,date,banji);
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
		   resp.sendRedirect("/Java1707Web2/student?method=findStudentServlet");
	}
}
