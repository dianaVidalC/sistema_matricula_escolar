package gui;

import arreglo.ArregloAlumnos;
import arreglo.ArregloCursos;
import arreglo.ArregloMatriculas;
import arreglo.ArregloRetiros;
import clase.Alumno;
import clase.Curso;
import clase.Matricula;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

public class Reporte extends JFrame {

	// Declaración global: las 4 fuentes de datos del sistema
	private ArregloAlumnos aa = new ArregloAlumnos();
	private ArregloCursos ac = new ArregloCursos();
	private ArregloMatriculas am = new ArregloMatriculas();
	private ArregloRetiros ar = new ArregloRetiros();

	private JComboBox<String> cbTipoReporte;
	private JComboBox<String> cbCiclo;
	private JComboBox<Curso> cbCurso;
	private JTextField txtFechaIni;
	private JTextField txtFechaFin;

	private JTable tablaReportes;
	private DefaultTableModel modeloTabla;
	private JTextArea txtReporte;
	private JTabbedPane tabs;

	private JLabel lblTotal;
	private JLabel lblCreditos;
	private JLabel lblEstado;
	private JLabel lblRetiros;
	private JLabel lblStatus;
	private JLabel lblCount;

	private JButton btnProcesar;
	private JButton btnImprimir;
	private JButton btnPDF;
	private JButton btnExcel;
	private JButton btnLimpiar;

	public Reporte() {
		initComponents();
		cargarCombos();
		cargarReporte();
		configurarAtajos();
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		setTitle("Reportes del Sistema - Colegio América del Callao High School");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 700);
		setMinimumSize(new Dimension(900, 620));
		setLayout(new BorderLayout());
		getContentPane().setBackground(new Color(240, 240, 240));

		UIManager.put("Button.font", new Font("Segoe UI", Font.PLAIN, 11));
		UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 11));
		UIManager.put("ComboBox.font", new Font("Segoe UI", Font.PLAIN, 11));

		setJMenuBar(crearMenu());
		add(crearBarraHerramientas(), BorderLayout.NORTH);
		add(crearContenido(), BorderLayout.CENTER);
		add(crearBarraEstado(), BorderLayout.SOUTH);
	}

	private JMenuBar crearMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu archivo = new JMenu("Archivo");
		JMenu mantenimiento = new JMenu("Mantenimiento");
		JMenu registro = new JMenu("Registro");
		JMenu consulta = new JMenu("Consulta");
		JMenu reportes = new JMenu("Reportes");
		JMenu ayuda = new JMenu("Ayuda");

		JMenuItem generar = new JMenuItem("Generar reporte");
		generar.addActionListener(e -> cargarReporte());
		reportes.add(generar);

		JMenuItem imprimir = new JMenuItem("Imprimir");
		imprimir.addActionListener(e -> imprimirReporte());
		reportes.add(imprimir);

		JMenuItem salir = new JMenuItem("Cerrar");
		salir.addActionListener(e -> dispose());
		archivo.add(salir);

		menuBar.add(archivo);
		menuBar.add(mantenimiento);
		menuBar.add(registro);
		menuBar.add(consulta);
		menuBar.add(reportes);
		menuBar.add(ayuda);

		return menuBar;
	}

	private JToolBar crearBarraHerramientas() {
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(186, 186, 186)),
				new EmptyBorder(3, 5, 3, 5)));
		toolbar.setBackground(new Color(249, 249, 249));

		JButton generar = crearBoton("Generar", "diagrama.png");
		generar.setToolTipText("Procesar y recargar datos (F5)");
		generar.addActionListener(e -> cargarReporte());

		JButton imprimir = crearBoton("Imprimir", "imprimir.png");
		imprimir.setToolTipText("Enviar a impresión (Ctrl+P)");
		imprimir.addActionListener(e -> imprimirReporte());

		JButton pdf = crearBoton("Exportar PDF", "exportar_pdf.png");
		pdf.addActionListener(
				e -> mostrarMensaje("PDF", "Aquí puedes integrar iText/OpenPDF para generar el archivo PDF."));

		JButton excel = crearBoton("Exportar Excel", "excel.png");
		excel.addActionListener(
				e -> mostrarMensaje("Excel", "Aquí puedes integrar Apache POI para generar el archivo Excel."));

		JButton limpiar = crearBoton("Limpiar Filtros", "limpiar.png");
		limpiar.addActionListener(e -> limpiarFiltros());

		toolbar.add(generar);
		toolbar.add(imprimir);
		toolbar.addSeparator();
		toolbar.add(pdf);
		toolbar.add(excel);
		toolbar.addSeparator();
		toolbar.add(limpiar);
		toolbar.add(Box.createHorizontalGlue());

		return toolbar;
	}

	private JButton crearBoton(String texto, String nombreIcono) {
		JButton b = new JButton(texto);
		b.setFocusPainted(false);
		b.setOpaque(true);
		b.setBackground(new Color(225, 225, 225));
		b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(120, 120, 120)),
				new EmptyBorder(3, 7, 3, 7)));
		b.setPreferredSize(new Dimension(b.getPreferredSize().width + 30, 28));

		java.net.URL url = getClass().getResource("/recursos/" + nombreIcono);
		if (url != null) {
			ImageIcon iconoOriginal = new ImageIcon(url);
			Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
			b.setIcon(new ImageIcon(imagenEscalada));
		}
		return b;
	}

	private JPanel crearContenido() {
		JPanel principal = new JPanel(new BorderLayout(0, 8));
		principal.setBorder(new EmptyBorder(8, 8, 8, 8));
		principal.setBackground(new Color(240, 240, 240));

		JPanel centro = new JPanel(new BorderLayout(0, 8));
		centro.setOpaque(false);
		centro.add(crearPanelFiltros(), BorderLayout.NORTH);
		centro.add(crearPanelResultados(), BorderLayout.CENTER);
		centro.add(crearPanelResumen(), BorderLayout.SOUTH);

		principal.add(centro, BorderLayout.CENTER);
		return principal;
	}

	private JPanel crearPanelFiltros() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(247, 247, 247));
		panel.setBorder(titled("Criterios y Selección de Reporte (JComboBox / JTextField)"));

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 5, 3, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;

		JLabel lTipo = new JLabel("Tipo de Reporte a Emitir: *");
		lTipo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		c.weightx = 0.5;
		panel.add(lTipo, c);

		JLabel lCiclo = new JLabel("Ciclo Académico:");
		lCiclo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		c.gridx = 5;
		c.gridwidth = 2;
		c.weightx = 0.2;
		panel.add(lCiclo, c);

		JLabel lCurso = new JLabel("Asignatura / Curso:");
		lCurso.setFont(new Font("Segoe UI", Font.BOLD, 11));
		c.gridx = 7;
		c.gridwidth = 3;
		c.weightx = 0.3;
		panel.add(lCurso, c);

		JLabel lFecha = new JLabel("Período de Emisión:");
		lFecha.setFont(new Font("Segoe UI", Font.BOLD, 11));
		c.gridx = 10;
		c.gridwidth = 2;
		c.weightx = 0.2;
		panel.add(lFecha, c);

		cbTipoReporte = new JComboBox<>(new String[] { "Alumnos matriculados por curso",
				"Alumnos con matrícula pendiente", "Alumnos matriculados vigentes" });

		cbCiclo = new JComboBox<>(new String[] { "Todos los ciclos", "Ciclo I", "Ciclo II", "Ciclo III", "Ciclo IV",
				"Ciclo V", "Ciclo VI" });

		cbCurso = new JComboBox<>();
		txtFechaIni = new JTextField("01/03/2025");
		txtFechaFin = new JTextField("15/04/2025");

		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 5;
		c.weightx = 0.5;
		panel.add(cbTipoReporte, c);
		c.gridx = 5;
		c.gridwidth = 2;
		c.weightx = 0.2;
		panel.add(cbCiclo, c);
		c.gridx = 7;
		c.gridwidth = 3;
		c.weightx = 0.3;
		panel.add(cbCurso, c);

		JPanel fechas = new JPanel(new GridLayout(1, 3, 3, 0));
		fechas.setOpaque(false);
		fechas.add(txtFechaIni);
		fechas.add(new JLabel("-", SwingConstants.CENTER));
		fechas.add(txtFechaFin);
		c.gridx = 10;
		c.gridwidth = 2;
		c.weightx = 0.2;
		panel.add(fechas, c);

		JPanel acciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 4));
		acciones.setOpaque(false);

		btnProcesar = botonPrimario("Procesar Reporte (F5)");
		btnProcesar.addActionListener(e -> cargarReporte());

		btnImprimir = crearBoton("Imprimir", "imprimir.png");
		btnImprimir.setToolTipText("Enviar a impresión (Ctrl+P)");
		btnImprimir.addActionListener(e -> imprimirReporte());

		btnPDF = crearBoton("Exportar PDF", "exportar_pdf.png");
		btnPDF.addActionListener(
				e -> mostrarMensaje("PDF", "Aquí puedes integrar iText/OpenPDF para generar el archivo PDF."));

		btnExcel = crearBoton("Exportar Excel", "excel.png");
		btnExcel.addActionListener(
				e -> mostrarMensaje("Excel", "Aquí puedes integrar Apache POI para generar el archivo Excel."));

		btnLimpiar = crearBoton("Limpiar Filtros", "limpiar.png");
		btnLimpiar.addActionListener(e -> limpiarFiltros());

		acciones.add(btnProcesar);
		acciones.add(btnImprimir);
		acciones.add(btnPDF);
		acciones.add(btnExcel);
		acciones.add(btnLimpiar);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 12;
		c.weightx = 1;
		c.insets = new Insets(5, 5, 0, 5);
		panel.add(acciones, c);

		return panel;
	}

	private JButton botonPrimario(String texto) {
		JButton b = new JButton(texto);
		b.setForeground(Color.WHITE);
		b.setBackground(new Color(11, 77, 156));
		b.setOpaque(true);
		b.setContentAreaFilled(true);
		b.setUI(new javax.swing.plaf.basic.BasicButtonUI());
		b.setFocusPainted(false);
		b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 43, 87)),
				new EmptyBorder(4, 9, 4, 9)));
		return b;
	}

	private JPanel crearPanelResultados() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(240, 240, 240));

		tabs = new JTabbedPane();
		tabs.setFont(new Font("Segoe UI", Font.PLAIN, 11));

		crearTabla();
		JScrollPane scrollTabla = new JScrollPane(tablaReportes);
		scrollTabla.setPreferredSize(new Dimension(900, 275));
		tabs.addTab("▦  Vista Previa Tabular (JTable)", scrollTabla);

		txtReporte = new JTextArea();
		txtReporte.setFont(new Font("Consolas", Font.PLAIN, 11));
		txtReporte.setEditable(false);
		txtReporte.setBackground(Color.WHITE);
		txtReporte.setBorder(new EmptyBorder(8, 8, 8, 8));
		tabs.addTab("▤  Ficha Impresa / Vista de Texto", new JScrollPane(txtReporte));

		panel.add(tabs, BorderLayout.CENTER);
		return panel;
	}

	private void crearTabla() {
		String[] columnas = { "N°", "N° Matrícula", "Cód. Alumno", "Nombres y Apellidos", "Cód. Curso",
				"Curso / Asignatura", "Ciclo", "Fecha y Hora", "Estado (*)" };

		modeloTabla = new DefaultTableModel(columnas, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaReportes = new JTable(modeloTabla);
		tablaReportes.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		tablaReportes.setRowHeight(24);
		tablaReportes.setSelectionBackground(new Color(0, 95, 184));
		tablaReportes.setSelectionForeground(Color.WHITE);
		tablaReportes.setGridColor(new Color(224, 224, 224));
		tablaReportes.setAutoCreateRowSorter(true);
		tablaReportes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
		tablaReportes.getTableHeader().setBackground(new Color(223, 223, 223));
		tablaReportes.getTableHeader().setForeground(new Color(35, 35, 35));

		tablaReportes.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablaReportes.getColumnModel().getColumn(1).setPreferredWidth(90);
		tablaReportes.getColumnModel().getColumn(2).setPreferredWidth(85);
		tablaReportes.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablaReportes.getColumnModel().getColumn(4).setPreferredWidth(75);
		tablaReportes.getColumnModel().getColumn(5).setPreferredWidth(190);
		tablaReportes.getColumnModel().getColumn(6).setPreferredWidth(70);
		tablaReportes.getColumnModel().getColumn(7).setPreferredWidth(120);
		tablaReportes.getColumnModel().getColumn(8).setPreferredWidth(90);

		tablaReportes.getColumnModel().getColumn(8).setCellRenderer(new EstadoRenderer());

		tablaReportes.getSelectionModel().addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting() && tablaReportes.getSelectedRow() >= 0) {
				actualizarFichaSeleccionada();
			}
		});
	}

	private JPanel crearPanelResumen() {
		JPanel panel = new JPanel(new GridLayout(1, 4, 8, 0));
		panel.setBorder(titled("Resumen Estadístico y Conteo de Operaciones"));
		panel.setBackground(new Color(247, 247, 247));

		lblTotal = crearDato("Total Registros", "0 alumnos", new Color(0, 35, 111));
		lblCreditos = crearDato("Créditos Totales", "0 créditos", new Color(30, 58, 138));
		lblEstado = crearDato("Estado Alumnos", "0 Activos | 0 Retirados", Color.DARK_GRAY);
		lblRetiros = crearDato("Retiros Registrados", "0 retiros", new Color(153, 27, 27));

		panel.add(tarjeta("∑", lblTotal));
		panel.add(tarjeta("CR", lblCreditos));
		panel.add(tarjeta("⇅", lblEstado));
		panel.add(tarjeta("✕", lblRetiros));

		return panel;
	}

	private JLabel crearDato(String titulo, String valor, Color color) {
		JLabel l = new JLabel("<html><div style='font-size:9px;color:#555;'>" + titulo.toUpperCase()
				+ "</div><div style='font-size:13px;color:" + toHex(color) + ";'><b>" + valor + "</b></div></html>");
		l.setBorder(new EmptyBorder(5, 8, 5, 8));
		l.setOpaque(true);
		l.setBackground(Color.WHITE);
		return l;
	}

	private JPanel tarjeta(String icono, JLabel dato) {
		JPanel p = new JPanel(new BorderLayout());
		p.setBackground(Color.WHITE);
		p.setBorder(BorderFactory.createLineBorder(new Color(197, 197, 211)));

		JLabel ico = new JLabel(icono, SwingConstants.CENTER);
		ico.setPreferredSize(new Dimension(38, 38));
		ico.setOpaque(true);
		ico.setBackground(new Color(220, 233, 255));
		ico.setForeground(new Color(0, 35, 111));
		ico.setFont(new Font("Segoe UI Symbol", Font.BOLD, 13));

		p.add(dato, BorderLayout.CENTER);
		p.add(ico, BorderLayout.EAST);
		return p;
	}

	private JPanel crearBarraEstado() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(181, 181, 181)), new EmptyBorder(5, 8, 5, 8)));
		panel.setBackground(new Color(230, 230, 230));

		JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
		izquierda.setOpaque(false);

		lblStatus = new JLabel("●  Estado: Listo");
		lblCount = new JLabel(" | Total Matrículas: 0 | Activos: 0 | Retirados: 0");
		izquierda.add(lblStatus);
		izquierda.add(lblCount);

		panel.add(izquierda, BorderLayout.WEST);
		return panel;
	}

	private void cargarCombos() {
		for (int i = 0; i < ac.tamaño(); i++)
			cbCurso.addItem(ac.obtener(i));
	}

	/**
	 * Arma la lista de matrículas correspondientes al curso seleccionado, cruzando
	 * con Alumno (nombres) y calculando el Estado según si existe un Retiro
	 * asociado a esa matrícula (no es un atributo guardado)
	 */
	private void cargarReporte() {
		modeloTabla.setRowCount(0);

		Curso curso = (Curso) cbCurso.getSelectedItem();
		if (curso == null)
			return;

		List<Matricula> filtradas = new ArrayList<>();
		for (int i = 0; i < am.tamaño(); i++) {
			Matricula m = am.obtener(i);
			if (m.getCodCurso().equalsIgnoreCase(curso.getCodCurso())) {
				filtradas.add(m);
			}
		}

		int n = 1;
		for (Matricula m : filtradas) {
			Alumno al = aa.buscar(m.getCodAlumno());
			String estado = ar.existeRetiro(m.getNumMatricula()) ? "Retirado" : "Matriculado";

			modeloTabla.addRow(new Object[] { n++, m.getNumMatricula(), m.getCodAlumno(),
					(al != null ? al.nombreCompleto() : ""), curso.getCodCurso(), curso.getAsignatura(),
					"Ciclo " + curso.getCiclo(), m.getFecha() + " " + m.getHora(), estado });
		}

		tablaReportes.getColumnModel().getColumn(8).setCellRenderer(new EstadoRenderer());

		actualizarResumen(curso, filtradas);
		generarTexto(curso, filtradas);

		lblStatus.setText("●  Estado: Reporte generado correctamente");
		lblCount.setText(
				" | Total Matrículas: " + filtradas.size() + " | Activos: " + contarPorEstado(filtradas, "Matriculado")
						+ " | Retirados: " + contarPorEstado(filtradas, "Retirado"));
	}

	private int contarPorEstado(List<Matricula> lista, String estadoBuscado) {
		int cont = 0;
		for (Matricula m : lista) {
			String estado = ar.existeRetiro(m.getNumMatricula()) ? "Retirado" : "Matriculado";
			if (estado.equals(estadoBuscado))
				cont++;
		}
		return cont;
	}

	private void actualizarResumen(Curso curso, List<Matricula> matriculas) {
		int activos = contarPorEstado(matriculas, "Matriculado");
		int retirados = contarPorEstado(matriculas, "Retirado");
		int creditos = matriculas.size() * curso.getCreditos();

		lblTotal.setText("<html><div style='font-size:9px;color:#555;'>TOTAL REGISTROS</div>"
				+ "<div style='font-size:13px;color:#00236f;'><b>" + matriculas.size() + " alumnos</b></div></html>");

		lblCreditos.setText("<html><div style='font-size:9px;color:#555;'>CRÉDITOS TOTALES</div>"
				+ "<div style='font-size:13px;color:#1e3a8a;'><b>" + creditos + " créditos</b></div></html>");

		lblEstado.setText("<html><div style='font-size:9px;color:#555;'>ESTADO ALUMNOS</div>"
				+ "<div style='font-size:12px;'><b><span style='color:#166534;'>" + activos
				+ " Activos</span> | <span style='color:#991b1b;'>" + retirados + " Retirados</span></b></div></html>");

		lblRetiros.setText("<html><div style='font-size:9px;color:#555;'>RETIROS REGISTRADOS</div>"
				+ "<div style='font-size:13px;color:#991b1b;'><b>" + retirados + " retiros</b></div></html>");
	}

	private void generarTexto(Curso curso, List<Matricula> matriculas) {
		StringBuilder sb = new StringBuilder();

		sb.append(
				"====================================================================================================\n");
		sb.append("SISTEMA DE GESTIÓN ACADÉMICA - REPORTE DE MATRÍCULAS POR ASIGNATURA\n");
		sb.append("CURSO: ").append(curso.getAsignatura().toUpperCase()).append(" | CÓDIGO: ")
				.append(curso.getCodCurso()).append(" | CICLO: ").append(curso.getCiclo()).append("\n");
		sb.append("ESTRUCTURA: CADA MATRÍCULA VINCULA A UN ÚNICO ALUMNO CON ESTA ASIGNATURA\n");
		sb.append("EMISIÓN: ").append(txtFechaFin.getText()).append("\n");
		sb.append(
				"====================================================================================================\n");
		sb.append(String.format("%-4s %-12s %-12s %-40s %-20s %-12s%n", "N°", "MATRÍC.", "CÓDIGO",
				"APELLIDOS Y NOMBRES", "FECHA Y HORA", "ESTADO"));
		sb.append(
				"----------------------------------------------------------------------------------------------------\n");

		int n = 1;
		for (Matricula m : matriculas) {
			Alumno al = aa.buscar(m.getCodAlumno());
			String estado = ar.existeRetiro(m.getNumMatricula()) ? "Retirado" : "Matriculado";
			sb.append(String.format("%02d.  %-12s %-12s %-40s %-20s %-12s%n", n++, m.getNumMatricula(),
					m.getCodAlumno(), (al != null ? al.nombreCompleto() : ""), m.getFecha() + " " + m.getHora(),
					estado.toUpperCase()));
		}

		int activos = contarPorEstado(matriculas, "Matriculado");
		int retirados = contarPorEstado(matriculas, "Retirado");

		sb.append(
				"----------------------------------------------------------------------------------------------------\n");
		sb.append("TOTAL MATRÍCULAS REGISTRADAS EN EL CURSO: ").append(matriculas.size())
				.append(" | MATRICULADOS ACTIVOS: ").append(activos).append(" | RETIRADOS: ").append(retirados)
				.append("\n");
		sb.append("CRÉDITOS TOTALES ASIGNADOS: ").append(matriculas.size() * curso.getCreditos())
				.append(" CRÉDITOS ACADÉMICOS\n");
		sb.append(
				"====================================================================================================");

		txtReporte.setText(sb.toString());
		txtReporte.setCaretPosition(0);
	}

	private void actualizarFichaSeleccionada() {
		int fila = tablaReportes.getSelectedRow();
		if (fila < 0)
			return;

		int modelRow = tablaReportes.convertRowIndexToModel(fila);
		txtReporte.setText("FICHA DE MATRÍCULA\n\n" + "N° Matrícula : " + modeloTabla.getValueAt(modelRow, 1) + "\n"
				+ "Cód. Alumno : " + modeloTabla.getValueAt(modelRow, 2) + "\n" + "Alumno      : "
				+ modeloTabla.getValueAt(modelRow, 3) + "\n" + "Curso       : " + modeloTabla.getValueAt(modelRow, 5)
				+ "\n" + "Ciclo       : " + modeloTabla.getValueAt(modelRow, 6) + "\n" + "Fecha/Hora  : "
				+ modeloTabla.getValueAt(modelRow, 7) + "\n" + "Estado      : " + modeloTabla.getValueAt(modelRow, 8));
	}

	private void limpiarFiltros() {
		cbTipoReporte.setSelectedIndex(0);
		cbCiclo.setSelectedIndex(0);
		if (cbCurso.getItemCount() > 0)
			cbCurso.setSelectedIndex(0);
		txtFechaIni.setText("01/03/2025");
		txtFechaFin.setText("15/04/2025");
		cargarReporte();
		lblStatus.setText("●  Estado: Filtros restablecidos");
	}

	private void imprimirReporte() {
		try {
			boolean ok = txtReporte.print();
			if (ok) {
				lblStatus.setText("●  Estado: Reporte enviado a impresión");
			}
		} catch (PrinterException ex) {
			mostrarMensaje("Impresión", "No se pudo imprimir: " + ex.getMessage());
		}
	}

	private void configurarAtajos() {
		getRootPane().registerKeyboardAction(e -> cargarReporte(), KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		getRootPane().registerKeyboardAction(e -> imprimirReporte(),
				KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	private TitledBorder titled(String texto) {
		return BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(179, 179, 179)), texto,
				TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 11), new Color(0, 71, 133));
	}

	private void mostrarMensaje(String titulo, String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	private String toHex(Color c) {
		return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
	}

	private static class EstadoRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
				int row, int column) {
			JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, selected, focused, row, column);

			l.setHorizontalAlignment(SwingConstants.CENTER);
			l.setFont(new Font("Segoe UI", Font.BOLD, 11));

			if (!selected && value != null) {
				if ("Retirado".equalsIgnoreCase(value.toString())) {
					l.setForeground(new Color(153, 27, 27));
				} else {
					l.setForeground(new Color(22, 101, 52));
				}
			} else if (selected) {
				l.setForeground(Color.WHITE);
			}

			return l;
		}
	}
}