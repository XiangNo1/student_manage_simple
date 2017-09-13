package com.situ.student.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Student;

public class OnlineStudentListListener implements ServletContextListener{
    //ServletContext创建的时候调用
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       //创建在线学生列表集合
       //每当用户登录时候，就往集合中添加。
       List<Accounts> onlineStudentList = new ArrayList<Accounts>();
       //放到ServletContext域对象中
       ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("onlineStudentList", onlineStudentList);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       
    }
}

