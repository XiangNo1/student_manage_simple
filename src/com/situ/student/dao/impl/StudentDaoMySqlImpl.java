package com.situ.student.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.situ.student.dao.IStudentDao;
import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.pojo.SearchCondition;
import com.situ.student.pojo.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.StudentServiceImpl;
import com.situ.student.util.JdbcUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class StudentDaoMySqlImpl implements IStudentDao {

	@Override
	public int add(Student student) {

		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		int result = 0;
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		           "yyyy-MM-dd");

		try {
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO student(NAME,age,gender,address,birthday,banji) VALUES(?,?,?,?,?,?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getGender());
			preparedStatement.setString(4, student.getAddress());
			String time = simpleDateFormat.format(student.getBirthday());
			preparedStatement.setString(5, time);
			preparedStatement.setString(6,student.getBanji());
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
		return result;
	}

	@Override
	public int deleteById(int id) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result =0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM student WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public int update(Student student) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM student WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, student.getId());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
					result = reviseStudent(student);
					
			}
			else{
				
				System.out.println("未找到该id的学生！");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}
	private int reviseStudent(Student student) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet = 0;
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		           "yyyy-MM-dd");
		try {
			connection = JdbcUtil.getConnection();
			String sql = "UPDATE student SET name=?,age=?,gender=?,address=?,birthday=?,banji=? WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getName());
			preparedStatement.setInt(2, student.getAge());
			preparedStatement.setString(3, student.getGender());
			preparedStatement.setString(4, student.getAddress());
			String time = simpleDateFormat.format(student.getBirthday());
			preparedStatement.setString(5, time);
			preparedStatement.setString(6, student.getBanji());
			preparedStatement.setInt(7, student.getId());
			resultSet = preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		return resultSet;
	}


	@Override
	public List<Student> findAll() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id,name,age,gender,address,birthday,banji FROM student;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address = resultSet.getString("address");
				Date birthday = resultSet.getDate("birthday");
				String banji = resultSet.getString("banji");

				Student student = new Student(id, name, age, gender, address,birthday, banji);
				list.add(student);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return list;
	}

	@Override
	public List<Student> findByName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id,name,age,gender,address,birthday,banji FROM student where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name1 = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address = resultSet.getString("address");
				Date time = resultSet.getDate("birthday");
				String banji = resultSet.getString("banji");
				Student student = new Student(id, name1, age, gender, address,time, banji);
				list.add(student);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Student> findByAddress(String address) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id,name,age,gender,address,birthday,banji FROM student where address=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, address);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address1 = resultSet.getString("address");
				Date time = resultSet.getDate("birthday");
				String banji = resultSet.getString("banji");
				Student student = new Student(id, name, age, gender, address1,time, banji);
				list.add(student);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public boolean checkName(String name) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isExist = false;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id,name,age,gender,address,birthday,banji FROM student where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				isExist = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return isExist;
	}

	@Override
	public List<Student> findByAge(int age) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id,name,age,gender,address,birthday,banji FROM student where age=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, age);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age1 = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String address = resultSet.getString("address");
				Date time = resultSet.getDate("birthday");
				String banji = resultSet.getString("banji");
				Student student = new Student(id, name, age1, gender, address,time, banji);
				list.add(student);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Student> findByGender(String gender) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id,name,age,gender,address,birthday,banji FROM student where gender=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, gender);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender1 = resultSet.getString("gender");
				String address = resultSet.getString("address");
				Date time = resultSet.getDate("birthday");
				String banji = resultSet.getString("banji");
				Student student = new Student(id, name, age, gender1, address,time, banji);
				list.add(student);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Student> findStudentByBirthday(String time1, String time2) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id,NAME,age,gender,address,birthday,banji FROM student WHERE birthday BETWEEN ? AND ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, time1);
			preparedStatement.setString(2, time2);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender1 = resultSet.getString("gender");
				String address = resultSet.getString("address");
				Date time = resultSet.getDate("birthday");
				String banji = resultSet.getString("banji");
				Student student = new Student(id, name, age, gender1, address,time, banji);
				list.add(student);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Student> findById(int id) {

		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT id,name,age,gender,address,birthday,banji FROM student where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id1 = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender1 = resultSet.getString("gender");
				String address = resultSet.getString("address");
				Date time = resultSet.getDate("birthday");
				String banji = resultSet.getString("banji");
				Student student = new Student(id1, name, age, gender1, address,time, banji);
				list.add(student);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int zhuce(String name, String password) {

		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		int result = 0;
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
		           "yyyy-MM-dd");

		try {
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO accounts VALUES(?,?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
		return result;
	}

	@Override
	public List<Accounts> searchAccounts() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Accounts> list = new ArrayList<Accounts>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM accounts;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String password = resultSet.getString("password");
				Accounts accounts = new Accounts(name, password);
				if(accounts != null){
					list.add(accounts);
				}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return list;
	}

	@Override
	public List<Accounts> findAccounts() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Accounts> list = new ArrayList<Accounts>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM accounts;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String password = resultSet.getString("password");
				Accounts accounts = new Accounts(name, password);

				list.add(accounts);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return list;
	}

	@Override
	public List<Accounts> findAccountsByName(String name) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Accounts> list = new ArrayList<Accounts>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from accounts where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String name1 = resultSet.getString("name");
				String password = resultSet.getString("password");
				Accounts accounts = new Accounts(name1, password);
				list.add(accounts);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public int updateAccounts(Accounts accounts) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet = 0;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "UPDATE accounts SET name=?,password=? WHERE name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, accounts.getName());
			preparedStatement.setString(2, accounts.getPassword());
			preparedStatement.setString(3, accounts.getName());
			resultSet = preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public int deleteAccounts(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result =0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM accounts WHERE name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			result = preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public List<Student> searchByCondition(SearchCondition searchCondition) {
			/*Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    ResultSet resultSet = null;
		    List<Student> list = new ArrayList<Student>();
		    try {
		       connection = JdbcUtil.getConnection();
		       String sql = "select * from student where 1=1 ";
		       List<String> listCondition = new ArrayList<String>();
		       if (searchCondition.getName() != null
		               && !searchCondition.getName().equals("")) {
		           sql += " and name like ? ";
		           listCondition.add("%" + searchCondition.getName() + "%");
		       }
		       if (searchCondition.getAge() != null
		               && !searchCondition.getAge().equals("")) {
		           sql += " and age = ? ";
		           listCondition.add(searchCondition.getAge());
		       }
		       if (searchCondition.getGender() != null
		               && !searchCondition.getGender().equals("")) {
		           sql += " and gender = ? ";
		           listCondition.add(searchCondition.getGender());
		       }
		       preparedStatement = connection.prepareStatement(sql);
		       
		       for (int i = 0; i < listCondition.size(); i++) {
		           preparedStatement.setObject(i + 1, listCondition.get(i));
		       }
		       
		       resultSet = preparedStatement.executeQuery();
		       while (resultSet.next()) {
		           int id = resultSet.getInt("id");
		           String name = resultSet.getString("name");
		           int age = resultSet.getInt("age");
		           String gender = resultSet.getString("gender");
		           String address = resultSet.getString("address");
		           Date birthday = resultSet.getDate("birthday");
		           Student student = new Student(id, name, age, gender,address, birthday);
		           list.add(student);
		       }
		    } catch (SQLException e) {
		       e.printStackTrace();
		    }
		    return list;*/
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list = new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from student where 1=1";
			List<String> listCondition = new ArrayList<String>();
			if (searchCondition.getName() != null && searchCondition.getName() != "") {
				
				sql += "and name like ?";
				listCondition.add("%" + searchCondition.getName() + "%");
			}
			if (searchCondition.getAge() != null
		               && !searchCondition.getAge().equals("")) {
		           sql += " and age = ? ";
		           listCondition.add(searchCondition.getAge());
		       }
		       if (searchCondition.getGender() != null
		               && !searchCondition.getGender().equals("")) {
		           sql += " and gender = ? ";
		           listCondition.add(searchCondition.getGender());
		       }
		       if (searchCondition.getAddress() != null
		               && !searchCondition.getAddress().equals("")) {
		           sql += " and address = ? ";
		           listCondition.add(searchCondition.getAddress());
		       }
		       if (searchCondition.getBanji() != null
		               && !searchCondition.getBanji().equals("")) {
		           sql += " and Banji = ? ";
		           listCondition.add(searchCondition.getBanji());
		       }
		       preparedStatement = connection.prepareStatement(sql);
		       for(int i =0 ; i <listCondition.size();i++){
		    	   preparedStatement.setString(i+1, listCondition.get(i));
		       }
		       resultSet = preparedStatement.executeQuery();
		       while (resultSet.next()) {
		    	   int id = resultSet.getInt("id");
		           String name = resultSet.getString("name");
		           int age = resultSet.getInt("age");
		           String gender = resultSet.getString("gender");
		           String address = resultSet.getString("address");
		           Date birthday = resultSet.getDate("birthday");
		           String banji = resultSet.getString("banji");
		           Student student = new Student(id, name, age, gender,address, birthday, banji);
		           list.add(student);
			}
		 } catch (SQLException e) {
		       e.printStackTrace();
		    }
		    return list;
		
		

	}

	@Override
	public int getTotalCount() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from student;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List<Student> findPageBeanList(int index, int pageSize) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list =new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from student limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, index);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				 int id = resultSet.getInt("id");
		           String name = resultSet.getString("name");
		           int age = resultSet.getInt("age");
		           String gender = resultSet.getString("gender");
		           String address = resultSet.getString("address");
		           Date birthday = resultSet.getDate("birthday");
		           String banji = resultSet.getString("banji");
		           Student student = new Student(id, name, age, gender,address, birthday, banji);
		           list.add(student);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
		
		
	}

	@Override
	public int getSearchStudentCount(int index, Integer pageSize, SearchCondition searchCondition) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list =new ArrayList<Student>();
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from student where 1=1 ";
			List<String> listCondition = new ArrayList<String>();
			if (searchCondition.getName() != null && searchCondition.getName() != "") {
				
				sql += " and name like ? ";
			    System.out.println(searchCondition.getName() );

				listCondition.add("%" + searchCondition.getName() + "%");
			}
			if (searchCondition.getAge() != null
		               && !searchCondition.getAge().equals("")) {
		           sql += " and age = ? ";
		           listCondition.add(searchCondition.getAge());
		       }
		       if (searchCondition.getGender() != null
		               && !searchCondition.getGender().equals("")) {
		           sql += " and gender = ? ";
		           listCondition.add(searchCondition.getGender());
		       }
		       if (searchCondition.getAddress() != null
		               && !searchCondition.getAddress().equals("")) {
		           sql += " and address = ? ";
		           listCondition.add(searchCondition.getAddress());
		       }
		       if (searchCondition.getBanji() != null
		               && !searchCondition.getBanji().equals("")) {
		           sql += " and Banji = ? ";
		           listCondition.add(searchCondition.getBanji());
		       }
			preparedStatement = connection.prepareStatement(sql);
			for(int i =0 ; i <listCondition.size();i++){
		    	   preparedStatement.setString(i+1, listCondition.get(i));
		    	   System.out.println(listCondition.get(i));
		       }
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
		           result++;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List<Student> findSearchStudentList(int index, Integer pageSize, SearchCondition searchCondition) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Student> list =new ArrayList<Student>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from student where 1=1 ";
			List<String> listCondition = new ArrayList<String>();
			if (searchCondition.getName() != null && searchCondition.getName() != "") {
				
				sql += " and name like ? ";
				listCondition.add("%" + searchCondition.getName() + "%");
			}
			if (searchCondition.getAge() != null
		               && !searchCondition.getAge().equals("")) {
		           sql += " and age = ? ";
		           listCondition.add(searchCondition.getAge());
		       }
		       if (searchCondition.getGender() != null
		               && !searchCondition.getGender().equals("")) {
		           sql += " and gender = ? ";
		           listCondition.add(searchCondition.getGender());
		       }
		       if (searchCondition.getAddress() != null
		               && !searchCondition.getAddress().equals("")) {
		           sql += " and address = ? ";
		           listCondition.add(searchCondition.getAddress());
		       }
		       if (searchCondition.getBanji() != null
		               && !searchCondition.getBanji().equals("")) {
		           sql += " and Banji = ? ";
		           listCondition.add(searchCondition.getBanji());
		       }
		       sql += " limit ?,?; ";
			preparedStatement = connection.prepareStatement(sql);
			for(int i =0 ; i <listCondition.size();i++){
		    	   preparedStatement.setString(i+1, listCondition.get(i));
		       }
			preparedStatement.setInt(listCondition.size() + 1, index);
			preparedStatement.setInt(listCondition.size() + 2, pageSize);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				 int id = resultSet.getInt("id");
		           String name = resultSet.getString("name");
		           int age = resultSet.getInt("age");
		           String gender = resultSet.getString("gender");
		           String address = resultSet.getString("address");
		           Date birthday = resultSet.getDate("birthday");
		           String banji = resultSet.getString("banji");
		           Student student = new Student(id, name, age, gender,address, birthday, banji);
		           list.add(student);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Banji> findBanji() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Banji> list = new ArrayList<Banji>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM banji;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Banji banji = new Banji(id, name);
				
				list.add(banji);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return list;
	}

	@Override
	public int addBanji(Banji banji) {
		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		int result = 0;

		try {
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO banji(NAME) VALUES(?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banji.getName());
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
		return result;
	}

	@Override
	public int deleteBanji(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result =0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM banji WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(id));
			result = preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public Banji findBanjiById(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Banji banji = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM banji where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id1 = resultSet.getInt("id");
				String name = resultSet.getString("name");
				banji = new Banji(id, name);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return banji;
	}

	@Override
	public int updateBanji(Banji banji) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "UPDATE banji SET name=? WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, banji.getName());
			preparedStatement.setInt(2, banji.getId());
			resultSet = preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		return resultSet;
	}

	@Override
	public boolean checkBanjiName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isExist = false;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM banji where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				isExist = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return isExist;
	}

	

	@Override
	public List<Kecheng> findKecheng() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Kecheng> list = new ArrayList<Kecheng>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM kecheng;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Kecheng kecheng = new Kecheng(id, name);
				
				list.add(kecheng);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return list;
	}

	@Override
	public int addKecheng(Kecheng kecheng) {
		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		int result = 0;

		try {
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO kecheng(NAME) VALUES(?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, kecheng.getName());
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
		return result;
	}

	@Override
	public int deleteKecheng(String id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result =0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM kecheng WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(id));
			result = preparedStatement.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public Kecheng findKechengById(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Kecheng kecheng = null;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM kecheng where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id1 = resultSet.getInt("id");
				String name = resultSet.getString("name");
				kecheng = new Kecheng(id, name);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return kecheng;
	}

	@Override
	public int updateKecheng(Kecheng kecheng) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int resultSet = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "UPDATE kecheng SET name=? WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, kecheng.getName());
			preparedStatement.setInt(2, kecheng.getId());
			resultSet = preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		return resultSet;
	}

	@Override
	public boolean checkKechengName(String name) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isExist = false;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT * FROM kecheng where name=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				isExist = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		
		
		return isExist;
	}

	@Override
	public void deleteAll(String[] ids) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result =0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM student WHERE id=?;";
			preparedStatement = connection.prepareStatement(sql);
			for(int i =0;i<ids.length; i++){
				preparedStatement.setInt(1, Integer.parseInt(ids[i]));
				result = preparedStatement.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
	}

	@Override
	public List<Kecheng> findKechengByBanji(int id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Kecheng> list = new ArrayList<Kecheng>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "SELECT k.id,k.name FROM banji AS b LEFT JOIN banji_kecheng bk ON b.id=bk.banji_id LEFT JOIN kecheng AS k ON bk.kecheng_id=k.id WHERE b.id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id1 = resultSet.getInt("k.id");
				String name = resultSet.getString("k.name");
				Kecheng kecheng = new Kecheng(id1, name);
				list.add(kecheng);

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public List<Banji> findBanjiKecheng() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Banji> list = new ArrayList<Banji>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from banji;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			List<Kecheng> kecheng = new ArrayList<Kecheng>();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				kecheng = findKechengByBanji(id);
				System.out.println("impl" + kecheng);
				/*String sql2 = "SELECT k.id,k.name FROM banji AS b LEFT JOIN banji_kecheng bk ON b.id=bk.banji_id LEFT JOIN kecheng AS k ON bk.kecheng_id=k.id where b.id=?;";
				preparedStatement2 = connection.prepareStatement(sql2);
				preparedStatement2.setInt(1, id);
				resultSet2 = preparedStatement.executeQuery();
				while (resultSet2.next()) {
					int id2 = resultSet2.getInt("id");
					String name2 = resultSet2.getString("name");
					Kecheng kecheng2 = new Kecheng(id, name2);
					kecheng.add(kecheng2);
				}*/
				Banji banji = new Banji(id, name, kecheng);
				list.add(banji);
				
			}
			/*String sql = "SELECT b.id,b.name,k.id,k.name FROM banji AS b LEFT JOIN banji_kecheng bk ON b.id=bk.banji_id LEFT JOIN kecheng AS k ON bk.kecheng_id=k.id;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id1 = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Kecheng kecheng = new Kecheng(id, name);
				list.add(kecheng);

			}*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int deleteBanjiKecheng(String banji_id, String kecheng_id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int result =0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "DELETE FROM banji_kecheng WHERE banji_id=? AND kecheng_id=?;";
			preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, Integer.parseInt(banji_id));
				preparedStatement.setInt(2, Integer.parseInt(kecheng_id));
				result = preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		return result;
	}

	@Override
	public int addJiaowu(String banji, String kecheng) {
		Connection connection = null;
		java.sql.PreparedStatement preparedStatement = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "INSERT INTO banji_kecheng VALUES(?,?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,Integer.parseInt(banji));
			preparedStatement.setInt(2,Integer.parseInt(kecheng));
			result = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement);
		}
		
		return result;
	}

	@Override
	public List<Banji> searchBanjiKecheng(String banjiId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Banji> list = new ArrayList<Banji>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from banji where 1=1 ";
			if(banjiId != null && !banjiId.equals("")){
				sql += " and id=? ";
			}
			preparedStatement = connection.prepareStatement(sql);
			if(banjiId != null && !banjiId.equals("")){
				preparedStatement.setString(1, banjiId);
			}
			resultSet = preparedStatement.executeQuery();
			List<Kecheng> kecheng = new ArrayList<Kecheng>();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				kecheng = findKechengByBanji(id);
				System.out.println("impl" + kecheng);
				Banji banji = new Banji(id, name, kecheng);
				list.add(banji);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

	@Override
	public int getBanjiTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from banji;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List<Banji> findBanjiPageBeanList(int index, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Banji> list =new ArrayList<Banji>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from banji limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, index);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				 int id = resultSet.getInt("id");
		           String name = resultSet.getString("name");
		           Banji banji = new Banji(id, name);
		           list.add(banji);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}
	
	@Override
	public int getKechengTotalCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = 0;
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from kecheng;";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				result++;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return result;
	}

	@Override
	public List<Kecheng> findKechengPageBeanList(int index, int pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Kecheng> list =new ArrayList<Kecheng>();
		try {
			connection = JdbcUtil.getConnection();
			String sql = "select * from kecheng limit ?,?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, index);
			preparedStatement.setInt(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				 int id = resultSet.getInt("id");
		           String name = resultSet.getString("name");
		           Kecheng kecheng = new Kecheng(id, name);
		           list.add(kecheng);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(connection, preparedStatement, resultSet);
		}
		return list;
	}

}


