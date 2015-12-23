package com.kuang135.demo.pojo;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="user")
public class User {
	@Id
	@GeneratedValue(generator = "UUID")
	@Column(name="ID")
    private String id;

	@Column(name="USERNAME")
    private String name;

	
	@Column(name="PASSWORD")
    private String password;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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