package com.innova.smart.web.servlets;

import com.innova.smart.dao.Provider;
import com.innova.smart.service.InventoryService;
import com.innova.smart.service.impl.InventoryServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Nirupam on 01-11-2018.
 */
@WebServlet("/inventory-list")
public class InventoryListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection con;
    private InventoryService inventoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String un = this.getServletContext().getInitParameter("dbusername");
            String pass = this.getServletContext().getInitParameter("dbpassword");
            con = Provider.getCon(un, pass);
            con.setAutoCommit(false);

            inventoryService = new InventoryServiceImpl(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", inventoryService.findAll());

        RequestDispatcher rd = req.getRequestDispatcher("inventory-list.jsp");
        rd.forward(req, resp);
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
