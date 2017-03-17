package br.com.tiaAmanda.model.bean.dao;

import br.com.tiaAmanda.model.bean.Usuario;

public class UsuarioDAO {
    
    public boolean autenticarUsuario(Usuario u) {
        if (u == null) {
            return false;
        }
        return u.getDs_senha().equalsIgnoreCase("admin");
    }
}
