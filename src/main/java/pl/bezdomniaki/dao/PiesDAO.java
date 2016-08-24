package pl.bezdomniaki.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import pl.bezdomniaki.Pies;
import pl.bezdomniaki.Schronisko;

public class PiesDAO {

	Connection conn;

	public void setCon(Connection con) {
		this.conn = con;
	}

	/*
	 * public void create1(Pies pies) throws SQLException{ Statement stmt =
	 * con.createStatement(); StringBuilder sql = new StringBuilder(
	 * "INSERT INTO pies (id, imie, data_przyjecia, id_schroniska, nr_chipa) VALUES ("
	 * ); sql.append(pies.getId()); sql.append(",'");
	 * sql.append(pies.getImie()); sql.append("','"); SimpleDateFormat df = new
	 * SimpleDateFormat("yyyy-MM-dd");
	 * sql.append(df.format(pies.getDataPrzyjecia())); sql.append("',");
	 * sql.append(pies.getIdSchroniska()); sql.append(",'");
	 * sql.append(pies.getNrChripa()); sql.append("')");
	 * System.out.println(sql); stmt.executeUpdate(sql.toString());
	 * stmt.close(); }
	 */

	public void create(Pies pies) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO Pies (imie, data_przyjecia, id_schroniska, nr_chipa) VALUES (?, ?, ?, ? )",
				Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, pies.getImie());
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate = new java.sql.Date(pies.getDataPrzyjecia().getYear(), pies.getDataPrzyjecia().getMonth(),
				pies.getDataPrzyjecia().getDay());
		//try{
		stmt.setDate(2, sqlDate);
		stmt.setInt(3, pies.getIdSchroniska());
		stmt.setString(4, pies.getNrChipa());
		stmt.execute();
		
		/*stmt.setString(1, pies.getImie() + "aaa");
		stmt.setString(4, pies.getNrChipa());
		stmt.execute();
		conn.commit();
		}
		catch (SQLException e){
			e.printStackTrace();
			conn.rollback();
			throw e;
		}*/

		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if (generatedKeys.next())
			pies.setId(generatedKeys.getInt(1));

		stmt.close();
	}

	public void update(Pies pies) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(
				"UPDATE Pies SET imie = ?, data_przyjecia = ?, id_schroniska = ?, nr_chipa = ? WHERE id = ?");
		stmt.setString(1, pies.getImie());
		java.sql.Date sqlDate = new java.sql.Date(pies.getDataPrzyjecia().getYear(), pies.getDataPrzyjecia().getMonth(),
				pies.getDataPrzyjecia().getDay());
		stmt.setDate(2, sqlDate);
		stmt.setInt(3, pies.getIdSchroniska());
		stmt.setString(4, pies.getNrChipa());
		stmt.setInt(5, pies.getId());
		stmt.execute();
		stmt.close();
	}

	public void delete(Pies pies) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM Pies WHERE id = ?");
		stmt.setInt(1, pies.getId());
		System.out.println(pies.getId());
		stmt.execute();
		stmt.close();
	}

	public List<Pies> listAll() throws SQLException {
		ArrayList<Pies> listaPsow = new ArrayList<Pies>();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM Pies");
		while (rs.next()) {
			Pies pies1 = new Pies();

			pies1.setId(rs.getInt("id"));
			// w getIn mozna nr kolumny albo jej "nazwe"
			pies1.setImie(rs.getString("imie"));
			pies1.setDataPrzyjecia(rs.getDate("data_przyjecia"));
			pies1.setIdSchroniska(rs.getInt("id_schroniska"));
			pies1.setNrChripa(rs.getString("nr_chipa"));
			listaPsow.add(pies1);
		}
		rs.close();
		stmt.close();

		return listaPsow;
	}

	public List<Pies> findByCity(String city) throws SQLException {
		ArrayList<Pies> listaPsow = new ArrayList<Pies>();
		PreparedStatement stmt = conn.prepareStatement("SELECT p.imie, p.data_przyjecia, p.nr_chipa, p.id, p.id_schroniska, s.nazwa, s.miejscowosc"
				+ " FROM Pies as p JOIN Schronisko AS s ON s.id = p.id_schroniska"
				+ " WHERE s.miejscowosc = ?");
		stmt.setString(1, city);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
				Pies pies1 = new Pies();
				pies1.setId(rs.getInt("id"));
				pies1.setImie(rs.getString("imie"));
				pies1.setDataPrzyjecia(rs.getDate("data_przyjecia"));
				pies1.setIdSchroniska(rs.getInt("id_schroniska"));
				pies1.setNrChripa(rs.getString("nr_chipa"));
				listaPsow.add(pies1);
			}
			rs.close();
			stmt.close();
			return listaPsow;
	}

	/*
	 * public List<Pies> findByCity(String city) throws SQLException {
	 * ArrayList<Pies> listaPsow = new ArrayList<Pies>(); PreparedStatement stmt
	 * = conn.prepareStatement("SELECT * FROM Schronisko WHERE miejscowosc = ?"
	 * ); stmt.setString(1, city); ResultSet rs = stmt.executeQuery();
	 * 
	 * while (rs.next()) { //System.out.println("w petli"); PreparedStatement
	 * stmt2 = conn.prepareStatement(
	 * "SELECT * FROM Pies WHERE id_schroniska = ? "); stmt2.setInt(1,
	 * (rs.getInt("id"))); ResultSet rs2 = stmt2.executeQuery(); while
	 * (rs2.next()) { //System.out.println("w petli 2"); Pies pies1 = new
	 * Pies(); pies1.setId(rs2.getInt("id"));
	 * pies1.setImie(rs2.getString("imie"));
	 * pies1.setDataPrzyjecia(rs2.getDate("data_przyjecia"));
	 * pies1.setIdSchroniska(rs2.getInt("id_schroniska"));
	 * pies1.setNrChripa(rs2.getString("nr_chipa")); listaPsow.add(pies1); }
	 * rs2.close(); stmt2.close(); } rs.close(); stmt.close(); return listaPsow;
	 * }
	 */

}
