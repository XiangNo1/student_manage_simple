package com.situ.student.view;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import com.situ.student.controller.StudentController;
import com.situ.student.dao.impl.StudentDaoMySqlImpl;
import com.situ.student.exception.NameRepeatException;
import com.situ.student.pojo.Student;

public class StudentView{

	private StudentController studentController = new StudentController();
	Scanner scanner = new Scanner(System.in);
	SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
	
	public void showView() {
		while(true){
			System.out.println("------学生信息管理系统------");
			System.out.println("1.查询所有");
			System.out.println("2.添加");
			System.out.println("3.修改");
			System.out.println("4.删除");
			System.out.println("5.根据条件查找学生");
			System.out.println("0.退出");
			System.out.println("请选择");
			int selected = scanner.nextInt();
			if (selected == 0) {
				System.out.println("谢谢使用，再见");
				break;
			}
			switch (selected) {
			case 1://查询所有
				findAll();
				break;
	
			case 2://添加
				add();
				break;
			case 3://修改
				update();
				break;
			case 4://删除
				deleteById();
				break;
			case 5:
				findStudentByCondition();
				break;
			default:
				break;
			}
		}
	}
		
	

	private void findStudentByCondition() {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<Student>();
		System.out.println("请输入要查找的条件：1.根据姓名查找2.根据地址查找3.根据年龄查找4.根据性别查找5.按照生日段查询");
		int select = scanner.nextInt();
		switch (select) {
		case 1:
			System.out.println("请输入要查找的姓名：");
			String name = scanner.next();
			list = studentController.findStudentByName(name);
			break;

		case 2:
			System.out.println("请输入要查找的地址：");
			String address = scanner.next();
			list = studentController.findStudentByAddress(address);
			break;
		case 3:
			System.out.println("请输入要查找的年龄：");
			int age = scanner.nextInt();
			list = studentController.findStudentByAge(age);
			break;
		case 4:
			System.out.println("请输入要查找的性别：");
			String gender = scanner.next();
			list = studentController.findStudentByGender(gender);
			break;
		case 5:
			System.out.println("请输入要查找的生日段：xxxx-xx-xx xxxx-xx-xx");
			String time1 = scanner.next();
			String time2 = scanner.next();
			list = studentController.findStudentByBirthday(time1,time2);
			break;
		default:
			break;
		}
		if(list.size() == 0){
			System.out.println("未找到符合条件的学生");
		}else{
			for (Student student : list) {
				System.out.println(student);
			}
		}
	}



	private void deleteById() {

		System.out.println("请输入要删除的学生的id：");
		int inputId = scanner.nextInt();
		int result = studentController.deleteById(inputId);
		if(result > 0){
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
	}



	private void update() {

		System.out.println("请输入要修改的id");
		int inputId = scanner.nextInt();
		System.out.println("请输入新的学生姓名：");
		String name = scanner.next();
		System.out.println("请输入新的学生年龄：");
		int age = scanner.nextInt();
		System.out.println("请输入新的学生性别：");
		String gender = scanner.next();
		System.out.println("请输入新的学生地址：");
		String address = scanner.next();
		System.out.println("请输入新的学生生日：xxxx-xx-xx");
		String birthday = scanner.next();
		Date date = null;
		try {
			date = simpleDateFormat.parse(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = studentController.update(inputId,name,age,gender,address,date);
		if(result > 0){
			System.out.println("修改成功");
		}
		else{
			System.out.println("修改失败");
		}
	}



	private void add() {

		while(true){
			System.out.println("请输入学生姓名：");
			String name = scanner.next();
			System.out.println("请输入学生年龄：");
			int age = scanner.nextInt();
			System.out.println("请输入学生性别：");
			String gender = scanner.next();
			System.out.println("请输入学生地址：");
			String address = scanner.next();
			System.out.println("请输入学生的生日：xxxx-xx-xx");
			String birthday = scanner.next();
			Date date = null;
			try {
				date = simpleDateFormat.parse(birthday);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			boolean result = false;
			try {
				result = studentController.add(name,age,gender,address,date);
			} catch (NameRepeatException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			if (result) {
				System.out.println("添加成功");
			}
			else{
				System.out.println("添加失败");
			}
			System.out.println("是否继续输入：1.继续 0.退出");
			int quit = scanner.nextInt();
			if (quit == 0) {
				break;
			}
		}
	}

	private void findAll() {
		List<Student> list = studentController.findAll();
		for (Student student : list) {
			System.out.println(student);
		}
	}
}
