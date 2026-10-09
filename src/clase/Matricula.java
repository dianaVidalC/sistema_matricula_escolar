package clase;

public class Matricula {
	private String numMatricula, codAlumno, codCurso, fecha, hora;

	public Matricula(String numMatricula, String codAlumno, String codCurso, String fecha, String hora) {
		this.numMatricula = numMatricula;
		this.codAlumno = codAlumno;
		this.codCurso = codCurso;
		this.fecha = fecha;
		this.hora = hora;
	}

	public String getNumMatricula() {
		return numMatricula;
	}

	public String getCodAlumno() {
		return codAlumno;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public String getFecha() {
		return fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setNumMatricula(String numMatricula) {
		this.numMatricula = numMatricula;
	}

	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
}
