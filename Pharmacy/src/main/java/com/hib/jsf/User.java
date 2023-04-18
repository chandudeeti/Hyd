package com.hib.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pharm")
@ManagedBean
@SessionScoped
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pharmId;
	private String name;
	private String email;
	private Long phone;
	private String userName;
	@Enumerated(value = EnumType.STRING)
	private Gender gender;
	private int age;
	private String password;
	private String address;
	
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPharmId() {
		return pharmId;
	}
	public void setPharmId(int pharmId) {
		this.pharmId = pharmId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [pharmId=" + pharmId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", userName="
				+ userName + ", gender=" + gender + ", age=" + age + ", password=" + password + ", address=" + address
				+ "]";
	}
	
	
	
	
	

}
