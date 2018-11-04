package com.innova.smart.web.servlets;

import com.innova.smart.beans.User;
import com.innova.smart.dao.Provider;
import com.innova.smart.service.UserService;
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
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nirupam on 01-11-2018.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy");

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("user-create.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name"),
                surname = req.getParameter("surname"),
                address = req.getParameter("address"),
                bd = req.getParameter("birthday"),
                empd = req.getParameter("employment_date"),
                role = req.getParameter("role"),
                username = req.getParameter("username"),
                password = req.getParameter("password");
        float salary = Float.parseFloat(req.getParameter("salary"));

        User user = new User(name, role, username, password);
        user.setSurname(surname);
        user.setAddress(address);
        user.setSalary(salary);
        try {
            Date birthday = DATE_FORMATTER.parse(bd);
            Date emp_date = DATE_FORMATTER.parse(empd);
            user.setBirthday(birthday);
            user.setEmploymentDate(emp_date);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Required Date Format dd-MM-yyyy");
        }

        user = userService.register(user);

        if (user != null)
            resp.sendRedirect("register");
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
