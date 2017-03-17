package br.com.tiaAmanda.modelo.bean.dao;

import br.com.tiaAmanda.modelo.bean.Produto;
import br.com.tiaAmanda.modelo.bean.Unidade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ProdutoDAO extends DAO<Produto> {

    private final UnidadeDAO unidadeDAO;

    public ProdutoDAO() {
        this.unidadeDAO = new UnidadeDAO();
    }

    private boolean existeProduto(Connection conn, int cd_produto) throws SQLException {
        sql = "select 1 from produto where cd_produto = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cd_produto);
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
    public void save(Connection conn, Produto p) throws SQLException {
        if (existeProduto(conn, p.getCd_produto())) {
            update(conn, p);
        } else {
            add(conn, p);
        }
    }

    private void add(Connection conn, Produto p) throws SQLException {
        sql = "insert into produto (cd_produto,cd_unidade,ds_produto,cd_barras,"
                + "ie_situacao,vl_avista,vl_aprazo) values (?,?,?,?,?,?,?)";
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

    private void prepararSqlAdd(Produto p) throws SQLException {
        int index = 1;
        pstmt.setInt(index++, p.getCd_produto());
        pstmt.setString(index++, p.getUnidade().getCd_unidade());
        pstmt.setString(index++, p.getDs_produto());
        pstmt.setString(index++, p.getCd_barras());
        pstmt.setString(index++, String.valueOf(p.getIe_situacao()));
        pstmt.setFloat(index++, p.getVl_avista());
        pstmt.setFloat(index++, p.getVl_aprazo());
    }

    private void prepararSqlUpdate(Produto p) throws SQLException {
        int index = 1;
        pstmt.setString(index++, p.getUnidade().getCd_unidade());
        pstmt.setString(index++, p.getDs_produto());
        pstmt.setString(index++, p.getCd_barras());
        pstmt.setString(index++, String.valueOf(p.getIe_situacao()));
        pstmt.setFloat(index++, p.getVl_avista());
        pstmt.setFloat(index++, p.getVl_aprazo());
        pstmt.setInt(index++, p.getCd_produto());
    }

    private void update(Connection conn, Produto p) throws SQLException {
        sql = "update produto set cd_produto=?,ds_produto=?,cd_barras=?,"
                + "ie_situacao=?,vl_avista=?,vl_aprazo=? where cd_produto=?";
        try {
            pstmt = conn.prepareStatement(sql);
            prepararSqlUpdate(p);
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    @Override
    public void delete(Connection conn, Produto p) throws SQLException {
        sql = "delete from produto where cd_produto=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p.getCd_produto());
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    public int getProxSequence(Connection conn) throws SQLException {
        sql = "SELECT nextval('produto_cd_produto_seq')";
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
    public List<Produto> getAll(Connection conn) throws SQLException {
        sql = "select cd_produto,cd_unidade,ds_produto,cd_barras,ie_situacao,"
                + "vl_avista,vl_aprazo from produto where ie_situacao "
                + "like 'A' order by ds_produto";
        List<Produto> produtos = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                produtos.add(InstantObjectFromResultSet(conn));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return produtos;
    }

    private Produto InstantObjectFromResultSet(Connection conn) throws SQLException {
        Produto p = new Produto();
        p.setCd_produto(rs.getInt("cd_produto"));
        p.setUnidade(unidadeDAO.getObject(conn, new Unidade(rs.getString("cd_unidade"))));
        p.setDs_produto(rs.getString("ds_produto"));
        p.setCd_barras(rs.getString("cd_barras"));
        p.setIe_situacao(rs.getString("ie_situacao").charAt(0));
        p.setVl_avista(rs.getFloat("vl_avista"));
        p.setVl_aprazo(rs.getFloat("vl_aprazo"));
        return p;
    }

    @Override
    public Produto getObject(Connection conn, Produto p) throws SQLException {
        sql = "select cd_produto,cd_unidade,ds_produto,cd_barras,ie_situacao,"
                + "vl_avista,vl_aprazo from produto where cd_produto=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p.getCd_produto());
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

    public List<Produto> getAllWithDesc(Connection conn, String descricao) throws SQLException {
        sql = "select cd_produto,cd_unidade,ds_produto,cd_barras,ie_situacao,"
                + "vl_aprazo,vl_avista from produto where upper(ds_produto) "
                + "like '%" + descricao.toUpperCase() + "%' order by ds_produto";
        List<Produto> produtos = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                produtos.add(InstantObjectFromResultSet(conn));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return produtos;
    }

    public Produto getProdutoByCdBarras(Connection conn, String cd_barras) throws SQLException {
        sql = "select cd_produto,cd_unidade,ds_produto,cd_barras,ie_situacao,"
                + "vl_avista,vl_aprazo from produto where cd_barras like ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cd_barras);
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
