package app;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BuscarTipo extends JPanel{

	private static final long serialVersionUID = 1L;
	JComboBox<String> tipo;
	JPanel panelBusqueda, panelAcciones;

	public BuscarTipo(JComboBox<String> tipo) {
		this.tipo = tipo;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panelBusqueda = new JPanel();
		add(panelBusqueda, BorderLayout.CENTER);
		
		panelBusqueda.add(tipo);
		
		panelAcciones = new JPanel(new FlowLayout());
		add(panelAcciones, BorderLayout.SOUTH);
		
		JButton buscar = new JButton("Realizar Busqueda");
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.buscar("SELECT * FROM vista WHERE nombre = '" + tipo.getSelectedItem() +"'");				
			}
		});
		panelAcciones.add(buscar);
		
	}

}
