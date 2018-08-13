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
import java.sql.SQLException;

@WebServlet(name = "onayKaldirServlet",urlPatterns = "/onayKaldirServlet")
public class onayKaldirServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        HttpSession session = request.getSession();
        int ilanid = (int) session.getAttribute("ilanid");
        long tc = Long.parseLong(request.getParameter("id"));
        String durum = "Reddedildi";
        PrintWriter out = response.getWriter();
        Db db = new Db();
        try {
            db.onayla(tc, ilanid,durum);
            request.setAttribute("errorMessage14", "Stajyer onayı kaldırıldı!");
            RequestDispatcher rd = request.getRequestDispatcher("/adminProfil.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
