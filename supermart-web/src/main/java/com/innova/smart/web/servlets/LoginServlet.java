package com.innova.smart.web.servlets;

import com.innova.smart.beans.Product;
import com.innova.smart.beans.User;
import com.innova.smart.dao.ProductCategoryDAO;
import com.innova.smart.dao.Provider;
import com.innova.smart.dao.SupplierDAO;
import com.innova.smart.dao.impl.ProductCategoryDAOImpl;
import com.innova.smart.dao.impl.SupplierDAOImpl;
import com.innova.smart.service.InventoryService;
import com.innova.smart.service.UserService;
import com.innova.smart.service.impl.InventoryServiceImpl;
import com.innova.smart.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Nirupam on 01-11-2018.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection con;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String un = this.getServletContext().getInitParameter("dbusername");
            String pass = this.getServletContext().getInitParameter("dbpassword");
            con = Provider.getCon(un, pass);
            con.setAutoCommit(false);

            userService = new UserServiceImpl(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username"),
                password = req.getParameter("password");

        User user = userService.login(username, password);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        if (user.getRole() == User.Role.ADMIN)
            resp.sendRedirect("inventory-add");
        else if (user.getRole() == User.Role.CASHIER)
            resp.sendRedirect("billing");
    }

    @Override
    public void destroy() {
        try {
            if (!con.isClosed())
                con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        super.destroy();
    }
}
