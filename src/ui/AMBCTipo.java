package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlers.CtrlABMTipo;
import entity.TipoElemento;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AMBCTipo extends JFrame {

	private CtrlABMTipo ctrl=new CtrlABMTipo();
	public TipoElemento te;
	

	
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtCantidadMax;
	private JTextField txtTiempoMax;
	private JTextField txtDias;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;

	
	
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AMBCTipo frame = new AMBCTipo();
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
	public AMBCTipo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblId = new JLabel("Id");
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad Maxima");
		
		txtCantidadMax = new JTextField();
		txtCantidadMax.setColumns(10);
		
		JLabel lblTiempo = new JLabel("Tiempo Maximo");
		
		JLabel lblDiasAnticipacion = new JLabel("Dias Anticipacion");
		
		txtTiempoMax = new JTextField();
		txtTiempoMax.setColumns(10);
		
		txtDias = new JTextField();
		txtDias.setColumns(10);
		
		JButton btnBuscarPorNombre = new JButton("Buscar por Nombre");
		btnBuscarPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscarPorNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClick();
			}
		});
		
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				agregarClick();
			}
		});
		
		btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificarClick();
			}
		});
		
		btnEliminar = new JButton("Borrar Registro");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				borrarClick();
			}
		});
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanscreen();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombre)
								.addComponent(lblId))
							.addGap(7)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnLimpiar))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnBuscarPorNombre))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTiempo)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblDiasAnticipacion)
											.addComponent(lblCantidad)))
									.addGap(18))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(btnAgregar)
									.addGap(9)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(btnModificar)
									.addGap(18)
									.addComponent(btnEliminar))
								.addComponent(txtDias, Alignment.LEADING, 177, 177, 177)
								.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtTiempoMax)
									.addComponent(txtCantidadMax, 175, 175, Short.MAX_VALUE)))))
					.addGap(34))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLimpiar))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscarPorNombre))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCantidad)
						.addComponent(txtCantidadMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTiempo)
						.addComponent(txtTiempoMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDiasAnticipacion))
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(btnModificar)
						.addComponent(btnEliminar))
					.addGap(22))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	

	protected void buscarClick() {
		this.mapearAForm(ctrl.getByNombre(this.txtNombre.getText()));
		
	}
	
	protected void agregarClick(){
		ctrl.add(this.mapearDeForm());
		cleanscreen();
	}
	
	

	protected void borrarClick(){
		ctrl.delete(this.mapearDeForm());
		cleanscreen();
	}
	
	protected void modificarClick(){
		ctrl.update(this.mapearDeForm());
		cleanscreen();
	}

	private void mapearAForm(TipoElemento te){
		if (te!=null) {
		this.txtId.setText(String.valueOf(te.getId_tipo()));
		this.txtNombre.setText(te.getNombre());
		this.txtCantidadMax.setText(String.valueOf(te.getCantMaxima()));
		this.txtTiempoMax.setText(String.valueOf(te.getTiempoMax()));
		this.txtDias.setText(String.valueOf(te.getDiasAnticipacion()));}
		else {
			JOptionPane.showMessageDialog(contentPane, "No se ha encontrado un tipo de elemento para el nombre ingresado.");
			this.cleanscreen();
			}
		
	}
	
	private TipoElemento mapearDeForm(){
		te= new TipoElemento();
		if(!this.txtId.getText().isEmpty()){
			te.setId_tipo(Integer.parseInt(this.txtId.getText()));
		}
		te.setNombre(this.txtNombre.getText());
		te.setCantMaxima(Integer.parseInt(this.txtCantidadMax.getText()));
		te.setTiempoMax(Integer.parseInt(this.txtTiempoMax.getText()));
		te.setDiasAnticipacion(Integer.parseInt(this.txtDias.getText()));
		
		return te;
	}
	/*	private TipoElemento getbyNombre() {
			te= new TipoElemento();
			te.setNombre(this.txtNombre.getText());
			return te;
			
		}*/
	
	
	private void cleanscreen() {
		this.txtId.setText("");
		this.txtCantidadMax.setText("");
		this.txtTiempoMax.setText("");
		this.txtNombre.setText("");
		this.txtDias.setText("");
	}
}
