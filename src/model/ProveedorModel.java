package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entidad.Pais;
import entidad.Proveedor;
import util.MySqlDBConexion;

public class ProveedorModel {

	private static Logger log = Logger.getLogger(ProveedorModel.class.getName());

	public int insertaProveedor(Proveedor obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = MySqlDBConexion.getConexion();
			
			String sql = "insert into proveedor value(null,?,?,?,?,?,?,curtime(),?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setString(3, obj.getDni());
			pstm.setString(4, obj.getDireccion());
			pstm.setString(5, obj.getTelefono());
			pstm.setString(6, obj.getCorreo());
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
	
	public int actualizaProveedor(Proveedor a) {
		int actualiza = -1;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			con = MySqlDBConexion.getConexion();
			String sql = "update Proveedor set nombres=?, apellidos=?, Dni=?, direccion=?, telefono=?, correo=?, estado=?, idPais=? where idProveedor=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, a.getNombres());
			pstm.setString(2, a.getApellidos());
			pstm.setString(3, a.getDni());
			pstm.setString(4, a.getDireccion());
			pstm.setString(5, a.getTelefono());
			pstm.setString(6, a.getCorreo());
			pstm.setInt(7, a.getEstado());
			pstm.setInt(8, a.getPais().getIdPais());
			pstm.setInt(9, a.getIdProveedor());
			log.info(">>> " + pstm);
			actualiza = pstm.executeUpdate();
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
		return actualiza;
	}
	
	public int eliminaProveedor(int idProveedor) {
		int elimina = -1;
		Connection con = null;
		PreparedStatement pstm = null;

		try {
			con = MySqlDBConexion.getConexion();
			String sql = "delete from proveedor where idProveedor=?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, idProveedor);
			log.info(">>> " + pstm);
			elimina = pstm.executeUpdate();
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
		return elimina;
	}
	
	public List<Proveedor> listaProveedor() {
		ArrayList<Proveedor> salida = new ArrayList<Proveedor>();
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null; 
		try {
			con = MySqlDBConexion.getConexion();
			String sql =  "SELECT pr.*, p.nombre FROM proveedor pr inner join pais p on pr.idPais = p.idPais";
			//SELECT a.*, p.nombre FROM proveedor a\r\n inner join pais p on a.idPais = p.idPais
			pstm = con.prepareStatement(sql);
			log.info(">>> " + pstm);
		
			rs = pstm.executeQuery();

			Proveedor objProveedor = null;
			Pais objPais = null;
			while (rs.next()) {
				objProveedor = new Proveedor();
				objProveedor.setIdProveedor(rs.getInt(1));
				objProveedor.setNombres(rs.getString(2));
				objProveedor.setApellidos(rs.getString(3));
				objProveedor.setDni(rs.getString(4));
				objProveedor.setDireccion(rs.getString(5));
				objProveedor.setTelefono(rs.getString(6));
				objProveedor.setCorreo(rs.getString(7));
				objProveedor.setFechaRegistro(rs.getTimestamp(8));
				objProveedor.setEstado(rs.getInt(9));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				objProveedor.setPais(objPais);
				salida.add(objProveedor);
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
	
	public List<Proveedor> listaPorPais(int idPais){
		ArrayList<Proveedor> salida = new ArrayList<Proveedor>();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			//1 Se crea la conexion
			conn = MySqlDBConexion.getConexion();
			
			//2 Se prepara el SQL
			String sql = "SELECT c.*, p.nombre FROM Proveedor c inner join pais p on c.idPais = p.idPais where c.idPais = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, idPais);
			
			//String sql = "call sp_Proveedor_list()";
			//psmt = conn.prepareStatement(sql);
			
			log.info(">>> " + psmt);
			
			//3 Se ejecuta el SQL en la base de datos
			rs = psmt.executeQuery();
			Proveedor objProveedor = null;
			Pais objPais = null;
			while(rs.next()) {
				objProveedor = new Proveedor();
				objProveedor.setIdProveedor(rs.getInt(1));
				objProveedor.setNombres(rs.getString(2));
				objProveedor.setApellidos(rs.getString(3));
				objProveedor.setDni(rs.getString(4));
				objProveedor.setDireccion(rs.getString(5));
				objProveedor.setTelefono(rs.getString(6));
				objProveedor.setCorreo(rs.getString(7));
				objProveedor.setFechaRegistro(rs.getTimestamp(8));
				objProveedor.setEstado(rs.getInt(9));
				
				objPais = new Pais();
				objPais.setIdPais(rs.getInt(10));
				objPais.setNombre(rs.getString(11));
				objProveedor.setPais(objPais);
				salida.add(objProveedor);
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
