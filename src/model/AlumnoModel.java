package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Alumno;
import entidad.Pais;
import util.MySqlDBConexion;

public class AlumnoModel {
	private static Logger log = Logger.getLogger(AlumnoModel.class.getName());

	public int insertaAlumno(Alumno obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "insert into alumno value(null,?,?,?,?,?,?,curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setString(3, obj.getTelefono());
			pstm.setString(4, obj.getDni());
			pstm.setString(5, obj.getCorreo());
			pstm.setDate(6, obj.getFechaNacimiento());
			pstm.setInt(7, obj.getEstado());
			pstm.setInt(8, obj.getPais().getIdPais());

			log.info(">>> " + pstm);

			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();

			} catch (Exception e2) {
			}

		}

		return salida;
	}

	public int actualizaAlumno(Alumno a) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update Alumno set nombres=?, apellidos=?, telefono=?, Dni=?, correo=?, fechaNacimiento=?, estado=?, idPais=? where idAlumno=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, a.getNombres());
			pstm.setString(2, a.getApellidos());
			pstm.setString(3, a.getTelefono());
			pstm.setString(4, a.getDni());
			pstm.setString(5, a.getCorreo());
			pstm.setDate(6, a.getFechaNacimiento());
			pstm.setInt(7, a.getEstado());
			pstm.setInt(8, a.getPais().getIdPais());
			pstm.setInt(9, a.getIdAlumno());
			log.info(">>> " + pstm);
			actualizados = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return actualizados;
	}

	public int eliminaAlumno(int idAlumno) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql = "delete from alumno where idAlumno=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idAlumno);
			log.info(">>> " + pstm);
			eliminados = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eliminados;
	}

	public List<Alumno> listaAlumno() {
		ArrayList<Alumno> salida = new ArrayList<Alumno>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; // Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "SELECT a.*, p.nombre FROM alumno a\r\n"
					+ " inner join pais p on a.idPais = p.idPais";
			pstm = con.prepareStatement(sql);
			log.info(">>> " + pstm);

			// En rs se trae los datos de la BD segun el SQL
			rs = pstm.executeQuery();

			// Se pasa la data del rs al ArrayList(data)
			Alumno 	a = null;
			Pais 	p = null;
			while (rs.next()) {
				a = new Alumno();
				// Se colocan los campos de la base de datos
				a.setIdAlumno(rs.getInt(1));
				a.setNombres(rs.getString(2));
				a.setApellidos(rs.getString(3));
				a.setTelefono(rs.getString(4));
				a.setDni(rs.getString(5));
				a.setCorreo(rs.getString(6));
				a.setFechaNacimiento(rs.getDate(7));
				a.setFechaRegistro(rs.getTimestamp(8));
				a.setEstado(rs.getInt(9));
				
				p = new Pais();
				p.setIdPais(rs.getInt(10));
				p.setNombre(rs.getString(11));
				a.setPais(p);
				salida.add(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				
			}
		}
		return salida;
	}
	
	public List<Alumno> listaPorPais(int idPais){
		ArrayList<Alumno> salida = new ArrayList<Alumno>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT a.*, p.nombre FROM Alumno a inner join Pais p on a.idPais = p.idPais where a.idPais = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idPais);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Alumno objAlumno = null;
			Pais objPais = null;
			while(rs.next()) {
				objAlumno = new Alumno();
				objAlumno.setIdAlumno(rs.getInt(1));
				objAlumno.setNombres(rs.getString(2));
				objAlumno.setApellidos(rs.getString(3));
				objAlumno.setTelefono(rs.getString(4));
				objAlumno.setDni(rs.getString(5));
				objAlumno.setCorreo(rs.getString(6));
				objAlumno.setFechaNacimiento(rs.getDate(7));
				objAlumno.setFechaRegistro(rs.getTimestamp(8));
				objAlumno.setEstado(rs.getInt(9));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				objAlumno.setPais(objPais);
				salida.add(objAlumno);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
	
	public List<Alumno> listaPorRangoFechas(Date fecIni, Date fecFin){
		ArrayList<Alumno> salida = new ArrayList<Alumno>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT a.*, p.nombre FROM alumno a inner join pais p on a.idPais = p.idPais where a.fechaNacimiento between ? and ? "; 
			psmt = conn.prepareStatement(sql);
			psmt.setDate(1, fecIni);
			psmt.setDate(2, fecFin);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Alumno objAlumno = null;
			Pais objPais = null;
			while(rs.next()) {
				objAlumno = new Alumno();
				objAlumno.setIdAlumno(rs.getInt(1));
				objAlumno.setNombres(rs.getString(2));
				objAlumno.setApellidos(rs.getString(3));
				objAlumno.setTelefono(rs.getString(4));
				objAlumno.setDni(rs.getString(5));
				objAlumno.setCorreo(rs.getString(6));
				objAlumno.setFechaNacimiento(rs.getDate(7));
				objAlumno.setFechaRegistro(rs.getTimestamp(8));
				objAlumno.setEstado(rs.getInt(9));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				objAlumno.setPais(objPais);
				salida.add(objAlumno);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}
}
