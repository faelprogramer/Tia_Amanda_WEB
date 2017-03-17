package br.com.tiaAmanda.modelo.bean;

import java.util.Objects;

public class Unidade {
    
    private String cd_unidade;
    private String ds_unidade;
    private char ie_situacao;

    public Unidade() {
    }

    public Unidade(String cd_unidade) {
        this.cd_unidade = cd_unidade;
    }
    
    public String getCd_unidade() {
        return cd_unidade;
    }

    public void setCd_unidade(String cd_unidade) {
        this.cd_unidade = cd_unidade;
    }

    public String getDs_unidade() {
        return ds_unidade;
    }

    public void setDs_unidade(String ds_unidade) {
        this.ds_unidade = ds_unidade;
    }

    public char getIe_situacao() {
        return ie_situacao;
    }

    public void setIe_situacao(char ie_situacao) {
        this.ie_situacao = ie_situacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.cd_unidade);
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
        final Unidade other = (Unidade) obj;
        if (!Objects.equals(this.cd_unidade, other.cd_unidade)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cd_unidade;
    }

}
