package com.javaex.phonebook;

public class Person {

	private String name;
	private String hp; // 휴대전화
	private String company; // 집전화

	public Person() {

	}

	public Person(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setPh(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void showInfo() {
		System.out.println(name + "   " + hp + "   " + company);
	}

}
