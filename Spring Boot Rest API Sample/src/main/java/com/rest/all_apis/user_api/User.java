package com.rest.all_apis.user_api;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("ageFilter")
public class User {
	
	@Size(min=4, message="Name should have atleast 2 chars")
	String name;
	
	Integer age;
	String place;
	Integer id;
	
	public User(Integer id,String name, Integer age, String place) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.place = place;		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", place=" + place + "]";
	}
	
	
}
