package ui;
import controlers.CtrlABMElemento;
import controlers.CtrlABMPersona;
import controlers.CtrlABMInscripcion;
import controlers.CtrlABMTipo;

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

public class PrincipalDestkop extends JFrame {

	private JPanel mainFrame;
	private CtrlABMPersona cp;
	private CtrlABMElemento el;
	private CtrlABMTipo te;
	private CtrlABMInscripcion cr;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalDestkop frame = new PrincipalDestkop();
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
	public PrincipalDestkop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAdministrar = new JMenu("Administrar");
		menuBar.add(mnAdministrar);
		
		JMenuItem mntmPersonas = new JMenuItem("Gestion de Personas");
		mntmPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cp = new CtrlABMPersona();
				cp.dialogoAlumnos();
			}
		});
		mnAdministrar.add(mntmPersonas);
		
		JMenuItem mntmCursos = new JMenuItem("Cursos");
		mntmCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				te = new CtrlABMTipo();
				te.DialogoTipo();
			}
		});
		
		JMenuItem mntmNuevaInscripcion = new JMenuItem("Nueva Inscripcion");
		mnAdministrar.add(mntmNuevaInscripcion);
		mntmNuevaInscripcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cr = new CtrlABMInscripcion();
				cr.DialogoReserva();
			}
		});
		mnAdministrar.add(mntmCursos);
		
		JMenuItem mntmCarreras = new JMenuItem("Carreras");
		mntmCarreras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				el = new CtrlABMElemento();
				el.DialogoElementos();
			}
		});
		mnAdministrar.add(mntmCarreras);
		mainFrame = new JPanel();
		mainFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setLayout(new BorderLayout(0, 0));
		setContentPane(mainFrame);
	}

}
