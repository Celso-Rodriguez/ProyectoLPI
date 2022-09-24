package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

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
}