package pta.MultistagePoker.dbEntities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class EstimateRelation {

	@Id
	@Column
	int id;
	
	@Column
	int idUser;
	
	@Column
	int idUserEsteemed;
	
	@Column
	int idTicket;

	@Column
	int idEstimate;
	
	@Column
	Timestamp timeStamp;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdUserEsteemed() {
		return idUserEsteemed;
	}

	public void setIdUserEsteemed(int idUserEsteemed) {
		this.idUserEsteemed = idUserEsteemed;
	}

	public int getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(int idTicket) {
		this.idTicket = idTicket;
	}

	public int getIdEstimate() {
		return idEstimate;
	}

	public void setIdEstimate(int idEstimate) {
		this.idEstimate = idEstimate;
	}
	
	
}
