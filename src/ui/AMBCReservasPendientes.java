package ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import controlers.CtrlABMElemento;
import controlers.CtrlABMPersona;
import controlers.CtrlABMReserva;

import entity.Persona;
import entity.Reserva;


import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.util.ArrayList;
import java.util.List;

import org.jdesktop.swingbinding.SwingBindings;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import javax.swing.JTable;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.beansbinding.BeanProperty;
import java.awt.Panel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AMBCReservasPendientes extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;

	private CtrlABMReserva ctrl = new CtrlABMReserva();
	private CtrlABMElemento ctrlelemento = new CtrlABMElemento();
	private ArrayList<Reserva> reservas = new ArrayList();
	private JFrame frame = new JFrame();
	/**
	 * Launch the application.
	 */
	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					AMBCReservasPendientes frame = new AMBCReservasPendientes();
					frame.setVisible(true);
									} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
		});
	}

	private ArrayList<Persona> pers;
	CtrlABMPersona ctrlPer= new CtrlABMPersona();
	
	private JTable table;

	/**
	 * Create the frame.
	 */
	public AMBCReservasPendientes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 507, 329);
		
		JScrollPane scrollPane = new JScrollPane();
		

	
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(368, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Panel panel = new Panel();
		scrollPane.setColumnHeaderView(panel);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAcciones = new JMenu("Acciones");
		menuBar.add(mnAcciones);
		
		JMenuItem mntmCancelarReservas = new JMenuItem("Cancelar Reservas");
		mntmCancelarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			ctrl.cancelarReservas(ctrlPer.getLogged());
			JOptionPane.showMessageDialog(frame, "Las reservas se han cancelado correctamente.");
			dispose();
			}
		});
		mnAcciones.add(mntmCancelarReservas);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnAcciones.add(mntmSalir);

		
		try{
			reservas = ctrl.reservasDePer(ctrlPer.getLogged());
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		initDataBindings();}


	private void mapearUser(Persona logged) {
		this.txtNombre.setText(logged.getNombre());
		this.txtApellido.setText(logged.getApellido());
		
	}

	public void mapearUsuarioLogueado(Persona per) {
		this.txtNombre.setText(per.getNombre());
		this.txtApellido.setText(per.getApellido());
	}

	protected void initDataBindings() {
		JTableBinding<Reserva, List<Reserva>, JTable> jTableBinding = SwingBindings
				.createJTableBinding(UpdateStrategy.READ, reservas, table);
		//
		BeanProperty<Reserva, Integer> reservaBeanProperty = BeanProperty.create("id_reserva");
		jTableBinding.addColumnBinding(reservaBeanProperty).setColumnName("ID Reserva").setEditable(false);
		//
		BeanProperty<Reserva, java.util.Date> reservaBeanProperty_1 = BeanProperty.create("fecha_hora");
		jTableBinding.addColumnBinding(reservaBeanProperty_1).setColumnName("Fecha / Hora").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_2 = BeanProperty.create("descripcion");
		jTableBinding.addColumnBinding(reservaBeanProperty_2).setColumnName("Descripcion").setEditable(false);
		//
		BeanProperty<Reserva, String> reservaBeanProperty_3 = BeanProperty.create("elemento.nombre");
		jTableBinding.addColumnBinding(reservaBeanProperty_3).setColumnName("Elemento").setEditable(false);
		//
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}
	
}



