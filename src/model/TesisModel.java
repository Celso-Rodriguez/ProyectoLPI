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
import entidad.Tesis;
import util.MySqlDBConexion;

public class TesisModel {
	private static Logger log = Logger.getLogger(TesisModel.class.getName());

	public int insertaTesis(Tesis obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "insert into tesis value(null,?,?,?,curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setString(2, obj.getTema());
			pstm.setDate(3, obj.getFechaCreacion());
			pstm.setInt(4, obj.getEstado());
			pstm.setInt(5, obj.getAlumno().getIdAlumno());
			
			log.info(">>>> " + pstm);
			
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
	
	
	
	public int actualizaTesis(Tesis a) {
		int actualizados = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update Tesis set titulo=?, tema=?, fechaCreacion=?, estado=?, idAlumno=? where idTesis=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, a.getTitulo());
			pstm.setString(2, a.getTema());
			pstm.setDate(3, a.getFechaCreacion());
			pstm.setInt(4, a.getEstado());
			pstm.setInt(5, a.getAlumno().getIdAlumno());
			pstm.setInt(6, a.getIdTesis());
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

	
	public int eliminaTesis(int idTesis) {
		int eliminados = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql = "delete from tesis where idTesis=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idTesis);
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

	public List<Tesis> listaTesis() {
		ArrayList<Tesis> salida = new ArrayList<Tesis>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; 
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "SELECT t.*, a.nombres, a.apellidos FROM Tesis t inner join alumno a on t.idAlumno = a.idAlumno";
			pstm = con.prepareStatement(sql);
			log.info(">>> " + pstm);

			rs = pstm.executeQuery();

			Tesis 	a = null;
			Alumno 	p = null;
			while (rs.next()) {
				a = new Tesis();

				a.setIdTesis(rs.getInt(1));
				a.setTitulo(rs.getString(2));
				a.setTema(rs.getString(3));
				a.setFechaCreacion(rs.getDate(4));
				a.setFechaRegistro(rs.getTimestamp(5));
				a.setEstado(rs.getInt(6));
				
				p = new Alumno();
				p.setIdAlumno(rs.getInt(7));
				p.setNombres(rs.getString(8)+" "+ rs.getString(9));
				a.setAlumno(p);
				salida.add(a);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	
	public List<Tesis> listaPorAlumno(int idAlumno){
		ArrayList<Tesis> salida = new ArrayList<Tesis>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT t.*, a.nombres FROM Tesis t inner join alumno a on t.idAlumno = a.idAlumno where t.idAlumno = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idAlumno);
			
			log.info(">>> " + psmt);
		
			rs = psmt.executeQuery();

			Tesis 	a = null;
			Alumno 	p = null;
			while (rs.next()) {
				a = new Tesis();

				a.setIdTesis(rs.getInt(1));
				a.setTitulo(rs.getString(2));
				a.setTema(rs.getString(3));
				a.setFechaCreacion(rs.getDate(4));
				a.setFechaRegistro(rs.getTimestamp(5));
				a.setEstado(rs.getInt(6));
				
				p = new Alumno();
				p.setIdAlumno(rs.getInt(7));
				p.setNombres(rs.getString(8));
				a.setAlumno(p);
				salida.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	
	
	public List<Tesis> listaPorFecha(Date fecIni, Date fecFin){
		ArrayList<Tesis> salida = new ArrayList<Tesis>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT t.*, a.nombres FROM Tesis t inner join alumno a on t.idAlumno = a.idAlumno  where a.fechaNacimiento between ? and ?";
			psmt = conn.prepareStatement(sql);
			psmt.setDate(1, fecIni);
			psmt.setDate(2, fecFin);
			
			log.info(">>> " + psmt);
		
			rs = psmt.executeQuery();

			Tesis 	t = null;
			Alumno 	p = null;
			while (rs.next()) {
				t = new Tesis();

				t.setIdTesis(rs.getInt(1));
				t.setTitulo(rs.getString(2));
				t.setTema(rs.getString(3));
				t.setFechaCreacion(rs.getDate(4));
				t.setFechaRegistro(rs.getTimestamp(5));
				t.setEstado(rs.getInt(6));
				
				p = new Alumno();
				p.setIdAlumno(rs.getInt(7));
				p.setNombres(rs.getString(8));
				t.setAlumno(p);
				salida.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
	
	public List<Tesis> listaPorTitulo(String filtro){
		ArrayList<Tesis> salida = new ArrayList<Tesis>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT t.*, a.nombres FROM Tesis t inner join alumno a on t.idAlumno = a.idAlumno where t.titulo like ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, filtro);
			
			log.info(">>> " + psmt);
		
			rs = psmt.executeQuery();

			Tesis 	a = null;
			Alumno 	p = null;
			while (rs.next()) {
				a = new Tesis();

				a.setIdTesis(rs.getInt(1));
				a.setTitulo(rs.getString(2));
				a.setTema(rs.getString(3));
				a.setFechaCreacion(rs.getDate(4));
				a.setFechaRegistro(rs.getTimestamp(5));
				a.setEstado(rs.getInt(6));
				
				p = new Alumno();
				p.setIdAlumno(rs.getInt(7));
				p.setNombres(rs.getString(8));
				a.setAlumno(p);
				salida.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (psmt != null) psmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}
}