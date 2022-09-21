package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

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
}
