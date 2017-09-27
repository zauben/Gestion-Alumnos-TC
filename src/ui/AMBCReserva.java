package ui;

import java.awt.BorderLayout;
import java.awt.Component;
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
		cboTipos.setModel(new DefaultComboBoxModel<TipoElemento>(ctrltipo.loadtipos().toArray(new TipoElemento[0])));
		cboTipos.setSelectedItem(null);;
		JComboBox cboElementos = new JComboBox();

		class CategoryListCellRenderer extends DefaultListCellRenderer {

		    @Override
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

		        if (value instanceof Elemento) {
		            value = ((Elemento)value).getNombre();
		        }

		        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.

		    }

		}
		
		cboElementos.setRenderer(new CategoryListCellRenderer());
		
		cboTipos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TipoElemento tipoItemActual = (TipoElemento) cboTipos.getSelectedItem();
				cboElementos.removeAllItems();
				cboElementos.setModel(loadele(tipoItemActual));

			}
			
			public DefaultComboBoxModel loadele(TipoElemento tipoItemActual) {
				DefaultComboBoxModel elem = new DefaultComboBoxModel();
				for (int i = 0; i < ctrlelemento.loadElementos(tipoItemActual).size(); i++) {
					elem.addElement(ctrlelemento.loadElementos(tipoItemActual).get(i));}
				return elem;}

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
				guardarClick(cboElementos.getSelectedIndex());
			}
		});
		btnGuardar.addMouseListener(new MouseAdapter() {

		});

		this.mapearUsuarioLogueado(ctrlper.getLogged());
		
		JLabel lblYyyymmdd = new JLabel("yyyy-mm-dd");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(lblApellido)
						.addComponent(lblIdReserva)
						.addComponent(lblTipo)
						.addComponent(lblElemento)
						.addComponent(lblFechahora)
						.addComponent(lblNewLabel))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtId_reserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(cboTipos, 0, 141, Short.MAX_VALUE)
							.addGap(130))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.addComponent(cboElementos, 0, 141, Short.MAX_VALUE))
							.addGap(130))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtFechaHora, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblYyyymmdd)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtNombre, Alignment.LEADING)
								.addComponent(txtApellido, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addGap(84))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdReserva)
						.addComponent(txtId_reserva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipo)
						.addComponent(cboTipos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblElemento)
						.addComponent(cboElementos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);

	}

	public void mapearUsuarioLogueado(Persona per) {
		this.txtNombre.setText(per.getNombre());
		this.txtApellido.setText(per.getApellido());
	}

	protected void guardarClick(int i) {
		ctrl.add(this.mapearDeForm(i));
		// TODO Auto-generated method stub
	}

	public Reserva mapearDeForm(int index) {
		DateFormat dateFormat = new SimpleDateFormat(txtFechaHora.getText());
		Reserva res = new Reserva();
		res.setDescripcion(this.txtDescripcion.getText());
		res.setPersona(ctrlper.getLogged());
		res.setElemento((Elemento) cboElementos.getModel().getSelectedItem());//arreglar esta linea
		res.setFecha_hora(dateFormat);

		return res;
	}
}
