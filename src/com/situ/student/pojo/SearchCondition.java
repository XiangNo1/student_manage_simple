package com.situ.student.pojo;

import java.io.Serializable;

public class SearchCondition implements Serializable {

	private Integer pageIndex;
	private Integer pageSize;
	private String name;
	private String age;
	private String gender;
	private String address;
	private String banji;
	
	public SearchCondition(String name, String age, String gender, String address, String banji) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.banji = banji;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBanji() {
		return banji;
	}
	public void setBanji(String banji) {
		this.banji = banji;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public SearchCondition(String name, String age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public SearchCondition() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public SearchCondition(Integer pageIndex, Integer pageSize, String name, String age, String gender) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	public SearchCondition(Integer pageIndex, Integer pageSize, String name, String age, String gender, String address,
			String banji) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.banji = banji;
	}
	@Override
	public String toString() {
		return "SearchCondition [pageIndex=" + pageIndex + ", pageSize=" + pageSize + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + ", address=" + address + ", banji=" + banji + "]";
	}
	
	
	
}
