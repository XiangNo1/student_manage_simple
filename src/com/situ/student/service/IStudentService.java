package com.situ.student.service;

import java.util.List;

import com.situ.student.exception.NameRepeatException;
import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.pojo.PageBean;
import com.situ.student.pojo.SearchCondition;
import com.situ.student.pojo.Student;

public interface IStudentService {

	List<Student> findAll();

	boolean add(Student student) throws NameRepeatException;

	int update(Student student);

	int deleteById(int inputId);

	List<Student> findStudentByName(String name);

	List<Student> findStudentByAddress(String address);

	List<Student> findStudentByAge(int age);

	List<Student> findStudentByGender(String gender);

	List<Student> findStudentByBirthday(String time1, String time2);

	List<Student> findById(int id);
	int zhuce(String name,String password);
	List<Accounts> searchAccounts();
	
	List<Accounts> findAccounts();
	
	List<Accounts> findAccountsByName(String name);
	
	int updateAccounts(Accounts accounts);
	
	int deleteAccounts(String name);
	List<Student> searchByCondition(SearchCondition searchCondition);

	PageBean getPageBean(int pageIndex, int pageSize);

	PageBean getPageBean2(SearchCondition searchCondition);

	boolean checkName(String name);

	List<Banji> findBanji();

	int addBanji(Banji banji);

	int deleteBanji(String id);

	Banji findBanjiById(int parseInt);

	int updateBanji(Banji banji);

	boolean checkBanjiName(String name);
	
	List<Kecheng> findKecheng();

	int addKecheng(Kecheng kecheng);

	int deleteKecheng(String id);

	Kecheng findKechengById(int parseInt);

	int updateKecheng(Kecheng kecheng);

	boolean checkKechengName(String name);

	void deleteAll(String[] ids);

	List<Kecheng> findKechengByBanji(int parseInt);

	List<Banji> findBanjiKecheng();

	int deleteBanjiKecheng(String banji_id, String kecheng_id);

	int addJiaowu(String banji, String kecheng);

	List<Banji> searchBanjiKecheng(String banjiId);

	PageBean<Banji> getPageBeanBanji(int pageIndex, int pageSize);

	PageBean<Kecheng> getPageBeanKecheng(int pageIndex, int pageSize);
}
