package com.innova.smart.web.security;

import com.innova.smart.beans.User;
import com.innova.smart.exceptions.UnauthorizedException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/inventory-add", "/inventory-list"})
public class AdminFilter implements Filter {

    public AdminFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.invalidate();
            resp.sendRedirect("login.jsp");
        } else {
            if (user.getRole() != User.Role.ADMIN) {
                resp.setStatus(401);
                throw new UnauthorizedException("You are not authorized");
            } else
                chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
