package application;

import java.io.Serializable;

public class Mangija implements Serializable, Comparable<Mangija>{

	int skoor;
	int arvatud;
	String nimi;
	
	Mangija() { }
	Mangija(String nimi, int skoor, int arvatud) {
		this.skoor = skoor;
		this.nimi = nimi;
	}
	int getArvatud() {
		return arvatud;
	}
	void setArvatud(int arvatud) {
		this.arvatud = arvatud;
	}
	int getSkoor() {
		return skoor;
	}
	void setSkoor(int skoor) {
		this.skoor = skoor;
	}
	String getNimi() {
		return nimi;
	}
	void setNimi(String nimi) {
		this.nimi = nimi;
	}
	public boolean isPlaying() {
		return true;
	}
	void mangijaReset() {
		this.nimi = "";
		this.skoor = 0;
		this.arvatud = 0;
	}
	@Override
	public int compareTo(Mangija m) {
		
		int vordleSkoor = ((Mangija) m).getSkoor();
		 if (this.skoor > vordleSkoor) {
		      return -1;
		    } else if (this.skoor < vordleSkoor) {
		      return 1;
		    } else {
		      return 0;
		    }

	}
	@Override
	public String toString() {
		return nimi + "\t\tÕigesti arvatud: " + arvatud + " sõna\t\tSkoor: " + skoor;
	}
	
}
