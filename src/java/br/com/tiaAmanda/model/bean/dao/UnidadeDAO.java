package br.com.tiaAmanda.modelo.bean.dao;

import br.com.tiaAmanda.modelo.bean.Unidade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UnidadeDAO extends DAO<Unidade> {

    private boolean existeUnidade(Connection conn, String cd_unidade) throws SQLException {
        sql = "select 1 from unidade where cd_unidade = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cd_unidade);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return false;
    }

    @Override
    public void save(Connection conn, Unidade u) throws SQLException {
        if (existeUnidade(conn, u.getCd_unidade())) {
            update(conn, u);
        } else {
            add(conn, u);
        }
    }

    private void add(Connection conn, Unidade p) throws SQLException {
        sql = "insert into unidade (cd_unidade,ds_unidade,ie_situacao) "
                + "values(?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            prepararSqlAdd(p);
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    private void update(Connection conn, Unidade u) throws SQLException {
        sql = "update unidade set ds_unidade=?,ie_situacao=?,"
                + "where cd_unidade=?";
        try {
            pstmt = conn.prepareStatement(sql);
            prepararSqlUpdate(u);
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    private void prepararSqlAdd(Unidade u) throws SQLException {
        int index = 1;
        pstmt.setString(index++, u.getCd_unidade());
        pstmt.setString(index++, u.getDs_unidade());
        pstmt.setString(index++, String.valueOf(u.getIe_situacao()));
    }
    
    private void prepararSqlUpdate(Unidade u) throws SQLException {
        int index = 1;
        pstmt.setString(index++, u.getDs_unidade());
        pstmt.setString(index++, String.valueOf(u.getIe_situacao()));
        pstmt.setString(index++, u.getCd_unidade());
    }

    @Override
    public void delete(Connection conn, Unidade u) throws SQLException {
        sql = "delete from unidade where cd_unidade=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, u.getCd_unidade());
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    @Override
    public List<Unidade> getAll(Connection conn) throws SQLException {
        sql = "select cd_unidade,ds_unidade,ie_situacao from unidade order by ds_unidade";
        List<Unidade> unidades = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                unidades.add(InstantObjectFromResultSet());
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return unidades;
    }

    @Override
    public Unidade getObject(Connection conn, Unidade u) throws SQLException {
        sql = "select cd_unidade, ds_unidade, ie_situacao "
                + "from unidade where cd_unidade = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, u.getCd_unidade());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return InstantObjectFromResultSet();
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return null;
    }

    private Unidade InstantObjectFromResultSet() throws SQLException {
        Unidade u = new Unidade();
        u.setCd_unidade(rs.getString("cd_unidade"));
        u.setDs_unidade(rs.getString("ds_unidade"));
        u.setIe_situacao((rs.getString("ie_situacao").charAt(0)));
        return u;
    }

}
