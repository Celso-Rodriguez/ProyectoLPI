package entidad;

import java.sql.Date;

public class Tesis {
	
	private int idTesis;
	private String titulo;
	private String tema;
	private Date fechaCreacion;
	private Date fechaRegistro;
	private int estado; private Alumno idAlumno;
	

	public int getIdTesis() {
		return idTesis;
	}
	public void setIdTesis(int idTesis) {
		this.idTesis = idTesis;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Alumno getAlumno() {
		return idAlumno;
	}
	public void setAlumno(Alumno idAlumno) {
		this.idAlumno = idAlumno;
	}
	

}
