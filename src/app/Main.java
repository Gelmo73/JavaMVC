package app;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JPanel userPanel, actionPanel;
	private static Tabla mainPanel;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JMenuBar menu = new Menu();

	/**
	 * Launch the application.
	 */
	
	public static JComboBox<String> getTableTipo() {
		JComboBox<String> cbx = new JComboBox<String>();
		cbx.setModel(mainPanel.getCbxTipo().getModel());
		return cbx;
	}
	
	public static void buscar(String consulta) {
		mainPanel.buscarTabla(consulta);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
		frame.validate();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Agenda Personal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menu);
		
		GridBagLayout gbl_actionPanel = new GridBagLayout();
		gbl_actionPanel.rowWeights = new double[]{0.0, 1.0};
		
		frame.getContentPane().setLayout(gbl_actionPanel);
		
		userPanel = new User();
		GridBagConstraints gbc_userPanel = new GridBagConstraints();
		gbc_userPanel.insets = new Insets(20, 20, 20, 20);
		gbc_userPanel.gridheight = 2;
		gbc_userPanel.gridy = 0;
		gbc_userPanel.gridx = 0;
		frame.getContentPane().add(userPanel, gbc_userPanel);
		
		gbc_userPanel.gridheight = 1;
		mainPanel = new Tabla();
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.insets = new Insets(0, 0, 5, 0);
		gbc_mainPanel.gridx = 1;
		gbc_mainPanel.gridy = 0;
		frame.getContentPane().add(mainPanel, gbc_mainPanel);
		
		actionPanel = new JPanel();
		GridBagConstraints gbc_actionPanel = new GridBagConstraints();
		gbc_actionPanel.ipadx = 10;
		gbc_actionPanel.anchor = GridBagConstraints.SOUTH;
		gbc_actionPanel.gridy = 1;
		gbc_actionPanel.gridx = 1;
		frame.getContentPane().add(actionPanel, gbc_actionPanel);
		actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));
		
		btnAgregar = new JButton("Agregar");
		actionPanel.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		actionPanel.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		actionPanel.add(btnEliminar);
		
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.agregarDatos();
			}
		});
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.modificarDatos();
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.eliminarDatos();
			}
		});

	}

	

}
