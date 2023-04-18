package com.hib.jsf;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info")
@ManagedBean
@SessionScoped
public class Info {
	
	@Id
	private int id;
	private String name;
	@Column(name = "date_time")
	private Date dateTime;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "Info [id=" + id + ", name=" + name + ", dateTime=" + dateTime + "]";
	}
	
	
	
	
	
	
	

}
