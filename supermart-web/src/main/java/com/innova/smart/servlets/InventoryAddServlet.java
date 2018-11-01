package com.innova.smart.servlets;

import com.innova.smart.beans.Product;
import com.innova.smart.dao.Provider;
import com.innova.smart.dao.SupplierDAO;
import com.innova.smart.dao.impl.SupplierDAOImpl;

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
@WebServlet("/inventory-add")
public class InventoryAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Connection con;
    private SupplierDAO supplierDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        /*try {
            String un = this.getServletContext().getInitParameter("dbusername");
            String pass = this.getServletContext().getInitParameter("dbpassword");
            con = Provider.getCon(un, pass);

            supplierDAO = new SupplierDAOImpl(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("suppliers", supplierDAO.findAll());

        RequestDispatcher rd = req.getRequestDispatcher("inventory-add.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String p_name = req.getParameter("p_name"),
                p_category = req.getParameter("p_category"),
                p_supplier = req.getParameter("p_supplier");
        int p_quantity = Integer.parseInt(req.getParameter("p_quantity"));
        float p_price =Float.parseFloat(req.getParameter("p_price"));

        Product p = new Product(p_name, p_category, p_supplier, p_quantity, p_price);
        System.out.println(p);
    }

    @Override
    public void destroy() {
        /*try {
            if (!con.isClosed())
                con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }*/
        super.destroy();
    }
}
