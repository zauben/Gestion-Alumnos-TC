package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlers.PersonaController;
import entity.Alumno;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionAlumnoView extends JFrame {

	private PersonaController ctrl = new PersonaController();

	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtLegajo;
	public Alumno currentPersona = new Alumno();
	private JTextField txtNacimientoField;
	private JTextField textFieldTipo;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionAlumnoView frame = new GestionAlumnoView();
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
	@SuppressWarnings("rawtypes")
	public GestionAlumnoView() {
		setTitle("Editar Alumnos");
		setBounds(100, 100, 479, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblDni = new JLabel("Documento");

		txtDni = new JTextField();
		txtDni.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");

		txtNombre = new JTextField();
		txtNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido");

		txtApellido = new JTextField();
		txtApellido.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buscarClick();
			}
		});

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAgregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				agregarClick();
			}
		});

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				borrarClick();
			}
		});

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificarClick();
			}
		});

		txtLegajo = new JTextField();
		txtLegajo.setColumns(10);

		JLabel lblID_persona = new JLabel("Legajo");

		JLabel lblNacimiento = new JLabel("Fecha Nacimiento");

		txtNacimientoField = new JTextField();

		JLabel lblTipoDocumento = new JLabel("Tipo Doc.");

		textFieldTipo = new JTextField();
		textFieldTipo.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(27)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblTipoDocumento, GroupLayout.PREFERRED_SIZE, 70,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(textFieldTipo, GroupLayout.PREFERRED_SIZE, 126,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNombre).addComponent(lblDni)
												.addComponent(lblApellido, GroupLayout.PREFERRED_SIZE, 71,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNacimiento))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(txtNacimientoField, GroupLayout.DEFAULT_SIZE, 264,
														Short.MAX_VALUE)
												.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, 126,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(txtApellido, 201, 264, Short.MAX_VALUE).addComponent(
														txtNombre, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
								.addGroup(gl_contentPane
										.createSequentialGroup().addGap(44).addComponent(btnAgregar).addGap(18)
										.addComponent(btnBorrar).addGap(18).addComponent(btnModificar)
										.addPreferredGap(ComponentPlacement.RELATED, 63, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblID_persona, GroupLayout.PREFERRED_SIZE, 55,
												GroupLayout.PREFERRED_SIZE)
										.addGap(33)
										.addComponent(txtLegajo, GroupLayout.PREFERRED_SIZE, 126,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnBuscar)))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(28)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblID_persona)
								.addComponent(txtLegajo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscar))
						.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(
										textFieldTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane
										.createSequentialGroup().addGap(3).addComponent(lblTipoDocumento)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblDni)
								.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNombre)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(12)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblApellido)
								.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNacimientoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNacimiento))
						.addGap(36).addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAgregar).addComponent(btnBorrar).addComponent(btnModificar))
						.addGap(39)));
		contentPane.setLayout(gl_contentPane);
	}

	protected void buscarClick() {
		currentPersona.setLegajo(Integer.valueOf(this.txtLegajo.getText()));
		Alumno al = ctrl.getByLegajo(currentPersona);
		if (al != null) {
			this.mapearAForm(al);
		} else {
			JOptionPane.showMessageDialog(null, "No se encontro ningun alumno con el legajo ingresado", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	protected void agregarClick() {
		
		if (ctrl.add(this.mapearDeForm())) {
			JOptionPane.showMessageDialog(null, "Alumno agregado exitosamente", "Exito",
					JOptionPane.PLAIN_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Ya existe un alumno con el legajo ingresado", "Error",
					JOptionPane.WARNING_MESSAGE);
		}
		cleanscreen();
	}

	protected void borrarClick() {
		if(ctrl.delete(currentPersona)) {
			JOptionPane.showMessageDialog(null, "Alumno borrado exitosamente", "Exito",
					JOptionPane.PLAIN_MESSAGE);
			}else {
		JOptionPane.showMessageDialog(null, "No existe un alumno con el legajo ingresado", "Error",
				JOptionPane.WARNING_MESSAGE);
			}	
		cleanscreen();
	}

	protected void modificarClick() {
		if(ctrl.update(this.mapearDeForm())) {
			JOptionPane.showMessageDialog(null, "Alumno Modificado exitosamente", "Exito",
					JOptionPane.PLAIN_MESSAGE);
	
		
			}else {
		JOptionPane.showMessageDialog(null, "No existe un alumno con el legajo ingresado", "Error",
				JOptionPane.WARNING_MESSAGE);
	
			}	
		cleanscreen();
		}

	private void mapearAForm(Alumno p) {
		this.currentPersona = p;
		this.txtDni.setText(p.getDni());
		this.txtNombre.setText(p.getNombre());
		this.txtApellido.setText(p.getApellido());
		this.txtLegajo.setText(p.getLegajo().toString());
		this.txtNacimientoField.setText(p.getFechaNac().toString());
		this.textFieldTipo.setText(p.getTipodoc());
	}

	private Alumno mapearDeForm() {
		if (valid()) {
			currentPersona.setLegajo(Integer.valueOf(this.txtLegajo.getText()));
			currentPersona.setDni(this.txtDni.getText());
			currentPersona.setTipodoc(this.textFieldTipo.getText());
			currentPersona.setNombre(this.txtNombre.getText());
			currentPersona.setApellido(this.txtApellido.getText());
			currentPersona.setFechaNac(Date.valueOf(this.txtNacimientoField.getText()));
			return currentPersona;
		}
		JOptionPane.showMessageDialog(null, "Hay campos sin completar", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
		return null;
	}

	private boolean valid() {
		if (txtApellido.getText().isEmpty() || txtDni.getText().isEmpty() || txtLegajo.getText().isEmpty()
				|| txtNacimientoField.getText().isEmpty() || txtNombre.getText().isEmpty()
				|| textFieldTipo.getText().isEmpty()) {
			return false;
		}
		return true;

	}

	public void showAlumno(Alumno p) {
		this.mapearAForm(p);
	}

	private void cleanscreen() {
		this.txtApellido.setText("");
		this.txtNombre.setText("");
		this.txtDni.setText("");
		this.txtLegajo.setText("");
		this.txtNacimientoField.setText("");
		this.textFieldTipo.setText("");
	}
}
