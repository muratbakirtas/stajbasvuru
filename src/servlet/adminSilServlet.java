package servlet;

import database.Db;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "adminSilServlet",urlPatterns = "/adminSilServlet")
public class adminSilServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        int adminid = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        Db db =new Db();
        try {
            db.adminSil(adminid);
            out.println("<script type=\"text/javascript\">");
            request.setAttribute("errorMessage7", "Admin silindi!");
            RequestDispatcher rd = request.getRequestDispatcher("/adminProfil.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
