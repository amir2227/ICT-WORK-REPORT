package com.javasampleapproach.jqueryboostraptable.model;




import java.util.List;

import javax.persistence.*;




@Entity
@Table(name = "gozaresh")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    
    private String is_complete;
    
    private String description;
    
    private String d_time;
    
    private String d_date;

    @ManyToMany(mappedBy = "reports")
    private List<User> users;
    
	public Report() {}


	public Report(Integer id, String name, String is_complete, String description, String d_time, String d_date) {
		super();
		this.id = id;
		this.name = name;
		this.is_complete = is_complete;
		this.description = description;
		this.d_time = d_time;
		this.d_date = d_date;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}



	public List<User> getUser() {
		return users;
	}


	public void setUser(List<User> user) {
		this.users = user;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIs_complete() {
		return is_complete;
	}


	public void setIs_complete(String is_complete) {
		this.is_complete = is_complete;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getD_time() {
		return d_time;
	}


	public void setD_time(String d_time) {
		this.d_time = d_time;
	}


	public String getD_date() {
		return d_date;
	}


	public void setD_date(String d_date) {
		this.d_date = d_date;
	}


	@Override
	public String toString() {
		return "Report [id=" + id + ", name=" + name + ", is_complete=" + is_complete + ", description=" + description
				+ ", d_time=" + d_time + ", d_date=" + d_date + "]";
	}

    

}