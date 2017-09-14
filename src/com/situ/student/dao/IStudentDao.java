package com.situ.student.dao;

import java.util.List;

import com.situ.student.pojo.Accounts;
import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.pojo.SearchCondition;
import com.situ.student.pojo.Student;

public interface IStudentDao {

	/**
	 * 添加学生到数据库
	 * @param student
	 * @return
	 */
	public int add(Student student);
	/**
	 * 根据id删除学生
	 * @param id
	 * @return
	 */
	public int deleteById(int id);
	/**
	 * 修改学生
	 * @param student
	 * @return
	 */
	public int update(Student student);
	/**
	 * 返回所有学生信息
	 * @return
	 */
	public List<Student> findAll();
	/**
	 * 根据名字查找
	 * @param name
	 * @return
	 */
	public List<Student> findByName(String name);
	/**
	 * 根据地址查找
	 * @param gender
	 * @return
	 */
	public List<Student> findByAddress(String Address);
	/**
	 * 判断name在数据库存在不存在
	 * @param name
	 * @return
	 */
	public boolean checkName(String name);
	public List<Student> findByAge(int age);
	public List<Student> findByGender(String gender);
	public List<Student> findStudentByBirthday(String time1, String time2);
	public List<Student> findById(int id);
	public int zhuce(String name, String password);
	public List<Accounts> searchAccounts();
	public List<Accounts> findAccounts();
	public List<Accounts> findAccountsByName(String name);
	public int updateAccounts(Accounts accounts);
	public int deleteAccounts(String name);
	public List<Student> searchByCondition(SearchCondition searchCondition);
	public int getTotalCount();
	public List<Student> findPageBeanList(int index, int pageSize);
	public List<Student> findSearchStudentList(int index, Integer pageSize,SearchCondition searchCondition);
	int getSearchStudentCount(int index, Integer pageSize, SearchCondition searchCondition);
	public List<Banji> findBanji();
	public int addBanji(Banji banji);
	public int deleteBanji(String id);
	public Banji findBanjiById(int id);
	public int updateBanji(Banji banji);
	public boolean checkBanjiName(String name);
	
	public List<Kecheng> findKecheng();
	public int addKecheng(Kecheng kecheng);
	public int deleteKecheng(String id);
	public Kecheng findKechengById(int id);
	public int updateKecheng(Kecheng kecheng);
	public boolean checkKechengName(String name);
	public void deleteAll(String[] ids);
	public List<Kecheng> findKechengByBanji(int id);
	public List<Banji> findBanjiKecheng();
	public int deleteBanjiKecheng(String banji_id, String kecheng_id);
	public int addJiaowu(String banji, String kecheng);
	public List<Banji> searchBanjiKecheng(String banjiId);
	public int getBanjiTotalCount();
	public List<Banji> findBanjiPageBeanList(int index, int pageSize);
	public int getKechengTotalCount();
	public List<Kecheng> findKechengPageBeanList(int index, int pageSize);
	
}
