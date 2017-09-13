package com.situ.student.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.service.IStudentService;
import com.situ.student.service.StudentServiceImpl;

public class BanjiServlet extends BaceServlet {

	IStudentService studentService = new StudentServiceImpl();
	
	private void addKechengToServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/jsp/addKecheng.jsp").forward(req, resp);
	}
	
	private void AddBanjiToServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getRequestDispatcher("/jsp/addBanji.jsp").forward(req, resp);
	}
	
	
	
	
	
	
	
	private void addJiaowuServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String banji = req.getParameter("banji");
		String kecheng = req.getParameter("kecheng");
		studentService.addJiaowu(banji,kecheng);
		resp.sendRedirect(req.getContextPath() + "/banji?method=findJiaowuServlet");
	}
	
	
	
private void manageJiaowuServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		List<Kecheng> list = studentService.findKecheng();
		req.setAttribute("kechengList", list);
			
		req.getRequestDispatcher("/jsp/addJiaowu.jsp").forward(req, resp);
		//req.getRequestDispatcher("/jsp/jiaowu.jsp").forward(req, resp);
	}
	
private void deleteBanjiKecheng(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	
	String banji_id = req.getParameter("banji_id");
	String kecheng_id = req.getParameter("kecheng_id");
	System.out.println("BanjiServlet.deleteBanjiKecheng()");
	System.out.println("delete" + banji_id);
	System.out.println(kecheng_id);
	studentService.deleteBanjiKecheng(banji_id,kecheng_id);
	resp.sendRedirect(req.getContextPath() + "/banji?method=findJiaowuServlet");
}

private void searchBanjiKecheng(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	
	String banjiId = req.getParameter("banjiSearch");
	List<Banji> banji1 = new ArrayList<Banji>();
	banji1 = studentService.searchBanjiKecheng(banjiId);
	System.out.println("searchSerlvet" + banji1);
	req.setAttribute("banjiId", banjiId);
	req.setAttribute("list", banji1);
	req.getRequestDispatcher("/jsp/jiaowu.jsp").forward(req, resp);
}

	private void findJiaowuServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		
		List<Banji> banji1 = new ArrayList<Banji>();
		banji1 = studentService.findBanjiKecheng();
		System.out.println("jiaowuserlvet" + banji1);
		req.setAttribute("list", banji1);
		req.getRequestDispatcher("/jsp/jiaowu.jsp").forward(req, resp);
		//req.getRequestDispatcher("/jsp/jiaowu.jsp").forward(req, resp);
	}
	
	private void findBanjiServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<Banji> list = studentService.findBanji();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/findBanji.jsp").forward(req, resp);
	}
	
	private void addBanjiServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String name = req.getParameter("name");
		Banji banji = new Banji(name);
		studentService.addBanji(banji);
		resp.sendRedirect(req.getContextPath() + "/banji?method=findBanjiServlet");
		
	}
	private void deleteBanjiServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		studentService.deleteBanji(id);
		resp.sendRedirect(req.getContextPath() + "/banji?method=findBanjiServlet");
	}
	
	private void findBanjiById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		Banji banji = studentService.findBanjiById(Integer.parseInt(id));
		req.setAttribute("banji", banji);
		req.getRequestDispatcher("/jsp/updateBanji.jsp").forward(req, resp);
	}
	
	private void updateBanjiServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		Banji banji = new Banji(Integer.parseInt(id), name);
		studentService.updateBanji(banji);
		resp.sendRedirect(req.getContextPath() + "/banji?method=findBanjiServlet");
	}
	private void checkBanjiName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		IStudentService studentService = new StudentServiceImpl();
		String name = req.getParameter("name");
		boolean isExit = studentService.checkBanjiName(name);
		resp.setContentType("charset=utf-8");
		resp.getWriter().write("{\"isExit\":" + isExit + "}");
	}
	
	
	
	
	
	
	
	private void findKechengServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		List<Kecheng> list = studentService.findKecheng();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/jsp/findKecheng.jsp").forward(req, resp);
	}
	
	private void addKechengServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String name = req.getParameter("name");
		Kecheng kecheng = new Kecheng(name);
		studentService.addKecheng(kecheng);
		resp.sendRedirect(req.getContextPath() + "/banji?method=findKechengServlet");
		
	}
	private void deleteKechengServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		studentService.deleteKecheng(id);
		resp.sendRedirect(req.getContextPath() + "/banji?method=findKechengServlet");
	}
	
	private void findKechengById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		Kecheng kecheng = studentService.findKechengById(Integer.parseInt(id));
		req.setAttribute("kecheng", kecheng);
		req.getRequestDispatcher("/jsp/updateKecheng.jsp").forward(req, resp);
	}
	
	private void updateKechengServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		Kecheng kecheng= new Kecheng(Integer.parseInt(id), name);
		studentService.updateKecheng(kecheng);
		resp.sendRedirect(req.getContextPath() + "/banji?method=findKechengServlet");
	}
	private void checkKechengName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		IStudentService studentService = new StudentServiceImpl();
		String name = req.getParameter("name");
		boolean isExit = studentService.checkKechengName(name);
		resp.setContentType("charset=utf-8");
		resp.getWriter().write("{\"isExit\":" + isExit + "}");
	}
	
	
}
