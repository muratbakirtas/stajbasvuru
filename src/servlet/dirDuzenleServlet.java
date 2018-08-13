package servlet;

import com.mysql.cj.protocol.WriterWatcher;
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

@WebServlet(name = "dirDuzenleServlet",urlPatterns = "/dirDuzenleServlet")
public class dirDuzenleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        PrintWriter out = response.getWriter();
        int dirid = Integer.parseInt(request.getParameter("did"));
        String adi = request.getParameter("ad");
        String  adres =request.getParameter("adres");
        Db db=new Db();
        try {
            db.direktorlukGüncelle(dirid,adi,adres);
            request.setAttribute("errorMessage8", "Direktörlük düzenlendi!");
            RequestDispatcher rd = request.getRequestDispatcher("/adminProfil.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
