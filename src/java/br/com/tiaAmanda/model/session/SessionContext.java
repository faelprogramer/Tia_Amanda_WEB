package br.com.tiaAmanda.model.session;


import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class SessionContext {

    private static SessionContext instance;

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    private SessionContext() {

    }

    public ExternalContext getCurrentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public void encerrarSessao() {
        getCurrentExternalContext().invalidateSession();
    }

    public Object getAttribute(String nome) {
        return getCurrentExternalContext().getSessionMap().get(nome);
    }

    public void setAttribute(String nome, Object valor) {
        getCurrentExternalContext().getSessionMap().put(nome, valor);
    }

}
