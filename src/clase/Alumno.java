package clase;

public class Alumno {
	private String codAlumno, nombres, apellidos, dni;
	private int edad, celular, estado;

	public Alumno(String codAlumno, String nombres, String apellidos, String dni, int edad, int celular) {
		this.codAlumno = codAlumno;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.edad = edad;
		this.celular = celular;
		this.estado = 0;
	}

	public String getCodAlumno() {
		return codAlumno;
	}

	public String getNombres() {
		return nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDni() {
		return dni;
	}

	public int getEdad() {
		return edad;
	}

	public int getCelular() {
		return celular;
	}

	public int getEstado() {
		return estado;
	}

	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	//  Operaciones públicas
	public String nombreCompleto() {
		return nombres + " " + apellidos;
	}

	public String getEstadoTexto() {
		switch (estado) {
		case 0:
			return "Registrado";
		case 1:
			return "Matriculado";
		case 2:
			return "Retirado";
		default:
			return "Desconocido";
		}
	}
}
