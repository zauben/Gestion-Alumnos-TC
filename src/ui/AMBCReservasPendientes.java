package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import controlers.CtrlABMElemento;
import controlers.CtrlABMPersona;
import controlers.CtrlABMReserva;
import controlers.CtrlABMTipo;
import entity.Elemento;
import entity.Persona;
import entity.Reserva;
import entity.TipoElemento;


import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import javax.swing.JTable;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.beansbinding.ObjectProperty;
import org.jdesktop.beansbinding.BeanProperty;

public class AMBCReservasPendientes extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;

	private CtrlABMReserva ctrl = new CtrlABMReserva();
	private CtrlABMElemento ctrlelemento = new CtrlABMElemento();
	private CtrlABMPersona ctrlper = new CtrlABMPersona();
	private JTable table;
	private ArrayList<Reserva> reservas = new ArrayList();

	/**
	 * Launch the application.
	 */
	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reservas.addAll(ctrl.reservasDePer(ctrlper.getLogged()));
					AMBCReservasPendientes frame = new AMBCReservasPendientes();
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
	@SuppressWarnings("unchecked")
	public AMBCReservasPendientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblNombre = new JLabel("Nombre ");

		JLabel lblApellido = new JLabel("Apellido");

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);

		this.mapearUsuarioLogueado(ctrlper.getLogged());

		

		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
	

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(lblApellido))
					.addGap(69)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtNombre, 126, 126, 126)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(229, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(15)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellido))
					.addGap(15)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();
		this.mapearUser(ctrlper.getLogged());

	}

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
				.createJTableBinding(UpdateStrategy.READ_WRITE, reservas, table, "Tabla");
		//
		BeanProperty<Reserva, Integer> reservaBeanProperty = BeanProperty.create("id_reserva");
		jTableBinding.addColumnBinding(reservaBeanProperty).setColumnName("ID Reserva");
		//
		BeanProperty<Reserva, java.util.Date> reservaBeanProperty_1 = BeanProperty.create("fecha_hora");
		jTableBinding.addColumnBinding(reservaBeanProperty_1).setColumnName("Fecha / Hora");
		//
		BeanProperty<Reserva, String> reservaBeanProperty_2 = BeanProperty.create("descripcion");
		jTableBinding.addColumnBinding(reservaBeanProperty_2).setColumnName("Descripcion");
		//
		BeanProperty<Reserva, String> reservaBeanProperty_3 = BeanProperty.create("elemento.nombre");
		jTableBinding.addColumnBinding(reservaBeanProperty_3).setColumnName("Elemento");
		//
		jTableBinding.bind();
	}
}
