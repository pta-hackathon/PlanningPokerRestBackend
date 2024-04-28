package pta.MultistagePoker.dbEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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


	
}
