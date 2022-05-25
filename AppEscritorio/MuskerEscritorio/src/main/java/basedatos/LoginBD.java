package basedatos;

import dao.DaoLogin;
import interfaz.MUsker;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginBD implements DaoLogin{
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String user;
	int tipoUsuario;
	
	@Override
	public void queryUser(String user) {



		try {
			ps = MUsker.conn.prepareStatement("SELECT username, tipo_usuario_id FROM usuario WHERE username = ?");
			ps.setString(1, user);
			rs = ps.executeQuery();
			while(rs.next()) {
				this.user = rs.getString(1);
				this.tipoUsuario=rs.getInt(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUser() {
		return user;
	}

	@Override
	public int getTipoDeUsuario() {
		return this.tipoUsuario;
	}


}
