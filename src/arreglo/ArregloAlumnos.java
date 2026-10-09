package arreglo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import clase.Alumno;

public class ArregloAlumnos {
	private ArrayList<Alumno> alumnos;
	private int correlativo;
	private static final String ARCHIVO = "alumnos.txt";

	public ArregloAlumnos() {
		alumnos = new ArrayList<Alumno>();
		correlativo = 202010001;
		cargarDesdeArchivo();
	}

	public void adicionar(Alumno x) {
		alumnos.add(x);
		actualizarCorrelativo(x.getCodAlumno());
		guardarEnArchivo();
	}

	public int tamaño() {
		return alumnos.size();
	}

	public Alumno obtener(int i) {
		return alumnos.get(i);
	}

	public Alumno buscar(String codAlumno) {
		for (int i = 0; i < tamaño(); i++) {
			Alumno a = obtener(i);
			if (a.getCodAlumno().equalsIgnoreCase(codAlumno)) {
				return a;
			}
		}
		return null;
	}

	public void eliminar(Alumno x) {
		alumnos.remove(x);
		guardarEnArchivo();
	}

	public void actualizar() {
		guardarEnArchivo();
	}

	public String siguienteCodigo() {
		return String.valueOf(correlativo);
	}

	private void guardarEnArchivo() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
			for (Alumno a : alumnos) {
				pw.println(a.getCodAlumno() + ";" + 
						   a.getNombres() + ";" + 
						   a.getApellidos() + ";" + 
						   a.getDni() + ";" + 
						   a.getEdad() + ";" + 
						   a.getCelular() + ";" + 
						   a.getEstado());
			}
		} catch (IOException e) {
			System.out.println("Error al guardar alumnos: " + e.getMessage());
		}
	}

	private void cargarDesdeArchivo() {
		File archivo = new File(ARCHIVO);
		if (!archivo.exists()) {
			return;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (linea.trim().isEmpty()) continue;
				String[] partes = linea.split(";");
				if (partes.length == 7) {
					String cod = partes[0].trim();
					String nombres = partes[1].trim();
					String apellidos = partes[2].trim();
					String dni = partes[3].trim();
					int edad = Integer.parseInt(partes[4]);
					int celular = Integer.parseInt(partes[5]);
					int estado = Integer.parseInt(partes[6]);

					Alumno a = new Alumno(cod, nombres, apellidos, dni, edad, celular);
					a.setEstado(estado);
					
					alumnos.add(a);
					actualizarCorrelativo(cod);
				}
			}
		} catch (IOException e) {
			System.out.println("Error al cargar alumnos: " + e.getMessage());
		}
	}

	private void actualizarCorrelativo(String codigo) {
		try {
			int num = Integer.parseInt(codigo);
			if (num >= correlativo) {
				correlativo = num + 1;
			}
		} catch (NumberFormatException e) {
			// Si el código no es puramente numérico, se ignora
		}
	}
}