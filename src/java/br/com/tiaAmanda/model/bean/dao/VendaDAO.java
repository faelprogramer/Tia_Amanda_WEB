package br.com.tiaAmanda.modelo.bean.dao;

import br.com.tiaAmanda.modelo.bean.FormaPagamento;
import br.com.tiaAmanda.modelo.bean.PessoaFisica;
import br.com.tiaAmanda.modelo.bean.Produto;
import br.com.tiaAmanda.modelo.bean.Venda;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class VendaDAO extends DAO<Venda> {

    private final ProdutoDAO produtoDAO;
    private final PessoaFisicaDAO pessoaFisicaDAO;
    private final FormaPagamentoDAO formaPagamentoDAO;
    
    public VendaDAO() {
        produtoDAO = new ProdutoDAO();
        pessoaFisicaDAO = new PessoaFisicaDAO();
        formaPagamentoDAO = new FormaPagamentoDAO();
    }
    
    private boolean existeVenda(Connection conn, int nr_sequencia) throws SQLException {
        sql = "select 1 from venda where nr_sequencia = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nr_sequencia);
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
    public void save(Connection conn, Venda v) throws SQLException {
        if (existeVenda(conn, v.getNr_sequencia())) {
            update(conn, v);
        } else {
            add(conn, v);
        }
    }

    private void add(Connection conn, Venda v) throws SQLException {
        sql = "insert into venda (nr_sequencia,cd_produto,cd_pessoa_fisica,"
                + "dt_venda,cd_forma_pagamento,vl_venda) values (?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            prepararSqlAdd(v);
            pstmt.execute();
            PessoaFisica p = v.getPessoa_fisica();
            p.setNr_compra_fidelidade(p.getNr_compra_fidelidade() + 1);
            pessoaFisicaDAO.save(conn, p);
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    private void prepararSqlAdd(Venda v) throws SQLException {
        int index = 1;
        pstmt.setInt(index++, v.getNr_sequencia());
        pstmt.setInt(index++, v.getProduto().getCd_produto());
        pstmt.setInt(index++, v.getPessoa_fisica().getCd_pessoa_fisica());
        pstmt.setDate(index++, utilDateFromSqlDate(v.getDt_venda()));
        pstmt.setInt(index++, v.getFormaPagamento().getCd_forma_pagamento());
        pstmt.setFloat(index++, v.getVl_venda());
    }
    
    private void prepararSqlUpdate(Venda v) throws SQLException {
        int index = 1;
        pstmt.setInt(index++, v.getProduto().getCd_produto());
        pstmt.setInt(index++, v.getPessoa_fisica().getCd_pessoa_fisica());
        pstmt.setDate(index++, utilDateFromSqlDate(v.getDt_venda()));
        pstmt.setInt(index++, v.getFormaPagamento().getCd_forma_pagamento());
        pstmt.setFloat(index++, v.getVl_venda());
        pstmt.setInt(index++, v.getNr_sequencia());
    }

    private void update(Connection conn, Venda v) throws SQLException {
        sql = "update venda set cd_produto=?,cd_pessoa_fisica=?,dt_venda=?,"
                + "cd_forma_pagamento=?,vl_venda=? where nr_sequencia=?";
        try {
            pstmt = conn.prepareStatement(sql);
            prepararSqlUpdate(v);
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    @Override
    public void delete(Connection conn, Venda v) throws SQLException {
        sql = "delete from venda where nr_sequencia=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, v.getNr_sequencia());
            pstmt.execute();
            
            PessoaFisica p = v.getPessoa_fisica();
            p.setNr_compra_fidelidade(p.getNr_compra_fidelidade() - 1);
            pessoaFisicaDAO.save(conn, p);
            
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    public int getProxSequence(Connection conn) throws SQLException {
        sql = "SELECT nextval('venda_nr_sequencia_seq')";
        int sequence = -1;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                sequence = rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return sequence;
    }

    @Override
    public List<Venda> getAll(Connection conn) throws SQLException {
        sql = "select nr_sequencia,cd_produto,cd_pessoa_fisica,dt_venda,"
                + "cd_forma_pagamento,vl_venda,"
                + "(select nm_pessoa_fisica from pessoa_fisica p "
                + "where p.cd_pessoa_fisica = v.cd_pessoa_fisica) as ds_pessoa_fisica "
                + "from venda v order by dt_venda desc, ds_pessoa_fisica";
        List<Venda> vendas = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                vendas.add(InstantObjectFromResultSet(conn));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return vendas;
    }
    
    public List<Venda> getVendasPorNomeCliente(Connection conn, String nome) throws SQLException {
        sql = "select nr_sequencia,cd_produto,cd_pessoa_fisica,dt_venda,"
                + "cd_forma_pagamento,vl_venda from venda v where "
                + "(select upper(nm_pessoa_fisica) from pessoa_fisica p where "
                + "p.cd_pessoa_fisica = v.cd_pessoa_fisica) like '%" + nome.toUpperCase() + "%' "
                + "order by dt_venda desc";
        List<Venda> vendas = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                vendas.add(InstantObjectFromResultSet(conn));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return vendas;
    }

    private Venda InstantObjectFromResultSet(Connection conn) throws SQLException {
        Venda v = new Venda();
        v.setNr_sequencia(rs.getInt("nr_sequencia"));
        v.setProduto(produtoDAO.getObject(conn, new Produto(rs.getInt("cd_produto"))));
        v.setPessoa_fisica(pessoaFisicaDAO.getObject(conn, new PessoaFisica(rs.getInt("cd_pessoa_fisica"))));
        v.setDt_venda(rs.getDate("dt_venda"));
        v.setFormaPagamento(formaPagamentoDAO.getObject(conn, new FormaPagamento(rs.getInt("cd_forma_pagamento"))));
        v.setVl_venda(rs.getFloat("vl_venda"));
        return v;
    }

    @Override
    public Venda getObject(Connection conn, Venda v) throws SQLException {
        sql = "select nr_sequencia,cd_produto,cd_pessoa_fisica,dt_venda,"
                + "cd_forma_pagamento,vl_venda from venda where nr_sequencia=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, v.getNr_sequencia());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return InstantObjectFromResultSet(conn);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return null;
    }

}