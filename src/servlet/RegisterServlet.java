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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        long tc = Long.parseLong(request.getParameter("tc"));
        String adi = request.getParameter("ad");
        String okul = request.getParameter("okul");
        String bolum = request.getParameter("bolumu");
        int sinif = Integer.parseInt(request.getParameter("sinifi"));
        String dogum = request.getParameter("dtarihi");
        String cinsiyet = request.getParameter("cinsiyet");
        double ort = Double.parseDouble(request.getParameter("ort"));
        String mail = request.getParameter("email");
        String sifre = request.getParameter("sifre");
        Db db = new Db();

        MessageDigest mdAlgorithm = null;
        try {
            mdAlgorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        mdAlgorithm.update(sifre.getBytes());

        byte[] digest = mdAlgorithm.digest();
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < digest.length; i++) {
            sifre = Integer.toHexString(0xFF & digest[i]);

            if (sifre.length() < 2) {
                sifre = "0" + sifre;
            }

            hexString.append(sifre);
        }
        try {

                db.stajyerEkle(tc, adi, cinsiyet, okul, bolum, sinif, ort, dogum, mail, hexString.toString());
            request.setAttribute("errorMessage16", "Kaydınız başarıyla oluşturuldu!");
            RequestDispatcher rd = request.getRequestDispatcher("/register.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            response.sendRedirect("index.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

    }
}
