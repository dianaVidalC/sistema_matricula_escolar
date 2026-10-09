package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.TableColumnModel;

import arreglo.ArregloCursos;
import clase.Curso;
import util.EstiloUI;
import java.awt.Color;

public class MantenimientoCurso extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCodigo, txtAsignatura, txtCreditos, txtHoras, txtFiltro;
	private JComboBox<String> cboCiclo;
	private JTable tblTabla;
	private DefaultTableModel modelo;
	private JButton btnAdicionar, btnModificar, btnEliminar, btnLimpiar, btnBuscar, btnListarTodos;
	private JScrollPane scrollPane;
	private JLabel lblContador;

	ArregloCursos ac = new ArregloCursos();

	public MantenimientoCurso() {
		setTitle("Mantenimiento de Cursos - Colegio América del Callao High School");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 601);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(EstiloUI.FONDO_VENTANA);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 834, 30);
		toolBar.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		contentPane.add(toolBar);

		JButton btnNuevoTb = new JButton("Nuevo");
		btnNuevoTb.setIcon(
				new ImageIcon(MantenimientoCurso.class.getResource("/recursos/Jackietran-Rounded-Add-Green.16.png")));
		btnNuevoTb.addActionListener(e -> limpieza());
		toolBar.add(btnNuevoTb);

		JButton btnGuardarTb = new JButton("Guardar");
		btnGuardarTb.setIcon(
				new ImageIcon(MantenimientoCurso.class.getResource("/recursos/Fatcow-Farm-Fresh-Diskette.16.png")));
		btnGuardarTb.addActionListener(e -> actionPerformedBtnAdicionar(null));
		toolBar.add(btnGuardarTb);

		JButton btnActualizarTb = new JButton("Actualizar");
		btnActualizarTb.setIcon(new ImageIcon(MantenimientoCurso.class
				.getResource("/recursos/Graphicloads-Colorful-Long-Shadow-Arrow-reload.16.png")));
		btnActualizarTb.addActionListener(e -> listar());
		toolBar.add(btnActualizarTb);

		toolBar.add(Box.createHorizontalGlue());

		JLabel lblCicloTool = new JLabel("Ciclo Lectivo: 2026-II  ");
		lblCicloTool.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblCicloTool.setForeground(EstiloUI.AZUL_CORPORATIVO);
		toolBar.add(lblCicloTool);

		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(EstiloUI.FONDO_VENTANA);
		panelDatos.setBorder(new TitledBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL),
				"Datos del Curso", TitledBorder.LEADING, TitledBorder.TOP, EstiloUI.FUENTE_TITULO_PANEL,
				EstiloUI.AZUL_CORPORATIVO));
		panelDatos.setBounds(10, 40, 810, 175);
		contentPane.add(panelDatos);
		panelDatos.setLayout(null);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblCodigo.setBounds(40, 30, 60, 20);
		panelDatos.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtCodigo.setBackground(EstiloUI.FONDO_VENTANA);
		txtCodigo.setForeground(EstiloUI.AZUL_CORPORATIVO);

		txtCodigo.setBounds(110, 30, 100, 24);
		panelDatos.add(txtCodigo);

		JLabel lblAuto = new JLabel("(4 dígitos)");
		lblAuto.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblAuto.setForeground(EstiloUI.TEXTO_SECUNDARIO);
		lblAuto.setBounds(220, 30, 100, 20);
		panelDatos.add(lblAuto);

		JLabel lblCiclo = new JLabel("Ciclo:");
		lblCiclo.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblCiclo.setBounds(460, 30, 40, 20);
		panelDatos.add(lblCiclo);

		cboCiclo = new JComboBox<String>();
		cboCiclo.setFont(EstiloUI.FUENTE_ETIQUETA);
		cboCiclo.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Ciclo I", "Ciclo II", "Ciclo III", "Ciclo IV", "Ciclo V", "Ciclo VI" }));
		cboCiclo.setBounds(510, 30, 180, 24);
		panelDatos.add(cboCiclo);

		JLabel lblAsignatura = new JLabel("Asignatura:");
		lblAsignatura.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblAsignatura.setBounds(20, 70, 80, 20);
		panelDatos.add(lblAsignatura);

		txtAsignatura = new JTextField();
		txtAsignatura.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtAsignatura.setBounds(110, 70, 580, 24);
		panelDatos.add(txtAsignatura);

		JLabel lblCreditos = new JLabel("Créditos:");
		lblCreditos.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblCreditos.setBounds(30, 110, 60, 20);
		panelDatos.add(lblCreditos);

		txtCreditos = new JTextField();
		txtCreditos.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtCreditos.setBounds(110, 110, 86, 24);
		panelDatos.add(txtCreditos);

		JLabel lblValorEntero = new JLabel("(Valor entero)");
		lblValorEntero.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblValorEntero.setForeground(EstiloUI.TEXTO_SECUNDARIO);
		lblValorEntero.setBounds(205, 110, 100, 20);
		panelDatos.add(lblValorEntero);

		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblHoras.setBounds(455, 110, 46, 20);
		panelDatos.add(lblHoras);

		txtHoras = new JTextField();
		txtHoras.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtHoras.setBounds(510, 110, 86, 24);
		panelDatos.add(txtHoras);

		JLabel lblHorasSemanales = new JLabel("(Horas Semanales)");
		lblHorasSemanales.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblHorasSemanales.setForeground(EstiloUI.TEXTO_SECUNDARIO);
		lblHorasSemanales.setBounds(605, 110, 120, 20);
		panelDatos.add(lblHorasSemanales);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(
				MantenimientoCurso.class.getResource("/recursos/Pictogrammers-Material-Light-Grid.16.png")));
		btnAdicionar.setFont(EstiloUI.FUENTE_BOTON);
		btnAdicionar.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnAdicionar.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		btnAdicionar.setFocusPainted(false);
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(140, 142, 110, 25);
		panelDatos.add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(MantenimientoCurso.class
				.getResource("/recursos/Oxygen-Icons.org-Oxygen-Actions-document-edit.16.png")));
		btnModificar.setFont(EstiloUI.FUENTE_BOTON);
		btnModificar.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnModificar.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		btnModificar.setFocusPainted(false);
		btnModificar.addActionListener(this);
		btnModificar.setBounds(260, 142, 110, 25);
		panelDatos.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(
				new ImageIcon(MantenimientoCurso.class.getResource("/recursos/Iconshock-Vista-General-Trash.16.png")));
		btnEliminar.setFont(EstiloUI.FUENTE_BOTON);
		btnEliminar.setBackground(EstiloUI.FONDO_BOTON_PELIGRO);
		btnEliminar.setForeground(EstiloUI.TEXTO_BOTON_PELIGRO);
		btnEliminar.setFocusPainted(false);
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(380, 142, 110, 25);
		panelDatos.add(btnEliminar);

		btnLimpiar = new JButton("Limpiar / Nuevo");
		btnLimpiar.setIcon(new ImageIcon(
				MantenimientoCurso.class.getResource("/recursos/Google-Noto-Emoji-Objects-62911-paintbrush.16.png")));
		btnLimpiar.setFont(EstiloUI.FUENTE_BOTON);
		btnLimpiar.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnLimpiar.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		btnLimpiar.setFocusPainted(false);
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(500, 142, 159, 25);
		panelDatos.add(btnLimpiar);

		JPanel panelTabla = new JPanel();
		panelTabla.setBackground(EstiloUI.FONDO_VENTANA);
		panelTabla.setBorder(new TitledBorder(javax.swing.BorderFactory.createLineBorder(EstiloUI.BORDE_PANEL),
				"Listado Maestro de Cursos", TitledBorder.LEADING, TitledBorder.TOP, EstiloUI.FUENTE_TITULO_PANEL,
				EstiloUI.AZUL_CORPORATIVO));
		panelTabla.setBounds(10, 225, 810, 302);
		contentPane.add(panelTabla);
		panelTabla.setLayout(null);

		JLabel lblFiltrar = new JLabel("Filtrar:");
		lblFiltrar.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblFiltrar.setBounds(20, 30, 45, 20);
		panelTabla.add(lblFiltrar);

		txtFiltro = new JTextField();
		txtFiltro.setFont(EstiloUI.FUENTE_ETIQUETA);
		txtFiltro.setBounds(70, 28, 180, 24);
		panelTabla.add(txtFiltro);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(
				new ImageIcon(MantenimientoCurso.class.getResource("/recursos/Gakuseisean-Aire-Search.16.png")));
		btnBuscar.setFont(EstiloUI.FUENTE_BOTON);
		btnBuscar.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnBuscar.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		btnBuscar.setFocusPainted(false);
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(260, 28, 100, 24);
		panelTabla.add(btnBuscar);

		btnListarTodos = new JButton("Listar Todos");
		btnListarTodos.setIcon(
				new ImageIcon(MantenimientoCurso.class.getResource("/recursos/Vexels-Office-Clipboard.16.png")));
		btnListarTodos.setFont(EstiloUI.FUENTE_BOTON);
		btnListarTodos.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnListarTodos.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		btnListarTodos.setFocusPainted(false);
		btnListarTodos.addActionListener(this);
		btnListarTodos.setBounds(510, 28, 125, 24);
		panelTabla.add(btnListarTodos);

		JButton btnExportar = new JButton("Exportar");
		btnExportar.setIcon(new ImageIcon(
				MantenimientoCurso.class.getResource("/recursos/Dtafalonso-Android-Lollipop-Downloads.16.png")));
		btnExportar.setFont(EstiloUI.FUENTE_BOTON);
		btnExportar.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		btnExportar.setForeground(EstiloUI.TEXTO_PRINCIPAL);
		btnExportar.setFocusPainted(false);
		btnExportar.setBounds(645, 28, 140, 24);

		btnExportar.addActionListener(e -> {
			try {
				java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter("cursos.txt"));
				for (int i = 0; i < ac.tamaño(); i++) {
					Curso x = ac.obtener(i);

					pw.println(x.getCodCurso() + ";" + x.getAsignatura() + ";" + x.getCiclo() + ";" + x.getCreditos()
							+ ";" + x.getHoras());
				}
				pw.close();
				mensaje("Archivo cursos.txt exportado correctamente.");
			} catch (Exception ex) {
				mensaje("Error al exportar: " + ex.getMessage());
			}
		});

		panelTabla.add(btnExportar);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 65, 765, 200);
		panelTabla.add(scrollPane);

		tblTabla = new JTable();
		tblTabla.setFont(EstiloUI.FUENTE_TABLA);
		tblTabla.setRowHeight(22);
		tblTabla.setSelectionBackground(EstiloUI.AZUL_FILA_SELECCIONADA);
		tblTabla.setSelectionForeground(EstiloUI.TEXTO_SOBRE_AZUL);
		tblTabla.setFillsViewportHeight(true);

		modelo = new DefaultTableModel(new Object[][] {},
				new String[] { "Cód. Curso", "Asignatura", "Ciclo", "Créditos", "Horas" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblTabla.setModel(modelo);
		scrollPane.setViewportView(tblTabla);

		tblTabla.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				editarFila();
			}
		});

		JLabel lblAyuda = new JLabel("* Haga clic en cualquier fila para cargar los datos en el formulario.");
		lblAyuda.setFont(EstiloUI.FUENTE_ETIQUETA);
		lblAyuda.setForeground(EstiloUI.TEXTO_SECUNDARIO);
		lblAyuda.setBounds(20, 275, 450, 20);
		panelTabla.add(lblAyuda);

		JLabel lblPrueba = new JLabel("●");
		lblPrueba.setForeground(new Color(0, 128, 0));
		lblPrueba.setBounds(20, 538, 14, 14);
		contentPane.add(lblPrueba);

		JLabel lblListo = new JLabel("Listo | Registros de Cursos:");
		lblListo.setForeground(new Color(128, 128, 128));
		lblListo.setBounds(33, 538, 136, 14);
		contentPane.add(lblListo);

		lblContador = new JLabel("0");
		lblContador.setForeground(new Color(128, 128, 128));
		lblContador.setBounds(168, 538, 14, 14);
		contentPane.add(lblContador);

		ajustarAnchoColumnas();
		listar();
		limpieza();
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnLimpiar) {
			limpieza();
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == btnListarTodos) {
			listar();
		}
	}

	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		String cod = txtCodigo.getText().trim();

		if (esCodigoValido(cod)) {
			if (ac.buscar(cod) == null) {
				String asig = leerAsignatura();
				if (asig.length() > 0) {
					try {
						int cred = leerCreditos();
						try {
							int horas = leerHoras();
							int ciclo = leerPosCiclo();

							Curso nuevo = new Curso(cod, asig, ciclo, cred, horas);
							ac.adicionar(nuevo);
							ac.ordenarPorCodigo();

							listar();
							limpieza();
							mensaje("Curso registrado y ordenado correctamente.");

						} catch (Exception x) {
							error("Ingrese una cantidad de HORAS correcta (número entero)", txtHoras);
						}
					} catch (Exception x) {
						error("Ingrese una cantidad de CRÉDITOS correcta (número entero)", txtCreditos);
					}
				} else {
					error("Ingrese un nombre de ASIGNATURA correcto", txtAsignatura);
				}
			} else {
				error("El CÓDIGO ingresado ya existe. Evite códigos repetidos.", txtCodigo);
			}
		} else {
			error("Ingrese un CÓDIGO válido de exactamente 4 dígitos (ej: 1024).", txtCodigo);
		}
	}

	boolean esCodigoValido(String cod) {

		if (cod.length() != 4) {
			return false;
		}

		for (int i = 0; i < cod.length(); i++) {
			char c = cod.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		String cod = txtCodigo.getText();
		Curso x = ac.buscar(cod);
		if (x != null) {
			String asig = leerAsignatura();
			if (asig.length() > 0) {
				try {
					int cred = leerCreditos();
					try {
						int horas = leerHoras();
						int ciclo = leerPosCiclo();

						x.setAsignatura(asig);
						x.setCiclo(ciclo);
						x.setCreditos(cred);
						x.setHoras(horas);

						listar();
						limpieza();
						mensaje("Curso modificado correctamente.");

					} catch (Exception ex) {
						error("Ingrese una cantidad de HORAS correcta (número entero)", txtHoras);
					}
				} catch (Exception ex) {
					error("Ingrese una cantidad de CRÉDITOS correcta (número entero)", txtCreditos);
				}
			} else {
				error("Ingrese un nombre de ASIGNATURA correcto", txtAsignatura);
			}
		} else {
			mensaje("Debe seleccionar un curso válido de la tabla para modificar.");
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		String cod = txtCodigo.getText();
		Curso x = ac.buscar(cod);
		if (x != null) {
			int ok = confirmar("¿Desea eliminar el curso seleccionado?");
			if (ok == JOptionPane.YES_OPTION) {
				ac.eliminar(x);
				listar();
				limpieza();
				mensaje("Curso eliminado correctamente.");
			}
		} else {
			mensaje("Debe seleccionar un curso válido de la tabla para eliminar.");
		}
	}

	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		String filtro = txtFiltro.getText().trim();
		Curso x = ac.buscar(filtro);
		if (x != null) {
			modelo.setRowCount(0);
			agregarFilaM(x);
		} else {
			mensaje("No se encontró el CÓDIGO de curso: " + filtro);
			txtFiltro.setText("");
			txtFiltro.requestFocus();
		}
	}

	void limpieza() {
		txtCodigo.setText("");
		txtCodigo.setEditable(true);
		txtCodigo.setBackground(EstiloUI.FONDO_CAJA_TEXTO);
		txtAsignatura.setText("");
		cboCiclo.setSelectedIndex(0);
		txtCreditos.setText("");
		txtHoras.setText("");
		txtFiltro.setText("");
		txtCodigo.requestFocus();
		tblTabla.clearSelection();
	}

	void listar() {
		modelo.setRowCount(0);
		for (int i = 0; i < ac.tamaño(); i++) {
			agregarFilaM(ac.obtener(i));
		}

		actualizarEstado();
	}

	void actualizarEstado() {
		if (lblContador != null) {
			lblContador.setText(String.valueOf(ac.tamaño()));
		}
	}

	void editarFila() {
		if (ac.tamaño() == 0) {
			limpieza();
		} else {
			int fila = tblTabla.getSelectedRow();
			if (fila >= 0) {
				String cod = modelo.getValueAt(fila, 0).toString();
				Curso x = ac.buscar(cod);
				if (x != null) {
					txtCodigo.setText(x.getCodCurso());
					txtCodigo.setEditable(false);
					txtCodigo.setBackground(EstiloUI.FONDO_VENTANA);

					txtAsignatura.setText(x.getAsignatura());
					cboCiclo.setSelectedIndex(x.getCiclo());
					txtCreditos.setText("" + x.getCreditos());
					txtHoras.setText("" + x.getHoras());
				}
			}
		}
	}

	private void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblTabla.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(80);
		tcm.getColumn(1).setPreferredWidth(280);
		tcm.getColumn(2).setPreferredWidth(70);
		tcm.getColumn(3).setPreferredWidth(60);
		tcm.getColumn(4).setPreferredWidth(60);
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", JOptionPane.INFORMATION_MESSAGE);
	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}

	void agregarFilaM(Curso c) {
		Object[] fila = { c.getCodCurso(), c.getAsignatura(), enTextoCiclo(c.getCiclo()), c.getCreditos(),
				c.getHoras() };
		modelo.addRow(fila);
	}

	String leerAsignatura() {
		return txtAsignatura.getText().trim();
	}

	int leerCreditos() {
		return Integer.parseInt(txtCreditos.getText().trim());
	}

	int leerHoras() {
		return Integer.parseInt(txtHoras.getText().trim());
	}

	int leerPosCiclo() {
		return cboCiclo.getSelectedIndex();
	}

	String enTextoCiclo(int i) {
		if (i >= 0 && i < cboCiclo.getItemCount()) {
			return cboCiclo.getItemAt(i);
		}
		return "Desconocido";
	}

	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
}
