package database;

import profil.*;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Db {


    static Connection connection;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/türksatdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8 ", "root", "12345");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean stajyerKontrol(String email, String password) throws SQLException {


        Connection connection = getConnection();

        ResultSet rs = null;

        String query = "SELECT * FROM stajyer WHERE stajyeremail= ? AND stajyersifre= ?";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setString(1, email);
            psmt.setString(2, password);
            rs = psmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return false;
    }

    public int stajyerEkle(long tc, String adi, String cinsiyet, String okul, String bolum, int sinif, double ort, String dogum, String mail, String sifre) throws SQLException {
        Connection connection = getConnection();

        Statement st = null;

        String query = "INSERT INTO stajyer (ogrenci_tc,ogrenciOrtalama,ogrenciAdi,ogrenciCinsiyeti,ogrenciOkul,ogrencibolumu,stajyeremail,stajyersifre,stajyersinifi,stajyerdogumtarihi) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setLong(1, tc);
            psmt.setDouble(2, Double.valueOf(ort));
            psmt.setString(3, adi);
            psmt.setString(4, cinsiyet);
            psmt.setString(5, okul);
            psmt.setString(6, bolum);
            psmt.setString(7, mail);
            psmt.setString(8, sifre);
            psmt.setInt(9, Integer.valueOf(sinif));
            psmt.setString(10, dogum);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public int stajyerGüncelle(long tc, String adi, String cinsiyet, String okul, String bolum, int sinif, double ort,
                               String dogum, String mail, String sifre) throws SQLException {
        Connection connection = getConnection();

        String query = "UPDATE stajyer SET ogrenciOrtalama=?,ogrenciAdi=?,ogrenciCinsiyeti=?,ogrenciOkul=?," +
                "ogrencibolumu=?,stajyeremail=?, stajyersifre=?,stajyersinifi=?,stajyerdogumtarihi=? where ogrenci_tc=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setDouble(1, Double.valueOf(ort));
            psmt.setString(2, adi);
            psmt.setString(3, cinsiyet);
            psmt.setString(4, okul);
            psmt.setString(5, bolum);
            psmt.setString(6, mail);
            psmt.setString(7, sifre);
            psmt.setInt(8, Integer.valueOf(sinif));
            psmt.setString(9, dogum);
            psmt.setLong(10, tc);

            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public boolean adminCek(String email, String password) throws SQLException {


        Connection connection = getConnection();

        ResultSet rs = null;

        try {
            String query = "SELECT * FROM admin WHERE adminEmail =? AND adminSifre = ?";
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setString(1, email);
            psmt.setString(2, password);
            rs = psmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return false;
    }

    public ArrayList<direktorluk> direktorlukleriCek() throws SQLException {

        ArrayList<direktorluk> direktorluks = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs;

        try {
            String query = "SELECT * FROM direktorlukler ";
            PreparedStatement psmt = connection.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next()) {
                direktorluk dir = new direktorluk();
                dir.setDirekid(rs.getInt("iddirektorlukler"));
                dir.setDirekad(rs.getString("direktorluklerAdi"));
                dir.setAdres(rs.getString("direktorlukler_adres"));
                direktorluks.add(dir);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return direktorluks;
    }

    public ArrayList<direktorluk> direktorluklerCek() throws SQLException {

        ArrayList<direktorluk> direktorluks = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs;

        try {
            String query = "SELECT * FROM direktorlukler d join  ilanlar i on d.iddirektorlukler = i.direktorid";
            PreparedStatement psmt = connection.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next()) {
                direktorluk dir = new direktorluk();
                dir.setDirekid(rs.getInt("iddirektorlukler"));
                dir.setDirekad(rs.getString("direktorluklerAdi"));
                dir.setAdres(rs.getString("direktorlukler_adres"));
                direktorluks.add(dir);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return direktorluks;
    }


    public int adminEkle(String adi, String mail, String sifre, String direktorluk) throws SQLException {
        Connection connection = getConnection();


        PreparedStatement pst1 = connection.prepareStatement("select max(idadmin)+1 from admin");
        ResultSet rs = pst1.executeQuery();
        int user_id = 0;
        if (rs.next()) {
            user_id = rs.getInt(1);
        }
        String que = "INSERT INTO admin (idadmin,admiAd,adminSifre,adminEmail,adminDirektorluk) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(que);
            pst.setInt(1, user_id);
            pst.setString(2, adi);
            pst.setString(3, sifre);
            pst.setString(4, mail);
            pst.setString(5, direktorluk);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public int direktorEkle(String adi, String adres) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement pst1 = connection.prepareStatement("select max(iddirektorlukler)+1 from direktorlukler");
        ResultSet rs = pst1.executeQuery();
        int user_id = 0;
        if (rs.next()) {
            user_id = rs.getInt(1);
        }
        String que = "INSERT INTO direktorlukler (iddirektorlukler,direktorluklerAdi,direktorlukler_adres) VALUES (?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(que);
            pst.setInt(1, user_id);
            pst.setString(2, adi);
            pst.setString(3, adres);
            return pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public int ilanEkle(int direktorlukid, String ilanadi, String acıklama, String bolum, int sinif, double ort, String tarih, String son) throws SQLException {
        Connection connection = getConnection();

        Statement st = null;
        try {

            PreparedStatement pst1 = connection.prepareStatement("select max(ilan_id)+1 from ilanlar");
            ResultSet rs = pst1.executeQuery();
            int user_id = 0;
            if (rs.next()) {
                user_id = rs.getInt(1);
            }
            String que = "INSERT INTO ilanlar (direktorid,ilanadi,ilan_id,aciklama,bolum,min_sinif,min_ort,yayin_tarihi,son_basvur) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = connection.prepareStatement(que);
            pst.setInt(1, direktorlukid);
            pst.setString(2, ilanadi);
            pst.setInt(3, user_id);
            pst.setString(4, acıklama);
            pst.setString(5, bolum);
            pst.setInt(6, sinif);
            pst.setDouble(7, ort);
            pst.setString(8, tarih);
            pst.setString(9, son);


            return pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }


    public ArrayList<ilan> ilanlariCek(int dirid) throws SQLException {

        ArrayList<ilan> ilans = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs;
        String query = "SELECT * FROM ilanlar i  join direktorlukler d on d.iddirektorlukler = i.direktorid where direktorid=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setInt(1,dirid);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ilan il = new ilan();
                il.setDirekid(rs.getInt("direktorid"));
                il.setIlanadi(rs.getString("ilanadi"));
                il.setIlanid(rs.getInt("ilan_id"));
                il.setAciklama(rs.getString("aciklama"));
                il.setBolum(rs.getString("bolum"));
                il.setMinSinif(rs.getInt("min_sinif"));
                il.setMinOrt(rs.getDouble("min_ort"));
                il.setYayintarihi(rs.getString("yayin_tarihi"));
                il.setSonbasvuru(rs.getString("son_basvur"));
                il.setDirektorAdi(rs.getString("direktorluklerAdi"));
                il.setAdres(rs.getString("direktorlukler_adres"));
                ilans.add(il);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return ilans;
    }

    public ArrayList<Admin> adminCek() throws SQLException {

        ArrayList<Admin> admins = new ArrayList<>();

        Connection connection = getConnection();
        ResultSet rs;
        String query = "SELECT * from admin";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next()) {

                Admin ad = new Admin();
                ad.setIdadmin(rs.getInt("idadmin"));
                ad.setAd(rs.getString("admiAd"));
                ad.setSifre(rs.getString("adminSifre"));
                ad.setEmail(rs.getString("adminEmail"));
                ad.setDirektorluk(rs.getString("adminDirektorluk"));
                admins.add(ad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return admins;
    }

    public long stajyer(String email) throws SQLException {

        Connection connection = getConnection();
        ResultSet rs;

        stajyer il = new stajyer();

        String query = "SELECT * FROM stajyer where stajyeremail=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setString(1, email);
            rs = psmt.executeQuery();
            if (rs.next()) {
                il.setOgrencitc(rs.getLong("ogrenci_tc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return il.getOgrencitc();
    }

    public int basvuruEkle(long tc, String tarih, int ilanid, String durum) throws SQLException {

        Connection connection = getConnection();
        String query = "INSERT INTO basvuranlar (basvuranTc,basvuruTarihi,ilan_id,durumu) " +
                "VALUES (?,?,?,?)";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setLong(1, tc);
            psmt.setString(2, tarih);
            psmt.setInt(3, ilanid);
            psmt.setString(4, durum);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public boolean basvuruKontrol(long tc, int ilanid) throws SQLException {
        Connection connection = getConnection();
        ResultSet rs = null;
        String query = "SELECT * FROM basvuranlar WHERE basvuranTc =? AND ilan_id =?";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setLong(1, tc);
            psmt.setInt(2, ilanid);
            rs = psmt.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return false;
    }

    public ArrayList<stajyer> stajyerCek(long tc) throws SQLException {

        ArrayList<stajyer> stajyers = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs;
        String query = "SELECT * FROM stajyer where ogrenci_tc=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setLong(1, tc);
            rs = psmt.executeQuery();
            while (rs.next()) {
                stajyer bas = new stajyer();
                bas.setOgrenciOrtalama(rs.getDouble("ogrenciOrtalama"));
                bas.setOgrenciAdi(rs.getString("ogrenciAdi"));
                bas.setOgrenciCin(rs.getString("ogrenciCinsiyeti"));
                bas.setOkulu(rs.getString("ogrenciOkul"));
                bas.setBolum(rs.getString("ogrencibolumu"));
                bas.setEmail(rs.getString("stajyeremail"));
                bas.setSifre(rs.getString("stajyersifre"));
                bas.setOgrenciCin(rs.getString("ogrenciCinsiyeti"));
                bas.setSinifi(rs.getInt("stajyersinifi"));
                bas.setDogum(rs.getString("stajyerdogumtarihi"));
                stajyers.add(bas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return stajyers;
    }

    public ArrayList<ilan> ilanlariCek() throws SQLException {
        ArrayList<ilan> ilans = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs;
        String query = "SELECT * FROM ilanlar i  join direktorlukler d on d.iddirektorlukler = i.direktorid ";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ilan il = new ilan();
                il.setDirekid(rs.getInt("direktorid"));
                il.setIlanadi(rs.getString("ilanadi"));
                il.setIlanid(rs.getInt("ilan_id"));
                il.setAciklama(rs.getString("aciklama"));
                il.setBolum(rs.getString("bolum"));
                il.setMinSinif(rs.getInt("min_sinif"));
                il.setMinOrt(rs.getDouble("min_ort"));
                il.setYayintarihi(rs.getString("yayin_tarihi"));
                il.setSonbasvuru(rs.getString("son_basvur"));
                il.setDirektorAdi(rs.getString("direktorluklerAdi"));
                il.setAdres(rs.getString("direktorlukler_adres"));
                ilans.add(il);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return ilans;
    }

    public ArrayList<direktorluk> direktorlukCek(int id) throws SQLException {
        ArrayList<direktorluk> direktorluks = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs;
        String query = "SELECT * FROM direktorlukler where iddirektorlukler=?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                direktorluk dir = new direktorluk();
                dir.setDirekid(rs.getInt("iddirektorlukler"));
                dir.setDirekad(rs.getString("direktorluklerAdi"));
                dir.setAdres(rs.getString("direktorlukler_adres"));
                direktorluks.add(dir);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return direktorluks;
    }

    public int direktorlukGüncelle(int id, String adi, String adres) throws SQLException {
        Connection connection = getConnection();
        String query = "UPDATE direktorlukler SET direktorluklerAdi=?,direktorlukler_adres=? where iddirektorlukler=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setString(1, adi);
            psmt.setString(2, adres);
            psmt.setInt(3, id);
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public int direktorlukSil(int id) throws SQLException {
        Connection connection = getConnection();
        String deleteTableSQL = "DELETE FROM direktorlukler WHERE iddirektorlukler=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(deleteTableSQL);
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }
    public int ilanDirektorlukSil(int ilandirid) throws SQLException {
        Connection connection = getConnection();
        String deleteTableSQL = "DELETE FROM ilanlar WHERE direktorid=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(deleteTableSQL);
            psmt.setInt(1, ilandirid);
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public ArrayList<Admin> adminCek(int id) throws SQLException {

        ArrayList<Admin> admins = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs;
        String query = "SELECT * FROM admin where idadmin=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Admin ad = new Admin();
                ad.setIdadmin(rs.getInt("idadmin"));
                ad.setAd(rs.getString("admiAd"));
                ad.setSifre(rs.getString("adminSifre"));
                ad.setEmail(rs.getString("adminEmail"));
                ad.setDirektorluk(rs.getString("adminDirektorluk"));
                admins.add(ad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return admins;
    }

    public int adminGüncelle(int id, String adi, String email, String sifre, String direktorluk) throws
            SQLException {
        Connection connection = getConnection();
        try {
            String query = "UPDATE admin SET admiAd=?,adminSifre=?,adminEmail=?,adminDirektorluk=? where idadmin=?";
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setString(1, adi);
            psmt.setString(2, sifre);
            psmt.setString(3, email);
            psmt.setString(4, direktorluk);
            psmt.setInt(5, id);
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public int adminSil(int id) throws SQLException {
        Connection connection = getConnection();
        String deleteTableSQL = "DELETE FROM admin WHERE idadmin=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(deleteTableSQL);
            psmt.setInt(1, id);
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public ArrayList<ilan> ilanlarCek(int ilanid) throws SQLException {
        ArrayList<ilan> ilans = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs;
        String query = "SELECT * FROM ilanlar i  join direktorlukler d on d.iddirektorlukler = i.direktorid where ilan_id=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setInt(1, ilanid);
            rs = psmt.executeQuery();
            while (rs.next()) {
                ilan il = new ilan();
                il.setDirekid(rs.getInt("direktorid"));
                il.setIlanadi(rs.getString("ilanadi"));
                il.setIlanid(rs.getInt("ilan_id"));
                il.setAciklama(rs.getString("aciklama"));
                il.setBolum(rs.getString("bolum"));
                il.setMinSinif(rs.getInt("min_sinif"));
                il.setMinOrt(rs.getDouble("min_ort"));
                il.setYayintarihi(rs.getString("yayin_tarihi"));
                il.setSonbasvuru(rs.getString("son_basvur"));
                il.setDirektorAdi(rs.getString("direktorluklerAdi"));
                il.setAdres(rs.getString("direktorlukler_adres"));
                ilans.add(il);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return ilans;
    }

    public int ilanGüncelle(int direktorlukid, String ilanadi, int ilanid, String aciklama, String bolum, int sinif,
                            double ort, String ytarih, String starih) throws SQLException {
        Connection connection = getConnection();
        try {
            String query = "UPDATE ilanlar SET ilanadi=?,aciklama=?,bolum=?,min_sinif=?,min_ort=?,yayin_tarihi=?,son_basvur=? where direktorid=? and ilan_id=? ";
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setString(1, ilanadi);
            psmt.setString(2, aciklama);
            psmt.setString(3, bolum);
            psmt.setInt(4, sinif);
            psmt.setDouble(5, ort);
            psmt.setString(6, ytarih);
            psmt.setString(7, starih);
            psmt.setInt(8, direktorlukid);
            psmt.setInt(9, ilanid);
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public int ilanSil(int id) throws SQLException {
        Connection connection = getConnection();
        String deleteTableSQL = "DELETE FROM ilanlar WHERE ilan_id=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(deleteTableSQL);
            psmt.setInt(1,id);
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }
    public int basvuranilanSil(int id) throws SQLException {
        Connection connection = getConnection();
        String deleteTableSQL = "DELETE FROM basvuranlar WHERE ilan_id=?";
        try {
            PreparedStatement psmt = connection.prepareStatement(deleteTableSQL);
            psmt.setInt(1,id);
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }

    public ArrayList<Basvuranlar> basvuranlariCek(int ilanid) throws SQLException {

        ArrayList<Basvuranlar> basvuranlars = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet rs;
        String query = "SELECT * FROM basvuranlar join stajyer on basvuranlar.basvuranTc= stajyer.ogrenci_tc where basvuranlar.ilan_id =?";
        try {
            PreparedStatement psmt =connection.prepareStatement(query);
            psmt.setInt(1,ilanid);
            rs=psmt.executeQuery();
            while (rs.next()) {
                Basvuranlar bas = new Basvuranlar();
                bas.setBasvurantc(rs.getLong("basvuranTc"));
                bas.setBasvurutarihi(rs.getString("basvuruTarihi"));
                bas.setIlanid(rs.getInt("ilan_id"));
                bas.setDurumu(rs.getString("durumu"));
                bas.setOgrenciAdi(rs.getString("ogrenciAdi"));
                basvuranlars.add(bas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return basvuranlars;
    }

    public int onayla(long tc, int ilanid, String durumu) throws SQLException {
        Connection connection = getConnection();
        try {
            String query = "UPDATE basvuranlar SET durumu=? where basvuranTc=? and  ilan_id=?";
            PreparedStatement psmt = connection.prepareStatement(query);
            psmt.setString(1, durumu);
            psmt.setLong(2, tc);
            psmt.setInt(3, ilanid);
            return psmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return 0;
    }


}



