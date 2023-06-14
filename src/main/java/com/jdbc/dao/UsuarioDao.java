package com.jdbc.dao;

import com.jdbc.conexao.SingleConnection;
import com.jdbc.model.BeanUsuarioFone;
import com.jdbc.model.Fone;
import com.jdbc.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Usuario usuario) {
        try {
            String sql = "insert into usuario(nome, email) values (?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, usuario.getNome());
            insert.setString(2, usuario.getEmail());
            insert.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException eSql) {
                eSql.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Usuario> listar() throws Exception {
        List<Usuario> list = new ArrayList<Usuario>();
        String sql = "select * from usuario";

        PreparedStatement listar = connection.prepareStatement(sql);
        ResultSet resultSet = listar.executeQuery();

        while (resultSet.next()) {
            Usuario obj = new Usuario();
            obj.setId(resultSet.getLong("id"));
            obj.setNome(resultSet.getString("nome"));
            obj.setEmail(resultSet.getString("email"));
            list.add(obj);
        }
        return list;
    }

    public Usuario listarPorId(Long id) throws Exception {
        Usuario obj = new Usuario();
        String sql = "select * from usuario where id = " + id;

        PreparedStatement listarPorId = connection.prepareStatement(sql);
        ResultSet resultSet = listarPorId.executeQuery();

        while (resultSet.next()) {
            obj.setId(resultSet.getLong("id"));
            obj.setNome(resultSet.getString("nome"));
            obj.setEmail(resultSet.getString("email"));
        }
        return obj;
    }

    public void atualizar(Usuario usuario) {
        try {
            String sql = "update usuario set nome = ? where id = " + usuario.getId();
            PreparedStatement atualizar = connection.prepareStatement(sql);
            atualizar.setString(1, usuario.getNome());
            atualizar.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException eSql) {
                eSql.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void deletar(Long id) {
        try {
            String sql = "delete from usuario where id = " + id;
            PreparedStatement deletar = connection.prepareStatement(sql);
            deletar.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException eSql) {
                eSql.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void salvarFone(Fone fone) {
        try {
            String sql = "insert into fone(numero, tipo, usuario) values (?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, fone.getNumero());
            insert.setString(2, fone.getTipo());
            insert.setLong(3, fone.getUsuario());
            insert.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException eSql) {
                eSql.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<BeanUsuarioFone> listarFoneUsuario(Long idUser) {
        List<BeanUsuarioFone> list = new ArrayList<BeanUsuarioFone>();
        String sql = " select nome, numero, email from fone as fone_user ";
        sql += " inner join usuario as usuario_fone ";
        sql += " on fone_user.usuario = usuario_fone.id ";
        sql += " where usuario_fone.id = " + idUser;
        try {

            PreparedStatement listarFoneUsuario = connection.prepareStatement(sql);
            ResultSet resultSet = listarFoneUsuario.executeQuery();

            while (resultSet.next()) {
                BeanUsuarioFone obj = new BeanUsuarioFone();
                obj.setNome(resultSet.getString("nome"));
                obj.setNumero(resultSet.getString("numero"));
                obj.setEmail(resultSet.getString("email"));
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deletarFoneUsuario(Long idUser) {
        try {
            String sqlFone = "delete from fone where usuario =" + idUser;
            String sqlUsuario = "delete from usuario where id   =" + idUser;

            PreparedStatement deletarFoneUsuario = connection.prepareStatement(sqlFone);
            deletarFoneUsuario.executeUpdate();
            connection.commit();

            deletarFoneUsuario = connection.prepareStatement(sqlUsuario);
            deletarFoneUsuario.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException eSql) {
                eSql.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public Boolean emailExiste(String email) throws Exception{

            String sql = "select count(1) > 0 as exist from usuario where upper(email) = upper('" + email + "')";
            PreparedStatement emailExiste = connection.prepareStatement(sql);
            ResultSet resultSet = emailExiste.executeQuery();
            resultSet.next();
            return resultSet.getBoolean("existe");
    }


}
