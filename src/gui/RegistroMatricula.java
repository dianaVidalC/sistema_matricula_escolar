package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import arreglo.ArregloAlumnos;
import arreglo.ArregloCursos;
import arreglo.ArregloMatriculas;
import clase.Alumno;
import clase.Curso;
import clase.Matricula;
import util.EstiloUI;

public class RegistroMatricula extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane, pnlDatosMatricula, pnlDatosAlumno, pnlDatosCurso, pnlDetalleCurso, pnlTabla, pnlAcciones;
	private JLabel lblNumMatricula, lblAutogenerada, lblFechaHora, lblCodAlumno, lblNombreAlumno, lblDni, lblCodCurso,
			lblCiclo, lblCreditos, lblHoras, lblContador, lblAyuda, lblEstadoAlumno;
	private JTextField txtNumMatricula, txtFechaHora, txtCodAlumno, txtNombreAlumno, txtDni, txtCiclo, txtCreditos,
			txtHoras, txtEstadoAlumno;
	private JComboBox<Curso> cboCurso;
	private JButton btnBuscar, btnRegistrar, btnNuevo, btnEliminar, btnCerrar;
	private JScrollPane scrollPane;
	private JTable tblTabla;
	private DefaultTableModel modelo;

	public RegistroMatricula() {
		setTitle("Registro de Matrícula - Colegio América del Callao High School");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 850, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(EstiloUI.FONDO_VENTANA);
		contentPane.setLayout(null);

		pnlDatosMatricula = new JPanel();
		pnlDatosMatricula.setLayout(null);
		pnlDatosMatricula.setBackground(EstiloUI.FONDO_VENTANA);
		pnlDatosMatricula.setBorder(new TitledBorder(BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL),
				"Datos de la Matrícula", TitledBorder.LEADING, TitledBorder.TOP, EstiloUI.FUENTE_TITULO_PANEL,
				EstiloUI.AZUL_CORPORATIVO));
		pnlDatosMatricula.setBounds(20, 15, 790, 75);
		contentPane.add(pnlDatosMatricula);

		lblNumMatricula = new JLabel("N° Matrícula:");
		lblNumMatricula.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblNumMatricula.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblNumMatricula.setBounds(15, 30, 90, 20);
		pnlDatosMatricula.add(lblNumMatricula);

		txtNumMatricula = new JTextField();
		txtNumMatricula.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtNumMatricula.setForeground(EstiloUI.AZUL_CORPORATIVO);
		txtNumMatricula.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtNumMatricula.setEditable(false);
		txtNumMatricula.setBounds(110, 27, 110, 24);
		pnlDatosMatricula.add(txtNumMatricula);

		lblAutogenerada = new JLabel("(Autogenerado)");
		lblAutogenerada.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblAutogenerada.setForeground(EstiloUI.TEXTO_SECUNDARIO);
		lblAutogenerada.setBounds(230, 30, 110, 20);
		pnlDatosMatricula.add(lblAutogenerada);

		lblFechaHora = new JLabel("Fecha y Hora:");
		lblFechaHora.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblFechaHora.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblFechaHora.setBounds(430, 30, 90, 20);
		pnlDatosMatricula.add(lblFechaHora);

		txtFechaHora = new JTextField();
		txtFechaHora.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtFechaHora.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		txtFechaHora.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtFechaHora.setEditable(false);
		txtFechaHora.setBounds(525, 27, 220, 24);
		pnlDatosMatricula.add(txtFechaHora);

		pnlDatosAlumno = new JPanel();
		pnlDatosAlumno.setLayout(null);
		pnlDatosAlumno.setBackground(EstiloUI.FONDO_VENTANA);
		pnlDatosAlumno.setBorder(new TitledBorder(BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL),
				"Datos del Alumno", TitledBorder.LEADING, TitledBorder.TOP, EstiloUI.FUENTE_TITULO_PANEL,
				EstiloUI.AZUL_CORPORATIVO));
		pnlDatosAlumno.setBounds(20, 100, 790, 85);
		contentPane.add(pnlDatosAlumno);

		lblCodAlumno = new JLabel("Código Alumno:");
		lblCodAlumno.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblCodAlumno.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblCodAlumno.setBounds(15, 30, 100, 20);
		pnlDatosAlumno.add(lblCodAlumno);

		txtCodAlumno = new JTextField();
		txtCodAlumno.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtCodAlumno.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		txtCodAlumno.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtCodAlumno.setBounds(120, 27, 110, 24);
		pnlDatosAlumno.add(txtCodAlumno);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(EstiloUI.FUENTE_BOTON);
		btnBuscar.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		btnBuscar.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(240, 26, 90, 26);
		pnlDatosAlumno.add(btnBuscar);

		lblNombreAlumno = new JLabel("Nombres:");
		lblNombreAlumno.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblNombreAlumno.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblNombreAlumno.setBounds(345, 30, 70, 20);
		pnlDatosAlumno.add(lblNombreAlumno);

		txtNombreAlumno = new JTextField();
		txtNombreAlumno.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtNombreAlumno.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		txtNombreAlumno.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtNombreAlumno.setEditable(false);
		txtNombreAlumno.setBounds(420, 27, 240, 24);
		pnlDatosAlumno.add(txtNombreAlumno);

		lblDni = new JLabel("DNI:");
		lblDni.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblDni.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblDni.setBounds(675, 30, 40, 20);
		pnlDatosAlumno.add(lblDni);

		txtDni = new JTextField();
		txtDni.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtDni.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		txtDni.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtDni.setEditable(false);
		txtDni.setBounds(710, 27, 65, 24);
		pnlDatosAlumno.add(txtDni);
		
		lblEstadoAlumno = new JLabel("Estado:");
		lblEstadoAlumno.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblEstadoAlumno.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblEstadoAlumno.setBounds(15, 55, 70, 20);
		pnlDatosAlumno.add(lblEstadoAlumno);

		txtEstadoAlumno = new JTextField();
		txtEstadoAlumno.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtEstadoAlumno.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		txtEstadoAlumno.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtEstadoAlumno.setEditable(false);
		txtEstadoAlumno.setBounds(120, 52, 110, 24);
		pnlDatosAlumno.add(txtEstadoAlumno);

		pnlDatosCurso = new JPanel();
		pnlDatosCurso.setLayout(null);
		pnlDatosCurso.setBackground(EstiloUI.FONDO_VENTANA);
		pnlDatosCurso.setBorder(new TitledBorder(BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL),
				"Datos del Curso a Matricular", TitledBorder.LEADING, TitledBorder.TOP, EstiloUI.FUENTE_TITULO_PANEL,
				EstiloUI.AZUL_CORPORATIVO));
		pnlDatosCurso.setBounds(20, 185, 790, 110);
		contentPane.add(pnlDatosCurso);

		lblCodCurso = new JLabel("Cód. Curso:");
		lblCodCurso.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblCodCurso.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblCodCurso.setBounds(15, 30, 90, 20);
		pnlDatosCurso.add(lblCodCurso);

		cboCurso = new JComboBox<Curso>();
		cboCurso.setFont(EstiloUI.FUENTE_ETIQUETA);
		cboCurso.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		cboCurso.addActionListener(this);
		cboCurso.setBounds(110, 27, 450, 26);
		pnlDatosCurso.add(cboCurso);

		pnlDetalleCurso = new JPanel();
		pnlDetalleCurso.setLayout(null);
		pnlDetalleCurso.setBackground(EstiloUI.FONDO_VENTANA);
		pnlDetalleCurso.setBorder(BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL));
		pnlDetalleCurso.setBounds(15, 65, 755, 32);
		pnlDatosCurso.add(pnlDetalleCurso);

		lblCiclo = new JLabel("Ciclo:");
		lblCiclo.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblCiclo.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblCiclo.setBounds(10, 7, 50, 20);
		pnlDetalleCurso.add(lblCiclo);

		txtCiclo = new JTextField();
		txtCiclo.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtCiclo.setForeground(EstiloUI.AZUL_CORPORATIVO);
		txtCiclo.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtCiclo.setEditable(false);
		txtCiclo.setHorizontalAlignment(JTextField.CENTER);
		txtCiclo.setBounds(60, 4, 190, 24);
		pnlDetalleCurso.add(txtCiclo);

		lblCreditos = new JLabel("Créditos:");
		lblCreditos.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblCreditos.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblCreditos.setBounds(270, 7, 60, 20);
		pnlDetalleCurso.add(lblCreditos);

		txtCreditos = new JTextField();
		txtCreditos.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtCreditos.setForeground(EstiloUI.AZUL_CORPORATIVO);
		txtCreditos.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtCreditos.setEditable(false);
		txtCreditos.setHorizontalAlignment(JTextField.CENTER);
		txtCreditos.setBounds(335, 4, 180, 24);
		pnlDetalleCurso.add(txtCreditos);

		lblHoras = new JLabel("Horas:");
		lblHoras.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblHoras.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblHoras.setBounds(540, 7, 50, 20);
		pnlDetalleCurso.add(lblHoras);

		txtHoras = new JTextField();
		txtHoras.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtHoras.setForeground(EstiloUI.AZUL_CORPORATIVO);
		txtHoras.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtHoras.setEditable(false);
		txtHoras.setHorizontalAlignment(JTextField.CENTER);
		txtHoras.setBounds(595, 4, 155, 24);
		pnlDetalleCurso.add(txtHoras);

		pnlTabla = new JPanel();
		pnlTabla.setLayout(null);
		pnlTabla.setBackground(EstiloUI.FONDO_VENTANA);
		pnlTabla.setBorder(new TitledBorder(BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL),
				"Registro de Matrículas", TitledBorder.LEADING, TitledBorder.TOP, EstiloUI.FUENTE_TITULO_PANEL,
				EstiloUI.AZUL_CORPORATIVO));
		pnlTabla.setBounds(20, 305, 790, 175);
		contentPane.add(pnlTabla);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 25, 760, 110);
		pnlTabla.add(scrollPane);

		tblTabla = new JTable();
		tblTabla.setFillsViewportHeight(true);
		tblTabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblTabla.setFont(EstiloUI.FUENTE_TABLA);
		tblTabla.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		tblTabla.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		tblTabla.setSelectionBackground(EstiloUI.AZUL_FILA_SELECCIONADA);
		tblTabla.setSelectionForeground(EstiloUI.TEXTO_SOBRE_AZUL);
		tblTabla.setRowHeight(22);
		tblTabla.setShowGrid(true);
		tblTabla.setGridColor(EstiloUI.BORDE_PANEL);
		tblTabla.setIntercellSpacing(new java.awt.Dimension(1, 1));
		tblTabla.getTableHeader().setFont(EstiloUI.FUENTE_TITULO_PANEL);
		tblTabla.getTableHeader().setForeground(EstiloUI.TEXTO_PRINCIPAL);
		tblTabla.getTableHeader().setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		scrollPane.setViewportView(tblTabla);

		modelo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		modelo.addColumn("N° Matrícula");
		modelo.addColumn("Cód. Alumno");
		modelo.addColumn("Nombres y Apellidos");
		modelo.addColumn("Cód. Curso");
		modelo.addColumn("Asignatura");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		tblTabla.setModel(modelo);
		tblTabla.getColumnModel().getColumn(0).setCellRenderer(new RendererDestacado());
		tblTabla.getColumnModel().getColumn(3).setCellRenderer(new RendererDestacado());

		tblTabla.getColumnModel().getColumn(0).setPreferredWidth(90);
		tblTabla.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblTabla.getColumnModel().getColumn(2).setPreferredWidth(200);
		tblTabla.getColumnModel().getColumn(3).setPreferredWidth(90);
		tblTabla.getColumnModel().getColumn(4).setPreferredWidth(220);
		tblTabla.getColumnModel().getColumn(5).setPreferredWidth(90);
		tblTabla.getColumnModel().getColumn(6).setPreferredWidth(80);

		tblTabla.getTableHeader().setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			{
				setOpaque(true);
				setBackground(EstiloUI.FONDO_VENTANA);
				setForeground(EstiloUI.TEXTO_PRINCIPAL);
				setFont(EstiloUI.FUENTE_TITULO_PANEL);
				setHorizontalAlignment(JLabel.LEFT);
				setBorder(BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL));
			}
		});

		tblTabla.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				if (evt.getClickCount() == 2) {
					cargarFilaSeleccionada();
				}
			}
		});

		lblContador = new JLabel("");
		lblContador.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblContador.setForeground(EstiloUI.TEXTO_SECUNDARIO);
		lblContador.setBounds(15, 143, 300, 18);
		pnlTabla.add(lblContador);

		lblAyuda = new JLabel("Doble clic en fila para cargar datos");
		lblAyuda.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblAyuda.setForeground(EstiloUI.TEXTO_SECUNDARIO);
		lblAyuda.setHorizontalAlignment(JLabel.RIGHT);
		lblAyuda.setBounds(480, 143, 295, 18);
		pnlTabla.add(lblAyuda);

		pnlAcciones = new JPanel();
		pnlAcciones.setLayout(null);
		pnlAcciones.setBackground(EstiloUI.FONDO_VENTANA);
		pnlAcciones.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL));
		pnlAcciones.setBounds(20, 490, 790, 45);
		contentPane.add(pnlAcciones);

		btnRegistrar = new JButton("Registrar Matrícula");
		btnRegistrar.setFont(EstiloUI.FUENTE_BOTON);
		btnRegistrar.setBackground(EstiloUI.AZUL_CORPORATIVO);
		btnRegistrar.setForeground(EstiloUI.TEXTO_SOBRE_AZUL);
		btnRegistrar.setOpaque(true);
		btnRegistrar.setBorderPainted(false);
		btnRegistrar.setFocusPainted(false);
		btnRegistrar.setContentAreaFilled(true);
		btnRegistrar.addActionListener(this);
		btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnRegistrar.setBackground(EstiloUI.AZUL_CONTENEDOR_CLARO);
				btnRegistrar.setForeground(EstiloUI.AZUL_CORPORATIVO);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnRegistrar.setBackground(EstiloUI.AZUL_CORPORATIVO);
				btnRegistrar.setForeground(EstiloUI.TEXTO_SOBRE_AZUL);
			}
		});
		btnRegistrar.setBounds(10, 8, 170, 30);
		pnlAcciones.add(btnRegistrar);

		btnNuevo = new JButton("Nuevo");
		btnNuevo.setFont(EstiloUI.FUENTE_BOTON);
		btnNuevo.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		btnNuevo.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnNuevo.setUI(new javax.swing.plaf.basic.BasicButtonUI());
		btnNuevo.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL));
		btnNuevo.addActionListener(this);
		btnNuevo.setBounds(190, 8, 100, 30);
		pnlAcciones.add(btnNuevo);

		btnEliminar = new JButton("Eliminar / Cancelar");
		btnEliminar.setFont(EstiloUI.FUENTE_BOTON);
		btnEliminar.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		btnEliminar.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnEliminar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
		btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(300, 8, 170, 30);
		pnlAcciones.add(btnEliminar);

		btnCerrar = new JButton("Cerrar / Salir");
		btnCerrar.setFont(EstiloUI.FUENTE_BOTON);
		btnCerrar.setBackground(EstiloUI.FONDO_BOTON_PELIGRO);
		btnCerrar.setForeground(EstiloUI.TEXTO_BOTON_PELIGRO);
		btnCerrar.setOpaque(true);
		btnCerrar.setContentAreaFilled(true);
		btnCerrar.setUI(new javax.swing.plaf.basic.BasicButtonUI());
		btnCerrar.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_BOTON_PELIGRO, 2));
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(640, 8, 140, 30);
		pnlAcciones.add(btnCerrar);

		cargarCombo();
		nuevaMatricula();
		listar();

	}

	/**
	 * Declaración global
	 */
	ArregloAlumnos aa = new ArregloAlumnos();
	ArregloCursos ac = new ArregloCursos();
	ArregloMatriculas am = new ArregloMatriculas();

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == cboCurso) {
			actionPerformedCboCurso(arg0);
		}
		if (arg0.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnNuevo) {
			nuevaMatricula();
		}
		if (arg0.getSource() == btnCerrar) {
			dispose();
		}
	}

	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		String codAlumno = leerCodAlumno();
		Alumno x = aa.buscar(codAlumno);
		if (x != null) {
			txtNombreAlumno.setText(x.nombreCompleto());
			txtDni.setText(x.getDni());
			txtEstadoAlumno.setText(x.getEstadoTexto());
		} else {
			mensaje("el CÓDIGO de alumno no existe");
			txtNombreAlumno.setText("");
			txtDni.setText("");
			txtEstadoAlumno.setText("");
		}
	}

	protected void actionPerformedCboCurso(ActionEvent arg0) {
		Curso x = (Curso) cboCurso.getSelectedItem();
		if (x != null) {
			txtCiclo.setText("Ciclo " + numeroRomano(x.getCiclo()));
			txtCreditos.setText(x.getCreditos() + " Créditos");
			txtHoras.setText(String.format("%02d", x.getHoras()) + " Hrs/Sem");
		}
	}

	protected void actionPerformedBtnRegistrar(ActionEvent arg0) {
		if (txtNombreAlumno.getText().isEmpty()) {
			mensaje("busque un alumno válido antes de registrar");
			return;
		}

		Curso curso = (Curso) cboCurso.getSelectedItem();
		if (curso == null) {
			mensaje("no hay cursos disponibles para matricular");
			return;
		}
		Matricula existente = am.buscar(txtNumMatricula.getText().trim());

		if (existente != null) {
			existente.setCodCurso(curso.getCodCurso());
			am.actualizar();
			mensaje("curso de la matrícula actualizado correctamente");
		} else {
			String codAlumno = leerCodAlumno();

			Alumno al = aa.buscar(codAlumno);

			if (al == null) {
				mensaje("El alumno no existe.");
				return;
			}

			if (al.getEstado() == 2) {
				mensaje("No se puede matricular: el alumno está retirado.");
				return;
			}

			if (am.existeMatriculaActiva(codAlumno)) {
				mensaje("este alumno ya tiene una matrícula registrada");
				return;
			}

			String fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
			String[] partes = fechaHora.split(" ");

			Matricula nueva = new Matricula(txtNumMatricula.getText(), codAlumno, curso.getCodCurso(), partes[0],
					partes[1]);
			am.adicionar(nueva);

			al.setEstado(1);
			aa.actualizar();
		}

		listar();
		nuevaMatricula();
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		Matricula x = am.buscar(txtNumMatricula.getText().trim());
		if (x == null) {
			mensaje("no hay una matrícula cargada para eliminar");
			return;
		}

		Alumno al = aa.buscar(x.getCodAlumno());
		if (al != null && al.getEstado() == 2) {
			mensaje("no se puede cancelar: el alumno ya está retirado");
			return;
		}

		int confirmar = JOptionPane.showConfirmDialog(this,
				"¿Confirma cancelar la matrícula " + x.getNumMatricula() + "?", "Confirmar cancelación",
				JOptionPane.YES_NO_OPTION);

		if (confirmar == JOptionPane.YES_OPTION) {
			am.eliminar(x);
			if (al != null) {
				al.setEstado(0);
			}
			listar();
			nuevaMatricula();
		}
	}

	void nuevaMatricula() {
		txtNumMatricula.setText(am.siguienteCodigo());
		txtFechaHora.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		txtCodAlumno.setText("");
		txtNombreAlumno.setText("");
		txtDni.setText("");
		txtEstadoAlumno.setText("");
		
		if (cboCurso.getItemCount() > 0)
			cboCurso.setSelectedIndex(0);
		txtCodAlumno.requestFocus();
	}

	void cargarCombo() {
		for (int i = 0; i < ac.tamaño(); i++)
			cboCurso.addItem(ac.obtener(i));
	}

	void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < am.tamaño(); i++) {
			Matricula m = am.obtener(i);
			Alumno al = aa.buscar(m.getCodAlumno());
			Curso cu = ac.buscar(m.getCodCurso());
			Object[] fila = { m.getNumMatricula(), m.getCodAlumno(), (al != null ? al.nombreCompleto() : ""),
					m.getCodCurso(), (cu != null ? cu.getAsignatura() : ""), m.getFecha(), m.getHora() };
			modelo.addRow(fila);
		}
		lblContador.setText(am.tamaño() + " matrícula(s) registradas en memoria");
	}

	void cargarFilaSeleccionada() {
		int fila = tblTabla.getSelectedRow();
		if (fila < 0)
			return;
		String numMatricula = (String) modelo.getValueAt(fila, 0);
		Matricula m = am.buscar(numMatricula);
		if (m == null)
			return;

		txtNumMatricula.setText(m.getNumMatricula());
		txtFechaHora.setText(m.getFecha() + " " + m.getHora());
		txtCodAlumno.setText(m.getCodAlumno());

		Alumno al = aa.buscar(m.getCodAlumno());
		if (al != null) {
			txtNombreAlumno.setText(al.nombreCompleto());
		    txtDni.setText(al.getDni());
		    txtEstadoAlumno.setText(al.getEstadoTexto());
		}

		Curso cu = ac.buscar(m.getCodCurso());
		if (cu != null) {
			cboCurso.setSelectedItem(cu);
		}
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	String leerCodAlumno() {
		return txtCodAlumno.getText().trim();
	}

	String numeroRomano(int ciclo) {
		String[] romanos = { "", "I", "II", "III", "IV", "V", "VI" };
		if (ciclo < 0 || ciclo >= romanos.length)
			return "" + ciclo;
		return romanos[ciclo];
	}

	class RendererDestacado extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable tabla, Object valor, boolean seleccionado,
				boolean conFoco, int fila, int columna) {
			java.awt.Component celda = super.getTableCellRendererComponent(tabla, valor, seleccionado, conFoco, fila,
					columna);
			if (!seleccionado) {
				celda.setForeground(EstiloUI.AZUL_CORPORATIVO);
			}
			setFont(EstiloUI.FUENTE_BOTON);
			return celda;
		}
	}

}
