package br.com.tiaAmanda.modelo.bean;

import java.text.DecimalFormat;
import java.util.Objects;

public class Produto {

    private Integer cd_produto;
    private String ds_produto;
    private String cd_barras;
    private char ie_situacao;
    private float vl_avista;
    private float vl_aprazo;
    private Unidade unidade;

    public Produto(Integer cd_produto) {
        this.cd_produto = cd_produto;
    }
    
    public Produto() {
    }

    public Integer getCd_produto() {
        return cd_produto;
    }

    public void setCd_produto(Integer cd_produto) {
        this.cd_produto = cd_produto;
    }

    public String getDs_produto() {
        return ds_produto;
    }

    public void setDs_produto(String ds_produto) {
        this.ds_produto = ds_produto;
    }

    public String getCd_barras() {
        return cd_barras;
    }

    public void setCd_barras(String cd_barras) {
        this.cd_barras = cd_barras;
    }

    public char getIe_situacao() {
        return ie_situacao;
    }

    public void setIe_situacao(char ie_situacao) {
        this.ie_situacao = ie_situacao;
    }

    public float getVl_avista() {
        return vl_avista;
    }
    
    public String getTxtVl_avista() {
        return new DecimalFormat("#,##0.00").format(vl_avista);
    }

    public void setVl_avista(float vl_avista) {
        this.vl_avista = vl_avista;
    }

    public float getVl_aprazo() {
        return vl_aprazo;
    }
    
    public String getTxtVl_aprazo() {
        return new DecimalFormat("#,##0.00").format(vl_aprazo);
    }

    public void setVl_aprazo(float vl_aprazo) {
        this.vl_aprazo = vl_aprazo;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.cd_produto);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.cd_produto, other.cd_produto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "cd_produto=" + cd_produto + ", ds_produto=" + ds_produto + ", cd_barras=" + cd_barras + ", ie_situacao=" + ie_situacao + ", vl_avista=" + vl_avista + ", vl_aprazo=" + vl_aprazo + ", unidade=" + unidade + '}';
    }

}
