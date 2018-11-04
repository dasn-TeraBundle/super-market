package com.innova.smart.web.servlets;

import com.innova.smart.beans.Invoice;
import com.innova.smart.beans.Product;
import com.innova.smart.dao.Provider;
import com.innova.smart.service.InventoryService;
import com.innova.smart.service.InvoiceService;
import com.innova.smart.service.impl.InventoryServiceImpl;
import com.innova.smart.service.impl.InvoiceServiceImpl;

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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nirupam on 01-11-2018.
 */
@WebServlet("/invoice")
public class InvoiceDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection con;
    private InvoiceService invoiceService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String un = this.getServletContext().getInitParameter("dbusername");
            String pass = this.getServletContext().getInitParameter("dbpassword");
            con = Provider.getCon(un, pass);
            con.setAutoCommit(false);

            invoiceService = new InvoiceServiceImpl(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Invoice inv = invoiceService.find(req.getParameter("id"));
        double total = inv.getProducts().stream().mapToDouble(Product::getPrice).sum();

        req.setAttribute("invoice", inv);
        req.setAttribute("total", (float) total);

        RequestDispatcher rd = req.getRequestDispatcher("invoice.jsp");
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
