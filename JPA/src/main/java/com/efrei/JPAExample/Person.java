package com.efrei.JPAExample;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {

	private List<Car> car = new ArrayList<Car>();
	private long id;
	private String name;
	private int age;
	
	public Person() {
		super();
	}

	public Person(long id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	@OneToMany(mappedBy="person", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	public List<Car> getCar() {
		return car;
	}
	public void setCar(List<Car> car) {
		this.car = car;
	}

	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
