package br.com.tiaAmanda.modelo.bean.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<O> {
    
    public void save(Connection conn, O obj) throws SQLException;
    
    public void delete(Connection conn, O obj) throws SQLException;
    
    public List<O> getAll(Connection conn) throws SQLException;
    
    public O getObject(Connection conn, O obj) throws SQLException;
    
}
