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
	
	@Column(columnDefinition="nvarchar(50)")
	private String fullname;
	
//	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@JsonIgnore
	@ManyToMany
	private List<Role> roles;
	
	
	@OneToMany(mappedBy="user")
	private List<Base> bases;
	
	public User() {}
	
	public User(Integer id, @NotEmpty(message = "*Please provide your personal id") String personalId,
			String full_name, String finger,
			@Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String pass) {
		super();
		this.id = id;
		this.personalId = personalId;
		this.fullname = full_name;
		this.pass = pass;
		this.finger = finger;
		
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

	public List<Base> getBases() {
		return bases;
	}

	public void setBases(List<Base> bases) {
		this.bases = bases;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", personalId=" + personalId + ", FName=" + FName + ", Lname=" + Lname + ", active="
				+ active + ", finger=" + finger + ", fullname=" + fullname + ", roles=" + roles + ", bases=" + bases
				+ "]";
	}


	
}
