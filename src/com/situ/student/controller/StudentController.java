package com.situ.student.controller;

import java.util.Date;
import java.util.List;

import com.situ.student.exception.NameRepeatException;
import com.situ.student.pojo.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.StudentServiceImpl;

public class StudentController {
	private IStudentService studentService = new StudentServiceImpl();

	public List<Student> findAll() {
		return studentService.findAll();
	}

	public boolean add(String name, Integer age, String gender, String address , Date date) throws NameRepeatException {

		Student student = new Student(name, age, gender, address,date);
		return studentService.add(student);
	}

	public int update(int inputId, String name, int age, String gender, String address,Date date) {

		Student student = new Student(inputId, name, age, gender, address,date);
		int result = studentService.update(student);
		return result;
	}

	public int deleteById(int inputId) {

		
		return studentService.deleteById(inputId);
	}

	public List<Student> findStudentByName(String name) {

		
		return studentService.findStudentByName(name);
	}

	public List<Student> findStudentByAddress(String address) {
		// TODO Auto-generated method stub
		return studentService.findStudentByAddress(address);
	}

	public List<Student> findStudentByAge(int age) {
		// TODO Auto-generated method stub
		return studentService.findStudentByAge(age);
	}

	public List<Student> findStudentByGender(String gender) {
		// TODO Auto-generated method stub
		return studentService.findStudentByGender(gender);
	}

	

	public List<Student> findStudentByBirthday(String time1, String time2) {
		// TODO Auto-generated method stub
		return studentService.findStudentByBirthday(time1,time2);
	}


	
}
