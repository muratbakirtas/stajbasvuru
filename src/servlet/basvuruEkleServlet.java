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
import java.util.Date;
import java.sql.SQLException;

@WebServlet(name = "basvuruEkleServlet", urlPatterns = "/basvuruEkleServlet")
public class basvuruEkleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        PrintWriter out = response.getWriter();
        String date = new Date().toString();
        int ilanid = Integer.parseInt(request.getParameter("id"));
        String durum = "Onay bekliyor";
        Db db = new Db();
        boolean x = false;
        try {
            long tc = db.stajyer(user);
            if (tc ==0) {
                session.invalidate();
                request.setAttribute("errorMessage1", "Sisteme giriş yapmış olmanız gerekmektedir!");
                RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
            x = db.basvuruKontrol(tc, ilanid);
            if (x == true) {
                request.setAttribute("errorMessage2", "Başvuru zaten yapılmış!");
                RequestDispatcher rd = request.getRequestDispatcher("/ilanDetay.jsp");
                rd.forward(request, response);
            } else {
                db.basvuruEkle(tc, date, ilanid, durum);
                request.setAttribute("errorMessage3", "Başvurunuz alındı!");
                RequestDispatcher rd = request.getRequestDispatcher("/ilanDetay.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
