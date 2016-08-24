package pl.bezdomniaki.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.bezdomniaki.Schronisko;

public class SchroniskoDAO {
	Connection conn;

	public void setCon(Connection con) {
		this.conn = con;
	}

	public void create(Schronisko schronisko) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO Schronisko (nazwa,miejscowosc, telefon, email) VALUES (?, ?, ?, ? )",
				Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, schronisko.getNazwa());
		stmt.setString(2, schronisko.getMiejscowosc());
		stmt.setString(3, schronisko.getTelefon());
		stmt.setString(4, schronisko.getEmail());
		stmt.execute();

		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if (generatedKeys.next())
			System.out.println(generatedKeys.getInt(1));
			schronisko.setId(generatedKeys.getInt(1));

		stmt.close();
	}

	public void update(Schronisko schronisko) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement("UPDATE Schronisko SET nazwa = ?, miejscowosc = ?, telefon = ?, email = ? WHERE id = ?");
		stmt.setString(1, schronisko.getNazwa());
		stmt.setString(2, schronisko.getMiejscowosc());
		stmt.setString(3, schronisko.getTelefon());
		stmt.setString(4, schronisko.getEmail());
		stmt.setInt(5, schronisko.getId());
		stmt.execute();
		stmt.close();
	}

	public void delete(Schronisko schronisko) throws SQLException{
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM Schronisko WHERE id = ?");
		stmt.setInt(1, schronisko.getId());
		System.out.println("Usuwam schronisko o id = " + schronisko.getId() + " i wszystkie jego psy");
		stmt.execute();
		stmt.close();
	}

	public List<Schronisko> listAll() throws SQLException {
		ArrayList<Schronisko> listaSchronisk = new ArrayList<Schronisko>();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Schronisko");
		while (rs.next()) {
			Schronisko schronisko1 = new Schronisko();
			schronisko1.setId(rs.getInt("id"));
			// w getIn mozna nr kolumny albo jej "nazwe"
			schronisko1.setNazwa(rs.getString("nazwa"));
			schronisko1.setMiejscowosc(rs.getString("miejscowosc"));
			schronisko1.setTelefon(rs.getString("telefon"));
			listaSchronisk.add(schronisko1);
		}
		rs.close();
		stmt.close();

		return listaSchronisk;
	}

}
