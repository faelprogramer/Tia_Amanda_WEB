package br.com.tiaAmanda.control;

import br.com.tiaAmanda.model.bean.Usuario;
import br.com.tiaAmanda.model.bean.dao.UsuarioDAO;
import br.com.tiaAmanda.model.session.SessionContext;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB {

    private final UsuarioDAO usuarioDAO;
    private Usuario usuario;

    public LoginMB() {
        usuarioDAO = new UsuarioDAO();
        usuario = new Usuario();
        usuario.setNm_usuario("Rafael");
    }

    public void autenticar() {
        if (usuarioDAO.autenticarUsuario(usuario)) {
            SessionContext sc = SessionContext.getInstance();
            sc.setAttribute("currentSessionUser", usuario);
            redirectPage("/view/home.jsf"); //return "/welcome/welcome?faces-redirect = true";
        } else {
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
                            "Erro no Login!"));
        }

    }

    private void redirectPage(String page) {
        ExternalContext ec = SessionContext.getInstance().getCurrentExternalContext();
        String contextPage = ec.getApplicationContextPath().concat(page);
        System.out.println(page);
        try {
            ec.redirect(contextPage);
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível redirecionar a página!",
                            "Erro!"));
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
