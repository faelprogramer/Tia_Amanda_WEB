package br.com.tiaAmanda.modelo.bean.dao;

import br.com.tiaAmanda.modelo.bean.FormaPagamento;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoDAO extends DAO<FormaPagamento>{

    private boolean existeFormaPagamento(Connection conn, Integer cd_forma_pagamento) throws SQLException {
        sql = "select 1 from forma_pagamento where cd_forma_pagamento = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cd_forma_pagamento);
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
    public void save(Connection conn, FormaPagamento fp) throws SQLException {
        if (existeFormaPagamento(conn, fp.getCd_forma_pagamento())) {
            update(conn, fp);
        } else {
            add(conn, fp);
        }
    }

    private void add(Connection conn, FormaPagamento fp) throws SQLException {
        sql = "insert into forma_pagamento "
                + "(cd_forma_pagamento,ds_forma_pagamento,ie_situacao) "
                + "values (?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            prepararSqlAdd(fp);
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    private void update(Connection conn, FormaPagamento fp) throws SQLException {
        sql = "update forma_pagamento set cd_forma_pagamento=?,"
                + "ds_forma_pagamento=?,ie_situacao=? where (?,?,?);";
        try {
            pstmt = conn.prepareStatement(sql);
            prepararSqlUpdate(fp);
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    private void prepararSqlAdd(FormaPagamento fp) throws SQLException {
        int index = 1;
        pstmt.setInt(index++, fp.getCd_forma_pagamento());
        pstmt.setString(index++, fp.getDs_forma_pagamento());
        pstmt.setString(index++, String.valueOf(fp.getIe_situacao()));
    }
    
    private void prepararSqlUpdate(FormaPagamento fp) throws SQLException {
        int index = 1;
        pstmt.setString(index++, fp.getDs_forma_pagamento());
        pstmt.setString(index++, String.valueOf(fp.getIe_situacao()));
        pstmt.setInt(index++, fp.getCd_forma_pagamento());
    }

    @Override
    public void delete(Connection conn, FormaPagamento fp) throws SQLException {
        sql = "delete from forma_pagamento where cd_forma_pagamento=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fp.getCd_forma_pagamento());
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    @Override
    public List<FormaPagamento> getAll(Connection conn) throws SQLException {
        sql = "select cd_forma_pagamento,ds_forma_pagamento,ie_situacao "
                + "from forma_pagamento order by ds_forma_pagamento";
        List<FormaPagamento> formasPagamento = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                formasPagamento.add(InstantObjectFromResultSet());
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return formasPagamento;
    }

    @Override
    public FormaPagamento getObject(Connection conn, FormaPagamento pf) throws SQLException {
        sql = "select cd_forma_pagamento,ds_forma_pagamento,ie_situacao "
                + "from forma_pagamento where cd_forma_pagamento=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pf.getCd_forma_pagamento());
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

    private FormaPagamento InstantObjectFromResultSet() throws SQLException {
        FormaPagamento fp = new FormaPagamento();
        fp.setCd_forma_pagamento(rs.getInt("cd_forma_pagamento"));
        fp.setDs_forma_pagamento(rs.getString("ds_forma_pagamento"));
        fp.setIe_situacao((rs.getString("ie_situacao").charAt(0)));
        return fp;
    }

    
}
