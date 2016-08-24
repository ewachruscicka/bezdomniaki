package pl.bezdomniaki.dao;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.bezdomniaki.Schronisko;

public class SchroniskoDAOTest {

	static SchroniskoDAO schroniskoDAO;
	static Connection conn;
	
	@BeforeClass
	public static void zainicjujTesty() {
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(
			"jdbc:sqlserver://127.0.0.1:1433;databaseName=psy","ewa","haslo");
			System.out.println("Po³¹czenie z BD nawi¹zane!\n");
			/* Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from pracownicy");
			while(rs.next())
			System.out.println(rs.getString(1)+"|"+rs.getString(4));
			con.close(); */
			}
		catch(SQLException ec) {ec.printStackTrace();}
		catch(ClassNotFoundException ex) {ex.printStackTrace();}
		
		schroniskoDAO = new SchroniskoDAO();
		schroniskoDAO.setCon(conn);
		}

	@Test
	public void testCreate() throws Exception{
		System.out.println("TEST METODY CREATE\n");
		Schronisko schronisko = new Schronisko();
		schronisko.setNazwa("Schronisko Testowe");
		schronisko.setMiejscowosc("Testerkowo 50");
		schronisko.setTelefon("666666666");
		schronisko.setEmail("testowe@tt.pl");
		
		schroniskoDAO.create(schronisko);
		System.out.println("Utworzono nowe schronisko:" + schronisko +"\n");
	}
	
	@Test
	public void testUpdate() throws Exception{
		System.out.println("TEST METODY UPDATE\n");
		List <Schronisko> listaSchronisk = schroniskoDAO.listAll();
		Schronisko schronisko = listaSchronisk.get(0);
		System.out.println("Schronisko przed edycj¹: " + schronisko);
		schronisko.setNazwa("Schronisko Zmnienione");
		schronisko.setMiejscowosc("a");
		
		schroniskoDAO.update(schronisko);
		System.out.println("Schronisko po zmianie: " + schronisko + "\n");
	}

	@Test
	public void testDelete() throws Exception{
		System.out.println("TEST METODY DELETE\n");
		List <Schronisko> listaSchronisk = schroniskoDAO.listAll();
		System.out.println("Lista schronisk przed usuniêciem pozycji: ");
		for (Schronisko s : listaSchronisk) {
			System.out.println(s);
		System.out.println("Iloœæ schronisk przed usuniêciem pozycji: " + listaSchronisk.size());
		}
		Schronisko schronisko = listaSchronisk.get(3);
		schroniskoDAO.delete(schronisko);
		System.out.println("Lista schronisk po usuniêciu pozycji: ");
		List <Schronisko> listaSchroniskNowa =schroniskoDAO.listAll();
		for (Schronisko s : listaSchroniskNowa) {
			System.out.println(s);
		}
		System.out.println("Iloœæ schronisk po usuniêciu pozycji: " + listaSchroniskNowa.size() + "\n");
	}	
	
	@Test
	public void testListAll() throws SQLException{
		System.out.println("TEST METODY LISTALL\n");
		List <Schronisko> listaSchronisk = schroniskoDAO.listAll();
		assertTrue(listaSchronisk.size() > 0);
		for (Schronisko schronisko : listaSchronisk) {
			System.out.println(schronisko);
		System.out.println();
		}
	}
	
	
}


