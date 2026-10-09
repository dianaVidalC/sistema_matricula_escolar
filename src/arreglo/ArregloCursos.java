package arreglo;

import java.util.ArrayList;

import clase.Curso;

public class ArregloCursos {
	private ArrayList<Curso> cur;

	public ArregloCursos() {
		cur = new ArrayList<Curso>();

		adicionar(new Curso("CUR-101", "Programación Orientada a Objetos II", 1, 4, 6));
		adicionar(new Curso("CUR-102", "Base de Datos Avanzada", 1, 4, 5));
		adicionar(new Curso("CUR-103", "Algoritmos y Estructura de Datos", 1, 4, 6));
		adicionar(new Curso("CUR-104", "Fundamentos de Gestión Empresarial", 0, 2, 2));
		adicionar(new Curso("CUR-105", "Introducción a la Algoritmia", 0, 6, 6));
		adicionar(new Curso("CUR-106", "Matemática I", 0, 3, 4));
	}

//  Operaciones públicas básicas
	public void adicionar(Curso x) {
		cur.add(x);
	}

	public int tamaño() {
		return cur.size();
	}

	public Curso obtener(int i) {
		return cur.get(i);
	}

	public Curso buscar(String codCurso) {
		for (int i = 0; i < tamaño(); i++)
			if (obtener(i).getCodCurso().equalsIgnoreCase(codCurso))
				return obtener(i);
		return null;
	}

	public void eliminar(Curso x) {
		cur.remove(x);
	}
	
	public void ordenarPorCodigo() {
		for (int i = 0; i < tamaño() - 1; i++) {
			for (int j = i + 1; j < tamaño(); j++) {
				if (obtener(i).getCodCurso().compareToIgnoreCase(obtener(j).getCodCurso()) > 0) {
					intercambiar(i, j);
				}
			}
		}
	}

	private void intercambiar(int pos1, int pos2) {
		Curso aux = cur.get(pos1);
		cur.set(pos1, cur.get(pos2));
		cur.set(pos2, aux);
	}
}
