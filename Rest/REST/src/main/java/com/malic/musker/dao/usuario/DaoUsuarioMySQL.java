package com.malic.musker.dao.usuario;

import com.malic.musker.config.MySQLConfig;
import com.malic.musker.dao.tipousuario.TipoUsuarioFacade;
import com.malic.musker.dto.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoUsuarioMySQL implements DaoUsuario{

    private MySQLConfig mysqlConfig;

    public DaoUsuarioMySQL() {
        mysqlConfig = MySQLConfig.getInstance();
    }

    @Override
    public Usuario loadUsuario(int usuario_id) {
        String sqlQuery = "SELECT * FROM usuario WHERE usuario_id = ?";
        Usuario usuario = null;

        Connection connection = mysqlConfig.connect();
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sqlQuery);
            stm.setInt(1, usuario_id);
            System.out.println(stm);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();

                usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setTipo_usuario(new TipoUsuarioFacade().loadTipoUsuario(rs.getInt("tipo_usuario_id")));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
            }
        } catch (SQLException e) {
            System.out.println(sqlQuery);
            e.printStackTrace();
            System.out.println("Error DaoTipoUsuarioMysql loadTipoUsuario");
        }
        mysqlConfig.disconnect(connection, stm);

        return usuario;
    }

    @Override
    public ArrayList<Usuario> loadALlUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        Connection connection = mysqlConfig.connect();
        String sqlQuery = "SELECT * FROM usuario";

        ResultSet rs = null;
        PreparedStatement stm = null;
        Usuario usuario;
        try {
            stm = connection.prepareStatement(sqlQuery);
            rs = stm.executeQuery();
            while (rs.next()) {

                usuario = new Usuario();

                usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setTipo_usuario(new TipoUsuarioFacade().loadTipoUsuario(rs.getInt("tipo_usuario_id")));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));

                lista.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(sqlQuery);
            e.printStackTrace();
            System.out.println("Error DaoUsuarioMysql loadAllUsuarios");
        }
        mysqlConfig.disconnect(connection, stm);

        return lista;
    }

    @Override
    public Usuario loadUsuarioByUsername(String username) {
        String sqlQuery = "SELECT * FROM usuario WHERE username = ?";
        Usuario usuario = null;

        Connection connection = mysqlConfig.connect();
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sqlQuery);
            stm.setString(1, username);
            System.out.println(stm);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();

                usuario.setUsuario_id(rs.getInt("usuario_id"));
                usuario.setTipo_usuario(new TipoUsuarioFacade().loadTipoUsuario(rs.getInt("tipo_usuario_id")));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
            }
        } catch (SQLException e) {
            System.out.println(sqlQuery);
            e.printStackTrace();
            System.out.println("Error DaoTipoUsuarioMysql loadTipoUsuario");
        }
        mysqlConfig.disconnect(connection, stm);

        return usuario;
    }
}
