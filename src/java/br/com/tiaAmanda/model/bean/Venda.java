package br.com.tiaAmanda.modelo.bean;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Objects;

public class Venda {

    private Integer nr_sequencia;
    private Produto produto;
    private PessoaFisica pessoa_fisica;
    private Date dt_venda;
    private FormaPagamento formaPagamento;
    private float vl_venda;

    public Venda() {
    }

    public Venda(Integer nr_sequencia) {
        this.nr_sequencia = nr_sequencia;
    }

    public Integer getNr_sequencia() {
        return nr_sequencia;
    }

    public void setNr_sequencia(Integer nr_sequencia) {
        this.nr_sequencia = nr_sequencia;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public PessoaFisica getPessoa_fisica() {
        return pessoa_fisica;
    }

    public void setPessoa_fisica(PessoaFisica pessoa_fisica) {
        this.pessoa_fisica = pessoa_fisica;
    }

    public Date getDt_venda() {
        return dt_venda;
    }

    public void setDt_venda(Date dt_venda) {
        this.dt_venda = dt_venda;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public float getVl_venda() {
        return vl_venda;
    }
    
    public String getTxtVl_venda() {
        return new DecimalFormat("#,##0.00").format(vl_venda);
    }

    public void setVl_venda(float vl_venda) {
        this.vl_venda = vl_venda;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nr_sequencia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        return Objects.equals(this.nr_sequencia, other.nr_sequencia);
    }

    @Override
    public String toString() {
        return "Venda{" + "nr_sequencia=" + nr_sequencia + ", produtos=" + produto + ", pessoa_fisica=" + pessoa_fisica + ", dt_venda=" + dt_venda + ", formaPagamento=" + formaPagamento + ", vl_venda=" + vl_venda + '}';
    }

}
