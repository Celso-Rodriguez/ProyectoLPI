package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

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
			
			String sql = "insert into sala value(null,?,?,?,?,curtime(),1,?);";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNumero());
			pstm.setInt(2, obj.getPiso());
			pstm.setInt(3, obj.getNumAlumnos());
			pstm.setString(4, obj.getRecursos());
			pstm.setInt(5, obj.getSede().getIdSede());		
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
