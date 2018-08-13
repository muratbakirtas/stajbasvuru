package profil;
public class Admin {
    private int idadmin;
    private String Ad;
    private String sifre;
    private String email;
    private String direktorluk;


    public Admin(int idadmin, String ad, String sifre, String email, String direktorluk) {
        this.idadmin = idadmin;
        Ad = ad;
        this.sifre = sifre;
        this.email = email;
        this.direktorluk = direktorluk;
    }

    public Admin() {

    }

    public int getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDirektorluk() {
        return direktorluk;
    }

    public void setDirektorluk(String direktorluk) {
        this.direktorluk = direktorluk;
    }
}
