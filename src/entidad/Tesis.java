package entidad;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Tesis {
	
	private int idTesis;
	private String titulo;
	private String tema;
	private Date fechaCreacion;
	private Timestamp fechaRegistro;
	private int estado; 
	private Alumno idAlumno;
	
	//INICIO atributos para el reporte
		SimpleDateFormat sdfFecini = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfFecreg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		public String getFormatoCreacion() {
			return sdfFecini.format(fechaCreacion);
		}
		public String getFormatoRegistro() {
			return sdfFecreg.format(fechaRegistro);
		}
		public String getFormatoEstado() {
			return estado == 1 ? "Activo":"Inactivo";
		}
		public String getFormatoAlumno() {
			return idAlumno.getNombres();
		}
		//FIN atributos para el reporte
	
	
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
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Timestamp fechaRegistro) {
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
