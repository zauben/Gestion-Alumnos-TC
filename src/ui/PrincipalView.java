package ui;

import controlers.CursoController;
import controlers.PersonaController;
import controlers.InscripcionCotroller;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalView extends JFrame {

	private JPanel mainFrame;
	private PersonaController cp;
	private CursoController el;
	private InscripcionCotroller cr;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalView frame = new PrincipalView();
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
	public PrincipalView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAdministrar = new JMenu("Administrar");
		menuBar.add(mnAdministrar);

		JMenuItem mntmPersonas = new JMenuItem("Gestion de Alumnos");
		mntmPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cp = new PersonaController();
				cp.dialogoAlumnos();
			}
		});
		mnAdministrar.add(mntmPersonas);

		JMenuItem mntmNuevaInscripcion = new JMenuItem("Nueva Inscripcion");
		mnAdministrar.add(mntmNuevaInscripcion);
		mntmNuevaInscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cr = new InscripcionCotroller();
				cr.DialogoReserva();
			}
		});
		mainFrame = new JPanel();
		mainFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setLayout(new BorderLayout(0, 0));
		setContentPane(mainFrame);
	}

}
