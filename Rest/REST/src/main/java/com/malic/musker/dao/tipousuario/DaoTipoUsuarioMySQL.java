package com.malic.musker.dao.tipousuario;

import com.malic.musker.config.MySQLConfig;
import com.malic.musker.dto.TipoUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoTipoUsuarioMySQL implements DaoTipoUsuario {

    private MySQLConfig mysqlConfig;

    public DaoTipoUsuarioMySQL() {
        mysqlConfig = MySQLConfig.getInstance();
    }

    @Override
    public TipoUsuario loadTipoUsuario(int tipo_usuario_id) {
        String sqlQuery = "SELECT * FROM tipo_usuario WHERE tipo_usuario_id = ?";
        TipoUsuario tipoUsuario = null;

        Connection connection = mysqlConfig.connect();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sqlQuery);
            stm.setInt(1, tipo_usuario_id);
            System.out.println(stm);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                tipoUsuario = new TipoUsuario();

                tipoUsuario.setTipo_usuario_id(rs.getInt("tipo_usuario_id"));
                tipoUsuario.setDescripcion(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            System.out.println(sqlQuery);
            e.printStackTrace();
            System.out.println("Error DaoTipoUsuarioMysql loadTipoUsuario");
        }
        mysqlConfig.disconnect(connection, stm);
        return tipoUsuario;

    }

    @Override
    public ArrayList<TipoUsuario> loadAllTipoUsuario() {
        ArrayList<TipoUsuario> lista = new ArrayList<>();
        Connection connection = mysqlConfig.connect();
        String sqlQuery = "SELECT * FROM tipo_usuario";

        ResultSet rs = null;
        PreparedStatement stm = null;
        TipoUsuario tipoUsuario;
        try {
            stm = connection.prepareStatement(sqlQuery);
            rs = stm.executeQuery();
            while (rs.next()) {

                tipoUsuario = new TipoUsuario();

                tipoUsuario.setTipo_usuario_id(rs.getInt("tipo_usuario_id"));
                tipoUsuario.setDescripcion(rs.getString("descripcion"));

                lista.add(tipoUsuario);
            }
        } catch (SQLException e) {
            System.out.println(sqlQuery);
            e.printStackTrace();
            System.out.println("Error DaoTipoUsuarioMysql loadAllTipoUsuario");
        }
        mysqlConfig.disconnect(connection, stm);

        return lista;
    }
}
