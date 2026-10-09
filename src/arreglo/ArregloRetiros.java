package arreglo;

import clase.Retiro;
import java.util.ArrayList;

public class ArregloRetiros {

	private ArrayList<Retiro> ret;
	private int correlativo;

	public ArregloRetiros() {
		ret = new ArrayList<Retiro>();
		correlativo = 20001;
	}

	// Operaciones públicas básicas
	public void adicionar(Retiro x) {
		ret.add(x);
		correlativo++;
	}

	public int tamaño() {
		return ret.size();
	}

	public Retiro obtener(int i) {
		return ret.get(i);
	}

	public Retiro buscar(String numRetiro) {
		for (int i = 0; i < tamaño(); i++)
			if (obtener(i).getNumRetiro().equalsIgnoreCase(numRetiro))
				return obtener(i);
		return null;
	}

	public boolean existeRetiro(String numMatricula) {
		for (int i = 0; i < tamaño(); i++)
			if (obtener(i).getNumMatricula().equalsIgnoreCase(numMatricula))
				return true;
		return false;
	}

	public void eliminar(Retiro x) {
		ret.remove(x);
	}

	public String siguienteCodigo() {
		return "RET-" + correlativo;
	}

}