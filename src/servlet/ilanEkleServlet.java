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

@WebServlet(name = "ilanEkleServlet",urlPatterns = "/ilanEkleServlet")
public class ilanEkleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        int direktorid= Integer.parseInt(request.getParameter("direktorlukler"));
        String ilanadi = request.getParameter("ilanadi");
        String  son =request.getParameter("son");
        String bolum= request.getParameter("bolumu");
        int minsinif = Integer.parseInt(request.getParameter("minsinif"));
        String ytarihi = new java.util.Date().toString();
        double  ort = Double.parseDouble(request.getParameter("minort"));
        String aciklama =request.getParameter("aciklama");

        Db db =new Db();
        try {
            db.ilanEkle(direktorid,ilanadi,aciklama,bolum,minsinif,ort,ytarihi,son);
            request.setAttribute("errorMessage12", "İlan Eklendi!");
            RequestDispatcher rd = request.getRequestDispatcher("/adminProfil.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
