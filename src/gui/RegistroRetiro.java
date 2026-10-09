package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloRetiros;
import clase.Retiro;

public class RegistroRetiro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumRetiro;
	private JTextField txtNumMatricula;
	private JTextField txtFecha;
	private JTextField txtHora;
	private JTable table;
	private JTextField txtFiltro;
	private DefaultTableModel modeloTabla;

	private ArregloRetiros arregloRetiros = new ArregloRetiros();

	public RegistroRetiro() {
		setTitle("Registro de Retiros - Colegio América del Callao High School");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 621);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 829, 30);
		contentPane.add(toolBar);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		toolBar.add(btnNuevo);

		JButton btnGuardar = new JButton("Guardar");
		toolBar.add(btnGuardar);

		JButton btnActualizar = new JButton("Actualizar");
		toolBar.add(btnActualizar);

		Component horizontalGlue = Box.createHorizontalGlue();
		toolBar.add(horizontalGlue);

		JLabel lblCiclo = new JLabel("Ciclo Académico: 2026-I   ");
		lblCiclo.setForeground(new Color(0, 0, 255));
		toolBar.add(lblCiclo);

		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new TitledBorder(null, "Datos del Retiro", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 128)));
		panelDatos.setBounds(10, 40, 809, 173);
		contentPane.add(panelDatos);
		panelDatos.setLayout(null);

		JLabel lblNumRetiro = new JLabel("N° Retiro:");
		lblNumRetiro.setBounds(30, 30, 80, 20);
		panelDatos.add(lblNumRetiro);

		txtNumRetiro = new JTextField();
		txtNumRetiro.setBounds(110, 30, 150, 20);
		panelDatos.add(txtNumRetiro);

		JLabel lblNumMatricula = new JLabel("N° Matrícula:");
		lblNumMatricula.setBounds(420, 30, 80, 20);
		panelDatos.add(lblNumMatricula);

		txtNumMatricula = new JTextField();
		txtNumMatricula.setBounds(510, 30, 180, 20);
		panelDatos.add(txtNumMatricula);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(30, 70, 80, 20);
		panelDatos.add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setBounds(110, 70, 150, 20);
		panelDatos.add(txtFecha);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setBounds(420, 70, 80, 20);
		panelDatos.add(lblHora);

		txtHora = new JTextField();
		txtHora.setBounds(510, 70, 180, 20);
		panelDatos.add(txtHora);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarRetiro();
			}
		});
		btnAdicionar.setBounds(160, 130, 110, 23);
		panelDatos.add(btnAdicionar);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarRetiro();
			}
		});
		btnModificar.setBounds(280, 130, 105, 23);
		panelDatos.add(btnModificar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarRetiro();
			}
		});
		btnEliminar.setBounds(395, 130, 100, 23);
		panelDatos.add(btnEliminar);

		JButton btnLimpiar = new JButton("Limpiar/Nuevo");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiar.setBounds(505, 130, 140, 23);
		panelDatos.add(btnLimpiar);

		JPanel panelTabla = new JPanel();
		panelTabla.setBorder(new TitledBorder(null, "Listado Maestro de Retiros", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 128)));
		panelTabla.setBounds(10, 220, 809, 290);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);

		Label labelFiltrar = new Label("Filtrar:");
		labelFiltrar.setBounds(20, 20, 45, 22);
		panelTabla.add(labelFiltrar);

		txtFiltro = new JTextField();
		txtFiltro.setBounds(70, 20, 180, 20);
		panelTabla.add(txtFiltro);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarRetiro();
			}
		});
		btnBuscar.setBounds(260, 19, 100, 23);
		panelTabla.add(btnBuscar);

		JButton btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarTabla();
			}
		});
		btnListarTodos.setBounds(540, 19, 125, 23);
		panelTabla.add(btnListarTodos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 57, 765, 200);
		panelTabla.add(scrollPane);

		table = new JTable();
		modeloTabla = new DefaultTableModel(new Object[][] {},
				new String[] { "Num. Retiro", "Num. Matrícula", "Fecha", "Hora" });
		table.setModel(modeloTabla);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = table.getSelectedRow();
				if (fila != -1) {
					txtNumRetiro.setText(table.getValueAt(fila, 0).toString());
					txtNumMatricula.setText(table.getValueAt(fila, 1).toString());
					txtFecha.setText(table.getValueAt(fila, 2).toString());
					txtHora.setText(table.getValueAt(fila, 3).toString());
				}
			}
		});

		scrollPane.setViewportView(table);

		JLabel lblInfo = new JLabel("* Haga clic en cualquier fila para cargar los datos en el formulario.");
		lblInfo.setForeground(Color.GRAY);
		lblInfo.setBounds(20, 265, 380, 14);
		panelTabla.add(lblInfo);
	}

	private void adicionarRetiro() {
		String numRetiro = txtNumRetiro.getText().trim();
		String numMatricula = txtNumMatricula.getText().trim();
		String fecha = txtFecha.getText().trim();
		String hora = txtHora.getText().trim();

		if (numRetiro.isEmpty() || numMatricula.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Complete el N° de Retiro y N° de Matrícula.", "Error de Formato",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (arregloRetiros.buscar(numRetiro) != null) {
			JOptionPane.showMessageDialog(this, "El N° de Retiro ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Retiro nuevo = new Retiro(numRetiro, numMatricula, fecha, hora);
		arregloRetiros.adicionar(nuevo);
		listarTabla();
		limpiarCampos();
		JOptionPane.showMessageDialog(this, "Retiro adicionado correctamente.");
	}

	private void modificarRetiro() {
		String numRetiro = txtNumRetiro.getText().trim();
		Retiro buscado = arregloRetiros.buscar(numRetiro);

		if (buscado != null) {
			buscado.setNumMatricula(txtNumMatricula.getText().trim());
			buscado.setFecha(txtFecha.getText().trim());
			buscado.setHora(txtHora.getText().trim());
			listarTabla();
			JOptionPane.showMessageDialog(this, "Retiro modificado con éxito.");
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró el N° de Retiro para modificar.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void eliminarRetiro() {
		String numRetiro = txtNumRetiro.getText().trim();
		Retiro buscado = arregloRetiros.buscar(numRetiro);

		if (buscado != null) {
			arregloRetiros.eliminar(buscado);
			listarTabla();
			limpiarCampos();
			JOptionPane.showMessageDialog(this, "Retiro eliminado.");
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró el N° de Retiro a eliminar.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void buscarRetiro() {
		String numRetiro = txtFiltro.getText().trim();
		Retiro buscado = arregloRetiros.buscar(numRetiro);

		if (buscado != null) {
			txtNumRetiro.setText(buscado.getNumRetiro());
			txtNumMatricula.setText(buscado.getNumMatricula());
			txtFecha.setText(buscado.getFecha());
			txtHora.setText(buscado.getHora());
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró ningún retiro con ese número.", "Sin resultados",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void listarTabla() {
		modeloTabla.setRowCount(0);
		for (int i = 0; i < arregloRetiros.tamaño(); i++) {
			Retiro r = arregloRetiros.obtener(i);
			Object[] fila = { r.getNumRetiro(), r.getNumMatricula(), r.getFecha(), r.getHora() };
			modeloTabla.addRow(fila);
		}
	}

	private void limpiarCampos() {
		txtNumRetiro.setText("");
		txtNumMatricula.setText("");
		txtFecha.setText("");
		txtHora.setText("");
		txtNumRetiro.requestFocus();
	}
}