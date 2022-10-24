package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Categoria;
import entidad.Libro;
import util.MySqlDBConexion;

public class LibroModel {
	
	private static Logger log = Logger.getLogger(LibroModel.class.getName());

	public int insertaLibro(Libro obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "insert into libro value(null,?,?,?,curtime(),?,?);";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setInt(2, obj.getAnio());
			pstm.setString(3, obj.getSerie());
			pstm.setInt(4, obj.getEstado());
			pstm.setInt(5, obj.getCategoria().getIdCategoria());
			
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
	
	public List<Libro> listaTodos() {

		ArrayList<Libro> salida = new ArrayList<Libro>();

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			// 1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			// 2 Se prepara el SQL
			String sql = "SELECT l.*, c.descripcion FROM libro l inner join categoria_libro c on l.idCategoria = c.idCategoria";
			psmt = conn.prepareStatement(sql);
			log.info(">>> " + psmt);

			// 3 Se ejecuta el SQL en la base de datos

			rs = psmt.executeQuery();
			Libro objLibro = null;
			Categoria objCategoria = null;
			while (rs.next()) {
				objLibro = new Libro();
				objLibro.setIdLibro(rs.getInt(1));
				objLibro.setTitulo(rs.getString(2));
				objLibro.setAnio(rs.getInt(3));
				objLibro.setSerie(rs.getString(4));				
				objLibro.setFechaRegistro(rs.getDate(5));
				objLibro.setEstado(rs.getInt(6));
				
				objCategoria = new Categoria();
				objCategoria.setIdCategoria(rs.getInt(7));
				objCategoria.setDescripcion(rs.getString(8));
				objLibro.setCategoria(objCategoria);
				salida.add(objLibro);

			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				if (rs != null)
					rs.close();

				if (psmt != null)
					psmt.close();

				if (conn != null)
					conn.close();

			} catch (Exception e2) {
			}

		}

		return salida;

	}
	public int eliminaLibro(int idLibro) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			// 1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el SQL
			String sql = "delete from libro where idLibro = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idLibro);
			log.info(">>> " + pstm);

			// 3 Ejecutamos a la base de datos
			// Retorna la cantidad de registrados en salida
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
	
	public int actualizaLibro(Libro obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			// 1 Se crea la conexión
			conn = MySqlDBConexion.getConexion();

			// 2 Se prepara el SQL
			String sql = "update Libro set titulo=?,anio=?,serie=?, estado=?, idCategoria=? where idLibro  = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getTitulo());
			pstm.setInt(2, obj.getAnio());
			pstm.setString(3, obj.getSerie());
			pstm.setInt(4, obj.getEstado());
			pstm.setInt(5, obj.getCategoria().getIdCategoria());
			pstm.setInt(6, obj.getIdLibro());
			log.info(">>> " + pstm);

			// 3 Ejecutamos a la base de datos
			// Retorna la cantidad de registrados en salida
			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return salida;
	}

}
