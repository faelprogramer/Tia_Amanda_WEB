package br.com.tiaAmanda.model.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMesssage {

    public static final int ERROR = 0;
    public static final int FATAL = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;

    public static void addMessage(int tipoMsg, String titulo, String msg) {
        switch (tipoMsg) {
            case 0:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, msg));
                break;
            case 1:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo, msg));
                break;
            case 2:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, msg));
                break;
            case 3:
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, msg));
                break;
        }
    }
    
}
