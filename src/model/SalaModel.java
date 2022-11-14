package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Sede;
import entidad.Sala;
import util.MySqlDBConexion;

public class SalaModel {
	
	private static Logger log = Logger.getLogger(SalaModel.class.getName());

	public int insertaSala(Sala obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "insert into sala value(null,?,?,?,?,curtime(),?,?);";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNumero());
			pstm.setInt(2, obj.getPiso());
			pstm.setInt(3, obj.getNumAlumnos());
			pstm.setString(4, obj.getRecursos());
			pstm.setInt(5, obj.getEstado());
			pstm.setInt(6, obj.getSede().getIdSede());		
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
	
	public int eliminaSala(int idSala) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "delete from sala where idSala = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idSala);	
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
	
	public int actualizaSala(Sala obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "update sala set numero=?, piso=?, numAlumnos=?, recursos=?, estado=?, idSede=? where idSala = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNumero());
			pstm.setInt(2, obj.getPiso());
			pstm.setInt(3, obj.getNumAlumnos());
			pstm.setString(4, obj.getRecursos());
			pstm.setInt(5, obj.getEstado());
			pstm.setInt(6, obj.getSede().getIdSede());
			pstm.setInt(7, obj.getIdSala());
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
	
	public List<Sala> listaSala() {
		ArrayList<Sala> salida = new ArrayList<Sala>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; // Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "SELECT sa.*, se.nombre FROM sala sa inner join sede se on sa.idSede = se.idSede";
			pstm = con.prepareStatement(sql);
			log.info(">>> " + pstm);

			// En rs se trae los datos de la BD segun el SQL
			rs = pstm.executeQuery();

			// Se pasa la data del rs al ArrayList(data)
			Sala objSala = null;
			Sede objSede = null;
			while (rs.next()) {
				// Se colocan los campos de la base de datos
				objSala = new Sala();
				objSala.setIdSala(rs.getInt(1));
				objSala.setNumero(rs.getString(2));
				objSala.setPiso(rs.getInt(3));
				objSala.setNumAlumnos(rs.getInt(4));
				objSala.setRecursos(rs.getString(5));
				objSala.setFechaRegistro(rs.getDate(6));
				objSala.setEstado(rs.getInt(7));
				
				objSede = new Sede();
				objSede.setIdSede(rs.getInt(8));
				objSede.setNombre(rs.getString(9));
				objSala.setSede(objSede);
				salida.add(objSala);
			}

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
		return salida;
	}
	
	public List<Sala> listaPorSede(int idSede) {
		ArrayList<Sala> salida = new ArrayList<Sala>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; // Trae la data de la BD
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "SELECT sa.*, se.nombre FROM sala sa inner join sede se on sa.idSede = se.idSede where sa.idSede = ?";
			pstm = con.prepareCall(sql);
			pstm.setInt(1, idSede);
			
			log.info(">>> " + pstm);

			// En rs se trae los datos de la BD segun el SQL
			rs = pstm.executeQuery();

			// Se pasa la data del rs al ArrayList(data)
			Sala objSala = null;
			Sede objSede = null;
			while (rs.next()) {
				// Se colocan los campos de la base de datos
				objSala = new Sala();
				objSala.setIdSala(rs.getInt(1));
				objSala.setNumero(rs.getString(2));
				objSala.setPiso(rs.getInt(3));
				objSala.setNumAlumnos(rs.getInt(4));
				objSala.setRecursos(rs.getString(5));
				objSala.setFechaRegistro(rs.getDate(6));
				objSala.setEstado(rs.getInt(7));
				
				objSede = new Sede();
				objSede.setIdSede(rs.getInt(8));
				objSede.setNombre(rs.getString(9));
				objSala.setSede(objSede);
				salida.add(objSala);
			}

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
		return salida;
	}

}
