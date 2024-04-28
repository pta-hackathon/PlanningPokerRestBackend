package pta.MultistagePoker.dbEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class User {
	@Id
	@Column
	int id;

	@Column
	String name;

	@Column
	String competence;

	@Column
	int isSignedIn;

	@Column
	int isTestUser;	

	@Transient
	String userstatus;	
	
	
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

	public int getIsSignedIn() {
		return isSignedIn;
	}

	public void setIsSignedIn(int isSignedIn) {
		this.isSignedIn = isSignedIn;
	}

	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

	public int getIsTestUser() {
		return isTestUser;
	}

	public void setIsTestUser(int isTestUser) {
		this.isTestUser = isTestUser;
	}

	public String getUserstatus() {
		return userstatus;
	}

	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}


	
}
