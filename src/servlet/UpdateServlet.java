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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "UpdateServlet", urlPatterns = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        HttpSession session = request.getSession();
        Db db = new Db();
        String user = (String) session.getAttribute("username");
        long tc = 0;
        try {
            tc = db.stajyer(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String adi = request.getParameter("ad");
        String okul = request.getParameter("okul");
        String bolum = request.getParameter("bolumu");
        int sinif = Integer.parseInt(request.getParameter("sinifi"));
        String dogum = request.getParameter("dtarihi");
        String cinsiyet = request.getParameter("cinsiyet");
        double ort = Double.parseDouble(request.getParameter("ort"));
        String mail = request.getParameter("email");
        String sifre = request.getParameter("sifre");

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

            db.stajyerGüncelle(tc, adi, cinsiyet, okul, bolum, sinif, ort, dogum, mail, hexString.toString());
            request.setAttribute("errorMessage4", "Bilgileriniz güncellendi!");
            RequestDispatcher rd = request.getRequestDispatcher("/stajyerBilgiGuncelle.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            response.sendRedirect("/profil.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
