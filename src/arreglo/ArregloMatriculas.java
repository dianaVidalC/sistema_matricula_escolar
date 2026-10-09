package arreglo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import clase.Matricula;

public class ArregloMatriculas {
	private ArrayList<Matricula> mat;
	private int correlativo;
	private static final String ARCHIVO = "matriculas.txt";

	public ArregloMatriculas() {
		mat = new ArrayList<Matricula>();
		correlativo = 1001;

		cargarDesdeArchivo();
	}

//  Operaciones públicas básicas
	public void adicionar(Matricula x) {
		mat.add(x);
		actualizarCorrelativo(x.getNumMatricula());
		guardarEnArchivo();
	}

	public int tamaño() {
		return mat.size();
	}

	public Matricula obtener(int i) {
		return mat.get(i);
	}

	public Matricula buscar(String numMatricula) {
		for (int i = 0; i < tamaño(); i++)
			if (obtener(i).getNumMatricula().equalsIgnoreCase(numMatricula))
				return obtener(i);
		return null;
	}

	public boolean existeMatriculaActiva(String codAlumno) {
		for (int i = 0; i < tamaño(); i++)
			if (obtener(i).getCodAlumno().equalsIgnoreCase(codAlumno))
				return true;
		return false;
	}

	public void eliminar(Matricula x) {
		mat.remove(x);
		guardarEnArchivo();
	}

	public String siguienteCodigo() {
		return "MAT-" + correlativo;
	}
	
	private void guardarEnArchivo() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO))) {
			for (Matricula m : mat) {
				pw.println(m.getNumMatricula() + ";" + m.getCodAlumno() + ";" + m.getCodCurso() + ";"
						+ m.getFecha() + ";" + m.getHora());
			}
		} catch (IOException e) {
			System.out.println("Error al guardar matrículas: " + e.getMessage());
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
				if (partes.length == 5) {
					Matricula m = new Matricula(partes[0], partes[1], partes[2], partes[3], partes[4]);
					mat.add(m);
					actualizarCorrelativo(partes[0]);
				}
			}
		} catch (IOException e) {
			System.out.println("Error al cargar matrículas: " + e.getMessage());
		}
	}

	private void actualizarCorrelativo(String numMatricula) {
		try {
			int num = Integer.parseInt(numMatricula.replace("MAT-", ""));
			if (num >= correlativo) {
				correlativo = num + 1;
			}
		} catch (NumberFormatException e) {
			// si el código no sigue el formato esperado, se ignora
		}
	}

	public void actualizar() {
		guardarEnArchivo();
	}
}
