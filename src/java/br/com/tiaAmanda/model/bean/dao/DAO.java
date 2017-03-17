package br.com.tiaAmanda.modelo.bean.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public abstract class DAO<O> implements IDAO<O> {

    protected PreparedStatement pstmt;
    protected Statement stmt;
    protected ResultSet rs;
    protected String sql;

    protected void closeAll() throws SQLException {
        closeRs();
        closePstmt();
        closeStmt();
    }

    private void closeRs() throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
    }

    private void closePstmt() throws SQLException {
        if (pstmt != null && !pstmt.isClosed()) {
            pstmt.close();
        }
    }

    private void closeStmt() throws SQLException {
        if (stmt != null && !stmt.isClosed()) {
            stmt.close();
        }
    }
    
    public static Date getDataServidor(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select current_date");
            if (rs.next()) {
                return rs.getDate(1);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        }
        return null;
    }
    
    protected java.sql.Date utilDateFromSqlDate(java.util.Date dt) {
        return new java.sql.Date(dt.getTime());
    }

}
