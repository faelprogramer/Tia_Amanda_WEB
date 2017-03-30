package br.com.tiaAmanda.control;

import br.com.tiaAmanda.model.bean.dao.factory.ConnectionFactory;
import br.com.tiaAmanda.model.bean.dao.factory.PostgresConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractManagedBean implements ICRUD {

    private ConnectionFactory connectionFactory = new PostgresConnectionFactory();
    protected Connection connection;

    protected void begin() throws SQLException, ClassNotFoundException {
        connection = connectionFactory.getConnection();
        connection.setAutoCommit(false);
    }

    protected void end() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException ex) {
            roolback();
        } finally {
            closeConnection();
        }
    }
    
    protected void roolback() throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
