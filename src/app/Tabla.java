 package app;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Tabla extends JPanel{

	private static final long serialVersionUID = 1L;
	public JTable table;
	public Object[][] filas;
	private JTextField descripcion;
	private JComboBox<String> tipo;
	private ResultSetTableModel modelo;
	private TableColumn col_id, col_descripcion, col_tipo, col_fecha;
	private JDateChooser dateChooser;
	private SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
	private int IdNumber;
	private Conexion conn;
	
	public JComboBox<String> getCbxTipo(){
		return tipo;
	}
	
	public Tabla() {
		IdNumber = 0;
		initialize();
		llenarTipo();
	}
	
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int selection = table.rowAtPoint(e.getPoint());
				IdNumber = (int) table.getValueAt(selection, 0);
				descripcion.setText(String.valueOf(table.getValueAt(selection, 1)));
				tipo.setSelectedItem(String.valueOf(table.getValueAt(selection, 2)));
				dateChooser.setDate((Date) table.getValueAt(selection, 3));
				System.out.println("Cambiado " + IdNumber);
			}
		}); 
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		actualizarTabla();
		
		
		add(new JScrollPane(table), BorderLayout.CENTER);
		
		JPanel pnl_acciones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnl_acciones.getLayout();
		flowLayout.setHgap(10);
		add(pnl_acciones, BorderLayout.SOUTH);
		
		descripcion = new JTextField();
		pnl_acciones.add(descripcion);
		descripcion.setColumns(50);
		
		tipo = new JComboBox<String>();
		pnl_acciones.add(tipo);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setPreferredSize(new Dimension(150, 25));
		Date fecha_actual = new Date();
		dateChooser.setDate(fecha_actual);
		dateChooser.setMinSelectableDate(fecha_actual);
		pnl_acciones.add(dateChooser);
	
	}
	
	void llenarTipo() {
		
		try {
			Conexion conn = new Conexion();
			String consulta = "SELECT * FROM tipo_evento";
			Statement stm = conn.getStatement();
			ResultSet result = stm.executeQuery(consulta);
			
			while(result.next()) {
				tipo.addItem(result.getString(2));
			}
			result.close();
			conn.desconectar();
			
		} catch (SQLException e) {
			e.getLocalizedMessage();
			e.printStackTrace();
			System.out.println("Error con el tipo");
		}

	}
	
	public void actualizarTabla() {
		conn = new Conexion();
		String consulta = "SELECT * FROM vista";
		Statement stm = conn.getStatement();
		rellenarTabla(stm, consulta);
	}
	
	public void buscarTabla(String consulta) {
		conn = new Conexion();
		Statement stm = conn.getStatement();
		rellenarTabla(stm, consulta);
	}
	
	public void rellenarTabla(Statement stm, String consulta) {

		try {
			ResultSet result = stm.executeQuery(consulta);
			
			modelo = new ResultSetTableModel(result);
			table.setModel(modelo);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error de la tabla");
			e.getLocalizedMessage();
		}
		

		col_id = table.getColumnModel().getColumn(0);
		col_descripcion = table.getColumnModel().getColumn(1);
		col_tipo = table.getColumnModel().getColumn(2);
		col_fecha = table.getColumnModel().getColumn(3);
		
		col_id.setPreferredWidth(40);
		col_id.setResizable(false);
		col_id.setMinWidth(20);
		col_id.setMaxWidth(40);
		
		col_descripcion.setResizable(false);
		col_descripcion.setPreferredWidth(200);
		col_descripcion.setMinWidth(180);
		
		col_tipo.setResizable(false);
		col_tipo.setPreferredWidth(80);
		col_tipo.setMinWidth(70);
		col_tipo.setMaxWidth(80);
		
		col_fecha.setResizable(false);
		col_fecha.setPreferredWidth(110);
		col_fecha.setMinWidth(90);
		col_fecha.setMaxWidth(110);
	}
	
	public void agregarDatos() { 
		if (descripcion.getText().trim().length() == 0) return;
		String date = date_format.format(dateChooser.getDate());
		conn = new Conexion();
		try {
			PreparedStatement ps = conn.getStatementAgregar();
			ps.setString(1, this.descripcion.getText());
			ps.setInt(2, tipo.getSelectedIndex() + 1);
			ps.setString(3, date);
			ps.executeUpdate();
			actualizarTabla();
			
		} catch (Exception e) {
			System.out.println("Error en insercion");
			e.printStackTrace();
		}
	}
	
	public void modificarDatos() { 
		if (IdNumber == 0) return;
		if (descripcion.getText().trim().length() == 0) return;
		String date = date_format.format(dateChooser.getDate());
		conn = new Conexion();
		try {
			PreparedStatement ps = conn.getStatementModificar();
			ps.setString(1, this.descripcion.getText());
			ps.setInt(2, tipo.getSelectedIndex() + 1);
			ps.setString(3, date);
			ps.setInt(4, IdNumber);
			ps.executeUpdate();
			actualizarTabla();
			
		} catch (Exception e) {
			System.out.println("Error en modificacion");
			e.printStackTrace();
		}
	}
	
	public void eliminarDatos() { 
		if (IdNumber == 0) return;
		conn = new Conexion();
		try {
			PreparedStatement ps = conn.getStatementEliminar();
			ps.setInt(1, IdNumber);
			ps.executeUpdate();
			actualizarTabla();
			
		} catch (Exception e) {
			System.out.println("Error en eliminacion");
			e.printStackTrace();
		}
	}

}

class ResultSetTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private ResultSetMetaData data;
	
	public ResultSetTableModel (ResultSet rs) {
		this.rs = rs;
		try {
			data = this.rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
			e.getLocalizedMessage();
		}
	}
	@Override
	public int getRowCount() {
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
			e.getLocalizedMessage();
		}
		return 0;
	}

	@Override
	public int getColumnCount() {
		try {
			return data.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
			e.getLocalizedMessage();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		try {
			rs.absolute(rowIndex + 1);
			return rs.getObject(columnIndex + 1);
		} catch (Exception e) {
			e.printStackTrace();
			e.getLocalizedMessage();
			return null;
		}
	}
	
	public String getColumnName(int c) {
		try {
			if (c == 2) return "TIPO";
			return data.getColumnName(c + 1).toUpperCase();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
