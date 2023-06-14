package com.jdbc.dao;

import com.jdbc.conexao.SingleConnection;
import com.jdbc.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao(){
        connection = SingleConnection.getConnection();
    }

    public void salvar(Usuario usuario){
        try {
            String sql = "insert into usuario(id, nome, email) values (?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setLong  (1, usuario.getId());
            insert.setString(2, usuario.getNome());
            insert.setString(3, usuario.getEmail());
            insert.execute();
            connection.commit();
        } catch (Exception e){
            try {
                connection.rollback();
            } catch (SQLException eSql){
                eSql.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Usuario> listar() throws Exception{
        List<Usuario> list = new ArrayList<Usuario>();
        String sql = "select * from usuario";

        PreparedStatement select = connection.prepareStatement(sql);
        ResultSet resultSet = select.executeQuery();

        while (resultSet.next()){
            Usuario obj = new Usuario();
            obj.setId(resultSet.getLong("id"));
            obj.setNome(resultSet.getString("nome"));
            obj.setEmail(resultSet.getString("email"));
            list.add(obj);
        }
        return list;
    }

    public Usuario listarPorId(Long id) throws Exception{
        Usuario obj = new Usuario();
        String sql = "select * from usuario where id = " + id;

        PreparedStatement select = connection.prepareStatement(sql);
        ResultSet resultSet = select.executeQuery();

        while (resultSet.next()){
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
        } catch (Exception e){
            try {
                connection.rollback();
            } catch (SQLException eSql){
                eSql.printStackTrace();
            }
            e.printStackTrace();
        }
    }







































}
