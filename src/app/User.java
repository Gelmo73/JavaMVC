package app;

import javax.swing.*;
import java.awt.*;

public class User extends JPanel{


	private static final long serialVersionUID = 1L;
	private JLabel foto;
	private JLabel nombre;
	private JLabel apellido;
	private JLabel matricula;

	/**
	 * Create the application.
	 */
	public User() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ImageIcon imagen = new ImageIcon("C:\\Users\\Alejandro\\eclipse-workspace\\Agenda\\usuario.png");
		Font nombres = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 0};
		gridBagLayout.rowHeights = new int[]{200, 24, 24, 24, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		foto = new JLabel(new ImageIcon(imagen.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		foto.setPreferredSize(new Dimension(200, 200));
		
		
		GridBagConstraints gbc_foto = new GridBagConstraints();
		gbc_foto.insets = new Insets(0, 0, 5, 0);
		gbc_foto.gridx = 0;
		gbc_foto.gridy = 0;
		add(foto, gbc_foto);
		
		nombre = new JLabel("Jose Alejandro");
		nombre.setFont(nombres);
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.insets = new Insets(0, 0, 5, 0);
		gbc_nombre.gridx = 0;
		gbc_nombre.gridy = 1;
		add(nombre, gbc_nombre);
		
		apellido = new JLabel("Germosen Mateo");
		apellido.setFont(nombres);
		GridBagConstraints gbc_apellido = new GridBagConstraints();
		gbc_apellido.insets = new Insets(0, 0, 5, 0);
		gbc_apellido.gridx = 0;
		gbc_apellido.gridy = 2;
		add(apellido, gbc_apellido);
		
		matricula = new JLabel("2019-7867");
		matricula.setFont(nombres);
		GridBagConstraints gbc_matricula = new GridBagConstraints();
		gbc_matricula.gridx = 0;
		gbc_matricula.gridy = 3;
		add(matricula, gbc_matricula);
	}

}
