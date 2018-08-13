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
import java.util.Date;

@WebServlet(name = "ilanDuzenleServlet",urlPatterns = "/ilanDuzenleServlet")
public class ilanDuzenleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        HttpSession session=request.getSession();
        String ilanadi = request.getParameter("ilanadi");
        String aciklama =request.getParameter("aciklama");
        String bolum= request.getParameter("bolumu");
        int minsinif = Integer.parseInt(request.getParameter("minsinif"));
        double  ort = Double.parseDouble(request.getParameter("minort"));

        String ytarihi = request.getParameter("ytarihi");

        String  son =request.getParameter("son");
        int dirid = (int) session.getAttribute("dirid");
        int ilanid = Integer.parseInt(request.getParameter("did"));
        Db db =new Db();
        try {
            db.ilanGüncelle(dirid,ilanadi,ilanid,aciklama,bolum,minsinif,ort,ytarihi,son);
            request.setAttribute("errorMessage11", "İlan Düzenlendi!");
            RequestDispatcher rd = request.getRequestDispatcher("/adminProfil.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
