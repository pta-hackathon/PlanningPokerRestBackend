package pta.MultistagePoker.Controller;

public class TabellenEintrag implements Comparable<TabellenEintrag> {

	int idUser;
	String userName;
	double punkteSumme=0.0;
	
	
	public TabellenEintrag(int id, String userName) {
		super();
		this.idUser = id;
		this.userName = userName;
	}
	
	@Override
	public int compareTo(TabellenEintrag o) {
		return Math.round( (int) (o.getPunkteSumme()-punkteSumme) );
	}

	public void addPunkte(double p) {
		punkteSumme+=p;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getPunkteSumme() {
		return punkteSumme;
	}

	public void setPunkteSumme(double punkteSumme) {
		this.punkteSumme = punkteSumme;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

}
