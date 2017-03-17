package br.com.tiaAmanda.model.bean;

public class Usuario {
    
    private String nm_usuario;
    private String ds_senha;

    public Usuario(String nm_usuario, String ds_senha) {
        this.nm_usuario = nm_usuario;
        this.ds_senha = ds_senha;
    }

    public Usuario() {}

    public String getNm_usuario() {
        return nm_usuario;
    }

    public void setNm_usuario(String nm_usuario) {
        this.nm_usuario = nm_usuario;
    }

    public String getDs_senha() {
        return ds_senha;
    }

    public void setDs_senha(String ds_senha) {
        this.ds_senha = ds_senha;
    }
    
}
