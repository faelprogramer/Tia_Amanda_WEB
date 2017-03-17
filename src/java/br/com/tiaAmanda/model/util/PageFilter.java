package br.com.tiaAmanda.model.util;


import br.com.tiaAmanda.model.bean.Usuario;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageFilter implements Filter {

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        /*
        HttpSession sess = ((HttpServletRequest) request).getSession(true);

        String newCurrentPage = ((HttpServletRequest) request).getServletPath();

        if (sess.getAttribute("currentPage") == null) {
            sess.setAttribute("lastPage", newCurrentPage);
            sess.setAttribute("currentPage", newCurrentPage);
        } else {

            String oldCurrentPage = sess.getAttribute("currentPage").toString();
            if (!oldCurrentPage.equals(newCurrentPage)) {
                sess.setAttribute("lastPage", oldCurrentPage);
                sess.setAttribute("currentPage", newCurrentPage);
            }
        }

        chain.doFilter(request, response);
         */
        HttpSession session = ((HttpServletRequest) request).getSession();
        Usuario usuario = (Usuario) session.getAttribute("currentSessionUser");
        
        if (usuario == null) {
            session.setAttribute("MSG", "Não é possível acessar essa página sem estar logado no sistema!");
            ((HttpServletResponse) response).sendRedirect("../login.jsf");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

}
