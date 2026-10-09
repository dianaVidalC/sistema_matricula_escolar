package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import util.EstiloUI;

public class MenuPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, pnlDatosProyecto, pnlEncabezado;
	private JMenuBar menuBar;
	private JMenu mnuArchivo, mnuMantenimiento, mnuRegistro, mnuConsulta, mnuReporte, mnuAyuda;
	private JLabel lblTitulo, lblSubtitulo, lblCiclo, lblEstado, lblEquipo, lblDescripcion, lblTituloEquipo,
			lblTituloEspec, lblEspecificaciones, lblProyectoFinal, lblInfoAcademica, lblTituloInfo, lblIcono;
	private JButton btnMantenimientoAlumno, btnMantenimientoCurso, btnRegistroMatricula, btnRegistroRetiro, btnConsulta,
			btnReporte, btnSalir;
	private JMenuItem mniRegistroMatricula, mniRetiroAlumno, mniMantenimientoAlumno, mniMantenimientoCurso;
	private JPanel pnlInfoAcademica, pnlEquipo, pnlEspecificaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		setTitle("Sistema de Registro y Matrícula - Colegio América del Callao High School");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 900, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(EstiloUI.FONDO_VENTANA);
		contentPane.setLayout(null);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnuArchivo = new JMenu("Archivo");
		menuBar.add(mnuArchivo);

		mnuMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnuMantenimiento);

		mniMantenimientoAlumno = new JMenuItem("Mantenimiento de Alumno");
		mniMantenimientoAlumno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		mniMantenimientoAlumno.addActionListener(this);
		mnuMantenimiento.add(mniMantenimientoAlumno);

		mniMantenimientoCurso = new JMenuItem("Mantenimiento de Curso");
		mniMantenimientoCurso.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mniMantenimientoCurso.addActionListener(this);
		mnuMantenimiento.add(mniMantenimientoCurso);

		mnuRegistro = new JMenu("Registro");
		menuBar.add(mnuRegistro);

		mniRegistroMatricula = new JMenuItem("Registro Matrícula");
		mniRegistroMatricula.setUI(new javax.swing.plaf.basic.BasicMenuItemUI());
		mniRegistroMatricula.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		mniRegistroMatricula.addActionListener(this);
		mnuRegistro.add(mniRegistroMatricula);

		mniRetiroAlumno = new JMenuItem("Retiro de Alumno");
		mniRetiroAlumno.setUI(new javax.swing.plaf.basic.BasicMenuItemUI());
		mniRetiroAlumno.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		mniRetiroAlumno.addActionListener(this);
		mnuRegistro.add(mniRetiroAlumno);

		mnuConsulta = new JMenu("Consulta");
		menuBar.add(mnuConsulta);

		mnuReporte = new JMenu("Reporte");
		mnuReporte.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				Reporte frame = new Reporte();
				frame.setVisible(true);
			}
		});
		menuBar.add(mnuReporte);

		mnuAyuda = new JMenu("Ayuda");
		menuBar.add(mnuAyuda);

		pnlEncabezado = new JPanel();
		pnlEncabezado.setLayout(null);
		pnlEncabezado.setBackground(EstiloUI.AZUL_CONTENEDOR_CLARO);
		pnlEncabezado.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL));
		pnlEncabezado.setBounds(20, 15, 850, 80);
		contentPane.add(pnlEncabezado);

		lblIcono = new JLabel("\uD83C\uDF93");
		lblIcono.setOpaque(true);
		lblIcono.setBackground(EstiloUI.AZUL_CORPORATIVO);
		lblIcono.setForeground(EstiloUI.TEXTO_SOBRE_AZUL);
		lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcono.setVerticalAlignment(SwingConstants.CENTER);
		lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
		lblIcono.setBounds(15, 15, 50, 50);
		pnlEncabezado.add(lblIcono);

		lblTitulo = new JLabel("COLEGIO AMÉRICA DEL CALLAO HIGH SCHOOL");
		lblTitulo.setFont(EstiloUI.FUENTE_ENCABEZADO);
		lblTitulo.setForeground(EstiloUI.AZUL_MARINO_OSCURO);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setBounds(80, 8, 420, 22);
		pnlEncabezado.add(lblTitulo);

		lblSubtitulo = new JLabel("Sistema Integrado de Registro y Matrícula");
		lblSubtitulo.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblSubtitulo.setForeground(EstiloUI.AZUL_CORPORATIVO);
		lblSubtitulo.setBounds(80, 30, 400, 18);
		pnlEncabezado.add(lblSubtitulo);

		lblDescripcion = new JLabel("Sistema de Gestión Académica • Nivel Secundaria / High School • Año Lectivo 2026");
		lblDescripcion.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblDescripcion.setForeground(EstiloUI.TEXTO_SECUNDARIO);
		lblDescripcion.setBounds(80, 52, 550, 18);
		pnlEncabezado.add(lblDescripcion);

		lblCiclo = new JLabel("CICLO LECTIVO 2026-II");
		lblCiclo.setFont(EstiloUI.FUENTE_TITULO_PANEL);
		lblCiclo.setForeground(EstiloUI.AZUL_CORPORATIVO);
		lblCiclo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCiclo.setOpaque(true);
		lblCiclo.setBackground(Color.WHITE);
		lblCiclo.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.AZUL_CORPORATIVO));
		lblCiclo.setBounds(690, 28, 145, 25);
		pnlEncabezado.add(lblCiclo);

		btnMantenimientoAlumno = new JButton("<html><b>1. Mantenimiento de Alumnos</b><br>"
				+ "<font size=2>Altas, bajas, modificaciones y consultas</font></html>");
		btnMantenimientoAlumno.setHorizontalAlignment(SwingConstants.LEFT);
		btnMantenimientoAlumno.addActionListener(this);
		btnMantenimientoAlumno.setBounds(20, 115, 420, 55);
		contentPane.add(btnMantenimientoAlumno);

		btnMantenimientoCurso = new JButton("<html><b>2. Mantenimiento de Cursos</b><br>"
				+ "<font size=2>Altas, bajas, modificaciones y consultas</font></html>");
		btnMantenimientoCurso.setHorizontalAlignment(SwingConstants.LEFT);
		btnMantenimientoCurso.addActionListener(this);
		btnMantenimientoCurso.setBounds(450, 115, 420, 55);
		contentPane.add(btnMantenimientoCurso);

		btnRegistroMatricula = new JButton("<html><b>3. Registro de Matrícula</b><br>"
				+ "<font size=2>Inscripción oficial de cursos por alumno</font></html>");
		btnRegistroMatricula.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistroMatricula.addActionListener(this);
		btnRegistroMatricula.setBounds(20, 180, 420, 55);
		btnRegistroMatricula.setFont(EstiloUI.FUENTE_BOTON);
		btnRegistroMatricula.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnRegistroMatricula.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		contentPane.add(btnRegistroMatricula);

		btnRegistroRetiro = new JButton("<html><b>4. Registro de Retiro</b><br>"
				+ "<font size=2>Retiro formal de asignaturas o ciclo</font></html>");
		btnRegistroRetiro.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistroRetiro.addActionListener(this);
		btnRegistroRetiro.setBounds(450, 180, 420, 55);
		contentPane.add(btnRegistroRetiro);

		btnConsulta = new JButton("<html><b>5. Consultas Generales</b><br>"
				+ "<font size=2>Búsqueda rápida por código, DNI o curso</font></html>");
		btnConsulta.setHorizontalAlignment(SwingConstants.LEFT);
		btnConsulta.addActionListener(this);
		btnConsulta.setBounds(20, 245, 420, 55);
		contentPane.add(btnConsulta);

		btnReporte = new JButton(
				"<html><b>6. Reporte</b><br>" + "<font size=2>Alumnos matriculados, vacantes y reportes</font></html>");
		btnReporte.setHorizontalAlignment(SwingConstants.LEFT);
		btnReporte.addActionListener(this);
		btnReporte.setBounds(450, 245, 420, 55);
		contentPane.add(btnReporte);

		pnlDatosProyecto = new JPanel();
		pnlDatosProyecto.setLayout(null);
		pnlDatosProyecto.setBorder(new TitledBorder(null,
				"Datos del Proyecto - Algoritmos y Estructura de Datos / POO II", TitledBorder.LEADING,
				TitledBorder.TOP, EstiloUI.FUENTE_TITULO_PANEL, EstiloUI.TEXTO_PRINCIPAL));
		pnlDatosProyecto.setBackground(EstiloUI.FONDO_VENTANA);
		pnlDatosProyecto.setBounds(20, 315, 860, 250);
		contentPane.add(pnlDatosProyecto);

		pnlInfoAcademica = new JPanel();
		pnlInfoAcademica.setLayout(null);
		pnlInfoAcademica.setBackground(Color.WHITE);
		pnlInfoAcademica.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL));
		pnlInfoAcademica.setBounds(15, 25, 270, 195);
		pnlDatosProyecto.add(pnlInfoAcademica);

		lblTituloInfo = new JLabel("Información Académica");
		lblTituloInfo.setFont(EstiloUI.FUENTE_TITULO_PANEL);
		lblTituloInfo.setForeground(EstiloUI.AZUL_CORPORATIVO);
		lblTituloInfo.setBounds(10, 8, 250, 18);
		pnlInfoAcademica.add(lblTituloInfo);

		lblInfoAcademica = new JLabel("<html>Curso: <b>Algoritmos y Estructura de<br>Datos</b><br>"
				+ "Ciclo Académico: <b>2026-II</b><br>" + "Institución: <b>Colegio América del Callao</b><br>"
				+ "Docente Guía: <b>Jaime Sixto Junco Yucra</b></html>");
		lblInfoAcademica.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblInfoAcademica.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblInfoAcademica.setBounds(10, 30, 250, 115);
		pnlInfoAcademica.add(lblInfoAcademica);

		pnlEquipo = new JPanel();
		pnlEquipo.setLayout(null);
		pnlEquipo.setBackground(Color.WHITE);
		pnlEquipo.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL));
		pnlEquipo.setBounds(295, 25, 270, 195);
		pnlDatosProyecto.add(pnlEquipo);

		lblTituloEquipo = new JLabel("Equipo de Desarrollo");
		lblTituloEquipo.setFont(EstiloUI.FUENTE_TITULO_PANEL);
		lblTituloEquipo.setForeground(EstiloUI.AZUL_CORPORATIVO);
		lblTituloEquipo.setBounds(10, 8, 250, 18);
		pnlEquipo.add(lblTituloEquipo);

		lblEquipo = new JLabel(
				"<html>&bull; Carlos Quispe Condori (Coordinador)<br>" + "&bull; María soledad Huillcas Yuto<br>"
						+ "&bull; Luis Jesus de la cruz Olave <br>" + "&bull; Erika Diana Vidal Capillo</html>");
		lblEquipo.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblEquipo.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblEquipo.setBounds(10, 30, 250, 115);
		pnlEquipo.add(lblEquipo);

		pnlEspecificaciones = new JPanel();
		pnlEspecificaciones.setLayout(null);
		pnlEspecificaciones.setBackground(Color.WHITE);
		pnlEspecificaciones.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL));
		pnlEspecificaciones.setBounds(575, 25, 270, 195);
		pnlDatosProyecto.add(pnlEspecificaciones);

		lblTituloEspec = new JLabel("Especificaciones Técnicas");
		lblTituloEspec.setFont(EstiloUI.FUENTE_TITULO_PANEL);
		lblTituloEspec.setForeground(EstiloUI.AZUL_CORPORATIVO);
		lblTituloEspec.setBounds(10, 8, 250, 18);
		pnlEspecificaciones.add(lblTituloEspec);

		lblEspecificaciones = new JLabel("<html>Arquitectura: <b>Modelo (clase) - Datos (arreglo) - Vista (gui)</b><br>"
				+ "Persistencia: <b>En memoria (ArrayList) - en desarrollo</b><br>"
				+ "GUI Framework: <b>Java Swing (JFrame / Eclipse)</b><br>"
				+ "JDK Requerido: <b>Java SE 21 LTS</b></html>");
		lblEspecificaciones.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblEspecificaciones.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		lblEspecificaciones.setBounds(10, 30, 250, 140);
		pnlEspecificaciones.add(lblEspecificaciones);

		lblProyectoFinal = new JLabel("Proyecto Final Integrador");
		lblProyectoFinal.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblProyectoFinal.setForeground(EstiloUI.TEXTO_SECUNDARIO);
		lblProyectoFinal.setBounds(10, 168, 150, 18);
		pnlEspecificaciones.add(lblProyectoFinal);

		btnSalir = new JButton("Salir del Sistema");
		btnSalir.addActionListener(this);
		btnSalir.setBounds(720, 570, 150, 25);
		btnSalir.setFont(EstiloUI.FUENTE_BOTON);
		btnSalir.setBackground(EstiloUI.FONDO_BOTON_PELIGRO);
		btnSalir.setForeground(EstiloUI.TEXTO_BOTON_PELIGRO);
		btnSalir.setBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_BOTON_PELIGRO));
		contentPane.add(btnSalir);

		lblEstado = new JLabel("Estado: Listo");
		lblEstado.setBounds(20, 575, 200, 14);
		contentPane.add(lblEstado);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnMantenimientoCurso || arg0.getSource() == mniMantenimientoCurso) {
			actionPerformedBtnMantenimientoCurso(arg0);
		}
		if (arg0.getSource() == btnMantenimientoAlumno || arg0.getSource() == mniMantenimientoAlumno) {
			actionPerformedBtnMantenimientoAlumno(arg0);
		}
		if (arg0.getSource() == btnRegistroMatricula || arg0.getSource() == mniRegistroMatricula) {
			actionPerformedBtnRegistroMatricula(arg0);
		}
		if (arg0.getSource() == btnRegistroRetiro || arg0.getSource() == mniRetiroAlumno) {
			actionPerformedBtnRegistroRetiro(arg0);
		}
		if (arg0.getSource() == btnConsulta) {
			actionPerformedBtnConsulta(arg0);
		}
		if (arg0.getSource() == btnReporte) {
			actionPerformedBtnReporte(arg0);
		}
		if (arg0.getSource() == btnSalir) {
			System.exit(0);
		}
	}

	protected void actionPerformedBtnMantenimientoCurso(ActionEvent arg0) {
		/**
		 * Abre la ventana de Mantenimiento de Cursos
		 */
		MantenimientoCurso frame = new MantenimientoCurso();
		frame.setVisible(true);
	}

	protected void actionPerformedBtnMantenimientoAlumno(ActionEvent arg0) {
		/**
		 * Abre la ventana de Mantenimiento de Alumnos
		 */
		/*
		 * MantenimientoCurso frame = new MantenimientoAlumno(); frame.setVisible(true);
		 */
		mensaje("Módulo de Mantenimiento Alumno: pendiente de integrar");
	}

	protected void actionPerformedBtnRegistroMatricula(ActionEvent arg0) {
		/**
		 * Abre la ventana de Registro de Matrícula (botón o menú Ctrl+M)
		 */
		RegistroMatricula frame = new RegistroMatricula();
		frame.setVisible(true);
	}

	protected void actionPerformedBtnRegistroRetiro(ActionEvent arg0) {
		/**
		 * Abre la ventana de Registro de Retiro (botón o menú Ctrl+R)
		 */
		RegistroRetiro frame = new RegistroRetiro();
		frame.setVisible(true);
	}

	protected void actionPerformedBtnConsulta(ActionEvent arg0) {
		/**
		 * Abre la ventana de Consultas Generales
		 */
		// Consultas frame = new Consultas();
		// frame.setVisible(true);
		mensaje("Módulo de Consultas: pendiente de integrar");
	}

	protected void actionPerformedBtnReporte(ActionEvent arg0) {
		/**
		 * Abre la ventana de Reportes y Listados
		 */
		Reporte frame = new Reporte();
		frame.setVisible(true);
	}

	void mensaje(String s) {
		javax.swing.JOptionPane.showMessageDialog(this, s);
	}

}
