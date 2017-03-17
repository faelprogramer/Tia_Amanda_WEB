package br.com.tiaAmanda.modelo.bean.dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class PostgresConnectionFactory extends ConnectionFactory {

    public PostgresConnectionFactory() {
        user = "postgres";
        password = "admin";
        db = "acai";
        host = "10.0.0.10";
        port = "5432";
        driver = "org.postgresql.Driver";
        url = getUrlConnection();
    }

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(driver);
            connection = (Connection) DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            printConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
        return connection;
    }

    private void printConnection() {
        System.out.println("Nova conexão aberta!");
    }
    
    private String getUrlConnection() {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:postgresql://").append(host).append(":").append(port).append("/").append(db);
        return sb.toString();
    }
    
    /*
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection c = new PostgresConnectionFactory().getConnection();
        System.out.println("Conexão aberta com sucesso!);
    }
    */
}
