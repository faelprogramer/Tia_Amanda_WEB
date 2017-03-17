package br.com.tiaAmanda.modelo.bean.dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionFactory {
    protected Connection connection;
    protected String user, password, db, host, port, url, driver;
    protected final String MSG_SQL_EXCEPTION = "Não foi possível estabelecer a conexão com o banco de dados.";
    
    public abstract Connection getConnection() throws SQLException, ClassNotFoundException;
    
    protected String getMsgSQLErro(SQLException ex) {
        return MSG_SQL_EXCEPTION + "\n" + ex.getMessage();
    }
    
}
