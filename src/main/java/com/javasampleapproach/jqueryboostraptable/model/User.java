package com.javasampleapproach.jqueryboostraptable.model;





import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;



@Entity
@Table(name = "USER")
public class User implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer id;
	
	@NotEmpty(message = "*Please provide your personal id")
	@Column(name = "PERSONAL_ID", columnDefinition="nvarchar(10)")
	private String personalId;
	
	@Column(name = "NAME", columnDefinition="nvarchar(20)")
	private String FName;
	
	@Column(name = "LAST_NAME", columnDefinition="nvarchar(20)")
	private String Lname;

	
	@Column(name = "PASSWORD" )
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@JsonIgnore
	private String pass;
	

	@Column(name = "ACTIVE")
	private int active;
	
	@Column(name = "Finger", columnDefinition="nvarchar(2)")
	private String finger;
	
	@Column(columnDefinition="LONGBLOB")
	@JsonIgnore
	private String profile; 
	
	@Column(columnDefinition="nvarchar(50)")
	private String fullname;
	
//	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@JsonIgnore
	@ManyToMany
	private List<Role> roles;
	
	
	@ManyToMany
	@JsonIgnore
	private List<Report> reports;
	
	public User() {}
	
	public User(Integer id, @NotEmpty(message = "*Please provide your personal id") String personalId,
			@NotEmpty(message = "*Please provide your first name") String fName,
			@NotEmpty(message = "*Please provide your last name") String lname,
			@Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String pass,
			int active) {
		super();
		this.id = id;
		this.personalId = personalId;
		this.FName = fName;
		this.Lname = lname;
		this.pass = pass;
		
		this.active = active;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String fName) {
		FName = fName;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getFinger() {
		return finger;
	}

	public void setFinger(String finger) {
		this.finger = finger;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", personalId=" + personalId + ", finger=" + finger + ", fullname=" + fullname
				+ ", roles=" + roles + ", reports=" + reports + "]";
	}


	
}
