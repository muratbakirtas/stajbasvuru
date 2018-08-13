package servlet;

import database.Db;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "AdminLoginServlet", urlPatterns = "/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        HttpSession session = request.getSession();
        Db db = new Db();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        MessageDigest mdAlgorithm = null;
        try {
            mdAlgorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        mdAlgorithm.update(password.getBytes());

        byte[] digest = mdAlgorithm.digest();
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < digest.length; i++) {
            password = Integer.toHexString(0xFF & digest[i]);

            if (password.length() < 2) {
                password = "0" + password;
            }

            hexString.append(password);
        }

        boolean x = false;
        try {
            x = db.adminCek(username, hexString.toString());
            if (x == true) {
                session.setAttribute("admin_username", username);
                session.setAttribute("admin_password", hexString.toString());
                response.sendRedirect("/adminProfil.jsp");
            } else {
                session.invalidate();
                request.setAttribute("errorMessage", "Sorry, username or password error!");
                RequestDispatcher rd = request.getRequestDispatcher("/adminLogin.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException e) {
            response.sendRedirect("/index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
