package pl.bezdomniaki;

public class Schronisko {
	private int id;
	private String nazwa;
	private String miejscowosc;
	private String telefon;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getMiejscowosc() {
		return miejscowosc;
	}
	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Schronisko [id=" + id + ", nazwa=" + nazwa + ", miejscowosc=" + miejscowosc + ", telefon=" + telefon + ", email="
				+ email + "]";
	}
	
	
	
}

