package app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;


public class BuscarFecha extends JPanel{

	private static final long serialVersionUID = 1L;
	private SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd");
	JPanel panelBusqueda, panelAcciones;
	JDateChooser fromChooser, toChooser;

	public BuscarFecha() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panelBusqueda = new JPanel(new FlowLayout());
		add(panelBusqueda, BorderLayout.CENTER);
		
		Dimension d = new Dimension(150, 25);
		Date fecha_actual = new Date();
		fromChooser = new JDateChooser(fecha_actual);
		fromChooser.setPreferredSize(d);
		fromChooser.setDateFormatString("yyyy-MM-dd");
		panelBusqueda.add(fromChooser);
		
		toChooser = new JDateChooser(fecha_actual);
		toChooser.setPreferredSize(d);
		toChooser.setDateFormatString("yyyy-MM-dd");
		panelBusqueda.add(toChooser);
		
		panelAcciones = new JPanel(new FlowLayout());
		add(panelAcciones, BorderLayout.SOUTH);
		
		JButton buscar = new JButton("Realizar Busqueda");
		panelAcciones.add(buscar);
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String antes, despues;
				if (fromChooser.getDate().after(toChooser.getDate())) {
					despues = date_format.format(fromChooser.getDate());
					antes = date_format.format(toChooser.getDate());
				} else {
					despues = date_format.format(toChooser.getDate());
					antes = date_format.format(fromChooser.getDate());
					
				}
				String consulta ="SELECT * FROM vista WHERE fecha >= '"+antes+"' and fecha <= '"+despues+"'"; 
				Main.buscar(consulta);
				System.out.println(consulta);
			}
		});

		
	}

}
