package pl.bezdomniaki;

import java.util.Date;

public class Pies {
	private int id;
	private String imie;
	private Date dataPrzyjecia;
	private int idSchroniska;
	private String nrChipa;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}
	public Date getDataPrzyjecia() {
		return dataPrzyjecia;
	}
	public void setDataPrzyjecia(Date dataPrzyjecia) {
		this.dataPrzyjecia = dataPrzyjecia;
	}
	public int getIdSchroniska() {
		return idSchroniska;
	}
	public void setIdSchroniska(int idSchroniska) {
		this.idSchroniska = idSchroniska;
	}
	public String getNrChipa() {
		return nrChipa;
	}
	public void setNrChripa(String nrChripa) {
		this.nrChipa = nrChripa;
	}
	
	@Override
	public String toString() {
		return "Pies [id=" + id + ", imie=" + imie + ", dataPrzyjecia=" + dataPrzyjecia + ", idSchroniska="
				+ idSchroniska + ", nrChripa=" + nrChipa + "]";
	}
	
	
}
