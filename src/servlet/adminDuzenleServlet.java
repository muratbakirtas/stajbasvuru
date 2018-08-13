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

@WebServlet(name = "adminDuzenleServlet",urlPatterns = "/adminDuzenleServlet")
public class adminDuzenleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("ISO-8859-9");
        String responseEncoding = response.getCharacterEncoding();
        System.out.println("responseEncoding:" + responseEncoding);
        PrintWriter out = response.getWriter();
        int adminid = Integer.parseInt(request.getParameter("adminid"));
        String adi = request.getParameter("ad");
        String  email =request.getParameter("email");
        String  sifre =request.getParameter("sifre");
        String direktorluk=request.getParameter("direktorlukler");

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

        Db db=new Db();
        try {
            db.adminGüncelle(adminid,adi,email,hexString.toString(),direktorluk);
            request.setAttribute("errorMessage5", "Admin bilgileri güncellendi!");
            RequestDispatcher rd = request.getRequestDispatcher("/adminProfil.jsp");
            rd.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
