package br.com.tiaAmanda.modelo.bean.dao;

import br.com.tiaAmanda.modelo.bean.PessoaFisica;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class PessoaFisicaDAO extends DAO<PessoaFisica> {

    private boolean existePessoaFisica(Connection conn, int cd_pessoa_fisica) throws SQLException {
        sql = "select 1 from pessoa_fisica where cd_pessoa_fisica = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, cd_pessoa_fisica);
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
    public void save(Connection conn, PessoaFisica p) throws SQLException {
        if (existePessoaFisica(conn, p.getCd_pessoa_fisica())) {
            update(conn, p);
        } else {
            add(conn, p);
        }
    }

    private void add(Connection conn, PessoaFisica p) throws SQLException {
        sql = "insert into pessoa_fisica (cd_pessoa_fisica,nm_pessoa_fisica,"
                + "ds_telefone,nr_compra_fidelidade,ds_endereco,nr_endereco,"
                + "ds_complemento,ds_bairro,ds_observacao) "
                + "values(?,?,?,?,?,?,?,?,?)";
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

    private void prepararSqlAdd(PessoaFisica p) throws SQLException {
        int index = 1;
        pstmt.setInt(index++, p.getCd_pessoa_fisica());
        pstmt.setString(index++, p.getNm_pessoa_fisica());
        pstmt.setString(index++, p.getDs_telefone());
        pstmt.setInt(index++, p.getNr_compra_fidelidade());
        pstmt.setString(index++, p.getDs_endereco());
        pstmt.setString(index++, p.getNr_endereco());
        pstmt.setString(index++, p.getDs_complemento());
        pstmt.setString(index++, p.getDs_bairro());
        pstmt.setString(index++, p.getDs_observacao());
    }

    private void update(Connection conn, PessoaFisica p) throws SQLException {
        sql = "update pessoa_fisica set nm_pessoa_fisica=?,ds_telefone=?,"
                + "nr_compra_fidelidade=?,ds_endereco=?,nr_endereco=?,"
                + "ds_complemento=?,ds_bairro=?,ds_observacao=? "
                + "where cd_pessoa_fisica=?";
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

    private void prepararSqlUpdate(PessoaFisica p) throws SQLException {
        int index = 1;
        pstmt.setString(index++, p.getNm_pessoa_fisica());
        pstmt.setString(index++, p.getDs_telefone());
        pstmt.setInt(index++, p.getNr_compra_fidelidade());
        pstmt.setString(index++, p.getDs_endereco());
        pstmt.setString(index++, p.getNr_endereco());
        pstmt.setString(index++, p.getDs_complemento());
        pstmt.setString(index++, p.getDs_bairro());
        pstmt.setString(index++, p.getDs_observacao());
        pstmt.setInt(index++, p.getCd_pessoa_fisica());
    }

    @Override
    public void delete(Connection conn, PessoaFisica p) throws SQLException {
        sql = "delete from pessoa_fisica where cd_pessoa_fisica=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p.getCd_pessoa_fisica());
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
    }

    public int getProxSequence(Connection conn) throws SQLException {
        sql = "SELECT nextval('pessoa_fisica_cd_pessoa_fisica_seq')";
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
    public List<PessoaFisica> getAll(Connection conn) throws SQLException {
        sql = "select cd_pessoa_fisica, nm_pessoa_fisica, ds_telefone, "
                + "nr_compra_fidelidade, ds_endereco, nr_endereco, "
                + "ds_complemento, ds_bairro, ds_observacao "
                + "from pessoa_fisica order by nm_pessoa_fisica";
        List<PessoaFisica> pessoas = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                pessoas.add(InstantObjectFromResultSet());
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return pessoas;
    }

    private PessoaFisica InstantObjectFromResultSet() throws SQLException {
        PessoaFisica p;
        p = new PessoaFisica();
        p.setCd_pessoa_fisica(rs.getInt("cd_pessoa_fisica"));
        p.setNm_pessoa_fisica(rs.getString("nm_pessoa_fisica"));
        p.setDs_telefone(rs.getString("ds_telefone"));
        p.setNr_compra_fidelidade(rs.getInt("nr_compra_fidelidade"));
        p.setDs_endereco(rs.getString("ds_endereco"));
        p.setNr_endereco(rs.getString("nr_endereco"));
        p.setDs_complemento(rs.getString("ds_complemento"));
        p.setDs_bairro(rs.getString("ds_bairro"));
        p.setDs_observacao(rs.getString("ds_observacao"));
        return p;
    }

    @Override
    public PessoaFisica getObject(Connection conn, PessoaFisica p) throws SQLException {
        sql = "select cd_pessoa_fisica, nm_pessoa_fisica, ds_telefone, "
                + "nr_compra_fidelidade, ds_endereco, nr_endereco, "
                + "ds_complemento, ds_bairro, ds_observacao "
                + "from pessoa_fisica where cd_pessoa_fisica = ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, p.getCd_pessoa_fisica());
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

    public List<PessoaFisica> getAllWithName(Connection conn, String nome) throws SQLException {
        sql = "select cd_pessoa_fisica, nm_pessoa_fisica, ds_telefone, "
                + "nr_compra_fidelidade, ds_endereco, nr_endereco, "
                + "ds_complemento, ds_bairro, ds_observacao from pessoa_fisica "
                + "where upper(nm_pessoa_fisica) like upper('%" + nome.toUpperCase() + "%') order by nm_pessoa_fisica";
        List<PessoaFisica> pessoas = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println(sql);
            while (rs.next()) {
                pessoas.add(InstantObjectFromResultSet());
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            closeAll();
        }
        return pessoas;
    }

}
