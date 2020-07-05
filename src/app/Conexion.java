package app;

import java.sql.*;

public class Conexion {
	
	private Connection conn;
	private String user = "root";
	private String password = "";
	private String bdname = "agenda";
	private String timezone = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String url = "jdbc:mysql://localhost:3306/" + bdname + timezone;
	
	public Conexion(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			e1.getLocalizedMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getLocalizedMessage();
		}
	}
	
	public Statement getStatement() {
		try {
			//conn.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			if (!conn.isClosed()) return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getLocalizedMessage();
		}
		
		return null;
	}
	
	public void desconectar() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PreparedStatement getStatementAgregar() {
		try {
			if (!conn.isClosed()) {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO evento(`descripcion`, `tipo`, `fecha`) VALUES (?,?,?) ");
				return ps;
			}
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PreparedStatement getStatementModificar() {
		try {
			if (!conn.isClosed()) {
				PreparedStatement ps = conn.prepareStatement("UPDATE `evento` SET descripcion=?,tipo=?,fecha=? WHERE id = ?");
				return ps;
			}
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public PreparedStatement getStatementEliminar() {
		try {
			if (!conn.isClosed()) {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM `evento` WHERE id = ?");
				return ps;
			}
			
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
