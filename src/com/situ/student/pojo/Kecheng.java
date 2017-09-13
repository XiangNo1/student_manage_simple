package com.situ.student.pojo;

import java.io.Serializable;

public class Kecheng implements Serializable{

	private Integer id;
	private String name;
	
	@Override
	public String toString() {
		return "Kecheng [id=" + id + ", name=" + name + "]";
	}
	public Kecheng(String name) {
		super();
		this.name = name;
	}
	public Kecheng() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Kecheng(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
