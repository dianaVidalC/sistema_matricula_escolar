package clase;

public class Curso {
	private String codCurso, asignatura;
	private int ciclo, creditos, horas;

	public Curso(String codCurso, String asignatura, int ciclo, int creditos, int horas) {
		this.codCurso = codCurso;
		this.asignatura = asignatura;
		this.ciclo = ciclo;
		this.creditos = creditos;
		this.horas = horas;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public int getCiclo() {
		return ciclo;
	}

	public int getCreditos() {
		return creditos;
	}

	public int getHoras() {
		return horas;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}

	@Override
	public String toString() {
	    return codCurso + " - " + asignatura;
	}
}
