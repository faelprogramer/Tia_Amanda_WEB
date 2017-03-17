package br.com.tiaAmanda.modelo.bean;

import java.util.Objects;

public class PessoaFisica {

    private Integer cd_pessoa_fisica;
    private String nm_pessoa_fisica;
    private String ds_telefone;
    private int nr_compra_fidelidade;
    private String ds_endereco;
    private String nr_endereco;
    private String ds_complemento;
    private String ds_bairro;
    private String ds_observacao;

    public PessoaFisica(Integer cd_pessoa_fisica) {
        this.cd_pessoa_fisica = cd_pessoa_fisica;
    }

    public PessoaFisica() {
    }

    public Integer getCd_pessoa_fisica() {
        return cd_pessoa_fisica;
    }

    public void setCd_pessoa_fisica(Integer cd_pessoa_fisica) {
        this.cd_pessoa_fisica = cd_pessoa_fisica;
    }

    public String getNm_pessoa_fisica() {
        return nm_pessoa_fisica;
    }

    public void setNm_pessoa_fisica(String nm_pessoa_fisica) {
        this.nm_pessoa_fisica = nm_pessoa_fisica;
    }

    public String getDs_telefone() {
        return ds_telefone;
    }

    public void setDs_telefone(String ds_telefone) {
        ds_telefone = removerMascaraTelefone(ds_telefone);
        this.ds_telefone = ds_telefone;
    }

    private String removerMascaraTelefone(String ds_telefone1) {
        if (ds_telefone1 != null) {
            ds_telefone1 = ds_telefone1.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
            return ds_telefone;
        } else {
            return null;
        }
    }

    public int getNr_compra_fidelidade() {
        return nr_compra_fidelidade;
    }

    public void setNr_compra_fidelidade(int nr_compra_fidelidade) {
        this.nr_compra_fidelidade = nr_compra_fidelidade;
    }

    public String getDs_endereco() {
        return ds_endereco;
    }

    public void setDs_endereco(String ds_endereco) {
        this.ds_endereco = ds_endereco;
    }

    public String getNr_endereco() {
        return nr_endereco;
    }

    public void setNr_endereco(String nr_endereco) {
        this.nr_endereco = nr_endereco;
    }

    public String getDs_complemento() {
        return ds_complemento;
    }

    public void setDs_complemento(String ds_complemento) {
        this.ds_complemento = ds_complemento;
    }

    public String getDs_bairro() {
        return ds_bairro;
    }

    public void setDs_bairro(String ds_bairro) {
        this.ds_bairro = ds_bairro;
    }

    public String getDs_observacao() {
        return ds_observacao;
    }

    public void setDs_observacao(String ds_observacao) {
        this.ds_observacao = ds_observacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.cd_pessoa_fisica);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final PessoaFisica other = (PessoaFisica) obj;
        return Objects.equals(this.cd_pessoa_fisica, other.cd_pessoa_fisica);
    }

    @Override
    public String toString() {
        return "PessoaFisica{" + "cd_pessoa=" + cd_pessoa_fisica + ", nm_pessoa_fisica=" + nm_pessoa_fisica + ", ds_telefone=" + ds_telefone + ", nr_compra_fidelidade=" + nr_compra_fidelidade + ", ds_endereco=" + ds_endereco + ", nr_endereco=" + nr_endereco + ", ds_complemento=" + ds_complemento + ", ds_bairro=" + ds_bairro + ", ds_observacao=" + ds_observacao + '}';
    }

}
