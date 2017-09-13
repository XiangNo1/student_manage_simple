package com.situ.student.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

import com.mysql.jdbc.PreparedStatement;
import com.situ.student.dao.IStudentDao;
import com.situ.student.dao.impl.StudentDaoMySqlImpl;
import com.situ.student.exception.NameRepeatException;
import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.pojo.PageBean;
import com.situ.student.pojo.SearchCondition;
import com.situ.student.pojo.Student;

public class StudentServiceImpl implements IStudentService{
	private IStudentDao studentDao = new StudentDaoMySqlImpl();
			
	@Override
	public List<Student> findAll() {
		return studentDao.findAll();
	}

	@Override
	public boolean add(Student student) throws NameRepeatException {

		if(studentDao.checkName(student.getName())){
			//System.out.println("用户名已经存在");
			throw new NameRepeatException("用户名已存在");
		}
		else{
			int result = studentDao.add(student);
			if(result>0){
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public int update(Student student) {

		
		return studentDao.update(student);
	}

	@Override
	public int deleteById(int inputId) {

		
		return studentDao.deleteById(inputId);
	}

	@Override
	public List<Student> findStudentByName(String name) {
		// TODO Auto-generated method stub
		return studentDao.findByName(name);
	}

	@Override
	public List<Student> findStudentByAddress(String address) {
		// TODO Auto-generated method stub
		return studentDao.findByAddress(address);
	}

	@Override
	public List<Student> findStudentByAge(int age) {
		// TODO Auto-generated method stub
		return studentDao.findByAge(age);
	}

	@Override
	public List<Student> findStudentByGender(String gender) {
		// TODO Auto-generated method stub
		return studentDao.findByGender(gender);
	}

	@Override
	public List<Student> findStudentByBirthday(String time1, String time2) {
		// TODO Auto-generated method stub
		return studentDao.findStudentByBirthday(time1,time2);
	}

	@Override
	public List<Student> findById(int id) {
		// TODO Auto-generated method stub
		return studentDao.findById(id);
	}

	@Override
	public int zhuce(String name, String password) {

		return studentDao.zhuce(name, password);
	}

	@Override
	public List<Accounts> searchAccounts() {

		return studentDao.searchAccounts();
	}

	@Override
	public List<Accounts> findAccounts() {
		return studentDao.findAccounts();
	}

	@Override
	public List<Accounts> findAccountsByName(String name) {
		return studentDao.findAccountsByName(name);
	}

	@Override
	public int updateAccounts(Accounts accounts) {
		// TODO Auto-generated method stub
		return studentDao.updateAccounts(accounts);
	}

	@Override
	public int deleteAccounts(String name) {
		// TODO Auto-generated method stub
		return studentDao.deleteAccounts(name);
	}

	@Override
	public List<Student> searchByCondition(SearchCondition searchCondition) {
		// TODO Auto-generated method stub
		return studentDao.searchByCondition(searchCondition);
	}

	@Override
	public PageBean getPageBean(int pageIndex, int pageSize) {
		PageBean pageBean = new PageBean();
		pageBean.setPageIndex(pageIndex);
		pageBean.setPageSize(pageSize);
		//总条数
		int totalCount = studentDao.getTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount / pageSize );
		pageBean.setTotalPage(totalPage);
		int index =( pageIndex - 1) * pageSize;
		List<Student> list = studentDao.findPageBeanList(index, pageSize);
		pageBean.setList(list);
		
		return pageBean;
		
		
	}
	@Override
	public PageBean<Student> getPageBean2(SearchCondition searchCondition) {
		PageBean<Student> pageBean = new PageBean<Student>();
		pageBean.setPageIndex(searchCondition.getPageIndex());
		pageBean.setPageSize(searchCondition.getPageSize());
		//总条数
		int index =( searchCondition.getPageIndex() - 1) * searchCondition.getPageSize();
		int totalCount = studentDao.getSearchStudentCount(index, searchCondition.getPageSize(),searchCondition);
		pageBean.setTotalCount(totalCount);
		int totalPage =(int) Math.ceil((double) totalCount /searchCondition.getPageSize() );
		pageBean.setTotalPage(totalPage);
		List<Student> list = studentDao.findSearchStudentList(index, searchCondition.getPageSize(),searchCondition);
		pageBean.setList(list);
		
		return pageBean;
		
		
	}

	@Override
	public boolean checkName(String name) {
		// TODO Auto-generated method stub
		return studentDao.checkName(name);
	}

	@Override
	public List<Banji> findBanji() {
		// TODO Auto-generated method stub
		return studentDao.findBanji();
	}

	@Override
	public int addBanji(Banji banji) {
		// TODO Auto-generated method stub
		return studentDao.addBanji(banji);
	}

	@Override
	public int deleteBanji(String id) {
		// TODO Auto-generated method stub
		return studentDao.deleteBanji(id);
	}

	@Override
	public Banji findBanjiById(int id) {
		// TODO Auto-generated method stub
		return studentDao.findBanjiById(id);
	}

	@Override
	public int updateBanji(Banji banji) {
		// TODO Auto-generated method stub
		return studentDao.updateBanji(banji);
	}

	@Override
	public boolean checkBanjiName(String name) {
		// TODO Auto-generated method stub
		return studentDao.checkBanjiName(name);
	}
	
	
	
	
	
	@Override
	public List<Kecheng> findKecheng() {
		// TODO Auto-generated method stub
		return studentDao.findKecheng();
	}

	@Override
	public int addKecheng(Kecheng kecheng) {
		// TODO Auto-generated method stub
		return studentDao.addKecheng(kecheng);
	}

	@Override
	public int deleteKecheng(String id) {
		// TODO Auto-generated method stub
		return studentDao.deleteKecheng(id);
	}

	@Override
	public Kecheng findKechengById(int id) {
		// TODO Auto-generated method stub
		return studentDao.findKechengById(id);
	}

	@Override
	public int updateKecheng(Kecheng kecheng) {
		// TODO Auto-generated method stub
		return studentDao.updateKecheng(kecheng);
	}

	@Override
	public boolean checkKechengName(String name) {
		// TODO Auto-generated method stub
		return studentDao.checkKechengName(name);
	}

	@Override
	public void deleteAll(String[] ids) {
		// TODO Auto-generated method stub
		studentDao.deleteAll(ids);
	}

	@Override
	public List<Kecheng> findKechengByBanji(int id) {
		// TODO Auto-generated method stub
		return studentDao.findKechengByBanji(id);
	}

	@Override
	public List<Banji> findBanjiKecheng() {
		// TODO Auto-generated method stub
		return studentDao.findBanjiKecheng();
	}

	@Override
	public int deleteBanjiKecheng(String banji_id, String kecheng_id) {
		// TODO Auto-generated method stub
		return studentDao.deleteBanjiKecheng(banji_id,kecheng_id);
	}

	@Override
	public int addJiaowu(String banji, String kecheng) {
		// TODO Auto-generated method stub
		return studentDao.addJiaowu(banji, kecheng);
	}

	@Override
	public List<Banji> searchBanjiKecheng(String banjiId) {
		// TODO Auto-generated method stub
		return studentDao.searchBanjiKecheng(banjiId);
	}

}
