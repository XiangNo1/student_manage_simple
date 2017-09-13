package com.situ.student.test;

import java.util.List;
import java.util.Date;
import org.junit.Test;

import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.StudentDaoMySqlImpl;
import com.situ.student.pojo.Student;

public class StudentDaoMySqlImplTest {

	@Test
	public void testAdd() {
		Date date = new Date();
		Student student = new Student("张三222", 30, "男", "青岛",date);
		IStudentDao studentDao = new StudentDaoMySqlImpl();
		int result = studentDao.add(student);
		if (result>0) {
			System.out.println("插入成功");
		}
		else{
			System.out.println("测试失败");
		}
	}
	
	@Test
	public void testFoundAll(){
		IStudentDao studentDao = new StudentDaoMySqlImpl();
		List<Student> list = studentDao.findAll();
		for (Student student : list) {
			
			System.out.println(student);
		}
	}
	
	@Test
	public void testUpdate(){
		Student student = new Student(1,"cad",22,"男",null,null);
		IStudentDao studentDao = new StudentDaoMySqlImpl();
		studentDao.update(student);
	}
}
