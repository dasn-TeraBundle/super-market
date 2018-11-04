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

/**
 * Created by Nirupam on 01-11-2018.
 */
@WebServlet("/invoice-create")
public class InvoiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection con;
    private InventoryService inventoryService;
    private InvoiceService invoiceService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String un = this.getServletContext().getInitParameter("dbusername");
            String pass = this.getServletContext().getInitParameter("dbpassword");
            con = Provider.getCon(un, pass);
            con.setAutoCommit(false);

            inventoryService = new InventoryServiceImpl(con);
            invoiceService = new InvoiceServiceImpl(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", inventoryService.findAll());

        RequestDispatcher rd = req.getRequestDispatcher("invoice-create.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> prods = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            String pid = req.getParameter("pr_" + i);
            if (pid != null) {
                if (pid.length() > 0) {
                    int q = Integer.parseInt(req.getParameter("quant_" + i));
                    Product e = new Product("", "", "", q, 0);
                    e.setId(pid);
                    prods.add(e);
                }
            } else
                break;
        }

        Invoice inv = invoiceService.create(prods);
        resp.sendRedirect("invoice?id=" + inv.getId());
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
