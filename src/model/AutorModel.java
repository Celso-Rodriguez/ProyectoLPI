package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import entidad.Autor;
import util.MySqlDBConexion;

public class AutorModel {
	
private static Logger log = Logger.getLogger(AutorModel.class.getName());
	
	public int insertaAutor(Autor obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();

			String sql = "insert into autor value(null,?,?,?,?,curtime(),1,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombres());
			pstm.setString(2, obj.getApellidos());
			pstm.setDate(3, obj.getFechaNacimiento());
			pstm.setInt(4, obj.getTelefono());
			pstm.setInt(5, obj.getGrado().getIdGrado());
			log.info(">>> " + pstm);

			salida = pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) pstm.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {}
		}
		return salida;
	}


}
