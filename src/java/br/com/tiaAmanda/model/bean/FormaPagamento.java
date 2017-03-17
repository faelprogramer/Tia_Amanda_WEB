package br.com.tiaAmanda.modelo.bean;

import java.util.Objects;

public class FormaPagamento {
    
    private Integer cd_forma_pagamento;
    private String ds_forma_pagamento;
    private char ie_situacao;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer cd_forma_pagamento) {
        this.cd_forma_pagamento = cd_forma_pagamento;
    }

    public Integer getCd_forma_pagamento() {
        return cd_forma_pagamento;
    }

    public void setCd_forma_pagamento(Integer cd_forma_pagamento) {
        this.cd_forma_pagamento = cd_forma_pagamento;
    }

    public String getDs_forma_pagamento() {
        return ds_forma_pagamento;
    }

    public void setDs_forma_pagamento(String ds_forma_pagamento) {
        this.ds_forma_pagamento = ds_forma_pagamento;
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
        hash = 79 * hash + Objects.hashCode(this.cd_forma_pagamento);
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
        final FormaPagamento other = (FormaPagamento) obj;
        if (!Objects.equals(this.cd_forma_pagamento, other.cd_forma_pagamento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cd_forma_pagamento + " - " + ds_forma_pagamento;
    }
    
}
