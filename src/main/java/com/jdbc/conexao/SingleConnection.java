package com.jdbc.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
    private static String url            = "jdbc:postgresql://localhost:5432/db_jdbc";
    private static String user           = "postgres";
    private static String password       = "admin";
    private static Connection connection = null;

    static {
        conectar();
    }

    public SingleConnection(){
        conectar();
    }
    private static void conectar(){
        try {
            if (connection == null){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
                System.out.println("Conectou ao banco de dados!");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
