package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import controlers.CtrlABMElemento;
import controlers.CtrlABMPersona;
import controlers.CtrlABMInscripcion;
import controlers.CtrlABMTipo;
import entity.Alumno;
import entity.Carrera;
import entity.Persona;
import entity.InscripcionCurso;
import entity.Curso;

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

public class AMBCIncripcionCurso extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JComboBox cboTipos;


	private CtrlABMInscripcion ctrl = new CtrlABMInscripcion();
	private CtrlABMElemento ctrlCurso = new CtrlABMElemento();
	private CtrlABMTipo ctrltipo = new CtrlABMTipo();
	private CtrlABMPersona ctrlper = new CtrlABMPersona();
	
	private JComboBox cboCursos;
	private JTextField textFieldLEG;
	private Alumno currentAlumno;
	private ArrayList<Curso> cursos= new ArrayList(); 
	private JTextField textFieldCarrera;

	/**
	 * Launch the application.
	 */
	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AMBCIncripcionCurso frame = new AMBCIncripcionCurso();
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
	public AMBCIncripcionCurso() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblTipo = new JLabel("Carrera");
		cboCursos = new JComboBox();

		class CategoryListCellRenderer extends DefaultListCellRenderer {

		    @Override
		    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

		        if (value instanceof Carrera) {
		            value = ((Carrera)value).getNombre();
		        }

		        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus); //To change body of generated methods, choose Tools | Templates.

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
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipo)
						.addComponent(lblElemento))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(cboCursos, 0, 262, Short.MAX_VALUE)
						.addComponent(textFieldCarrera, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
					.addGap(132))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(154)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(215, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(lblApellido))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(txtNombre, Alignment.LEADING)
						.addComponent(txtApellido, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(270, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLegajo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(textFieldLEG, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnBuscar)
					.addContainerGap(182, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLegajo)
						.addComponent(textFieldLEG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApellido))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTipo)
						.addComponent(textFieldCarrera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblElemento)
						.addComponent(cboCursos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addComponent(btnGuardar)
					.addContainerGap(91, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		initDataBindings();


	}

	public void mapearUsuario(Alumno per) {
		this.txtNombre.setText(per.getNombre());
		this.txtApellido.setText(per.getApellido());
		this.textFieldCarrera.setText(per.getCarrera().getNombre());
	}

	protected void guardarClick(Curso cursoElegido) {
		ctrl.add(this.mapearDeForm(cursoElegido));
		this.dispose();
		// TODO Auto-generated method stub
	}



	public InscripcionCurso mapearDeForm(Curso curso) {
		SimpleDateFormat f= new SimpleDateFormat("dd/MM/yyyy HH:mm");
		InscripcionCurso res = new InscripcionCurso();
		res.setAlumno(currentAlumno);
		res.setCurso(curso);

		return res;
	}

	protected void initDataBindings() {
		JComboBoxBinding<Curso,List<Curso>,JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, cursos, cboCursos);
		jComboBinding.bind();
		cboCursos.setSelectedIndex(-1);
	}
}
