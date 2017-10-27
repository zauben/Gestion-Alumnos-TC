package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


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

public class AMBCReserva extends JFrame {

	private JPanel contentPane;
	private JTextField txtId_reserva;
	private JTextField txtFechaHora;
	private JTextField txtDescripcion;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JComboBox cboTipos;


	private CtrlABMReserva ctrl = new CtrlABMReserva();
	private CtrlABMElemento ctrlelemento = new CtrlABMElemento();
	private CtrlABMTipo ctrltipo = new CtrlABMTipo();
	private CtrlABMPersona ctrlper = new CtrlABMPersona();
	private ArrayList<Elemento> elementos = new ArrayList(); 
	private JComboBox cboElementos_1;

	/**
	 * Launch the application.
	 */
	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AMBCReserva frame = new AMBCReserva();
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
	public AMBCReserva() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTipo = new JLabel("Tipo de Elemento");

		JComboBox cboTipos = new JComboBox();
		cboTipos.setModel(new DefaultComboBoxModel<TipoElemento>(ctrltipo.loadtipos().toArray(new TipoElemento[0])));
		cboTipos.setSelectedItem(null);
		cboElementos_1 = new JComboBox();

		class CategoryListCellRenderer extends DefaultListCellRenderer {

		    @Override
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

		        if (value instanceof Elemento) {
		            value = ((Elemento)value).getNombre();
		        }

		        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.

		    }

		}
		
		cboElementos_1.setRenderer(new CategoryListCellRenderer());
		
		cboTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoElemento tipoItemActual = (TipoElemento) cboTipos.getSelectedItem();
				elementos = ctrlelemento.getElementos(tipoItemActual);
				ctrl.initDataBindings(elementos, cboElementos_1);
				
				

			}
			


		});

		JLabel lblIdReserva = new JLabel("Id Reserva");

		txtId_reserva = new JTextField();
		txtId_reserva.setEditable(false);
		txtId_reserva.setColumns(10);

		JLabel lblElemento = new JLabel("Elemento");

	

		JLabel lblFechahora = new JLabel("Fecha - Hora");

		txtFechaHora = new JTextField();
		txtFechaHora.setColumns(10);

		JLabel lblNewLabel = new JLabel("Descripcion");

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre ");

		JLabel lblApellido = new JLabel("Apellido");

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarClick((Elemento) cboElementos_1.getSelectedItem());
			}
		});
		btnGuardar.addMouseListener(new MouseAdapter() {

		});

		this.mapearUsuarioLogueado(ctrlper.getLogged());
		
		JLabel lblYyyymmdd = new JLabel("dd/MM/yyyy HH:mm");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTipo)
								.addComponent(lblElemento)
								.addComponent(lblFechahora)
								.addComponent(lblNewLabel)
								.addComponent(lblNombre)
								.addComponent(lblApellido)
								.addComponent(lblIdReserva))
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtId_reserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(txtNombre, Alignment.LEADING)
											.addComponent(txtApellido, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
										.addContainerGap())
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(cboTipos, 0, 222, Short.MAX_VALUE)
												.addGap(130))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(cboElementos_1, 0, 222, Short.MAX_VALUE)
												.addGap(130))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(txtFechaHora, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(lblYyyymmdd)
												.addContainerGap()))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
											.addContainerGap())))))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(174))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellido))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtId_reserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdReserva))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipo)
						.addComponent(cboTipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblElemento)
						.addComponent(cboElementos_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechahora)
						.addComponent(txtFechaHora, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYyyymmdd))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnGuardar)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();

	}

	public void mapearUsuarioLogueado(Persona per) {
		this.txtNombre.setText(per.getNombre());
		this.txtApellido.setText(per.getApellido());
	}

	protected void guardarClick(Elemento ele) {
		ctrl.add(this.mapearDeForm(ele));
		this.dispose();
		// TODO Auto-generated method stub
	}



	public Reserva mapearDeForm(Elemento ele) {
		SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Reserva res = new Reserva();
		res.setDescripcion(this.txtDescripcion.getText());
		res.setPersona(ctrlper.getLogged());
		res.setElemento(ele);
		java.util.Date fechaHora = null;
		try {
			fechaHora = f.parse(this.txtFechaHora.getText());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.setFecha_hora(fechaHora);

		return res;
	}
	protected void initDataBindings() {
		JComboBoxBinding<Elemento,List<Elemento>,JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, elementos, cboElementos_1, "elementosDeUnTipo");
		jComboBinding.bind();
	}
}
