package com.javasampleapproach.jqueryboostraptable.model;



import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name = "gozaresh")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  
    
    private Boolean is_complete;
    
    private String description;
    
    private String d_time;
    
    private String d_date;
    
    private String username; //peygiri konande

    private String location; //bakhsh
    
    private String state; 
    
    private String client; // morjee konandeh
    
    private String type; // noe morajee
    
    

    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private Base base;
    
	public Report() {}


	public Report(Integer id, String title, Boolean is_complete, String description, String d_time, String d_date) {
		super();
		this.id = id;
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


	public Base getBase() {
		return base;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getClient() {
		return client;
	}


	public void setClient(String client) {
		this.client = client;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getUsername() {
		return username;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setBase(Base base) {
		this.base = base;
	}




	public Boolean getIs_complete() {
		return is_complete;
	}


	public void setIs_complete(Boolean is_complete) {
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
		return "Report [id=" + id + ", is_complete=" + is_complete + ", description=" + description + ", d_time="
				+ d_time + ", d_date=" + d_date + ", username=" + username + ", state=" + state + ", client=" + client
				+ ", type=" + type + "]";
	}




    

}