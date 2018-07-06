package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlers.CtrlABMElemento;
import controlers.CtrlABMTipo;
import entity.Elemento;
import entity.TipoElemento;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class AMBCElemento extends JFrame {

	private Elemento elemento;
	private CtrlABMElemento ctrl=new CtrlABMElemento();
	private CtrlABMTipo ctrltipo = new CtrlABMTipo();

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNombre;
	private JComboBox cboTipo;
	private ArrayList<TipoElemento> tipoElementos = new ArrayList(); 
	
	

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AMBCElemento frame = new AMBCElemento();
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
	public AMBCElemento() {
		setBounds(100, 100, 424, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblid = new JLabel("ID");
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarClick((TipoElemento) cboTipo.getSelectedItem());
			}
		});
		btnAgregar.addMouseListener(new MouseAdapter() {
			
		});
		
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificarClick((TipoElemento) cboTipo.getSelectedItem());
			}
		});
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				borrarClick((TipoElemento) cboTipo.getSelectedItem());
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buscarClick();
			}
		});
		
		JLabel lblTipo = new JLabel("Tipo de Elemento");
		
		cboTipo = new JComboBox();
		tipoElementos = ctrltipo.getTipos();
		initDataBindings(tipoElementos, cboTipo);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblid)
										.addComponent(lblTipo))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
												.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
													.addGap(10)
													.addComponent(cboTipo, 0, 170, Short.MAX_VALUE)))
											.addGap(18)
											.addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnAgregar)
									.addGap(40)
									.addComponent(btnModificar)
									.addGap(36)
									.addComponent(btnBorrar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)))
							.addGap(39))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNombre)
							.addContainerGap(351, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblid)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(49)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipo)
								.addComponent(cboTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAgregar)
						.addComponent(btnModificar)
						.addComponent(btnBorrar))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
				
	}

	

	protected void borrarClick(TipoElemento tipoEle) {
		ctrl.delete(this.mapearDeForm(tipoEle));
		this.clearScreen();
	}

	protected void modificarClick(TipoElemento tipoEle) {
		ctrl.update(this.mapearDeForm(tipoEle));
	}

	protected void agregarClick(TipoElemento tipoEle) {
		ctrl.add(this.mapearDeForm(tipoEle));
		this.clearScreen();
		
	}

	private void clearScreen() {
		this.txtNombre.setText("");
		this.cboTipo.setSelectedIndex(-1);
		
		
	}

	protected void buscarClick() {
		elemento = new Elemento();
		elemento = ctrl.getByNombre(elemento);
		this.mapearAForm(elemento);
		
	}
	

	private void mapearAForm(Elemento e){
 		this.txtNombre.setText(e.getNombre());
 		this.txtId.setText(String.valueOf(e.getId_elemento()));
 		if (e.getTipoElemento()!=null){
 		this.cboTipo.setSelectedItem(e.getTipoElemento());
			};
	}
	
	
	private Elemento mapearDeForm(TipoElemento tipoEle){
		Elemento e=new Elemento();
		e.setNombre(this.txtNombre.getText());
		e.setTipoElemento(tipoEle);
		return e;
	}
	
	public void showElemento(Elemento e){
		this.mapearAForm(e);
	}
	

	protected void initDataBindings(ArrayList<TipoElemento> tipoElementos, JComboBox cboTipo) {
		JComboBoxBinding<TipoElemento, List<TipoElemento>, JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, tipoElementos, cboTipo, "tipoElementos");
		jComboBinding.bind();
	}	
}
