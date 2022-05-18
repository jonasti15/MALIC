package controladores;

import basedatos.LoginBD;
import dialogo.DialogoLogin;
import elementos.Permisos;
import elementos.User;
import interfaz.MUsker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControladorDialogoLogin implements ActionListener{
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	DialogoLogin login;
	
	public ControladorDialogoLogin(DialogoLogin login) {
		this.login = login;
	}
	
	/**
	 * Método que sirve para iniciar sesión al pulsar el botón
	 * Casos:
	 * mostrar - Muestra la contraseá con caracteres o con '*'
	 * confirmar - Se conecta a la base de datos con la cuenta introducida, 
	 * si no se conecta significa que no tiene cuenta, si se conecta, puede acceder a la aplicación
	 * @param evt - Nombre de la accion
	 *@author Jon
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		String accion = evt.getActionCommand();
		Permisos permiso = null;

		switch(accion) {
			case "confirmar":
				MUsker.userDB = login.getUsuario().getText();
				MUsker.pass = String.valueOf(login.getPassword().getPassword());
				MUsker.connectToDB();
				if(MUsker.conn != null) {

					LoginBD loginBd = new LoginBD();
					loginBd.queryUser(login.getUsuario().getText());
					String name = loginBd.getUser();
					int perm = loginBd.getTipoDeUsuario();

					switch(perm) {

						case 2:
							permiso = Permisos.WORKER;
							break;

						}
						if(permiso != null && name != "") {
							login.setUser(new User(loginBd.getUser()));
						}

				login.dispose();
				}else {
					JOptionPane.showConfirmDialog(login, "El usuario o la contraseña no son correctos", "Usuario inválido", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
					login.getPassword().setText("");
				}break;

			case "mostrar":
				if(login.getMostrar().isSelected()) login.getPassword().setEchoChar((char)0);
				else login.getPassword().setEchoChar('*');
				break;
		}
	}

}
