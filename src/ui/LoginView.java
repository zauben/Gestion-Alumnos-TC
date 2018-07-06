package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlers.PersonaController;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class LoginView extends JFrame {
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;

	/**
	 * Launch the application.
	 */
	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblSdf = new JLabel("Usuario");

		JLabel lblContrasea = new JLabel("Contraseña");

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);

		txtContraseña = new JPasswordField();
		txtContraseña.setColumns(10);

		JButton btnAceptar = new JButton("Iniciar Sesion");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarSesion();
			}

			private void iniciarSesion() {
				PersonaController ctrl = new PersonaController();
				Boolean valido = ctrl.validarUSR(txtUsuario.getText(), txtContraseña.getText());
				if (valido == true) {
					ctrl.principalFrame();
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Usuario erroneo o no habilitado, intente nuevamente");
					cleanscreen();
				}
				// TODO Auto-generated method stub

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false).addGroup(gl_contentPane
						.createSequentialGroup().addGap(51)
						.addGroup(gl_contentPane
								.createParallelGroup(Alignment.LEADING).addComponent(lblSdf).addComponent(lblContrasea))
						.addGap(32)
						.addGroup(
								gl_contentPane.createParallelGroup(Alignment.LEADING, false).addComponent(txtContraseña)
										.addComponent(txtUsuario, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(Alignment.TRAILING,
								gl_contentPane.createSequentialGroup()
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAceptar).addGap(58)))
				.addContainerGap(95, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblSdf).addComponent(
						txtUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(lblContrasea)
						.addComponent(txtContraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(btnAceptar).addContainerGap(23, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	private void cleanscreen() {
		this.txtUsuario.setText("");
		this.txtContraseña.setText("");

	}

}
