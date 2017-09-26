package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.fabric.xmlrpc.base.Array;

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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AMBCReserva extends JFrame {

	private JPanel contentPane;
	private JTextField txtId_reserva;
	private JTextField txtFechaHora;
	private JTextField txtDescripcion;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JComboBox cboTipos;
	private JComboBox cboElementos;

	private CtrlABMReserva ctrl;
	private CtrlABMElemento ctrlelemento = new CtrlABMElemento();
	private CtrlABMTipo ctrltipo = new CtrlABMTipo();
	private CtrlABMPersona ctrlper = new CtrlABMPersona();

	/**
	 * Launch the application.
	 */
	public void start() {
		ctrl = new CtrlABMReserva();

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTipo = new JLabel("Tipo de Elemento");

		JComboBox cboTipos = new JComboBox();
		cboTipos.setModel(new DefaultComboBoxModel<TipoElemento>(this.loadtipos().toArray(new TipoElemento[0])));
		JComboBox cboElementos = new JComboBox();
		
		cboTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoElemento tipoItemActual = (TipoElemento) cboTipos.getSelectedItem();
				cboElementos.removeAllItems();
				cboElementos.setModel(new DefaultComboBoxModel<Elemento>(
						ctrlelemento.loadElementos(tipoItemActual).toArray(new Elemento[0])));

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

		JButton btnGuardar = new JButton("Guardar Reserva");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarClick();
			}
		});
		btnGuardar.addMouseListener(new MouseAdapter() {

		});

		this.mapearUsuarioLogueado(ctrlper.getLogged());

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblNombre)
								.addComponent(lblApellido).addComponent(lblIdReserva).addComponent(lblTipo)
								.addComponent(lblElemento).addComponent(lblFechahora).addComponent(lblNewLabel))
						.addGap(30)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(txtDescripcion, 98, 98, 98).addGap(173))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(txtFechaHora, GroupLayout.PREFERRED_SIZE, 129,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(142, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(cboTipos, 0, 141, Short.MAX_VALUE).addGap(130))
								.addGroup(gl_contentPane
										.createSequentialGroup()
										.addComponent(txtId_reserva, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(txtApellido, Alignment.LEADING).addComponent(txtNombre,
														Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 112,
														Short.MAX_VALUE))
										.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 122,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(cboElementos, 0, 141, Short.MAX_VALUE))
										.addGap(130)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addGap(23)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNombre).addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblApellido).addComponent(txtApellido, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtId_reserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdReserva))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblTipo).addComponent(
						cboTipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblElemento).addComponent(
						cboElementos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(lblFechahora).addComponent(txtFechaHora,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(btnGuardar).addContainerGap(13, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);

	}

	public void mapearUsuarioLogueado(Persona per) {
		this.txtNombre.setText(per.getNombre());
		this.txtApellido.setText(per.getApellido());
	}

	protected void guardarClick() {
		ctrl.add(this.mapearDeForm());
		// TODO Auto-generated method stub
	}

	public Reserva mapearDeForm() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Reserva res = new Reserva();
		Elemento ele = new Elemento();
		res.setPersona(ctrlper.getLogged());
		// aca hay que pasar el elemento seleccionado en el JCOMBOBOXELEMENTO
		// dateFormat.parse(this.txtFechaHora.getText()); //ver conversion aca
		// res.setFecha_hora(dateFormat);
		res.setDescripcion(this.txtDescripcion.getText());

		return res;
	}

	public List<TipoElemento> loadtipos() { // ESTAMOS TENIENDO ALGUN PROBLEMA CON ESTE METODO
		java.util.List<TipoElemento> tipos = new java.util.ArrayList<TipoElemento>();
		tipos.addAll(ctrltipo.getTipos());
		return tipos;
	}

}
