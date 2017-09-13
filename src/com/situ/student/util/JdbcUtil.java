package com.situ.student.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletContext;

//工具类
public class JdbcUtil {

	/*public final static String  driverClass="com.mysql.jdbc.Driver";
	public final static String  url="jdbc:mysql://localhost:3306/java1707";
	public final static String  userName = "root";
	public final static String  password = "123698745";*/
	public static String  driverClass;
	public static String  url;
	public static String  userName;
	public static String  password;
	
	
	public static void init(ServletContext context){
		InputStream inputStream = null;
		try {
			
			inputStream = context.getResourceAsStream("/db.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			driverClass = properties.getProperty("driverClass");
			url = properties.getProperty("url");
			userName = properties.getProperty("userName");
			password = properties.getProperty("password");
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	       // 1、加载驱动 Class.forName("");
	       try {
	           Class.forName(driverClass);
	       } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	       }
	    }

	public static Connection getConnection() throws SQLException {
	       return DriverManager.getConnection(
	               url, userName, password);
	    }

	// 6、关闭
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
       if (resultSet != null) {
           try {
               resultSet.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       if (statement != null) {
           try {
               statement.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       if (connection != null) {
           try {
               connection.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }
    
 // 6、关闭
    public static void close(Connection connection, Statement statement) {
       
       if (statement != null) {
           try {
               statement.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       if (connection != null) {
           try {
               connection.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }

}
