package com.javasampleapproach.jqueryboostraptable.model;




import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name = "base")
public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String d_date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy="base",cascade=CascadeType.ALL)
	private List<Report> reports;
    
	public Base() {}


	public Base(Integer id, String d_date) {
		super();
		this.id = id;
		this.d_date = d_date;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public List<Report> getReports() {
		return reports;
	}


	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public String getD_date() {
		return d_date;
	}


	public void setD_date(String d_date) {
		this.d_date = d_date;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Base [id=" + id + ", d_date=" + d_date + ", user=" + user + ", reports=" + reports + "]";
	}



}