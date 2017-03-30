package br.com.tiaAmanda.control;

import br.com.tiaAmanda.model.util.FacesMesssage;
import br.com.tiaAmanda.modelo.bean.PessoaFisica;
import br.com.tiaAmanda.modelo.bean.dao.PessoaFisicaDAO;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CadastroClientesMB extends AbstractManagedBean {
    
    private PessoaFisica cliente;
    private final PessoaFisicaDAO dao;
    private List<PessoaFisica> clientes;

    public PessoaFisica getCliente() {
        return cliente;
    }

    public void setCliente(PessoaFisica cliente) {
        this.cliente = cliente;
    }

    public List<PessoaFisica> getClientes() {
        return clientes;
    }

    public void setClientes(List<PessoaFisica> clientes) {
        this.clientes = clientes;
    }

    public CadastroClientesMB() {
        this.dao = new PessoaFisicaDAO();
        this.cliente = new PessoaFisica();
        try {
            begin();
            this.clientes = dao.getAll(connection);
            end();
        } catch (SQLException | ClassNotFoundException ex) {
            FacesMesssage.addMessage(FacesMesssage.ERROR, "",
                    "Erro ao consultar clientes!");
        }
        
    }
    
    
    @Override
    public void btnNovo() {
        cliente = new PessoaFisica();
        try {
            begin();
            cliente.setCd_pessoa_fisica(dao.getProxSequence(connection));
            end();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            FacesMesssage.addMessage(FacesMesssage.ERROR, "",
                    "Erro ao consultar código do novo cliente!");
        }
    }
    
    @Override
    public void btnSalvar() {
        try {
            begin();
            System.out.println("Vai salvar");
            dao.save(connection, cliente);
            System.out.println("salvou");
            end();
            cliente = new PessoaFisica();
        } catch (SQLException | ClassNotFoundException ex) {
            FacesMesssage.addMessage(FacesMesssage.ERROR, "",
                    "Erro ao salvar o cliente!");
        }
        FacesMesssage.addMessage(FacesMesssage.INFO, "Informação",
                    "Cliente salvo com sucesso!");
    }

    @Override
    public void btnEditar() {
        
    }

    @Override
    public void btnExcluir() {
        
    }
    
}
