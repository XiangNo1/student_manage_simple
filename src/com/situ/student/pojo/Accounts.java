package com.situ.student.pojo;

import java.io.Serializable;

public class Accounts implements Serializable{

	private String name;
	private String password;
	
	@Override
	public String toString() {
		return "Accounts [name=" + name + ", password=" + password + "]";
	}
	public Accounts() {
		super();
	}
	public Accounts(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
