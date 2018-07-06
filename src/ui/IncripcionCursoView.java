package ui;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controlers.CursoController;
import controlers.PersonaController;
import controlers.InscripcionCotroller;
import entity.Alumno;
import entity.Carrera;
import entity.InscripcionCurso;
import entity.Curso;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class IncripcionCursoView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private InscripcionCotroller ctrl;
	private CursoController ctrlCurso;
	private PersonaController ctrlper;
	private JComboBox cboCursos;
	private JTextField textFieldLEG;
	private Alumno currentAlumno;
	private ArrayList<Curso> cursos;
	private JTextField textFieldCarrera;

	/**
	 * Launch the application.
	 */
	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IncripcionCursoView frame = new IncripcionCursoView();
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
	public IncripcionCursoView() {
		ctrl = new InscripcionCotroller();
		ctrlCurso = new CursoController();
		ctrlper = new PersonaController();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 397, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTipo = new JLabel("Carrera");
		cboCursos = new JComboBox();

		class CategoryListCellRenderer extends DefaultListCellRenderer {

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				if (value instanceof Carrera) {
					value = ((Carrera) value).getNombre();
				}

				return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); // To change
																											// body of
																											// generated
																											// methods,
																											// choose
																											// Tools |
																											// Templates.

			}

		}

		cboCursos.setRenderer(new CategoryListCellRenderer());

		JLabel lblElemento = new JLabel("Curso");

		JLabel lblNombre = new JLabel("Nombre ");

		JLabel lblApellido = new JLabel("Apellido");

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);

		JButton btnGuardar = new JButton("Inscribir!");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarClick((Curso) cboCursos.getSelectedItem());
			}
		});
		btnGuardar.addMouseListener(new MouseAdapter() {

		});

		JLabel lblLegajo = new JLabel("Legajo");

		textFieldLEG = new JTextField();
		textFieldLEG.setColumns(10);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {

			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent arg0) {
				Alumno al = new Alumno();
				al.setLegajo(Integer.valueOf(textFieldLEG.getText()));
				currentAlumno = ctrlper.getByLegajo(al);
				mapearUsuario(currentAlumno);
				cursos = ctrlCurso.getCursos(currentAlumno.getCarrera().getIdentificador());
				initDataBindings();
			}
		});

		textFieldCarrera = new JTextField();
		textFieldCarrera.setEditable(false);
		textFieldCarrera.setColumns(10);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addGroup(
						gl_contentPane
								.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createParallelGroup(
										Alignment.LEADING).addGroup(
												gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
														.createParallelGroup(Alignment.LEADING).addComponent(lblTipo)
														.addComponent(lblElemento)).addGap(33).addGroup(
																gl_contentPane.createParallelGroup(Alignment.LEADING,
																		false).addComponent(textFieldCarrera)
																		.addComponent(cboCursos, 0, 224,
																				Short.MAX_VALUE))
														.addContainerGap())
										.addGroup(gl_contentPane.createSequentialGroup().addGroup(gl_contentPane
												.createParallelGroup(Alignment.TRAILING, false)
												.addGroup(
														Alignment.LEADING,
														gl_contentPane.createSequentialGroup()
																.addComponent(lblLegajo, GroupLayout.PREFERRED_SIZE, 40,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(30).addComponent(textFieldLEG))
												.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNombre).addComponent(lblApellido))
														.addGap(30)
														.addGroup(gl_contentPane
																.createParallelGroup(Alignment.LEADING, false)
																.addComponent(txtNombre).addComponent(txtApellido,
																		GroupLayout.DEFAULT_SIZE, 124,
																		Short.MAX_VALUE))))
												.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
												.addComponent(btnBuscar).addGap(67)))
								.addGroup(Alignment.TRAILING,
										gl_contentPane
												.createSequentialGroup().addComponent(btnGuardar,
														GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
												.addGap(129)))));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(37)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblLegajo)
								.addComponent(textFieldLEG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscar))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombre))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblApellido))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblTipo)
								.addComponent(textFieldCarrera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblElemento)
								.addComponent(cboCursos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(26).addComponent(btnGuardar).addContainerGap(40, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
		initDataBindings();

	}

	public void mapearUsuario(Alumno per) {
		this.txtNombre.setText(per.getNombre());
		this.txtApellido.setText(per.getApellido());
		if (per.getCarrera().getNombre() != null && !per.getCarrera().getNombre().equals("")) {
			this.textFieldCarrera.setText(per.getCarrera().getNombre());
		} else {
			JOptionPane.showMessageDialog(null, "El alumno no esta inscripto a ninguna carrera", "Error",
					JOptionPane.WARNING_MESSAGE);
			this.dispose();
		}
	}



	protected void guardarClick(Curso cursoElegido) {
		if (ctrl.add(this.mapearDeForm(cursoElegido))) {
			JOptionPane.showMessageDialog(null, "Inscripcion realizada correctamente", "Exito",
					JOptionPane.PLAIN_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "El alumno ya se encuentra inscripto al curso este año", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}
		this.dispose();
	}

	public InscripcionCurso mapearDeForm(Curso curso) {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		InscripcionCurso res = new InscripcionCurso();
		res.setAlumno(currentAlumno);
		res.setCurso(curso);
		return res;
	}

	protected void initDataBindings() {
		JComboBoxBinding<Curso, List<Curso>, JComboBox> jComboBinding = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ_WRITE, cursos, cboCursos);
		jComboBinding.bind();
		cboCursos.setSelectedIndex(-1);
	}
}
