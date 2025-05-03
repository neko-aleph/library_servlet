package org.example.java_servlet.app.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/book")
public class AuthFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        boolean loggedIn = session != null && session.getAttribute("user") != null;

        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("index.jsp");
        }
    }
}
