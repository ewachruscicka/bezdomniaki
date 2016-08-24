package pl.bezdomniaki.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import pl.bezdomniaki.Pies;
import pl.bezdomniaki.Schronisko;
import pl.bezdomniaki.dao.PiesDAO;

public class PiesDAOTest {
	static Connection conn;
	static PiesDAO piesDAO;
	
	
	@BeforeClass
	public static void zainicjujTesty() throws Exception {
		
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
			//conn.setAutoCommit(false);
			}
		catch(SQLException ec) {ec.printStackTrace();}
		catch(ClassNotFoundException ex) {ex.printStackTrace();}
		
		piesDAO = new PiesDAO();
		piesDAO.setCon(conn);
	}
		
	
	@Test
	public void testCreate() throws Exception{
		System.out.println("TEST METODY CREATE\n");
		Pies pies = new Pies();
		pies.setDataPrzyjecia(new Date());
		pies.setIdSchroniska(1);
		pies.setImie("Fedra" + new Date());
		pies.setNrChripa("0002"+ new Date());
		piesDAO.create(pies);
		
		System.out.println("Zapisano psa:" + pies +"\n");
	}
	
	@Test
	public void testUpdate() throws Exception{
		//conn.commit();
		System.out.println("TEST METODY UPDATE\n");
		List <Pies> listaPsow = piesDAO.listAll();
		Pies pies = listaPsow.get(0);
		System.out.println("Pies przed edycj¹: " + pies);
		pies.setDataPrzyjecia(new Date());
		pies.setImie("Tester");
		System.out.println(pies.getNrChipa());
		pies.setNrChripa("9999");
		piesDAO.update(pies);
		System.out.println("Pies po edycji: " + pies + "\n");
	}
	
	@Test
	public void testDelete() throws Exception{
		System.out.println("TEST METODY DELETE\n");
		List <Pies> listaPsow = piesDAO.listAll();
		System.out.println("Lista psów przed usuniêciem pozycji: ");
		for (Pies p : listaPsow) {
			System.out.println(p);
		System.out.println("Iloœæ psów przed usuniêciem pozycji: " + listaPsow.size());
		}
		Pies pies = listaPsow.get(0);
		piesDAO.delete(pies);
		System.out.println("Lista psów po usuniêciu pozycji: ");
		List <Pies> listaPsowNowa = piesDAO.listAll();
		for (Pies p : listaPsowNowa) {
			System.out.println(p);
		}
		System.out.println("Iloœæ psów po usuniêciu pozycji: " + listaPsowNowa.size() + "\n");
	}	
	
	@Test
	public void testListAll() throws Exception{
		System.out.println("TEST METODY LISTALL\n");
		List <Pies> listaPsow = piesDAO.listAll();
		assertTrue(listaPsow.size() > 0);
		for (Pies pies : listaPsow) {
			System.out.println(pies);
			System.out.println();
		}
	}
	
	@Test
	public void findByCity() throws SQLException{
		System.out.println("TEST METODY FINDBYCITY\n");
		List <Pies> listaWybranychPsow = piesDAO.findByCity("a");
		assertTrue(listaWybranychPsow.size() > 0);
		for (Pies pies : listaWybranychPsow) {
			System.out.println(pies);
			System.out.println();
		}
	}
	
}
