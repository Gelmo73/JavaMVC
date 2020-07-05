package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Menu extends JMenuBar{

	private static final long serialVersionUID = 1L;
	public JMenu buscar;
	public JMenuItem fecha, tipo_evento;
	
	public Menu() {
		initialize();
	}
	
	void initialize() {
		buscar = new JMenu("Buscar por");
		fecha = new JMenuItem("Rango de Fecha");
		tipo_evento = new JMenuItem("Tipo de evento");
		
		add(buscar);
		
		buscar.add(fecha);
		buscar.add(tipo_evento);
		
		tipo_evento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showOptionDialog(null, new BuscarTipo(Main.getTableTipo()), "Busqueda por Tipo", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
						new Object[] {}, null);
			}
		});
		
		fecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showOptionDialog(null, new BuscarFecha(), "Busqueda por Fecha", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
						new Object[] {}, null);
			}
		});
	}
}
