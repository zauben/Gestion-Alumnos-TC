package ui;
import controlers.CtrlABMElemento;
import controlers.CtrlABMPersona;
import controlers.CtrlABMReserva;
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
	private CtrlABMReserva cr;

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
		
		JMenu mnReservas = new JMenu("Reservas");
		menuBar.add(mnReservas);
		
		JMenuItem mntmListaPendiente = new JMenuItem("Nueva Reserva");
		mntmListaPendiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cr = new CtrlABMReserva();
				cr.DialogoReserva();
			}
		});
		mnReservas.add(mntmListaPendiente);
		
		
		JMenuItem mntmRealizarReserva = new JMenuItem("Reservas Pendientes");
		mnReservas.add(mntmRealizarReserva);
		
		JMenu mnAdministrar = new JMenu("Administrar");
		menuBar.add(mnAdministrar);
		
		JMenuItem mntmPersonas = new JMenuItem("Personas");
		mntmPersonas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cp = new CtrlABMPersona();
				cp.DialogoPersonas();
			}
		});
		mnAdministrar.add(mntmPersonas);
		
		JMenuItem mntmTipoElementos = new JMenuItem("Tipo Elementos");
		mntmTipoElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				te = new CtrlABMTipo();
				te.DialogoTipo();
			}
		});
		mnAdministrar.add(mntmTipoElementos);
		
		JMenuItem mntmElementos = new JMenuItem("Elementos");
		mntmElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				el = new CtrlABMElemento();
				el.DialogoElementos();
			}
		});
		mnAdministrar.add(mntmElementos);
		mainFrame = new JPanel();
		mainFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setLayout(new BorderLayout(0, 0));
		setContentPane(mainFrame);
	}

}
